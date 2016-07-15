import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.StringBuilder;

public class Fractals {

	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
       
        for (int tc = 1; tc <= t; tc++) {
        	
        	StringBuilder sb = new StringBuilder("");
        	Map<String,String> hm=new HashMap<String,String>();
        	String inp[]=br.readLine().split(" ");
        	int n=Integer.valueOf(inp[0]);
        	int d=Integer.valueOf(inp[1]);
        	int a=Integer.valueOf(inp[2]);
        	String s=inp[3];
        	int j,k=0,l=0;
        	StringBuilder output = new StringBuilder("");
        	while(l<n)
        	{
        		inp=br.readLine().split("=>");
        		hm.put(inp[0], inp[1]);
        		l++;
        	}
        	hm.put("+","+");
        	hm.put("-","-");
        	for(j=1;j<=d;j++)
        	{
        		while(k<s.length())
        		{
        			output=output.append(hm.get(Character.valueOf(s.charAt(k)).toString()));
        			k++;
        		}
        		s=output.toString();
        		output = new StringBuilder("");
        		k=0;
        	}
        	double currX=0.0;
        	double currY=0.0;
        	char cur;
        	double pi=Math.PI/180;
        	int toR=0;
        	//System.out.println("Case #"+tc+":");
        	j=0;
        	//System.out.println(currX+" "+currY);
            sb.append("Case #");
            sb.append(tc);
            sb.append(":\n");
            sb.append(currX);
            sb.append(" ");
            sb.append(currY);
            sb.append("\n");
        	while(j<s.length())
        	{
        		cur=s.charAt(j);
        		if(cur!='+' && cur!='-')
        		{
        			currY=currY+Math.sin(toR*pi);
        			currX=currX+Math.cos(toR*pi);
        			//System.out.println(currX+" "+currY);
                    sb.append(currX);
                    sb.append(" ");
                    sb.append(currY);
                    sb.append("\n");
        		}
        		else
        		{
        			toR=cur == '+'?toR+a:toR-a;
        		}
        		j++;
        	}
        	System.out.print(sb.toString());
         	br.readLine();
        }
       
	}
}