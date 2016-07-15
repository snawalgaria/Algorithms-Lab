// framework taken from http://code.geeksforgeeks.org/index.php

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.LinkedList;


class Artifacts
{
	final static int INF=10000;
	class Edge
	{
	    // To store current flow and capacity of edge
	    int flow, capacity;
	 
	    // An edge u--->v has start vertex as u and end
	    // vertex as v.
	    int u, v;
	 
	    Edge(int flow, int capacity, int u, int v)
	    {
	        this.flow = flow;
	        this.capacity = capacity;
	        this.u = u;
	        this.v = v;
	    }
	};
	class Vertex
	{
		int h, e_flow;
		 
	    Vertex(int h, int e_flow)
	    {
	        this.h = h;
	        this.e_flow = e_flow;
	    }
	}
	
	class Graph
	{
	    int V;    // No. of vertices
	    Vector<Vertex> ver;
	    Vector<Edge> edge;
	 
	    // Function to push excess flow from 
	 
	Graph(int V)
	{
	    this.V = V;
	    ver=new Vector<Vertex>();
	    edge=new Vector<Edge>();
	    // all vertices are initialized with 0 height
	    // and 0 excess flow
	    for (int i = 0; i < V; i++)
	    {
	        Vertex v=new Vertex(0,0);
	        ver.add(v);
	    }
	}
	 
	void addEdge(int u, int v, int capacity)
	{
	    // flow is initialized with 0 for all edge
		Edge e=new Edge(0,capacity,u,v);
	    edge.add(e);
	}
	 
	void preflow(int s)
	{
	    // Making h of source Vertex equal to no. of vertices
	    // Height of other vertices is 0.
	    ver.get(s).h = ver.size();
	 
	    //
	    for (int i = 0; i < edge.size(); i++)
	    {
	        // If current edge goes from source
	        if (edge.get(i).u == s)
	        {
	            // Flow is equal to capacity
	            edge.get(i).flow = edge.get(i).capacity;
	 
	            // Initialize excess flow for adjacent v
	            ver.get(edge.get(i).v).e_flow += edge.get(i).flow;
	 
	            // Add an edge from v to s in residual graph with
	            // capacity equal to 0
	            //edge.push_back(Edge(-edge[i].flow, 0, edge[i].v, s));
	            Edge e=new Edge(-edge.get(i).flow,0,edge.get(i).v,s);
	            edge.add(e);
	        }
	    }
	}
	 
	// returns index of overflowing Vertex
	int overFlowVertex(Vector<Vertex> ver)
	{
	    for (int i = 1; i < ver.size() - 1; i++)
	       if (ver.get(i).e_flow > 0)
	            return i;
	 
	    // -1 if no overflowing Vertex
	    return -1;
	}
	 
	// Update reverse flow for flow added on ith Edge
	void updateReverseEdgeFlow(int i, int flow)
	{
	    int u = edge.get(i).v, v = edge.get(i).u;
	 
	    for (int j = 0; j < edge.size(); j++)
	    {
	        if (edge.get(j).v == v && edge.get(j).u == u)
	        {
	            edge.get(j).flow -= flow;
	            return;
	        }
	    }
	 
	    // adding reverse Edge in residual graph
	    Edge e = new Edge(0, flow, u, v);
	    edge.add(e);
	}
	 
	// To push flow from overflowing vertex u
	Boolean push(int u)
	{
	    // Traverse through all edges to find an adjacent (of u)
	    // to which flow can be pushed
	    for (int i = 0; i < edge.size(); i++)
	    {
	        // Checks u of current edge is same as given
	        // overflowing vertex
	        if (edge.get(i).u == u)
	        {
	            // if flow is equal to capacity then no push
	            // is possible
	            if (edge.get(i).flow == edge.get(i).capacity)
	                continue;
	 
	            // Push is only possible if height of adjacent
	            // is smaller than height of overflowing vertex
	            if (ver.get(u).h > ver.get(edge.get(i).v).h)
	            {
	                // Flow to be pushed is equal to minimum of
	                // remaining flow on edge and excess flow.
	                int flow = Math.min(edge.get(i).capacity - edge.get(i).flow,
	                               ver.get(u).e_flow);
	 
	                // Reduce excess flow for overflowing vertex
	                ver.get(u).e_flow -= flow;
	 
	                // Increase excess flow for adjacent
	                ver.get(edge.get(i).v).e_flow += flow;
	 
	                // Add residual flow (With capacity 0 and negative
	                // flow)
	                edge.get(i).flow += flow;
	 
	                updateReverseEdgeFlow(i, flow);
	 
	                return true;
	            }
	        }
	    }
	    return false;
	}
	 
	// function to relabel vertex u
	void relabel(int u)
	{
	    // Initialize minimum height of an adjacent
	    int mh = Integer.MAX_VALUE;
	 
	    // Find the adjacent with minimum height
	    for (int i = 0; i < edge.size(); i++)
	    {
	        if (edge.get(i).u == u)
	        {
	            // if flow is equal to capacity then no
	            // relabeling
	            if (edge.get(i).flow == edge.get(i).capacity)
	                continue;
	 
	            // Update minimum height
	            if (ver.get(edge.get(i).v).h < mh)
	            {
	                mh = ver.get(edge.get(i).v).h;
	 
	                // updating height of u
	                ver.get(u).h = mh + 1;
	            }
	        }
	    }
	}
	 
	// main function for printing maximum flow of graph
	int getMaxFlow(int s, int t)
	{
	    preflow(s);
	 
	    // loop untill none of the Vertex is in overflow
	    while (overFlowVertex(ver) != -1)
	    {
	        int u = overFlowVertex(ver);
	        if (!push(u))
	            relabel(u);
	    }
	 
	    // ver.back() returns last Vertex, whose
	    // e_flow will be final maximum flow
	    return ver.get(ver.size()-1).e_flow;
	    //return ver.back().e_flow;
	}
	}
	// Driver program to test above functions
	public static void main (String[] args) throws java.lang.Exception
	{
		// Let us create a graph shown in the above example
		/*int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0},
									{0, 0, 10, 12, 0, 0},
									{0, 4, 0, 0, 14, 0},
									{0, 0, 9, 0, 0, 20},
									{0, 0, 0, 7, 0, 4},
									{0, 0, 0, 0, 0, 0}
								};*/
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		
		for(int j=1;j<=noOfCases;j++)
		{
			int w,m,a,s;
		    int bi,ci,c;
		 	int di,t;
		 	int e;
		 	String typ;
		 	int f,siz;
		 	int arti[]=new int[100];
		 	String input[]=br.readLine().split(" ");
		 	w=Integer.valueOf(input[0]);
		 	m=Integer.valueOf(input[1]);
		 	a=Integer.valueOf(input[2]);
		 	s=Integer.valueOf(input[3]);
		 	Artifacts x=new Artifacts();
		 	Graph warrior=x.new Graph((w+m+a+s+2)); //total nodes is the number of artifacts+mages+spells+warriors+source+dest
		 	for(int i=1;i<=w;i++) //data for warriors
		 	{
		 		String inp[]=br.readLine().split(" ");
		 		bi=Integer.parseInt(inp[0]);
		 		ci=Integer.parseInt(inp[1]);
		 		for(int k=1;k<=ci;k++)
		 		{
		 			c=Integer.parseInt(inp[1+k]);
		 			warrior.addEdge((a+m+c),(a+m+s+i),INF);
				 }
				 warrior.addEdge((a+m+s+i),(w+m+a+s+1),bi);
			 }
			 for(int i=1;i<=m;i++) //for mages
			 {
				 String inp1[]=br.readLine().split(" ");
			 	di=Integer.parseInt(inp1[0]);
			 	for(int k=1;k<=di;k++)
			 	{
			 		t=Integer.parseInt(inp1[k]);
			 		warrior.addEdge((a+i),(a+m+t),INF);
			 		//cout<<"We have a connection between: "<<(a+i)<<" to "<<(a+m+t)<<" weight "<<INF<<endl;
				 }
			 }
			 for(int i=1;i<=a;i++) //artifacts
			 {
				 String inp[]=br.readLine().split(" ");
			 	e=Integer.parseInt(inp[0]);
			 	typ=inp[1];
			 	if(typ.charAt(0)=='n')
			 	{
			 		//cout<<"Typ= Normal"<<endl;
					 warrior.addEdge(0,i,(e/3));
			 		arti[i]=e/3;
				 }
				 else
				 {
				 	//cout<<"Typ= Epic"<<endl;
				 	warrior.addEdge(0,i,e);
				 	arti[i]=e;
				 }
				 //cout<<"We have a connection between: 0"<<" to "<<i<<" weight "<<e<<endl;
			 }
			 /*for(int i=1;i<=a;i++)
			 {
			 	cout<<arti[i]<<endl;
			 }*/
			 for(int i=1;i<=m;i++) //mages and artifacts
			 {
				 String inp[]=br.readLine().split(" ");
			 	f=Integer.parseInt(inp[0]);
			 	for(int k=1;k<=f;k++)
			 	{
			 		siz=Integer.parseInt(inp[k]);
			 		warrior.addEdge(siz,(a+i),INF);
			 		//cout<<"We have a connection between: "<<siz<<" to "<<(a+i)<<" weight "<<INF<<endl;
				 }
			 } 
		    // Initialize source and sink 
		    System.out.println("Case #" +j+": "+warrior.getMaxFlow(0, (w+m+s+a+1)));
		    br.readLine();
			}
		 	
		}
	}


