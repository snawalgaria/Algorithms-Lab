import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class Woodchuck {

	public static int findMinIndex(int sum[],int rows)
	{
		int minIndex=0;
		int minValue=0;
		for(int j=0;j<rows;j++)
		{
			if(sum[j]<minValue)
				minValue=sum[j];
			minIndex=j;
		}
		return minIndex;
	}
	public static int MaxValue(int sum[],int rows)
	{
		int maxValue=0;
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
		Double n=Double.parseDouble(input[0]);
		Double m=Double.parseDouble(input[1]);
		Integer col=(int) Math.ceil(n/m);
		Integer rows=m.intValue();
		Integer Mat[][]=new Integer[rows][col];
		Integer heights[]=new Integer[n.intValue()];
		for(j=0;j<n.intValue();j++)
		{
			heights[j]=Integer.parseInt((br.readLine()));
		}
		Arrays.sort(heights,Collections.reverseOrder());
		int sum[]=new int[rows];
		int minIndex=0;
		int high=n.intValue()-1;
		for(j=0;j<col;j++)
		{
			for(k=0;k<rows;k++)
			{	
		
				//Mat[k][j]=heights[low];
				minIndex=findMinIndex(sum,rows);
				sum[minIndex]=sum[minIndex]+heights[low];
				low++;
			}
			if(low>=high)
				break;
		}
		int max=0;
		for(j=0;j<rows;j++)
		{
			if(max<sum[j])
				max=sum[j];
		}
		System.out.println("Case #"+t+": "+max);
		br.readLine();
	}
	
	}
}
