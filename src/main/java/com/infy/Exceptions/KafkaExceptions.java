package com.infy.Exceptions;

/**
 * @author rajesh
 *
 */
public class KafkaExceptions extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param exceptionMessage
	 */
	public KafkaExceptions(String exceptionMessage) {

		super(exceptionMessage);
	}
}
