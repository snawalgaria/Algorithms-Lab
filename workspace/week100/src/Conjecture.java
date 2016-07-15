import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
public class Conjecture
{

	public static void main(String args[]) throws NumberFormatException, IOException
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int a,b;
	boolean pi[]=new boolean[100000001];
	
	for(a=2;a<=100000000;a++)
	{
		pi[a]=true;
	}
	
	for(a=1;a<=100000000;a++)
	{
	if(pi[a] == true)
	{
		for(b=2*a;b<=100000000;b=b+a)
		{
			pi[b]=false;
		}
	}
	}
	int cases=Integer.parseInt(br.readLine());
	
	for(int t=1;t<=cases;t++)
	{
		Vector<Integer> ans;
		Integer n=Integer.parseInt(br.readLine());
		int i,j,k,l;
		
		if(n%2 == 0)
		{
			ans=findEven(n, pi);
		}
		else
		{
			ans=new Vector<Integer>();
			if(pi[n-4] == true)
			{
				ans.add(2);
				ans.add(2);
				ans.add(n-4);
			}
			else
			{
			ans.add(3);
			Vector<Integer> ans2=findEven(n-3,pi);
			ans.add(ans2.get(0));
			ans.add(ans2.get(1));
			}
		}
		System.out.print("Case #"+t+": ");
		Iterator tr=ans.iterator();
		while(tr.hasNext())
			System.out.print(tr.next() +" ");
		System.out.println();
		
			
}
}

	private static Vector<Integer> findEven(Integer n, boolean[] pi) {
		int k;
		int l;
		Vector <Integer> num=new Vector<Integer>();
		k=2;l=n-k;
		while(true)
		{
		if(pi[k] == true && pi[l] == true)
			
		{
			num.add(k);
			num.add(l);
			return num;
		}
		else
		{
			k++;
			l--;
		}
		}
		
	}
}
