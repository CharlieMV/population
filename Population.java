import java.util.*;
import java.io.*;
/**
 *	Population - sorts cities by population, name, state, and type from
 * 	a database
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Charles Chang
 *	@since	December 7, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/** Initiate and add cities the City arraylist **/
	public void readInitiate() {
		// Initialize arraylist
		cities = new ArrayList<City>();
		try {
			File data = new File(DATA_FILE);
			Scanner input = new Scanner(data);
			while (input.hasNextLine()) {
				// Store text line
				String line = input.nextLine();
				// Get state
				String state = line.substring(0, line.indexOf("\t"));
				line = line.substring(line.indexOf("\t") + 1);
				// Get name
				String name = line.substring(0, line.indexOf("\t"));
				line = line.substring(line.indexOf("\t") + 1);
				// Get designation
				String designation = line.substring(0, line.indexOf("\t"));
				line = line.substring(line.indexOf("\t") + 1);
				// Get population
				int population = Integer.parseInt(line.trim());
				// Add new city object to arraylist with read info
				cities.add(new City(name, state, designation, population));
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("ERROR: FileNotFoundException");
		}
		// Print intro when done reading
		printIntroduction();
		System.out.println(cities.size() + " cities in database\n");
	}
	
	public static void main(String[] args) {
		Population pop = new Population();
		pop.readInitiate();
		pop.run();
	}
	
	public void run() {
		boolean isClosed = false;
		while (!isClosed) {
			printMenu();
			Prompt menuSelection = new Prompt();
			int selection = menuSelection.getInt("Enter Selection");
			switch (selection) {
				case 9:	isClosed = true;	break;
				default:	
				System.out.println(selection + " is not a valid selection.");
			}
		}
	}
}
