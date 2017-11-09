package web;

/*
 * The goal of this class is to illustrate good design principles.  It also 
 * makes use of the string processing methods we learned in the last topic.  
 * This class is only for teaching purposes -- Java has a built-in URL class 
 * that has more features and has been more fully tested.
 * 
 * Here are some characteristics of good classes:
 * 
 * cohesion � a class should describe a single entity and all of its methods 
 * should be logically relevant to that entity's purpose
 * 
 * consistency � follow normal style guidelines, such as naming conventions, 
 * organized with data fields, then constructors, then methods, provide a 
 * public no-argument constructor (or document the reason why you didn't)
 * 
 * encapsulation � make the data fields private unless you have a really good 
 * reason not to. provide getter and setter methods if appropriate.
 * 
 * clarity � name methods and data fields intuitively, provide comments where 
 * appropriate; have as few preconditions for your methods as possible (e.g. 
 * don't have two methods where one always needs to be called before the other 
 * for them to work correctly)
 * 
 * completeness � try to provide any methods other developers would need to be 
 * able to use your class in a wide variety of applications.
 * 
 * instance vs static � if a field or method can be static, it should be. Use 
 * the class name to access a static field/method rather than an instance of 
 * the class (for clarity). Use set methods to set the value of static fields 
 * rather than doing it in the constructor.
 * 
 * Our goal is to write a class that represents a URL (link) to a web page.  
 * We need to be able to ensure the link has a valid form.
 * 
 * The general form of a link is:
 * protocol://prefix.server.top-level-domain/path/to/file/filename
 * 
 * For example, for the link http://en.wikipedia.org/wiki/Computer_science
 *      protocol - http
 *      prefix - en
 *      server - wikipedia
 *      top-level-domain - org
 *      path/to/file - wiki
 *      filename - Computer_science
 */

public class URL {

	// Notice that this code follows standard naming conventions (consistency) 
	// and anything that is not totally obvious is commented (clarity).  Also, 
	// all fields of this class are private and getter/setter methods are 
	// available to access them (encapsulation)

	private String protocol;
	private String prefix;
	private String server;
	private String tld; // top-level-domain
	private String pathToFile;
	private String filename;

	// All of these class fields are instance rather than static fields.  
	// Remember that static fields are the same for all instances of a class.
	// You might argue that protocol should be static, but some web pages begin
	// with things beside "http", such as "https" or "ftp".

	public URL(String link) {

		// Note all of the "if" statements in this method -- these are to
		// detect unexpected conditions and avoid the program crashing.  The 
		// number one mistake beginning programmers make is to code for the 
		// expected case but not handle errors or invalid input gracefully.

		if (link.contains("://")) {
			protocol = link.substring(0, link.indexOf("://"));

			// strip the protocol from the rest of the link -- add 3 to avoid 
			// including the "://";
			link = link.substring(link.indexOf("://") + 3);
		}

		// the variable "firstPart" is holding the part of the link after the 
		// protocol:// part that contains the prefix, server name, and domain 
		// name.  for instance, in https://www.gmail.com/inbox, firstPart is 
		// www.gmail.com
		String firstPart = "";

		if (link.contains("/")) {
			firstPart = link.substring(0, link.indexOf("/"));
			link = link.substring(link.indexOf("/"));
		} else {
			firstPart = link;
			link = "";
		}

		if (firstPart.contains(".")) {

			if (firstPart.indexOf(".") != firstPart.lastIndexOf(".")) {

				prefix = firstPart.substring(0, firstPart.indexOf("."));

				server = firstPart.substring(firstPart.indexOf(".") + 1, 
						firstPart.lastIndexOf("."));

			} else {
				server = firstPart.substring(0, firstPart.indexOf("."));
			}

			tld = firstPart.substring(firstPart.lastIndexOf("."));
		}

		// pathToFile is everything between firstPart and the last / in the
		// link
		if (link.indexOf("/") != link.lastIndexOf("/")) {
			pathToFile = link.substring(link.indexOf("/") + 1, 
					link.lastIndexOf("/") + 1);
		}

		// the filename is everything from the last / in the link until the end
		if (link.contains("/")) {
			filename = link.substring(link.lastIndexOf("/") + 1);
		}
	}

	// The getter and setter methods follow naming conventions (consistency).
	// These are the only way to access the class's fields (encapsulation).
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getTld() {
		return tld;
	}

	public void setTld(String tld) {
		this.tld = tld;
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	// For a URL to be valid, the path to the prefix, file and filename can be 
	// null but all of the other fields must have a value.  Notice that this 
	// definition might work for my application, but other programmers using 
	// this class might have different needs for a "valid" URL.  By clearly 
	// documenting what is considered valid in this case, the other programmer 
	// can clearly see if they need to write a different method to suit their 
	// needs.

	public boolean isValid() {
		return (protocol != null && server != null && tld != null);
	}

	// Cohesion involves avoiding "feature creep" in the classes you develop.  
	// For instance, say we want to write a Java program that visits a web page
	// and retrieves the text on the page.  I would not add the method to do
	// that to this class, because this class only represents a URL -- code
	// that goes beyond that should be in a separate class that makes use of 
	// this one.
	
	// On the other side of cohesion is completeness.  We don't want to include
	// methods in our class that don't make sense.  But we *do* want to write
	// classes that are useful in more than just the one program we are 
	// currently working on.  To do this, it is important to think about what 
	// methods are reasonable to have in a class that represents URLs.  In our
	// current situation we only need to determine if a URL has a valid format.
	// However, others might want to be able to determine if two URLs are equal
	// or if they are on the same server.  These kinds of methods will make 
	// your class worthwhile in a variety of situations.

	// I used Eclipse to automatically generate this equals method -- go to 
	// Source -> Generate hashCode() and equals()
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		URL other = (URL) obj;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (pathToFile == null) {
			if (other.pathToFile != null)
				return false;
		} else if (!pathToFile.equals(other.pathToFile))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (protocol == null) {
			if (other.protocol != null)
				return false;
		} else if (!protocol.equals(other.protocol))
			return false;
		if (server == null) {
			if (other.server != null)
				return false;
		} else if (!server.equals(other.server))
			return false;
		if (tld == null) {
			if (other.tld != null)
				return false;
		} else if (!tld.equals(other.tld))
			return false;
		return true;
	}
	
	
	public boolean onSameServer(URL other) {
		if (server != null && other.getServer() != null) {
			return server.equals(other.getServer());
		}
		
		return false;
	}

	// It is important to unit test your class, which means testing the class
	// by itself.  Be sure to cover standard, boundary, and invalid cases.
	public static void main(String[] args) {

		// Standard case
		URL url1 = new URL("http://en.wikipedia.org/wiki/Computer_science");
		System.out.println("http://en.wikipedia.org/wiki/Computer_science");
		System.out.println("protocol: " + url1.getProtocol());
		System.out.println("prefix: " + url1.getPrefix());
		System.out.println("server: " + url1.getServer());
		System.out.println("tld: " + url1.getTld());
		System.out.println("path to file: " + url1.getPathToFile());
		System.out.println("filename: " + url1.getFilename());
		System.out.println("valid: " + url1.isValid());
		System.out.println();

		// Boundary case
		URL url2 = new URL("https://gmail.com");
		System.out.println("https://gmail.com");
		System.out.println("protocol: " + url2.getProtocol());
		System.out.println("prefix: " + url2.getPrefix());
		System.out.println("server: " + url2.getServer());
		System.out.println("tld: " + url2.getTld());
		System.out.println("path to file: " + url2.getPathToFile());
		System.out.println("filename: " + url2.getFilename());
		System.out.println("valid: " + url2.isValid());
		System.out.println();
		
		// Invalid case
		URL url3 = new URL("hello-how-are-you.net");
		System.out.println("hello-how-are-you.net");
		System.out.println("protocol: " + url3.getProtocol());
		System.out.println("prefix: " + url3.getPrefix());
		System.out.println("server: " + url3.getServer());
		System.out.println("tld: " + url3.getTld());
		System.out.println("path to file: " + url3.getPathToFile());
		System.out.println("filename: " + url3.getFilename());
		System.out.println("valid: " + url3.isValid());
		System.out.println();
	}
}
