package leetcode.binarySearch;

import java.util.Arrays;

public class FourSum {

	// compute how many tuples A[i]+B[j]+C[k]+D[l]=0
	int[] combineSum(int[]A, int[]B)
	{
        int sumAB[]=new int[A.length*B.length];
        for (int i=0; i<A.length; i++) {
        	for (int j=0; j<B.length; j++) {
        		sumAB[i*A.length+j]=A[i]+B[j];
        	}
        }
        Arrays.sort(sumAB);
		return sumAB;
	}
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) { // very slow
        int sumAB[]=combineSum(A, B);
        int sumCD[]=combineSum(C, D);
        int ans=0;
        for (int i=0; i<sumAB.length; i++) {
        	int pos=Arrays.binarySearch(sumCD, -sumAB[i]);
        	if (pos<0)
        		continue;
        	for (int j=pos; j<sumCD.length; j++) {
        		if (sumCD[j]==-sumAB[i])
        			ans++;
        		else
        			break;        		
        	}
        	for (int j=pos-1; j>=0; j--) {
        		if (sumCD[j]==-sumAB[i])
        			ans++;
        		else
        			break;        		
        	}
        }
        return ans;
    }
    public static void main(String[] args)
    {
    	System.out.println(new FourSum().fourSumCount(new int[] {1,2},new int[] {-2,-1},new int[] {-1,2},new int[] {0,2}));
    }
}
