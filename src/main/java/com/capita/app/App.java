package com.capita.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.capita.app.calculator.Calculator;
import com.capita.app.calculator.impl.StringCalculator;

/**
 * This class main class which runs the Calculator.
 */

public class App {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter no. of testcases (0<T<100): ");
		int noOfTestCases = 0;
		try {
			noOfTestCases = Integer.parseInt(input.nextLine());
		} catch (NumberFormatException nfex) {
			System.out.println("No. of testcase should be valid number between 0 to 100");
		}

		if (0 < noOfTestCases && noOfTestCases < 100) {

			List<String> expressions = new ArrayList<>(noOfTestCases);
			for (int i = 0; i < noOfTestCases; i++)
				expressions.add(input.nextLine());

			Calculator calculator = new StringCalculator();
			List<String> result = expressions.stream().map(expr -> calculator.evaluate(expr))
					.collect(Collectors.toList());
			System.out.println(result);

			input.close();
		} else {
			System.out.println("No. of testcase should be between 0 to 100");
			System.exit(1);
		}
		System.exit(0);
	}

}
