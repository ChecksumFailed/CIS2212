package clocks;

/*
 * This class illustrates the importance of abstraction.  If you are 
 * writing classes that other programmers will be using in their code, you need
 * to design them very carefully.  The goal is for the other programmer to be
 * able to use your class without knowing anything about how it works.  This 
 * is called abstraction (you are abstracting away the details).
 * 
 * One part of abstraction is to encapsulate the state of your object.  
 * Encapsulation means that the state of an object (its fields) should only be 
 * changed by methods in this class.  One benefit of this is that you can 
 * completely change the fields of your class, and as long as you keep the same
 * methods, no one who is using your class will need to change any of their
 * code.  In this case we could change our hours and minutes fields from ints 
 * to Strings and any programs that used our class would still work, provided
 * we kept the method signatures the same.
 */

// All Java classes extend (inherit from) something.  Because this class does
// not explicitly extend anything, it implicitly extends Object.  The Object 
// class is the root of the Java class hierarchy, so every class is a descendant
// of Object, including all of the classes we have written for this course. The
// Object class contains default implementations for the toString() and 
// equals(Object o) methods.  Most classes that you write will need to override
// one or both of these methods.
public class Clock {
	
	// the default constructor initializes the time to noon
	public Clock() {
		hours = 12;
		minutes = 0;
	}
	
	// Notice that here I put the default constructor above the class fields.
	// This is bad style, but I wanted to illustrate that the "scope" of a 
	// class field (i.e. where it can be seen) is the entire .java file, no 
	// matter where it is.  It doesn't work top-to-bottom like local variables
	// declared inside methods.  So I can use the hours, minutes, and seconds
	// fields inside the default constructor, even though that comes before the 
	// field declarations.
	
	// Part of encapsulation is making all of your class fields private unless
	// there is a *very* good reason not to.  All of the classes you write for
	// this course should have private fields -- you will lose points for 
	// following coding conventions if not.  Private means that only methods in
	// this class can see and modify the fields directly.  You must provide 
	// getter and setter methods (shown below) to allow other classes to see 
	// the fields.
	
	// there is another type of access besides public and private.  it is 
	// called protected.  if a field is protected, it can be seen by all other
	// classes in the same package and subclasses that may be in different 
	// packages.  I've been programming in Java for 12 years now, and I've yet
	// to come across a situation where this would be useful.  If you leave off
	// the access modifier from class fields completely, they are accessible to
	// all other classes in the same package.  This is not generally a good idea
	
	private int hours;
	private int minutes;
	
	// this constructor initializes the clock to the time specified.
	
	// If a local variable (such as a method argument) has the same name as a 
	// class field, the local variable takes precedence. In that case you must 
	// you the ÒthisÓ keyword to refer to the class field. The ÒthisÓ keyword 
	// refers to Òthis objectÓ, as in the current instance of the class.
	
	// This constructor takes method arguments called hours and minutes 
	// but those are also the names of the fields of this class.  
	// The argument names take precedence.  So if we just write hours = hours
	// as in the first line, what we are actually doing is setting the hours 
	// argument equal to itself.  This is not an error, but it's nonsensical, 
	// so the compiler flags it as a warning (underlined in yellow) to indicate 
	// that we should... reconsider.  In order to set the class field equal to 
	// the argument value, we need to use the "this" keyword.
	
	// Naming your constructor arguments the same as your field name is a 
	// standard coding convention in Java, so you will see this a lot in other
	// people's code.
	
	public Clock(int hours, int minutes) {
		hours = hours;  // this is not what we want
		this.hours = hours;
		this.minutes = minutes;
	}
	
	
	// Getter and setter fields allow other classes to access this Clock's 
	// fields without giving up control.  For example, the setter methods first
	// check to make sure the clock isn't set to an invalid time, like 25:67.
	
	public void setHours(int hours) {
		if (hours > 0 && hours <= 12) {
			this.hours = hours;
		} else {
			System.out.println(hours + " is an invalid hour");
		}
	}
	
	public int getHours() {
		return hours;
	}
	
	public void setMinutes(int minutes) {
		if (minutes > 0 && minutes <= 59) {
			this.minutes = minutes;
		} else {
			System.out.println(minutes + " is an invalid minute");
		}
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	
	// the tick method increments the number of minutes, and then handles the 
	// cases where the minutes or hours need to loop.
	public void tick() {
		minutes++;
		
		if (minutes >= 60) {
			minutes = 0;
			hours++;
		}
		
		if (hours >= 13) {
			hours = 0;
		}
	}
	
	
	// the toString method gets automatically called if you pass an object to 
	// System.out.toString().  See the ClockShop class for an example.  For 
	// this to work, your toString method must have *exactly* this signature
	// (it must be public, return a String, be called toString, and take no
	// arguments). toString() is originally defined in Object, the root of
	// the Java class hierarchy.
	public String toString() {
		
		String time = "";
		
		time += hours + ":";
		
		if (minutes < 10) { // need a leading zero
			time += "0";
		}
		
		time += minutes;
		
		return time;
	}
	
	// the equals method is also originally defined in Object.  The default 
	// behavior isn't very useful (it just compares the memory locations of two
	// objects, so we will write a more meaningful version here.
	public boolean equals(Object o) {
		
		// if the other thing is null, it can't be equal to this object
		if (o == null) {
			return false;
		}
		
		// if the other thing isn't a Clock, it can't be equal to this object
		if (!(o instanceof Clock)) {
			return false;
		}
		
		Clock otherClock = (Clock) o;
		
		// this clock is equal to the other one if their hours and minutes are
		// the same
		return this.hours == otherClock.getHours() && 
			this.minutes == otherClock.getMinutes();
	}
}
