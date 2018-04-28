package leetcode.sort;

public class MergeSort {
	ListNode merge(ListNode l1, int sz1, ListNode l2, int sz2)
	{
		ListNode head=null;
		ListNode cur=head;
		while (sz1>0 &&sz2>0) {
			ListNode next=null;
			if (l1.val<l2.val) { // stitch list from 2 sorted lists
				next=l1;
				l1=l1.next;
				sz1--;
			}
			else {
				next=l2;
				l2=l2.next;
				sz2--;
			}
			if (head==null) {
				head=next;
				cur=head;
			} else {
				cur.next=next;
				cur=next;
			}
		}
		if (sz1==0) {
			sz1=sz2;
			l1=l2;
		}
		while (sz1-->0) {  // just append leftover nodes from longer list
			cur.next=l1;
			cur=l1;
			l1=l1.next;
		}
		cur.next=null;  // terminate the list
		return head;
	}
	ListNode mergeSort(ListNode head, int size)
	{
		if (size==1)  // terminal condition, one element is sorted
			return head;
		ListNode second=head;
		int n=size/2;
		for (int i=0; i<n; i++)
			second=second.next;
		head=mergeSort(head, n);  // sort first half
		second = mergeSort(second, size-n);// sort second half
		return merge(head, n, second, size-n);  // merge 2 sorted halves
	}
    public ListNode sortList(ListNode head) {
        int count=0;
        ListNode cur=head;
        while (cur!=null) {
        	count++;
        	cur=cur.next;
        }
        if (count<2)
        	return head;
        return mergeSort(head, count);
    }

    public static void main(String[] args)
    {
    	ListNode head = ListNode.asList(new int[] {4,2,1,3});
    	head = new MergeSort().sortList(head);
    	ListNode.print(head);
    	head = ListNode.asList(new int[] {-1,5,3,4,0});
    	head = new MergeSort().sortList(head);
    	ListNode.print(head);
    }
}
