import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scrambled {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases= Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			String input= br.readLine();
			String split1[]=input.split("#");
			int x=Integer.valueOf(split1[0]);
			String s2=split1[1].substring(0, x);
			String s1=split1[1].substring(x);
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(s1.concat(s2));
			System.out.println(sb);
		}
	}
}
