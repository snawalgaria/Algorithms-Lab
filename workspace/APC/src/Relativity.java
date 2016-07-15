import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Relativity {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		long c=299792458;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		if(noOfCases<1 || noOfCases>1000)
		{
			System.out.println("The Value Should be between 1 and 1000");
		}
		else
		{
		for(int i=1;i<=noOfCases;i++)
		{
		int m=Integer.parseInt(br.readLine());
		if(m>=1 && m<=100)
		{
			long E=m*c*c;
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(E);
			System.out.println(sb);
		}
		}
			
		}
		}


}
