package paint;

import paint.View;
import java.awt.Color;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * JPanel/ActionListener that uses buttons to set the parameters for shape objects.
 * 
 * @author CSC207 Group
 */

public class ToolPanel extends JPanel implements ActionListener, ChangeListener{
	
	private View view;
	
	private boolean isFill; 
	
	private JButton fillButton;
	private JButton drawButton;
	
	private JSlider thicknessSlider;
	private JLabel thicknessLabel;
	
	private static final int MIN_THICK = 0; // Min thickness of stroke
	private static final int MAX_THICK = 30; // Max thickness of stroke
	
	private int thickValue = 5;// Starting thickness of stroke

	/** 
	 * Constructs  ToolPanel. Creates buttons for fill and slider for thickness.  
	 * 
	 * @param view View instance.
	 */
	public ToolPanel (View view)
	{
		this.isFill = false; 
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		//Fill selector GUI
		fillButton = new JButton ("Fill");
		fillButton.addActionListener(this);
		fillButton.setPreferredSize(new Dimension(100, 50));
		
		add(Box.createHorizontalStrut(20)); //Add space to make GUI look better
		
		this.add(fillButton);
		
		// Fill selector GUI
		drawButton = new JButton ("Draw");
		drawButton.setBackground(Color.orange);
		drawButton.addActionListener(this);
		drawButton.setPreferredSize(new Dimension(100, 50));
		
		add(Box.createHorizontalStrut(20));
		
		this.add(drawButton);
		
		add(Box.createHorizontalStrut(20));
		
		//Line Thickness selector GUI
		thicknessSlider = new JSlider(JSlider.HORIZONTAL,MIN_THICK,MAX_THICK,thickValue);
		thicknessLabel = new JLabel("Thickness:");

		thicknessSlider.setMajorTickSpacing(10);
		thicknessSlider.setMinorTickSpacing(5);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.setPaintLabels(true);
		
		thicknessSlider.addChangeListener(this);
		add(thicknessLabel);
		add(Box.createHorizontalStrut(20));
		add(thicknessSlider);
		
		add(Box.createHorizontalStrut(60));
		
		this.view = view;
	}
	
	/**
	 * Sets fill attribute based on if you choose fill button or draw button.
	 */
	public void actionPerformed(ActionEvent e) 
	{	
		if (e.getActionCommand() == "Fill"){
			this.isFill = true;
			fillButton.setBackground(Color.orange);
			drawButton.setBackground(null);
		}
		
		if (e.getActionCommand() == "Draw") {
			this.isFill = false; 
			fillButton.setBackground(null);
			drawButton.setBackground(Color.orange);
		}
		
		if(this.view.getPaintPanel().hasShapeSelector())
		{
			this.view.getPaintPanel().getShapeSelector().setShapeFill(this.isFill);
		}
	}

	/**
	 * Listener that checks for changes to JSlider
	 * to change the corresponding thickness attribute.
	 */
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider)e.getSource();
		
		if(!slider.getValueIsAdjusting())
		{
			this.thickValue = slider.getValue();
		}		
		
		if(this.view.getPaintPanel().hasShapeSelector())
		{
			this.view.getPaintPanel().getShapeSelector().setShapeThickness(this.thickValue);
		}
	}
	
	/**
	 * @return the String that dictates line thickness.
	 */
	public int getThickness(){
		return this.thickValue;
	}
	
	/**
	 * @return boolean that dictates if shapes are filled or not.
	 */
	public boolean getIsFilled(){
		return this.isFill;
	}

}
