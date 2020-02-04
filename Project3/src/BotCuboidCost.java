package build;

import java.util.*;
import java.io.*;

/**
 * The following class is responsible for packing simulatoins.
 * @author group #10 UM DKE2019
 */
public class BotCuboidCost extends BotCuboid {
	/**
	 * Opens tests.
	 * @param n number of tests
	 * @param filename the name of the file 
	 */
	public void openParcelTests(int n, String filename) throws FileNotFoundException {
		File fi = new File(filename);
		Scanner input = new Scanner(fi);
		
		for (int i = 0; i < n; i++) {
			int line = input.nextInt();
			int number = input.nextInt();
			numb1.add(number);
			double cost = input.nextDouble();
			AbstractCuboidParcel o1 = new ParcelA(cost, number);

			number = input.nextInt();
			numb2.add(number);
			cost = input.nextDouble();
			AbstractCuboidParcel o2 = new ParcelB(cost, number);
	
			number = input.nextInt();
			numb3.add(number);
			cost = input.nextDouble();
			AbstractCuboidParcel o3 = new ParcelC(cost, number);

			obj1.add(o1);
			obj2.add(o2);
			obj3.add(o3);
		}
	}

	/**
	 * Returns sum of costs in the field.
	 * @return res sum of costs
	 */
	@Override
	public double getAnswer() {
		return Field.getSum();
	}

	/*public double findDumbSolution() {
		double res = 0;
		for (int i = 0; i < obj1.size(); i++) {
			int number = 22;
			for (int j = 0; j < number; j++) {
				double[] kek = new double[3];
				if (obj1.get(i).getNumber() > 0)
					kek[0] = obj1.get(i).getCost();
				if (obj2.get(i).getNumber() > 0)
					kek[1] = obj2.get(i).getCost();
				if (obj3.get(i).getNumber() > 0)
					kek[2] = obj3.get(i).getCost();

				if (kek[0] >= kek[1] && kek[0] >= kek[2] && kek[0] != 0) {
					obj1.get(i).addNumber(-1);
					res += kek[0];
					continue;
				}
				
				if (kek[1] >= kek[0] && kek[1] >= kek[2] && kek[1] != 0) {
					obj2.get(i).addNumber(-1);
					res += kek[1];
					continue;
				}
				
				if (kek[2] >= kek[0] && kek[2] >= kek[1] && kek[2] != 0) {
					obj3.get(i).addNumber(-1);
					res += kek[2];
					continue;
				}

				break;	
			}

			obj1.get(i).setNumber(numb1.get(i));
			obj2.get(i).setNumber(numb2.get(i));
			obj3.get(i).setNumber(numb3.get(i));
		}
		return res;
	}*/
}