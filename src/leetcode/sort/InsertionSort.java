package leetcode.sort;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class InsertionSort {
	ListNode insert(ListNode head, ListNode n)
	{
		if (n.val<=head.val) {  // new head
			n.next=head;
			return n;
		}
		ListNode cur=head;
		while (cur != null) {
			if (cur.next!=null && n.val<=cur.next.val) { // insert n after cur
				n.next=cur.next;
				cur.next=n;
				return head;
			}
			cur=cur.next;
		}
		System.out.println("error");
		return head;
	}
    public ListNode insertionSortList(ListNode head) {  // beat 97%
        ListNode tail=head; // tail is the last sorted node
        while (tail !=null) {
        	ListNode next=tail.next;
        	if (next==null)
        		break;
        	if (next.val>=tail.val)
        		tail=next;  // extend tail
        	else {  // insertion should happen before tail
        		next=next.next;  // save the next code
        		head=insert(head, tail.next);
        		tail.next=next;  // connect next to the tail
        	}
        }
        return head;
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
    static void print (ListNode head) 
    {
    	while (head !=null) {
    		System.out.print(head.val+" ");
    		head=head.next;
    	}
    	System.out.println();
    }
    public static void main(String[] args)
    {
    	ListNode head = asList(new int[] {4,2,1,3});
    	print(head);
    	head = new InsertionSort().insertionSortList(head);
    	print(head);
    	head = asList(new int[] {-1,5,3,4,0});
    	head = new InsertionSort().insertionSortList(head);
    	print(head);
    }
}
