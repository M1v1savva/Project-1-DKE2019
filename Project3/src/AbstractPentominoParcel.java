package build;

/**
 * Abstract class that is introduced to the sake of convenient structure and short constructors implementations. 
 * @author group #10 UM DKE2019
 */
public class AbstractPentominoParcel extends AbstractParcel {
	protected int[][][] base;
	protected int[][] bits;
	
	/** 
	 * Creates an AbstractPentominoParcel object that represents a pentomino parcel with fixed coordinates, color id and orientation.
	 * @param _cost cost
	 * @param _number number of parcels of same type
	 * @param _x the first coordinate
	 * @param _y the second coordinate
	 * @param _z the third coordinate
	 * @param _colorID color id
	 * @param _bits all possible rotations
	 */ 
	protected AbstractPentominoParcel(double _cost, int _number, int _x, int _y, int _z, int _colorID, int[][][] _bits) {
		cost = _cost;
		number = _number;
		x = _x;
		y = _y;
		z = _z;
		colorID = _colorID;
		rotationID = 0;
		width = 0;
		height = 0;
		depth = 0;

		base = new int[_bits.length * 3][5][3];

		for (int i = 0; i < _bits.length; i++) {
			for (int j = 0; j < 5; j++) {
				base[i][j][0] = _bits[i][j][0];
				base[i][j][1] = _bits[i][j][1];
			}
		}

		for (int i = 0; i < _bits.length; i++) {
			for (int j = 0; j < 5; j++) {
				base[i + _bits.length][j][0] = _bits[i][j][0];
				base[i + _bits.length][j][2] = _bits[i][j][1];
			}
		}

		for (int i = 0; i < _bits.length; i++) {
			for (int j = 0; j < 5; j++) {
				base[i + 2 * _bits.length][j][1] = _bits[i][j][0];
				base[i + 2 * _bits.length][j][2] = _bits[i][j][1];
			}
		}
	}

	/**
	 * Performs rotation to the next consequtive possible rotation.
	 */
	public void rotate() {
		rotationID++;
		rotationID %= base.length;
		bits = base[rotationID];
	}

	/**
	 * Turns object to the specified location.
	 * @param _rotationID rotationID 
	 */
	@Override
	public void setRotationID(int _rotationID) {
		rotationID = _rotationID;
		bits = base[rotationID];
	}

	/**
	 * Returns current rotation info.
	 * @return bits
	 */
	public int[][] getBits() {
		return bits;
	}
}