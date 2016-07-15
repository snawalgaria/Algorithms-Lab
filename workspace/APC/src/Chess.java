import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chess {

	static class Array{
		int arr[][];
		
	}
	public static void sortArray(int lb,int ub, int k,Array obj)
	{
		int boundsArray[][]= new int[1000][2];
		for(int j=lb,r=0;j<ub;j++,r++)
		{
			for(int l=lb+1;l<ub-r;l++)
			{ 
				if(obj.arr[l-1][k]<obj.arr[l][k])
				{
					int temp[]=obj.arr[l-1];
					obj.arr[l-1]=obj.arr[l];
					obj.arr[l]=temp;
				}
			}
		}
		int row=-1;
		int newlb;
		for(int i=lb;i<(ub-1);i++)
		{   
			if(obj.arr[i][k]!=obj.arr[i+1][k])
			{
				continue;
			}
			newlb=i;
			while(obj.arr[i][k]== obj.arr[i+1][k])
			{
				i++;
				if(i == (ub-1))
				{
					break;
				}
			}
			row++;
			boundsArray[row][0]=newlb;
			boundsArray[row][1]=i+1;
		}
		for(int i=0;i<=row;i++)
		{
			if((k+1)<5)
			sortArray(boundsArray[i][0],boundsArray[i][1],k+1,obj);
		}	
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stubx
		Array obj=new Array();
		int arr[][]=new int[1000][5];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
		//System.out.println("Case #"+i+":");
		int n=Integer.parseInt(br.readLine());
	
			for(int j=0;j<n;j++)
			{
				String arra=br.readLine();
				String ar[]=arra.split(" ");
				for(int k=0;k<ar.length;k++)
				{
					arr[j][k]=Integer.valueOf(ar[k]);
				}
			}
			int temp;
			for(int j=0;j<n;j++)
			{
				for(int k=0;k<5;k++)
				{
					for(int l=1;l<5-k;l++)
					{
						if(arr[j][l-1]<arr[j][l])
						{
							temp=arr[j][l-1];
							arr[j][l-1]=arr[j][l];
							arr[j][l]=temp;
						}
					}
				}
			}
			obj.arr=arr;
			int lb=0,ub=n,k=0;
			sortArray(lb,ub,k,obj);
			
		System.out.println("Case #"+i+":");
		for(int j=0;j<n;j++)
		{
			for(k=0;k<5;k++)
			{
				System.out.print(obj.arr[j][k]+" ");
			}
			System.out.println();
		}
		br.readLine();
		}
	}
}

			