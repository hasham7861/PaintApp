package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 * Squiggle, subclass of Shape
 * An object made up of connected dots. Draws lines 
 * based on it's current list of points.
 * 
 * @author CSC207 Group
 *
 */
public class Squiggle extends Shape  {

	protected ArrayList<Point> points = new ArrayList<Point>(); 
	
	/**
	 * Constructs Squiggle object
	 * @param color (Color of the squiggle object)
	 * @param thickness (The stroke size of the squiggle)
	 * @param isFill ( used to check whether to draw or fill in shape)
	 */
	public Squiggle (Color color, int thickness, boolean isFill)
	{
		super(color, thickness,isFill, false);

	}

	
	/**
	 * Adds points to the current instance of squiggle.
	 * 
	 * @param p the new Point to be added to list.
	 */
	public void addPoint(Point p)
	{
		this.points.add(p);
	}

	/**
	 * 
	 * @return ArrayList of points that make up this Squiggle.
	 */
	public ArrayList<Point> getPoints() {
		return this.points;
	}	
	
	
	public void paint(Graphics2D g2d) 
	{	
		for(int i=0;i < points.size()- 1; i++){
			g2d.setColor(this.color);
			g2d.setStroke(new BasicStroke(this.thickness));
			
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			
			if(this.hasBorder)
			{
				g2d.setStroke(new BasicStroke(1));
				g2d.setColor(Color.ORANGE);
				g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			}
		}
	
	}

	@Override
	public boolean contains(Point p) 
	{
		for(Point dot: this.points)
		{
			boolean withinXBound = p.getX() >= dot.getX() - this.thickness - 3 && p.getX() <= dot.getX() + this.thickness + 3;
			
			boolean withinYBound = p.getY() >= dot.getY() - this.thickness - 3 && p.getY() <= dot.getY() + this.thickness + 3;
			
			if(withinXBound && withinYBound)
			{
				return true;
			}	
		}
		
		return false;
	}

	@Override
	public void move(int x_moved, int y_moved) 
	{	
		ArrayList<Point> newPoints = new ArrayList<Point>();
		
		for(Point p: this.points)
		{
			int newX = p.getX() - x_moved;
			int newY = p.getY() - y_moved;
			newPoints.add(new Point(newX,newY));
		}
		
		this.points = newPoints;
	}
	
	@Override
	public Shape copy() 
	{	
		Squiggle copy = new Squiggle(this.color,this.thickness,this.isFill);
		
		for(Point p: this.points)
		{
			copy.addPoint(new Point(p.getX(),p.getY()));
		}
		
		return copy;
		
	}

}
