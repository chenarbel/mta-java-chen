package com.mta.chen.model;

import java.util.Date;
/**
 * this class:
 * - creats members of portfolio case and initialized it
 * -insert value to otput string  
 * -use setters & getters
 * @author Chen Arbel
 * @since 3/12/14
 */
public class Stock {

	//members
	protected String StockSymbol;
	protected float Ask;
	protected float Bid;
	protected java.util.Date Date;

	public Stock(){
		this.StockSymbol = null;
		this.Ask = 0;
		this.Bid = 0;
		this.Date = null;
	}

	//c'tor (overlodaing)
	public Stock(String StockSymbol, float Ask, float Bid, java.util.Date Date){
		this.StockSymbol = StockSymbol;
		this.Ask = Ask;
		this.Bid = Bid;
		this.Date = Date;
	}

	//copy constructor
	public Stock(Stock stock) {
		this(stock.getStockSymbol(),stock.getAsk(),stock.getBid(),new Date(stock.Date.getTime()));
	}

	//setters & getters
	public void setStockSymbol(String stockSymbol) {
		StockSymbol = stockSymbol;
	}
	public String getStockSymbol() {
		return StockSymbol;
	}
	public void setAsk(float ask) {
		Ask = ask;
	}
	public float getAsk() {
		return Ask;
	}
	public void setBid(float bid) {
		Bid = bid;
	}
	public float getBid() {
		return Bid;
	}
	public void setDate(java.util.Date date) {
		Date = date;
	}
	public java.util.Date getDate() {
		return Date;
	}	

	/**
	 * @param use catenation to insert values to string
	 * @return thr fina string
	 */
	public String getHtmlDescription(){
		String stockHtmlDetailsString = "<br><b>Stock symbol</b>: " +this.getStockSymbol()+  "<b> Ask</b>: " +this.getAsk()+ "<b> Bid</b>: "+this.getBid()+ "<b> Date</b>: " +this.getDate();
		return stockHtmlDetailsString;
	}
}