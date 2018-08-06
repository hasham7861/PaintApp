package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * A Square of type Shape.
 * 
 * @author CSC207 Group
 *
 */
public class Square extends Shape{
	private Point point;
	private int width;
	private int quadrant;

	/**
	 * Construct a square with a corner point, and a width. 
	 * Set its color, thickness and if the square is filled or not.
	 * 
	 * @param point
	 			(a Point object) consisting of X and Y coordinates. This is the top left corner of the square.
	 * @param width
	 * 			(a int value) the width of the square
	 * @param color
	 * 			(a color object) the color of the the square
	 * @param thickness
	 * 			(a non-negative integer) This determines the thickness of the border of the square
	 * @param isFill
	 * 			(a boolean) determines whether to fill the square with the chosen color
	 */
	public Square(Point point, int width, Color color, int thickness,boolean isFill){
		super(color, thickness, isFill,false);
		this.point = point;
		this.width = width;
	}

	/**
	 * 
	 * @return (A point object) the top left point of the square
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * 
	 * @return (an int) return the width of the square 
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * 
	 * @param nWidth
	 *			(an integer) set the width for the square to the given value
	 */
	public void setWidth(int nWidth) {
		this.width = nWidth;
	}

	/**
	 * 
	 * @param last
	 * 		(a Point object) the point(last) that, relative to this rectangles point, 
	 * 						 dictates what quadrant this rectangle will be built in.
	 */

	public void setQuadrant(Point last){
		//when the Y coordinate from the mouse event from the paintPanel is less than the Y coordinate 
		//of the top left corner of the square
		if (last.getX()-this.point.getX()>0 && last.getY()-this.point.getY()<0)
		{
			this.quadrant=1;	
		}

		//when the X and Y coordinate from the mouse event from the paintPanel is less than the X and Y coordinate 
		//of the top left corner of the square
		else if (last.getX()-this.point.getX()<0 && last.getY()-this.point.getY()<0)
		{
			this.quadrant=2;	
		}

		//when the X coordinate from the mouse event from the paintPanel is less than the X coordinate 
		//of the top left corner of the square
		else if (last.getX()-this.point.getX()<0 && last.getY()-this.point.getY()>0)
		{
			this.quadrant=3;	
		}

		else if (last.getX()-this.point.getX()>0 && last.getY()-this.point.getY()>0)
		{
			this.quadrant=4;	
		}
	}
	
	public void paint(Graphics2D g2d) {
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));

		int x = this.point.getX();
		int y = this.point.getY();

		//when the mouse cursor in the 1st quadrant, then change the y-coor of the top left
		if (quadrant==1){
			y = this.point.getY() - this.getWidth();
		}

		//when the mouse cursor in the 2nd quadrant, then change the x and y-coor of the top right
		else if (quadrant==2){
			x = this.point.getX() - this.getWidth();
			y = this.point.getY() - this.getWidth();
		}

		//when the mouse cursor in the 3rd quadrant, then change the x-coor of the bot left
		else if (quadrant==3){
			x = this.point.getX() - this.getWidth();
		}
		
		//if fill is selected then fill the square
		if (!this.isFill)
		{
			g2d.drawRect(x,y,this.width,this.width);
		}
		
		else
		{
			g2d.fillRect(x,y,this.width,this.width);
		}
		
		if(this.hasBorder)
		{
			g2d.setStroke(new BasicStroke(1));
			g2d.setColor(Color.ORANGE);
			g2d.drawRect(x,y,this.width,this.width);
		}

	}

	@Override
	public boolean contains(Point p){
		
		boolean withinYBoundaries = p.getY() >= this.point.getY() - this.thickness - 3 && 
									p.getY() <= this.point.getY() + this.width + this.thickness + 3;
		
		boolean withinXBoundaries = p.getX() >= this.point.getX() - this.thickness - 3 && 
									p.getX() <= this.point.getX() + this.width + this.thickness + 3;
		
		if (this.isFill){
			if(withinYBoundaries && withinXBoundaries)
			{
				return true;
			}
		}
		else
		{
			//Check if point is ON either right or left lines of rectangle. Also, check if the point is WITHIN 
			//the top and bottom lines of rectangle. If both true, then return true.
			boolean onLeftHeightLine = p.getX() >= this.point.getX() - this.thickness - 3 && 
									   p.getX() <= this.point.getX() + this.thickness + 3;
			
			boolean onRightHeightLine = p.getX() >= this.point.getX() + this.width - this.thickness - 3 && 
									    p.getX() <= this.point.getX() + this.width + this.thickness + 3;
			
			boolean onEitherHeightLine = onLeftHeightLine || onRightHeightLine;
			
			if(onEitherHeightLine && withinYBoundaries){
				return true;
			}
			
			//Check if point is ON either top or bottom lines of rectangle. Also, check if the point is WITHIN 
			//the left and right lines of rectangle. If both true, then return true.
			boolean onTopWidthLine = p.getY() >= this.point.getY() - this.thickness - 3 && 
									 p.getY() <= this.point.getY() + this.thickness + 3;
			
			boolean onBotWidthLine = p.getY() >= this.point.getY() + this.width - this.thickness - 3 && 
									 p.getY() <= this.point.getY() + this.width + this.thickness + 3;
			
			boolean onEitherWidthLine = onTopWidthLine || onBotWidthLine;
			
			if(onEitherWidthLine && withinXBoundaries)
			{
					return true;
			}
		}
		
		return false;
	}
	

	@Override
	public void move(int x_moved, int y_moved) 
	{
		int newX = this.point.getX() - x_moved;
		int newY = this.point.getY() - y_moved;
		
		this.point = new Point(newX, newY);
		
	}
	
	@Override
	public Shape copy() 
	{
		return new Square(this.point,this.width,this.color,this.thickness,this.isFill);
	}
}

