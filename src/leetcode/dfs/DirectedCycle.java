/*
 * Detect cycle
 */
package leetcode.dfs;

import java.util.List;

public class DirectedCycle {
    
    boolean visited[ ];
    boolean onStack[ ];
    int N;
    List<List<Integer>> adjList;
    
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
}
