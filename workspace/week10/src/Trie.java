import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
 
    public TrieNode() {}
 
    public TrieNode(char c){
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode();
    }
 
    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;
 
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
 
            TrieNode t;
            if(children.containsKey(c)){
                    t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }
 
            children = t.children;
 
            //set leaf node
            if(i==word.length()-1)
                t.isLeaf = true;    
        }
    }
 
    // Returns if the word is in the trie.
   /* public boolean search(String word) {
        TrieNode t = searchNode(word);
 
        if(t != null && t.isLeaf) 
            return true;
        else
            return false;
    }*/
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
   // public boolean startsWith(String prefix) {
      /*  if(searchNode(prefix) == null) 
            return false;
        else
            return true;*/
    	//return searchNode(prefix).isLeaf;
    //}
 
    public boolean searchNode(String str){
        Map<Character, TrieNode> children = root.children; 
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }
        }
        return t.children.isEmpty();
    }
    public static void main(String args[]) throws NumberFormatException, IOException
    {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	int cases=Integer.parseInt(br.readLine());
    	int i,j,k,l;
    	for(i=1;i<=cases;i++)
    	{
    		Trie st=new Trie();
    		int n=Integer.parseInt(br.readLine());
    		String names[]=new String[n];
    		for(j=0;j<n;j++)
    		{
    			names[j]=br.readLine();
    			st.insert(names[j]);
    		}
    		int counter=0;
    		for(k=0;k<n;k++)
    		{
    			if(!st.searchNode(names[k]))
    			{
    				counter++;
    				
    			}
    		
    		}
    		System.out.println("Case #"+i+": "+counter);
    		br.readLine();
    	}
   }
}