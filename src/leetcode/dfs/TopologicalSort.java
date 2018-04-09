/*
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * prerequisites, expressed as a pair: [0,1], meaning to take course 0 you have to first take course 1
 * return the ordering of courses you should take to finish all courses
 */
package leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    void dfs(int u, List<List<Integer>> adjList, Stack<Integer> top){
        visited[u]=true;
        onStack[u]=true;
        for (Integer v : adjList.get(u)) {
            if (onStack[v]) {
                cycle=true;
                return;
            }
            if (visited[v] )
                continue;
            dfs(v, adjList, top);
        }
        onStack[u]=false;
        top.push(u);
    }

    boolean cycle=false;
    boolean visited[ ];
    boolean onStack[ ];
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj= new ArrayList<>();
        for (int i=0; i<numCourses; i++)
            adj.add(new ArrayList<Integer>());
        for (int req[] : prerequisites) {
            adj.get(req[1]).add(req[0]);
        }
        int courses[]=new int[numCourses];
        visited=new boolean[numCourses];
        onStack=new boolean[numCourses];
        Stack<Integer> top = new Stack<>();
        for (int i=0; i<numCourses; i++)
            if (!visited[i])
                dfs(i, adj, top);
        if (  cycle )
            return new int[]{};
        for (int i=0; i<numCourses; i++)
            courses[i]=top.pop();
        return courses;
    }
    
    public static void main(String[] args)
    {
        TopologicalSort sch=new TopologicalSort();
        int []ans=sch.findOrder(2, new int[][]{{1,0}});
        System.out.println(Arrays.toString(ans));
        
        sch=new TopologicalSort();
        ans=sch.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}});
        System.out.println(Arrays.toString(ans));
        
        sch=new TopologicalSort();
        ans=sch.findOrder(2, new int[][]{{1,0},{0,1}});
        System.out.println(Arrays.toString(ans));
    }
}
