package build;

/**
 * Object of the following class represent vectors in finite dimensional space and allows to perform basic operations with vectors.
 * @author group #10 UM DKE2019
 */
public class Vec {
	/**
	 * Coordinates of the vector.
	 */
	public double[] v;

	/**
	 * The dimensions (length of coordinates array).
	 */	
	public int length = 0;

	/**
	 * Returns a new Vec object representing a 1-dimensional random normalized vector.
	 */		
	public Vec() {
		this(1);
	}

	/**
	 * Returns a new Vec object representing a len-dimensional random normalized vector.
	 * @param len dimension
	 */		
	public Vec(int len) {
		length = len;
		v = new double[len];
		for (int i = 0; i < len; i++)
			v[i] = RandomEngine.getDouble();

		normalize();
	}

	/**
	 * Returns a new Vec object with given set of coordinates. You do not need to update length variable, it is updates automatically.
	 * @param _v coordinates of a new vector
	 */		
	public Vec(double[] _v) {
		v = _v;
		length = v.length;
	}

	/**
	 * Returns a new Vec object representing the sum of the two given vectors.
	 * @param l the first vector
	 * @param r the second vector
	 * @return sum of the two vectors
	 */
	public static Vec add(Vec l, Vec r) {
		Vec res = new Vec(l.length);	
		for (int i = 0; i < l.length; i++)
			res.v[i] = l.v[i] + r.v[i];
		return res;
	}

	/**
	 * Returns a new Vec object representing the difference of the two given vectors.
	 * @param l the first vector
	 * @param r the second vector
	 * @return difference of the two vectors
	 */
	public static Vec substract(Vec l, Vec r) {
		Vec res = new Vec(l.length);
		for (int i = 0; i < l.length; i++)
			res.v[i] = l.v[i] - r.v[i];
		return res;
	}

	/**
	 * Returns a new Vec object representing the product of the two given vectors.
	 * @param l the first vector
	 * @param r the second vector
	 * @return product of the two vectors
	 */
	public static double multiply(Vec l, Vec r) {
		double res = 0;
		for (int i = 0; i < l.length; i++)
			res += l.v[i] * r.v[i];
		return res;
	}

	/**
	 * Returns a new Vec object representing the product of the given vector and the constant.
	 * @param l the vector
	 * @param r the constant
	 * @return product of vector and constant
	 */
	public static Vec multiply(Vec l, double r) {
		Vec res = new Vec(l.length);
		for (int i = 0; i < l.length; i++) 
			res.v[i] = l.v[i] * r;
		return res;
	}

	/**
	 * Returns a new Vec object representing the given vector divided by the given constant.
	 * @param l the vector
	 * @param r the constant
	 * @return vector divided by the constant value
	 */
	public static Vec divide(Vec l, double r) {
		Vec res = new Vec(l.length);
		for (int i = 0; i < l.length; i++) 
			res.v[i] = l.v[i] / r;
		return res;
	}

	/**
	 * Returns geometrical length of the vector.
	 * @return non-negative length of the vector
	 */
	public double size() {
		double res = 0;
		for (int i = 0; i < length; i++) 
			res += v[i] * v[i];
		return Math.sqrt(res);
	}

	/**
	 * Normalizes the vector.
	 */
	public void normalize() {
		double len = size();
		for (int i = 0; i < length; i++) 
			v[i] /= len;
	}

	/**
	 * Copies all the inner components of parameter vector to the current vector.
	 * @param another parameter vector
	 */
	public void copy(Vec another) {
		length = another.length;
		v = new double[length];
		for (int i = 0; i < length; i++)
			v[i] = another.v[i];
	}

	/**
	 * Checks whether two Vec objects are equal or not. Overrides the Object method.
	 * @param otherObject
	 * @return true is they are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		if (otherObject.getClass() != getClass())
			return false;
		Vec r = (Vec)otherObject;
		return (length == r.length && v == r.v);
	}

	/**
	 * Returns a string representation of the Vec object. Overrides the Object method.
	 * @return string representation
	 */
	@Override
	public String toString() {
		String ans = "[";
		for (int i = 0; i < length; i++) {
			ans += String.format("%-50s ", Double.toString(v[i]));
			if (i != length - 1)
				ans += " ";
		}
		ans += "]";
		return ans;
	}
}