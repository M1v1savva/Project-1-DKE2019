package build;

/**
 * Abstract class that is introduced to the sake of convenient structure and short constructors implementations. 
 * @author group #10 UM DKE2019
 */
public class AbstractCuboidParcel extends AbstractParcel {	
	protected int[][] ids;
	/**
	 * Creates an AbstractCuboidParcel object that represents a cuboid parcel with fixed coordinates, color id and orientation.
	 * @param _cost cost
	 * @param _number number of parcels of same type
	 * @param _width width
	 * @param _height height
	 * @param _depth depth
	 * @param _x the first coordinate
	 * @param _y the second coordinate
	 * @param _z the third coordinate
	 * @param _colorID color id
	 * @param _ids all possible rotations
	 */
	protected AbstractCuboidParcel(double _cost, int _number, int _width, int _height, int _depth, int _x, int _y, int _z, int _colorID, int[][] _ids) {
		cost = _cost;
		number = _number;
		x = _x;
		y = _y;
		z = _z;
		colorID = _colorID;
		rotationID = 0;
		width = _width;
		height = _height;
		depth = _depth;
		ids = _ids;
	}

	/**
	 * Performs rotation to the next consequtive possible rotation.
	 */
	public void rotate() {
		rotationID++;
		rotationID %= ids.length;
		width = ids[rotationID][0];
	    height = ids[rotationID][1];
	    depth = ids[rotationID][2];
	}

	/**
	 * Turns object to the specified location.
	 * @param _rotationID rotationID 
	 */
	@Override
	public void setRotationID(int _rotationID) {
		rotationID = _rotationID;
		width = ids[rotationID][0];
	    height = ids[rotationID][1];
	    depth = ids[rotationID][2];
	}
}