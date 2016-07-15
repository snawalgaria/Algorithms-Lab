import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
public class Hiking {
	static class Node implements Comparator<Node>{
		int dist;
		boolean visited;
		Node predecessor;
		int no;
	Node(int no)
	{
		this.no=no;
		visited=false;
		dist=1000001;
		predecessor=null;		
	}
	@Override
	public int compare(Node arg0, Node arg1) {
		// TODO Auto-generated method stub
		return (arg0.dist-arg1.dist); 
	}
	
}

	public static boolean notAllVisited(ArrayList<Node> nodeList)
	{
		int flag=0;
		for(int i=0;i<nodeList.size();i++)
		{
			if(nodeList.get(i).visited==false)
			{
				flag=1;
				return true;
			}

		}
			return false;
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		
		for(int i=1;i<=noOfCases;i++)
		{
			int j,k;
			String input[]=br.readLine().split(" ");
			int intersections=Integer.valueOf(input[0]);
			PriorityQueue<Node> p1=new PriorityQueue<Node>(intersections,new Node(-1));
			ArrayList<Node> nodeList=new ArrayList<Node>();
			for(k=0;k<intersections;k++)
			{
				nodeList.add(new Node(k));
			}
			int adjMat[][]=new int[intersections][intersections];
			int edges=Integer.valueOf(input[1]);
			for(k=1;k<=edges;k++)
			{
				String inp[]=br.readLine().split(" ");
				int n1=Integer.valueOf(inp[0]);
				int n2=Integer.valueOf(inp[1]);
				int e=Integer.valueOf(inp[2]);
				adjMat[n1-1][n2-1]=e;adjMat[n2-1][n1-1]=e;
			}
			nodeList.get(0).dist=0;
			nodeList.get(0).visited=true;
			for(int m=0;m<intersections;m++)
			{
				if(adjMat[0][m]!=0)
				{
					nodeList.get(m).dist=nodeList.get(0).dist+adjMat[0][m];
					p1.add(nodeList.get(m));
				}

			}
			while(!p1.isEmpty())
			{
				Node w=p1.poll();
				w.visited=true;
				for(int l=0;l<intersections;l++)
				{
					if(adjMat[w.no][l]!=0) 
					{
						if(nodeList.get(l).visited==false)
						{
						if(!p1.contains(nodeList.get(l)))
						{
							
							nodeList.get(l).dist=nodeList.get(l).dist<nodeList.get(w.no).dist+adjMat[w.no][l]?nodeList.get(l).dist:nodeList.get(w.no).dist+adjMat[w.no][l];
							nodeList.get(l).visited=true;
							p1.add(nodeList.get(l));
						}
						else
						{
							p1.remove(nodeList.get(l));
							nodeList.get(l).dist=nodeList.get(l).dist<nodeList.get(w.no).dist+adjMat[w.no][l]?nodeList.get(l).dist:nodeList.get(w.no).dist+adjMat[w.no][l];
							nodeList.get(l).visited=true;
							p1.add(nodeList.get(l));
						}
						
					}
					else
						{ 
						if(p1.contains(nodeList.get(l)))
						{
						if(nodeList.get(l).dist>=nodeList.get(w.no).dist+adjMat[w.no][l])
						{
							p1.remove(nodeList.get(l));
							nodeList.get(l).dist=nodeList.get(w.no).dist+adjMat[w.no][l];
							nodeList.get(l).visited=true;
							p1.add(nodeList.get(l));
						}
						}
					}
				}
				
			}
				
			}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(nodeList.get(intersections-1).dist);
			System.out.println(sb);
			br.readLine();
			
			
		}
	}


}
