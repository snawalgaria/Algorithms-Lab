import java.util.Scanner;
public class ProblemD {
	static double findDistance(double x1, double y1, double x2, double y2) {
		double dist = Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow(x2 - x1, 2));
		return dist;
	}
	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int counter = 1; counter <= t; counter++) {
			int n = sc.nextInt();
			double[][] points = new double[n + 1][2];
			for (int i = 1; i <= n; i++) {
				points[i][0] = sc.nextDouble();
				points[i][1] = sc.nextDouble();
			}
			double minDistance1 = Double.MAX_VALUE;
			double minDistance2 = Double.MAX_VALUE;
			for (int i = 2; i <= n; i++) {
				double temp = findDistance(points[1][0], points[1][1], points[i][0], points[i][1]);
				if (temp < minDistance1)
					minDistance1 = temp;

				if(i!=n){
				for(int j=i+1;j<=n;j++)
				{
					temp = findDistance(points[i][0], points[i][1], points[j][0], points[j][1]);
					if (temp < minDistance2)
						minDistance2 = temp;		
				}	
				}

			}
			double min = minDistance1 - (minDistance2 / 2.0);
			if (min < 0) 
				min = 0;
			double radius;
			if ((minDistance1 + min) / 2 > ((minDistance1 * (n - 1)) / n))
				radius = Math.pow((minDistance1 - 0.0000000000001), 2) + Math.pow(0.0000000000001, 2) * (n - 1);
			else
				radius = Math.pow(min, 2) + Math.pow((minDistance1 - min), 2) * (n - 1);
			System.out.println("Case #" + counter + ": " + Math.PI * radius);

		}
	}
}