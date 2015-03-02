import java.util.LinkedList;


/**
 * @author Dan Verständig
 *
 */
public class GrahamScan {

	public GrahamScan(LinkedList<Point> list) {
		array = new Point[list.size()];
		OthersList = new LinkedList<Point>();
		marker = new int[list.size()];
		isOnHull = true;
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
			marker[i] = 0;
		}

		n = array.length;
		hp = 0;
	}

	public void Scan() {
		MyQuicksort s = new MyQuicksort(array);
		s.swap(0, lowestPoint());
		
		Point q = new Point(array[0]);
		makeRelativeTo(q);
		s.sort();
		makeRelativeTo(q.negate());
		int i = 3;
		for (int k = 3; k < n; k++) {
			s.swap(i, k);
			for (; !isConvex(i - 1); s.swap(i - 1, i--))
				OthersList.add(new Point(array[i - 1]));
			i++;
		}

		cleanUp();
	}

	private void cleanUp() {
		for (int i = 0; i < array.length; i++) {
			Point hPoint = array[i];
			for (int j = 0; j < OthersList.size(); j++) {
				Point v = OthersList.get(j);
				if (!hPoint.isEqual(v))
					continue;
				isOnHull = false;
				break;
			}

			if (isOnHull) {
				hp++;
				marker[i] = 1;
			} else {
				marker[i] = 0;
				isOnHull = true;
			}
		}

	}

	private void makeRelativeTo(Point p0) {
		for (int i = 0; i < n; i++)
			array[i].makeRelativeTo(p0);

	}

	private int lowestPoint() {
		int min = 0;
		for (int i = 0; i < n; i++)
			if (array[i].isLower(array[min]))
				min = i;

		return min;
	}

	private boolean isConvex(int i) {
		return array[i].relativeTo(array[i - 1]).isLeft(
				array[i + 1].relativeTo(array[i - 1]));
	}

	public int getLastHullPoint() {
		for (int i = array.length - 1; i > 0; i--)
			if (marker[i] == 1)
				return i;

		return -1;
	}

	public int[] getMarker() {
		return marker;
	}

	public Point[] getPoints() {
		return array;
	}

	public int getHullPoints() {
		return hp;
	}

	private Point array[];
	private int n;
	private int hp;
	private int marker[];
	private LinkedList<Point> OthersList;
	private boolean isOnHull;
}