import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MakingChange {

	static ArrayList<Integer> coins;
	static int freq[];
	static int usedCoin[];
	static int table[];
	MakingChange(ArrayList<Integer>coins,Integer l,int c)
	{
		this.coins=coins;
		this.freq=new int[l];
		this.usedCoin=new int[c+1];
		this.table=new int[c+1];
		for(int i=0;i<c;i++)
			table[i]=-1;
	}
	static int coinsCal(int c)
	{
		if(c<0) return -1;
		if(c==0)
		{
			return 0;
		}
		if(table[c]!=-1)
			return table[c];
		int ans=-1;
		for(int i=0;i<coins.size();++i)
		{
			if(ans>coinsCal(c-coins.get(i)))
			{
				ans=coinsCal(c-coins.get(i));
				usedCoin[c]=coins.get(i);
			}
		}
		table[c]=ans+1;
		return table[c];
	}
	public static int[] minChange(int[] denom, int changeAmount)
    {
        int n = denom.length;
        int[] count = new int[changeAmount + 1];
        int[] from = new int[changeAmount + 1];

        count[0] = 1;
        for (int i = 0 ; i < changeAmount; i++)
            if (count[i] > 0)
                for (int j = 0; j < n; j++)
                {
                    int p = i + denom[j];
                    if (p <= changeAmount)
                    {
                        if (count[p] == 0 || count[p] > count[i] + 1)
                        {
                            count[p] = count[i] + 1;
                            from[p] = j;
                        }
                    }
                }

        // No solutions:
        if (count[changeAmount] == 0)
            return null;

        // Build answer.
        int[] result = new int[count[changeAmount] - 1];
        int k = changeAmount;
        while (k > 0)
        {
            result[count[k] - 2] = denom[from[k]];
            k = k - denom[from[k]];
        }

        return result;
    }
	public static void main(String args[]) throws NumberFormatException, IOException
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int cases=Integer.parseInt(br.readLine());
	for(int t=1;t<=cases;t++)
	{
		
		String input[]=br.readLine().split(" ");
		int n=Integer.parseInt(input[0]);
		int c=Integer.parseInt(input[1]);
		String coin[]=br.readLine().split(" ");
		int coinsd[]=new int[n];
		ArrayList<Integer>coins=new ArrayList<Integer>();
		int i,j,k,l;
		for(i=0;i<coin.length;i++)
		{
			coinsd[i]=(Integer.valueOf(coin[i]));
			coins.add(Integer.valueOf(coin[i]));
		}
		//MakingChange m = new MakingChange(coinsd,coin.length,c);
			//coinsCal(c);
		freq=minChange(coinsd,c);
		/*for(k=c;k>=0;k=k-usedCoin[k])
		{
			if(k==0)
				break;
			int index=coins.indexOf(usedCoin[k]);
			freq[index]++;
		}*/
		int count[]=new int[n];
		for(i=0;i<freq.length;i++)
		{
			int index=coins.indexOf(freq[i]);
			count[index]++;
		}
		String output="";
		for(i=0;i<coin.length;i++)
		{
			output=output+ " "+count[i];
		}
		System.out.println("Case #"+t+": "+output);
		br.readLine();
	}
}
}
