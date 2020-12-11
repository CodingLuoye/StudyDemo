package com.study.algorithm;
public class ListNode {
    int data;
    ListNode next;
    ListNode(int data,ListNode next){
        this.data = data;
        this.next = next;
    }
    @Override
    public String toString() {
        ListNode node = next;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(data);
        stringBuffer.append( " -> ");
        while (node !=null){
            stringBuffer.append(node.data);
            node = node.next;
            if(node != null){
                stringBuffer.append( " -> ");
            }
        }
        return stringBuffer.toString();
    }
    public static  ListNode fromArray(int[] arr){
        if(arr.length == 0){
            return  null;
        }
        ListNode root = new ListNode(arr[0],null);
        if(arr.length == 1){
            return root;
        }
        ListNode other = root;
        for(int i = 1;i< arr.length;i++){
            ListNode temp = new ListNode(arr[i],null);
            other.next = temp;
            other = temp;
        }
        return root;
    }

    /**
     * 删除链表指定元素
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {

        while (head != null && head.data == val) {
            head = head.next;
        }
        if(head == null){
            return null;
        }
        ListNode cur = head;
        ListNode prev = null;
        while(cur != null){
            if(cur.data == val){
                prev.next = cur.next;
            }else{
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }
    public static void main(String[] args) {
//        int [] arr = new int[]{1,1};
//        ListNode listNode = ListNode.fromArray(arr);
////        System.out.println(listNode.toString());
//        ListNode listNode1 = listNode.removeElements(listNode,1);
//        System.out.println(listNode1.toString());
    }

}