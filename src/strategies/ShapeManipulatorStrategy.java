package strategies;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;

import paint.PaintModel;
import paint.View;
import shapes.Shape;

/**
 * Abstract class that every Shape Strategy class extends. Has 
 * creation and modification methods for Shape Strategy classes 
 * to implement
 * 
 * @author CSC207 Group
 *
 */
public abstract class ShapeManipulatorStrategy implements MouseListener, MouseMotionListener
{
	View view;
	PaintModel model;

	public ShapeManipulatorStrategy(View view, PaintModel model)
	{
		this.model = model;
		this.view = view;
	}
	
	public abstract Shape createShape(int x, int y);
	
	public abstract void modifyShape(Shape lastShape, int x, int y);

}
