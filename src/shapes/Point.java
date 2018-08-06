package shapes;

/**
 * An object with x and y coordinates)
 * 
 * @author CSC207 Group
 *
 */
public class Point {

	int x, y;	//the x and y coordinates of a point in 2d 

	
	/**
	 * Construct a point with the x and y coordinates of the point
	 * 
	 * @param x
	 * 		(an integer) the x coordinate value of the point
	 * @param y
	 * 		(an integer) the y coordinate value of the point
	 */

	public Point(int x, int y){
		this.x=x; this.y=y;
	}
	
	
	/**
	 * The method returns the X-coordinate of the point
	 * 
	 * @return (integer) the X-coordinate of the point
	 */
	public int getX() {
		return x;
	}

	
	/**
	 * sets the X-coordinate to the given int value
	 * 
	 * @param x 
	 * 		(an integer) the integer value of the new X coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	
	/**
	 * The method returns the Y-coordinate of the point
	 * 
	 * @return 
	 * 		(integer) the Y-coordinate of the point
	 */
	public int getY() {
		return y;
	}

	
	/**
	 * sets the Y-coordinate to the given int value
	 * 
	 * @param y
	 * 		(an integer) the integer value of the new Y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
}
