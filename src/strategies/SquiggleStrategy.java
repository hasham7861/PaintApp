package strategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import paint.PaintModel;
import paint.View;
import shapes.Point;
import shapes.Shape;
import shapes.Squiggle;

/**
 * A strategy class that handles the creation and modification of Squiggle objects.
 *  
 * @author CSC207 Group
 *
 */
public class SquiggleStrategy extends ShapeManipulatorStrategy {

	
	/**Constructs strategy with given View and PaintModel
	 * 
	 * @param view View instance
	 * @param model PaintModel instance
	 */
	public SquiggleStrategy(View view, PaintModel model) {
		super(view, model);
	}

	/**
	 * Creates a squiggle shape with a point at x and y. 
	 * 
	 * @param x X coordinate of mouse point.

	 * @param y Y coordinate of mouse point
	 * 
	 * @return the new Squiggle shape.
	 */
	public Shape createShape(int x, int y) {
		
		Color colorMode = this.view.getColorPanel().getColor();
		int thickness = this.view.getToolPanel().getThickness();
		Boolean isFill = this.view.getToolPanel().getIsFilled();
		
		Squiggle squiggle = new Squiggle(colorMode,thickness,isFill);
		
		squiggle.addPoint(new Point(x,y));

		return squiggle;
	}

	/**
	 * Take in a Squiggle shape and adds Point with x and y to it.
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @param s Shape that has to be Squiggle.
	 */
	public void modifyShape(Shape s,int x, int y) 
	{
		Squiggle squiggle = (Squiggle)s;
		
		squiggle.addPoint(new Point(x,y));
		
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
	 * Creates new shape when mouse pressed.
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
	 * Modifies last shape when mouse dragged.
	 */
	public void mouseDragged(MouseEvent e) 
	{
		Shape lastShape = this.model.getLastShape();
		
		this.modifyShape(lastShape,e.getX(),e.getY());
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
