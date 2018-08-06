package shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * This class makes a circle shape. Circle gets a point that is the center of it.
 * Circle has radius from the center of the circle. It has a color, a line thickness,
 * and a boolean dictating if it's filled or not. Circle class also can paint it's current instance 
 * of circle.
 * 
 * @author CSC207 Group
 *
 */

public class Circle extends Shape {
	private Point centre;
	private int radius;
	
	/**
	 * Construct a Circle with a center point, and its radius. 
	 * Sets its color, thickness and if the circle is filled or not.
	 * 
	 * @param centre
	 * 			(a Point) consisting of X and Y coordinates. This is the center of the circle.
	 * @param radius
	 * 			(a non-negative integer) the radius of the circle 
	 * @param color
	 * 			(a Color object) the color of the circle
	 * @param thickness
	 * 			(a non-negative integer) This determines with thickness of the border of the circle
	 * @param isFill
	 * 			(a boolean) This determines whether to fill the circle with the chosen color
	 */
	public Circle(Point centre, int radius, Color color, int thickness,boolean isFill){
		super(color, thickness,isFill,false);
		this.centre = centre;
		this.radius = radius;
	}

	
	/**
	 * 
	 * @return (a Point) The center of the circle.
	 */
	public Point getCentre() {
		return centre;
	}

	
	/**
	 * This method sets the center to a given point
	 * 
	 * @param centre
	 * 			(a Point) which is the center of this circle.
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}

	
	/**
	 * 
	 * @return (integer) the radius of the circle
	 */
	public int getRadius() {
		return radius;
	}

	
	/**
	 * This method sets a new radius to the circle
	 * 
	 * @param radius 
	 * 			(an integer) a new radius for the circle
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void paint(Graphics2D g2d) {
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		
		int diameter = radius * 2;
		
		//The starting point of the oval related to the circle is at the top left corner
		
		if (isFill){
			g2d.fillOval(this.centre.getX() - radius, this.centre.getY() - radius, diameter, diameter);	
		}
		
		else{
			g2d.drawOval(this.centre.getX() - radius, this.centre.getY() - radius, diameter, diameter);
		}
		
		if(this.hasBorder)
		{
			g2d.setStroke(new BasicStroke(1));
			g2d.setColor(Color.ORANGE);
			g2d.drawOval(this.centre.getX() - radius, this.centre.getY() - radius, diameter, diameter);
		}
		
	}
	
	@Override
	public boolean contains(Point p)
	{
		if (this.isFill){
			double pointDistance = Math.sqrt(Math.pow(this.centre.getX() - p.getX(),2) + Math.pow(this.centre.getY() - p.getY(),2));
			return pointDistance <= this.radius + this.thickness;
		}
		
		else{
			double pointDistance = Math.sqrt(Math.pow(this.centre.getX() - p.getX(),2) + Math.pow(this.centre.getY() - p.getY(),2));
			return pointDistance >= (this.radius - this.thickness - 3) && pointDistance <= (this.radius + this.thickness + 3);
		}
	}


	@Override
	public void move(int x_moved, int y_moved) 
	{
		int newX = this.getCentre().getX() - x_moved;
		int newY = this.getCentre().getY() - y_moved;
		
		this.setCentre(new Point(newX , newY));
	}


	@Override
	public Shape copy() 
	{
		return new Circle(this.centre,this.radius,this.color,this.thickness,this.isFill);
	}
}
