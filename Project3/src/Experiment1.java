package build;

import java.io.*;
import java.util.*;

public class Experiment1 {
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		RandomEngine.init();
		Bot currentBot = new BotCuboidCost();
		try {
			currentBot.openParcelTests(60, "test.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		//TODO start whatever experiment you want


		//System.out.println(Bot.findDumbSolution());

		long startTime = System.nanoTime();

		GenAlgoInterface g1 = new GenAlgo1(50, 17, 0.1, currentBot, new Function1(), "population-final-2.log", "population", 3);
		calcGen(g1);

		long elapsedTime = System.nanoTime() - startTime;
		long sec = elapsedTime / 1000000000;
		System.out.println("elapsed time: " + (sec / 3600) + " h " + ((sec % 3600) / 60) + " m " + (sec % 60) + " s");
	}

	public static void calcGen(GenAlgoInterface algo) {
		algo.evolve();
		algo.select();
		algo.finish();
	}
}