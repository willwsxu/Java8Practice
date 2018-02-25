/*
 * given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 */
package leetcode;
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class SingleLinkedList {
    static public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode head = null;
        ListNode cur=head;
        int carry=0;
        do {
            if (head==null) {
                head = new ListNode(0);
                cur=head;
                head.next=null;
            } else {
                cur.next = new ListNode(0);
                cur = cur.next;
                cur.next=null;
            }
            int sum = l1.val+l2.val+carry;
            cur.val = sum%10;
            carry = sum/10;
            l1 = l1.next;
            l2 = l2.next;
        } while (l1!=null && l2!=null);
        if (l1==null)
            l1=l2;
        while (l1 != null ) {
            cur.next = new ListNode(l1.val);
            cur = cur.next;
            cur.next=null;   
            if ( carry>0) {
                int sum=l1.val+carry;
                cur.val = sum%10;
                carry = sum/10;
            }
            l1 = l1.next;
        }
        if ( carry>0) {
            cur.next = new ListNode(carry);
            cur.next.next=null;
        }
        return head;
    }
    
    static ListNode create(int[] val)
    {
        ListNode head = new ListNode(0);
        head.next=null;
        ListNode prev=head;
        for (int v : val) {
            prev.next = new ListNode(v);
            prev=prev.next;
            prev.next=null;
        }
        return head.next;
    }
    static void print(ListNode ln)
    {
        while (ln != null) {
            System.out.print(ln.val);
            System.out.print(" ");
            ln = ln.next;
        }
        System.out.println();
    }
    
    static public ListNode removeNthFromEnd(ListNode head, int n) {
        // two pointers
        ListNode nth=head;  // n+1 the node from last
        ListNode last=head;
        while (n-->0 && last!=null)
            last=last.next;
        if (last == null)
            return head.next;
        while (last.next !=null) { // find n+1 node from end
            last =last.next;
            nth=nth.next;
        }
        nth.next=nth.next.next;
        return head;
    }

    static void testAddTwoNumber()
    {
        ListNode l1=create(new int[]{2,4,9,9});
        ListNode l2=create(new int[]{5,6,4});
        ListNode sum=addTwoNumbers(l1,l2);
        print(sum);        
    }
    public static void main(String[] args)
    {
        ListNode l1=create(new int[]{2,4,8,9});
        ListNode ans=removeNthFromEnd(l1, 3);
        print(ans);
        l1=create(new int[]{2,4,8,9});
        ans=removeNthFromEnd(l1, 4);
        print(ans);
        l1=create(new int[]{2,4,8,9});
        ans=removeNthFromEnd(l1, 1);
        print(ans);
        l1=create(new int[]{2});
        ans=removeNthFromEnd(l1, 1);
        print(ans);
    }
}
