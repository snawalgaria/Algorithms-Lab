
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class pickUpSticks{

	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			int k,l,m;
			String input[]=br.readLine().split(" ");
			int vertices=Integer.valueOf(input[0]);
			int pickedUp[]=new int[vertices+1];
			int paths=Integer.valueOf(input[1]);
			int adjMat[][]=new int[vertices+1][vertices+1];
			String points[]=br.readLine().split(" ");
			int sumPoints=0;
			for(int j=0;j<paths;j++)
			{
				String pair[]=br.readLine().split(" ");
				int topVertice=Integer.valueOf(pair[0]);
				int bottomVertice=Integer.valueOf(pair[1]);
				adjMat[bottomVertice][topVertice]=1;
			}
			while(true)
			{
			int counter=0;	
			for(k=1;k<=vertices;k++)
			{
				int flag=0;
				if(pickedUp[k]==1)
					continue;
				for(l=1;l<=vertices;l++)
				{
					if(adjMat[k][l]!=0)
					{
						flag=1;
						break;
					
					}
				}
					if(flag == 0)
					{
						if(pickedUp[k]!=1)
						{
						pickedUp[k]=1;
						counter=1;
						sumPoints=sumPoints+Integer.valueOf(points[k-1]);
						for(m=1;m<=vertices;m++)
							adjMat[m][k]=0;
						}
					}
		}
			if(counter == 0)
				break;
	}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(sumPoints);
			System.out.println(sb);
			br.readLine();
	}
}
}