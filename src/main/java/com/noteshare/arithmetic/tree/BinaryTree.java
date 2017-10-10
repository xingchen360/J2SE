package com.noteshare.arithmetic.tree;

import com.noteshare.arithmetic.tree.model.Node;

/**
* @Title:  二叉树相关测试代码
* @author  NoteShare
* @since   JDK1.8
* @history 2017年10月10日
*/
public class BinaryTree {
    public static void main(String[] args) {
        
    }
    /**
     * 先序遍历，该节点-左孩子节点-右孩子节点
     * @param node
     */
    public void preOrder(Node<Integer> node){
        if(null != node){
            System.out.println(node.getData());
        }
        preOrder(node.getLeftChild());
        preOrder(node.getRightChild());
    }
    /**
     * 中序遍历：做孩子节点-该节点-右孩子节点
     * @param node
     */
    public void inOrder(Node<Integer> node){
        if(null == node){
            return;
        }
        inOrder(node.getLeftChild());
        System.out.println(node.getData());
        inOrder(node.getRightChild());
    }
    /**
     * 后序遍历：左节点-右节点-该节点
     * @param node
     */
    public void postOrder(Node<Integer> node){
        if(null == node){
            return ;
        }
        postOrder(node.getLeftChild());
        postOrder(node.getRightChild());
        System.out.println(node.getData());
    }
}
