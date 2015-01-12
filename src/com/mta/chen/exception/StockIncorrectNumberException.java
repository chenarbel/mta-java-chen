package com.mta.chen.exception;
/**
 * this class inherit Extendes class, init the string with the preferably error- input error
 * @author Chen Arbel
 * @since 12/01/15
 */
public class StockIncorrectNumberException extends Exception {
	public StockIncorrectNumberException (){
		super("you asked incorrect number of stocks (zero or nagative number)");
	}
}
