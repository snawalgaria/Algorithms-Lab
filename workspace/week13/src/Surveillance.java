import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;

public class Surveillance {
	
	public static double calcDistance(double x2,double y2,double x1,double y1) {
	    return Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow(x2 - x1, 2));
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		
		for(int i=1;i<=noOfCases;i++)
		{
			double minD=Double.MAX_VALUE;
			double dist1;
			double dist;
			int n=Integer.parseInt(br.readLine());
			String inp[]=null;
			double x,y;
			double cords[][]=new double[n][2];
			for(int j=0;j<n;j++)
			{
				inp=br.readLine().split(" ");
				cords[j][0]=Double.parseDouble(inp[0]);
				cords[j][1]=Double.parseDouble(inp[1]);
			}
			double minD1=Double.MAX_VALUE;
			int len=n;
			for(int j=1;j<len-1;j++)
			{
				dist1=calcDistance(cords[j][0],cords[j][1],cords[0][0],cords[0][1]);
				minD1=minD1>dist1?dist1:minD1;
				for(int k=j+1;k<len;k++)
				{
					dist=calcDistance(cords[j][0],cords[j][1],cords[k][0],cords[k][1]);
					minD=minD>dist?dist:minD;
				}
			}
			dist1=calcDistance(cords[len-1][0],cords[len-1][1],cords[0][0],cords[0][1]);
			minD1=minD1>dist1?dist1:minD1;
			double min = minD1 - (minD / 2.0);
			if (min < 0) 
				min = 0;
			double radius;
			if ((minD1 + min)/2 >((minD1*(len - 1))/len))
			{
				radius = Math.pow((minD1 - Math.pow(10, -12)), 2) + Math.pow(Math.pow(10, -12), 2) * (len - 1);
			}
			else
			{
				radius = Math.pow(min, 2) + Math.pow((minD1 - min), 2) * (len - 1);
			}
			
			double area=Math.PI*radius;
			System.out.println("Case #" + i + ": " + area);
			
			br.readLine();
		}
	}
}
