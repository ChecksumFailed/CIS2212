import java.util.Scanner;

/**
 * Ben Scherer
 * CIS 2212
 * Nov 3, 2017
 * 
 * Description
 */

/**
 * Ben Scherer CIS 2212 Nov 3, 2017 ScientificCalculator Description
 */
public class ScientificMemCalc extends MemoryCalc {
	
	public ScientificMemCalc() {
		
	}
	
	@Override
	public int displayMenu() {
		
		int userInput; // stores user choice
		Scanner scannerObj = new Scanner(System.in); // Object for all user input
		int counter = 0; // counts failed menu choices

		System.out.println("\nMenu");
		System.out.println("1. Add");
		System.out.println("2. Subtract");
		System.out.println("3. Multiply");
		System.out.println("4. Divide");
		System.out.println("5. Power");
		System.out.println("6. Logarithm");
		System.out.println("7. Clear");
		System.out.println("8. Quit");
		System.out.println("What would you like to do?");
		userInput = scannerObj.nextInt();
		while (userInput < 1 || userInput > 8) {
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
	
	public void power(double powToRaise) {
		setCurrentValue(Math.pow(getCurrentValue(), powToRaise));
	}
	
	public void logarithm() {
		setCurrentValue(Math.log(getCurrentValue()));
	}
	
}
