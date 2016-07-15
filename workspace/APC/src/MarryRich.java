import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MarryRich {

	static class Tree implements Comparator<Tree>
	{
		int money;
		Tree parent;
		int subtree;
		int data;
		boolean married;
		Tree(int data)
		{
			this.parent = this;
			this.data=data;
			subtree=1;
			married=false;
		}
		@Override
		public int compare(Tree arg0, Tree arg1) {
			// TODO Auto-generated method stub
			return arg1.money-arg0.money;
		}
	}
	public static Tree find(Tree tree)
	{
		Tree temp;
		if(tree == tree.parent)
			return tree;
		else
		{
			temp= find(tree.parent);
			tree.parent=temp;
			return temp;
			//return find(tree.parent);
		}
	}
	public static void union(Tree t1, Tree t2,int size)
	{
		t1=find(t1);
		t2=find(t2);
		if(t1.data == t2.data)
		{}
		else
		{
		if(t1.data == size)
		{
			t2.parent=t1;
			t1.subtree+=t2.subtree;
			
		}
		else if(t2.data == size)
		{
			t1.parent=t2;
			t2.subtree+=t1.subtree;
		}
		else if(t1.subtree>= t2.subtree)
		{
			t2.parent=t1;
			t1.subtree+=t2.subtree;
		}
		else
		{
			t1.parent=t2;
			t2.subtree+=t1.subtree;
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
			String input= br.readLine();
			
			String split1[]=input.split(" ");
			int noOfPeople=Integer.valueOf(split1[0]);
			int noOfRelation = Integer.valueOf(split1[1]);
			int noOfMarriage=Integer.valueOf(split1[2]);
			for(int j=0;j<noOfPeople;j++)
			{
				Tree tree = new Tree(j);
				people.add(tree);
			}
			people.get(noOfPeople-1).money=0;
			String money[]=br.readLine().split(" ");
			
			for(int j=0;j<noOfPeople-1;j++)
			{
				people.get(j).money=Integer.valueOf(money[j]);
			}
			
			for(int j=0;j<noOfRelation;j++)
			{
				String relation=br.readLine();
				
				String rel[]=relation.split(" ");
				union(people.get(Integer.valueOf(rel[0])-1),people.get(Integer.valueOf(rel[1])-1), noOfPeople-1);
				/*people.get(Integer.valueOf(rel[0])-1).parent=people.get(noOfPeople-1);
				people.get(Integer.valueOf(rel[1])-1).parent=people.get(noOfPeople-1);*/
			}
			for(int j=0;j<noOfMarriage;j++)
			{
				String marriage=br.readLine();
				
				String rel[]=marriage.split(" ");
				people.get(Integer.valueOf(rel[0])-1).married=true;
				people.get(Integer.valueOf(rel[1])-1).married=true;
				union(people.get(Integer.valueOf(rel[0])-1),people.get(Integer.valueOf(rel[1])-1),noOfPeople-1);
			//union(find(people.get(Integer.valueOf(rel[0])-1)),people.get(noOfPeople-1),noOfPeople-1);
			}
			List<Tree> availablePeople =new ArrayList<Tree>();
			for(int j=0;j<noOfPeople-1;j++)
			{
				if(find(people.get(j)) != people.get(noOfPeople-1))
				{
					if(people.get(j).married == false)
					{
					availablePeople.add(people.get(j));
					}
				}	
			}
			String output;
			if(availablePeople.size()==0)
				output="impossible";
			else
			{
			availablePeople.sort(new Tree(1));
			output=Integer.valueOf(availablePeople.get(0).money).toString();
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
