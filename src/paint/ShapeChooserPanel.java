package paint;

import javax.imageio.ImageIO;


import javax.swing.*;

import strategies.ShapeFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * This class creates the buttons to choose which
 * type of shape user wants to draw.
 * 
 * @author CSC207 Group
 *
 */
class ShapeChooserPanel extends JPanel implements ActionListener {
	
	private String mode; 
	
	//Variables for the button icons
	private Image circle,rectangle,square,squiggle,polyline,eraser,polygon,selector;

	private View view;
	
	private ShapeFactory factory;
	
	/***
	 * Constructs a new JPanel with buttons for Shapes.
	 * 
	 * 
	 * @param view View instance
	 */
	public ShapeChooserPanel(View view) {	
		
		// A list of icons for the buttons
		ArrayList<ImageIcon> iconList = this.getIcons();
		
		//Iterator to go through the list of ImageIcons
		Iterator<ImageIcon> iconListIt = iconList.iterator();
		

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline","Polygon","Eraser","Selector"};
		
		this.setLayout(new GridLayout(buttonLabels.length, 1));
		
		//Goes through each type of button and makes the button with the right icon
		for (String label : buttonLabels) {
			JButton button = new JButton();
			
			button.setIcon(iconListIt.next());

		
			this.add(button);
			button.addActionListener(this);
			button.setActionCommand(label);
		}
		
		this.view = view;

		factory = new ShapeFactory(this.view);

	}
	
	/**
	 * Returns a list of ImageIcons that are the images on each button.
	 * 
	 * @return the ArrayList of ImageIcons
	 */
	public ArrayList<ImageIcon> getIcons(){
		ArrayList<ImageIcon> iconArray = new ArrayList<>();
		
		try {
			circle = ImageIO.read(getClass().getResource("/resources/Circle_Icon.png"))
							.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );
			
			rectangle = ImageIO.read(getClass().getResource("/resources/Rectangle_Icon.png"))
							.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );
			
			square = ImageIO.read(getClass().getResource("/resources/Square_Icon.png"))
							.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );
			
			squiggle = ImageIO.read(getClass().getResource("/resources/Squiggle_Icon.png"))
							.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );
			
			polyline = ImageIO.read(getClass().getResource("/resources/Polyline_Icon.png"))
							.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );
			
			eraser = ImageIO.read(getClass().getResource("/resources/Eraser_Icon.png"))
					.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );

			polygon = ImageIO.read(getClass().getResource("/resources/Polygon_Icon.png"))
					.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );
			
			selector = ImageIO.read(getClass().getResource("/resources/Selector_Icon.png"))
					.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH );
			
			//Converts each Image instance to and ImageIcon
			iconArray.add(new ImageIcon(circle));
			iconArray.add(new ImageIcon(rectangle));
			iconArray.add(new ImageIcon(square));
			iconArray.add(new ImageIcon(squiggle));
			iconArray.add(new ImageIcon(polyline));
			iconArray.add(new ImageIcon(polygon));
			iconArray.add(new ImageIcon(eraser));
			iconArray.add(new ImageIcon(selector));
			
			return iconArray;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return iconArray;
	}
	
	/**
	 * When one of the shape buttons is clicked, it changes the current mode. 
	 * Tells factory to create strategy based on the current mode. Resets any 
	 * feedback or borders of the shapes.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//Sets mode to the button command
		this.mode = e.getActionCommand();
			
		if(this.view.getPaintModel().getShapes().size() > 0)
		{
			/*Reset the feedback for Polyline/Polygon in the case that 
			user didn't press right click to complete Polyline/Polygon*/
			this.view.getPaintModel().resetFeedback();
			
			//Reset the highlight given to a shape when it's selected.
			this.view.getPaintModel().resetBorders();
		}
		
		this.factory.setStrategy(this.mode);

		for(Component c: this.getComponents())
		{
			JButton b = (JButton)c;
			
			if(b.getActionCommand() == mode)
			{
				b.setBackground(Color.orange);
			}
			
			else
			{
				c.setBackground(null);
			}
			
		}
	}
	
	/**
	 * 
	 * @return String that dictates the current shape mode
	 */
	public String getMode ()
	{	
		return this.mode;
	}

	
}
