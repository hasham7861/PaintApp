package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Polyline extends Squiggle 
{
	protected Point feedbackPoint;
	
	public Polyline(Color color, int thickness, boolean isFill) {
		super(color, thickness, isFill);
	}
	
	/**
	 * 
	 * @param point the new feedback point.
	 */
	public void setFeedback(Point point){
		this.feedbackPoint = point;;
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
		
		//If there's a feedback point, make a line from last point to feedback point.
		if(this.feedbackPoint != null)
		{
			Point p1 = points.get(points.size() - 1);
			
			g2d.setColor(this.color);
			g2d.setStroke(new BasicStroke(this.thickness));
			
			g2d.drawLine(p1.getX(), p1.getY(), this.feedbackPoint.getX(), this.feedbackPoint.getY());
			
			if(this.hasBorder)
			{
				g2d.setStroke(new BasicStroke(1));
				g2d.setColor(Color.ORANGE);
				g2d.drawLine(p1.getX(), p1.getY(), this.feedbackPoint.getX(), this.feedbackPoint.getY());
			}
		}
	
	}
	
	/**
	 * Checks if the given Point object is within a threshold to any of the 
	 * lines of this Polyline.
	 * 
	 * @param click the point that is checked to see if it is within lines.
	 * 
	 * @param list the slist of points that make up the lines.
	 * 
	 * @return if the Point is within any line of this shape or not.
	 */
	public boolean pointOnLine(Point click, ArrayList<Point> list)
	{
		for(int i = 0; i < list.size() - 2; i++)
		{
			Point p1 = list.get(i);
			Point p2 = list.get(i+1);
			
			/*
			 * Algorithm for finding distance between click and p1 and p2 is used from:
			 * http://stackoverflow.com/questions/15616405/how-to-check-if-point-is-on-a-diagonal-line
			 * 
			 */
			double x1 = p1.getX();
			double x2 = p2.getX();
			
			double y1 = p1.getY();
			double y2 = p2.getY();
			
			double px = click.getX();
			double py = click.getY();
			
			double pd2 = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
			  
			double x, y;
			
			if (pd2 == 0)
			{
				// Points are coincident.
				x = x1;
				y = y2;
			}
			else
			{
				double u = ((px - x1) * (x2 - x1) + (py - y1) * (y2 - y1)) / pd2;
				x = x1 + u * (x2 - x1);
				y = y1 + u * (y2 - y1);
			}
				 
			double distance = Math.sqrt((x - px) * (x - px) + (y - py) * (y - py));
			
			//We have checked if the distance is similar, now we have to check if the click within bounds 
			//of the line segment.
			
			//The bounds in the case that p1 coordinate is less than p2 coordinate.
			boolean withinXBoundsP1 = p1.getX() <= p2.getX() && click.getX() >= p1.getX() - 3 - this.thickness && 
									  click.getX() <= p2.getX() + 3 + this.thickness;
			
			boolean withinYBoundsP1 = p1.getY() <= p2.getY() && click.getY() >= p1.getY() - 3 - this.thickness && 
					 			      click.getY() <= p2.getY() + 3 + this.thickness;
			
			//The bounds in the case that p1 coordinate is more than p2 coordinate.
			boolean withinXBoundsP2 = p1.getX() >= p2.getX() && click.getX() >= p2.getX() - 3 - this.thickness && 
					 			      click.getX() <= p1.getX() + 3 + this.thickness;
				
			boolean withinYBoundsP2 = p1.getY() >= p2.getY() && click.getY() >= p2.getY() - 3 - this.thickness && 
									  click.getY() <= p1.getY() + 3 + this.thickness;

			//If distance is around 0, and the click is within x and y bounds, then return true. 
			if((withinXBoundsP1 || withinXBoundsP2) && (withinYBoundsP1 || withinYBoundsP2) && (distance <= 6))
			{
				return true;
			}		
		}
		
		return false;
		
	}
	
	public boolean contains(Point p)
	{	
		ArrayList<Point> pointsList = new ArrayList<Point>();
		
		for(Point dot: this.points)
		{
			pointsList.add(new Point(dot.getX(),dot.getY()));
		}
		
		/*For some reason, the last line wasn't being detected by algorithm. So,
		I add the 2 last points a second time.*/
		Point secondLast = this.points.get(this.points.size() - 2);
		Point last = this.points.get(this.points.size() - 1);
		
		pointsList.add(secondLast);
		pointsList.add(last);
		
		return this.pointOnLine(p,pointsList);
	}
	
	public Shape copy() 
	{	
		Polyline copy = new Polyline(this.color,this.thickness,this.isFill);

		//Have to add the first point twice.
		copy.addPoint(new Point(this.points.get(0).getX(),this.points.get(0).getY()));
		
		for(Point p: this.points)
		{
			copy.addPoint(new Point(p.getX(),p.getY()));
		}
		
		return copy;
		
	}

}
