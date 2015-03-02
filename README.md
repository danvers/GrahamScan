# Graham Scan Algorithm
This is a demo of the Graham Scan algorithm, implemented in Java and delivered with an Applet.

## Usage

```Java
LinkedList<Point> list = new List();

Point p0 = new Point(x, y);
list.add(p0);
...
Point pn = new Point(x, y);
list.add(pn);

GrahamScan GS = new GrahamScan(list);
GS.Scan();
````
## References
* T.H. Cormen, C.E. Leiserson, R.L. Rivest & C. Stein (2001). Introduction to Algorithms. 2nd Edition, The MIT Press 
* R.L. Graham (1972). An Efficient Algorithm for Determining the Convex Hull of a Finite Planar Set. Information Processing Letters 1, 132-133
* or simply here: https://en.wikipedia.org/wiki/Graham_scan
