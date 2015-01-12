package com.mta.chen.exception;
/**
 * this class inherit Extendes class, init the string with the preferably error- unexist value
 * @author Chen Arbel
 * @since 12/01/15
 */
public class StockNotExistException extends Exception{
	public StockNotExistException (){
		super("the stock you asked to remove does not exsists");
	}
}