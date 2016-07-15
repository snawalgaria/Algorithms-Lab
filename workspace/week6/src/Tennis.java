import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tennis 
{
public static class Subset
{
	List<Integer> li;
	
	Subset(List<Integer> li)
	{
	this.li=li;
	}
}

	public static boolean checkMax(int adjMat[][],int n,int l,Subset s)
	{	
		int p,q,r,count=0,t,val,flag=0;
		//Map<Integer,List<Integer>> hm=new HashMap<Integer,List<Integer>>();
		for(p=1;p<n+1;p++)
		{
			if(l>n+1-p)
			{
				flag=1;
				break;
			}
			s.li.clear();
			count=0;
			//bool[p]=1;
			flag=0;
			for(q=1;q<n+1;q++)
			{
				if(adjMat[p][q]==1)
				{
					count++;
					//bool[q]=1;
					s.li.add(q);
				}
			}
				if(count >= l)
				{
					flag=0;
					for(r=0;r<s.li.size();r++)
					{
						val=s.li.get(r);
						if(val == p)
						{
							continue;
						}
						count =0;
						for(t=0;t<s.li.size();t++)
						{
							if(adjMat[val][s.li.get(t)]==1)
								count++;
						}
						if(count >= l)
						{
							if(count == l && s.li.size()>l)
							{
								int size=s.li.size();
								for(r=0;r<s.li.size();r++)
								{
									if(adjMat[val][s.li.get(r)]==0)
									{
										s.li.remove(r);
									}
								}
							}
								
							continue;
						}
						else
						{
							flag=1;
							s.li.clear();
							break;
						}
					}
				}
				
				else
				{
					s.li.clear();
					flag=1;
				}
				
			if(flag == 0)
			{
				break;
			}
			
		}
		
		if(flag == 0)
			return true;
			else
				return false;
	}
public static void main(String args[]) throws NumberFormatException, IOException
{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int cases=Integer.parseInt(br.readLine());
	for(int i=1;i<=cases;i++)
	{
		int n,m,j,k,l;
		String inp[]=br.readLine().split(" ");
		String mat[]=null;
		n=Integer.parseInt(inp[0]);
		m=Integer.parseInt(inp[1]);
		int bool[]=new int[n+1];
		int adjMat[][]=new int[n+1][n+1];
		for(j=0;j<m;j++)
		{
			mat=br.readLine().split(" ");
			adjMat[Integer.valueOf(mat[0])][Integer.valueOf(mat[1])]=1;
			adjMat[Integer.valueOf(mat[1])][Integer.valueOf(mat[0])]=1;
		}
		for(j=1;j<n+1;j++)
		{
			adjMat[j][j]=1;
		}
		Subset s=new Subset(new ArrayList<Integer>());
		for(l=n;l>=1;l--)
		{
			if(checkMax(adjMat,n,l,s))
			{
				break;
			}
		}
		String output="";
		for(j=0;j<s.li.size();j++)
		{
			output=output+s.li.get(j).toString()+" ";
					
		}
		System.out.println("Case #"+i+": "+output);
		br.readLine();
		
	}
	
	
}
}

