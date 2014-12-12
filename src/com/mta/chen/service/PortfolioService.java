package com.mta.chen.service;

import java.util.Calendar;
import java.util.Date;
import java.io.IOException;
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
		Portfolio portfolio1 = new Portfolio("my Portfolio - Portfolio #1");

		Calendar MyDate = Calendar.getInstance();
		MyDate.set(2014, 10, 15);
		Date CohosenDate = MyDate.getTime();

		Stock PIH = new Stock("PIH",(float)12.4,(float)13.1,CohosenDate);
		portfolio1.addStock(PIH);

		Stock AAL = new Stock("AAL",(float)5.5,(float)5.78,CohosenDate);
		portfolio1.addStock(AAL);

		Stock CAAS = new Stock("CAAS",(float)31.5,(float)31.2,CohosenDate);	
		portfolio1.addStock(CAAS);

		return portfolio1;
	}
}