import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Football {

	public int goldBerg(int G[][],int s,int t)
	{
		return t;
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int noOfCases=Integer.parseInt(br.readLine());
		for(int i=0;i<noOfCases;i++)
		{
			int j,k,l,x;
			String input[]=br.readLine().split(" ");
			Set<HashSet<Integer>> pairs=new HashSet<HashSet<Integer>>();
			int n=Integer.valueOf(input[0]);
			int m=Integer.valueOf(input[1]);
			String wins[]=br.readLine().split(" ");
			int w[]=new int[n+1];
			int g[]=new int[n+1];
			int gi[][]=new int[n+1][n+1];
			int max[]=new int[n+1];
			for(j=1;j<=n;j++)
				w[j]=Integer.valueOf(wins[j]);
			for(j=0;j<m;j++)
			{
				String inp[]=br.readLine().split(" ");
				int src=Integer.valueOf(inp[0]);
				int dst=Integer.valueOf(inp[1]);
				gi[src][dst]+=1;
				gi[dst][src]+=1;
				HashSet<Integer> pair=new HashSet<Integer>();
				pair.add(src);
				pair.add(dst);
				pairs.add(pair);
				
			}
			for(j=1;j<=n;j++)
			{
				for(k=1;k<=n;k++)
				{
					g[j]+=gi[j][k];
				}
			}
			for(j=1;j<=n;j++)
			{
				max[j]=w[j]+g[j];
			}
			int size=n+1+(n*(n-1)/2);
			int adjMat[][]= new int[size][size];
			for(j=1;j<=n;j++)
			{
				int diff[]=new int[n+1];
				
			for(k=1;k<=n;k++)
			{
				//Checking if team J will be eliminated
				
				if(j==k)
				{
					diff[j]=-1;
				}
				diff[k]=max[j]-w[k];
					
			}
			
			for(l=1;l<=n;l++)
			{
				if(l==j)
					continue;
				for(int p=1;p<=n;p++)
				{
					if(p == j)
						continue;
					if(l == p)
						continue;
					adjMat[0][(n-1)*(l-1)+p]=gi[l][p];
				}
				
			}
			for()
			
			
			
				
			
			
			
		}
				
	}
	}
}
