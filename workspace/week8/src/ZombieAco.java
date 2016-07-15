//code referenced from http://introcs.cs.princeton.edu/java/96optimization/Knapsack.java.html
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ZombieAco {

    public static void main(String args[])throws IOException
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
            int weight[]=new int[n+1];
            int calories[]=new int[n+1];
            int maxPacks[]=new int[n+1];
            int packets=0;
            for(j=1;j<=n;j++)
            {
            	input=br.readLine().split(" ");
            	maxPacks[j]=Integer.parseInt(input[0]);
                packets+=maxPacks[j];
                weight[j]=Integer.parseInt(input[1]);
                calories[j]=Integer.parseInt(input[2]);
            }
            int[]finalWeights=new int[packets+1];
            int[]finalCalories=new int[packets+1];
            int[]id=new int[packets+1];

            l=1;
            for(j=1;j<=n;j++)
            {
              for(int count=1;count<=maxPacks[j];count++)
                {
                    finalWeights[l]=weight[j];
                    finalCalories[l]=calories[j];
                    id[l]=j;
                    l++;
                }
            }
   
            String output=findPackets(finalWeights,finalCalories,id,w,packets);
            System.out.println("Case #"+t+": "+output);
            br.readLine();
        }
    }
    //code framework from http://introcs.cs.princeton.edu/java/96optimization/Knapsack.java.html
    public static String findPackets(int weight[],int cal[],int id[],int W,int N)
    {
        int[][] opt = new int[N+1][W+1];
        boolean[][] sol = new boolean[N+1][W+1];

        for (int n = 1; n <= N; n++) {
            for (int w = 1; w <= W; w++) {

                // don't take item n
                int option1 = opt[n-1][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (weight[n] <= w) option2 = cal[n] + opt[n-1][w-weight[n]];

                // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }

        // determine which items to take
        boolean[] take = new boolean[N+1];
        for (int n = N, w = W; n > 0; n--) {
            if (sol[n][w]) { take[n] = true;  w = w - weight[n]; }
            else           { take[n] = false;                    }
        }

        String output="";
        for (int n = 1; n <= N; n++) 
        {
            if(take[n]==true)
            {
            	output=output+ " "+id[n];
            }
        
        }
        
        return output;
    }
}