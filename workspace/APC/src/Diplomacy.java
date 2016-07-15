import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Diplomacy {

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
	public static void union(Tree t1, Tree t2)
	{
		t1=findParent(t1);
		t2=findParent(t2);
		if(t1.data == t2.data)
		{}
		else
		{
		if(t1.data == 1)
		{
			t2.parent=t1;
			t1.subtree+=t2.subtree;
			/*if(t1.enemy!=null && t2.enemy!=null && flag == 0)
			{
				union(t1.enemy,t2.enemy,1);
			}
			if(t2.enemy!=null)
			{
				t1.enemy=t2.enemy;
				t1.enemy.enemy=t1;
				t2.enemy=null;
			}*/
			
		}
		else if(t2.data == 1)
		{
			t1.parent=t2;
			t2.subtree+=t1.subtree;
			//t2.enemy=t1.enemy!=null?t1.enemy:t2.enemy;
			/*if(t1.enemy!=null)
			{
				t2.enemy=t1.enemy;
				t2.enemy.enemy=t2;
				t1.enemy=null;
			}*/
		}
		else if(t1.subtree>= t2.subtree)
		{
			t2.parent=t1;
			t1.subtree+=t2.subtree;
			//t1.enemy=t2.enemy!=null?t2.enemy:t1.enemy;
			/*if(t2.enemy!=null)
			{
				t1.enemy=t2.enemy;
				t1.enemy.enemy=t1;
				t2.enemy=null;
			}*/
		}
		else
		{
			t1.parent=t2;
			t2.subtree+=t1.subtree;
			//t2.enemy=t1.enemy!=null?t1.enemy:t2.enemy;
			/*if(t1.enemy!=null)
			{
				t2.enemy=t1.enemy;
				t2.enemy.enemy=t2;
				t1.enemy=null;
			}*/
		}
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
				if(sign[0].equals("F"))
				{
					Tree t1=people.get(Integer.valueOf(sign[1])-1);
					Tree t2=people.get(Integer.valueOf(sign[2])-1);
					//union(t1,t2);
					if(findParent(t1).enemy!=null && findParent(t2).enemy!=null)
					{
						unionWithBothEnemy(t1, t2);
					}
					if(findParent(t1).enemy!=null && findParent(t2).enemy == null)
					{
						unionWithEnemy(t1, t2);
						
					}
					else if(findParent(t1).enemy==null && findParent(t2).enemy != null)
					{
						unionWithEnemy(t2,t1);
					}
					else
						union(t1,t2);
						
					
				}
				if(sign[0].equals("A"))
				{
					Tree t1=findParent(people.get(Integer.valueOf(sign[1])-1));
					Tree t2=findEnemy(t1);
					Tree t3= findParent(people.get(Integer.valueOf(sign[2])-1));
					Tree t4 = findEnemy(t3);
					if(t2 == null)
					{
						
						if(t4 == null)
						{
							t1.enemy=t3;
							t3.enemy=t1;
						}
						else
						{
							unionWithEnemy(t4,t1);
						}
					}
					else
					{
						if(t4 == null)
						{
						unionWithEnemy(t2,t3);
						}
						else
						{
							unionWithBothEnemy(t1,t3);
						}
					}
				}
			}
			String output;
			if(people.get(0).subtree>(noOfPeople/2))
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
	private static void unionWithBothEnemy(Tree t1, Tree t2) {
		Tree enemy1=findParent(t1).enemy;
		union(findParent(t1).enemy,findParent(t2).enemy);
		union(t1,t2);
		findParent(t1).enemy=findParent(enemy1);
		findParent(enemy1).enemy=findParent(t1);
		t1.enemy=t1!=findParent(t1)?null:t1.enemy;
		t2.enemy=t2!=findParent(t2)?null:t2.enemy;
	}
	private static void unionWithEnemy(Tree t1, Tree t2) {
		Tree temp=findParent(t1).enemy;
		union(t1,t2);
		temp.enemy.enemy=null;
		findParent(t1).enemy=temp;
		temp.enemy=findParent(t1);
	}
}
