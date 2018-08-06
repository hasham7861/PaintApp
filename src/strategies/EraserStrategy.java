package strategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import paint.PaintModel;
import paint.View;
import shapes.Eraser;
import shapes.Point;
import shapes.Shape;
/**
 * A strategy class that handles the creation and modification of Squiggle objects that act as erasers.
 *  
 * @author CSC207 Group
 *
 */
public class EraserStrategy extends ShapeManipulatorStrategy {

	
	/**Constructs strategy with given View and PaintModel
	 * 
	 * @param view View instance
	 * @param model PaintModel instance.
	 */
	public EraserStrategy(View view, PaintModel model) {
		super(view, model);
	}

	/**
	 * Creates a white eraser shape at the x and y.
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @return the new eraser shape.
	 */
	public Shape createShape(int x, int y) {
		
		int thickness = this.view.getToolPanel().getThickness();
		Boolean isFill = this.view.getToolPanel().getIsFilled();
		
		Eraser eraser = new Eraser(thickness,isFill);
		eraser.addPoint(new Point(x,y));
		return eraser;
	}

	/**
	 * Take in a eraser and add the x and y point to it.
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @param s Shape that has to be eraser instance.
	 */
	public void modifyShape(Shape s,int x, int y) 
	{
		Eraser eraser = (Eraser)s;
		
		eraser.addPoint(new Point(x,y));

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
