import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Paradox 
{
	static class Node{
	    public final int points;
	    public final HashSet<Edge> inEdges;
	    public final HashSet<Edge> outEdges;
	    public Node(int points) {
	      this.points = points;
	      inEdges = new HashSet<Edge>();
	      outEdges = new HashSet<Edge>();
	    }
	    public Node addEdge(Node node){
	      Edge e = new Edge(this, node);
	      outEdges.add(e);
	      node.inEdges.add(e);
	      return this;
	    }
	  }

	  static class Edge{
	    public final Node from;
	    public final Node to;
	    public Edge(Node from, Node to) {
	      this.from = from;
	      this.to = to;
	    }
	  }
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			int k,l;
			String input[]=br.readLine().split(" ");
			int n=Integer.parseInt(input[0]);
			int m=Integer.parseInt(input[1]);
			int g=Integer.parseInt(input[2]);
			String points[]=br.readLine().split(" ");
			ArrayList<Node> allNodes=new ArrayList<Node>();
			for(k=0;k<=n;k++)
			{
				if(k==0)
				{
					allNodes.add(new Node(0));
				allNodes.add(new Node(Integer.valueOf(points[k])));
			}
			
			}
		}
	}
}
