

/*	Ben Scherer
 * 	CIS 2212
 *  8/23/2017
 *  Menu Driven Calculator
 *  
 */

//Import Libraries
import java.util.Scanner;

public class MenuDrivenCalculator {

	public static void main(String[] args) {
		//Variables
		int userInput;  //Holds menu choice
		Scanner scannerObj = new Scanner(System.in); //Object for all user input
		double firstNum; //first input 
		double secondNum; //second input
		int counter; //Tracks invalid menu inputs
		
		do  {
			counter = 0;
			
			// Display Menu
			System.out.println("Menu");
			System.out.println("1. Add");
			System.out.println("2. Subtract");
			System.out.println("3. Multiply");
			System.out.println("4. Divide");
			System.out.println("5. Generate Random Number");
			System.out.println("6. Quit");
			System.out.println("Please select a number between 1-6");
			
			//user Input
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
			
			
			//Calculate value based on choice
			switch (userInput) {
			case 1: { //Add two numbers together
				System.out.println("Enter first number");
				firstNum = scannerObj.nextDouble();
				System.out.println("Enter first number");
				secondNum = scannerObj.nextDouble();
				System.out.println("Answer:" + (firstNum + secondNum));
				break;			
			}
			case 2: { //Subtract two numbers
				System.out.println("Enter first number");
				firstNum = scannerObj.nextDouble();
				System.out.println("Enter first number");
				secondNum = scannerObj.nextDouble();
				System.out.println("Answer:" + (firstNum - secondNum));
				break;	
			}
			case 3: { //multiply two numbers
				System.out.println("Enter first number");
				firstNum = scannerObj.nextDouble();
				System.out.println("Enter first number");
				secondNum = scannerObj.nextDouble();
				System.out.println("Answer:" + (firstNum * secondNum));
				break;	
			}
			case 4: { //divide two numbers
				System.out.println("Enter first number");
				firstNum = scannerObj.nextDouble();
				System.out.println("Enter first number(non zero)");
				secondNum = scannerObj.nextDouble();
				while (secondNum == 0) {
					System.out.println("ERROR: Cannot divide by zero.  Enter a non-zero number");
					secondNum = scannerObj.nextDouble();
				}
				System.out.println("Answer:" + (firstNum / secondNum));
				break;	
			}
			case 5: {//generate random number
				System.out.println("Answer:" + Math.random());
				break;
			}
	
		}
	} while (userInput != 6);
	
	//exit message
	System.out.println("Goodbye. Thank you for using the CIS 2212 Calculator");
}

}
