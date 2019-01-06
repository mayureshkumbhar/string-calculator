package com.capita.app.calculator;

/**
 * Interface defines contract for Calculator functionality.
 */
public interface Calculator {

	/**
	 * Method to evaluate given String expression and return result or invalid
	 * expression if failed.
	 * 
	 * @param expression
	 * @return {@link String}
	 */
	public String evaluate(String expression);
}
