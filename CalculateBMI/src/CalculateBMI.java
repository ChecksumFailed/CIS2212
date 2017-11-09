import java.util.Scanner;

/**
 * 
 */

/**
 * Student: Benjamin Scherer
 * Class: CIS2212
 * Date: 08/23/2017
 * Project: BMI Calculator.  
 * Description: Prompt user to input weight(pounds0 and height(inches).  Then calculate BMI.  Output will be double
 *
 */
public class CalculateBMI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Variables
		double weight; //weight in pounds
		double height; //height in inches
		double bmi;  //calculate bmi
		Scanner scannerObj = new Scanner(System.in);  // Scanner object for user input
		
		//Initial User Input
		System.out.println("Enter weight in pounds");
		weight = scannerObj.nextDouble();
		System.out.println("Enter height in inches");
		height = scannerObj.nextDouble();
		
		
		//Calculate BMI
		bmi = (weight / Math.pow(height, 2)) * 703;
		
		//Output
		System.out.println("Your BMI is " + bmi);
		System.out.println("A BMI between 20 and 24 is considered ideal.  What would you like your BMI to be?");
		double desiredBMI = scannerObj.nextDouble();
		
		//Calculate weight for desired BMI;
		double targetWeight = (desiredBMI / 703) * Math.pow(height, 2);
		System.out.println("Your weight needs to be " + targetWeight + " pounds for your BMI to be " + desiredBMI);
		
		

	}

}
