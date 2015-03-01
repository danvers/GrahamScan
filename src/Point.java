/**
 * @author Dan Verständig
 *
 */
class Point {

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		x = p.x;
		y = p.y;
	}

	public Point() {
		x = 0;
		y = 0;
	}

	public void setX(int newX) {
		x = newX;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int newY) {
		y = newY;
	}

	public float doubledist() {
		return x * x + y * y;
	}

	public void makeRelativeTo(Point p) {
		x -= p.x;
		y -= p.y;
	}

	public Point relativeTo(Point p) {
		return new Point(x - p.x, y - p.y);
	}

	public Point negate() {
		return new Point(-x, -y);
	}

	public boolean isEqual(Point p) {
		return x == p.x && y == p.y;
	}

	public boolean isLeft(Point p) {
		float f = x * p.y - y * p.x;
		return f > 0.0F || f == 0.0F && doubledist() < p.doubledist();
	}

	public boolean isLower(Point p) {
		return y < p.y || y == p.y && x < p.x;
	}

	private int x;
	private int y;
}