package build;

import java.util.*;
import java.io.*;

/**
 * Interface for all the bots.
 * @author group #10 UM DKE2019
 */
interface Bot { 
	public void openParcelTests(int n, String filename) throws FileNotFoundException;
	//public double findDumbSolution();
	public double testParcels(Function f);
}