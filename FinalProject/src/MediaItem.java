 class MediaItem {
	private String title; // Title of media
	private String format; // Format of media(DVD,Bluray,XBOX,etc...)
	private boolean onLoan; // Is the media currently loaned out
	private String loanedTo; // Whom is the media loaned to
	private String dateLoaned; // Date media checked out

	// Constructors
	MediaItem() {
	}

	MediaItem(String title, String format) {
		this.title = title;
		this.format = format;
		this.onLoan = false;

	}

	
	///Setter Methods
	
	// Sets media status to On Loan
	void markOnLoan(String name, String date) throws LibraryException {
		if (this.onLoan) { // Check if item is currently on loan
			throw new LibraryException("Error:" + this.title + " is already out on loan to " + this.loanedTo);
			
		}
		this.onLoan = true;
		this.loanedTo = name;
		this.dateLoaned = date;
	}

	// Sets status to returned
	void markReturned() throws LibraryException {
		if (this.onLoan == false) { // Check if item has already been returned
			throw new LibraryException("Error:" + this.title + " is not currently out on loan");
			
		}
		this.onLoan = false;
		this.dateLoaned = null;
		this.loanedTo = null;
	}
	
	void setTitle(String title) {
		this.title = title;
	}
	
	void setFormat(String format) {
		this.format = format;
	}
	
	
	//Getter Methods
	String getTitle() {
		return this.title;
	}
	
	String getFormat() {
		return this.format;
	}
	
	String getLoanedTo() {
		return this.loanedTo;
	}
	
	String getDateLoaned() {
		return this.dateLoaned;
	}
	
	boolean getOnLoan() {
		return this.onLoan;
	}
	
	public String toString(){ 
		String tmpString = String.format("%s(%s)", getTitle(),getFormat());
		//System.out.println(tmpString);
		if (getOnLoan()) 
			tmpString += String.format(" loaned to %s on %s", getLoanedTo(),getDateLoaned());
		return tmpString;
	}  
	

	

}

