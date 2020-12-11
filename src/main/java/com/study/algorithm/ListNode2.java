package com.study.algorithm;
public class ListNode2 {
    int val;
     ListNode2 next;
     ListNode2(int x) { val = x; }

    @Override
    public String toString() {
        ListNode2 node = next;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(val);
        stringBuffer.append( " -> ");
        while (node !=null){
            stringBuffer.append(node.val);
            node = node.next;
            if(node != null){
                stringBuffer.append( " -> ");
            }
        }
        return stringBuffer.toString();
    }

    public static ListNode2 addTwoNumbers(ListNode2 l1, ListNode2 l2) {
        ListNode2 root = new ListNode2(0);
        ListNode2 cursor = root;
        int carry = 0;
        while(l1 !=null || l2 !=null || carry != 0){
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode2 sumNode = new ListNode2(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if(l1 != null){ l1 = l1.next;}
            if(l2 != null){ l2 = l2.next;}

        }
        return root.next;
    }
    public static void main(String[] args) {
        ListNode2 listNode1 = new ListNode2(2);
        ListNode2 listNode12 = new ListNode2(4);
        ListNode2 listNode13 = new ListNode2(4);
        listNode1.next = listNode12;
        listNode12.next = listNode13;
        ListNode2 listNode2 = new ListNode2(5);
        ListNode2 listNode21 = new ListNode2(6);
        ListNode2 listNode22 = new ListNode2(4);
        listNode2.next = listNode21;
        listNode21.next= listNode22;
        ListNode2 root = ListNode2.addTwoNumbers(listNode1,listNode2);
        System.out.println(listNode1.toString());
        System.out.println(listNode2.toString());
        System.out.println(root.toString());
    }


}