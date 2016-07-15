import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Poker {
	
	static class series implements Comparator<series>
	{
		int start;
		int finish;
		int profit;
		series(int a,int b,int p)
		{
			this.start=a;
			this.finish=b;
			this.profit=p;
		}
		@Override
		public int compare(series arg0, series arg1) {
			// TODO Auto-generated method stub
			return arg0.finish-arg1.finish;
		}
	}
	static int latestNonConflict(ArrayList<series> arr, int i)
	{
	    for (int j=i-1; j>=0; j--)
	    {
	        if (arr.get(j).finish < arr.get(i).start)
	            return j;
	    }
	    return -1;
	}
	static long findMaxProfit(ArrayList<series> arr, int n)
	{
	    // Sort jobs according to finish time
	    // Create an array to store solutions of subproblems.  table[i]
	    // stores the profit for jobs till arr[i] (including arr[i])
	    long table[] = new long[n];
	    table[0] = arr.get(0).profit;
	 
	    // Fill entries in M[] using recursive property
	    for (int i=1; i<n; i++)
	    {
	        // Find profit including the current job
	        int inclProf = arr.get(i).profit;
	        int l = latestNonConflict(arr, i);
	        if (l != -1)
	            inclProf += table[l];
	 
	        // Store maximum of including and excluding
	        table[i] = Math.max(inclProf, table[i-1]);
	    }
	 
	    // Store result and free dynamic memory allocated for table[]
	    long result = table[n-1];
	    
	    return result;
	}

	public static void main(String args[]) throws NumberFormatException, IOException
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int cases=Integer.parseInt(br.readLine());
	for(int t=1;t<=cases;t++)
	{
		int n=Integer.parseInt(br.readLine());
		int i,j,k,l;
		String inp[]=null;
		series s;
		ArrayList<series> sList=new ArrayList<series>();
		for(i=0;i<n;i++)
		{
			inp=br.readLine().split(" ");
			s=new series(Integer.valueOf(inp[0]),Integer.valueOf(inp[1]),Integer.valueOf(inp[2]));
			sList.add(s);
		}
		sList.sort(new series(-1,-1,-1));
		System.out.println("Case #"+t+": "+findMaxProfit(sList,sList.size()));
		br.readLine();
	}
	
	}
}
