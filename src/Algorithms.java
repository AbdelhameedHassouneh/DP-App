import eu.hansolo.enzo.led.Led;
//this method is the main dynamic method 
//it calculates the number of lights and what are the lights
public class Algorithms {
	static String DpMethod(int arr[], int lis[], int sublist[]) {
		int n = arr.length;

		int i, j, max = 0;

		for (i = 0; i < n; i++) {
			lis[i] = 1;
			sublist[i] = -1;
		}

		for (i = 1; i < n; i++)
			for (j = 0; j < i; j++)
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
					sublist[i] = j;

				}

		int index = 0;
		for (i = 0; i < n; i++)
			if (max < lis[i]) {
				index = i;

				max = lis[i];
			}

		

		String output = rec(arr, sublist, index);
		
		return output;

	}

	
	//this method finds out what the lights recursevly
	//the return type is string 
	public static String rec(int values[], int secondery[], int index) {
		if (index == -1) {
			return "";
		}
		String str = values[index] + "";
		str += rec(values, secondery, secondery[index]);

		return (str);
	}

	
	//this method finds out what the lights recursevly
	//the return type is array of integers 
	public static int  recIndecies(int indecies[],int counter, int secondery[], int index) {
		if (index == -1) {
			return 0;
		}
		indecies[counter]=index;

		return 1+recIndecies(indecies,++counter, secondery, secondery[index]);
		
	}

}
