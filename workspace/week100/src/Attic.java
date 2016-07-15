import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
public class Attic {
	
	static int[] nextPermutation(int[] array) {
	    // Find longest non-increasing suffix
	    int i = array.length - 1;
	    while (i > 0 && array[i - 1] >= array[i])
	        i--;
	    // Now i is the head index of the suffix
	    
	    // Are we at the last permutation already?
	    if (i <= 0)
	        return null;
	    
	    // Let array[i - 1] be the pivot
	    // Find rightmost element that exceeds the pivot
	    int j = array.length - 1;
	    while (array[j] <= array[i - 1])
	        j--;
	    // Now the value array[j] will become the new pivot
	    // Assertion: j >= i
	    
	    // Swap the pivot with j
	    int temp = array[i - 1];
	    array[i - 1] = array[j];
	    array[j] = temp;
	    
	    // Reverse the suffix
	    j = array.length - 1;
	    while (i < j) {
	        temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	        i++;
	        j--;
	    }
	    // Successfully computed the next permutation
	    return array;
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		int a,b;
		boolean pi[]=new boolean[100000000];
		
		for(a=2;a<100000000;a++)
		{
			pi[a]=true;
		}
		
		for(a=1;a<100000000;a++)
		{
		if(pi[a] == true)
		{
			for(b=2*a;b<100000000;b=b+a)
			{
				pi[b]=false;
			}
		}
		}	
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int cases = Integer.parseInt(br.readLine());
	for(int t=1;t<=cases;t++)
	{
		
		int i,j,l,p;
		Integer k;
		k=Integer.parseInt(br.readLine());
		Integer x=k+1;
		int len=k.toString().length();
		int output = 0;
		
		int sum;
		Double logX;
		int flag=0;
		while(true)
		{
			int[] iarray = new int[len];
			if(x.toString().length() != len)
			{
				break;
			}
			if(pi[x]!=true)
			{
				x++;
				continue;
			}
			p=x;
			for (int index = len-1; index >=0; index--) {
			    iarray[index] = p % 10;
			    p /= 10;
			}
			logX=Math.log10(x);
			int count=0;
			Arrays.sort(iarray);
			flag=0;
		while(true)
		{
			if(count > logX.intValue())
			{
				break;
			}
			sum = 0;l=1;
			for (int index = len-1; index >=0; index--) {
			    sum = sum + (l*iarray[index]); 
			    l=l*10;
			}
			if(pi[sum] == true)
			{
				count++;
			}
			else
			{
				flag=1;
			}
			iarray=nextPermutation(iarray);
			if(iarray == null)
			{
				break;
			}
			
		}
		if(count>logX.intValue())
		{
			output=x;
			break;
		}
		if(iarray == null && flag == 0)
		{
			output=x;
			break;
		}
		x++;
	
	}
		System.out.println("Case #"+t+": "+output);
		
}
}
}
