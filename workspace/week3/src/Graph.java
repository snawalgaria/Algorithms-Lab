import java.util.*;
import java.lang.*;
import java.io.*;
 
class Graph
{
    int V, E;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // /collection of all edges
 
    class Edge
    {
        int src, dest;
    };
 
    // Creates a graph with V vertices and E edges
    Graph(int v,int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
 
    // A utility function to find the subset of an element i
    int find(int parent[], int i)
    {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }
 
    // A utility function to do union of two subsets
    void Union(int parent[], int x, int y)
    {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }
 
 
    // The main function to check whether a given graph
    // contains cycle or not
    static int calculatePoints( Graph graph,String points[])
    {
        // Allocate memory for creating V subsets
        int parent[] = new int[graph.V];
     // Initialize all subsets as single element sets
        for (int i=0; i<graph.V; ++i)
            parent[i]=-1;
        
        int pickedup[]=new int[graph.V];
        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
        for (int i = 0; i < graph.E; ++i)
        {
            int x = graph.find(parent, graph.edge[i].src);
            int y = graph.find(parent, graph.edge[i].dest); 
            graph.Union(parent, x, y);
        }
        for(int j=1;j<graph.V;j++)
        {
        	int x=graph.find(parent,j);
        	int y=graph.find(parent, graph.edge[j].dest);
        }

    }
    public static void main (String[] args)
    {
  
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			String input[]=br.readLine().split(" ");
			int vertices=Integer.valueOf(input[0]);
			int edges=Integer.valueOf(input[1]);
        Graph graph = new Graph(vertices, edges);
        String points[]=br.readLine().split(" ");
        for(int j=0;j<edges;j++)
        {
        	String pair[]=br.readLine().split(" ");
        	graph.edge[j].src=Integer.valueOf(pair[1]);
        	graph.edge[j].dest=Integer.valueOf(pair[0]);
        }
        calculatePoints(graph,points);
        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
 
        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;
 
        // add edge 0-2
        graph.edge[2].src = 0;
        graph.edge[2].dest = 2;
 
        if (graph.isCycle(graph)==1)
            System.out.println( "graph contains cycle" );
        else
            System.out.println( "graph doesn't contain cycle" );
    }
}