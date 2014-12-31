package com.mta.chen.model;

import com.mta.chen.model.StockStatus;

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
	private StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];

	//c'tor (after overloading)
	public Portfolio(String title, int portfolioSize, float balance, StockStatus [] stockStatus){
		this.title = title;
		this.portfolioSize = portfolioSize;
		this.balance = balance;
		for (int i = 0; i < portfolioSize; i++)
		{
			this.stocksStatus[i] = stockStatus[i];
		}
	}

	//copy constructor
	public Portfolio(Portfolio portfolio) {
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();
		this.balance = portfolio.getBalance();
		for(int i = 0; i < portfolioSize; i++)
		{
			this.stocksStatus[i] = new StockStatus(getStocksStatus()[i]);
		}
	}

	//getters & setters
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
			if (stocksStatus[k].getStockSymbol() == stock.getStockSymbol()){
				doesStockExists = true;
			} 
		}
		
		if (portfolioSize >= MAX_PORTFOLIO_SIZE){
			System.out.println("Can�t add new stock, portfolio can have only "+ MAX_PORTFOLIO_SIZE +" stocks");
			return;
		}
		else if (doesStockExists == true){
			System.out.println("Can�t add new stock, it is already exists");
			return;
		}
		else{
			stocksStatus[portfolioSize] = new StockStatus(stock.getStockSymbol(), stock.getAsk(),stock.getBid(), stock.getDate(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
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
			if (stocksStatus[k].getStockSymbol() == symbol){
				doesStockExists = true;
				currentStockSymbolIndex = k;
			} 
		}
		
		if (doesStockExists == false){
			System.out.println("the stock you asked to remove does not exsists");
			return false;
		}

		if (currentStockSymbolIndex == MAX_PORTFOLIO_SIZE-1){//if the stock is the last one
			sellStock (symbol, this.stocksStatus[currentStockSymbolIndex].getStockQuantity());
			this.portfolioSize--;
			return true;
		}

		if (doesStockExists == true){//shrink the array: the empty place gets the last's place valus
			sellStock (symbol, this.stocksStatus[currentStockSymbolIndex].getStockQuantity());
			this.stocksStatus[currentStockSymbolIndex] = stocksStatus[portfolioSize-1];
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
		
		int capablePurchaseQuantity = (int)(Math.floor((double)(balance/stocksStatus[index].Ask)));
		if (capablePurchaseQuantity < quantity){
			System.out.println("you can buy smaller quantity, not enough balance to complete purchase");
			return false;
		}
		
		if (quantity < -1 || quantity == 0){//error
			System.out.println("you asked to buy incorrect number of stocks (zero or nagative number))");
			return false;
		}
		
		for (int i = 0; i < portfolioSize; i++){//check if stocks exists
			if (stocksStatus[i].getStockSymbol() == symbol){
				flag = true;
				index = i;
			}
		}
		
		if (flag == false){
			System.out.println("you asked to buy stock which is not exists");
			return false;
		} 
		
		if (quantity == -1){
			int numOfStocks = (int)(Math.floor((double)(balance/stocksStatus[index].Ask)));
			this.stocksStatus[index].stockQuantity += numOfStocks;
			updateBalance(-numOfStocks * stocksStatus[index].Ask);
			return true;
		}
		
		updateBalance(-quantity * this.stocksStatus[index].Ask);
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
			if (stocksStatus[i].getStockSymbol() == symbol){
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
		
		updateBalance (quantity * this.stocksStatus[index].Bid);
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
			getHtmlString += stocksStatus[i].getHtmlDescription(); 
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
			stocksValue += stockStatus[i].stockQuantity * stockStatus[i].Bid;
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
}