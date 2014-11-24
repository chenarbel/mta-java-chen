package com.mta.chen;

//class
public class Stock {
	//members
	private String StockSymbol;
	private float Ask;
	private float Bid;
	java.util.Date Date;
	
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
	
	//print function
	public String getHtmlDescription(){
		String stockHtmlDetailsString = "<br><b>Stock symbol</b>: " +this.getStockSymbol()+  "<b> Ask</b>: " +this.getAsk()+ "<b> Bid</b>: "+this.getBid()+ "<b> Date</b>: " +this.getDate();
		return stockHtmlDetailsString;
	}
}