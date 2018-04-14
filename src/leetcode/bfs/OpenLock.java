/*
 * a lock has 4 circular wheels. Each wheel has 10 slots, from 0 to 9
 * The wheels can rotate freely and wrap around, forward or reverse
 * The lock initially starts at '0000', a string representing the state of the 4 wheels
 * given a list of dead ends, meaning if the lock displays any of these codes, 
 * the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock, 
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible
 */
package leetcode.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class OpenLock {
    
    int[] convert(String s)
    {
        int []ans=new int[s.length()];
        for (int i=0; i<s.length(); i++)
            ans[i]=s.charAt(i)-'0';
        return ans;
    }
    boolean equals(int[]v1, int[]v2)
    {
        for (int j=0; j<4; j++)
            if (v1[j]!=v2[j])
                return false;
        return true;
    }
    void nextMove(boolean visited[][][][], Queue<int[]> q, int n1, int n2, int n3, int n4)
    {
        if (!visited[n1][n2][n3][n4]) {
            visited[n1][n2][n3][n4]=true;
            q.add(new int[]{n1, n2, n3, n4});
        }        
    }
    public int openLock(String[] deadends, String target) {
        boolean visited[][][][]=new boolean[10][10][10][10];
        // mark deadends as visited so we never go there
        for (String s: deadends) {
            int[] nums=convert(s);
            visited[nums[0]][nums[1]][nums[2]][nums[3]]=true;
        }
        if (visited[0][0][0][0])
            return -1;
        int[]_target=convert(target);
        Queue<int[]> q= new ArrayDeque<>();
        q.add(new int[]{0,0,0,0});
        int count=0;
        while (!q.isEmpty()) {
            count++;
            int oldSize=q.size();
            for (int i=0; i<oldSize; i++) {
                int[] config=q.poll();
                if (equals(config, _target))
                    return count-1;
                // check 8 different config
                nextMove(visited, q, (config[0]+1)%10, config[1], config[2], config[3]);
                nextMove(visited, q, (config[0]+9)%10, config[1], config[2], config[3]);
                nextMove(visited, q, config[0], (config[1]+1)%10, config[2], config[3]);
                nextMove(visited, q, config[0], (config[1]+9)%10, config[2], config[3]);
                nextMove(visited, q, config[0], config[1], (config[2]+1)%10, config[3]);
                nextMove(visited, q, config[0], config[1], (config[2]+9)%10, config[3]);
                nextMove(visited, q, config[0], config[1], config[2], (config[3]+1)%10);
                nextMove(visited, q, config[0], config[1], config[2], (config[3]+9)%10);
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        int ans=new OpenLock().openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202");
        System.out.println(ans==6);
        ans=new OpenLock().openLock(new String[]{"8888"}, "0009");
        System.out.println(ans==1);
        ans=new OpenLock().openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888");
        System.out.println(ans==-1);
        ans=new OpenLock().openLock(new String[]{"0000"}, "8888");
        System.out.println(ans==-1);
    }
}
