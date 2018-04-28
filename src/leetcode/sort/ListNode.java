package leetcode.sort;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    
    static void print (ListNode head) 
    {
    	while (head !=null) {
    		System.out.print(head.val+" ");
    		head=head.next;
    	}
    	System.out.println();
    }
    static ListNode asList(int[] nums)
    {
    	ListNode head=new ListNode(nums[0]);
    	ListNode cur=head;
    	for (int i=1; i<nums.length; i++) {
    		cur.next=new ListNode(nums[i]);
    		cur=cur.next;
    	}
    	return head;
    }
}
