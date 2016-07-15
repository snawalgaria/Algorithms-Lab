import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Water {

	static class ControlRooms implements Comparator<ControlRooms>
	{
		int number;
		boolean isReachable;
		Integer d;
		ControlRooms(int number,int d,boolean is)
		{
			this.number=number;
			this.d=d;
			this.isReachable=is;
		}
		@Override
		public int compare(ControlRooms arg0, ControlRooms arg1) {
			// TODO Auto-generated method stub
			return arg0.d-arg1.d;
		}
	}
	public static ControlRooms getRoom(int number,List<ControlRooms>cRooms)
	{
		for(ControlRooms n:cRooms)
		{
			if(n.number==number)
				return n;
		}
		return null;
	}
	static class Hallways implements Comparator<Hallways>
	{
		boolean reachable;
		int from;
		int to;
		int level;
		Hallways(int from,int to,int level,int currentlevel)
		{
			this.from=from;
			this.to=to;
			this.level=level;
			if(level>=currentlevel)
			{
				reachable=true;
			}
			else
			{
				reachable=false;
			}
		}
		@Override
		public int compare(Hallways arg0, Hallways arg1) {
			// TODO Auto-generated method stub
			return arg1.level-arg0.level;
		}
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		for(int t=1;t<=cases;t++)
		{
			String input[]=br.readLine().split(" ");
			String inp[]=null;
			int n=Integer.parseInt(input[0]);
			int m=Integer.parseInt(input[1]);
			int k=Integer.parseInt(input[2]);
			int l=Integer.parseInt(input[3]);
			int clevel=l;int from,to,level;
			int a,b,c,d,ai;
			Hallways h;
			Set<Integer> reachableRooms=new HashSet<Integer>();
			List<Hallways> unreachableHalls=new ArrayList<Hallways>();
			boolean reachableControls[]=new boolean[k+1];
			List<Integer> rControls=new ArrayList<Integer>();
			List<ControlRooms> cRooms=new ArrayList<ControlRooms>();
			for(int i=1;i<=m;i++)
			{
				inp=br.readLine().split(" ");
				from=Integer.parseInt(inp[0]);
				to=Integer.parseInt(inp[1]);
				level=Integer.parseInt(inp[2]);
				h=new Hallways(from,to,level,clevel);
				if(h.reachable == true)
				{
					for(a=from;a<=to;a++)
					{
						reachableRooms.add(a);
					}
				}
				else
				{
					unreachableHalls.add(h);
				}
			}
			int minD=Integer.MAX_VALUE;
			for(b=1;b<=k;b++)
			{
				inp=br.readLine().split(" ");
				ai=Integer.parseInt(inp[0]);
				d=Integer.parseInt(inp[1]);
				if(reachableRooms.contains(ai))
				{
					//cRooms.add(new ControlRooms(ai,d,true));
					minD=minD>d?d:minD;
				}
				else
				{
					cRooms.add(new ControlRooms(ai,d,false));
				}
				
			}
			Collections.sort(unreachableHalls, new Hallways(1,2,3,4));
			Collections.sort(cRooms,new ControlRooms(0,0,false));
	
			List<Hallways> temp=unreachableHalls;
			ControlRooms r;
			int f1=0,f2=0;
			if(reachableRooms.size()==n)
			{
				f1=1;
			}
			else
			{
			while(clevel>=minD)
				{		
		
					for(Hallways hall:temp)
					{
						if(hall.level>=clevel)
						{
							unreachableHalls.remove(hall);
							hall.reachable=true;
							for(a=hall.from;a<=hall.to;a++)
							{
								reachableRooms.add(a);
								r=getRoom(a,cRooms);
								if(r!=null)
								{
									minD=minD>r.d?r.d:minD;
									cRooms.remove(r);
								}
							}
						}
						else
						{
							temp=unreachableHalls;
							clevel=hall.level;
							break;
						}
						
					}
					if(reachableRooms.size()==n)
					{
						f1=1;
						break;
					}
					
				}
			}
			if(f1==1)
			{
				System.out.println("Case #"+t+": "+clevel);
			}
			else
				System.out.println("Case #"+t+": impossible");
			
			br.readLine();
		}
	}
}
