import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
public class Street {
	public static void main(String args[]) throws NumberFormatException, IOException
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int cases=Integer.parseInt(br.readLine());
	for(int t=1;t<=cases;t++)
	{
		String input[]=br.readLine().split(" ");
		int l=Integer.parseInt(input[0]);
		int n=Integer.parseInt(input[1]);
		int d=Integer.parseInt(input[2]);
		int j,k=0,m;
		Integer pos[]=new Integer[n];
		String inp[]=br.readLine().split(" ");
		for(j=0;j<n;j++)
		{
			pos[j]=Integer.parseInt(inp[j]);
		}
		Arrays.sort(pos);
		int currPos=0;
		int switched=0;
		int flag=0;
		if(n==0)
		{
			flag=1;
		}
			while(currPos<l && k<n)
			{
				while((k<n))
				{
					if(pos[k]<=currPos+d)
					{
						k++;
						continue;
					}
					
					else
					{
						break;
					}
				}
				//if(pos[k-1]<=(currPos+d))
				if(k==0)
				{
					flag=1;
					break;
				}
					currPos=pos[k-1]+d;
					switched++;
					if(k==n)
					{
					 if(currPos<l)
					{
						flag=1;
						break;
					}
					else
					{
						break;
					}
					}
					if(pos[k]>currPos+d)
					{
						flag=1;
						break;
					}
				//}
				
			}
			
			if(flag==0)
			{
			System.out.println("Case #"+t+": "+switched);
			}
			else
			{
				System.out.println("Case #"+t+": "+"impossible");	
			}
			br.readLine();
	}
}
}
