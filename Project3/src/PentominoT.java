package build;

/**
 * Objects of the following class represent the parcels of type A. 
 * @author group #10 UM DKE2019
 */
public class PentominoT extends AbstractPentominoParcel {	
	/**
	 * Creates a ParcelA object that represents a parcel of type A default(0) coordinates, color id and orientation.
	 */

	private static int[][][] base = new int[][][]
							 {{{0, 2, 0}, {1, 2, 0}, {2, 2, 0}, {1, 1, 0}, {1, 0, 0}},
                              {{0, 0, 0}, {1, 0, 0}, {2, 0, 0}, {1, 1, 0}, {1, 2, 0}},
                              
                          	  {{0, 0, 0}, {0, 1, 0}, {0, 2, 0}, {1, 1, 0}, {2, 1, 0}},
                              {{0, 1, 0}, {1, 1, 0}, {2, 0, 0}, {2, 1, 0}, {2, 2, 0}}};

	public static void main(String[] args) {
		for (int i = 0; i < base.length; i++) {
			int[][] bits = new int[3][3];
			for (int j = 0; j < base[i].length; j++) {
				int[] coord = base[i][j];
				bits[coord[0]][coord[1]] = 1;
			}
			for (int j = 0; j < bits.length; j++) {
				for (int t = 0; t < bits[j].length; t++) {
					System.out.print(bits[j][t]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public PentominoT() {
		this(0, 0, 0, 0, 0); 
	}	

	public PentominoT(double _cost, int _number) {
		this(_cost, _number, 0, 0, 0);
	}

	/**
	 * Creates a ParcelA object that represents a parcel of type A with fixed coordinates but random color id and orientation.
	 * @param _cost cost of the parcel
	 * @param _number number of parcels
	 * @param _x the first coordinate
	 * @param _y the second coordinate
	 * @param _z the third coordinate
	 */
	public PentominoT(double _cost, int _number, int _x, int _y, int _z) {
		this(_cost, _number, _x, _y, _z, 2); //TODO color randomization
	}

	/**
	 * Creates a ParcelA object that represents a parcel of type A with fixed coordinates and color id but random orientation.
	 * @param _cost cost of the parcel
	 * @param _number number of parcels
	 * @param _x the first coordinate 
	 * @param _y the second coordinate
	 * @param _z the third coordinate
	 * @param _colorID color id
	 */
	public PentominoT(double _cost, int _number, int _x, int _y, int _z, int _colorID) {
		super(_cost, _number, _x, _y, _z, _colorID, base);
	}
}