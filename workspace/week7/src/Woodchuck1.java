import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class Woodchuck1 {

	public static int findMinIndex(long sum[],int rows)
	{
		int minIndex=0;
		long minValue=Long.MAX_VALUE;
		for(int j=0;j<rows;j++)
		{
			if(sum[j]<minValue)
			{
				minValue=sum[j];
				minIndex=j;
			}
		}
		return minIndex;
	}
	public static long maxValue(long sum[],int rows)
	{
		long maxValue=0;
		for(int j=0;j<rows;j++)
		{
			if(sum[j]>maxValue)
				maxValue=sum[j];
			
		}
		return maxValue;
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int cases=Integer.parseInt(br.readLine());
	for(int t=1;t<=cases;t++)
	{
		int j,k,low=0;
		String input[]=br.readLine().split(" ");
		int n=Integer.parseInt(input[0]);
		int m=Integer.parseInt(input[1]);
		//Integer col=(int) Math.ceil(n/m);
		//Integer rows=m.intValue();
		//Integer Mat[][]=new Integer[rows][col];
		Integer heights[]=new Integer[n];
		for(j=0;j<n;j++)
		{
			heights[j]=Integer.parseInt((br.readLine()));
		}
		Arrays.sort(heights,Collections.reverseOrder());
		long sum[]=new long[m];
		int minIndex=0;
			for(low=0;low<n;low++)
			{	
		
				//Mat[k][j]=heights[low];
				minIndex=findMinIndex(sum,m);
				sum[minIndex]=sum[minIndex]+heights[low];
			}
		System.out.println("Case #"+t+": "+maxValue(sum,m));
		br.readLine();
	}
	}
}
