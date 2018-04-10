package leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Tree {
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
    public static void main(String[] args)
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
    	
    	List<List<Integer>> ans=levelOrder(r);
    	for (List<Integer> li: ans) {
    		System.out.println(li);
    	}
    	ans=levelOrder(null);
    }
}
