import java.util.Scanner;

/**
 * Ben Scherer
 * CIS 2212
 * Sep 27, 2017
 * Calculator
 * Description:  Stateful calculator using classes and objects.
 */
public class Calculator {
	//Fields
	private double currentValue = 0.0;
	
	//constructors
	Calculator() {
	}
	
	Calculator(double newValue) {
		currentValue = newValue;
	}
	
	//methods
	public static int displayMenu() {
		// Display Menu
		int userInput; // stores user choice
		Scanner scannerObj = new Scanner(System.in); // Object for all user input
		int counter = 0; // counts failed menu choices
		
		System.out.println("\nMenu");
		System.out.println("1. Add");
		System.out.println("2. Subtract");
		System.out.println("3. Multiply");
		System.out.println("4. Divide");
		System.out.println("5. Clear");
		System.out.println("6. Quit");
		System.out.println("What would you like to do?");
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
	
	// Get user input and return double
	public static double getOperand(String prompt) {

		Scanner scannerObj = new Scanner(System.in); // Object for all user input
		System.out.println(prompt);
		
		return scannerObj.nextDouble();
		
	
		
	}
	
	// Add two numbers together
	public void add( double operand2) {

		currentValue += operand2;

	}

	// Subract two numbers
	public void subtract( double operand2) {
		currentValue -= operand2;

	}

	// Multiply two numbers
	public void multiply( double operand2) {
		currentValue *= operand2;
	}

	// Divide two numbers
	public void divide( double operand2) {
		if (operand2 == 0) {
			currentValue = Double.NaN;
		} else {
			currentValue /= operand2;
		}

	}
	
	//Reset currentValue to 0
	public void clear() {
		currentValue = 0.0;
	}
	
	//Get current value
	public double getCurrentValue() {
		return currentValue;
	}
	
	
	public static void main(String[] args) {
		int userInput;
		Calculator calcObj = new Calculator();
		
		do {
			System.out.println("The current value is " + calcObj.getCurrentValue());
			userInput = displayMenu(); // Get menu choice and validate input
			switch (userInput) {
			case 1: { // Add two numbers together
				calcObj.add(getOperand("What is the second number?"));
				break;
			}
			case 2: { // Subtract two numbers
				calcObj.subtract(getOperand("What is the second number?"));
				break;

			}
			case 3: { // multiply two numbers
				calcObj.multiply(getOperand("What is the second number?"));
				break;
			}
			case 4: { // divide two numbers
				calcObj.divide(getOperand("What is the second number?"));
				break;
			}
			case 5: {// generate random array
				calcObj.clear();
				break;
			}
	

			}
		} while (userInput != 6);

		System.out.println("Goodbye!");
		

	}
	
}
