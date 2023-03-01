package midtermPrep;

/**
 * Planet defined by its name and diameter.
 * 
 * @author Starter Code + .............
 *
 */
public class Planet {
	private final String name;
	private final double diameter;  // in miles
	
	/**
	 * Creates an initializes a planet.
	 * 
	 * @param name 
	 * @param diameter
	 */
	public Planet(String name, double diameter) {
		this.name = name;
		this.diameter = diameter;
	}

	public String getName() {
		return name;
	}

	public double getDiameter() {
		return diameter;
	}

	/**
	 * <p>Creates a string of the format {name} ({diameter})
	 * where {name} and {diameter} are substituted with the corresponding field values.</p>
	 *
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return name + " (" + diameter + ")";
	}
	
	// = = = =   T e s t   C l i e n t    = = = = = 

	public static void main(String[] args) {
		Planet[] planets = {
				new Planet("Earth", 7_917.5), new Planet("Jupiter", 86_881), 
				new Planet("Mars", 4_212.3), new Planet("Mercury", 3_031.9), 
				new Planet("Neptune", 30_599), new Planet("Saturn", 72_367), 
				new Planet("Uranus", 31_518), new Planet("Venus", 7_520.8),				
		};	
		printAll(planets, "Original array:");
		
//		java.util.Arrays.parallelSort(planets);
//		printAll(planets, "Sorted array:");
	}

	private static <T> void printAll(T[] array, String title) {
		System.out.println(title);
		for(T el : array ) {
			System.out.println(el);
		}
		System.out.println();
	}

}
