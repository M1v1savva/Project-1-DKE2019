package build;

import java.util.*;
import java.io.*;

/**
 * The following class is responsible for packing simulatoins.
 * @author group #10 UM DKE2019
 */
public abstract class BotCuboid implements Bot {
	public ArrayList<AbstractCuboidParcel> obj1 = new ArrayList<>(), obj2 = new ArrayList<>(), obj3 = new ArrayList<>();
	public Function f;
	public ArrayList<Integer> numb1 = new ArrayList<>(), numb2 = new ArrayList<>(), numb3 = new ArrayList<>();

	/**
	 * Goes over all the tests and calculates sum of values using function f.
	 * @param f
	 * @return result for all the tests
	 */
	public double testParcels(Function f) {
		double res = 0;
		for (int j = 0; j < obj1.size(); j++) {
			String logOutput = "game #" + Integer.toString(j);
			String goingBack = "";
			for (int k = 0; k < logOutput.length(); k++)
				goingBack += "\b";
			
			System.out.println(logOutput);

			obj1.get(j).setNumber(numb1.get(j));
			obj2.get(j).setNumber(numb2.get(j));
			obj3.get(j).setNumber(numb3.get(j));
			double delta = simulate(obj1.get(j), obj2.get(j), obj3.get(j), f);	
			System.out.println(delta);
			res += delta;
		}
		return res;
	}

	/**
	 * Applies a function, basically solves the problem.
	 * @param _objA PentominoL
	 * @param _objB PentominoP
	 * @param _objC PentominoT
	 * @param _f assessing function
	 * @return result
	 */
	public double simulate(AbstractParcel _objA, AbstractParcel _objB, AbstractParcel _objC, Function _f) {
		Field.init();
		f = _f;

		AbstractCuboidParcel objA = (AbstractCuboidParcel)_objA;
		AbstractCuboidParcel objB = (AbstractCuboidParcel)_objB;
		AbstractCuboidParcel objC = (AbstractCuboidParcel)_objC;

		boolean nextPhase = true;

		if (objA.getCost() <= 0)
			objA.setNumber(0);
		if (objB.getCost() <= 0)
			objB.setNumber(0);
		if (objC.getCost() <= 0)
			objC.setNumber(0);

		while (nextPhase) {
			double resultA = 0,
				   resultB = 0,
				   resultC = 0;

			resultA = getBestPos(objA);
			resultB = getBestPos(objB);
			resultC = getBestPos(objC);

			nextPhase = false;
			if (resultA >= resultB && resultA >= resultC && resultA != -1e9) {
				Field.addParcel(objA);
				nextPhase = true;
			} else if (resultB >= resultA && resultB >= resultC && resultB != -1e9) {	
				Field.addParcel(objB);
				nextPhase = true;
			} else if (resultC >= resultA && resultC >= resultB && resultC != -1e9) {
				Field.addParcel(objC);
				nextPhase = true;
			} 
		}
		return getAnswer();
	}

	/**
	 * To be overridden.
	 */
	public double getAnswer() {
		return 0;
	}
	
	/**
	 * Searches for all the positions for parcel and keeps best coordinates in passed object. Sets coordinates to (-1, -1, -1)
	 * and returns -1e9 in case position is not found or all the parcels of the given type are already used.
	 * @param obj passed parcel
	 * @return best function value or -1e9 in case there is no possible position 
	 */
	public double getBestPos(AbstractCuboidParcel obj) {
		int[] coord = new int[]{-1, -1, -1, 0};
		double ans = -1e9;

		if (obj.getNumber() == 0)
			return ans;

		for (int x = 0; x < Field.getWidth(); x++) {
		for (int y = 0; y < Field.getHeight(); y++) {
		for (int z = 0; z < Field.getDepth(); z++) {
			obj.setX(x);
			obj.setY(y);
			obj.setZ(z);
			obj.setRotationID(0);

			do {
				boolean ok = false;
				if (z == 0)
					ok = true;
				else if (Field.isNotEmpty(x, y, z - 1) == 1 && 
					     Field.isNotEmpty(x, y, z) == 0)
					ok = true;
				else if (Field.isNotEmpty(x + obj.getWidth() - 1, y, z - 1) == 1 && 
					     Field.isNotEmpty(x + obj.getWidth() - 1, y, z) == 0)
					ok = true;
				else if (Field.isNotEmpty(x, y + obj.getHeight() - 1, z - 1) == 1 && 
					     Field.isNotEmpty(x, y + obj.getHeight() - 1, z) == 0)
					ok = true;
				else if (Field.isNotEmpty(x + obj.getWidth() - 1, y + obj.getHeight() - 1, z - 1) == 1 && 
					     Field.isNotEmpty(x + obj.getWidth() - 1, y + obj.getHeight() - 1, z) == 0)
					ok = true;
				
				if (!ok) {
					obj.rotate();
					continue;
				}

				boolean result = Field.addParcel(obj);
				if (result) {
					double cur = f.calc();
					Field.deleteParcel(obj);

					if (cur > ans) {
						ans = cur;
						coord[0] = obj.getX();
						coord[1] = obj.getY();
						coord[2] = obj.getZ();
						coord[3] = obj.getRotationID();
					}
				}
				obj.rotate();
			} while (obj.getRotationID() != 0);
		}
		}
		}

		obj.setX(coord[0]);
		obj.setY(coord[1]);
		obj.setZ(coord[2]);
		obj.setRotationID(coord[3]);
		return ans;
	}
}