package build;

/**
 * The following class is responsible for packing simulatoins.
 * @author group #10 UM DKE2019
 */
public class Function {
	/**
	 * Vector representing the assessing function coefficients.
	 */
	public Vec p;

	/** 
	 * Returns a Function object with p = Vec().
	 */
	public Function() {
		p = new Vec();
	}

	/** 
	 * Returns a Function object with p = _p.
	 * @param _p given set of coefficients
	 */
	public Function(Vec _p) {
		p = _p;
	}

	public void setVec(Vec _p) {
		p = _p;
	}

	/**
	 * Returns the value of the function with the current field state as a parameter.
	 */
	public double calc() {
		return 0;
	}
}