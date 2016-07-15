import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

public class Tour {
	
	static boolean nextPermutation(int[] array) {
	    // Find longest non-increasing suffix
	    int i = array.length - 1;
	    while (i > 0 && array[i - 1] >= array[i])
	        i--;
	    // Now i is the head index of the suffix
	    
	    // Are we at the last permutation already?
	    if (i <= 0)
	        return false;
	    
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
	    return true;
	}

	public static void main(String args[]) throws NumberFormatException, IOException
	{
		
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int cases=Integer.parseInt(br.readLine());
	for(int i=1;i<=cases;i++)
	{
		int pathSum=100000;
		int path=0;
		int j,k=1,l,r;
		int totalChap=0;
		int n=Integer.parseInt(br.readLine());
		int array[]=new int[n-1];
		int shortestPath[]=new int[n-1];
		int adjMat[][]=new int[n][n];
		for(j=0;j<n;j++)
		{
			String input[]=br.readLine().split(" ");
			for(k=0;k<n;k++)
			{
			adjMat[j][k]=Integer.parseInt(input[k]);
			}
		}
		if(n>1)
		{
			for(l=0;l<n-1;l++)
				array[l]=l+1;
		}
		do {  // Must start at lowest permutation
			path=adjMat[0][array[0]];
		    for(r=0;r<n-2;r++)
		    {
		    	path=path+adjMat[array[r]][array[r+1]];
		    }
		    path=path+adjMat[array[n-2]][0];
		    if(path<pathSum)
		    {
		    	pathSum=path;
		    	shortestPath=array.clone();
		    }
		} while (nextPermutation(array));
		String shortest="1 ";
		for(k=0;k<n-1;k++)
		{
			Integer t=shortestPath[k]+1;
			shortest=shortest.concat(t.toString()+" ");
		}
		System.out.println("Case #"+i+": "+shortest);
		br.readLine();

		
}
	}
}
