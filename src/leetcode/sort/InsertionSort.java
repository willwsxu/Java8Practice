package leetcode.sort;

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

    public static void main(String[] args)
    {
    	ListNode head = ListNode.asList(new int[] {4,2,1,3});
    	//ListNode.print(head);
    	head = new InsertionSort().insertionSortList(head);
    	ListNode.print(head);
    	head = ListNode.asList(new int[] {-1,5,3,4,0});
    	head = new InsertionSort().insertionSortList(head);
    	ListNode.print(head);
    }
}
