import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Story {

	static Vector<Integer> chaps;
	static Vector<Integer>	ind;
	static Vector<Integer> charChaps;
static Integer backtrack(Vector<Integer> config,Vector<Boolean> left,Vector<Vector<Integer>> adj)
{
	if(config.size() == left.size()){
		return 1;
	}
	int sum=0;
	boolean allClear=true;
	for (int i=0; i<left.size(); ++i) {
        if (left.get(i) && (adj.get(i).size() != 0 || (config.size() > 0 && charChaps.get(i) == charChaps.get(config.get(config.size()-1))))) {
            allClear = false;
            break;
        }
    }
    if (allClear) {
        sum = 1;
        for (int i=1; i<=left.size()-config.size(); ++i) {
            sum *= i;
        }
        return sum;
    }
    for (int i=0; i<left.size(); ++i) {
    	if (left.get(i) || (adj.get(i).size() != 0 || (config.size() > 0 && charChaps.get(i) == charChaps.get(config.get(config.size()-1))))) continue;
        Vector<Vector<Integer>> newAdj = adj;
        config.add(i);
        left.set(i, false);
        for (int j=0; j<newAdj.size(); ++j) {
        	for(int k=0;k<newAdj.get(j).size();k++)
        	{
        		if(newAdj.get(j).get(k)== i)
        			continue;
        		newAdj.get(j).remove(k);
        	}
            //newAdj.get(j).erase(remove(newAdj.get(j).firstElement(), newAdj.get(j).lastElement(), i), newAdj.get(j).lastElement());
        }
        sum += backtrack(config, left, newAdj);
        config.remove(config.get(config.size()-1));
        left.set(i,true);
    }
    return sum;
}
public static void main(String args[]) throws IOException
{

	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int cases=Integer.parseInt(br.readLine());
	for(int t=1;t<=cases;t++)
	{
	
        String input[]=br.readLine().split(" ");
		int n=Integer.valueOf(input[0]);
		int m=Integer.valueOf(input[1]);
        chaps = new Vector<Integer>(n);
        ind = new Vector<Integer>(n);
        charChaps = new Vector<Integer>();
        int sumOfChaps = 0;
        for (int i=0; i<n; ++i) {
            //cin >> chaps[i];
        chaps.add(Integer.parseInt(br.readLine()));
            ind.add(sumOfChaps);
            sumOfChaps += chaps.get(i);
            for (int j=0; j<chaps.get(i); ++j) {
                charChaps.add(i);
            }
        }
        Vector<Vector<Integer>> adj=new Vector<Vector<Integer>>(sumOfChaps);
        for (int i=0; i<n; ++i) {
            for (int j=1; j<chaps.get(i); ++j) {
                for (int k=0; k<j; ++k) {
                    adj.get(ind.get(i)+j).add(ind.get(i)+k);
                }
            }
        }
        for (int i=0; i<m; ++i) {
            int c,p,d,q;
         
		String depen[]=br.readLine().split(" ");
		c=Integer.parseInt(depen[0]);
		p=Integer.parseInt(depen[1]);
		d=Integer.parseInt(depen[2]);
		q=Integer.parseInt(depen[3]);
            adj.get(ind.get(d-1)+q-1).add(ind.get(c-1)+p-1);
        }
        
        int sum = backtrack(new Vector<Integer>(), new Vector<Boolean>(sumOfChaps), adj);
        
        
        System.out.println("Case #"+t+": "+sum);
		br.readLine();
    }
}
}


