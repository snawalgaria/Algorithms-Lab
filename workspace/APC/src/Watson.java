import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Watson {
	static class Result{
		long data;
		
	}
	public static void operation(Result res,String operator,int op,String restOfString)
	{
		switch(operator)
		{
		case "plus":
			res.data=res.data+op;
			break;
		case "minus":
			res.data=res.data-op;
			break;
		case "times":
			res.data=res.data*op;
			break;
		case "tothepowerof":
			res.data=(int) Math.pow(res.data,op);
			break;
		}
		if(restOfString!= "")
		{		
				int k=0;
				while(restOfString.charAt(k)<48 || restOfString.charAt(k)>57)
				{
					k++;
				}
				int endPos=k;
				while(restOfString.charAt(k)>=48 && restOfString.charAt(k)<58)
				{
					k++;
					if(k==restOfString.length())
						break;
				}
				int endPos2=k;
				operator=restOfString.substring(0,endPos);
				op=Integer.valueOf(restOfString.substring(endPos,endPos2));
				
				restOfString=endPos2!=restOfString.length()?restOfString.substring(endPos2):"";
				operation(res,operator,op,restOfString);
			}
		}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Result res=new Result();
		int noOfCases= Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			String input= br.readLine();
			int length=input.length();
			try
			{
			if(Integer.valueOf(input) != null)
			{
				res.data=Integer.valueOf(input);
			}
			}
			catch(Exception exe)
			{
					int k=0;
					while( input.charAt(k)>=48 && input.charAt(k)<58)
					{
						k++;
					}
					int startPos=k;
					while(input.charAt(k)<48 || input.charAt(k)>57)
					{
						k++;
					}
					int endPos=k;
					while(input.charAt(k)>=48 && input.charAt(k)<58)
					{
						k++;
						if(k==length)
							break;
					}
					int endPos2=k;
					res.data=Integer.valueOf(input.substring(0, startPos));
					String operator=input.substring(startPos,endPos);
					int op=Integer.valueOf(input.substring(endPos,endPos2));
					
					String restOfString=endPos2!=length?input.substring(endPos2):"";
					operation(res,operator,op,restOfString);
				}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(res.data);
			System.out.println(sb);
		}
	}
}
	
	
