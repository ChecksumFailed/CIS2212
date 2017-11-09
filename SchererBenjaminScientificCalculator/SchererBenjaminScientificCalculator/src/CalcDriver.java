/**
 * Ben Scherer
 * CIS 2212
 * Nov 3, 2017
 * CalcDriver
 * Description
 */
public class CalcDriver {

	public static void main(String[] args) {
		int userInput;
		ScientificMemCalc calcObj = new ScientificMemCalc();
		
		do {
			System.out.println("The current value is " + calcObj.getCurrentValue());
			userInput = calcObj.displayMenu(); // Get menu choice and validate input
			switch (userInput) {
			case 1: { // Add two numbers together
				calcObj.add(calcObj.getOperand("What is the second number?"));
				break;
			}
			case 2: { // Subtract two numbers
				calcObj.subtract(calcObj.getOperand("What is the second number?"));
				break;

			}
			case 3: { // multiply two numbers
				calcObj.multiply(calcObj.getOperand("What is the second number?"));
				break;
			}
			case 4: { // divide two numbers
				calcObj.divide(calcObj.getOperand("What is the second number?"));
				break;
			}
			case 5: {
				calcObj.power(calcObj.getOperand("What is the second number?"));
				break;
			}
			case 6: {
				calcObj.logarithm();
				break;
			}
			case 7: {// generate random array
				calcObj.clear();
				break;
			}
	

			}
		} while (userInput != 8);

		System.out.println("Goodbye!");
		

	}

	

}
