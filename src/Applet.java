import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.util.Stack;

/**
 * @author Dan Verständig
 *
 */
public class Applet extends JApplet {

	private static final long serialVersionUID = 1L;

	public Applet() {
		vp = new Viewport(400, 150);
		vp.setForeground(Color.WHITE);
		vp.setBounds(10, 10, 400, 150);
		vp.setBorder(null);
		setFocusable(true);
		mp = new JPanel();
		panel = new JPanel();
		try {
			initUI();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init() {
		vp.drawFrame();
		vp.drawGrid();
		showCoords = false;
		msg = vp.getStack();
		tlog.setText("");
	}

	private void initUI() throws Exception {

		getContentPane().add(mp);
		mp.setMinimumSize(new Dimension(600, 500));
		mp.setPreferredSize(new Dimension(600, 500));
		mp.setSize(new Dimension(600, 500));
		mp.setBackground(new Color(255, 255, 255));
		mp.setFont(new Font("Tahoma", 0, 10));

		vp.setLayout(null);
		vp.addMouseListener(new MyMouseAdapter(this));
		vp.addKeyListener(new MyKeyAdapter(this));
		mp.setLayout(null);
		mp.add(vp);

		JCheckBox chckbxShowCoordinates = new JCheckBox("show coordinates");
		chckbxShowCoordinates.setBounds(10, 173, 150, 23);
		chckbxShowCoordinates.setBackground(Color.WHITE);
		mp.add(chckbxShowCoordinates);
		chckbxShowCoordinates.addActionListener(new MyActionAdapter(this));

		JCheckBox chckbxNewCheckBox = new JCheckBox("show grid");
		chckbxNewCheckBox.setBounds(10, 199, 150, 23);
		chckbxNewCheckBox.setBackground(Color.WHITE);
		mp.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new MyActionAdapter(this));

		JButton btnResetField = new JButton("reset");
		btnResetField.setBounds(10, 229, 89, 23);
		mp.add(btnResetField);
		btnResetField.addActionListener(new MyActionAdapter(this));

		JPanel panel = new JPanel();
		panel.setBounds(160, 170, 250, 119);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new LineBorder(
				new Color(192, 192, 192), 1, true), "Console",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		mp.add(panel);
		panel.setLayout(null);
		tlog = new JTextArea();
		tlog.setBounds(10, 20, 230, 80);
		tlog.setBackground(new Color(225, 240, 240));
		tlog.setOpaque(false);
		tlog.setFont(new Font("Times New Roman", 0, 12));
		tlog.setLineWrap(true);

		jsp = new JScrollPane(tlog);
		jsp.setBounds(10, 17, 230, 91);
		panel.add(jsp);
		jsp.setPreferredSize(new Dimension(220, 210));
		jsp.setFont(new Font("Tahoma", 0, 10));
		jsp.setBackground(new Color(255, 255, 255));
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(31);

	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() > 1) {
			vp.setCalc(true);
			vp.drawPoints();
			msg = vp.getStack();
		} else {
			if (isInGrid(e.getX(), e.getY())) {
				vp.ProceedScan(e.getX(), e.getY());
				msg = vp.getStack();
			}
		}
		tlog.append(msg.pop() + "\n");

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			vp.reset();
		}

	}

	private boolean isInGrid(int x, int y) {
		return x < vp.width && x > 0 && y < vp.height && y > 0;
	}

	public Viewport vp;
	public JPanel mp;
	public JPanel panel;
	protected boolean showCoords;
	public Stack<String> msg;
	public JEditorPane editorPane;
	public JScrollPane jsp;
	public JTextArea tlog;

}