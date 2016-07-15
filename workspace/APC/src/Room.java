import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Room {
	static class Array{
		long arr[][];
		
	}
	public static long checkLine(Array obj,long roomNo,int n)
	{
		long lineNo;
		int counter=0;
		int stationNo[]=new int[1000];
		for(int i=0;i<1000;i++)
		{
			if(roomNo>=obj.arr[i][0] && roomNo<=obj.arr[i][1])
			{
				stationNo[counter]=(int) obj.arr[i][2];
				counter++;
			}
		}
			lineNo=counter*(roomNo-1) +1;
			return lineNo;
	}
	public static void sortArray(int lb,int ub, int k,Array obj)
	{
		int boundsArray[][]= new int[1000][2];
		for(int j=lb,r=0;j<ub;j++,r++)
		{
			for(int l=lb+1;l<ub-r;l++)
			{ 
				if(obj.arr[l-1][k]>obj.arr[l][k])
				{
					long[] temp=obj.arr[l-1];
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
			if((k+1)<3)
			sortArray(boundsArray[i][0],boundsArray[i][1],k+2,obj);
		}	
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
	long[][] arr=new long[1000][3];
	long lineNos[] = new long[1000];
	long roomNo[]=new long[1000];
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	int noOfCases = Integer.parseInt(br.readLine());
	for(int i=1;i<=noOfCases;i++)
	{
		String input[]=br.readLine().split(" ");
		int noOfStations=Integer.valueOf(input[0]);
		int noOfFriends=Integer.valueOf(input[1]);
		for(int j=0;j<noOfStations;j++)
		{
			String roomNos[]=br.readLine().split(" ");
			arr[j][0]=Long.valueOf(roomNos[0]);
			arr[j][1]=Long.valueOf(roomNos[1]);
			arr[j][2]=j+1;
		}
		Array objArr=new Array();
		objArr.arr=arr;
		int lb=0,ub=noOfStations,r=0;
		sortArray(lb,ub,r,objArr);
		for(int l=0;l<noOfFriends;l++)
		{
			lineNos[l]=Long.valueOf(br.readLine());
		}
		long high=(long) (Math.pow(2, 31)-1);
		long low=0;
		long mid;
		for(int k=0;k<noOfFriends;k++)
		{
		while(low<high)
		{
			mid=(low+high)/2;
			if(lineNos[k]>checkLine(objArr,mid,noOfStations))
			{
				low=mid;
			}
			else if(lineNos[k]<checkLine(objArr,mid,noOfStations))
			{
				high=mid;
			}
			else if(lineNos[k] == checkLine(objArr,mid,noOfStations) )
			{
				roomNo[k]=mid;
				break;
			}
		}
	}
		System.out.println("Case #"+i+":");
		for(int m=0;m<noOfFriends;m++)
		{
			System.out.println(roomNo[m]);
		}
		br.readLine();
}
}
}


