/**
 * Ben Scherer
 * CIS 2212
 * Oct 4, 2017
 * 
 * Description
 */

/**
 * Ben Scherer
 * CIS 2212
 * Oct 4, 2017
 * ArabicToRomanNumeral
 * Description: Converts Arabic Integers to corresponding Roman Numeral.  
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class ArabicToRomanNumeral {

	
	
	public static String arabicToRoman(int arabic) {
		//Create parallel arrays. This will map the Arabic numbers to Roman numerals. 
		//See following wikipedia article for reference.  https://en.wikipedia.org/wiki/Roman_numerals
		
		//Check for invalid numbers
		if (arabic > 3999) {
			return "ERROR: Roman Numerals do not support any integer greater than 3999";
		} else if (arabic <= 0) {
			return "ERROR: Roman Numerals only support values greater than 0.";
		}
		
		
		int[] arabicArr = new int[] {1000,900,500,400,100,90,50,40,10,9,5,4,1}; //Arabic numbers.   Includes Subtractive notation numbers
		String[] romanArr = new String[] {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"}; //Corresponding Roman Numerals
		String roman = ""; //roman numeral to return
		
		for(int i=0;i < arabicArr.length;i++) { //Loop through integer array.  
			if (arabic >= arabicArr[i]) { // number must be at least as big as the base integer for roman numeral
				//System.out.println((arabic / arabicArr[i]));
				int numConcat = arabic / arabicArr[i];//number of times to add roman numeral to string
				for(int y=0;y < numConcat;y++) { 
					arabic -= arabicArr[i]; //Subtract value from arabic number, otherwise we will end up with an invalid roman numeral.
					roman = roman.concat(romanArr[i]); //Add roman numeral to string 
				}
			}
		}
		return roman;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scannerObj = new Scanner(System.in); // Object for all user input
		int userInput = 0;
		
		do {
			System.out.print("Enter an integer (-1 to quit): ");
			try {
				userInput = scannerObj.nextInt();
				if (userInput != -1) {
					System.out.println(arabicToRoman(userInput));
				}
			} catch (Exception e) {
				System.out.println("ERROR: Please enter integers only");
				scannerObj.next(); //clear token to avoid infinite loop
			}
	
		} while (userInput != -1);
		scannerObj.close();
		System.out.println("Goodbye!");

	}

}

/*
 * Test Case:
 * 	Normal Test Cases. 
 * 		Input	Reason											Result
		14	    Included in assignment pdf.  					XIV
		9	    Roman numeral that uses subtractive notation	IX
		55		Random valid number to test against				LV 
		 * 
 * Invalid Test Case:  Numbers less than or equal to 0 or invalid input
	 * 	Input	Reason											Result
		0		No concept of 0 in Roman Numeral				ERROR: Roman Numerals only support values greater than 0.
		-2		No concept of negative in Roman Numeral			ERROR: Roman Numerals only support values greater than 0.
		-999	Random negative number							ERROR: Roman Numerals only support values greater than 0.
		d		Non Integer Number								ERROR: Please enter integers only
 * 
 * Boundary Test Case: Numbers greater than the upper boundary of the Roman numeral system(3999)
	 * 	Input	Reason											Result
		4000	first number greater than boundary(3999)		ERROR: Roman Numerals do not support any integer greater than 3999
		5904	Random number greater than boundary(3999)		ERROR: Roman Numerals do not support any integer greater than 3999
 */


