import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Dan Verständig
 *
 */
public class MyMouseAdapter extends MouseAdapter {

	MyMouseAdapter(Applet adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		adaptee.mouseClicked(e);

	}

	Applet adaptee;
}