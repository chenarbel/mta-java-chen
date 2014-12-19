package com.mta.chen.model;

import java.util.Date;
/**
 * An instance of this class represents a portfolio case- stocks with relevant details
 * @author Chen Arbel
 * @since 3/12/14
 */
public class Portfolio {
	//members
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private int portfolioSize;//phisycal size
	private float balance;

	//stocks arrays
	private Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	private StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];

	//c'tor (after overloading)
	public Portfolio(String title, int portfolioSize, float balance, Stock [] stocks, StockStatus [] stockStatus){
		this.title = title;
		this.portfolioSize = portfolioSize;
		this.balance = balance;
		for (int i = 0; i < portfolioSize; i++)
		{
			this.stocks[i] = stocks[i];
			this.stocksStatus[i] = stockStatus[i];
		}
	}

	//copy constructor
	public Portfolio(Portfolio portfolio) {
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();
		this.balance = portfolio.getBalance();
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for(int i = 0; i < portfolioSize; i++)
		{
			this.stocks[i] = new Stock(portfolio.stocks[i]);
		}
	}

	//getters & setters
	public Stock[] getStocks(){
		return this.stocks;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public StockStatus[] getStocksStatus() {
		return this.stocksStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * @param initialized the title
	 * @return does not return a value
	 */
	public Portfolio (String title){
		this.title = title;
	}

	//ìòãëï 
	/**
	 * @param gets stock into place in array and counts it
	 * @return does not return a value
	 */
	public void addStock(Stock stock) {
		boolean doesStockExists = false;
		
		for (int k = 0; k < portfolioSize; k++){
			if (stocks[k].getStockSymbol() == stock.getStockSymbol()){
				doesStockExists = true;
			} 
		}
		
		if (portfolioSize >= MAX_PORTFOLIO_SIZE){
			System.out.println("Can’t add new stock, portfolio can have only"+ MAX_PORTFOLIO_SIZE +"stocks");
			return;
		}
		else if (doesStockExists == true){
			System.out.println("Can’t add new stock, it is already exists");
			return;
		}
		else{
			this.stocks[portfolioSize] = stock;
			stocksStatus[portfolioSize] = new Portfolio.StockStatus(stock.getStockSymbol(), stock.getBid(), stock.getAsk(), stock.getDate(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
			this.portfolioSize++;
		}
	}
	
	/**
	 * @param gets stock's and delete the first stock, updates size
	 */
	public boolean removetStock(String symbol){
		boolean doesStockExists = false;
		// int countCurrentStockSymbol = 0;
		int currentStockSymbolIndex = 0;
		
		for (int k = 0; k < portfolioSize; k++){//find curent index
			if (stocks[k].getStockSymbol() == symbol){
				doesStockExists = true;
				//countCurrentStockSymbol++;
				currentStockSymbolIndex = k;
			} 
		}
		
		if (doesStockExists == false){
			System.out.println("the stock you asked to remove does not exsists");
			return false;
		}

		//if (countCurrentStockSymbol > 1){
		//	System.out.println("there are more then one stock with the symbol you asked to remove");
		//	return false;
		//}

		if (currentStockSymbolIndex == MAX_PORTFOLIO_SIZE-1){//if the stock is the last one
			sellStock (symbol, this.stocksStatus[currentStockSymbolIndex].stockQuantity);
			this.portfolioSize--;
			return true;
		}

		if (doesStockExists == true){//shrink the array: the empty pkace gets the last's place valus
			sellStock (symbol, this.stocksStatus[currentStockSymbolIndex].stockQuantity);
			this.stocks[currentStockSymbolIndex] = stocks[portfolioSize-1];
			this.stocksStatus[currentStockSymbolIndex] = stocksStatus[portfolioSize-1];
			this.portfolioSize--;
			return true;
		}
		return false;
	}
	
	public boolean buyStock(String symbol, int quantity){
		boolean flag = false;
		int index = 0;
		
		if (balance < 0){
			System.out.println("you don't have enough money to buy stocks");
			return false;
		}
		if (quantity < -1 || quantity == 0){//error
			System.out.println("you asked to buy incorrect numbers of stocks (zero or nagative number))");
			return false;
		}
		
		for (int i = 0; i < portfolioSize; i++){//check if stocks exists
			if (stocks[i].getStockSymbol() == symbol){
				flag = true;
				index = i;
			}
		}
		
		if (flag == false){
			System.out.println("you asked to buy stock which is not exists");
			return false;
		} 
		
		if (quantity == -1){
			int numOfStocks = (int)(Math.floor((double)(balance/stocksStatus[index].currentAsk)));
			this.stocksStatus[index].stockQuantity += numOfStocks;
			balance -= quantity * numOfStocks;
			return true;
		}
		
		balance -= quantity * this.stocksStatus[index].currentAsk;
		this.stocksStatus[index].stockQuantity += quantity;
		return true;
	}
	
	public boolean sellStock (String symbol, int quantity){
		int index = 0;
		if (quantity < -1 || quantity == 0){//error
			System.out.println("you asked to sell incorrect numbers of stocks (zero or nagative number))");
			return false;
		}
		
		for (int i = 0; i < portfolioSize; i++){//find index of requested stock symbol
			if (stocks[i].getStockSymbol() == symbol){
				index = i;
			}
		}
		
		if (quantity > this.stocksStatus[index].stockQuantity){//help to user 
			System.out.println("you asked to sell more stocks than you actually have, we sell them all: "+ this.stocksStatus[index].stockQuantity +" stocks");
			quantity = this.stocksStatus[index].stockQuantity;
		}
		
		if (quantity == -1 || quantity == this.stocksStatus[index].stockQuantity){//sell all stocks
			quantity = this.stocksStatus[index].stockQuantity;
		}
		
		balance += quantity * this.stocksStatus[index].currentBid;
		this.stocksStatus[index].stockQuantity -= quantity;

		if (this.stocksStatus[index].stockQuantity == 0){
			System.out.println("attention! your stock is empty (stock quantity is zero)");
		}
		return true;
	}
	
	/**
	 * @param gets stock into place in array and counts it
	 * @return string of few strings catenation 
	 */
	public String getHtmlString() {
		String getHtmlString = "";
		getHtmlString += "<br>" + this.title + "</br>";
		for (int i = 0; i < this.portfolioSize; i++) {
			getHtmlString += stocks[i].getHtmlDescription(); 
		}
		
		getHtmlString += "<br><b> Total portfolio value: </b>" +getTotalValue(getStocksStatus())+ "$,<b> Total Stocks value: </b>" +getStocksValue(getStocksStatus())+" $,<b> Balance: </b>"+getBalance()+" $</br>";
		return getHtmlString;
	}
	
	//java doc
	public void updateBalance(float amount){
		this.balance = amount;
		if (amount < 0){
			System.out.println("notice, the value is negative!");
		}
	}
	
	//java doc
	public float getStocksValue (StockStatus [] stockStatus){
		float stocksValue = 0;
		for (int i = 0; i < portfolioSize; i++){
			stocksValue += stockStatus[i].stockQuantity * stockStatus[i].currentBid;
		}
		return stocksValue;
	}
	
	//java doc
	public float getTotalValue (StockStatus [] stockStatus){
		float totalValue = getStocksValue(stockStatus) + getBalance();
		return totalValue;
	}

	//java doc
	public enum ALGO_RECOMMENDATION {
		DO_NOTHING(0), BUY(1) ,SELL(2);
		private int recommend;
		private ALGO_RECOMMENDATION(int recommend) {
			this.recommend = recommend;
		} 
	}

	/**
	 * An instance of this class represents current status of stock and reccomendation
	 * this class includes:
	 * -constants members
	 * -members
	 * -setter & gettes to those members
	 * -c'tor which gets values (overloading) and initial the members
	 * -copy c'tor which gets a type (stockStatus) and insert the values from the c'tors
	 * @author Chen Arbel
	 * @since 12/12/14
	 */
	public class StockStatus {

		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private ALGO_RECOMMENDATION recommendation;
		private int stockQuantity;

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public float getCurrentBid() {
			return currentBid;
		}

		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}

		public float getCurrentAsk() {
			return currentAsk;
		}

		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

		//c'tor
		public StockStatus (String symbol, float currentBid, float currentAsk, Date date, ALGO_RECOMMENDATION recommendation, int stockQuantity){
			this.symbol = symbol;
			this.currentBid = currentBid;//ìäîùéê 
			this.currentAsk = currentAsk;
			this.date = date;
			this.recommendation = recommendation;
			this.stockQuantity = stockQuantity;	
		}

		//copy c'tor
		public StockStatus (StockStatus stockStatus){
			this.symbol = stockStatus.getSymbol();
			this.currentBid = stockStatus.getCurrentBid();
			this.currentAsk = stockStatus.getCurrentAsk();
			this.date = new Date (stockStatus.date.getTime());
			this.recommendation =stockStatus.getRecommendation();
			this.stockQuantity = stockStatus.getStockQuantity();
		}
	}
}