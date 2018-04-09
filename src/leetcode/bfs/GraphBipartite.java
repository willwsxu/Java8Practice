/*
 * Given an undirected graph, return true if and only if it is bipartite
 */
package leetcode.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class GraphBipartite {
    
    public boolean isBipartite(int[][] graph) {
        int color[] = new int[graph.length];  // color 1 or 2
        Queue<Integer> q=new ArrayDeque<>();
        for (int i=0; i<graph.length; i++) {  // in case graph is disconnected
            if (color[i]!=0)
                continue;
            q.add(i);
            color[i]=1;
            while (!q.isEmpty()) {
                int u=q.poll();
                for (int v: graph[u]) {
                    switch (color[v]) {
                        case 0:
                            color[v]=3-color[u]; // opposite color of each edge
                            q.add(v);
                            break;
                        case 1:
                        case 2:
                            if (color[u]==color[v])
                                return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args)
    {
        System.out.println(new GraphBipartite().isBipartite(new int[][]{{1,3}, {0,2}, {1,3}, {0,2}}));
        System.out.println(new GraphBipartite().isBipartite(new int[][]{{1,2,3}, {0,2}, {0,1,3}, {0,2}})==false);
        System.out.println(new GraphBipartite().isBipartite(new int[][]{{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}})==false);
    }
}
