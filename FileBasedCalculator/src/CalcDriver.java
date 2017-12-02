import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;

/**
 * Ben Scherer
 *  CIS 2212
 *   Nov 8, 2017
 *    CalcDriver
 *    Main program.  Manages User input/Output and calculator objects.
 */
public class CalcDriver {

	// Get user input and return double
	
	public static void main(String[] args) {
		int userInput;

		MemoryCalc calcObj = new MemoryCalc();

		do {
			System.out.println("The current value is " + calcObj.getCurrentValue());
			userInput = calcObj.displayMenu(); // Get menu choice and validate input
			switch (userInput) {
			case 1: { // Add two numbers together
				try {

					calcObj.add(calcObj.getOperand("What is the second number?"));

				} catch (ArithmeticException | InputMismatchException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
				break;
			}
			case 2: { // Subtract two numbers
				try {
					calcObj.subtract(calcObj.getOperand("What is the second number?"));
				} catch (ArithmeticException | InputMismatchException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
				break;

			}
			case 3: { // multiply two numbers
				try {
					calcObj.multiply(calcObj.getOperand("What is the second number?"));
				} catch (ArithmeticException | InputMismatchException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
				break;
			}
			case 4: { // divide two numbers
				try {
					calcObj.divide(calcObj.getOperand("What is the second number?"));
				} catch (ArithmeticException | InputMismatchException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
				break;
			}
			case 5: {// Clear current value
				calcObj.clear();
				break;
			}
			case 6: { // Get file and calculator output
				try {
					
					calcObj.arrToFile();
					
				} catch (IOException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}

			}
		} while (userInput != 7);

		System.out.println("Goodbye!");

	}

}
