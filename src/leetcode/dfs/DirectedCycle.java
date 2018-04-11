/*
 * Detect cycle
 */
package leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectedCycle {
    
    boolean visited[ ];
    boolean onStack[ ];
    int N;
    List<List<Integer>> adjList;
    
    DirectedCycle()
    {    	
    }
    DirectedCycle(List<List<Integer>> adjList)
    {
        N=adjList.size();
        visited = new boolean[N];
        onStack = new boolean[N];
        this.adjList = adjList;
        for (int i=0; i<N; i++)
        {
            if (!visited[i])
                dfs(i);
        }
    }
    
    void dfs(int u)
    {
        visited[u]=true;
        onStack[u]=true;
        for (int v: adjList.get(u)) {
            if (cycle)
                return;
            if (onStack[v]) {
                cycle=true;
                return;
            }
            if (!visited[v])
                dfs(v);
        }
        onStack[u]=false;
    }
    boolean cycle=false;
    public boolean hasCycle()
    {
        return cycle;
    }
    
    //Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses? 
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj= new ArrayList<>();
        for (int i=0; i<numCourses; i++)
            adj.add(new ArrayList<Integer>());
        for (int req[] : prerequisites) {
            adj.get(req[1]).add(req[0]);
        }
        return !new DirectedCycle(adj).hasCycle();
    }
    
    public static void main(String[] args)
    {
		DirectedCycle sch=new DirectedCycle();
		System.out.println(sch.canFinish(2, new int[][]{{1,0}}));		
		System.out.println(sch.canFinish(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));		
		System.out.println(sch.canFinish(2, new int[][]{{1,0},{0,1}})==false);
    }
}
