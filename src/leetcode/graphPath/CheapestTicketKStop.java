package leetcode.graphPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class CheapestTicketKStop {
    static public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    	Queue<int[]> nodes=new ArrayBlockingQueue<>(n*n);
    	List<List<int[]>> adjList=new ArrayList<>();
    	for (int i=0; i<n; i++)
    		adjList.add(new ArrayList<>());
    	for (int[] f: flights) {
    		adjList.get(f[0]).add(f);
    	}
    	nodes.add(new int[] {src, 0, 0}); // city, stop, price
    	int stops=0;
    	int ans=Integer.MAX_VALUE;
    	while (!nodes.isEmpty() && stops<=K) {
    		int lastSize=nodes.size();
    		for (int i=0; i<lastSize; i++) {
    			int[] s=nodes.poll();
    			for (int [] f : adjList.get(s[0])) {
    				if (stops==K && f[1]!=dst)
    					continue;
    				if (f[1]==dst) {
    					ans = Integer.min(ans, s[2]+f[2]);
    					continue;
    				}
    				System.out.println("add "+s[0]+": size"+nodes.size()+" stops "+stops);
    				nodes.add(new int[]{f[1], s[1]+1, s[2]+f[2]});
    			}
    		}
    		stops++;
    	}
    	return ans;
    }
    public static void main(String[] args)
    {
    	int [][]flights=new int[][] {{3,4,4},{2,5,6},{4,7,10},{9,6,5},{7,4,4},{6,2,10},{6,8,6},{7,9,4},{1,5,4},{1,0,4},{9,7,3},{7,0,5},{6,5,8},{1,7,6},{4,0,9},{5,9,1},{8,7,3},{1,2,6},{4,1,5},{5,2,4},{1,9,1},{7,8,10},{0,4,2},{7,2,8}};
    	int ans=findCheapestPrice(10, flights, 6, 0, 7);
    	System.out.println(ans);
    }

}
