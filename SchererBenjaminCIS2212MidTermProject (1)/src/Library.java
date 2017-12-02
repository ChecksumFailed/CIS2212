import java.util.Scanner;
import java.util.ArrayList;

/**
 * Ben Scherer CIS 2212 Oct 10, 2017 Mid Term Project Description
 */
class MediaItem {
	String title; // Title of media
	String format; // Format of media(DVD,Bluray,XBOX,etc...)
	boolean onLoan; // Is the media currently loaned out
	String loanedTo; // Whom is the media loaned to
	String dateLoaned; // Date media checked out

	// Constructors
	MediaItem() {
	}

	MediaItem(String title, String format) {
		this.title = title;
		this.format = format;
		this.onLoan = false;

	}

	// Sets media status to On Loan
	void markOnLoan(String name, String date) {
		if (this.onLoan) { // Check if item is currently on loan
			System.out.println("Error:" + this.title + " is already out on loan to " + this.loanedTo);
			return;
		}
		this.onLoan = true;
		this.loanedTo = name;
		this.dateLoaned = date;
	}

	// Sets status to returned
	void markReturned() {
		if (this.onLoan == false) { // Check if item has already been returned
			System.out.println("Error:" + this.title + " is not currently out on loan");
			return;
		}
		this.onLoan = false;
		this.dateLoaned = null;
		this.loanedTo = null;
	}

}

public class Library {
	final int maxLibrarySize = 100; // maximum size of library
	// MediaItem mediaArr[] = new MediaItem[100]; // Array to store media items in
	// library
	ArrayList<MediaItem> mediaArr = new ArrayList<MediaItem>();
	int numberOfItems = 0; // Counter. Keeps track of items added to library

	Library() {

	}

	void addNewItem(String title, String format) {
		MediaItem itemExists = searchLibrary(title); // index returned form searchLibrary function
		if (itemExists != null) {
			System.out.println("Error:  Item already exists in library");
			System.out.println("Item Details: " + itemExists.title);
			return;
		}
		if (this.numberOfItems == this.maxLibrarySize) {
			System.out.println(
					"Error: Reached max size of library(" + this.maxLibrarySize + ").  No more items can be added");
			return;
		}

		this.mediaArr.add(new MediaItem(title, format));

		this.numberOfItems++;
	}

	static int displayMenu() {
		int usrInput = 0; // stores user choice
		Scanner scannerObj = new Scanner(System.in); // Object for all user input

		System.out.println("Please make a selection.");
		System.out.println("1. Add New Item");
		System.out.println("2. Mark an item as on loan");
		System.out.println("3. List all items");
		System.out.println("4. Mark an item as returned");
		System.out.println("5. Quit");

		do {
			try {
				usrInput = scannerObj.nextInt();
				if (usrInput < 1 || usrInput > 5) {
					displayError("Please enter a number between 1-5");
				}
			} catch (Exception e) {
				displayError("Please enter integers only");
				scannerObj.next(); // clear token to avoid infinite loop
			}
		} while (usrInput < 1 || usrInput > 5);
		scannerObj.nextLine();// clear newline from system.in
		return usrInput;
	}

	// Perform case insensitive search of library based on title
	MediaItem searchLibrary(String title) {

		// Search library array. typeOfSearch is field
		for (MediaItem media : this.mediaArr) {
			if (media.title.equalsIgnoreCase(title)) {
				return media;
			}
		}
		return null;
	}

	static void listAllItems(ArrayList<MediaItem> mediaArr) {
		// enumerate array list and print tab delimited list. Use ternary operator to
		// account for null values
		if (mediaArr.size() == 0) {
			displayError("Library empty.  Please add more items");
			return;
		}
		System.out.printf("%-30s\t%-15s\t%-15s\t%-10s\n", "Title", "Format", "Loaned To", "Date Loaned");
		System.out.println("---------------------------------------------------------------------------");
		for (MediaItem m : mediaArr) {
			System.out.printf("%-30s\t%-15s\t%-15s\t%-10s\n", m.title, m.format,
					((m.loanedTo == null) ? "" : m.loanedTo), ((m.dateLoaned == null) ? "" : m.dateLoaned));

		}
	}

	static void displayError(String errorMsg) {
		System.out.printf("ERROR: %s\n\n", errorMsg);
	}

	static String getStrInput(String usrPrompt) {
		Scanner scannerObj = new Scanner(System.in); // Object for all user input
		System.out.println(usrPrompt);
		String usrInput = null;
		do {
			try {
				usrInput = scannerObj.nextLine();
			} catch (Exception e) {
				displayError(e.getMessage());
				scannerObj.next();
			}
		} while (usrInput == null);
		return usrInput;
	}

	// Main function
	public static void main(String[] args) {
		Scanner scannerObj = new Scanner(System.in); // Object for all user input
		int userInput = 0;
		Library libraryObj = new Library(); // create new library object.
		
		do {
			userInput = displayMenu();
			switch (userInput) {
			case 1: {
				// Add new media item
				String title = getStrInput("Please enter the title of the media: ");
				String format = getStrInput("Please enter format of media: ");
				libraryObj.addNewItem(title, format);
				break;
			}
			case 2: {
				// mark item as out on loan

				String title = getStrInput("Please enter title of media to mark on loan: ");
				MediaItem media = libraryObj.searchLibrary(title); // Search library for title prior to prompting for
																	// rest of information
				if (media == null) {
					displayError("Unable to find title " + title + " in library.");
					break;
				}
				String loanedTo = getStrInput("Who is the " + title + " being loaned to? ");
				String dateLoaned = getStrInput("On what date was " + title + " loaned to " + loanedTo + "?");
				media.markOnLoan(loanedTo, dateLoaned); // Call markOnLoan method of MediaItem class
				break;
			}
			case 3: {
				// List all items in library
				listAllItems(libraryObj.mediaArr); // Pass library array to method
				break;
			}
			case 4: {
				// mark item as returned

				String title = getStrInput("Please enter title of media to mark as returned: ");
				MediaItem media = libraryObj.searchLibrary(title); // Search library for title and return MediaItem
																	// Object
				if (media == null) {
					displayError("Unable to find title " + title + " in library.");
					break;
				}
				media.markReturned(); // Call MediaItem markReturned method
				break;
			}

			}

		} while (userInput != 5);
		scannerObj.close();
		System.out.println("Goodbye!");

	}

}