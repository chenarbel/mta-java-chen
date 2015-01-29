package com.mta.chen.model;

import java.lang.Object;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import com.mta.chen.exception.BalanceException;
import com.mta.chen.exception.PortfolioFullException;
import com.mta.chen.exception.StockAlreadyExistsException;
import com.mta.chen.exception.StockIncorrectNumberException;
import com.mta.chen.exception.StockNotExistException;
import com.mta.chen.model.StockStatus;

/**
 * An instance of this class represents a portfolio case- stocks with relevant details & it's current status
 * this class includes: constant & premitive members, c'tor & copy c'tor, getters & setters, methods and throw exceptions
 * @author Chen Arbel
 * @param <node>
 * @since 3/12/14
 * @update 12/01/15
 */
public class Portfolio<node>{
	//members
	public final static int SIZE = 5;
	private String title;
	private int portfolioSize;//phisycal size
	private float balance;

	//stocks arrays
	private StockStatus[] stocksStatus = new StockStatus[SIZE];

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
			this.stocksStatus[i] = new StockStatus(getStocksStatus()[i]);//instance of this class
		}
	}

	//list c'tor
	public Portfolio (List<StockStatus> stockStatusList) {
		for (int i =0; i < stockStatusList.size(); i++ ){
			this.stocksStatus[i] = stockStatusList.get(i);
		}
	}
	
	//getters & setters
	public StockStatus[] getStocks() {
		return this.stocksStatus;
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
	 * @throws StockAlreadyExistsException 
	 * @throws PortfolioFullException 
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException {
		boolean doesStockExists = false;

		for (int k = 0; k < portfolioSize; k++){
			if (stocksStatus[k].getStockSymbol().equals(stock.getStockSymbol())){
				doesStockExists = true;
			} 
		}

		if (portfolioSize >= SIZE){
			throw new PortfolioFullException();
		}
		else if (doesStockExists == true){
			throw new StockAlreadyExistsException(stock.getStockSymbol());
		}
		else{
			stocksStatus[portfolioSize] = new StockStatus(stock.getStockSymbol(), stock.getAsk(),stock.getBid(), stock.getDate(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
			this.portfolioSize++;
		}
	}

	/**
	 * @param delete the asked stock, updates size & arrays
	 * @return boolean value: removed or not
	 * @throws StockNotExistException 
	 * @throws StockIncorrectNumberException 
	 */
	public void removetStock(String symbol) throws StockNotExistException, StockIncorrectNumberException{
		boolean doesStockExists = false;
		int currentStockSymbolIndex = 0;

		for (int k = 0; k < portfolioSize; k++){//find curent index
			if (stocksStatus[k].getStockSymbol().equals(symbol)){
				doesStockExists = true;
				currentStockSymbolIndex = k;
			} 
		}

		if (doesStockExists == false){
			throw new StockNotExistException();
		}

		if (currentStockSymbolIndex == SIZE-1){//if the stock is the last one
			sellStock (symbol, this.stocksStatus[currentStockSymbolIndex].getStockQuantity());
			this.portfolioSize--;
		}

		if (doesStockExists == true){//shrink the array: the empty place gets the last's place valus
			sellStock (symbol, this.stocksStatus[currentStockSymbolIndex].getStockQuantity());
			this.stocksStatus[currentStockSymbolIndex] = stocksStatus[portfolioSize-1];
			this.stocksStatus[currentStockSymbolIndex] = stocksStatus[portfolioSize-1];
			this.portfolioSize--;
		}
	}

	/**
	 * @param buy stock, updates balance & quantity
	 * @return boolean value: bought or not
	 * @throws StockNotExistException 
	 * @throws BalanceException 
	 * @throws StockIncorrectNumberException 
	 */
	public void buyStock(String symbol, int quantity) throws StockNotExistException, BalanceException, StockIncorrectNumberException{
		boolean flag = false;
		int index = 0;

		if (balance == 0){
			throw new BalanceException();
		}

		int capablePurchaseQuantity = (int)(Math.floor((double)(balance/stocksStatus[index].Ask)));
		if (capablePurchaseQuantity < quantity){
			throw new BalanceException();
		}

		if (quantity < -1 || quantity == 0){//error
			throw new StockIncorrectNumberException();
		}

		for (int i = 0; i < portfolioSize; i++){//check if stocks exists
			if (stocksStatus[i].getStockSymbol().equals(symbol)){
				flag = true;
				index = i;
			}
		}

		if (flag == false){
			throw new StockNotExistException ();
		} 

		if (quantity == -1){
			int numOfStocks = (int)(Math.floor((double)(balance/stocksStatus[index].Ask)));
			this.stocksStatus[index].stockQuantity += numOfStocks;
			updateBalance(-numOfStocks * stocksStatus[index].Ask);
		}

		updateBalance(-quantity * this.stocksStatus[index].Ask);
		this.stocksStatus[index].stockQuantity += quantity;
	}

	/**
	 * @param sell stock, updates balance & quantity
	 * @return boolean value: sold or not
	 * @throws StockIncorrectNumberException 
	 * @throws StockNotExistException 
	 */
	public void sellStock (String symbol, int quantity) throws StockIncorrectNumberException, StockNotExistException{
		int index = 0;
		boolean flag = false;
		
		if (quantity < -1 || quantity == 0){//error
			throw new StockIncorrectNumberException();
		}

		for (int i = 0; i < portfolioSize; i++){//find index of requested stock symbol
			if (stocksStatus[i].getStockSymbol().equals(symbol)){
				index = i;
				flag = true;
			}
		}
		if (flag == false){
			throw new StockNotExistException ();
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