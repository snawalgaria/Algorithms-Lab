import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class Pizza {
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
		String output="yes";
		Tree t1,t2,enemyt1,enemyt2;
		List<Tree> toppings = new ArrayList<Tree>();
		String input[]= br.readLine().split(" ");
		int noOfToppings = Integer.valueOf(input[0]);
		int noOfPairs = Integer.valueOf(input[1]);
		for(int j=1;j<=noOfToppings;j++)
		{
		Tree tree = new Tree(j);
		toppings.add(tree);
		}
		for(int k=0;k<noOfPairs;k++)
		{
			String pair[]= br.readLine().split(" ");
			t1=toppings.get(Integer.valueOf(pair[0])-1);
			t2=toppings.get(Integer.valueOf(pair[1])-1);
			t1=findParent(t1);
			t2=findParent(t2);
			enemyt1=t1.enemy;
			enemyt2=t2.enemy;
			if(t1.data==t2.data)
					{
						output="no";
					}
			else if(enemyt1!=null && enemyt2!=null)
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
		StringBuilder sb = new StringBuilder();
		sb.append("Case #");
		sb.append(i);
		sb.append(": ");
		sb.append(output);
		System.out.println(sb);
		br.readLine();

	}
   }
}