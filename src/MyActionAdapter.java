import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

/**
 * @author Dan Verständig
 *
 */
class MyActionAdapter implements ActionListener {

	MyActionAdapter(Applet adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "reset") {
			adaptee.vp.reset();
			adaptee.init();
		}else{
			AbstractButton abstractButton = (AbstractButton) e.getSource();
			boolean selected = abstractButton.getModel().isSelected();	
			if(e.getActionCommand() =="show coordinates"){
				adaptee.vp.setCoords(abstractButton.getModel().isSelected());
			}else if(e.getActionCommand() =="show grid"){
				adaptee.vp.setGrid(selected);
			}
		}
	    
	}

	private Applet adaptee;
}
