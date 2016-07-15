import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class StoryTime {

	static boolean nextPermutation(Integer[] array) {
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
		int j,k=1,l,r;
		int totalChap=0;
		ArrayList<Vector> arrVec=new ArrayList<Vector>();
		String input[]=br.readLine().split(" ");
		int n=Integer.valueOf(input[0]);
		int m=Integer.valueOf(input[1]);
		for(j=1;j<=n;j++)
		{
			r=Integer.parseInt(br.readLine());
			totalChap=totalChap+r;
			Vector<Integer> ch=new Vector<Integer>();
			for(l=1;l<=r;l++)
			{
				ch.add(k);
				k++;
			}
			arrVec.add(ch);
		}
		Integer array[]=new Integer[totalChap];
		int p=0,q=0;
		for(l=0;l<totalChap;l++)
		{
			if(p==arrVec.size())
			{
			p=0;q++;	
			}
			if(q==arrVec.get(p).size())
			{
				q=0;
				p++;
			}
			array[l]=(Integer) arrVec.get(p).get(q);
			p++;
			
		}
		
		int count=0;
		String depen[];
		int dep[][]=new int[n][4];int flag=0;int tflag=0;
		int constraint1[]=new int[m];int constraint2[]=new int[m];
		for(k=0;k<m;k++)
		{
			depen=br.readLine().split(" ");
			dep[k][0]=Integer.parseInt(depen[0]);
			dep[k][1]=Integer.parseInt(depen[1]);
			dep[k][2]=Integer.parseInt(depen[2]);
			dep[k][3]=Integer.parseInt(depen[3]);
		}
		do {  // Must start at lowest permutation
			flag=0;tflag=0;
			for(k=0;k<m;k++)
			{
				constraint1[k]=(int) arrVec.get(dep[k][0]-1).get(dep[k][1]-1);
				constraint2[k]=(int)arrVec.get(dep[k][2]-1).get(dep[k][3]-1);
				if(Arrays.asList(array).indexOf(constraint1[k])>Arrays.asList(array).indexOf(constraint2[k]))
						{
							flag=1;
							break;
						}
			}
			for(k=0;k<arrVec.size();k++)
			{
				for(l=0;l<arrVec.get(k).size()-1;l++)
				{
					if(Arrays.asList(array).indexOf(arrVec.get(k).get(l))>Arrays.asList(array).indexOf(arrVec.get(k).get(l+1)))
						{
							tflag=1;
							break;
						}
					for(j=l+1;j<arrVec.get(k).size();j++)
					{
					if(Math.abs(Arrays.asList(array).indexOf(arrVec.get(k).get(l))-Arrays.asList(array).indexOf(arrVec.get(k).get(j)))==1)
					{
						tflag=1;
						break;
					}
				}
					if(tflag == 1)
						break;
				}
				if(tflag == 1)
					break;
			}
			if(flag ==0 && tflag == 0)
				count++;
			
			
		} while (nextPermutation(array));
		
		System.out.println("Case #"+i+": "+count);
		br.readLine();
		
}
}
}
