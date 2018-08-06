package strategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import paint.PaintModel;
import paint.View;
import shapes.Point;
import shapes.Shape;
import shapes.Square;

/**
 * A strategy class that handles the creation and modification of Square objects.
 *  
 * @author CSC207 Group
 *
 */
public class SquareStrategy extends ShapeManipulatorStrategy {

	
	/**Constructs strategy with given View and PaintModel
	 * 
	 * @param view View instance
	 * @param model PaintModel instance
	 */
	public SquareStrategy(View view, PaintModel model) {
		super(view, model);
	}

	/**
	 * Creates a square shape with 0 width at the x and y. 
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point
	 * 
	 * @return the new Square shape.
	 */
	public Shape createShape(int x, int y) {
		Color colorMode = this.view.getColorPanel().getColor();
		int thickness = this.view.getToolPanel().getThickness();
		Boolean isFill = this.view.getToolPanel().getIsFilled();
		
		Square square = new Square (new Point(x,y),0,colorMode,thickness,isFill);

		return square;
	}

	/**
	 * Take in a Square shape and modify it's width and quadrant.
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @param s Shape that has to be Square.
	 * 
	 */
	public void modifyShape(Shape s,int x, int y) 
	{
		Square square = (Square)s;
		
		int width1 = Math.max(square.getPoint().getX() - x, x - square.getPoint().getX());
		
		int width2 = Math.max(square.getPoint().getY() - y, y - square.getPoint().getY());
		
		int width = Math.max(width1,width2);
		
		square.setWidth(width);
		
		square.setQuadrant(new Point(x,y));
		
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
