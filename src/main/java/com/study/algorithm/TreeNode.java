package com.study.algorithm;

import java.util.LinkedList;
import java.util.Stack;

public class TreeNode {
    public int data;
    public TreeNode leftChild;
    public TreeNode rightChild;

    public TreeNode(int data){
        this.data = data;
    }

    /**
     * 构建二叉树
     * @param list   输入序列
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> list){
        TreeNode node = null;
        if(list == null || list.isEmpty()){
            return null;
        }
        Integer data = list.removeFirst();
        if(data!=null){
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(list);
            node.rightChild = createBinaryTree(list);
        }
        return node;
    }

    public static void main(String[] args) {
//        LinkedList<Integer> list = new LinkedList<>();
//        list.add(5);
//        list.add(3);
//        list.add(2);
//        list.add(1);
//        list.add(4);
//        TreeNode node = createBinaryTree(list);
        TreeNode node = new TreeNode(2);
        node.rightChild = new TreeNode(1);
        node.leftChild = new TreeNode(3);
        node.leftChild.leftChild = new TreeNode(5);
        node.rightChild.rightChild = new TreeNode(4);
        //             2
        //          3      1
        //        5           4


        postOrder(node);
    }

    /**
     * 二叉树前序遍历   根-> 左-> 右
     * @param node    二叉树节点
     */
    public static void preOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        System.out.print(node.data+" ");
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树前序遍历   根-> 左-> 右
     * @param node    二叉树节点
     */
    public static void preOrderN(TreeNode node){
        if(node == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()){
            TreeNode p = stack.pop();
            System.out.print(p.data+" ");
            if(p.rightChild != null){
                stack.push(p.rightChild);
            }
            if(p.leftChild != null){
                stack.push(p.leftChild);
            }
        }
    }
    // 先序遍历非递归
    public static void preOrder2(TreeNode t) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (t != null || !s.empty()) {
            while (t != null) {
                System.out.print(t.data + "");
                s.push(t);
                t = t.leftChild;
            }
            if (!s.empty()) {
                t = s.pop();
                t = t.rightChild;
            }
        }
    }


    /**
     * 二叉树前序遍历   左-> 根-> 右
     * @param node    二叉树节点
     */
    public static void midOrderN(TreeNode node){
        if(node == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp = node;
        while (tmp != null || !stack.empty() ){
            //1.将根节点入栈
            //2.将所有左孩子入栈
            while(tmp!=null) {
                stack.push(tmp);
                tmp=tmp.leftChild;
            }
            tmp = stack.pop();
            System.out.print(tmp.data+" ");
            //4.如果栈顶元素存在右孩子，则将右孩子赋值给tmp，也就是将右孩子入栈 //否则，将tmp置为null，表示下次要访问的是栈顶元素
            if(tmp.rightChild!=null) {
                tmp=tmp.rightChild;
            } else {
                tmp=null;
            }
        }
    }


    // 中序遍历非递归
    public static void InOrder2(TreeNode t) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (t != null || !s.empty()) {
            while (t != null) {
                s.push(t);
                t = t.leftChild;
            }
            if (!s.empty()) {
                t = s.pop();
                System.out.print(t.data);
                t = t.rightChild;
            }
        }
    }

    // 后序遍历非递归
    public static void PostOrder2(TreeNode t) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<Integer> s2 = new Stack<Integer>();
        Integer i = new Integer(1);
        while (t != null || !s.empty()) {
            while (t != null) {
                s.push(t);
                s2.push(new Integer(0));
                t = t.leftChild;
            }
            while (!s.empty() && s2.peek().equals(i)) {
                s2.pop();
                System.out.print(s.pop().data);
            }
            if (!s.empty()) {
                s2.pop();
                s2.push(new Integer(1));
                t = s.peek();
                t = t.rightChild;
            }
        }
    }

    public static void postOrder(TreeNode root) {
        if(root==null) {
            System.out.println("空树");
            return;
        }
        TreeNode tmp=root;  //当前节点
        TreeNode prev=null; //上一次访问的节点
        Stack<TreeNode> s=new Stack<TreeNode>();
        while(tmp!=null || !s.empty()) {
            //1.将根节点及其左孩子入栈
            while(tmp!=null) {
                s.push(tmp);
                tmp=tmp.leftChild;
            }

            if(!s.empty()) {
                //2.获取栈顶元素值
                tmp=s.peek();
                //3.没有右孩子，或者右孩子已经被访问过
                if(tmp.rightChild==null || tmp.rightChild==prev) {
                    //则可以访问栈顶元素
                    tmp=s.pop();
                    System.out.print(tmp.data+" ");
                    //标记上一次访问的节点
                    prev=tmp;
                    tmp=null;
                }
                //4.存在没有被访问的右孩子
                else {
                    tmp=tmp.rightChild;
                }
            }
        }
        System.out.println();
    }

}
