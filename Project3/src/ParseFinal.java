package build;

import java.util.*;
import java.io.*;

public class ParseFinal {
	public static ArrayList<Vec> parse(String filename) {
		ArrayList<Vec> ans = new ArrayList<>();
		try {
			File f = new File(filename);
			Scanner input = new Scanner(f);

			while (input.hasNextLine()) {
				String line = input.nextLine().replace("[", "").replace("]", "");
				String[] doubles = line.split("\\s+");
				double a = Double.parseDouble(doubles[0]);	
				double b = Double.parseDouble(doubles[1]);
				double c = Double.parseDouble(doubles[2]);
				double d = Double.parseDouble(doubles[3]);
				ans.add(new Vec(new double[]{a, b, c, d}));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return ans;
	}
}