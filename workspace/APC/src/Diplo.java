import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class Diplo {
	static class Tree 
	{
		Tree parent;
		Tree enemy;
		int subtree;
		int data;
		Tree(int data)
		{
			this.parent = this;
			this.data=data;
			subtree=1;
			this.enemy=null;
		}
	}
	public static Tree findParent(Tree tree)
	{
		Tree temp;
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
	public static Tree findEnemy(Tree tree)
	{
		if(tree.enemy == null)
		{
			return null;
		}
		else
		{
			return tree.enemy;
		}
	}
public static void union(Tree t1,Tree t2)
{
	if(t1.data == t2.data)
	{}
	else
	{
		t2.parent=t1;
		t1.subtree+=t2.subtree;
	}
}
public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int noOfCases= Integer.parseInt(br.readLine());
	for(int i=1;i<=noOfCases;i++)
	{
		List<Tree> people = new ArrayList<Tree>();
		String input[]= br.readLine().split(" ");
		int noOfPeople = Integer.valueOf(input[0]);
		int noOfRelation = Integer.valueOf(input[1]);
		for(int j=1;j<=noOfPeople;j++)
		{
			Tree tree = new Tree(j);
			people.add(tree);
		}
		for(int j=0;j<noOfRelation;j++)
		{
			String sign[]=br.readLine().split(" ");
			Tree t1;
			Tree t2;
			Tree enemyt1;
			Tree enemyt2;
			t1=people.get(Integer.valueOf(sign[1])-1);
			t2=people.get(Integer.valueOf(sign[2])-1);
			t1=findParent(t1);
			t2=findParent(t2);
			if(t1.data == t2.data)
			{
				continue;
			}
			if(sign[0].equals("F"))
				{
					if(t1.subtree>=t2.subtree)
					{
						union(t1,t2);
						enemyt1=t1.enemy;
						enemyt2=t2.enemy;
						t2.enemy=null;
						if(enemyt1!=null)
						{
							unionEnemy(t1, enemyt1, enemyt2);
						}
							else
							{
								if(enemyt2!=null)
								{
									t1.enemy=enemyt2;
									enemyt2.enemy=t1;
								}
								
							}
						}
					else
					{
						union(t2,t1);
						enemyt2=t2.enemy;
						enemyt1=t1.enemy;
						t1.enemy=null;
						if(enemyt2!=null)
						{
							unionEnemy(t2,enemyt2,enemyt1);
						}
						else
						{
							if(enemyt1!=null)
							{
								t2.enemy=enemyt1;
								enemyt1.enemy=t2;
							}
					    }
                }
			}
			else
			{
				
				enemyt1=t1.enemy;
				enemyt2=t2.enemy;
				if(enemyt1!=null && enemyt2!=null)
				{
					if(enemyt1.data == t2.data)
					{
						continue;
					}
					if(t2.subtree>=enemyt1.subtree)
					{
						union(t2,enemyt1);
						t1.enemy=null;
						enemyt1.enemy=null;
						union(enemyt2,t1);
					}
					else
					{
						union(enemyt1,t2);
						t2.enemy=null;
						enemyt2.enemy=null;
						union(t1,enemyt2);
					}
				}
				else if(enemyt1 == null && enemyt2!=null)
				{
					union(enemyt2,t1);
				}
				else if(enemyt2 == null && enemyt1!=null)
				{
					union(enemyt1,t2);
				}
				else if(enemyt1 == null && enemyt2 == null)
				{
					t1.enemy=t2;
					t2.enemy=t1;
				}
			}
			
		}
		String output;
		if(findParent(people.get(0)).subtree>(noOfPeople/2))
		{
			output="yes";
		}
		else
		{
			output="no";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Case #");
		sb.append(i);
		sb.append(": ");
		sb.append(output);
		System.out.println(sb);
		br.readLine();
	}
}
private static void unionEnemy(Tree t1, Tree enemyt1, Tree enemyt2) {
	if(enemyt2!=null)
	{
		if(enemyt2.subtree>enemyt1.subtree)
		{
			union(enemyt2,enemyt1);
			t1.enemy=enemyt2;
			enemyt2.enemy=t1;
			enemyt1.enemy=null;
		}
		else 
		{
			union(enemyt1,enemyt2);
			t1.enemy=enemyt1;
			enemyt1.enemy=t1;
			enemyt2.enemy=null;
		}
		
	}
	else
	{
		//Do Nothing
	}
}
}
