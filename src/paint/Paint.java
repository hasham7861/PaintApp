package paint;

/*
 * Instructions for the more difficult shapes;
 * 
 * For Polyline you will click the point that you want a line to be drawn.
 * To stop drawing a polyline right click.
 * 
 * For Polygon you will click the points that you want it to make the new polygon.
 * To stop drawing a polygon right click.
 * 
 * To use the selector to move shapes, first double click the border of a shape that is
 * not solid, or if it is solid you can double click anywhere on the shape.
 * The shape will be highlighted yellow where you can then click the shape and drag it.
 * To stop dragging a shape right click.
 * 
 * To use the eraser click it and then draw on top of the design that you want to erase.
 * 
 * To undo/redo a change go to the edit tab at the top and click undo/redo.
 * 
 * Copy/Paste/Cut is also done to shapes that are selected, not just the latest shape.
 *
 */

/***
 * Class that invokes runnable and initalizes model and view.
 * 
 * @author CSC207 Group
 *
 */
public class Paint {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(Paint::new);
	}

	PaintModel model; // Model
	View view; // View+Controller

	/***
	 * Constructs a new Paint application with instances for model and view.
	 */
	public Paint() {
		// Create MVC components and hook them together

		// Model
		this.model = new PaintModel();

		// View+Controller
		this.view = new View(model);
		
	}
}
