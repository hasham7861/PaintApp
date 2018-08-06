package shapes;

import java.awt.Color;

public class Eraser extends Squiggle 
{

	/**
	 * Construct an Eraser object.
	 * 
	 * @param thickness how thick the eraser stroke will be.
	 * @param isFill if eraser is filled or not.
	 */
	public Eraser(int thickness, boolean isFill) {
		super(Color.WHITE, thickness + 10, isFill);
	}
	
	/**
	 * If a certain click is within an eraser object. 
	 * Returns false because eraser objects can't be moved
	 * by clicking on them.
	 */
	public boolean contains(Point p)
	{
		return false;
	}

	/**
	 * Checks if a given shape is within the boundaries 
	 * of this eraser object.
	 * 
	 * @param s Shape object that is checked to see if it is on this eraser.
	 * 
	 * @return the boolean dictate if this eraser is on the shape or not.
	 */
	public boolean isOnTopOf(Shape s)
	{
		for (Point p: this.points)
		{
			if(s != null)
			{
				if(s.contains(p))
				{
					return true;
				}
			}
			
		}
		
		return false;
	}
}
