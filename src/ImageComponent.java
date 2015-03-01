
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * @author Dan Verständig
 *
 */
public class ImageComponent extends JPanel {

	private static final long serialVersionUID = 1L;

	public ImageComponent() {
		img = new BufferedImage(1, 1, 1);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 1, 1, this);
	}

	public void setImage(BufferedImage image) {
		img = image;
		setSize(image.getWidth(), image.getHeight());
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(img.getWidth(this), img.getHeight(this));
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(img.getWidth(this), img.getHeight(this));
	}

	public BufferedImage img;
}
