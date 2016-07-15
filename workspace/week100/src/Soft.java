
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//Solution taken from http://www.geeksforgeeks.org/compute-ncr-p-set-2-lucas-theorem/
// https://github.com/GregOwen/Chinese-Remainder-Theorem/blob/master/CRT.java
// http://fishi.devtail.com/weblog/2015/06/25/computing-large-binomial-coefficients-modulo-prime-non-prime/

public class Soft {

    public static void main(String[] args) throws IOException {
    	
    	int[] mods = {2, 3, 5, 7, 11, 13, 17, 19, 23};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= cases; t++) 
        
        {	
          String[] inp = br.readLine().split(" ");
          int n = Integer.parseInt(inp[0]);
          int r = Integer.parseInt(inp[1]);
          int[] constraints = new int[9];
          Long x = Long.parseUnsignedLong("17916881237904312345");
          x=(long) 0;
          int M = 1;
          for(int i=0;i<9;i++)
          {
                constraints[i] = nCrModPLucas(n,r,mods[i]);
                M*=mods[i];
          }

          int[] multInv = new int[constraints.length];

         
           
            for(int i=0;i<multInv.length;i++)
            {
                multInv[i]=euclidean(M/mods[i],mods[i])[0];
            }

            for(int i=0;i<mods.length;i++)
            {
                x+=M/mods[i]*constraints[i]*multInv[i];
            }

            x=leastPosEquiv(x,M);
            String l1Str = Long.toUnsignedString(x);
            System.out.println("Case #"+t+": "+l1Str);


        }
    }
//http://www.geeksforgeeks.org/compute-ncr-p-set-2-lucas-theorem/
    public static int nCrModpDP(int n,int r, int p) {
        int[] c = new int[r + 1];

        c[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, r); j > 0; j--)
                c[j] = (c[j] + c[j - 1]) % p;

        }
        return c[r];
    }

    public static int nCrModPLucas(int n, int r, int p){
        if(r==0)
            return 1;

        int ni=n%p, ri = r%p;

        return (nCrModPLucas(n/p,r/p,p)*nCrModpDP(ni,ri,p))%p;
    }
    //https://github.com/GregOwen/Chinese-Remainder-Theorem/blob/master/CRT.java
    public static int[] euclidean(int a, int b)
    {
        if(b > a)
        {
            int[] coeffs = euclidean(b, a);
            int[] output = {coeffs[1], coeffs[0]};
            return output;
        }

        int q = a/b;
        int r = a -q*b;

        if(r == 0)
        {
            int[] output = {0, 1};
            return output;
        }

        int[] next = euclidean(b, r);

        int[] output = {next[1], next[0] - q*next[1]};
        return output;
    }

    public static long leastPosEquiv(long a, long m)
    {

        if(m < 0)
            return leastPosEquiv(a, -1*m);

        if(a >= 0 && a < m)
            return a;


        if(a < 0)
            return -1*leastPosEquiv(-1*a, m) + m;

        long q = a/m;

        return a - q*m;
    }

}
