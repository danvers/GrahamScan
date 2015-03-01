import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Dan Verständig
 *
 */
public class Viewport extends ImageComponent {

	private static final long serialVersionUID = 1L;

	public Viewport(int width, int height) {
		this.width = width;
		this.height = height;
		img = new BufferedImage(width, height, 1);
		graphics = img.createGraphics();
		list = new LinkedList<Point>();
		stack = new Stack<String>();
		coords = false;
		calc = false;
		this.ps = 4;
	}

	public void setPointSize(int s) {
		this.ps = s;
	}

	public Stack<String> getStack() {
		return stack;
	}

	public void setCoords(boolean c) {
		this.coords = c;
		this.drawCoords();
		this.drawPoints();
	}

	public void setGrid(boolean c) {
		this.grid = c;
		this.drawFrame();
		this.drawCoords();
		this.drawPoints();
	}

	public boolean getCalc() {
		return calc;
	}

	public void setCalc(boolean bool) {
		calc = bool;
	}

	public void drawFrame() {
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, width, height);
		setImage(img);
	}

	public void drawGrid() {

		if (grid) {
			graphics.setColor(new Color(240, 240, 240));
			for (int x = 0; x < width; x += 10) {
				for (int y = 0; y < height; y += 10) {
					graphics.drawLine(x, 0, x, height);
					graphics.drawLine(0, y, width, y);
				}

			}
		}
		graphics.setColor(new Color(240, 240, 240));
		graphics.drawRoundRect(0, 0, width - 2, height - 2, 6, 6);
		setImage(img);
	}

	public void drawPoint(Point p) {
		graphics.setColor(Color.black);
		graphics.fillRect(p.getX() - ps / 2, p.getY() - ps / 2, ps, ps);
		setImage(img);
	}

	public void drawPointOnHull(Point p) {
		graphics.setColor(Color.green);
		graphics.fillRect(p.getX() - ps / 2, p.getY() - ps / 2, ps, ps);
		graphics.setColor(new Color(100, 100, 100));
		setImage(img);
	}

	public void drawMissMatch(Point p) {
		graphics.setColor(Color.red);
		graphics.fillRect(p.getX() - ps / 2, p.getY() - ps / 2, ps, ps);
		setImage(img);
	}

	public void drawCoords() {
		if (!this.coords)
			return;

		graphics.setColor(new Color(100, 100, 100));
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Point p = list.get(i);
				graphics.drawString((new StringBuilder("x:")).append(p.getX())
						.append(" y:").append(height - p.getY()).toString(),
						p.getX(), p.getY());
			}

		}
		setImage(img);
	}

	public void drawConvLine(Point p0, Point p1) {

		graphics.setColor(Color.blue);
		graphics.drawLine(p0.getX(), p0.getY(), p1.getX(), p1.getY());
		setImage(img);
	}

	private boolean exist(Point p) {
		for (int i = 0; i < list.size(); i++) {
			Point p1 = list.get(i);
			if (p.isEqual(p1)) {
				stack.push((new StringBuilder("Point ( x:")).append(p.getX())
						.append(" y:").append(height - p.getY())
						.append(") already exists").toString());
				return true;
			}
		}

		return false;
	}

	public void reset() {
		list.clear();
		drawFrame();
		drawGrid();
		array = null;
		calc = false;
		setImage(img);
	}

	public void drawPoints() {
		drawFrame();
		drawGrid();
		if (list.size() >= 3) {
			GrahamScan GS = new GrahamScan(list);
			GS.Scan();
			array = GS.getPoints();
			int marker[] = GS.getMarker();
			for (int i = 0; i < array.length; i++) {
				Point hPoint = array[i];
				drawPoint(hPoint);
				if (calc) {
					if (marker[i] == 1) {
						drawPointOnHull(hPoint);
						if (i > 0)
							drawConvLine(array[i - 1], hPoint);
					} else {
						drawMissMatch(hPoint);
					}
				}
			}
			if (list.size() > 1 && calc)
				drawConvLine(array[0], array[GS.getLastHullPoint()]);
			if (coords)
				drawCoords();

			stack.push((new StringBuilder(
					"\nConvex Hull calculation finished\n"))
					.append(GS.getHullPoints()).append("/").append(list.size())
					.append(" Points are on the Convex Hull\n").toString());

		} else {
			for (int i = 0; i < list.size(); i++) {
				Point p = list.get(i);
				drawPoint(p);
			}

			if (coords)
				drawCoords();
			stack.push("\nA polygon must have at least 3 Points\n");
		}
		setImage(img);
	}

	public void ProceedScan(int x, int y) {
		Point p = new Point(x, y);
		if (!exist(p)) {
			stack.push((new StringBuilder("Point #")).append(list.size() + 1)
					.append(" (X:" + p.getX()).append(" Y:")
					.append(height - p.getY() + ")").append(" added")
					.toString());
			list.add(p);
			drawPoint(p);
			if (coords)
				drawCoords();
		}
	}

	private int ps;
	public int height;
	public int width;
	private Graphics graphics;
	private LinkedList<Point> list;
	private Point array[];
	private Stack<String> stack;
	private boolean coords;
	private boolean grid;
	private boolean calc;
}
