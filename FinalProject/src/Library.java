
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

	public ArrayList<MediaItem> mediaArr = new ArrayList<MediaItem>();
	
	Library()  {
		   
	}
	
	void loadDB (String fileName) throws Exception  {
		File inFile = new File(fileName);
		if (!inFile.exists())
				return;//If no library file exists, we will create a new one 
		Scanner scannerObj = new Scanner(inFile);
		scannerObj.useDelimiter("\\|");
		while (scannerObj.hasNext()) {
			String line = scannerObj.nextLine();
		//	System.out.println(line);
			String [] rowItems = line.split("\\|");
			MediaItem tmpMedia = new MediaItem(rowItems[0],rowItems[1]);
		//	System.out.println("A:" + rowItems[0]);
		//	System.out.println("B:" + rowItems[2]);
			if (rowItems[2].equalsIgnoreCase("TRUE"))
					tmpMedia.markOnLoan(rowItems[3], rowItems[4]);
			this.mediaArr.add(tmpMedia);
			
		}
		scannerObj.close();
		
		
	}
	void addMedia(String title, String format) throws LibraryException {
		MediaItem itemExists = searchLibrary(title,format); // index returned form searchLibrary function
		if (itemExists != null) {
			throw new LibraryException("Item already exists in library");

		}

		this.mediaArr.add(new MediaItem(title, format));

	}

	void deleteMedia(String title, String format ) throws LibraryException {
		MediaItem itemExists = searchLibrary(title,format); // index returned form searchLibrary function
		if (itemExists == null) {
			throw new LibraryException("Item not found in library");

		}
		System.out.println("Deleteing " + itemExists.getTitle());
		System.out.println(mediaArr.size());
		this.mediaArr.remove(itemExists);
		System.out.println(mediaArr.size());
	}
	
	// Perform case insensitive search of library based on title
	MediaItem searchLibrary(String title, String format) {

		// Search library array. typeOfSearch is field
		for (MediaItem media : this.mediaArr)  {
			if (media.getTitle().equalsIgnoreCase(title) && media.getFormat().equalsIgnoreCase(format)) {
				
				return media;
			}
		}
		return null;
	}

	
	ArrayList<MediaItem> getLibrary() {
		return this.mediaArr;
	}


}