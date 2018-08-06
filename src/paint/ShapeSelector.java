package paint;

import paint.View;
import paint.PaintModel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;

import shapes.Eraser;
import shapes.Point;
import shapes.Shape;

/**
 * MouseListener/MouseMotionListener that selects shapes and manipulates selected shapes. 
 * 
 * @author CSC207 Group
 *
 */
public class ShapeSelector implements MouseListener, MouseMotionListener 
{

	private View view;
	private PaintModel model;
	
	private Shape selectedShape;
	private Point pivotClick;
	
	private ArrayList<Shape> erasersOnTopOfShape = new ArrayList<Shape>();
	
	private boolean dragged = false;
	
	/**
	 * Constructs a Shape Selector listener.
	 * 
	 * @param view View instance.
	 * @param model PaintModel instance.
	 */
	public ShapeSelector(View view, PaintModel model)
	{
		this.view = view;
		this.model = model;
	}

	/**
	 * Checks to see which eraser shapes are on top of the given shape and 
	 * adds them to the arraylist of erasers. 
	 * 
	 * @param s the shape which the erasers are checked to be on top of. 
	 */
	public void getErasers(Shape s)
	{
		for (Shape e: this.model.getShapes())
		{
			if (e instanceof Eraser)
			{
				if (((Eraser) e).isOnTopOf(s))
				{
					this.erasersOnTopOfShape.add(e);
				}
			}
		}
	}
	
	/**
	 * Moves the eraser objects by given parameters.
	 * 
	 * @param erasers the arraylist of eraser shapes that are to be moved.
	 * 
	 * @param x_moved the x value by which shapes move.
	 * 
	 * @param y_moved the y value by which shapes move.
	 */
	public void moveErasers(ArrayList<Shape> erasers, int x_moved, int y_moved)
	{
		for (Shape e: erasers)
		{
			e.move(x_moved, y_moved);
		}
	}
	
	/**
	 * @param color the new Color for the selected shape.
	 */
	public void setShapeColor(Color color)
	{
		if(this.selectedShape != null)
		{
			this.selectedShape.setColor(color);
			
			this.view.getPaintPanel().update(null,null);
		}
		
	}
	
	/**
	 * @param thickness the new thickness for the selected shape.
	 */
	public void setShapeThickness(int thickness)
	{
		if(this.selectedShape != null)
		{
			this.selectedShape.setThickness(thickness);
			
			this.view.getPaintPanel().update(null,null);
		}
		
	}
	
	/**
	 * @param fill determines isFill for the selected shape.
	 */
	public void setShapeFill(boolean fill)
	{
		if(this.selectedShape != null)
		{
			this.selectedShape.setIsFill(fill);
			
			this.view.getPaintPanel().update(null,null);
		}
		
	}
	
	/**
	 * Copy the selected shape to the model.
	 */
	public void copySelectedShape()
	{
		if(this.selectedShape != null)
		{
			Shape copy = this.selectedShape.copy();
			copy.move(10,10);
			this.model.setCopyShape(copy);
		}
	}
	
	/**
	 * Copy the selected shape to the model and remove the shape from 
	 * shape list.
	 */
	public void cutSelectedShape()
	{
		if(this.selectedShape != null)
		{
			this.copySelectedShape();
			this.model.getShapes().remove(this.selectedShape);
			
			this.view.getPaintPanel().update(null,null);
		}
	}
	
	/**
	 * Move shape when the mouse is dragged, assuming a shape is selected and 
	 * clicked on.
	 */
	public void mouseDragged(MouseEvent arg0) 
	{
		if(this.selectedShape != null && this.dragged)
		{
			int x_moved = this.pivotClick.getX() - arg0.getX();
			int y_moved = this.pivotClick.getY() - arg0.getY();
			
			this.selectedShape.move(x_moved, y_moved);
			
			this.pivotClick = new Point(arg0.getX(),arg0.getY());	
			
			//Erasers move with the shape if the eraser is on top of the shape.
			this.moveErasers(this.erasersOnTopOfShape, x_moved,y_moved);
			
			this.view.getPaintPanel().update(null,null);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	 * Detect if a shape is selected by mouse pressed. Deselect a shape
	 * if right clicked.
	 */
	public void mousePressed(MouseEvent arg0) {
		Point click = new Point(arg0.getX(),arg0.getY());
		
		//Shape is selected on double click.
		if(arg0.getClickCount() == 2 && !arg0.isConsumed())
		{
			arg0.consume();

			for(Shape s: this.model.getShapes())
			{
				if(s.contains(click))
				{
					if(this.selectedShape != null)
					{
						//Remove highlight(border) from old selected shape.
						this.selectedShape.setHasBorder(false);
					}
					
					this.erasersOnTopOfShape.clear();
					
					this.selectedShape = s;
					
					this.selectedShape.setHasBorder(true);
				}
			}
			
			this.getErasers(this.selectedShape);
			
			this.view.getPaintPanel().update(null,null);
		}
		
		//But shape can still be moved on single click, 
		//provided it's been selected already.
		if(this.selectedShape != null && this.selectedShape.contains(click))
		{
			this.dragged = true;
			this.pivotClick = click;
		}
		
		//If right click, remove the selected shape.
		if(arg0.getButton() == MouseEvent.BUTTON3 && this.selectedShape != null)
		{
			this.selectedShape.setHasBorder(false);
			
			this.dragged = false;
			this.selectedShape = null;
			this.pivotClick = null;
			
			this.view.getPaintPanel().update(null,null);
		}
		
	}

	/**
	 * Shape is no longer movable until it is clicked on again (the shape is still
	 * selected, just isnt moving until you drag it again).
	 */
	public void mouseReleased(MouseEvent arg0) {
		this.dragged = false;
		this.pivotClick = null;
		
	}

}
