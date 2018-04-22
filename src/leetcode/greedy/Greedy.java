package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public static void main(String[] args)
    {
    	List<Integer> ans=partitionLabels("ababcbacadefegdehijhklij");
    	System.out.println(ans);
    	
    }
}
