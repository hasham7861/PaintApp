package strategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import paint.PaintModel;
import paint.View;
import shapes.Point;
import shapes.Polyline;
import shapes.Shape;

/**
 * A strategy class that handles the creation and modification of Polyline object.
 *  
 * @author CSC207 Group
 *
 */
public class PolylineStrategy extends SquiggleStrategy {

	private boolean shapeBeingMade = false;
	
	/**Constructs strategy with given View and PaintModel
	 * 
	 * @param view View instance
	 * @param model PaintModel instance.
	 */
	public PolylineStrategy(View view, PaintModel model) {
		super(view,model);

		
	}

	/**
	 * 
	 * Creates a new Polyline with Points at x and y.
	 * 
	 * @param x X coordinate of point.
	 * @param y Y coordinate of point.
	 * @return the Polyline that was created.
	 */
	public Shape createShape(int x, int y) {
		
		Color colorMode = this.view.getColorPanel().getColor();
		int thickness = this.view.getToolPanel().getThickness();
		Boolean isFill = this.view.getToolPanel().getIsFilled();
		
		Polyline poly = new Polyline(colorMode,thickness,isFill);
		
		poly.addPoint(new Point(x,y));

		return poly;
	}

	/**
	 * Modifes the feedback point of the Polyline shape. Used to create the feedback 
	 * for Polyline.
	 * 
	 * @param shape Shape object that has to be a Polyline.
	 * @param point the point that replaces the last point of Polyline.
	 */
	public void setFeedbackPoint(Shape shape, Point point){
		Polyline polyline = (Polyline)shape;
		polyline.setFeedback(point);
		
		this.view.getPaintPanel().update(null,null);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{

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
	 * When mouse is pressed, either creates a new shape,
	 * or adds points (to pointsList) for the currently being made shape. This is done 
	 * based on what button is clicked.
	 */
	public void mousePressed(MouseEvent arg0) {
		
		//End creation of shape on right click
		if (arg0.getButton() == MouseEvent.BUTTON3)
		{
			this.shapeBeingMade = false;
			
			Shape lastShape = this.model.getLastShape();
			this.setFeedbackPoint(lastShape, null);
		}	
		
		else
		{
			if (!this.shapeBeingMade)
			{
				this.model.addShape(this.createShape(arg0.getX(), arg0.getY()));
				
				this.model.clearRedoList();
				this.shapeBeingMade = true;
			}
			
			else
			{
				Shape lastShape = this.model.getLastShape();
				this.modifyShape(lastShape, arg0.getX(), arg0.getY());
			}		
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * When the mouse is moved, this function modifies the last shape so that a temporary feedback is 
	 * displayed for that shape.
	 */
	public void mouseMoved(MouseEvent arg0) {
		
		if (this.shapeBeingMade){
			Shape lastShape = this.model.getLastShape();
			this.setFeedbackPoint(lastShape, new Point(arg0.getX(),arg0.getY()));
		}
	}
}
