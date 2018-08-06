package paint;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
/***
 * A JPanel Actionlistener that shows buttons and changes it's color attribute 
 * based on the buttons clicked.
 * 
 * 
 * @author CSC207 Group
 *
 */

public class ColorPanel extends JPanel implements ActionListener{
	
	View view;
	private Color colorMode; 
	
	Color[] buttonColours= {Color.BLACK,Color.DARK_GRAY,Color.GRAY,Color.LIGHT_GRAY,
			Color.BLUE,Color.GREEN,Color.YELLOW,Color.RED};
	/**
	 * Construct a JPanel with buttons for each of the Color objects.
	 * 
	 * @param view View instance.
	 */
	public ColorPanel (View view)
	{
		colorMode = Color.BLACK; 
		
		this.setLayout(new GridLayout());
		
		for (Color color: buttonColours){
			JButton button = new JButton();
			button.setSize(30, 40);
			button.setBackground(color);
			button.addActionListener(this);
			button.setOpaque(true);
			this.add(button);
			
		}
		
		this.view = view;
	}

	/***
	 * This method changes Color attribute based on the button that's clicked.
	 * 
	 */
	public void actionPerformed(ActionEvent e) 
	{
		JButton buttonClicked = (JButton)e.getSource();

		this.colorMode = buttonClicked.getBackground();
		
		for(Component c : this.getComponents())
		{
			JButton b = (JButton)c;
			
			b.setBorder(UIManager.getBorder("Button.border"));
		}

		buttonClicked.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2),Color.ORANGE));

		
		if(this.view.getPaintPanel().hasShapeSelector())
		{
			this.view.getPaintPanel().getShapeSelector().setShapeColor(this.colorMode);
		}
	}
	
	/**
	 * This method returns the current color selected by panel buttons.
	 * 
	 * @return the current color mode.
	 */
	public Color getColor(){
		return this.colorMode;
	}


}
