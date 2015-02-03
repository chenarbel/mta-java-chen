package com.mta.chen.model;

import java.util.Date;
/**
 * this class:
 * - creats members of portfolio case and initialized it
 * -insert value to otput string  
 * -use setters & getters
 * @author Chen Arbel
 * @since 3/12/14 (update- 4/2/15)
 */
public class Stock {

	//members
	protected String symbol;
	protected float ask;
	protected float bid;
	protected java.util.Date date;

	public Stock(){
		symbol = null;
		ask = 0;
		bid = 0;
		date = null;
	}

	//c'tor (overlodaing)
	public Stock(String StockSymbol, float Ask, float Bid, java.util.Date Date){
		this.symbol = StockSymbol;
		this.ask = Ask;
		this.bid = Bid;
		this.date = Date;
	}

	//copy constructor
	public Stock(Stock stock) {
		this(stock.getSymbol(),stock.getAsk(),stock.getBid(),new Date(stock.date.getTime()));
	}

	//setters & getters
	public void setSymbol(String stockSymbol) {
		symbol = stockSymbol;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getAsk() {
		return ask;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public float getBid() {
		return bid;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public java.util.Date getDate() {
		return date;
	}	

	/**
	 * @param use catenation to insert values to string
	 * @return thr fina string
	 */
	public String getHtmlDescription(){
		String stockHtmlDetailsString = "<br><b>Stock symbol</b>: " +this.getSymbol()+  "<b> Ask</b>: " +this.getAsk()+ "<b> Bid</b>: "+this.getBid()+ "<b> Date</b>: " +this.getDate();
		return stockHtmlDetailsString;
	}
}