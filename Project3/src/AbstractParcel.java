package build;

/**
 * Abstract class containing common field and methods of all types of parcels that we needed throughout our project. 
 * @author group #10 UM DKE2019
 */
public abstract class AbstractParcel {	
	protected double cost;
	protected int number, width, height, depth, x, y, z, colorID, rotationID;

	/** 
	 * Sets the cost of the parcel.
	 * @param _cost
	 */
	public void setCost(double _cost) {
		cost = _cost;
	}

	/** 
	 * Returns the cost of the parcel.
	 * @return cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Return the number of parcels.
	 * @return number of parcels
	 */
	public int getNumber() {
		return number;
	}

	/** 
	 * Sets the number of parcels.
	 * @param _number
	 */
	public void setNumber(int _number) {
		number = _number;
	}

	/** 
	 * Changes the number by the given delta parameter.
	 * @param delta
	 */
	public void addNumber(int delta) {
		number += delta;
	}

	/**
	 * Returns the scaled width of the parcel.
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the scaled height of the parcel.
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the scaled depth of the parcel.
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Returns the color id of the parcel.
	 * @return color id
	 */
	public int getColorID() {
		return colorID;
	}

	/**
	 * Sets the color id of the parcel.
	 * @param _colorID id
	 */
	public void setColorID(int _colorID) {
		colorID = _colorID;
	}

	/**
	 * Returns the rotation id of the parcel.
	 * @return rotation id
	 */
	public int getRotationID() {
		return rotationID;
	}

	/**
	 * Sets the rotation id of the parcel.
	 * @param _rotationID rotation id
	 */
	public void setRotationID(int _rotationID) {
		rotationID = _rotationID;
	}	

	/**
	 * Returns the first coordinate of the parcel.
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the first coordinate of the parcel.
	 * @param _x the x coordinate 
	 */
	public void setX(int _x) {
		x = _x;
	}

	/**
	 * Returns the second coordinate of the parcel.
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the second coordinate of the parcel.
	 * @param _y y coordinate 
	 */
	public void setY(int _y) {
		y = _y;
	}

	/**
	 * Returns the third coordinate of the parcel.
	 * @return z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Sets the third coordinate of the parcel.
	 * @param _z z coordinate 
	 */
	public void setZ(int _z) {
		z = _z;
	}
}