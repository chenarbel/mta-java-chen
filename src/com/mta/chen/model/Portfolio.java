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
	
	//c'tor
	public Portfolio(String title, int portfolioSize){
		title = "unKnown";
		portfolioSize = 0;
	}
	
	//copy constructor
	public Portfolio(Portfolio portfolio) {
		this(portfolio.getTitle(), portfolio.getPortfolioSize());
		}
	
	//stocks arrays
	private Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	private StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];

	//getters & setters
	public Stock[] getStocks(){
		return this.stocks;
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

	/**
	* @param initialized the title
	* @return does not return a value
	*/
	public Portfolio (String title){
		this.title = title;
	}

	/**
	* @param gets stock into place in array and counts it
	* @return does not return a value
	*/
	public void addStock(Stock stock) {
		this.stocks[portfolioSize] = stock;
		this.portfolioSize++;
	}
	
	/**
	* @param gets stock into place in array and counts it
	* @return string of few strings catenation 
	*/
	public String getHtmlString() {
		String getHtmlString = "";
		getHtmlString += "<h1>" + this.title + "</h1>";
		for (int i = 0; i < this.portfolioSize; i++) {
			getHtmlString += stocks[i].getHtmlDescription(); 
		}
		return getHtmlString;
	}
	
	/**
	* An instance of this class represents current status of stock and reccomendation 
	* @author Chen Arbel
	* @since 3/12/14
	*/
	public class StockStatus {
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
		
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
	}
}