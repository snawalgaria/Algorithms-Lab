import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calendar {
	
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) 
        {
        	String s=br.readLine();
        	Character c;
        	int x1,x0,mod;
        	if(s.length()==1)
        	{
        		mod=Integer.valueOf(s);
        		mod=mod%13;
        	}
        	else
        	{
        	Character z=s.charAt(0);
        	mod=Integer.valueOf(z.toString());
        	for(int i=1;i<s.length();i++)
        	{
        		c=s.charAt(i);
        		x0=Integer.valueOf(c.toString());
        		mod=((mod*8)+x0)%13;
        	}
        	}
        	mod=(mod+3)%13;
        	mod=mod == 0?13:mod;
        	System.out.println("Case #"+tc+": "+mod);
        }
	}
}
