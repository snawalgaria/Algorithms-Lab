import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
public class SpaceTravel {

	static class City{
		int x;int y;int z;
		City parent;
		int noOfConnections;
	City(int x,int y,int z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
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
			return arg0.length-arg1.length;
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
		if(t1 == t2)
		{}
		else
		{
			t2.parent=t1;
			t1.noOfConnections+=t2.noOfConnections;
		}
	}
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			String output="";
			int k,l,c=0;
			City c3 = null,c4=null;
			String planets=br.readLine();
			int planet=Integer.valueOf(planets);
			ArrayList<City> planetList= new ArrayList<City>();
			ArrayList<Edge> edgeList=new ArrayList<Edge>();
			ArrayList<Edge> mst=new ArrayList<Edge>();
			for(k=0;k<planet;k++)
			{
				String coordinate[]=br.readLine().split(" ");
				int x=Integer.valueOf(coordinate[0]);
				int y=Integer.valueOf(coordinate[1]);
				int z=Integer.valueOf(coordinate[2]);
				planetList.add(new City(x,y,z));
			}
			for(k=0;k<planet-1;k++)
			{
				City c1=planetList.get(k);
				for(l=k+1;l<planet;l++)
				{
					
					City c2=planetList.get(l);
					int dx=c1.x-c2.x;
					int dy=c1.y-c2.y;
					int dz=c1.z-c2.z;
					dx=Math.abs(dx);
					dy=Math.abs(dy);
					dz=Math.abs(dz);
					edgeList.add(new Edge(c1,c2,dx+dy+dz));
				}
			}
			edgeList.sort(new Edge(new City(0,0,0),new City(0,0,0),-1));
			for(int m=0;m<edgeList.size();m++)
			{
				City c1=findParent(edgeList.get(m).a);
				City c2=findParent(edgeList.get(m).b);
				if(c1!=c2)
				{
					mst.add(edgeList.get(m));
					union(c1,c2);
				}
			}
			int sum=0;
			for(int n=0;n<mst.size();n++)
			{
				sum=sum+mst.get(n).length;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(sum);
			System.out.println(sb);
			br.readLine();
		}
	}
}