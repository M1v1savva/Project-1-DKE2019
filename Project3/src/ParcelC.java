package build;

/**
 * Objects of the following class represent the parcels of type C. 
 * @author group #10 UM DKE2019
 */
public class ParcelC extends AbstractCuboidParcel {	
	public ParcelC(double _cost, int _number) {
		this(_cost, _number, 0, 0, 0, 2);
	}

	public ParcelC(double _cost, int _number, int _colorID) {
		this(_cost, _number, 0, 0, 0, _colorID);
	}

	/**
	 * Creates a ParcelC object that represents a parcel of type C with fixed coordinates and color id but random orientation.
	 * @param _cost cost of the parcel
	 * @param _number number of parcels
	 * @param _x the first coordinate 
	 * @param _y the second coordinate
	 * @param _z the third coordinate
	 * @param _colorID color id
	 */
	public ParcelC(double _cost, int _number, int _x, int _y, int _z, int _colorID) {
		super(_cost, _number, 3, 3, 3, _x, _y, _z, _colorID, new int[][]{{3, 3, 3}});
	}
}