package leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Tree {
    // Given a binary tree, find the leftmost value in the last row of the tree
    static public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q=new ArrayDeque<>();
        q.add(root);
        int ans=0;
        while (!q.isEmpty()) {
        	int size=q.size();
        	for (int i=0; i<size; i++) {
        		TreeNode tn=q.poll();
        		if (i==0)  // first node of the row
        			ans=tn.val;
        		if (tn.left!=null)
        			q.add(tn.left);
        		if (tn.right!=null)
        			q.add(tn.right);        		
        	}
        }
        return ans;
    }

    // Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
    static public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
    	if (root==null)
    		return ans;
        Queue<TreeNode> q=new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
        	int size=q.size();
        	List<Integer> level = new ArrayList<>();
        	ans.add(level);
        	for (int i=0; i<size; i++) {
        		TreeNode tn=q.poll();
        		level.add(tn.val);
        		if (tn.left!=null)
        			q.add(tn.left);
        		if (tn.right!=null)
        			q.add(tn.right);        		
        	}
        }
        return ans;
    }
    
    // Given a binary tree, imagine yourself standing on the right side of it, 
    // return the values of the nodes you can see ordered from top to bottom.
    static public List<Integer> rightSideView(TreeNode root) {  // similar idea to findBottomLeftValue
        List<Integer> ans=new ArrayList<>();
    	if (root==null)
    		return ans;
        Queue<TreeNode> q=new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
        	int size=q.size();
                int last=0;
        	for (int i=0; i<size; i++) {
                    TreeNode tn=q.poll();
                    last=tn.val;
                    if (tn.left!=null)
                            q.add(tn.left);
                    if (tn.right!=null)
                            q.add(tn.right);        		
        	}
                ans.add(last);
        }
        return ans;        
    }
    
    static void rightSideView_recur(TreeNode node, List<Integer> ans, int d)
    {
        if (node==null)
            return;
        if (ans.size()==d)  // first node from right at this level
            ans.add(node.val);
        rightSideView_recur(node.right, ans, d+1); // right side first
        rightSideView_recur(node.left, ans, d+1); // right side first
    }
    static public List<Integer> rightSideView_recur(TreeNode root) {  // faster 2 ms vs 3ms in rightSideView
        List<Integer> ans=new ArrayList<>();
        rightSideView_recur(root, ans, 0);
        return ans;
    }
    
    // Given a binary tree, return the zigzag level order traversal of its nodes' values
    // left to right, then right to left
    static public List<List<Integer>> zigzagLevelOrder(TreeNode root) { // borrow from levelOrder
        List<List<Integer>> ans=new ArrayList<>();
    	if (root==null)
    		return ans;
        Queue<TreeNode> q=new ArrayDeque<>();
        q.add(root);
        boolean leftRight=true;
        while (!q.isEmpty()) {
        	int size=q.size();
        	List<Integer> level = new ArrayList<>();
        	ans.add(level);
        	for (int i=0; i<size; i++) {
        		TreeNode tn=q.poll();
        		level.add(tn.val);
        		if (tn.left!=null)
        			q.add(tn.left);
        		if (tn.right!=null)
        			q.add(tn.right);        		
        	}
                if (!leftRight)
                    Collections.reverse(level);
                leftRight = !leftRight;
        }
        return ans;
    }

    //Given an integer n, generate all structurally unique BST's that store values 1 ... n
    List<TreeNode> generate(int L, int R) // top down recursion, beat 97%
    {
    	List<TreeNode> root=new ArrayList<>();
    	if (L>R) {
    		root.add(null);
    		return root;
    	} else if (L==R) {
    		root.add(new TreeNode(L));
    		return root;
    	}
    	List<TreeNode> left, right;
    	for (int i=L; i<=R; i++) {
    		left=generate(L, i-1);
    		right=generate(i+1, R);
    		for (int j=0; j<left.size(); j++) { // combine left tree with right, create a root for each combo, store in list
    			for (int k=0; k<right.size(); k++) {
    				TreeNode r=new TreeNode(i);
    				r.left=left.get(j);  // dp, share nodes from leaf up to current root
    				r.right=right.get(k);
    				root.add(r);
    			}
    		}
    	}
    	return root;
    }
    
    public List<TreeNode> generateTrees(int n) {
    	if (n==0)
    		return new ArrayList<>();
    	return generate(1, n);
    }

    static void testTreeBfs()
    {
    	TreeNode r=new TreeNode(1);
    	r.left=new TreeNode(2);
    	r.right=new TreeNode(3);
    	r.left.left=new TreeNode(4);
    	r.right.left=new TreeNode(5);
    	r.right.right=new TreeNode(6);
    	r.right.left.left=new TreeNode(7);
    	r.right.left.right=new TreeNode(8);
    	System.out.println(findBottomLeftValue(r)==7);
    	        
        System.out.println(rightSideView(r));
        System.out.println(rightSideView_recur(r));
        
    	List<List<Integer>> ans=levelOrder(r);
    	for (List<Integer> li: ans) {
    		System.out.println(li);
    	}
    	ans=levelOrder(null);
        
        ans=zigzagLevelOrder(r);
    	for (List<Integer> li: ans) {
            System.out.println(li);
    	}    	
    }
    static void print(TreeNode root) {
        Queue<TreeNode> q=new ArrayDeque<>();
        q.add(root);
        TreeNode nil=new TreeNode(0);
        while (!q.isEmpty()) {
        	int oldSize=q.size();
        	for (int i=0; i<oldSize; i++) {
        		TreeNode tn=q.poll();
            	if (tn.val==0) {
            		System.out.print("null, ");
            		continue;
            	}
            	else
            		System.out.print(tn.val+", ");
            	if (tn.left!=null)
            		q.add(tn.left);
            	else if ( tn.right !=null ) {
            		q.add(nil);
            	}
            	if ( tn.right !=null)
            		q.add(tn.right);
            	else if (tn.left!=null)
            		q.add(nil);
        	}
        }
    }
    public static void main(String[] args)
    {
    	List<TreeNode> trees=new Tree().generateTrees(3);
    	for (TreeNode t: trees) {
    		print(t);
    		System.out.println();
    	}
    	trees=new Tree().generateTrees(0);
    	for (TreeNode t: trees) {
    		print(t);
    		System.out.println();
    	}
    	trees=new Tree().generateTrees(1);
    	for (TreeNode t: trees) {
    		print(t);
    		System.out.println();
    	}
    }
}
