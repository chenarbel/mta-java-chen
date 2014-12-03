package com.mta.chen.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.chen.model.Stock;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException{
		
		//constructor
		Calendar MyDate = Calendar.getInstance();//date calculate
		MyDate.set(2014, 10, 15);
		java.util.Date MyDate1 = MyDate.getTime();
		
		Stock PIH = new Stock();//instances
		Stock AAL = new Stock();
		Stock CAAS = new Stock();
	
		resp.setContentType("text/html");//print
		
		PIH.setStockSymbol("PIH");//initial stock
		PIH.setAsk((float)12.4);
		PIH.setBid((float)13.1);
		PIH.setDate(MyDate1);
		resp.getWriter().println(PIH.getHtmlDescription());//link to class stock & print
		
		AAL.setStockSymbol("AAL");//initial stock
		AAL.setAsk((float)5.5);
		AAL.setBid((float)5.78);
		AAL.setDate(MyDate1);
		resp.getWriter().println(AAL.getHtmlDescription());//link to class stock & print
		
		CAAS.setStockSymbol("CAAS");//initial stock
		CAAS.setAsk((float)31.5);
		CAAS.setBid((float)31.2);
		CAAS.setDate(MyDate1);
		resp.getWriter().println(CAAS.getHtmlDescription());//link to class stock & print
	}
}
