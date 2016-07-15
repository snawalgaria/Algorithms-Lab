import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Walking {

	static class City{
		int no;
		City parent;
		int noOfConnections;
	City(int i)
	{
		this.no=i;
		this.parent=this;
		this.noOfConnections=1;
	}
	
}
	static class Edge implements Comparator<Edge>{
		public final City a;
		public final City b;
		public final int length;
		public Edge(City a,City b,int length)
		{
			this.a=a;
			this.b=b;
			this.length=length;
		}
		@Override
		public int compare(Edge arg0, Edge arg1) {
			// TODO Auto-generated method stub
			return arg1.length-arg0.length;
		}
		
		
	}
	public static City findParent(City tree)
	{
		City temp;
		if(tree == tree.parent)
			return tree;
		else
		{
			temp= findParent(tree.parent);
			tree.parent=temp;
			return temp;
			//return find(tree.parent);
		}
	}
	public static void union(City t1,City t2)
	{
		if(t1.no == t2.no)
		{}
		else
		{
			t2.parent=t1;
			t1.noOfConnections+=t2.noOfConnections;
		}
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			int k,l;
			int totalCapacity=0;
			String input[]=br.readLine().split(" ");
			int vertices=Integer.valueOf(input[0]);
			int paths=Integer.valueOf(input[1]);
			ArrayList<City> cityList=new ArrayList<City>();
			ArrayList<Edge> edgeList=new ArrayList<Edge>();
			ArrayList<Edge> mst=new ArrayList<Edge>();
			for(k=1;k<=vertices;k++)
			{
				cityList.add(new City(k));
			}
			for(l=1;l<=paths;l++)
			{
				String rd[]=br.readLine().split(" ");
				int city1=Integer.valueOf(rd[0]);
				int city2=Integer.valueOf(rd[1]);
				int length=Integer.valueOf(rd[2]);
				edgeList.add(new Edge(cityList.get(city1-1),cityList.get(city2-1),length));
			}
			edgeList.sort(new Edge(new City(-1),new City(-2),1));
			for(int m=0;m<edgeList.size();m++)
			{
				City c1=findParent(edgeList.get(m).a);
				City c2=findParent(edgeList.get(m).b);
				if(c1!=c2)
				{
					mst.add(edgeList.get(m));
					union(c1,c2);
				}
				totalCapacity=totalCapacity+edgeList.get(m).length;
			}
				int mstCapacity=0;
				for(Edge e:mst)
				{
					mstCapacity=mstCapacity+e.length;
				}
				int out=totalCapacity-mstCapacity;
				StringBuilder sb = new StringBuilder();
				sb.append("Case #");
				sb.append(i);
				sb.append(": ");
				sb.append(out);
				System.out.println(sb);
				br.readLine();
		}
}
}
