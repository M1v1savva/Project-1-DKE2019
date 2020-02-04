package build;

/**
 * The following class represents the truck in which we try to fit the given multiset of parcels.
 * @author group #10 UM DKE2019
 */
public class Field {  
    /**
     * The width of the field, scaled (multiplied by 2) in order to stay with checkerboard with integer indexation.
     */
    public static final int WIDTH = 8;

    /**
     * The height of the field, scaled (multiplied by 2) in order to stay with checkerboard with integer indexation.
     */
    public static final int HEIGHT = 5;
    
    /**
     * The depth of the field, scaled (multiplied by 2) in order to stay with checkerboard with integer indexation.
     */
    public static final int DEPTH = 33;
    
    /**
     * Default checkerboard cell size for animation.
     */
    public static final int DEFAULT_CELL_SIZE = 20;
    
    private static int[][][] used;
    private static double sum;

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getDepth() {
        return DEPTH;
    }
   
    /**
     * Clears the field and sets the current sum of parcel costs equal to zero.
     */
    public static void init() {
        sum = 0;
        used = new int[WIDTH][HEIGHT][DEPTH];
        for (int i = 0; i < WIDTH;  i++)
        for (int j = 0; j < HEIGHT; j++)
        for (int k = 0; k < DEPTH;  k++)
            used[i][j][k] = -1;
    }
   
    /**
     * Adds a parcel to the field. If successfully, then it also updates the current sum of parcel costs.
     * @param obj The parcel to be added
     * @return true if we can, false otherwise
     */
    public static boolean addParcel(AbstractCuboidParcel obj) {
        int colorID = obj.getColorID();
        int x = obj.getX(), y = obj.getY(), z = obj.getZ();
 
        for (int i = 0; i < obj.getWidth();  i++) 
        for (int j = 0; j < obj.getHeight(); j++) 
        for (int k = 0; k < obj.getDepth();  k++) {
            if (inRange(x + i, y + j, z + k) && used[x + i][y + j][z + k] != -1)
                return false;
            if (!inRange(x + i, y + j, z + k))
                return false;
        }
 
        for (int i = 0; i < obj.getWidth();  i++) 
        for (int j = 0; j < obj.getHeight(); j++) 
        for (int k = 0; k < obj.getDepth();  k++) 
        	used[x + i][y + j][z + k] = colorID;

        obj.addNumber(-1);
        sum += obj.getCost();
        return true;
    }
    
    /**
     * Deletes a parcel from the field and decrements the current sum of parcel costs by the cost of deleted parcel. 
     * @param obj the parcel to be deleted
     */
    public static void deleteParcel(AbstractCuboidParcel obj) {
        int x = obj.getX(), y = obj.getY(), z = obj.getZ();
       
        for (int i = 0; i < obj.getWidth();  i++) 
        for (int j = 0; j < obj.getHeight(); j++) 
        for (int k = 0; k < obj.getDepth();  k++) 
            if (inRange(x + i, y + j, z + k) && used[x + i][y + j][z + k] == obj.getColorID())
                used[x + i][y + j][z + k] = -1;

        obj.addNumber(1);
       	sum -= obj.getCost();
    }

    /**
     * Adds a parcel to the field. If successfully, then it also updates the current sum of parcel costs.
     * @param obj The parcel to be added
     * @return true if we can, false otherwise
     */
    public static boolean addParcel(AbstractPentominoParcel obj) {
        int colorID = obj.getColorID();
        int[][] bits = obj.getBits();
        int x = obj.getX(), y = obj.getY(), z = obj.getZ();
 
        for (int i = 0; i < 5; i++) {
            if (inRange(x + bits[i][0], y + bits[i][1], z + bits[i][2]) && used[x + bits[i][0]][y + bits[i][1]][z + bits[i][2]] != -1)
                return false;
            if (!inRange(x + bits[i][0], y + bits[i][1], z + bits[i][2]))
                return false;
        }
 
        for (int i = 0; i < 5; i++) 
            used[x + bits[i][0]][y + bits[i][1]][z + bits[i][2]] = colorID;

        obj.addNumber(-1);
        sum += obj.getCost();
        return true;
    }

    /**
     * Deletes a parcel from the field and decrements the current sum of parcel costs by the cost of deleted parcel. 
     * @param obj the parcel to be deleted
     */
    public static void deleteParcel(AbstractPentominoParcel obj) {
        int x = obj.getX(), y = obj.getY(), z = obj.getZ();
       
        int[][] bits = obj.getBits();
        for (int i = 0; i < 5; i++)
            if (inRange(x + bits[i][0], y + bits[i][1], z + bits[i][2]) && used[x + bits[i][0]][y + bits[i][1]][z + bits[i][2]] == obj.getColorID())
                used[x + bits[i][0]][y + bits[i][1]][z + bits[i][2]] = -1;

        obj.addNumber(1);
        sum -= obj.getCost();
    }
   
    /**
     * Verifies if a parcel's cell is within the range of the field.
     * @param x the first coordinate
     * @param y the second coordinate
     * @param z the third coordinate
     * @return true if the parcel is within the range of the field, false otherwise
     */
    public static boolean inRange(int x, int y, int z) {
        return (0 <= x && x < WIDTH && 0 <= y && y < HEIGHT && 0 <= z && z < DEPTH);
    }
   
    /** 
     * Verifies whether the field's cell is within the boards and empty.
     * @param x the first coordinate
     * @param y the second coordinate
     * @param z the third coordinate
     * @return 1 if the parcel's cell is emty and inside, 0 otherwise
     */ 
    public static int isNotEmpty(int x, int y, int z) {
        return ((inRange(x, y, z) && (used[x][y][z] != -1)) ? 1 : 0);
    } 

    /**
     * Returns the color of the cell.
     * @param x the first coordinate
     * @param y the second coordinate
     * @param z the third coordinate
     * @return used[x][y][z] if coordinates are in range, -1 otherwise
     */
    public static int getColor(int x, int y, int z) {
        if (!inRange(x, y, z))
            return -1;
        else 
            return used[x][y][z];
    }

    /**
     * Returns current sum of parcel costs that are placed in the field.
     * @return the sum of parcel costs
     */
    public static double getSum() {
        return sum;
    }
 
    /**
     * Returns the field represented as a 3-dimensional static integer array.
     * Each cell contains a parcelID(non-negative integer value) of a parcel that covers this cell or -1 in case it is empty.
     * @return the field
     */
    public static int[][][] getField() {
        return used;
    }
}