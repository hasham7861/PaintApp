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
		this.setJMenuBar(createMenuBar());
		
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

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Clear");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		return menuBar;
	}

	/**
	 * Handle buttons pressed from the menu bar.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		String command = e.getActionCommand();
		
		if(command == "Clear")
		{
			this.model.getShapes().clear();
			
			this.paintPanel.update(null,null);
		}
		
		if(command == "Exit")
		{
			this.dispose();
		}
		
		if(command == "New")
		{
			int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to close this canvas?","New Canvas?", 
					JOptionPane.YES_NO_OPTION);
		
			switch(choice)
			{
				case 0:
					this.dispose();
					Paint.main(null);
			}
		}
		
		if (this.model.getShapes().size() > 0)
		{
			if(command == "Undo"){
				this.model.undo();
			}
			
			if(command == "Copy" && this.paintPanel.hasShapeSelector())
			{
				this.paintPanel.getShapeSelector().copySelectedShape();
			}
			
			if(command == "Cut" && this.paintPanel.hasShapeSelector())
			{
				this.paintPanel.getShapeSelector().cutSelectedShape();
			}
				
		}
		
		//If a redo shape exists, redo.
		if (command == "Redo" && this.model.getRedoList().size() > 0)
		{
			this.model.redo();
		}
		
		//If a copy shape exists, then paste.
		if(command == "Paste" && this.model.getCopyShape() != null)
		{
			/*The reason we add the copy of copyShape is so that we can 
			Infinitly paste that copied shape if we like. If we just add 
			this.model.getCopyShape() to shape list by itself, it's reference 
			will forever be in shape list so it can only be added once.
			In this case, we create a new reference each time we copy.*/
			this.model.addShape(this.model.getCopyShape().copy());
		}
		
	}
}
