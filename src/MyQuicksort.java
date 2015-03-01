/**
 * @author Dan Verständig
 *
 */
public class MyQuicksort {
	
	private Point[] array = null;
	public MyQuicksort(Point[] toSort){
		this.array = toSort;
	}
	
	public void sort() {
		quicksort(1, this.array.length - 1);
	}
	
	private void quicksort(int lo, int hi) {
		int i = lo;
		int j = hi;
		Point q = this.array[(lo + hi) / 2];
		while (i <= j) {
			while (this.array[i].isLeft(q))
				i++;
			for (; q.isLeft(this.array[j]); j--)
				;
			if (i <= j)
				swap(i++, j--);
		}
		if (lo < j)
			quicksort(lo, j);
		if (i < hi)
			quicksort(i, hi);
	}

	public void swap(int i, int j) {
		Point p = this.array[i];
		this.array[i] = this.array[j];
		this.array[j] = p;
	}
}