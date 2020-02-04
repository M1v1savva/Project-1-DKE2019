package build;

import java.io.*;
import java.util.*;

public class Experiment4 {
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		RandomEngine.init();
		Bot currentBot = new BotPentominoGaps();
		try {
			currentBot.openParcelTests(5, "test_pent.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		//TODO start whatever experiment you want


		//System.out.println(Bot.findDumbSolution());

		long startTime = System.nanoTime();

		GenAlgoInterface g1 = new GenAlgo1(25, 8, 0.2, currentBot, new Function1(), "population-pent-gaps-final-10.log", "population-pent-gaps", 11);
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