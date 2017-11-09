/**
 * Ben Scherer
 * CIS 2212
 * Aug 23, 2017
 * 
 * Description
 */

/**
 * Ben Scherer
 * CIS 2212
 * Aug 23, 2017
 * CalcWithMethods
 * Description
 */
//Import Libraries
import java.util.Scanner;

public class CalcWithMethods {

	/**
	 * @param args
	 */

	// Generate menu and get user input
	public static int getMenuOption() {

		int userInput; // stores user choice
		Scanner scannerObj = new Scanner(System.in); // Object for all user input
		int counter = 0; // counts failed menu choices

		// Display Menu
		System.out.println("\nMenu");
		System.out.println("1. Add");
		System.out.println("2. Subtract");
		System.out.println("3. Multiply");
		System.out.println("4. Divide");
		System.out.println("5. Generate Random Number");
		System.out.println("6. Quit");
		System.out.println("Please select a number between 1-6");
		userInput = scannerObj.nextInt();
		while (userInput < 1 || userInput > 6) {
			if (counter >= 3) {
				System.out.println("ERROR: Threshold of 3 invalid menu inputs reached.  Please try again later");
				System.exit(1);
			}

			System.out.println("Error: Enter a number between 1-6");
			userInput = scannerObj.nextInt();
			counter++;
		}
		return userInput;
	}

	// Add two numbers together
	public static double add( double operand2) {

		return operand1 + operand2;

	}

	// Subract two numbers
	public static double subtract( double operand2) {
		return operand1 - operand2;

	}

	// Multiply two numbers
	public static double multiply( double operand2) {
		return operand1 * operand2;

	}

	// Divide two numbers
	public static double divide( double operand2) {
		if (operand2 == 0) {
			return Double.NaN;
		}
		return operand1 / operand2;

	}

	// return random number
	public static double random(double lowerLimit, double upperLimit) {
		return (lowerLimit + (double) (Math.random() * upperLimit));

	}

	// Get user input and return double
	public static double getOperand(String prompt) {
		Scanner scannerObj = new Scanner(System.in); // Object for all user input
		System.out.println(prompt);
		return scannerObj.nextDouble();
	}

	public static void main(String[] args) {
		int userInput;

		do {
			userInput = getMenuOption(); // Get menu choice and validate input
			switch (userInput) {
			case 1: { // Add two numbers together
				System.out
						.println("Answer: " + add(getOperand("Enter first number"), getOperand("Enter Second Number")));
				break;
			}
			case 2: { // Subtract two numbers
				System.out.println(
						"Answer: " + subtract(getOperand("Enter first number"), getOperand("Enter Second Number")));
				break;
			}
			case 3: { // multiply two numbers
				System.out.println(
						"Answer: " + multiply(getOperand("Enter first number"), getOperand("Enter Second Number")));
				break;
			}
			case 4: { // divide two numbers
				System.out.println(
						"Answer: " + divide(getOperand("Enter first number"), getOperand("Enter Second Number")));
				break;
			}
			case 5: {// generate random number
				System.out.println(
						"Answer: " + random(getOperand("Enter first number"), getOperand("Enter Second Number")));
			}

			}
		} while (userInput != 6);

	}

}
