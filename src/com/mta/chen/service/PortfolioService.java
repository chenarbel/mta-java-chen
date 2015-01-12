package com.mta.chen.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.chen.exception.BalanceException;
import com.mta.chen.exception.PortfolioFullException;
import com.mta.chen.exception.StockAlreadyExistsException;
import com.mta.chen.exception.StockIncorrectNumberException;
import com.mta.chen.exception.StockNotExistException;
import com.mta.chen.model.Portfolio;
import com.mta.chen.model.Stock;

/**
 * this class represents:
 * portfolio's instance, date's instance & initialize, stocks instance
 * insert data to stock, add stock to array
 * using in methods, return created portfolio 
 * this class propagates exceptions
 * @author Chen Arbel
 * @since 3/12/14
 */
public class PortfolioService {

	public Portfolio getPortfolio() throws Exception {
		Portfolio portfolio = new Portfolio("<h1>Exercise 9 portfolio</h1>");
		
		Calendar MyDate = Calendar.getInstance();
		MyDate.set(2014, 11, 15);
		Date CohosenDate = MyDate.getTime();
		
		portfolio.setBalance(10000);
		
		Stock PIH = new Stock("PIH",(float)10,(float)8.5,CohosenDate);	
		portfolio.addStock(PIH);
		portfolio.buyStock("PIH", 20);
		
		Stock AAL = new Stock("AAL",(float)30,(float)25.5,CohosenDate);
		portfolio.addStock(AAL);
		portfolio.buyStock("AAL", 30);
		
		Stock CAAS = new Stock("CAAS",(float)20,(float)15.5,CohosenDate);	
		portfolio.addStock(CAAS);
		portfolio.buyStock("CAAS", 40);
		
		portfolio.addStock(CAAS);//throw an exception
		
		portfolio.sellStock("AAL", -1);
		portfolio.removetStock("CAAS");
			
		return portfolio;
	}
}