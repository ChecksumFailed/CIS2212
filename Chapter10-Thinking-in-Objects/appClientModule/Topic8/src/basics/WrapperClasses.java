package basics;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class WrapperClasses {

	public static void main(String[] args) {

		// There are wrapper classes corresponding to each primitive data type 
		// (Integer, Double, Boolean, Character, Float, Byte, Short, Long). 
		// These are all immutable. 

		// You can construct an instance of the wrapper classes with either a 
		// constant or a string value. 
		Double doubleVal = new Double(5.2);
		Boolean booleanVal = new Boolean("true");
		Integer intValue = new Integer("4");
		
		// These classes also have parse methods to convert a string to the
		// appropriate type.
		String s = "42";
		Integer intValue1 = Integer.parseInt(s);
		
		// These classes also have a compareTo method, which returns a negative
		// value if the first object is less than the second, zero if they are
		// equal, and a positive number if the first is greater than the second
		
		int comparison = intValue.compareTo(intValue1);
		
		if (comparison < 0) {
			System.out.println(intValue + " is less than " + intValue1);
		} else if (comparison == 0) {
			System.out.println(intValue + " is equal to " + intValue1);
		} else {
			System.out.println(intValue + " is greater than " + intValue1);
		}
		
		// Wrapper classes that represent numbers also have constants like 
		// MIN_VALUE and MAX_VALUE defined.  These can be useful if you are 
		// doing something like searching an array for the smallest value.
		int[] intArray = {3, 1, -4, 7, 0, -314, 8};
		
		int min = Integer.MAX_VALUE;
		for (int i: intArray) {
			if (i < min) {
				min = i;
			}
		}
		
		System.out.println("The smallest value in " + Arrays.toString(intArray) 
				+ " is " + min);
		
		// Java will automatically convert from a primitive to its wrapper class 
		// (called autoboxing) and from a wrapper class to a primitive (called 
		// auto-unboxing)
		
		double x = 5;
		Double y = new Double(6);
		
		Double wrapperDouble = x;  // this is auto-boxing
		double primitiveDouble = y; // this is auto-unboxing
		
		// auto-boxing and unboxing comes up commonly with methods that take
		// a wrapper or primitive number as an argument and with ArrayLists
		// (and other Java containers), which can only hold objects and not
		// primitives.
		Double negation = negate(y);
		System.out.println("The negation of " + y + " is " + negation);
		
		ArrayList<Integer> intList = new ArrayList<Integer>();
		intList.add(4);
		intList.add(intValue);
		intList.add(intValue1);
		
		// The BigInteger and BigDecimal classes can represent numbers of 
		// (essentially) infinite size. You can perform standard arithmetic 
		// operations with these classes using methods such as add, multiply, 
		// remainder, etc.  This avoids the problem of arithmetic overflow that
		// we talked about in the early part of this course.
		int a = 500000000;
		int c = a * 5;
		System.out.println("Using integers, " + a + " * 5 = " + c + 
				" (overflow)");

		BigInteger bi = new BigInteger("500000000");
		BigInteger factor = new BigInteger("5");
		BigInteger ans = bi.multiply(factor);
		System.out.println("Using the BigInteger class, " + bi + " * 5 = " + 
				ans);
	}
	
	public static double negate(double val) {
		return -val;
	}
}
