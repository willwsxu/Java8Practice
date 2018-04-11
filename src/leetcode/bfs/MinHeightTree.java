package leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// Given undirected graph, find rooted tree of minimum height
// Bfs leaf nodes, remove edges connected to them and repeat until one or 2 left
public class MinHeightTree {
    static public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    	List<Integer> ans=new ArrayList<>();
    	if (n<=2) {
    		for (int i=0; i<n; i++)
    			ans.add(i);
    		return ans;
    	}
        List<List<Integer>> adj= new ArrayList<>();
        for (int i=0; i<n; i++)
            adj.add(new ArrayList<Integer>());
        for (int e[] : edges) {
            adj.get(e[1]).add(e[0]);
            adj.get(e[0]).add(e[1]);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i=0; i<n; i++)
        	if (adj.get(i).size()==1) // leaf node
        		q.add(i);
        while (!q.isEmpty() && n>2) {
        	int oldSize=q.size();
        	n -= oldSize;
        	for (int k=0; k<oldSize; k++) {
        		int u=q.poll();
        		for (int v: adj.get(u)) {
        			adj.get(v).remove(new Integer(u)); // remove u from all connected nodes
        			if (adj.get(v).size()==1)  // add new leaf node to queue
        				q.add(v);
        		}
        	}
        }
        for (int u: q)
        	ans.add(u);
        return ans;
    }

    public static void main(String[] args)
    {
    	List<Integer> ans=findMinHeightTrees(4, new int[][] {{1, 0}, {1, 2}, {1, 3}});  //1
    	System.out.println(ans);
    	
    	ans=findMinHeightTrees(6, new int[][] {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}} );//3.4
    	System.out.println(ans);    	

    	ans=findMinHeightTrees(6, new int[][] {{0,1},{0,2},{0,3},{3,4},{4,5}} ); //3
    	System.out.println(ans);

    	ans=findMinHeightTrees(2, new int[][] {{0,1}} ); //0,1
    	System.out.println(ans);
    }
}
