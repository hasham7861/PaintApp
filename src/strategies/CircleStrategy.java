package strategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import paint.PaintModel;
import paint.View;
import shapes.Circle;
import shapes.Point;
import shapes.Shape;

/**
 * A strategy class that handles the creation and modification of Circle objects.
 *  
 * @author CSC207 Group
 *
 */
public class CircleStrategy extends ShapeManipulatorStrategy {
	
	/**Constructs strategy with given View and PaintModel
	 * 
	 * @param view View instance
	 * @param model PaintModel instance.
	 */
	public CircleStrategy(View view, PaintModel model) {
		super(view,model);

	}

	/**
	 * Creates a circle shape with 0 radius at the x and y. 
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @return the new circle shape.
	 */
	public Shape createShape(int x, int y) 
	{
		Color colorMode = this.view.getColorPanel().getColor();
		int thickness = this.view.getToolPanel().getThickness();
		Boolean isFill = this.view.getToolPanel().getIsFilled();
		
		Circle circle = new Circle(new Point(x,y), 0 ,colorMode,thickness,isFill);

		return circle;
	}

	/**
	 * Take in a Circle shape and modifies it's radius.
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @param s Shape that has to be Circle.
	 */
	public void modifyShape(Shape s,int x, int y) 
	{
		Circle circle = (Circle)s;
		
		Double x_square = Math.pow((double) (circle.getCentre().getX() - x),2);
		Double y_square = Math.pow((double) (circle.getCentre().getY() - y),2);
		int radius = (int)Math.sqrt(x_square + y_square);
		
		circle.setRadius(radius);
		
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
