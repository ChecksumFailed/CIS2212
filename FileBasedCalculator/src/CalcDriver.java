/**
 * Ben Scherer CIS 2212 Nov 3, 2017 CalcDriver Description
 */
public class CalcDriver {

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
				} catch (ArithmeticException ex) {
					System.out.println("Error: " + ex.toString());
				}
				break;
			}
			case 2: { // Subtract two numbers
				try {
					calcObj.subtract(calcObj.getOperand("What is the second number?"));
				} catch (ArithmeticException ex) {
					System.out.println("Error: " + ex.toString());
				}
				break;

			}
			case 3: { // multiply two numbers
				try {
					calcObj.multiply(calcObj.getOperand("What is the second number?"));
				} catch (ArithmeticException ex) {
					System.out.println("Error: " + ex.toString());
				}
				break;
			}
			case 4: { // divide two numbers
				try {
					calcObj.divide(calcObj.getOperand("What is the second number?"));
				} catch (ArithmeticException ex) {
					System.out.println("Error: " + ex.toString());
				}
				break;
			}
			case 5: {// generate random array
				calcObj.clear();
				break;
			}
			case 6: {
				
			}

			}
		} while (userInput != 8);

		System.out.println("Goodbye!");

	}

}
