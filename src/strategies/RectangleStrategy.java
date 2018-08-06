package strategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import paint.PaintModel;
import paint.View;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

/**
 * A strategy class that handles the creation and modification of Rectangle objects.
 *  
 * @author CSC207 Group
 *
 */
public class RectangleStrategy extends ShapeManipulatorStrategy {

	/**Constructs strategy with given View and PaintModel
	 * 
	 * @param view View instance
	 * @param model PaintModel instance.
	 */
	public RectangleStrategy(View view, PaintModel model) {
		super(view, model);
	}

	/**
	 * Creates a rectangle shape with 0 width/height at the x and y. 
	 * 
	 @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @return the new Rectangle shape.
	 */
	public Shape createShape(int x, int y) {
		
		Color colorMode = this.view.getColorPanel().getColor();
		int thickness = this.view.getToolPanel().getThickness();
		Boolean isFill = this.view.getToolPanel().getIsFilled();
		
		
		Rectangle rectangle = new Rectangle(new Point(x,y),0,0,colorMode,thickness,isFill);
		
		return rectangle;
	}

	/**
	 * Take in a Rectangle shape and modifies it's width and height.
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @param s Shape that has to be Rectangle.
	 */
	public void modifyShape(Shape s,int x, int y) 
	{
		Rectangle rectangle = (Rectangle)s;
		
		int width = x - rectangle.getPoint().getX();
		int height = y - rectangle.getPoint().getY();
		
		rectangle.setHeight(height);
		rectangle.setWidth(width);
		
		this.view.getPaintPanel().update(null,null);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Add shape when mouse pressed.
	 */
	public void mousePressed(MouseEvent arg0) {
		this.model.addShape(this.createShape(arg0.getX(),arg0.getY()));
		this.model.clearRedoList();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Modify last shape when mouse dragged.
	 */
	public void mouseDragged(MouseEvent e) {
		Shape lastShape = this.model.getLastShape();
		
		this.modifyShape(lastShape,e.getX(),e.getY());
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
