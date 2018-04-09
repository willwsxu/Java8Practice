package leetcode.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class CheapestTicketKStop {
    static public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    	Queue<Integer> nodes=new ArrayBlockingQueue<>(2*n);
    	List<List<int[]>> adjList=new ArrayList<>();
    	for (int i=0; i<n; i++)
    		adjList.add(new ArrayList<>());
    	for (int[] f: flights) {
    		adjList.get(f[0]).add(f);
    	}
    	nodes.add(src); // bfs, add src city
    	int stops=0;
        int ans[] = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE/2);
        ans[src]=0;  // ensure each step, price is computed one city away from src
        boolean added[]=new boolean[n];
    	while (!nodes.isEmpty() && stops<=K) {
            int lastSize=nodes.size();
            Arrays.fill(added, false);  // don't add same note twice
            for (int i=0; i<lastSize; i++) {
                int from=nodes.poll();  // from city
                for (int [] f : adjList.get(from)) {
                    if (stops==K && f[1]!=dst)
                            continue;
                    ans[f[1]] = Integer.min(ans[f[1]], ans[from]+f[2]);
                    if (f[1]==dst) {
                        continue;
                    }
                    if ( !added[f[1]]) {
                        nodes.add(f[1]);
                        added[f[1]]=true;
                    }
                }
            }
            //System.out.println(Arrays.toString(ans));
            stops++;
    	}
    	return ans[dst]>=Integer.MAX_VALUE/2?-1:ans[dst];
    }
    public static void main(String[] args)
    {
    	int [][]flights=new int[][] {{3,4,4},{2,5,6},{4,7,10},{9,6,5},{7,4,4},{6,2,10},{6,8,6},{7,9,4},{1,5,4},{1,0,4},{9,7,3},{7,0,5},{6,5,8},{1,7,6},{4,0,9},{5,9,1},{8,7,3},{1,2,6},{4,1,5},{5,2,4},{1,9,1},{7,8,10},{0,4,2},{7,2,8}};
    	int ans=findCheapestPrice(10, flights, 6, 0, 7);
    	System.out.println(ans==14);
        
        ans=findCheapestPrice(5, new int[][]{{4,1,1},{1,2,3},{0,3,2},{0,4,10},{3,1,1},{1,4,3}}, 2, 1, 1);
    	System.out.println(ans==-1);
    }

}
