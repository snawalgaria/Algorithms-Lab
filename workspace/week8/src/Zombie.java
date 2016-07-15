import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.io.IOException;
public class Zombie {

	static class grocery implements Comparator<grocery>
	{
		int no;
		int quantity;
		int wt;
		int value;
		double ratio;
		grocery(int no,int quantity,int wt,int value)
		{
			this.no=no;
			this.quantity=quantity;
			this.wt=wt;
			this.value=value;
			ratio=value/wt;
		}
		@Override
		public int compare(grocery arg0, grocery arg1) {
			// TODO Auto-generated method stub
			return (int) (arg1.ratio-arg0.ratio);
		}
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		for(int t=1;t<=cases;t++)
		{
			String inp[]=br.readLine().split(" ");
			int w=Integer.parseInt(inp[0]);
			int n=Integer.parseInt(inp[1]);
			String input[]=null;
			int i,j,k,l;
			for(i=0;i<n;i++)
			{
				input=br.readLine().split(" ");
			}
			
		}
	}
}
