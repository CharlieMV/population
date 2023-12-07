/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Charles Chang
 *	@since	December 7, 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String name;
	private String state;
	private String designation;
	private int population;
	
	// constructor
	public City(String cityName, String cityState, String cityDesignation, 
															int cityPop) {
		name = cityName;
		state = cityState;
		designation = cityDesignation;
		population = cityPop;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other) {
		if (this.population - other.getPopulation() != 0)
			return this.population - other.getPopulation();
		if (this.state.compareTo(other.getState()) != 0)
			return this.state.compareTo(other.getState());
		return this.name.compareTo(other.getName());
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other) {
		return (this.compareTo(other) == 0);
	}
	
	/** get the name 
	 * @return	String	name **/
	public String getName() {
		return name;
	}
	/** get the state 
	 * @return	String	state **/
	public String getState() {
		return state;
	}
	/** get the designation 
	 * @return	String	designation **/
	public String getDesignation() {
		return designation;
	}
	/** get the population 
	 * @return	int		population **/
	public int getPopulation() {
		return population;
	}
	
	/** set the name 
	 * 	@param	String	new name **/
	public void setName(String newName) {
		name = newName;
	}
	/** set the state 
	 * 	@param	String	new state **/
	public void setState(String newState) {
		state = newState;
	}
	/** set the designation 
	 * 	@param	String	new designation **/
	public void setDesignation(String newDesignation) {
		designation = newDesignation;
	}
	/** set the population 
	 * 	@param	int		new population **/
	public void setPopulation(int newPopulation) {
		population = newPopulation;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
