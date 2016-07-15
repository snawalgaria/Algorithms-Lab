//Code copied from http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
import java.util.*;
import java.lang.*;
import java.io.*;
public class CurrencyExchange
{
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
   void bellmanFord(CurrencyExchange graph,int src, int k, int cntr)
    {
        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE
        int V = graph.V, E = k;
        double dist[] = new double[V];
        for (int i=0; i<V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        for (int i=1; i<V; ++i)
        {
            for (int j=0; j<E; ++j)
            {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                double weight = graph.edge[j].weight;
                if (dist[u]!=Integer.MAX_VALUE &&
                    dist[u]+weight<dist[v])
                    dist[v]=dist[u]+weight;
            }
        }
        
        boolean cycle=false;
        for (int j=0; j<E; ++j)
        {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            double weight = graph.edge[j].weight;
            if (dist[u]!=Integer.MAX_VALUE &&
                dist[u]+weight<dist[v])
            {
             // System.out.println("Graph contains negative weight cycle");
              cycle = true;
            }
        }
        if(cycle==false)
        	{        	
        	if(Double.isInfinite((Math.pow(Math.E, dist[V-1]))))
        		System.out.println("Case #"+cntr+": "+"impossible"); 
        	else
        		System.out.println("Case #"+cntr+": "+(Math.pow(Math.E, dist[V-1])));       	
        	}
        else
        	System.out.println("Case #"+cntr+": "+"Jackpot"); //negative edge cycle 
    } 
    public static void main(String[] args)
    {
        int V = 0;  // Number of vertices in graph
        int E = 0;  // Number of edges in graph
        Scanner in = new Scanner(System.in);
        int test=in.nextInt();      
        for(int t=1;t<=test;t++)
        {
        V = in.nextInt();
        E = in.nextInt();
        CurrencyExchange graph = new CurrencyExchange(V, E); 
        double points=0;
        int src=0;
        int dst = 0;
        int cnt=0;
        for(int i=1;i<=E;i++)
        {
        		src = in.nextInt();       	
        		dst = in.nextInt();
        		points = in.nextDouble(); 
        		graph.edge[cnt].dest = dst-1;
        		graph.edge[cnt].src = src-1;
        	   graph.edge[cnt].weight = Math.log(points);//store weight as log to perform bellman shortest path
        		cnt++;      	
        }
        graph.bellmanFord(graph, 0,cnt,t);
      }
    }
}