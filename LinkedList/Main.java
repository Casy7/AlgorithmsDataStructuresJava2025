
public class Main {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        Solution solver = new Solution();
        ListNode mergedHead = solver.mergeTwoLists(list1, list2);

    }

}
