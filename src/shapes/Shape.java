package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Observable;

/**
 * Abstract class Shape.
 * 
 * @author CSC207 Group
 *
 */
public abstract class Shape implements PaintCommand{
	
	protected Color color;
	protected int thickness;
	protected boolean isFill;
	protected boolean hasBorder;
	
	
	/**
	 * Constructs a Shape object
	 * @param color (Color of the current Shape)
	 * @param thickness (size of the current stroke)
	 * @param isFill ( used to check whether to draw or fill in shape)
	 * @param hasBorder (used to check whether to draw border or not)
	 */
	public Shape(Color color,int thickness, boolean isFill, boolean hasBorder)
	{
		this.color = color;
		this.thickness = thickness;
		this.isFill = isFill;
		this.hasBorder = hasBorder;
	}
	
	
	/**
	 * 
	 * @return the color of this shape.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 
	 * @param color the new color of this shape.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * 
	 * @return the thickness of this shape.
	 */
	public int getThickness() {
		return thickness;
	}

	/**
	 * 
	 * @param thickness the new thickness of this shape.
	 */
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	/**
	 * 
	 * @return if this shape is filled or not.
	 */
	public Boolean getIsFill() {
		return isFill;
	}

	/**
	 * 
	 * @param isFill boolean to say if this shape is filled or not now.
	 */
	public void setIsFill(Boolean isFill) 
	{
		this.isFill = isFill;
	}
	
	/**
	 * 
	 * @return if this shape has a border.
	 */
	public boolean shapeHasBorder() 
	{
		return hasBorder;
	}

	/**
	 * 
	 * @param hasBorder boolean to dictate if shape should 
	 * have border.
	 */
	public void setHasBorder(boolean hasBorder) {
		this.hasBorder = hasBorder;
	}


	/**
	 * If this shape contains the given Point object.
	 * 
	 * @param p Point that is checked to bee in this shape.
	 * 
	 * @return if the Point is in shape or not.
	 */
	public abstract boolean contains(Point p);
	
	/**
	 * Move the current shape by the given parameters.
	 * 
	 * @param x_moved the x value shape moves by.
	 * @param y_moved the y value shape moves by.
	 */
	public abstract void move(int x_moved, int y_moved);
	
	/**
	 * Copy the current shape instance with a new reference.
	 * 
	 * @return the new shape with new reference.
	 */
	public abstract Shape copy();


}
