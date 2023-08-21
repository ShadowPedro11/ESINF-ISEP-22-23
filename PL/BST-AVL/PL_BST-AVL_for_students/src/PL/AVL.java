/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import org.w3c.dom.Node;

/**
 *
 * @author DEI-ESINF
 * @param <E>
 */
public class AVL <E extends Comparable<E>> extends BST<E> {
    
    
    private int balanceFactor(Node<E> node){
        return (height(node.getRight())-height(node.getLeft()));
    }
    
    private Node<E> rightRotation(Node<E> node){
        Node<E> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        return left;
    }
    
    private Node<E> leftRotation(Node<E> node){
        Node<E> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return right;
    }
    
    private Node<E> twoRotations(Node<E> node){
        if(balanceFactor(node)<0){
            node.setLeft(leftRotation(node.getLeft()));
            node=rightRotation(node);
        }else {
            node.setRight(rightRotation(node.getRight()));
            node=leftRotation(node);
        }
        return node;
    }
    
    private Node<E> balanceNode(Node<E> node) {
        int BF = balanceFactor(node);
        if(BF>1 && node.getRight()!=null){
            if(balanceFactor(node.getRight())<0){
                return twoRotations(node);
            }else {
                return leftRotation(node);
            }
        }else if(BF<-1 && node.getLeft()!=null){
            if(balanceFactor(node.getLeft())>0){
                return twoRotations(node);
            }else {
                return rightRotation(node);
            }
        }
        return node;
    }
    
    @Override
    public void insert(E element){
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node){
        if(node == null){
            return new Node<>(element,null,null);
        }
        if(node.getElement()==element){
            node.setElement(element);
        }
        if(node.getElement().compareTo(element)>0){
            node.setLeft(insert(element,node.getLeft()));
            node = balanceNode(node);
        }else{
            node.setRight(insert(element,node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }
    
    @Override  
    public void remove(E element){
        root = remove(element, root());
    }

    private Node<E> remove(E element, BST.Node<E> node) {
        if(node==null){
            return new Node<>(element,null,null);
        }
        if(node.getElement()==element){
            if (node.getRight() == null && node.getRight() == null){
                return null;
            }
            if (node.getRight()==null){
                return node.getLeft();
            }
            if (node.getLeft()==null){
                return node.getRight();
            }
            E smallestElement = smallestElement(node.getRight());
            node.setElement(smallestElement);
            node.setRight(remove(smallestElement,node.getRight()));
            node = balanceNode(node);
        }
        if(node.getElement().compareTo(element)>0){
            node.setLeft(remove(element,node.getLeft()));
            node = balanceNode(node);
        }else{
            node.setRight(remove(element,node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }
    
    
    public boolean equals(Object otherObj) {

        if (this == otherObj) 
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        AVL<E> second = (AVL<E>) otherObj;
        return equals(root, second.root);
    }

    public boolean equals(Node<E> root1, Node<E> root2) {
        if (root1 == null && root2 == null) 
           return true;
        else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else  
                return false; 
        }
        else return false;
    }
   
}