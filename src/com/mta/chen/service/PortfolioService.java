package com.mta.chen.service;

import java.util.Calendar;
import java.util.Date;
import java.io.IOException;
import com.mta.chen.model.Portfolio;
import com.mta.chen.model.Stock;
import com.mta.chen.servlet.StockDetailsServlet;

/**
* this class represents:
* -portfolio instance
* -date instance & initialize
* -stocks instance
* -insert data to stock
* -add stock to array
* this class return created portfolio 
* @author Chen Arbel
* @since 3/12/14
*/
public class PortfolioService {
	
	public Portfolio getPortfolio() {
		Portfolio portfolio = new Portfolio("myPortfolio");
		
		Calendar MyDate = Calendar.getInstance();
		MyDate.set(2014, 10, 15);
		Date CohosenDate = MyDate.getTime();
		
		Stock PIH = new Stock();
		Stock AAL = new Stock();
		Stock CAAS = new Stock();	
		
	PIH.setStockSymbol("PIH");
	PIH.setAsk((float)12.4);
	PIH.setBid((float)13.1);
	PIH.setDate(CohosenDate);
	portfolio.addStock(PIH);
	
	AAL.setStockSymbol("AAL");
	AAL.setAsk((float)5.5);
	AAL.setBid((float)5.78);
	AAL.setDate(CohosenDate);
	portfolio.addStock(AAL);
	
	CAAS.setStockSymbol("CAAS");
	CAAS.setAsk((float)31.5);
	CAAS.setBid((float)31.2);
	CAAS.setDate(CohosenDate);
	portfolio.addStock(CAAS);
	
	return portfolio;
	}
}