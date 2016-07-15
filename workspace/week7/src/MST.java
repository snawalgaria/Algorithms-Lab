//referred from Geek for geeks http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
import java.util.*;
import java.lang.*;
import java.io.*;
 
class MST
{
    // Number of vertices in the graph
    private int V;
    MST(int n)
    {
    	V=n;
    }
 
    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    
    // Function to construct and print MST for a graph represented
    //  using adjacency matrix representation
    int[] primMST(int graph[][])
    {
        // Array to store constructed MST
        int parent[] = new int[V];
 
        // Key values used to pick minimum weight edge in cut
        int key[] = new int [V];
 
        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];
 
        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        // Always include first 1st vertex in MST.
        key[0] = 0;     // Make key 0 so that this vertex is
                        // picked as first vertex
        parent[0] = -1; // First node is always root of MST
 
        // The MST will have V vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);
 
            // Add the picked vertex to the MST Set
            mstSet[u] = true;
 
            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)
 
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v]!=0 && mstSet[v] == false &&
                    graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }
 
        // print the constructed MST
        //printMST(parent, V, graph);
        return parent;
    }
 
    public static void main (String[] args) throws IOException
    {
      
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	int cases=Integer.parseInt(br.readLine());
    	for(int i=1;i<=cases;i++)
    	{
    		int j,k,low=0;
        int n=Integer.parseInt(br.readLine());
        MST t = new MST(n);
		int graph[][]=new int[n][n];
		String inp[]=new String[n];
		for(j=0;j<n;j++)
		{
			inp=br.readLine().split(" ");
			for(k=0;k<n;k++)
			{
				graph[j][k]=Integer.parseInt(inp[k]);
			}
		}
        int parent[]=t.primMST(graph);
        String output=Integer.valueOf(n).toString();
        int size=n-1;
        while(true)
        {
        	if(size == 0)
        	{
        		break;
        	}
        	if(parent[size]==0)
        	{
        		low=parent[size]+1;
        		output=low+" "+output;
        		break;
        	}
        	low=parent[size]+1;
        	output=low+" "+output;
        	size=parent[size];
        }
        System.out.println("Case #"+i+": "+output);
		br.readLine();
    }
}
}
// This code is contributed by Aa