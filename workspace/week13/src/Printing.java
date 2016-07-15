
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Printing {
    static int[]parent;
    static boolean[]visited;
    static int[][]graph;
    public static void main (String[] args) throws java.lang.Exception
    {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int noOfCases = Integer.parseInt(br.readLine());

        for(int j=1;j<=noOfCases;j++)
        {
            int n,m;
            String typ;
            int f,siz;
            int from,to;
            String input[]=br.readLine().split(" ");
            String inp[]=null;
            n=Integer.valueOf(input[0]);
            m=Integer.valueOf(input[1]);
           // Printing x=new Printing();

            graph=new int[n+m+2][n+m+2];
           // gr warrior=x.new Graph(n+m+2);
            for(int i=1;i<=n;i++)
            {
               // warrior.addEdge(0, i, 1);
                graph[0][i]=1;
            }
            for(int i=1;i<=n;i++) //data for warriors
            {
                inp=br.readLine().split(",");
                for(int k=0;k<inp.length;k++)
                {
                    try
                    {
                      //  warrior.addEdge(i,n+Integer.parseInt(inp[k]),1);
                        graph[i][n+Integer.parseInt(inp[k])]=1;
                    }
                    catch(Exception exe)
                    {
                        String fromTo[]=inp[k].split("-");
                        from=Integer.parseInt(fromTo[0]);
                        to=Integer.parseInt(fromTo[1]);
                        for(int l=from;l<=to;l++)
                        {
                            //warrior.addEdge(i, n+l, 1);
                            graph[i][n+l]=1;
                        }

                    }
                }
            }

            for(int k=n+1;k<=n+m;k++)
            {
                //warrior.addEdge(k,n+m+1,1);
                graph[k][n+m+1]=1;
            }
            maxFlow(0,n+m+2,j,m);


            br.readLine();
        }

    }


    public static void maxFlow(int source,int noOfVertices,int c,int mm)
    {
        int target=noOfVertices-1;
        int maximumFlow=0;
        int pathFlow;
        int maxpathFLow=0;

        while(bfs(source,target,noOfVertices))
        {
            pathFlow=Integer.MAX_VALUE;

            for(int i=target;i!=source;i=parent[i])
            {
                int j=parent[i];
                pathFlow=Math.min(pathFlow,graph[j][i]);

            }
            //int startingVertex=target;
            for(int i=target;i!=source;i=parent[i])
            {
                int first=parent[i];
                graph[first][i]-=pathFlow;
                graph[i][first]+=pathFlow;

            }

            // if(pathFlow>maxpathFLow)
            //   maxpathFLow=pathFlow;
            maximumFlow+=pathFlow;
            //System.out.println("herebfs"+pathFlow);


        }
        // System.out.println(maximumFlow+"ll");

        //maximumFlow=maximumFlow/3;
        if(maximumFlow==mm)
            System.out.println("Case #"+c+": "+"yes");
        else
            System.out.println("Case #"+c+": "+"no");

    }
    public static boolean bfs(int source,int target,int noOfVertices){

        boolean pathExists=false;
        parent=new int[noOfVertices];
        Arrays.fill(parent, -1);
        visited=new boolean[noOfVertices];
        Arrays.fill(visited, false);

        Queue<Integer> queue=new LinkedList<Integer>();
        queue.add(source);

        int current;
        //int endpoint;
        while(!queue.isEmpty())
        {

            current=queue.poll();

            for(int i=0;i<noOfVertices;i++)
            {
                if(graph[current][i]>0 && !visited[i])
                {
                    parent[i]=current;
                    queue.add(i);
                    visited[i]=true;
                }
            }
            // System.out.println("hereq");

        }
        if(visited[target]==true)
            return true;
        else
            return false;

    }

}




