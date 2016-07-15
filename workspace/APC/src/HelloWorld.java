import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloWorld {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases= Integer.parseInt(br.readLine());
		/*if(noOfCases<1 || noOfCases>20)
		{
			System.out.println("The Value Should be between 1 and 20");
		}
		else
		{*/
		for(int i=1;i<=noOfCases;i++)
		{
		String name=br.readLine();
		/*if(name!="" && name.length()<=100)
		{*/
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": Hello ");
			sb.append(name);
			sb.append("!");
			System.out.println(sb);
		
		}			
		}


}
