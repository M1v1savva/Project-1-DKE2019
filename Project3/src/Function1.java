package build;

/**
 * The objects of the following class represent the assessing linear functions with different coefficients.
 * @author group #10 UM DKE2019
 */
public class Function1 extends Function {
	/** 
	 * Returns a Function1 object.
	 */
	public Function1() {
		super();
	}

	/** 
	 * Returns a Function1 object.
	 * @param _v vector to be used
	 */
	public Function1(Vec _v) {
		super(_v);
	} 

	/**
	 * Returns the value of the function with the current field state as a parameter.
	 * Function is a scalar product of coefficient vector and parameter vector.
	 * Public vector p represents the coefficient vector.
	 * The following function calculates the parameter vector of dimension 4.
	 * The first dimension goes for sum of costs.
	 * The second dimension goes for number of "complete holes".
	 * The third dimension goes for number of "almost holes".
	 * The fourth dimension goes for bumpiness.
	 * By complete holes i mean empty cells that are restricted at least from 3 sides in any plane.
	 * By almost holes i mean empty cells that have at least one non-empty cell above.
	 * By bumpiness i mean sum of absolute differences of each pair of neighbour columns.
	 */
	@Override
	public double calc() {
		Vec param = new Vec(new double[]{0, 0, 0, 0});
		param.v[0] = Field.getSum();
		for (int z = 0; z < Field.getDepth();  z++) {
		for (int x = 0; x < Field.getWidth();  x++) {
		for (int y = 0; y < Field.getHeight(); y++) {
			int plane1 = 0, plane2 = 0, plane3 = 0;

			plane1 += Field.isNotEmpty(x - 1, y, z);
			plane1 += Field.isNotEmpty(x, y - 1, z);
			plane1 += Field.isNotEmpty(x + 1, y, z);
			plane1 += Field.isNotEmpty(x, y + 1, z);
				
			plane2 += Field.isNotEmpty(x - 1, y, z);
			plane2 += Field.isNotEmpty(x, y, z - 1);
			plane2 += Field.isNotEmpty(x + 1, y, z);
			plane2 += Field.isNotEmpty(x, y, z + 1);
			
			plane3 += Field.isNotEmpty(x, y, z - 1);
			plane3 += Field.isNotEmpty(x, y - 1, z);
			plane3 += Field.isNotEmpty(x, y, z + 1);
			plane3 += Field.isNotEmpty(x, y + 1, z);

			if (Math.max(Math.max(plane1, plane2), plane3) >= 3) {
				param.v[1]++;
				continue;
			}

			int ok = 0;
			for (int z1 = z + 1; z1 < Field.getDepth(); z1++)
				ok = Math.max(Field.isNotEmpty(x, y, z1), ok);
			param.v[2] += ok;
		}
		}
		}

		int[][] height = new int[Field.getWidth()][Field.getHeight()];

		for (int z = 0; z < Field.getDepth();  z++) 
		for (int x = 0; x < Field.getWidth();  x++) 
		for (int y = 0; y < Field.getHeight(); y++) 
			if (Field.isNotEmpty(x, y, z) == 1)
				height[x][y] = z; 

		for (int x = 0; x < Field.getWidth() - 1; x++) 
			for (int y = 0; y < Field.getHeight(); y++) 
				param.v[3] += Math.abs(height[x][y] - height[x + 1][y]);

		for (int y = 0; y < Field.getHeight() - 1; y++) 
			for (int x = 0; x < Field.getWidth(); x++)
				param.v[3] += Math.abs(height[x][y] - height[x][y + 1]);

		return Vec.multiply(param, p);
	}
}