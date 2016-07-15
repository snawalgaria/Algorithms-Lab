import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
// rational class code taken from http://introcs.cs.princeton.edu/java/92symbolic/Rational.java.html
class Rational implements Comparable<Rational> {
    private static Rational zero = new Rational(0, 1);

    private long num;   // the numerator
    private long den;   // the denominator

    // create and initialize a new Rational object
    public Rational(long numerator, long denominator) {

        // deal with x/0
        //if (denominator == 0) {
        //   throw new RuntimeException("Denominator is zero");
        //}

        // reduce fraction
        long g = gcd(numerator, denominator);
        num = numerator   / g;
        den = denominator / g;

        // only needed for negative numbers
        if (den < 0) { den = -den; num = -num; }
    }

    // return the numerator and denominator of (this)
    public long numerator()   { return num; }
    public long denominator() { return den; }

    // return double precision representation of (this)
    public double toDouble() {
        return (double) num / den;
    }

    // return string representation of (this)
    public String toString() { 
        if (den == 1) return num + "";
        else          return num + "/" + den;
    }

    // return { -1, 0, +1 } if a < b, a = b, or a > b
    public int compareTo(Rational b) {
        Rational a = this;
        long lhs = a.num * b.den;
        long rhs = a.den * b.num;
        if (lhs < rhs) return -1;
        if (lhs > rhs) return +1;
        return 0;
    }

    // is this Rational object equal to y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Rational b = (Rational) y;
        return compareTo(b) == 0;
    }

    // hashCode consistent with equals() and compareTo()
    public int hashCode() {
        return this.toString().hashCode();
    }


    // create and return a new rational (r.num + s.num) / (r.den + s.den)
    public static Rational mediant(Rational r, Rational s) {
        return new Rational(r.num + s.num, r.den + s.den);
    }

    // return gcd(|m|, |n|)
    private static long gcd(long numerator, long denominator) {
        if (numerator < 0) numerator = -numerator;
        if (denominator < 0) denominator = -denominator;
        if (0 == denominator) return numerator;
        else return gcd(denominator, numerator % denominator);
    }

    // return lcm(|m|, |n|)
    private static long lcm(long den2, long den3) {
        if (den2 < 0) den2 = -den2;
        if (den3 < 0) den3 = -den3;
        return den2 * (den3 / gcd(den2, den3));    // parentheses important to avoid overflow
    }

    // return a * b, staving off overflow as much as possible by cross-cancellation
    public Rational times(Rational b) {
        Rational a = this;

        // reduce p1/q2 and p2/q1, then multiply, where a = p1/q1 and b = p2/q2
        Rational c = new Rational(a.num, b.den);
        Rational d = new Rational(b.num, a.den);
        return new Rational(c.num * d.num, c.den * d.den);
    }


    // return a + b, staving off overflow
    public Rational plus(Rational b) {
        Rational a = this;

        // special cases
        if (a.compareTo(zero) == 0) return b;
        if (b.compareTo(zero) == 0) return a;

        // Find gcd of numerators and denominators
        long f = gcd(a.num, b.num);
        long g = gcd(a.den, b.den);

        // add cross-product terms for numerator
        Rational s = new Rational((a.num / f) * (b.den / g) + (b.num / f) * (a.den / g),
                                  lcm(a.den, b.den));

        // multiply back in
        s.num *= f;
        return s;
    }

    // return -a
    public Rational negate() {
        return new Rational(-num, den);
    }

    // return a - b
    public Rational minus(Rational b) {
        Rational a = this;
        return a.plus(b.negate());
    }


    public Rational reciprocal() { return new Rational(den, num);  }

    // return a / b
    public Rational divides(Rational b) {
        Rational a = this;
        return a.times(b.reciprocal());
    }
}
// referred code ends

public class Ginger {

	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) 
        {
        	String inp[]=br.readLine().split(" ");
        	int n=Integer.parseInt(inp[0]);
        	int m=Integer.parseInt(inp[1]);
        	List<Point2D> cords = new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                String[] cord = br.readLine().split(" ");
                cords.add(new Point2D.Double(Integer.parseInt(cord[0]),Integer.parseInt(cord[1])));
            }
            int len = cords.size();

            Double area = areaPolygon(cords, len);
            Long intArea=area.longValue();
            if(intArea<0)
    		{
    			intArea=(-1)*intArea;
    		}
            Rational totalArea=new Rational(intArea,2);
           Double sumOfNum=0.0;
           Double areaTriangle;
            for(int i=0;i<n;i++)
    		{
    			Point2D p,q,r;
    			if(i==0)
    			{
    				p=new Point2D.Double(cords.get(i).getX(),cords.get(i).getY());
    				q=new Point2D.Double(cords.get(i+1).getX(),cords.get(i+1).getY());
    				r=new Point2D.Double(cords.get(n-1).getX(),cords.get(n-1).getY());
    			}
    			else if(i==n-1)
    			{
    				p=new Point2D.Double(cords.get(i).getX(),cords.get(i).getY());
    				q=new Point2D.Double(cords.get(0).getX(),cords.get(0).getY());
    				r=new Point2D.Double(cords.get(i-1).getX(),cords.get(i-1).getY());
    			}
    			else
    			{
    				p=new Point2D.Double(cords.get(i).getX(),cords.get(i).getY());
    				q=new Point2D.Double(cords.get(i+1).getX(),cords.get(i+1).getY());
    				r=new Point2D.Double(cords.get(i-1).getX(),cords.get(i-1).getY());
    			}
    			List<Point2D> crds = new ArrayList<>();
    			crds.add(p);
    			crds.add(q);
    			crds.add(r);
    			areaTriangle=areaPolygon(crds,3);
    			if(areaTriangle<0)
    			{
    				areaTriangle=(-1)*areaTriangle;
    			}
    			sumOfNum=sumOfNum+areaTriangle;
    		}
            Rational areaCut=new Rational(sumOfNum.intValue(),2*m*m);
            Rational ratio=new Rational(sumOfNum.intValue(),m*m*intArea);
            System.out.println("Case #"+tc+": "+ratio.numerator()+"/"+ratio.denominator());
       	 	br.readLine();
        	
        }
	}

	private static Double areaPolygon(List<Point2D> cords, int len) {
		Double area=0.0D;
		for(int i=0;i<len;i++){
			
		    Point2D curr = cords.get(i);
		    Point2D next = cords.get((i+1)%len);
		    
		    area+= curr.getX() * next.getY() - curr.getY() * next.getX();
		}
		return area;
	}
      
}
