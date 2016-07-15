import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
public class Snowball {

	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		for(int t=1;t<=cases;t++)
		{
			String inp[]=br.readLine().split(" ");
			int xl=Integer.valueOf(inp[0]);
			int yl=Integer.valueOf(inp[1]);
			inp=br.readLine().split(" ");
			double xs=Double.valueOf(inp[0]);
			double ys=Double.valueOf(inp[1]);
			inp=br.readLine().split(" ");
			double xw1=Double.valueOf(inp[0]);
			double yw1=Double.valueOf(inp[1]);
			double xw2=Double.valueOf(inp[2]);
			double yw2=Double.valueOf(inp[3]);
			inp=br.readLine().split(" ");
			double xe=Double.valueOf(inp[0]);
			double ye=Double.valueOf(inp[1]);
			double xans = 0;
			double yans = 0;
			if(xw1 == xw2)
			{
				yans=ye;
				xans=(2*xw1)-xe;
			}
			else
			{
				double m=(yw2-yw1)/(xw2-xw1);
				double mP=-1/m;
				//double c=ye-(mP*xe);
				double c=yw2-(m*xw2);
				double d= (xe + (ye - c)*m)/(1 + Math.pow(m,2));
				xans=(2*d)-xe;
				yans=(2*d*m) -ye + 2*c;
			}
			System.out.println("Case #"+t+": "+xans+" "+yans);
			br.readLine();
		}
	}
}
