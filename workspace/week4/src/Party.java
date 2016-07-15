import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
public class Party {
	static class Node implements Comparator<Node>{
		int dist;
		boolean visited;
		int wt;
		int no;
	Node(int no,int wt)
	{
		this.no=no;
		visited=false;
		dist=-1;
		this.wt=wt;		
	}
	@Override
	public int compare(Node arg0, Node arg1) {
		// TODO Auto-generated method stub
		return (arg1.dist-arg0.dist); 
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
			int intersections=Integer.parseInt(br.readLine());
			PriorityQueue<Node> p1=new PriorityQueue<Node>(intersections,new Node(-1,-1));
			ArrayList<Node> nodeList=new ArrayList<Node>();
			ArrayList<int[]> adjList=new ArrayList<int[]>();
			for(k=0;k<intersections;k++)
			{
				String inp[]=br.readLine().split(" ");
				int n1=Integer.valueOf(inp[0]);
				nodeList.add(new Node(k,n1));
				int n2=Integer.valueOf(inp[1]);
				int arr[]=new int[n2];
				for(j=2;j<=n2+1;j++)
				{	
				arr[j-2]=Integer.valueOf(inp[j]);
				}
				adjList.add(arr);
			}
			nodeList.get(0).dist=nodeList.get(0).wt;
			nodeList.get(0).visited=true;
			for(int m=0;m<adjList.get(0).length;m++)
			{
				int adj=adjList.get(0)[m]-1;
					nodeList.get(adj).dist=nodeList.get(0).dist+nodeList.get(adj).wt;
					p1.add(nodeList.get(adj));
			}
			while(!p1.isEmpty())
			//while(notAllVisited(nodeList))
			{
				Node w=p1.poll();
				w.visited=true;
				for(int l=0;l<adjList.get(w.no).length;l++)
				{
					int adj=adjList.get(w.no)[l]-1;
				
					
						if(nodeList.get(adj).dist<nodeList.get(w.no).dist+nodeList.get(adj).wt)
						{
							//if(p1.contains(nodeList.get(adj)))
							//if(nodeList.get(adj).visited==true)
							//{
							p1.remove(nodeList.get(adj));
							//}
							nodeList.get(adj).dist=nodeList.get(w.no).dist+nodeList.get(adj).wt;
							nodeList.get(adj).visited=true;
							p1.add(nodeList.get(adj));
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

/*if(nodeList.get(adj).visited==false)
{
//if(!p1.contains(nodeList.get(adj)))
//{
	
	nodeList.get(adj).dist=nodeList.get(adj).dist>nodeList.get(w.no).dist+nodeList.get(adj).wt?nodeList.get(adj).dist:nodeList.get(w.no).dist+nodeList.get(adj).wt;
	nodeList.get(adj).visited=true;
	p1.add(nodeList.get(adj));
}
else
{
	if(nodeList.get(adj).dist<nodeList.get(w.no).dist+nodeList.get(adj).wt)
	{
	p1.remove(nodeList.get(adj));
	nodeList.get(adj).dist=nodeList.get(w.no).dist+nodeList.get(adj).wt;
	nodeList.get(adj).visited=true;
	p1.add(nodeList.get(adj));
	}
}

}*/
//else
//{
/*else
{
	if(nodeList.get(adj).dist<nodeList.get(w.no).dist+nodeList.get(adj).wt)
	{
		nodeList.get(adj).dist=nodeList.get(w.no).dist+nodeList.get(adj).wt;
		nodeList.get(adj).visited=true;
		p1.add(nodeList.get(adj));
	}
}*/