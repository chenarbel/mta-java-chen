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
 * this class creats instanse of portfolio service and prints the string to html landing page(befor and after portfolio changes)
 * @author Chen Arbel
 * @since 3/12/14
 */
public class PortfolioServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException{
		
		//portfolio #1
		resp.setContentType("text/html");
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio1 = portfolioService.getPortfolio();
		Stock[] stocks = portfolio1.getStocks();
		StockStatus[] stocksStatus = portfolio1.getStocksStatus();
		//porftgolio #2
		Portfolio portfolio2 = new Portfolio(portfolio1);//gets p#1 parameters
		portfolio2.setTitle("<br></br>Portfolio #2");
		//print
		resp.getWriter().println("<h1>portfolio 1 & 2 original</h1>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		//cdelete the first stock in portfolio #1 and show the difference between it and #2
		resp.getWriter().println("<h1>portfolio 1 & 2 after remove first stock in #1</h1>");
		portfolio1.removeFirstStock(stocks);
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		//change value only in portfolio #2 and show the difference between it and #1
		resp.getWriter().println("<h1>portfolio 1 & 2 after bid's value change in #2</h1>");
		portfolio2.getStocks()[2].setBid((float) 55.55);
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
	}
}