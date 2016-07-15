import java.util.*;
import java.lang.*;
import java.io.*;

class Cabled
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		int count = 1;
		
		while (count <= t) {
			double d = (double) scanner.nextInt();
			int poles = scanner.nextInt();
			double canyonStart = (double)scanner.nextInt();
			double canyonEnd = (double)scanner.nextInt();
			
			double low = 0;
			double high = d;
			
			

			while( high-low > 0.0001 ){
				
				double mid = (low + high)/ 2 ;
				
							//System.out.println("Value of mid is "+ mid);
				
				if( checkPossibility( mid, d, canyonStart, canyonEnd, poles) ) {
		        				//System.out.println("Function returned  true for "+ mid);
					low = mid;
				}else {
		        				//System.out.println("Function returned  false for "+ mid);
					high = mid;
				}
			}

			System.out.printf("Case #"+ count +": %.11f\n", low);


			
			count++;
		}
	}

	public static boolean checkPossibility(double distance, double d, double start, double end, int poles){

		double i = 0;
		int numPoles = 0;

		while( i <= d ){
			
			if( (i + distance) > start && (i + distance) < end ){
				i = end;
				numPoles++;
			}else {

				numPoles++;
				i = i + distance;
				
			}	
		}

		return numPoles >= poles;

	}
}
