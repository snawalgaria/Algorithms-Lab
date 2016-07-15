import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
public class CurrencyExchange {
	    
	    private static class Edge  {
	        int u;
	        int v;
	        double w;

	        public Edge(int a, int b, double c)     {
	            u=a;
	            v=b;
	            w=c;
	        }
	    }
	    public static boolean cycle(ArrayList<Edge> edges,double d[]) {
            int j;
            for (j = 0; j < edges.size(); ++j)
                if (d[edges.get(j).u] + edges.get(j).w < d[edges.get(j).v])
                     return false;
            return true;
        }
	    public static int getPred(int p[],int n)
	    {
	    	return p[n];
	    }
	    public static void main(String args[]) throws IOException
	    {
	    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    		int noOfCases = Integer.parseInt(br.readLine());
    		
    		for(int i=1;i<=noOfCases;i++)
    		{
    		double d[];
    		int p[];
   	    int n,e,s,j,l;
    	    String output="";

	        ArrayList<Edge> edges = new ArrayList<Edge>();
	        
	        String input[]=br.readLine().split(" ");
	        n=Integer.valueOf(input[0]);
	        e=Integer.valueOf(input[1]);
	        for(l=0;l<e;l++) {
	        	String inp[]=br.readLine().split(" ");
	        	int from=Integer.valueOf(inp[0])-1;
	        	int to=Integer.valueOf(inp[1])-1;
	        	double wt=Double.valueOf(inp[2]);
	        	double logWt= Math.log10(wt);
	            edges.add(new Edge(from,to,logWt));
	            }
	       
	        e = edges.size();
	        d = new double[n];
	        p = new int[n];
	        for(l=0;l<n;++l) {
	            d[l]=100000000;
	            p[l] = -1;
	        }

	        d[0] = 0;
	        for (int t = 0; t < n-1; ++t) {
	        	
	            for (j = 0; j < e; ++j) { 
	            	
	                if (d[edges.get(j).u] + edges.get(j).w < d[edges.get(j).v]) {
	                    d[edges.get(j).v] = d[edges.get(j).u] + edges.get(j).w;
	                    p[edges.get(j).v] = edges.get(j).u;
	                }
	             }
	         }
	        
	            if(!cycle(edges,d)) {
	                output="Jackpot";
	            }
	            int pd=n-1;
	            int pr;
	            int flag=0;
	            for(int q=0;q<n;q++,pd=pr)
	            {
	            	pr=getPred(p,pd);
	            	if(pr != 0 && pr!=-1)
	            		continue;
	            	if(pr ==0)
	            	{
	            	flag=1;
	            	break;
	            	}
	            	if(pr==-1)
	            	{
	            		break;
	            	}
	            }
	            if(flag == 0)
	            {
	            	output="impossible";
	            }
	        
	            if(output == "")
	            {
	            output=String.format("%6f", Math.pow(10, d[n-1]));
	            }
	            StringBuilder sb = new StringBuilder();
				sb.append("Case #");
				sb.append(i);
				sb.append(": ");
				sb.append(output);
				System.out.println(sb);
				br.readLine();
	        }
	    }
}
