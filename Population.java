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
	// Copy of list so that the original isn't affected
	private ArrayList<City> newCities;
	
	// SortMethods object
	private SortMethods sm;
	
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
	
	/**	Print the menu and return the user input selection after
	 * 	checking that the input is valid
	 * 	@return	int	selection
	 */
	public int menuSelect() {
		//	Print menu and prompt
		printMenu();
		Prompt menuSelection = new Prompt();
		// Keep prompting user until a valid response is given
		boolean isValid = false;
		while (!isValid) {
			int selection = menuSelection.getInt("Enter Selection");
			// Break out of method if selection is valid
			if(selection >= 1 && selection <= 6 || selection == 9)
				return selection;
			// Error only reached if response not valid, process is repeated
			System.out.println("ERROR: " + selection + 
										" is not a valid selection"); 
		}
		return -1;
	}
	
	/** Find the 50 cities with the least population using selection sort
	 * 	and print them
	 */
	public void findLeastPop() {
		// 	Copy list so old isn't affected
		newCities = new ArrayList<City>(cities);
		// 	Start timer
		long startMillisec = System.currentTimeMillis();
		//	Sort
		newCities = sm.insertionSort(newCities);
		// 	Stop timer
		long endMillisec = System.currentTimeMillis();
		//	Print list and time
		System.out.println("Fifty least populous states");
		printList(newCities);
		System.out.println("\nElapsed Time " + 
								(endMillisec - startMillisec) + "\n");
	}
	
	/** Find the 50 cities with the most population using merge sort
	 *	and print them
	 */
	public void findMostPop() {
		// 	Copy list so old isn't affected.
		newCities = new ArrayList<City>(cities);
		// 	Start timer
		long startMillisec = System.currentTimeMillis();
		//	Sort
		newCities = sm.mergeSort(newCities);
		// 	Flip list
		ArrayList<City> flippedCities = new ArrayList<City>();
		for (int i = newCities.size() - 1; i >= 0; i--) {
			flippedCities.add(newCities.get(i));
		}
		// 	Stop timer
		long endMillisec = System.currentTimeMillis();
		//	Print list and time
		System.out.println("Fifty most populous states");
		printList(flippedCities);
		System.out.println("\nElapsed Time " + 
								(endMillisec - startMillisec) + "\n");
	}
	
	/**	Sort to find first 50 cities with asc name and print them
	 */
	public void findNameAsc() {
		// 	Copy list so old isn't affected
		newCities = new ArrayList<City>(cities);
		// 	Start timer
		long startMillisec = System.currentTimeMillis();
		//	Sort
		newCities = sm.insertionSortName(newCities);
		// 	Stop timer
		long endMillisec = System.currentTimeMillis();
		//	Print list and time
		System.out.println("Fifty cities sorted by name");
		printList(newCities);
		System.out.println("\nElapsed Time " + 
								(endMillisec - startMillisec) + "\n");
	}
	
	/** Sort to find first 50 cities with dec name and print them
	 */
	public void findNameDec() {
		// 	Copy list so old isn't affected.
		newCities = new ArrayList<City>(cities);
		// 	Start timer
		long startMillisec = System.currentTimeMillis();
		//	Sort
		newCities = sm.mergeSortName(newCities);
		// 	Flip list
		ArrayList<City> flippedCities = new ArrayList<City>();
		for (int i = newCities.size() - 1; i >= 0; i--) {
			flippedCities.add(newCities.get(i));
		}
		// 	Stop timer
		long endMillisec = System.currentTimeMillis();
		//	Print list and time
		System.out.println("Fifty cities sorted by name descending");
		printList(flippedCities);
		System.out.println("\nElapsed Time " + 
								(endMillisec - startMillisec) + "\n");
	}
	
	/** Find 50 most populous cities in a state
	 */
	public void findPopState() {
		Prompt in = new Prompt();
		boolean isValid = false;
		String state;
		// 	Keep prompting user until a valid name is given
		while (!isValid) {
			state = in.getString("Enter state name (ie. Alabama)");
			if (isValidState(state)) isValid = true;
		}
	}
	
	/** Check if the state is a valid state
	 *	@param	String	String to check whether it is a state
	 * 	@return	Boolean	Whether state is valid
	 */
	private boolean isValidState(String state) {
		switch (state) {
			case "Alabama":	case "Alaska":	case "Arizona":
			case "Arkansas":	case "California":	case "Colorado":
			case "Conneticut":	case "Delaware":	case "Florida":
			case "Georgia":	case "Hawaii":	case "Idaho":
			case "Illinois":	case "Indiana":	case "Iowa":
			case "Kansas":	case "Kentuky":	case "Louisiana":
			case "Maine": // Left off 12/12 in class
		}
	}
	
	/** Print first 50 elements of a list
	 * 	@param	ArrayList<City>		list to print
	 */
	public void printList(ArrayList<City> arr) {
		//	Size of the list should be size of the array, capped at 50
		int listSize = arr.size();
		if (listSize > 50) listSize = 50;
		//	Header
		System.out.printf("%9s", "State");
		System.out.printf("%24s", "City");
		System.out.printf("%30s", "Type");
		System.out.printf("%31s", "Population");
		System.out.println("");
		//	List
		for (int i = 0; i < listSize; i++) {
			System.out.printf("%4s", (i + 1) + ": ");
			System.out.printf("%-25s", arr.get(i).getState());
			System.out.printf("%-30s", arr.get(i).getName());
			System.out.printf("%-15s", arr.get(i).getDesignation());
			System.out.printf("%20s", addCommas(arr.get(i).getPopulation()));
			System.out.println("");
		}
	}
	
	/** Add commas to int as appropriate
	 * 	@param	int		number to add commas to
	 * 	@return String	number with commas
	 */
	private String addCommas(int num) {
		// 	Set output as input
		String output = Integer.toString(num);
		// 	Keep track of num of digits since last comma
		int numCount = 0;
		for (int i = output.length() - 1; i > 0; i--) {
			numCount++;
			if (numCount == 3) {
				output = output.substring(0, i) + "," + output.substring(i);
				numCount = 0;
			}
		} 
		return output;
	}
	
	
	public static void main(String[] args) {
		// Initialize and instantiate 
		Population pop = new Population();
		pop.readInitiate();
		// Run
		pop.run();
	}
	
	public void run() {
		// instantiate sort method object
		sm = new SortMethods();
		// Keep running until quit is selected
		boolean isClosed = false;
		while(!isClosed) {
			int selection = menuSelect();
			System.out.println("");
			switch (selection) {
				case 1:	findLeastPop(); break;
				case 2: findMostPop();	break;
				case 3:	findNameAsc();	break;
				case 4:	findNameDec();	break;
				case 5:	findPopState();	break;
				case 6:
				// Includes 9
				default:	isClosed = true;;
			}
		}
	}
}
