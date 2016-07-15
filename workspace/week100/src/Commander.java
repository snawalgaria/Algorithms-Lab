import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Commander {
	private static Integer gcdTwo(Integer a, Integer b)
	{
	    while (b > 0)
	    {
	        Integer temp = b;
	        b = a % b;
	        a = temp;
	    }
	    return a;
	}
	private static Integer gcd(Integer[] input)
	{
	    Integer result = input[0];
	    
	    for(int i = 1; i < input.length; i++) 
	    {
	    result = gcdTwo(result, input[i]);
	    }
	    return result;
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int cases=Integer.parseInt(br.readLine());
	for(int t=1;t<=cases;t++)
	{
		int n,i,j,k;
		n=Integer.parseInt(br.readLine());
		String inp[]=br.readLine().split(" ");
		Integer arr[]=new Integer[n];
		for(i=0;i<n;i++)
		{
			arr[i]=Integer.parseInt(inp[i]);
		}
		Integer result=gcd(arr);
		System.out.println("Case #"+t+": "+result);
		br.readLine();
		
		
	}
}
}
