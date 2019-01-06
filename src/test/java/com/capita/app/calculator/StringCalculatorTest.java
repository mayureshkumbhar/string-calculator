package com.capita.app.calculator;

import static com.capita.app.common.Constants.INVLD_EXPR;

import org.junit.Assert;
import org.junit.Test;

import com.capita.app.calculator.impl.StringCalculator;

/**
 * This class contains JUnit test cases for {@code StringCalculator}
 */
public class StringCalculatorTest {

	Calculator calculator = new StringCalculator();

	@Test
	public void testValidExpresssion1() {
		Assert.assertEquals("5", calculator.evaluate("3+2"));
	}

	@Test
	public void testValidExpresssion2() {
		Assert.assertEquals("6", calculator.evaluate("(3*2)"));
	}

	@Test
	public void testValidExpresssion3() {
		Assert.assertEquals("158", calculator.evaluate("7+(6*5^2+3-4/2)"));
	}

	@Test
	public void testValidExpresssion4() {
		Assert.assertEquals("206", calculator.evaluate("(7+6)*2^(1+3)-(4/2)"));

	}

	@Test
	public void testValidExpresssion5() {
		Assert.assertEquals("156.5", calculator.evaluate("7+(6*5^2+(3-4)/2)"));
	}

	@Test
	public void testValidExpresssion6() {
		Assert.assertEquals("-3", calculator.evaluate("(8*5/8)-(3/1)-5"));

	}

	@Test
	public void testInvalidExpresssion4() {
		Assert.assertEquals(INVLD_EXPR, calculator.evaluate("(7+(6*5^2+3-4/2)"));
	}

	@Test
	public void testInvalidExpresssion5() {
		Assert.assertEquals(INVLD_EXPR, calculator.evaluate("(7+(6*5(^2)+3-4/2)"));
	}

	@Test
	public void testInvalidExpresssion6() {
		Assert.assertEquals(INVLD_EXPR, calculator.evaluate("7+(67(56*2))"));
	}

	@Test
	public void testInvalidExpresssion7() {
		Assert.assertEquals(INVLD_EXPR, calculator.evaluate("7+(67(56*22+))"));
	}

	@Test
	public void testInvalidExpresssion8() {
		Assert.assertEquals(INVLD_EXPR, calculator.evaluate("7+(3^a)"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testDivideByZeroExpresssion() {
		calculator.evaluate("(7+(6*5(^2)+3-4/0)");
	}

}
