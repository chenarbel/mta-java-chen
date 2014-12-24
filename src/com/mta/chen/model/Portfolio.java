package com.mta.chen.model;

import java.util.Date;
/**
 * An instance of this class represents a portfolio case- stocks with relevant details & it's current status
 * this class includes: constant & premitive members, c'tor & copy c'tor, getters & setters, methods and inner class
 * @author Chen Arbel
 * @since 3/12/14
 */
public class Portfolio{
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

	/**
	 * @param gets stock to the array according to limits
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
			System.out.println("Can’t add new stock, portfolio can have only "+ MAX_PORTFOLIO_SIZE +" stocks");
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
	 * @param delete the asked stock, updates size & arrays
	 * @return boolean value: removed or not
	 */
	public boolean removetStock(String symbol){
		boolean doesStockExists = false;
		int currentStockSymbolIndex = 0;
		
		for (int k = 0; k < portfolioSize; k++){//find curent index
			if (stocks[k].getStockSymbol() == symbol){
				doesStockExists = true;
				currentStockSymbolIndex = k;
			} 
		}
		
		if (doesStockExists == false){
			System.out.println("the stock you asked to remove does not exsists");
			return false;
		}

		if (currentStockSymbolIndex == MAX_PORTFOLIO_SIZE-1){//if the stock is the last one
			sellStock (symbol, this.stocksStatus[currentStockSymbolIndex].stockQuantity);
			this.portfolioSize--;
			return true;
		}

		if (doesStockExists == true){//shrink the array: the empty place gets the last's place valus
			sellStock (symbol, this.stocksStatus[currentStockSymbolIndex].stockQuantity);
			this.stocks[currentStockSymbolIndex] = stocks[portfolioSize-1];
			this.stocksStatus[currentStockSymbolIndex] = stocksStatus[portfolioSize-1];
			this.portfolioSize--;
			return true;
		}
		return false;
	}
	
	/**
	 * @param buy stock, updates balance & quantity
	 * @return boolean value: bought or not
	 */
	public boolean buyStock(String symbol, int quantity){
		boolean flag = false;
		int index = 0;
		
		if (balance == 0){
			System.out.println("your balance is zero, can't buy stocks");
			return false;
		}
		
		int capablePurchaseQuantity = (int)(Math.floor((double)(balance/stocksStatus[index].currentAsk)));
		if (capablePurchaseQuantity < quantity){
			System.out.println("you can buy smaller quantity, not enough balance to complete purchase");
			return false;
		}
		
		if (quantity < -1 || quantity == 0){//error
			System.out.println("you asked to buy incorrect number of stocks (zero or nagative number))");
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
			updateBalance(-numOfStocks * stocksStatus[index].currentAsk);
			return true;
		}
		
		updateBalance(-quantity * this.stocksStatus[index].currentAsk);
		this.stocksStatus[index].stockQuantity += quantity;
		return true;
	}
	
	/**
	 * @param sell stock, updates balance & quantity
	 * @return boolean value: sold or not
	 */
	public boolean sellStock (String symbol, int quantity){
		int index = 0;
		if (quantity < -1 || quantity == 0){//error
			System.out.println("you asked to sell incorrect number of stocks (zero or nagative number))");
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
		
		updateBalance (quantity * this.stocksStatus[index].currentBid);
		this.stocksStatus[index].stockQuantity -= quantity;

		if (this.stocksStatus[index].stockQuantity == 0){
			System.out.println("attention! your current stock is empty (stock quantity is zero)");
		}
		return true;
	}
	
	/**
	 * @param gets stock's details string
	 * @return string of few strings catenation 
	 */
	public String getHtmlString() {
		String getHtmlString = "";
		getHtmlString += this.title;
		for (int i = 0; i < this.portfolioSize; i++) {
			getHtmlString += stocks[i].getHtmlDescription(); 
		}
		
		getHtmlString += "<br><b> Total portfolio value: </b>" +getTotalValue(getStocksStatus())+ "$,<b> Total Stocks value: </b>" +getStocksValue(getStocksStatus())+" $,<b> Balance: </b>"+getBalance()+" $</br>";
		return getHtmlString;
	}
	
	/**
	 * @param gets amount and insert its value
	 * @return does not return a value
	 */
	public void updateBalance(float amount){
		this.balance += amount;
	}
	
	/**
	 * @param gets current status array and calculates the stock's value 
	 * @return return the value
	 */
	public float getStocksValue (StockStatus [] stockStatus){
		float stocksValue = 0;
		for (int i = 0; i < portfolioSize; i++){
			stocksValue += stockStatus[i].stockQuantity * stockStatus[i].currentBid;
		}
		return stocksValue;
	}
	
	/**
	 * @param gets current status array and calculates the portfolio's total value 
	 * @return return the total value
	 */
	public float getTotalValue (StockStatus [] stockStatus){
		float totalValue = getStocksValue(stockStatus) + getBalance();
		return totalValue;
	}

	/**
	 * create enums as a constants
	 */
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
	 * -constants members, premitive members
	 * -setter & gettes to those members
	 * -c'tor which gets values (overloading), copy c'tor
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