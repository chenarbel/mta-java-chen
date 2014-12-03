package com.mta.chen.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.chen.model.Portfolio;
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
			
			PortfolioService portfolioService = new PortfolioService();
			Portfolio portfolio = portfolioService.getPortfolio();
			Stock[] stocks = portfolio.getStocks();

			resp.getWriter().println(portfolio.getHtmlString());
	}
}