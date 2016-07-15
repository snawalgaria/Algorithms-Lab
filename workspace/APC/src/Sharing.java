import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sharing {

	public static int getPartitions(long[] arr, long maxPartvalue) {
		  long sum = 0;
		  int numPartitions = 1;
		  for (int i = 0; i < arr.length; i++) {
		    sum =sum+ arr[i];
		    if (sum > maxPartvalue) {
		      sum = arr[i];
		      numPartitions++;
		    }//if ends
		  } // for ends
		  return numPartitions;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases= Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			String input[]=br.readLine().split(" ");
			int n=Integer.valueOf(input[0]);
			long p=Integer.valueOf(input[1]);
			long q= Integer.valueOf(input[2]);
			long r = Integer.valueOf(input[3]);
			long s = Integer.valueOf(input[4]);
			long arr[] = new long[n];
			long maxElement=0;
			long arraySum=0;
			for(int j=1;j<=n;j++)
			{
				arr[j-1]=(((j*p)+q)%r)+s;
				if(arr[j-1]>maxElement)
				{
					maxElement=arr[j-1];
				}
				arraySum=arraySum+arr[j-1];
			}
			long low=maxElement;
			long high=arraySum;
			while(low<high)
			{
				long mid= (low+high)/2;
				int partitions=getPartitions(arr,mid);
				if(partitions>3)
				{
					low=mid+1;
				}
				else
				{
					high=mid;
				}
			}
			String output=Long.valueOf(arraySum-low).toString();
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(output);
			System.out.println(sb);
		   }
		}
			
	}

