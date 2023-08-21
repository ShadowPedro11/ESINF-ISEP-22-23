
package PL;

import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author DEI-ESINF
 */
public class TREE_WORDS extends BST<TextWord> {
    
    public void createTree() throws FileNotFoundException{
        Scanner readfile = new Scanner(new File("PL_BST-AVL_for_students/src/PL/xxx.xxx"));
        while(readfile.hasNextLine()){
            String[] pal = readfile.nextLine().split("(\\,)|(\\s)|(\\.)");
            for(String word : pal)
                if (word.length() > 0 )
                    insert(new TextWord(word, 1));
        }
        readfile.close();
    }

    /**
     * Inserts a new word in the tree, or increments the number of its occurrences.
       * @param element
     */
    @Override
    public void insert(TextWord element){
        insert(element,root);
    }
    
    private Node<TextWord> insert(TextWord element, Node<TextWord> node){
        if (node==null){
         return new Node<>(element,null,null);
        }

        if (node.getElement().compareTo(element)==0){
            node.setElement(element);
            element.incOcorrences();
        }

        if (node.getElement().compareTo(element)>0){
            node.setLeft(insert(element, node.getLeft()));
        }else {
            node.setRight(insert(element,node.getRight()));
        }

        return node;
    }

    /**
     * Returns a map with a list of words for each occurrence found.
     * @return a map with a list of words for each occurrence found.
     */
    public Map<Integer,List<String>> getWordsOccurrences(){
        Map<Integer,List<String>> map = new HashMap<>();
        Iterable<TextWord> list = inOrder();

        for (TextWord c : list){
            Integer occ = c.getOcorrences();
            if(map.get(occ)==null){
                map.put(occ, new ArrayList<>());
            }
            map.get(occ).add(c.getWord());
        }

        return map;

    }

}
