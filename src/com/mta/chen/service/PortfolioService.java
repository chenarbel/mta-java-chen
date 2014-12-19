package com.mta.chen.service;

import java.util.Calendar;
import java.util.Date;
import com.mta.chen.model.Portfolio;
import com.mta.chen.model.Stock;

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
		Portfolio portfolio = new Portfolio("<h1>Exercise 7 portfolio</h1>");

		Calendar MyDate = Calendar.getInstance();
		MyDate.set(2014, 11, 15);
		Date CohosenDate = MyDate.getTime();

		Stock PIH = new Stock("PIH",(float)10,(float)8.5,CohosenDate);
		portfolio.addStock(PIH);
		portfolio.buyStock("PIH", 20);
		
		Stock AAL = new Stock("AAL",(float)30,(float)25.5,CohosenDate);
		portfolio.addStock(AAL);
		portfolio.buyStock("AAL", 30);
		portfolio.sellStock("AAL", -1);

		Stock CAAS = new Stock("CAAS",(float)20,(float)15.5,CohosenDate);	
		portfolio.addStock(CAAS);
		portfolio.buyStock("CAAS", 40);
		portfolio.removetStock("CAAS");
		
		portfolio.updateBalance(10000);
		return portfolio;
	}
}