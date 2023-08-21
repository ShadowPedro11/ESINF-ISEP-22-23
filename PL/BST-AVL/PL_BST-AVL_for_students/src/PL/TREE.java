
package PL;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

/*
 * @author DEI-ESINF
 * @param <E>
 */

public class TREE<E extends Comparable<E>> extends BST<E>{
 
   /*
   * @param element A valid element within the tree
   * @return true if the element exists in tree false otherwise
   */   
    public boolean contains(E element) {
         if(find(root,element)==null){
             return false;
         }else {
             return true;
         }
    }

 
    public boolean isLeaf(E element){
        Node<E> node = find(root,element);
        if(element==null || node==null){
            return false;
        }

         if(node.getLeft()==null && node.getRight()==null){
             return true;
         }else {
             return false;
         }

    }

   /*
   * build a list with all elements of the tree. The elements in the 
   * left subtree in ascending order and the elements in the right subtree 
   * in descending order. 
   *
   * @return    returns a list with the elements of the left subtree 
   * in ascending order and the elements in the right subtree is descending order.
   */
    public Iterable<E> ascdes(){
        List<E> nodeList = new ArrayList<>();
        if(root!=null){
            ascSubtree(root.getLeft(),nodeList);
            nodeList.add(root.getElement());
            desSubtree(root.getRight(),nodeList);
        }
        return nodeList;
    }

    private void ascSubtree(Node<E> node, List<E> snapshot) {

    }
    
    private void desSubtree(Node<E> node, List<E> snapshot) {

    }
   
    /**
    * Returns the tree without leaves.
    * @return tree without leaves
    */
    public BST<E> autumnTree() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Node<E> copyRec(Node<E> node){
           throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
    * @return the the number of nodes by level.
    */
    public int[] numNodesByLevel(){
        int[] array = new int[this.height()];
        numNodesByLevel(root,array,0);
        return array;
    }
    
    private void numNodesByLevel(Node<E> node, int[] result, int level){
        if (node==null){
            return;
        }
        result[level]++;

        numNodesByLevel(node.getLeft(),result,level+1);

        numNodesByLevel(node.getRight(),result,level+1);

    }
    
    public boolean perfectBalanced(){
       return perfectBalanced(root);
    }
    
    private boolean perfectBalanced(Node<E> node){
        
        if (node==null){
            return true;
        }
        return perfectBalanced(node.getLeft()) &&  perfectBalanced(node.getRight());
    }
    
    
    public E lowestCommonAncestor(E elem1, E elem2){
        Node<E> n = lowestCommonAncestor(root,elem1,elem2);
        if(n==null){
            return null;
        }
        return n.getElement();
    }
    
    private Node<E> lowestCommonAncestor(Node<E> node, E elem1, E elem2) {
        if (node == null) {
            return null;
        }
        int cmp1 = elem1.compareTo(node.getElement());
        int cmp2 = elem2.compareTo(node.getElement());
        if (cmp1 * cmp2 <= 0) {
            return node;
        }
        if (cmp1 < 0) {
            return lowestCommonAncestor(node.getLeft(), elem1, elem2);
        } else {
            return lowestCommonAncestor(node.getRight(), elem1, elem2);
        }
    }
    
    public BST<E>  minCompletSubtree(E elem1, E elem2){

        Node<E> node = lowestCommonAncestor(root,elem1,elem2);
        ArrayList listInOrder = new ArrayList();
        posOrderSubtree(node,listInOrder);
        return construcTreeposOrder(listInOrder);
    }
    

    public BST<E>  construcTreeposOrder (ArrayList<E> posOrder){
        BST<E> newBST = new TREE<>();
        for (E el : posOrder){
            newBST.insert(el);
        }
        return newBST;
    }
}
