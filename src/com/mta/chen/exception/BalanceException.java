package com.mta.chen.exception;
/**
 * this class inherit Extendes class, init the string with the preferably error- balance error
 * @author Chen Arbel
 * @since 12/01/15
 */
public class BalanceException extends Exception {
	public BalanceException (){
		super("not enough balance to complete purchase");
	}
}
 