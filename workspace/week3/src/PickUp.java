
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

	public class PickUp {

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
	  public static void main(String[] args) throws IOException {
	    
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int noOfCases = Integer.parseInt(br.readLine());
			for(int i=1;i<=noOfCases;i++)
			{
				int k,l;
				String input[]=br.readLine().split(" ");
				int vertices=Integer.valueOf(input[0]);
				int paths=Integer.valueOf(input[1]);
				int sumPoints=0;
				String points[]=br.readLine().split(" ");
				ArrayList<Node> allNodes=new ArrayList<Node>();
				for(k=0;k<vertices;k++)
				{
					allNodes.add(new Node(Integer.valueOf(points[k])));
				}
				for(l=0;l<paths;l++)
				{
					String pair[]=br.readLine().split(" ");
					int from=Integer.valueOf(pair[0]);
					int to=Integer.valueOf(pair[1]);
					allNodes.get(from-1).addEdge(allNodes.get(to-1));
				}
	    HashSet<Node> S = new HashSet<Node>(); 
	    for(Node n : allNodes){
	      if(n.inEdges.size() == 0){
	        S.add(n);
	      }
	    }

	    //while S is non-empty do
	    while(!S.isEmpty()){
	      //remove a node n from S
	      Node n = S.iterator().next();
	      sumPoints=sumPoints+n.points;
	      S.remove(n);
	      //for each node m with an edge e from n to m do
	      for(Iterator<Edge> it = n.outEdges.iterator();it.hasNext();){
	        //remove edge e from the graph
	        Edge e = it.next();
	        Node m = e.to;
	        it.remove();//Remove edge from n
	        m.inEdges.remove(e);//Remove edge from m

	        //if m has no other incoming edges then insert m into S
	        if(m.inEdges.isEmpty()){
	          S.add(m);
	        }
	      }
	    }
	    StringBuilder sb = new StringBuilder();
		sb.append("Case #");
		sb.append(i);
		sb.append(": ");
		sb.append(sumPoints);
		System.out.println(sb);
		br.readLine();
	   
}
}}
	  