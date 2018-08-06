package paint;

import java.util.ArrayList;
import java.util.Observable;

import shapes.Polyline;
import shapes.Shape;
/***
 * Model which holds all the shapes and points that are created by other classes.
 * Can modify the currently held shapes/points.
 * 
 * @author CSC207 Group
 *
 */
public class PaintModel extends Observable {
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private ArrayList<Shape> redoList = new ArrayList<Shape>();
	
	private Shape copyShape = null;


	/***
	 * Adds an object that extends the abstract class Shape to the model's shape list. 
	 * 
	 * @param s (a Shape) that is to be added to shape list.
	 */
	public void addShape(Shape s){
		this.shapeList.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	
	/***
	 * 
	 * @return the list of shapes currently in model.
	 */
	public ArrayList<Shape> getShapes(){
		return shapeList;
	}
	
	/**
	 * @return the last shape that was added to shape list.
	 */
	public Shape getLastShape(){
		
		return this.shapeList.get(this.shapeList.size() - 1);
		
	}
	
	/**
	 * Removes the last shape from shapeList and adds it to the redoShapeList
	 * Then, notifies the observer that the model has changed.
	 */
	public void redo(){
		Shape redoShape = this.redoList.get(this.redoList.size() - 1);
		this.shapeList.add(redoShape);
		this.redoList.remove(this.redoList.size()-1);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Removes the last shape from redoShapeList and adds it back to the shapesList.
	 * Then, notifies the observer that the model has changed.
	 */
	public void undo(){
		Shape undoShape = this.shapeList.get(this.shapeList.size()-1);
		this.redoList.add(undoShape);
		this.shapeList.remove(this.shapeList.size()-1);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Clears the redoShapeList.
	 */
	public void clearRedoList(){
		this.redoList.clear();
	}

	/**
	 * @return the list of shapes that were undo'd.
	 */
	public ArrayList<Shape> getRedoList() {
		return redoList;
	}
	
	/**
	 * @return the shape that was copied.
	 */
	public Shape getCopyShape() {
		return copyShape;
	}

	/**
	 * @param copyShape the shape that is now copied and stored in mdoel.
	 */
	public void setCopyShape(Shape copyShape) {
		this.copyShape = copyShape;
	}
	
	/**
	 * Reset the border for all Shapes. Make it so they
	 * don't have a border. Then, notify observer.
	 */
	public void resetBorders()
	{
		for(Shape s: this.shapeList)
		{
			s.setHasBorder(false);
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Reset the feedback point for the latest shape, if it is
	 * a poly shape.
	 */
	public void resetFeedback()
	{
		if(this.getLastShape() instanceof Polyline)
		{
			Polyline poly = (Polyline)this.getLastShape();
			
			poly.setFeedback(null);
		}
	}
}
