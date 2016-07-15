import java.util.*;
import java.lang.*;
import java.io.*;
 
// A class to represent a connected, directed and weighted graph
public class CurrencyExchange
{
    // A class to represent a weighted edge in graph
    class Edge {
        int src, dest;
        double weight;
        Edge() {
            src = dest = 0;
            weight = 0;
        }
    };
 
    int V, E;
    Edge edge[];
 
    // Creates a graph with V vertices and E edges
    CurrencyExchange(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
 
    // The main function that finds shortest distances from src
    // to all other vertices using Bellman-Ford algorithm.  The
    // function also detects negative weight cycle
   void BellmanFord(CurrencyExchange graph,int src, int k, int counter)
    {
        int V = graph.V, E = k;
        double dist[] = new double[V];
 
        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE
        for (int i=0; i<V; ++i)
            dist[i] = -1;
        dist[src] = 0;
 
        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i=1; i<V; ++i)
        {
            for (int j=0; j<E; ++j)
            {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                double weight = graph.edge[j].weight;
                if (dist[u]!=-1 &&
                    dist[u]+weight>dist[v])
                    dist[v]=dist[u]+weight;
            }
        }
 
        // Step 3: check for negative-weight cycles.  The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        //  path, then there is a cycle.
        boolean flag=false;
        for (int j=0; j<E; ++j)
        {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            double weight = graph.edge[j].weight;
            if (dist[u]!=Integer.MAX_VALUE &&
                dist[u]+weight<dist[v])
            {
             // System.out.println("Graph contains negative weight cycle");
              flag = true;
            }
        }
       
        		System.out.println("Case #"+counter+": "+(dist[V-1]));
        
    }
 
   
    // Driver method to test above function
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        int numberofvertices = 0;  // Number of vertices in graph
        int E = 0;  // Number of edges in graph
        Scanner scanner = new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
        int testcases=Integer.parseInt(br.readLine());      
        for(int counter=1;counter<=testcases;counter++)
        {
        numberofvertices = Integer.parseInt(br.readLine());
        double points=0;
        int source=0;
        int dest[][] = new int[numberofvertices][numberofvertices-1];
        int k=0;
        double wt[] = new double[numberofvertices];
        int noOfedges[]=new int[numberofvertices];
        for(int i=0;i<numberofvertices;i++)
        {
        	String inp[]=br.readLine().split(" ");
        		wt[i] = Integer.valueOf(inp[0]);      	
        		noOfedges[i] = Integer.valueOf(inp[1]);
        		for(int m=0;m<noOfedges[i];m++)
        		{
        		//graph.edge[k] = new Edge();
        			
        		dest[i][m] =Integer.valueOf(inp[m+2])-1;
        		}      		
        	
        }
        for(int z=0;z<noOfedges.length;z++)
        	E=E+noOfedges[z];
        CurrencyExchange graph = new CurrencyExchange(numberofvertices, E);
        for(int j=0;j<numberofvertices;j++)
        {
        	for(int l=0;l<noOfedges[j];l++)
        	{
        	graph.edge[k].src=j;
        	graph.edge[k].dest=dest[j][l];
        	graph.edge[k].weight=wt[dest[j][l]];
        	k++;
        	}
        }

        graph.BellmanFord(graph, 0,k,counter);
  // System.out.println("Case #"+"counter"+": "+((graph.BellmanFord(graph, 0,k)*-1)+points));
    }
    }
}