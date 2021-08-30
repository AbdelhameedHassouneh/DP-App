//arbitrary object used to display data in the tables
public class Arbitrary {
	int numberOfLights;
	String arOfLights[];

	String arOfValues[];

	public Arbitrary() {
		super();
	}


	public Arbitrary(int numberOfLights) {
		super();
		this.numberOfLights = numberOfLights;
		arOfLights = new String[numberOfLights];
		
		arOfValues=new String [numberOfLights];
		
	}

	public Arbitrary(int numberOfLights, String[] arOfLights, String[] arOfValues) {
		super();
		this.numberOfLights = numberOfLights;
		this.arOfLights = arOfLights;
		this.arOfValues = arOfValues;
	}

	public int getNumberOfLights() {
		return numberOfLights;
	}

	public void setNumberOfLights(int numberOfLights) {
		this.numberOfLights = numberOfLights;
	}

	public String[] getArOfLights() {
		return arOfLights;
	}

	public void setArOfLights(String[] arOfLights) {
		this.arOfLights = arOfLights;
	}

	public String[] getArOfValues() {
		return arOfValues;
	}

	public void setArOfValues(String[] arOfValues) {
		this.arOfValues = arOfValues;
	}

}
