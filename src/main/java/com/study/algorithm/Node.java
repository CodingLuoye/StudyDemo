package com.study.algorithm;

/**
 * java 链表 翻转
 */
public class Node {
	public int value;
	public Node next;

	public Node(int data) {
		this.value = data;
	}


	public static void main(String[] args) {

		Node node = new Node(3);
		node.next = new Node(2);
		node.next.next = new Node(1);
		node.next.next.next = new Node(4);

//		Node node1 = bianli(node);
//		node1 = reverse(node1);
		Node node1 = swapPairs2(node);
		while(node1 != null){
			System.out.println(node1.value);
			node1 = node1.next;
		}

	}

	/**
	 *
	 * @param head
	 * @return
	 */
	public static Node swapPairs2(Node head) {
		Node root=head;
		if(head!=null&&head.next!=null){
			root=head.next;
			Node pre=head;
			Node current = null;
			Node next = null;
			while(head!=null&&head.next!=null){
				current = head.next;
				next = current.next;
				pre.next = current;
				current.next = head;
				head.next = next;
				pre = head;
				head = next;
			}
		}
		return root;
	}


	/**
	 * 改变相邻节点的值
	 * @param head
	 * @return
	 */
	public static Node swapPairs(Node head) {
		Node current = head;
		while(current!=null){
			Node next = current.next;
			if(next!=null){
				int temp=current.value;
				current.value=next.value;
				next.value=temp;
				current=current.next;
			}
			current=current.next;
		}
		return head;
	}

	/**
	 * 通过递归实现
	 * @param head
	 * @return
	 */
	public static Node reverse(Node head) {
		if (head == null || head.next == null){
			return head;
		}
		Node temp = head.next;
		Node newHead = reverse(head.next);
		temp.next = head;
		head.next = null;
		return newHead;
	}

	/**
	 * 通过遍历实现
	 * @param node
	 * @return
	 */
	public static Node bianli(Node node) {
		if (node == null || node.next == null){
			return node;
		}
		Node pre = null;
		Node next = null;
		while (node != null){
			next = node.next;
			node.next = pre;
			pre = node;
			node = next;
		}
		return pre;

	}

}