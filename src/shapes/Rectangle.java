package shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * This is the Rectangle class that extends the shape.
 * 
 * @author CSC207 Group
 *
 */
public class Rectangle extends Shape {
	
	private Point point;//Starting point
	private int width, height;//Width an height of rectangle
	
	/**
	 * The constructor for a rectangle
	 * @param point Starting point(one of the corners)
	 * @param width Width of the rectangle
	 * @param height Height of the rectangle
	 * @param color Color of the rectangle
	 * @param thickness Thickness of the line that makes rectangle
	 * @param isFill Boolean to check if fill is selected
	 */
	public Rectangle(Point point, int width, int height, Color color,int thickness, boolean isFill){
		super(color, thickness, isFill,false);
		this.point = point;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * 
	 * @return  the Point that the rectangle starts at.
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @return the width of the rectangle.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Sets the width of the Rectangle
	 * 
	 * @param nWidth the new width of the Rectangle.
	 */
	public void setWidth(int nWidth) {
		this.width = nWidth;
	}
	
	/**
	 * @return the height of the Rectangle.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Sets the height of the Rectangle.
	 * 
	 * @param nHeight the new height of the Rectangle.
	 */
	public void setHeight(int nHeight) {
		this.height = nHeight;

	}

	public void paint(Graphics2D g2d) {
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		
		int rect_x = this.point.getX();
		int rect_y = this.point.getY();
		int rect_width = this.width;
		int rect_height = this.height;
		
		//The next few if statements look for the direction that the mouse is from the origin
		// point so that you can draw the rectangle in any direction
		
		
		//If the mouse goes to the left and down from the origin point.
		if (this.getWidth()<0 && this.getHeight()>0){			
			rect_x += this.width;
			
			rect_width = Math.abs(this.width);
		}
		
		//If the mouse goes right and up from the origin point
		else if (this.getWidth()>0 && this.getHeight()<0){
			rect_y += this.height;

			rect_height = Math.abs(this.height);
		}
		
		//If the mouse goes left and up from the origin point.
		else if (this.getWidth()<0 && this.getHeight()<0){
			rect_x += this.width;
			rect_y += this.height;
			rect_width = Math.abs(this.width);
			rect_height = Math.abs(this.height);
		}
		
		//Checks if the user selected a fill or not fill rectangle
		if (!isFill)
		{
			g2d.drawRect(rect_x, rect_y, rect_width, rect_height);	
		}
		
		else 
		{
			g2d.fillRect(rect_x, rect_y, rect_width, rect_height);	
		}
		
		if(this.hasBorder)
		{
			g2d.setStroke(new BasicStroke(1));
			g2d.setColor(Color.ORANGE);
			g2d.drawRect(rect_x, rect_y, rect_width, rect_height);	
		}
		
	}

	@Override
	public boolean contains(Point p) {
		
		boolean withinYBoundaries = p.getY() >= this.point.getY() - this.thickness - 3 && 
									p.getY() <= this.point.getY() + this.height + this.thickness + 3;
		
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
			
			boolean onBotWidthLine = p.getY() >= this.point.getY() + this.height - this.thickness - 3 && 
									 p.getY() <= this.point.getY() + this.height + this.thickness + 3;
			
			boolean onEitherWidthLine = onTopWidthLine || onBotWidthLine;
			
			if(onEitherWidthLine && withinXBoundaries)
			{
					return true;
			}
		}
		
		return false;
	}

	@Override
	public void move(int x_moved, int y_moved) {
		int newX = this.point.getX() - x_moved;
		int newY = this.point.getY() - y_moved;
		
		this.point = new Point(newX, newY);
		
	}
	
	@Override
	public Shape copy() 
	{
		return new Rectangle(this.point,this.width,this.height,this.color,this.thickness,this.isFill);
	}
	
}
		
