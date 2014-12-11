package com.mta.chen.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.chen.model.Portfolio;
import com.mta.chen.model.Portfolio.StockStatus;
import com.mta.chen.model.Stock;
import com.mta.chen.service.PortfolioService;

/**
 * this class creats instanse of portfolio service and prints the string to html landing page 
 * @author Chen Arbel
 * @since 3/12/14
 */
public class PortfolioServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException{
		resp.setContentType("text/html");
		PortfolioService portfolioService = new PortfolioService();
		
		Portfolio portfolio1 = portfolioService.getPortfolio();
		portfolio1.setTitle("Portfolio#1");
		Stock[] stocks = portfolio1.getStocks();
		StockStatus[] stocksStatus = portfolio1.getStocksStatus();
		resp.getWriter().println("<h1><b>port 1 & 2 original</b></h1>");
		resp.getWriter().println(portfolio1.getHtmlString());
		
		Portfolio portfolio2 = new Portfolio(portfolio1);
	
		
		portfolio2.setTitle("Portfolio#2");
		resp.getWriter().println(portfolio2.getHtmlString());
		
		resp.getWriter().println("<h1><b>port 1 & 2 after change 1- remove stock</b></h1>");
		// change first stock from portfolio1
		portfolio1.changeStockValue(stocks, 0);
		// print portfolio1 
		resp.getWriter().println(portfolio1.getHtmlString());
		// print portfolio2
		resp.getWriter().println(portfolio2.getHtmlString());
		
		resp.getWriter().println("<h1><b>port 1 & 2 after port 2 bid's value change- remove stock</h1></b>");
		//change last stock's bid value
		portfolio2.getStocks()[2].setBid((float) 55.5);
		// print portfolio1 
		resp.getWriter().println(portfolio1.getHtmlString());
		// print portfolio2
		resp.getWriter().println(portfolio2.getHtmlString());
	}
}
