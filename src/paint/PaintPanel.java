package paint;

import paint.PaintModel;
import javax.swing.*;  

import shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * This class extends JPanel and creates a panel that you draw on, it also reads users mouse movements
 * and mouse inputs.
 * 
 * @author CSC 207
 *
 */
public class PaintPanel extends JPanel implements Observer {
	private int i=0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	
	/**
	 * Constructor for the JPanel
	 * 
	 * @param model PaintModel instance
	 */
	public PaintPanel(PaintModel model){
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300,300));
		
		this.model = model;
		this.model.addObserver(this);
	}

	/**
	 * This method paints all the shapes that the user has drawn
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
		
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// setBackground (Color.blue); 
		// Origin is at the top left of the window 50 over, 75 down
		g2d.setColor(Color.white);
        g2d.drawString ("i="+i, 50, 75);
		//i=i+1;
		
		// This is a List of all the shapes that were drawn
        // and is located in model
		ArrayList<Shape> shapes = this.model.getShapes();	
		
		for(Shape s: shapes)
		{
			s.paint(g2d);	
			
		}

		g2d.dispose();
	}

	/***
	 * Repaints the panel when it's observable objects(PaintModel) change.
	 */
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}

	/**
	 * Removes all MouseListeners/MouseMotionListeners from this class.
	 */
	public void removeListeners()
	{
		for(MouseListener l : this.getMouseListeners()) 
		{
			this.removeMouseListener(l);
	        
	    }
		
		for(MouseMotionListener l2 : this.getMouseMotionListeners()) 
		{
			this.removeMouseMotionListener(l2);
			
	    }
	}
	
	/**
	 * @return the shape selector listener that is attached to 
	 * this JPanel (if there is any).
	 */
	public ShapeSelector getShapeSelector()
	{
		for(MouseListener l : this.getMouseListeners()) 
		{
			if(l instanceof ShapeSelector)
			{
				return (ShapeSelector) l;
			}
	        
	    }
		
		return null;
	}
	
	/**
	 * @return if or if not this JPanel has a ShapeSelector instance as 
	 * a mouselistener.
	 */
	public boolean hasShapeSelector()
	{
		return this.getShapeSelector() != null;
	}
}
