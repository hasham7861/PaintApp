package strategies;

import paint.ShapeSelector;
import paint.View;

/***
 * 
 * A factory pattern that creates Shape Strategy classes based on given parameters.
 * 
 * @author CSC207 Group
 *
 */
public class ShapeFactory
{
	View view;
	
	/**
	 * Construct a factory with the View instance.
	 * @param view instance of View.
	 */
	public ShapeFactory(View view)
	{
		this.view = view;
	}

	/**
	 * Creates a strategy instance based on mode and makes it the new listener(s) to 
	 * PaintPanel.
	 * 
	 * @param mode the String that dictates which strategy will be created
	 * Creates a strategy instance (or ShapeSelector) based on mode and makes it the new listener(s) to 
	 * PaintPanel.
	 */
	public void setStrategy(String mode)
	{

		ShapeManipulatorStrategy strategy = null;
		
		//Set the strategy based on mode
		if (mode == "Squiggle")
		{
			strategy = new SquiggleStrategy(this.view,this.view.getPaintModel());
		}
		
		if (mode == "Circle")
		{
			strategy = new CircleStrategy(this.view,this.view.getPaintModel());
		}
		
		if (mode == "Square")
		{
			strategy = new SquareStrategy(this.view,this.view.getPaintModel());
		}
		
		if (mode == "Rectangle")
		{
			strategy = new RectangleStrategy(this.view,this.view.getPaintModel());
		}
		
		if (mode == "Polygon")
		{
			strategy = new PolygonStrategy(this.view,this.view.getPaintModel());
		}
		
		if (mode == "Eraser")
		{
			strategy = new EraserStrategy(this.view,this.view.getPaintModel());
		}
		
		if(mode == "Polyline")
		{
			strategy = new PolylineStrategy(this.view,this.view.getPaintModel());
		}
		
		//If mode is not for a shape strategy, and it is for ShapeSelector, then create selector not a strategy.
		if(mode == "Selector")
		{
			ShapeSelector selector = new ShapeSelector(this.view,this.view.getPaintModel());
			
			this.view.getPaintPanel().removeListeners();
			
			this.view.getPaintPanel().addMouseListener(selector);
			this.view.getPaintPanel().addMouseMotionListener(selector);
			
		}
		
		else
		{
			this.view.getPaintPanel().removeListeners();
			
			this.view.getPaintPanel().addMouseListener(strategy);
			this.view.getPaintPanel().addMouseMotionListener(strategy);	
		}
	}


}
