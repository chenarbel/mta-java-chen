package com.mta.chen.exception;
/**
 * this class inherit Extendes class, init the string with the preferably error- limit of array error
 * @author Chen Arbel
 * @since 12/01/15
 */
@SuppressWarnings("serial")
public class PortfolioFullException extends Exception {
	public PortfolioFullException (){
	super("Can’t add new stock, portfolio have reached the maximum number of stocks");
	}
}
