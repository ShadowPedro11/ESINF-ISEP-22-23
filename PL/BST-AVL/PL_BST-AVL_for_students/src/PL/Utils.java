
package PL;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEI-ESINF
 */
public class Utils {
    public static <E extends Comparable<E>> Iterable<E> sortByBST(List<E> listUnsorted){
        BST tree = new BST();
        for (E c : listUnsorted){
            tree.insert(c);
        }
        return tree.inOrder();
    }    
}
