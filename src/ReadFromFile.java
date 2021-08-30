import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile {
	public static int[] readFromFile(File file) {
		int ar[];
		try {

			Scanner scanner = new Scanner(file);
			int numbers = scanner.nextInt();
			ar = new int[numbers];
			boolean bools[]=new boolean[numbers+1];
			for (int i = 0; i < bools.length; i++) {
				bools[i]=false;
			}
			
			int counter = 0;
			int sum=0;
			while (scanner.hasNext()) {
				if(counter>=numbers) {
					AlertBox.display("Please fix the number of lids to match the powers");
					System.out.println("The counter is "+ counter+" and the numbers is "+numbers);
					return null;
				}
				int x=0;
				try {
					 x=scanner.nextInt();
				}catch(Exception ee) {
					AlertBox.display("Please just enter integers");
					return null;

				}
				if(bools[x]) {
					AlertBox.display("There is a reputation in the file");
					return null;
				}else {
					bools[x]=true;
				}
				sum+=x;
				ar[counter] = x;
				counter++;
			}
			
			if(counter!=numbers) {
				AlertBox.display("Please fix the number of lids to match the powers");
				System.out.println("The counter issssssss "+ counter+" and the numbers is "+numbers);
				return null;
			}else {
				if(sum!=(numbers*(numbers+1)/2)) {
					AlertBox.display("Please enter conscutive numbers with no Repetition");
					return null;
					
				}
				
				return ar;
			}
			
		} catch (FileNotFoundException e) {
		
			AlertBox.display("error occured while opening the file");
			return null;
		}

	}
}
