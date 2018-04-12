/*
 * Directed Graph single source shortest path
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100
 */
package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraSP {
    List<List<int[]>> adjList;
    int[][] edgeTo;
    int[]   distTo;
    PriorityQueue<int[]> pq;
    int N;
    
    void sssp(List<List<int[]>> adj, int K)
    {
        N=adj.size();
        this.adjList=adj;
        edgeTo = new int[N][];
        distTo = new int[N];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[K]=0;
        Comparator<int[]> cmp = Comparator.comparingLong(a->a[1]);
        pq=new PriorityQueue<>(N*2, cmp);
        pq.add(new int[]{K, 0});
        while (!pq.isEmpty())
            relax();
    }
    void relax()
    {
        int[] u=pq.poll();
        for (int edge[] : adjList.get(u[0])) {
            int v=edge[1];
            if (u[1]+edge[2]<distTo[v]) {
                distTo[v] = u[1]+edge[2];
                pq.add(new int[]{v, distTo[v]});
                edgeTo[v]=edge;
                //if (distTo[v]>maxDist)  wrong to do it here
                //    maxDist=distTo[v];
            }
        }
    }
    
    public void bellmanford(int[][] times, int N, int K)
    {
        distTo = new int[N+1];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[K]=0;
        distTo[0]=0;
        //System.out.println(Arrays.toString(distTo));
        for (int i=0; i<N; i++) {  // repeat N times
            for (int[]edge : times) {
                int u=edge[0];
                if ( distTo[u]==Integer.MAX_VALUE)  // ignore u if not valid!!!
                    continue;
                int v=edge[1];
                if (distTo[u]+edge[2]<distTo[v])
                    distTo[v] = distTo[u]+edge[2];                
            }
        }        
    }
    
    /* Given times, a list of travel times as directed edges times[i] = (u, v, w), 
     * where u is the source node, v is the target node, and w is the time it takes 
     * for a signal to travel from source to target.
     * How long will it take for all nodes to receive the signal from K? If it is impossible, return -1.
    */
    // 743. all vertex are 1 based, convert to 0 based
    public int networkDelayTime(int[][] times, int N, int K) {
        /*List<List<int[]>> adj = new ArrayList<>(N);
        for (int i=0; i<N; i++)
            adj.add(new ArrayList<>());
        for (int[]e: times) {
            e[1]--;  // convert to 0 based
            adj.get(e[0]-1).add(e);
        }
        sssp(adj, K-1); // run dijkstra to get shortest distance to all nodes
        */
        bellmanford(times, N, K);
        int maxDist=-1;
        for (int d: distTo) {  // find the largest
            if (d == Integer.MAX_VALUE )
                return -1;
            if (d>maxDist)
                maxDist = d;
        }
        //System.out.println(Arrays.toString(distTo));
        return maxDist;
    }
        
    public static void main(String[] args)
    {
        int ans=new DijkstraSP().networkDelayTime(new int[][]{{1,2,1}}, 2, 2);
        System.out.println(ans==-1);
        
        int[][]t=new int[][]{{1,2,1},{2,3,2},{1,3,4}};
        ans=new DijkstraSP().networkDelayTime(t, 3, 1);
        System.out.println(ans==3);
        
        t=new int[][]{{1,2,1},{2,1,3}};
        ans=new DijkstraSP().networkDelayTime(t, 2, 2);
        System.out.println(ans==3);
    }
}
