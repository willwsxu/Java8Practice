package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}
public class Greedy {
	// partition a string into as many parts as possible so that each letter appears in at most one part, 
	// and return a list of integers representing the size of these parts
    static public List<Integer> partitionLabels(String S) { // why it is so slow? beat 4%
        List<int[]> sections=new ArrayList<>(26);// find span of each letter
        for (int i=0; i<26; i++)
        	sections.add(new int[] {0,0});
        for (int i=0; i<S.length(); i++) {
        	int[] span=sections.get(S.charAt(i)-'a');
        	if (span[0]==0) {  // first time for this letter
        		span[0]=span[1]=i+1;
        	}
        	else
        		span[1]=i+1;
        }
        Collections.sort(sections, (c1,c2)->c1[0]-c2[0]);
        List<Integer> ans=new ArrayList<>();
        int[] interval=null;
        for (int i=0; i<sections.size(); i++) {
        	int[] span=sections.get(i);
        	if (span[0]==0)  // not exist
        		continue;
        	if (interval==null)
        		interval=span;
        	else {  // combine it with last one
        		if (interval[1]<span[0]) {  // no intersection
        			ans.add(interval[1]-interval[0]+1);
        			interval=span;
        		} else {  // overlapping
        			if (interval[1]<span[1])
        				interval[1]=span[1];
        		}
        	}
        }
        if (interval!=null)
        	ans.add(interval[1]-interval[0]+1);
        return ans;
    }
    
    // Given a collection of intervals, find the minimum number of intervals you need to remove 
    // to make the rest of the intervals non-overlapping
    // same idea as classic scheduling problem to find max none overlapping
    static public int eraseOverlapIntervals(Interval[] intervals) {// beat 35%
    	if (intervals.length<2)
    		return 0;
    	Comparator<Interval> cmp=(c1,c2)->c1.end-c2.end; // sort by end point
        Arrays.sort(intervals, cmp);
        int deleted=0;
        int end=intervals[0].end;
        for (int i=1; i<intervals.length; i++) {
        	if (intervals[i].start<end)  // overlap last good interval
        		deleted++;
        	else
        		end=intervals[i].end;  // update end from last
        }
        return deleted;
    }
    
    // Given a list of tasks, from A to Z, each can be complete in one interval
    // there is a non-negative cooling interval n that means between two same tasks, 
    // there must be at least n intervals, some other task or idle
    // return the least number of intervals the CPU will take to finish all the given tasks.
    // idea: find max repeating task, calculate idles required to satisfy n
    static public int leastInterval(char[] tasks, int n) {
        int[]count=new int[26]; // count of each task
        int maxCount=0;			// max repeating task
        for (char t: tasks) {
        	count[t-'A']++;
        	maxCount=Integer.max(maxCount, count[t-'A']);
        }
        int filling=0; // tasks filling in between to satisfy n
        boolean seenMax=false;
        for (int c: count) {
        	if (c==maxCount) {
        		if (!seenMax) {  // skip this as it is the first MAX
        			seenMax=true;
        			continue;
        		}
        		else  // only need to fill c-1 in between
        			filling += (c-1);
        	}
        	else
        		filling+=c;
        }
        int idles=n*(maxCount-1)-filling;
        if (idles<=0)
        	return tasks.length;

        return idles+tasks.length;
    }
    
    //Each person is described by a pair of integers (h, k), where h is the height and k is number of people 
    // in front of this person who have a height greater than or equal to h
    // The number of people is less than 1,100
    static void print(int[][] people)
    {
    	for (int[] r:people)
    		System.out.print(Arrays.toString(r)+" ");
    	System.out.println();
    }
    static public int[][] reconstructQueue(int[][] people) {
    	Comparator<int[]> cmp1=(c1,c2)->c1[1]-c2[1]; // sort by k
    	Comparator<int[]> cmp2=(c1,c2)->c1[0]-c2[0]; // sort by h
        Arrays.sort(people, cmp1.thenComparing(cmp2)); // sorted primarily by k, if ksame, sort by h
        List<int[]> temp=new ArrayList<>();
        for (int i=0; i<people.length; i++) {
        	if (people[i][1]==0) {  // k==0, just append
        		temp.add(people[i]);
        		continue;
        	}
        	int taller=0;
        	for (int j=0; j<temp.size();j++) {
        		if (temp.get(j)[0]>=people[i][0])  // count people in the temp list no sorter than the one next to add
        			taller++;
        		if (taller==people[i][1]) {  // find position to satisfy k
        			for (j=j+1; j<temp.size();j++)  // keep look ahead to pass all shorter people
        				if (temp.get(j)[0]>=people[i][0])
        					break;
        			temp.add(j, people[i]);
        			//System.out.println("insert at "+(j+1)+" "+Arrays.toString(people[i]));
        			break;
        		}
        	}
        }
        for (int i=0; i<people.length; i++)
        	people[i]=temp.get(i);
        return people;
    }
    
    // process senator from left to right, each senator can ban other party to vote. 
    // when one round is done, repeat, until all senators belong to one party
    // e.g. DDRRR, first round, 2D ban 2R, R ban D, result DR. 2nd round D ban R, D win
    void vote(int count[], int[] ban, List<Integer> alive, int side)
    {
		if (ban[side]>0) { // previous opponents have outstanding bans
			ban[side]--;  // simply reduce the count
		} else {
			count[side]++;
			if (count[1-side]>0) {  // if there are opponents before this guy, it is preferred to ban 
				alive.remove(new Integer(1-side)); // ban previous opponent
				count[1-side]--;
				System.out.println("remove "+(1-side));
			}
			else 
				ban[1-side]++;  // issue outstanding ban for next
			alive.add(side);
		}    	
    }
    public String predictPartyVictory(String senate) {
    	int[] count=new int[2]; // 0-R, 1-D
    	int[] ban=new int[2];
    	List<Integer> alive=new ArrayList<>();  // senators not banned
    	for (int i=0; i<senate.length(); i++) { // first round
    		int side=senate.charAt(i)=='R'?0:1;
    		vote(count, ban, alive, side);
    		System.out.println(alive);
    	}
    	System.out.println(Arrays.toString(count));
    	System.out.println(Arrays.toString(ban));
        while (count[0]>0 && count[1]>0) {  // rounds
        	count[0]=count[1]=0;//  reset
        	List<Integer> next=new ArrayList<>(); 
        	for (Integer side: alive ) {
        		vote(count, ban, next, side);       
        		System.out.println(next); 		
        	}
        	alive=next;
        	System.out.println(Arrays.toString(count));
        	System.out.println(Arrays.toString(ban));
        }
        if (count[0]>0)
        	return "Radiant";
        else
        	return "Dire";
    }
    public static void main(String[] args)
    {
    	List<Integer> ans=partitionLabels("ababcbacadefegdehijhklij");
    	System.out.println(ans);
    	
    	System.out.println(leastInterval(new char[] {'A','A','A','B','B','B'}, 2)==8);
    	
    	int[][] p=new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
    	p = reconstructQueue(p);
        print(p); // [5, 0] [7, 0] [5, 2] [6, 1] [4, 4] [7, 1] 
        p=new int[][]{{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}};
    	p = reconstructQueue(p);
        print(p);  // [4, 0] [5, 0] [2, 2] [3, 2] [1, 4] [6, 0]

        System.out.println(new Greedy().predictPartyVictory("DRRDRDRDRDDRDRDR")); // R
        /*System.out.println(new Greedy().predictPartyVictory("RD"));
        System.out.println(new Greedy().predictPartyVictory("DR"));
        System.out.println(new Greedy().predictPartyVictory("R"));
        System.out.println(new Greedy().predictPartyVictory("RDD"));*/
    }
}
