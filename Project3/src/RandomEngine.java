package build;

import java.security.*;

/**
 * The following class is fully static and performs secure random values generation.
 * @author group #10 UM DKE2019
 */
public class RandomEngine {
	private static SecureRandom r;

	/**
	 * The following method gets the class ready for random values generation.
	 */
	public static void init() {
		try {
			r = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Returns a random integer value from semi-interval [mn; mx).
	 * @param mn bottom value (including)
	 * @param mx top value (excluding)
	 * @return random integer
	 */
	public static int getInt(int mn, int mx) {
		return mn + r.nextInt(mx - mn);
	}

	/**
	 * Returns a random double value from semi-interval [0; 1);
	 * @return random double
	 */
	public static double getDouble() {
		return r.nextDouble();
	}
}