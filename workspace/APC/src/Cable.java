import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cable {

	public static boolean checkPost(double distance,double length, int poles,double start,double end)
	{
		int num=0;
		for(double i=0;i<=length;)
		{
			if((i+distance)>start && (i+distance)<end ){
				i=end;
				num++;
			}else {

				num++;
				i=i+distance;
				
			}	
		}
		if(num>=poles)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases= Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			String input[]= br.readLine().split(" ");
			Double length=Double.valueOf(input[0]);
			int poles = Integer.valueOf(input[1]);
			Double start=Double.valueOf(input[2]);
			Double end=Double.valueOf(input[3]);
			
			double low=0;
			double high=length.doubleValue();
			while(high-low>0.0001)
			{
				double mid=(low+high)/2;
				if(checkPost(mid,length,poles,start.doubleValue(),end.doubleValue()))
				{
					low=mid;
				}
				else
				{
					high=mid;
				}
				
			}
			System.out.printf("Case #"+ i +": %.10f\n", low);
		}
	}
			
}
