package com.mta.chen.exception;
/**
 * this class inherit Extendes class, init the string with the preferably error- double values error
 * @author Chen Arbel
 * @since 12/01/15
 */
public class StockAlreadyExistsException extends Exception {
	 public StockAlreadyExistsException(String str) {
		 super("the stock you askes to add is already exists");
 }
}
