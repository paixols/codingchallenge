public class MergeSortedLists {

    public static void main(String[] args) {

        //[1,3,5,6]
        ListNode l4 = new ListNode(6, null);
        ListNode l3 = new ListNode(5, l4);
        ListNode l2 = new ListNode(3, l3);
        ListNode l1 = new ListNode(1, l2);
        //[2,4,6,20,34]
        ListNode j5 = new ListNode(34, null);
        ListNode j4 = new ListNode(20, j5);
        ListNode j3 = new ListNode(6, j4);
        ListNode j2 = new ListNode(4, j3);
        ListNode j1 = new ListNode(2, j2);

        mergeSingleLinkedLists(l1, j1);

    }

    /*
     * Definition for singly-linked list [ListNode]
     * */
    public static class ListNode {
        int data;
        ListNode next;

        ListNode() {
        }

        ListNode(int data) {
            this.data = data;
        }

        ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    /*
    * Merge 2 single linked lists
    * */
    public static ListNode mergeSingleLinkedLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.data < l2.data) {
            l1.next = mergeSingleLinkedLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeSingleLinkedLists(l1, l2.next);
            return l2;
        }
    }


}
