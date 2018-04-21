package leetcode.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Greedy {
	// partition a string into as many parts as possible so that each letter appears in at most one part, 
	// and return a list of integers representing the size of these parts
    static public List<Integer> partitionLabels(String S) {
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
    public static void main(String[] args)
    {
    	List<Integer> ans=partitionLabels("ababcbacadefegdehijhklij");
    	System.out.println(ans);
    }
}
