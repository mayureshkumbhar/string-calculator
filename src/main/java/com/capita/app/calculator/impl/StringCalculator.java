package com.capita.app.calculator.impl;

import static com.capita.app.common.Constants.ADDITION;
import static com.capita.app.common.Constants.BLANK;
import static com.capita.app.common.Constants.DIGIT_REGEX;
import static com.capita.app.common.Constants.DIVISION;
import static com.capita.app.common.Constants.INVLD_EXPR;
import static com.capita.app.common.Constants.LEFT_PARENTHESIS;
import static com.capita.app.common.Constants.MULTIPLICATION;
import static com.capita.app.common.Constants.POWER;
import static com.capita.app.common.Constants.RIGHT_PARENTHESIS;
import static com.capita.app.common.Constants.SUBSTRACTION;

import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Stack;

import com.capita.app.calculator.Calculator;

/**
 * This class provides implementation for {@link Calculator} interface.
 *
 */
public class StringCalculator implements Calculator {

	private static final DecimalFormat FORMAT = new DecimalFormat("0.##");

	@Override
	public String evaluate(String expression) {
		if (null != expression && !BLANK.equals(expression)) {
			Stack<Double> values = new Stack<Double>();
			Stack<String> ops = new Stack<String>();
			try {
				for (String token : expression.split(BLANK)) {
					if (token.matches(DIGIT_REGEX))
						values.push(Double.parseDouble(token));

					else if (LEFT_PARENTHESIS.equals(token))
						ops.push(token);

					else if (RIGHT_PARENTHESIS.equals(token)) {
						while (!LEFT_PARENTHESIS.equals(ops.peek()))
							values.push(applyOperator(ops.pop(), values.pop(), values.pop()));
						ops.pop();

					} else if (isOperator(token)) {
						while (!ops.empty() && hasPrecedence(token, ops.peek()))
							values.push(applyOperator(ops.pop(), values.pop(), values.pop()));
						ops.push(token);
					}
				}

				while (!ops.empty())
					values.push(applyOperator(ops.pop(), values.pop(), values.pop()));

				return (values.size() == 1) ? FORMAT.format(values.pop()).toString() : INVLD_EXPR;

			} catch (EmptyStackException esex) {
				return INVLD_EXPR;
			} 
			
		} else
			return INVLD_EXPR;

	}

	/**
	 * Method applies operator over operands.
	 * 
	 * @param operator
	 * @param operand2
	 * @param operand1
	 * @return {@link Double}
	 */
	private Double applyOperator(String operator, Double operand2, Double operand1) {

		switch (operator) {
		case POWER:
			return Math.pow(operand1, operand2);
		case ADDITION:
			return operand1 + operand2;
		case SUBSTRACTION:
			return operand1 - operand2;
		case MULTIPLICATION:
			return operand1 * operand2;
		case DIVISION:
			if (0 == operand2)
				throw new UnsupportedOperationException("Divide by 0 not supported");
			return operand1 / operand2;
		}
		return 0D;
	}

	/**
	 * Returns true if 'tosOperator' has higher or same precedence as
	 * 'currOperator', otherwise returns false.
	 * 
	 * @param currOperator
	 * @param tosOperator
	 * @return {@link Boolean}
	 */
	private boolean hasPrecedence(String currOperator, String tosOperator) {
		if (LEFT_PARENTHESIS.equals(tosOperator) || RIGHT_PARENTHESIS.equals(tosOperator))
			return false;
		else if (POWER.equals(currOperator) && (MULTIPLICATION.equals(tosOperator) || DIVISION.equals(tosOperator)))
			return false;
		else if (POWER.equals(currOperator) && (ADDITION.equals(tosOperator) || SUBSTRACTION.equals(tosOperator)))
			return false;
		else if ((MULTIPLICATION.equals(currOperator) || DIVISION.equals(currOperator))
				&& (ADDITION.equals(tosOperator) || SUBSTRACTION.equals(tosOperator)))
			return false;
		else
			return true;
	}

	/**
	 * Method check whether current token is operator or not
	 * 
	 * @param token
	 * @return {@link Boolean}
	 */
	private boolean isOperator(String token) {
		return (POWER.equals(token) || MULTIPLICATION.equals(token) || DIVISION.equals(token) || ADDITION.equals(token)
				|| SUBSTRACTION.equals(token));
	}
}
