package build;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class GenTests {
	public static void main(String args[]) throws FileNotFoundException {
		File file = new File("test_pent.txt");
		PrintWriter out = new PrintWriter(file);
		String fileString = "";
		RandomEngine.init();
		for (int i = 0; i < 300; i ++) {
			fileString += i + "  " + generateLine() + System.lineSeparator();
		}
		//out.print("Hello");
		out.print(fileString);
		out.close();
	}
	
	public static String generateLine() {
		while (true) {
			String line = "";
			int n1 = RandomEngine.getInt(1, 264 - 2);
			double cost_of_parcels_A = RandomEngine.getDouble();
			int n2 = RandomEngine.getInt(1, 264 - n1 - 1);
			double cost_of_parcels_B = RandomEngine.getDouble();
			int n3 = RandomEngine.getInt(1, 264 - n1 - n2);
			double cost_of_parcels_C = RandomEngine.getDouble();

			System.out.println(n1 + "  " + cost_of_parcels_A + "  " + n2 + "  " + cost_of_parcels_B + "  " + n3 + "  " + cost_of_parcels_C);
			
			line += n1 + "  " + cost_of_parcels_A + "  " + n2 + "  " + cost_of_parcels_B + "  " + n3 + "  " + cost_of_parcels_C;
			 
			return line;
		}
	}
}
