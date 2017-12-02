import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Ben Scherer CIS 2212 11/7/2017 Calculator Description: Stateful calculator
 * using classes and objects.
 */
public class MemoryCalc {
	// Fields
	private double currentValue = 0.0;
	private ArrayList<String> calcOutput = new ArrayList<String>(); // Stores history of calculator

	// constructors
	MemoryCalc() {
		calcOutput.add("Initial Value is " + currentValue);
	}

	MemoryCalc(double newValue) {
		currentValue = newValue;
		calcOutput.add("Initial Value is " + currentValue);
	}

	// Add two numbers together
	public void add(double operand2) throws ArithmeticException {
		double oldValue = currentValue;
		currentValue += operand2;
		calcOutput.add(String.format("%s + %s = %s", oldValue, operand2, currentValue));

	}

	// Subtract two numbers
	public void subtract(double operand2) throws ArithmeticException {
		double oldValue = currentValue;
		currentValue -= operand2;
		calcOutput.add(String.format("%s - %s = %s", oldValue, operand2, currentValue));

	}

	// Multiply two numbers
	public void multiply(double operand2) throws ArithmeticException {
		double oldValue = currentValue;
		currentValue *= operand2;
		calcOutput.add(String.format("%s * %s = %s", oldValue, operand2, currentValue));
	}

	// Divide two numbers
	public void divide(double operand2) throws ArithmeticException {
		double oldValue = currentValue;
		if (operand2 == 0) {

			currentValue = Double.NaN;
		} else {
			currentValue /= operand2;
		}
		calcOutput.add(String.format("%s / %s = %s", oldValue, operand2, currentValue));
	}

	// Reset currentValue to 0
	public void clear() {
		currentValue = 0.0;
		calcOutput.add("Cleared");
	}

	// Get current value
	public double getCurrentValue() {
		return currentValue;
	}

	// Sets currentvalue
	public void setCurrentValue(double newValue) {
		currentValue = newValue;
	}

	public double getOperand(String prompt) throws InputMismatchException {

		try {
			Scanner scannerObj = new Scanner(System.in); // Object for all user input

			System.out.println(prompt);

			return scannerObj.nextDouble();
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Second number must must be a double");

		}
	}

	// writes array to file
	public void arrToFile() throws IOException {
		File outFile = getFile();
		java.io.PrintWriter outObj = new java.io.PrintWriter(outFile);  //Get file object
		displayOutput("Writing calculator output to: " + outFile.getAbsolutePath());
		for (String tmpStr : calcOutput) {
			outObj.println(tmpStr);
		}
		outObj.close();

	}
	
	//Writes output to screen.   Can be overriden to not use system.out
   void displayOutput(String strOutput) {
	   System.out.println(strOutput);
	   
   }
	// Adds string to Array List
	void addToLog(String strToAdd) {
		this.calcOutput.add(strToAdd);
		// System.out.println(strToAdd);
	}

	// Returns ArrayList of calculator output
	public ArrayList<String> getCalcOutput() {
		return calcOutput;
	}

	// Displays menu and gets user input for calculator
	public int displayMenu() {

		int userInput; // stores user choice
		Scanner scannerObj = new Scanner(System.in); // Object for all user input
		int counter = 0; // counts failed menu choices

		System.out.println("\nMenu");
		System.out.println("1. Add");
		System.out.println("2. Subtract");
		System.out.println("3. Multiply");
		System.out.println("4. Divide");
		System.out.println("5. Clear");
		System.out.println("6. Save");
		System.out.println("7. Quit");
		System.out.println("What would you like to do?");
		userInput = scannerObj.nextInt();
		while (userInput < 1 || userInput > 8) {
			if (counter >= 3) {
				System.out.println("ERROR: Threshold of 3 invalid menu inputs reached.  Please try again later");
				System.exit(1);
			}

			System.out.println("Error: Enter a number between 1-7");
			try {
				userInput = scannerObj.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Error: input must be a number between 1-7");
			}
			counter++;
		}

		return userInput;
	}

	// Returns file object from JFileChooser Input
	public File getFile() throws IOException {

		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			throw new IOException("Select a valid file to write output too.");
		}

	}

}
