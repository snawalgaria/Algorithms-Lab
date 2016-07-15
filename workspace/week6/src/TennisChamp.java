// MaximalClique code frame work taken from https://algos.org/maximal-cliquesbronkerbosch-without-pivot-java/
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader; 
import java.util.ArrayList; 
import java.util.Collections;
import java.util.Scanner; 


public class TennisChamp {

    int nodesCount; 
    ArrayList<Vertex> graph = new ArrayList<Vertex>(); 
    ArrayList<Vertex> max_list = new ArrayList<Vertex>();
  
    class Vertex implements Comparable<Vertex> {
        int x; 

        int degree; 
        ArrayList<Vertex> nbrs = new ArrayList<Vertex>(); 

        public int getX() {
            return x; 
        } 

        public void setX(int x) {
            this.x = x; 
        } 

        public int getDegree() {
            return degree; 
        } 

        public void setDegree(int degree) {
            this.degree = degree; 
        } 

        public ArrayList<Vertex> getNbrs() { 
            return nbrs; 
        } 

        public void setNbrs(ArrayList<Vertex> nbrs) {
            this.nbrs = nbrs; 
        } 

        public void addNbr(Vertex y) {
            this.nbrs.add(y); 
            if (!y.getNbrs().contains(y)) { 
                y.getNbrs().add(this); 
                y.degree++; 
            } 
            this.degree++; 

        } 

        public void removeNbr(Vertex y) {
            this.nbrs.remove(y); 
            if (y.getNbrs().contains(y)) { 
                y.getNbrs().remove(this); 
                y.degree--; 
            } 
            this.degree--; 

        } 

        @Override 
        public int compareTo(Vertex o) {
            if (this.degree < o.degree) {
                return -1; 
            } 
            if (this.degree > o.degree) {
                return 1;
            } 
            return 0; 
        } 
    } 

    void initGraph() { 
        graph.clear(); 
        for (int i = 0; i < nodesCount; i++) {
            Vertex V = new Vertex(); 
            V.setX(i); 
            graph.add(V); 
        } 
    } 

    int readTotalGraphCount(BufferedReader bufReader) throws Exception {

        return Integer.parseInt(bufReader.readLine()); 
    } 

    // Reads Input 
    void readNextGraph(BufferedReader bufReader) throws Exception {
        try { 
            nodesCount = Integer.parseInt(bufReader.readLine()); 
            int edgesCount = Integer.parseInt(bufReader.readLine());
            initGraph(); 

            for (int k = 0; k < edgesCount; k++) {
                String[] strArr = bufReader.readLine().split(" "); 
                int u = Integer.parseInt(strArr[0]);
                int v = Integer.parseInt(strArr[1]);
                Vertex vertU = graph.get(u); 
                Vertex vertV = graph.get(v); 
                vertU.addNbr(vertV); 

            } 

        } catch (Exception e) { 
            e.printStackTrace(); 
            throw e; 
        } 
    } 

    // Finds nbr of vertex i 
    ArrayList<Vertex> getNbrs(Vertex v) { 
        int i = v.getX(); 
        return graph.get(i).nbrs; 
    } 

    // Intersection of two sets 
    ArrayList<Vertex> intersect(ArrayList<Vertex> arlFirst, 
            ArrayList<Vertex> arlSecond) { 
        ArrayList<Vertex> arlHold = new ArrayList<Vertex>(arlFirst); 
        arlHold.retainAll(arlSecond); 
        return arlHold; 
    } 

    // Union of two sets 
    ArrayList<Vertex> union(ArrayList<Vertex> arlFirst, 
            ArrayList<Vertex> arlSecond) { 
        ArrayList<Vertex> arlHold = new ArrayList<Vertex>(arlFirst); 
        arlHold.addAll(arlSecond); 
        return arlHold; 
    } 

    // Removes the neigbours 
    ArrayList<Vertex> removeNbrs(ArrayList<Vertex> arlFirst, Vertex v) { 
        ArrayList<Vertex> arlHold = new ArrayList<Vertex>(arlFirst); 
        arlHold.removeAll(v.getNbrs()); 
        return arlHold; 
    } 

    // Version without a Pivot 
    void Bron_KerboschWithoutPivot(ArrayList<Vertex> R, ArrayList<Vertex> P,
            ArrayList<Vertex> X, String pre) { 

    	int max =0;
    		
        if ((P.size() == 0) && (X.size() == 0)) {
            printClique(R); 
            return; 
        } 
    
        ArrayList<Vertex> P1 = new ArrayList<Vertex>(P); 

        for (Vertex v : P) { 
            R.add(v); 
            Bron_KerboschWithoutPivot(R, intersect(P1, getNbrs(v)), 
                    intersect(X, getNbrs(v)), pre + "\t"); 
            R.remove(v); 
            P1.remove(v); 
            X.add(v); 
        } 
    } 

    void Bron_KerboschPivotExecute() { 

        ArrayList<Vertex> X = new ArrayList<Vertex>(); 
        ArrayList<Vertex> R = new ArrayList<Vertex>(); 
        ArrayList<Vertex> P = new ArrayList<Vertex>(graph); 
        Bron_KerboschWithoutPivot(R, P, X, ""); 
        for(int i=0;i<max_list.size();i++)
        System.out.print(max_list.get(i).getX()+1 +" ");
        max_list = new <Vertex> ArrayList();
    } 

    void printClique(ArrayList<Vertex> R) { 
    //   int max = 0;
       
    	if (max_list.size() < R.size())
    	{//System.out.println(max_list.size());
    		max_list = new <Vertex> ArrayList(R);
    		//System.out.println(max_list.size());
    	}
//    	for (Vertex v : R) { 
//        	
//            System.out.print(" " + (v.getX())); 
//        	
//        } 
//        System.out.println(); 
    } 

   

    public static void main(String[] args) throws IOException {
       
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufReader = null;
    	int cases=Integer.parseInt(br.readLine());
    	 StringBuilder sb = new StringBuilder();
    	 sb.append(String.valueOf(cases));
         sb.append("\n");
    	for(int t=1;t<=cases;t++)
         {
    		int n,m;
    		String mat[];
    		String inp[]=br.readLine().split(" ");
    		n=Integer.parseInt(inp[0]);
    		m=Integer.parseInt(inp[1]);
			 
			for (int i = 1; i <= m; i++) {
				mat=br.readLine().split(" ");
				int x = Integer.valueOf(mat[0]);
				int y = Integer.valueOf(mat[1]);
				sb.append(String.valueOf(x-1)+" "+String.valueOf(y-1)+"\n");
				
			}
      
			// Unit Test Mode 
            bufReader = new BufferedReader(new StringReader(
                    sb.toString())); 
          
        TennisChamp tennis = new TennisChamp();
        //System.out.println("Max Cliques Without Pivot"); 
        try { 
            int totalGraphs = tennis.readTotalGraphCount(bufReader);
            for (int i = 0; i < totalGraphs; i++) {
                System.out.print("Case #"+(i+1)+": ");
                tennis.readNextGraph(bufReader); 
                tennis.Bron_KerboschPivotExecute(); 
                System.out.println();
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
            System.err.println("Exiting : " + e); 
        } finally { 
            try { 
                bufReader.close(); 
            } catch (Exception f) { 

            } 
        } 
    } 
   }
} 