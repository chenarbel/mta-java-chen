package com.mta.chen.model;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.chen.service.PortfolioService;

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

	//stocks arrays
	private Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	private StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];

	//c'tor
	public Portfolio(String title, int portfolioSize, Stock [] stocks, StockStatus [] stockStatus){
		this.title = title;
		this.portfolioSize = portfolioSize;
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

		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for(int i = 0; i < portfolioSize; i++)
		{
			stocks[i] = new Stock(portfolio.stocks[i]);
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
	
	public void changeStockValue(Stock [] stocks, int i){
		stocks[i].setBid((float) 100.01);
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


		public int getRecommendation() {
			return recommendation;
		}


		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}


		public int getStockQuantity() {
			return stockQuantity;
		}


		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

		//ctor
		public StockStatus (String symbol, float currentBid, float currentAsk, Date date, int recommendation, int stockQuantity){
			this.symbol = getSymbol();
			this.currentBid = getCurrentBid();
			this.currentAsk = getCurrentAsk();
			this.date = getDate();
			this.recommendation = getRecommendation();
			this.stockQuantity = getStockQuantity();	
		}
		
		//copy ctor
		public StockStatus (StockStatus stockStatus){
			this.symbol = stockStatus.getSymbol();
			this.currentBid = stockStatus.getCurrentBid();
			this.currentAsk = stockStatus.getCurrentAsk();
			this.date = stockStatus.getDate();
			this.recommendation =stockStatus.getRecommendation();
			this.stockQuantity = stockStatus.getStockQuantity();
		}
		
		
	}
}