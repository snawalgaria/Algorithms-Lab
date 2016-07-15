import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class LongestCommonSubsequence
{

	public static int lcs(String str1, String str2)
    {
        int l1 = str1.length();
        int l2 = str2.length();
 
        int[][] arr = new int[l1 + 1][l2 + 1];
 
        for (int i = l1 - 1; i >= 0; i--)
        {
            for (int j = l2 - 1; j >= 0; j--)
            {
                if (str1.charAt(i) == str2.charAt(j))
                    arr[i][j] = arr[i + 1][j + 1] + 1;
                else 
                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);
            }
        }
 
        int i = 0, j = 0;
        StringBuffer sb = new StringBuffer();
        while (i < l1 && j < l2) 
        {
            if (str1.charAt(i) == str2.charAt(j)) 
            {
                sb.append(str1.charAt(i));
                i++;
                j++;
            }
            else if (arr[i + 1][j] >= arr[i][j + 1]) 
                i++;
            else
                j++;
        }
        return sb.toString().length();
    }
	public static String rotate(String str2)
	{
		str2=str2.substring(str2.length()-1).concat(str2.substring(0, str2.length()-1));
		return str2;
	}
	public static void main(String[] args) throws IOException
    {    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("Longest Common Subsequence Algorithm Test\n");
        int cases=Integer.parseInt(br.readLine());
    	for(int t=1;t<=cases;t++)
    	{
        //System.out.println("\nEnter string 1");
        String str1 = br.readLine();
 
        //System.out.println("\nEnter string 2");
        String str2 = br.readLine();
 
       // LongestCommonSubsequence obj = new LongestCommonSubsequence(); 
        //String result = lcs(str1, str2);
        int max=0;
        
        for (int i=0; i<str2.length();i++)
        {
            int x = lcs(str1,str2);
            StringBuilder sb=new StringBuilder();
            sb.append(str2);
            int y = lcs(str1,sb.reverse().toString());
            max=Math.max(max, x);
            max=Math.max(max, y);
            str2=rotate(str2);
        }
        System.out.println("Case #"+t+": "+max);
		br.readLine();
}
    }
}

