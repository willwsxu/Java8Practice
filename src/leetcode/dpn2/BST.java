/*
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 */
package leetcode.dpn2;

public class BST {
    static public int numTrees(int n) { // beat 100%
        int dp[]=new int[n+1];
        dp[0]=1;
        dp[1]=1; // 1 node
        for (int i=2; i<=n; i++) {  //tree length
            int total=0;
            for (int j=1; j<=i; j++) {  // try each node as root
                total += dp[j-1] * dp[i-j];  // left tree * right tree
            }
            dp[i]=total;
        }
        return dp[n];
    }
    
    public static void main(String[] args)
    {
    	System.out.println(numTrees(2)==2);     
    	System.out.println(numTrees(3)==5);       
    }
}
