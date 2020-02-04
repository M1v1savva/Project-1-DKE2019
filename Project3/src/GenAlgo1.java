package build;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class GenAlgo1 implements GenAlgoInterface {
	private String currentLogFilename, finalLogFilename;
	private int POP_SZ = 20;
	private int OFF_SZ = 6;
	private double PERC = 0.1;
	Vec[] population = new Vec[1000];
	Vec[] offset = new Vec[333];
	Bot tester;
	Function f;

	public GenAlgo1(int pop_sz, int off_sz, double _perc, Bot _tester, Function _f, String populationData, String currentName, int populationNumber) {
		POP_SZ = pop_sz;
		OFF_SZ = off_sz;
		PERC = _perc;
		currentLogFilename = currentName + "-" + Integer.toString(populationNumber) + ".log";
		finalLogFilename = currentName + "-final-" + Integer.toString(populationNumber) + ".log";
		tester = _tester;
		f = _f;
		try {
			Log.add(currentLogFilename);
			Log.add(finalLogFilename);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		try {
			File f = new File(populationData);
			Scanner input = new Scanner(f);
			for (int i = 0; i < POP_SZ; i++) {
				String line = input.nextLine().replace("[", "").replace("]", "");

				String[] doubles = line.split("\\s+");
				for (int j = 0; j < doubles.length; j++)
					System.out.println(doubles[j]);
				double a = Double.parseDouble(doubles[0]);	
				double b = Double.parseDouble(doubles[1]);
				double c = Double.parseDouble(doubles[2]);
				double d = Double.parseDouble(doubles[3]);

				population[i] = new Vec(new double[]{a, b, c, d});
			}
			for (int i = 0; i < POP_SZ; i++)
				Log.write(currentLogFilename, "", population[i], true);
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			System.exit(0);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void evolve() {
		double[] fitness = new double[POP_SZ];
		for (int i = 0; i < POP_SZ; i++) {
			population[i].normalize();
			System.out.println("function #" + i);
			f.setVec(population[i]);
			fitness[i] = tester.testParcels(f);
		}
		
		ArrayList<Integer> toBeShuffled = new ArrayList<>();
		for (int i = 0; i < POP_SZ; i++)
			toBeShuffled.add(i);

		toBeShuffled.sort(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (fitness[o1] < fitness[o2])
					return -1;
				else if (fitness[o1] == fitness[o2])
					return 0;
				else
					return 1;
			}
		});

		Vec[] _population = new Vec[POP_SZ];
		double[] _fitness = new double[POP_SZ];

		for (int i = 0; i < POP_SZ; i++) {
			_population[i] = new Vec();
			_population[i].copy(population[i]);
			_fitness[i] = new Double(fitness[i]);
		}

		for (int i = 0; i < POP_SZ; i++) {
			population[i] = _population[toBeShuffled.get(i)];
			fitness[i] = _fitness[toBeShuffled.get(i)];
		}

		for (int i = 0; i < POP_SZ; i++) {
			try {
				Log.write(currentLogFilename, "", population[i], false);
				Log.write(currentLogFilename, "", fitness[i], true);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		ArrayList<Vec[]> offsetToBePrinted = new ArrayList<>();
		ArrayList<Vec[]> offsetToBePrintedMutated = new ArrayList<>();

		for (int i = 0; i < OFF_SZ; i++) {
			Collections.shuffle(toBeShuffled);

			int id1 = -1, id2 = -1;
			for (int j = 0; j < POP_SZ * PERC; j++) {
				int currentID = toBeShuffled.get(j);
				if (id1 == -1) {
					id1 = currentID;
					continue;
				}
				if (id2 == -1) {
					id2 = currentID;
					continue;
				}
				if (fitness[currentID] > fitness[id1]) {
					id2 = id1;
					id1 = currentID;
					continue;
				}
				if (fitness[currentID] > fitness[id2]) {
					id2 = currentID;
					continue;
				}
			}

			Vec p1 = Vec.multiply(population[id1], fitness[id1]);
			Vec p2 = Vec.multiply(population[id2], fitness[id2]);
			Vec son = Vec.add(p1, p2);
			son.normalize();
			offset[i] = son;

			Vec old_son = son;
			boolean result = mutate(offset[i]);

			if (result) 
				offsetToBePrintedMutated.add(new Vec[]{population[id1], population[id2], old_son, son});
			else
				offsetToBePrinted.add(new Vec[]{population[id1], population[id2], son});
		}
		try {
			for (int j = 0; j < offsetToBePrinted.size(); j++) {
				Vec[] cur = offsetToBePrinted.get(j);
				Log.write(currentLogFilename, "parent1", cur[0], false);
				Log.write(currentLogFilename, "parent2", cur[1], false);
				Log.write(currentLogFilename, "son", cur[2], true);
			}
			
			for (int j = 0; j < offsetToBePrintedMutated.size(); j++) {
				Vec[] cur = offsetToBePrintedMutated.get(j);
				Log.write(currentLogFilename, "parent1", cur[0], false);
				Log.write(currentLogFilename, "parent2", cur[1], false);
				Log.write(currentLogFilename, "son_before_mutation", cur[2], false);
				Log.write(currentLogFilename, "son_after_mutation", cur[3], true);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void select() {
		Vec[] replaced = new Vec[OFF_SZ];
		for (int i = 0; i < OFF_SZ; i++) {
			replaced[i] = population[i];
			population[i] = offset[i];
		}

		for (int i = 0; i < OFF_SZ; i++) {
			try {
				Log.write(currentLogFilename, "replaced", replaced[i], false);
				Log.write(currentLogFilename, "with", offset[i], true);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		for (int i = 0; i < POP_SZ; i++) {
			try {
				Log.write(finalLogFilename, "", population[i], true);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	private boolean mutate(Vec v) {
		double prob = RandomEngine.getDouble();
		if (prob >= 0.05)
			return false;

		double val = RandomEngine.getDouble() * 2 / 5;
		val -= 0.2;
		if (val == 0)
			val = 0.2;
		int id = RandomEngine.getInt(0, v.length);
		v.v[id] += val;
		v.normalize();
		return true;
	}

	public void finish() {
		try {
			Log.delete(currentLogFilename);
			Log.delete(finalLogFilename);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);	
		}
	}
}