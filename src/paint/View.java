package paint;

import paint.PaintModel;
import paint.Paint;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private PaintModel model;
	
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private ColorPanel colorPanel;
	private ToolPanel toolPanel;
	
	/**
	 * Constructs a new View JFrame with shapechooser panel, toolpanel and color panel.
	 * 
	 * @param model PaintModel instance.
	 */
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	
		
		Container c=this.getContentPane();
		// c.add(new JButton("North"),BorderLayout.NORTH);
		// c.add(new JButton("South"),BorderLayout.SOUTH);
		// c.add(new JButton("East"),BorderLayout.EAST);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		c.add(this.shapeChooserPanel,BorderLayout.WEST);
	
		this.model=model;
		
		this.paintPanel = new PaintPanel(model);
		c.add(this.paintPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new GridLayout());
		
		c.add(bottomPanel,BorderLayout.SOUTH);
		
		this.colorPanel = new ColorPanel(this);
		bottomPanel.add(this.colorPanel);
		
		this.toolPanel = new ToolPanel(this);
		bottomPanel.add(this.toolPanel);
		
		this.pack();
		this.setSize(1200,800);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setVisible(true);
	}
	
	/**
	 * 
	 * @return (PaintModel) current instance of model.
	 */
	public PaintModel getPaintModel(){
		return this.model;
	}
	
	/**
	 * 
	 * @return (PaintPanel) current instance of PaintPanel.
	 */
	public PaintPanel getPaintPanel(){
		return paintPanel;
	}
	
	/**
	 * 
	 * @return (ShapeChooserPanel) current instance of ShapeChooserPanel.
	 */
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	
	/**
	 * 
	 * @return (ColorPanel) current instance of ColorPanel.
	 */
	public ColorPanel getColorPanel(){
		return colorPanel;
	}
	
	/**
	 * 
	 * @return (ToolPanel) current instance of ToolPanel.
	 */
	public ToolPanel getToolPanel(){
		return toolPanel;
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
