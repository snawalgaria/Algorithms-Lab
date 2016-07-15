import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
public class Euler {

	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		for(int i=1;i<=cases;i++)
		{
			String inp[]=null;
			Vector<Double> a=new Vector<Double>();
			Vector<Double> b=new Vector<Double>();
			Vector<Double> c=new Vector<Double>();
				inp=br.readLine().split(" ");
				a.add(Double.parseDouble(inp[0]));
				a.add(Double.parseDouble(inp[1]));
				a.add((double) 1);
				inp=br.readLine().split(" ");
				b.add(Double.parseDouble(inp[0]));
				b.add(Double.parseDouble(inp[1]));
				b.add((double) 1);
				inp=br.readLine().split(" ");
				c.add(Double.parseDouble(inp[0]));
				c.add(Double.parseDouble(inp[1]));
				c.add((double) 1);
				
				Vector<Double> ab=new Vector<Double>();
				Vector<Double> bc=new Vector<Double>();
				Vector<Double> ca=new Vector<Double>();
				Vector<Double> medA=new Vector<Double>();
				Vector<Double> medB=new Vector<Double>();
				Vector<Double> medC=new Vector<Double>();
				Vector<Double> midA=new Vector<Double>();
				Vector<Double> midB=new Vector<Double>();
				Vector<Double> midC=new Vector<Double>();
				medA.add((b.get(0)+c.get(0))/2);
				medA.add((b.get(1)+c.get(1))/2);
				medA.add((double)1);
				medB.add((a.get(0)+c.get(0))/2);
				medB.add((a.get(1)+c.get(1))/2);
				medB.add((double)1);
				medC.add((a.get(0)+b.get(0))/2);
				medC.add((a.get(1)+b.get(1))/2);
				medC.add((double)1);
				ab.add((a.get(1)*b.get(2))-(b.get(1)*a.get(2)));
				ab.add(a.get(0)*b.get(2)-(b.get(0)*a.get(2)));
				ab.add((a.get(0)*b.get(1))-(b.get(0)*a.get(1)));
				bc.add((b.get(1)*c.get(2))-(c.get(1)*b.get(2)));
				bc.add(b.get(0)*c.get(2)-(c.get(0)*b.get(2)));
				bc.add((b.get(0)*c.get(1))-(c.get(0)*b.get(1)));
				ca.add((c.get(1)*a.get(2))-(a.get(1)*c.get(2)));
				ca.add(c.get(0)*a.get(2)-(a.get(0)*c.get(2)));
				ca.add((c.get(0)*a.get(1))-(a.get(0)*c.get(1)));
				midA.add(e)
		}
	}
}
