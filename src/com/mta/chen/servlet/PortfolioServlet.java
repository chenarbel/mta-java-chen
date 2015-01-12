package com.mta.chen.servlet;

import java.io.IOException;

import com.mta.chen.exception.BalanceException;
import com.mta.chen.exception.PortfolioFullException;
import com.mta.chen.exception.StockAlreadyExistsException;
import com.mta.chen.exception.StockIncorrectNumberException;
import com.mta.chen.exception.StockNotExistException;
import com.mta.chen.model.StockStatus;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.chen.model.Portfolio;
import com.mta.chen.model.Stock;
import com.mta.chen.service.PortfolioService;

/**
 * this class creats instanse of portfolio service and prints the string to html landing page, catches exceptions
 * @author Chen Arbel
 * @since 3/12/14
 * @update 12/01/15 
 */
public class PortfolioServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException{
		
		try {
			resp.setContentType("text/html");
			PortfolioService portfolioService = new PortfolioService();
			Portfolio portfolio = portfolioService.getPortfolio();
		} catch (Exception e) {
			resp.getWriter().println(e.getMessage());
		} 
		//Stock[] stocks = portfolio.getStocksStatus();
		//StockStatus[] stocksStatus = portfolio.getStocksStatus();
	}
}