package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 * 
 * Polygon, subclass of Shape
 * 
 * A Polygon object that is created by 
 * several interconnected Points.
 * 
 * @author CSC207 Group
 *
 */
public class Polygon extends Polyline  {	

	/**
	 * Constructs a Polygon object
	 * @param color (Color of the polygon object)
	 * @param thickness (The stroke size of the polygon)
	 * @param isFill (used to check whether to draw or fill in shape)
	 */
	public Polygon (Color color, int thickness, boolean isFill)
	{
		super(color, thickness,isFill);

	}

	/**
	 * Return a list of x coordinates taken from the list of points.
	 * 
	 * @return xPoints an array of ints that contains all the x values in the point list.
	 */
	public int[] getXPoints(){
		int[] xPoints = new int[this.points.size() + 1];
		int i=0;
		for(Point point:this.points){
			xPoints[i] = point.getX();
			i++;
		}
		
		if(this.feedbackPoint != null)
		{
			xPoints[i] = this.feedbackPoint.getX();
		}
		
		return xPoints;
	}
	
	/**
	 * Return a list of y coordinates taken from the list of points.
	 * 
	 * @return yPoints an array of ints that contains all the y values in the point list.
	 */
	public int[] getYPoints(){
		int[] yPoints = new int[this.points.size() + 1];
		int i=0;
		for(Point point:this.points){
			yPoints[i] = point.getY();
			i++;
		}
		
		if(this.feedbackPoint != null)
		{
			yPoints[i] = this.feedbackPoint.getY();
		}
		
		return yPoints;
	}
	
	public void paint(Graphics2D g2d) 
	{
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		
		int sides = this.points.size();
		
		if(this.feedbackPoint != null)
		{
			sides++;
		}
		
		if (!isFill){	
			g2d.drawPolygon(getXPoints(), getYPoints(), sides);
		}
		
		else{
			if (this.points.size()<=2){
				g2d.drawPolygon(getXPoints(), getYPoints(), sides);
			}
			else{
				g2d.fillPolygon(getXPoints(), getYPoints(), sides);
			}
		}
		
		if(this.hasBorder)
		{
			g2d.setStroke(new BasicStroke(1));
			g2d.setColor(Color.ORANGE);
			g2d.drawPolygon(getXPoints(), getYPoints(), this.points.size());
		}
	}

	@Override
	public boolean contains(Point p) 
	{
		if(this.isFill)
		{
			/*
			 * This algorithm for dectecting if point is within polygon is used from:
			 * https://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
			 */
			int nvert = this.points.size();
			int[] vertx = this.getXPoints(); 
			int[] verty = this.getYPoints(); 
			float testx = p.getX();
			float testy = p.getY();
			
			int i, j= 0;
			boolean c = false;
			
			for (i = 0, j = nvert-1; i < nvert; j = i++) {
			  if (((verty[i]>testy) != (verty[j]>testy)) &&
				 (testx < (vertx[j]-vertx[i]) * (testy-verty[i]) / (verty[j]-verty[i]) + vertx[i])){
			       c = !c;
			  }
			 
			}
	
			return c;
		}
		
		else
		{
			ArrayList<Point> pointsList = new ArrayList<Point>();
			
			for(Point dot: this.points)
			{
				pointsList.add(new Point(dot.getX(),dot.getY()));
			}
			
			/*For some reason, the line that connects from first point to last point 
			wasn't being detected by algorithm. So,I added those points a second time.*/
			Point first = this.points.get(0);
			Point last = this.points.get(this.points.size() - 1);
			
			
			pointsList.add(first);
			pointsList.add(last);
			
			return this.pointOnLine(p, pointsList);
		}
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
	
	public Shape copy() 
	{	
		Polygon copy = new Polygon(this.color,this.thickness,this.isFill);

		//Have to add the first point twice.
		copy.addPoint(new Point(this.points.get(0).getX(),this.points.get(0).getY()));
		
		for(Point p: this.points)
		{
			copy.addPoint(new Point(p.getX(),p.getY()));
		}
		
		return copy;
		
	}


}
