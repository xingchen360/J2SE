package com.noteshare.arithmetic.tree.model;


/**
* @Title:  二叉树节点定义
* @author  NoteShare
* @since   JDK1.8
* @history 2017年10月10日
*/
public class Node<T> {
    /**
     * 左孩子节点
     */
    private Node<T> leftChild;
    /**
     * 右孩子节点
     */
    private Node<T> rightChild;
    /**
     * 当前节点数据
     */
    T data;
    public Node<T> getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }
    public Node<T> getRightChild() {
        return rightChild;
    }
    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
