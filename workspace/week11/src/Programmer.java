
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;



/*frame work referred from <a href="http://algs4.cs.princeton.edu/99scientific">Section 1.2</a> of
   <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.*/
 
final class Point2 implements Comparable<Point2D> {

    final Double x;    // x coordinate
    final Double y;    // y coordinate

    public Point2D(double x, double y) {
        
         this.x = x;
         this.y = y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if      (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else                return  0;
    }

    public int compareTo(Point2D that) {
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        return 0;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Point2D that = (Point2D) other;
        return this.x == that.x && this.y == that.y;
    }


    
    public Comparator<Point2D> polarOrder() {
        return new PolarOrder();
    }
    
    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;

            if      (dy1 >= 0 && dy2 < 0) return -1;    // q1 above; q2 below
            else if (dy2 >= 0 && dy1 < 0) return +1;    // q1 below; q2 above
            else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if      (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else                          return  0;
            }
            else return -ccw(Point2D.this, q1, q2);     // both above or below

            // Note: ccw() recomputes dx1, dy1, dx2, and dy2
        }
    }

    
}
/*referred from <a href="http://algs4.cs.princeton.edu/99scientific">Section 9.9</a> of
 <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.*/
public class Programmer {
    private Stack<Point2D> hull = new Stack<Point2D>();
    public Programmer(Point2D[] pts) {

       
        int N = pts.length;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++)
            points[i] = pts[i];
        
        Arrays.sort(points);
        
        Arrays.sort(points, 1, N, points[0].polarOrder());

        hull.push(points[0]);       

        
        int k1;
        for (k1 = 1; k1 < N; k1++)
            if (!points[0].equals(points[k1])) break;
        if (k1 == N) return;        
        int k2;
        for (k2 = k1 + 1; k2 < N; k2++)
            if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
        hull.push(points[k2-1]);    
        for (int i = k2; i < N; i++) {
            Point2D top = hull.pop();
            if(!hull.isEmpty())
            {
            while (Point2D.ccw(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            }
            hull.push(top);
            hull.push(points[i]);
        }

        assert isConvex();
    }

    public List<Point2D> hull() {
        List<Point2D> s  = new ArrayList<Point2D>();
        for (Point2D p : hull) s.add(p);
        
        Collections.sort(s);
        return s;
    }

    private boolean isConvex() {
        int N = hull.size();
        if (N <= 2) return true;

        Point2D[] points = new Point2D[N];
        int n = 0;
        for (Point2D p : hull()) {
            points[n++] = p;
        }

        for (int i = 0; i < N; i++) {
            if (Point2D.ccw(points[i], points[(i+1) % N], points[(i+2) % N]) <= 0) {
                return false;
            }
        }
        return true;
    }

   
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(br.readLine());

        for (int t = 1; t <= cases; t++) 
        {
        int n=Integer.parseInt(br.readLine());
        int i,j;
        String inp[]=null;
        Point2D[] points = new Point2D[n];
        for(i=0;i<n;i++)
        {
        	inp=br.readLine().split(" ");
        	int x = Integer.valueOf(inp[0]);
            int y = Integer.valueOf(inp[1]);
            points[i] = new Point2D(x, y);
        }
        
        Programmer graham = new Programmer(points);
        System.out.println("Case #"+t+": ");
        
        List<Point2D> st =graham.hull();
        System.out.println(st.size());
        for (Point2D p : graham.hull())
            System.out.println(p.x.intValue()+" "+p.y.intValue());
        
        br.readLine();
        }
    }
}
