package strategies;

import java.awt.Color;
import java.awt.event.MouseEvent;

import paint.PaintModel;
import paint.View;
import shapes.Point;
import shapes.Polygon;
import shapes.Shape;

/**
 * A strategy class that handles the creation and modification of Polygon (extends Polyline) objects.
 *  
 * @author CSC207 Group
 *
 */
public class PolygonStrategy extends ShapeManipulatorStrategy {
	
	private boolean shapeBeingMade = false;
	
	/**Constructs strategy with given View and PaintModel
	 * 
	 * @param view View instance
	 * @param model PaintModel instance
	 */
	public PolygonStrategy(View view, PaintModel model) {
		super(view, model);
	}

	/**
	 * 
	 * Creates a new polygon using x and y.
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @return the polygon that is created.
	 */
	public Shape createShape(int x, int y) {
		
		Color colorMode = this.view.getColorPanel().getColor();
		int thickness = this.view.getToolPanel().getThickness();
		Boolean isFill = this.view.getToolPanel().getIsFilled();
		
		Polygon polygon = new Polygon(colorMode,thickness,isFill);
		
		polygon.addPoint(new Point(x,y));
		polygon.addPoint(new Point(x,y));

		return polygon;
	}

	/**
	 * Takes in a polygon shape and adds x and y point to it.
	 * 
	 * @param x X coordinate of mouse point.
	 * @param y Y coordinate of mouse point.
	 * 
	 * @param s Shape object that has to be a polygon.
	 */
	public void modifyShape(Shape s,int x, int y) 
	{
		Polygon polygon = (Polygon)s;
		
		polygon.addPoint(new Point(x,y));
		
		this.view.getPaintPanel().update(null,null);
	}
	
	/**
	 * Modifes the feedback point of a polygon shape. Used to create the feedback 
	 * for Polygon.
	 * 
	 * @param shape Shape object that has to be a polygon.
	 * @param point the point that is now the feedback point of polygon.
	 */
	public void setFeedbackPoint(Shape shape, Point point){
		Polygon polygon = (Polygon)shape;
		
		polygon.setFeedback(point);
		
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
		
		if (this.shapeBeingMade)
		{
			Shape lastShape = this.model.getLastShape();
			this.setFeedbackPoint(lastShape, new Point(arg0.getX(),arg0.getY()));
		}

	}
}
