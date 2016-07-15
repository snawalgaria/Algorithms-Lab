import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Soccer {

	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int cases=Integer.parseInt(br.readLine());
		for(int t=1;t<=cases;t++)
		{
			String players[]=new String[11];
			int totalPossible=1;
			int gCount=0;
			for(int i=0;i<11;i++)
			{
				players[i]=br.readLine();
				totalPossible=totalPossible*players[i].length();
			}
			int a,b,c,d,e,f,g,h,i,j,k;
			Character combinat[]=new Character[11];
			boolean reject=false;
			int count=0;
			for(a=0;a<players[0].length();a++)
			{
				//combination.append(players[0].charAt(a));
				combinat[0]=players[0].charAt(a);
			for(b=0;b<players[1].length();b++)
			{
				//combination.append(players[1].charAt(b));
				combinat[1]=players[1].charAt(b);
				for(c=0;c<players[2].length();c++)
				{
					//combination.append(players[2].charAt(c));
					combinat[2]=players[2].charAt(c);
					for(d=0;d<players[3].length();d++)
					{
						//combination.append(players[3].charAt(d));
						combinat[3]=players[3].charAt(d);
						for(e=0;e<players[4].length();e++)
						{
							//combination.append(players[4].charAt(e));
							combinat[4]=players[4].charAt(e);
							for(f=0;f<players[5].length();f++)
							{
								//combination.append(players[5].charAt(f));
								combinat[5]=players[5].charAt(f);
								for(g=0;g<players[6].length();g++)
								{
									//combination.append(players[6].charAt(g));
									combinat[6]=players[6].charAt(g);
									for(h=0;h<players[7].length();h++)
									{
										//combination.append(players[7].charAt(h));
										combinat[7]=players[7].charAt(h);
										for(i=0;i<players[8].length();i++)
										{
											//combination.append(players[8].charAt(i));
											combinat[8]=players[8].charAt(i);
											for(j=0;j<players[9].length();j++)
											{
												//combination.append(players[9].charAt(j));
												combinat[9]=players[9].charAt(j);
												for(k=0;k<players[10].length();k++)
												{
													//combination.append(players[10].charAt(k));
													combinat[10]=players[10].charAt(k);
													reject=false;
													gCount=0;
													//combination.setLength(0);
													if(combinat[1] == combinat[10] || combinat[2] == combinat[9] || combinat[3] == combinat[8] || combinat[4] == combinat[7] || combinat[5]== combinat[6])
													{
														reject=true;
														continue;
													}
													for(int p=0;p<11;p++)
														if(combinat[p]== 'G')
															gCount++;
													
													if(gCount>1 || gCount == 0)
													{
														reject=true;
														continue;
													}
													if(reject == false)
														count++;
															
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			
		}
			System.out.println("Case #"+t+": "+count);
			br.readLine();
		
	}
}
}
