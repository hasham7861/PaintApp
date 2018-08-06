package shapes;
import java.awt.Graphics2D;

public interface PaintCommand {
	
	/**
	 * 
	 * Paints the current instance of object.
	 * 
	 * @param g2d the Graphics2d object used to paint.
	 */
	public void paint(Graphics2D g2d);

}
