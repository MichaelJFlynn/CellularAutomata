
import structure5.*;

/**
 * A Position is an (x,y) coordinate in the World, much like
 * the Positions for the maze program.
 */    
public class Position {


    /** The North compass point. */

    static public final int NORTH = 0;     

    /** The East compass point. */
    static public final int EAST = 1;      

    /** The South compass point. */
    static public final int SOUTH = 2;     

    /** The West compass point. */
    static public final int WEST = 3;      

    protected int x,y;

    /**
     * Create a new position for the given x and y coordinates.
     */
    public Position(int x, int y) {
	this.x = x;
	this.y = y;
    }

    /**
     * Return the x coordinate for the position.
     */
    public int getX() {
	return x; 
    }

    /**
     * Return the y coordinate for the position.
     */
    public int getY() {
	return y;
    }

    public String toString() { 
	return "(" + x + ", " + y + ")";
    }

    /**
     * Return a new position that is in one of the four
     * compass directions from this.
     * @pre direction must be NORTH, SOUTH, EAST, or WEST.
     * @post the Position adjecent to this in the given direction.
     */
    public Position getAdjacent(int direction) {
	switch (direction) {
	case NORTH:
	    return new Position(x, y-1);
	case SOUTH:
	    return new Position(x, y+1);
	case EAST:
	    return new Position(x+1, y);
	case WEST:
	    return new Position(x-1, y);
	default:
	    Assert.fail("bad direction");
	}
	return null;
    }
}
