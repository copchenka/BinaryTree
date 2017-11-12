import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.SortedSet;


public class Tests {

    @Test
    public void set() throws Exception {
        BinaryTree tree = new BinaryTree();
        BinaryTree tree1 = new BinaryTree();
        tree.add(4);
        tree.add(7);
        tree.add(3);
        tree.add(6);
        tree.add(1);
        tree.add(8);
        tree.add(10);
        tree.add(2);
        SortedSet<Integer> headSet = tree.headSet(4);
        assertEquals(3, headSet.size());
        assertTrue(headSet.contains(3));
        assertTrue(headSet.contains(1));
        assertTrue(headSet.contains(2));


        tree1.add(8);
        tree1.add(2);
        tree1.add(4);
        tree1.add(7);
        tree1.add(11);
        tree1.add(6);
        tree1.add(9);
        tree1.add(1);
        SortedSet<Integer> headSet1 = tree1.headSet(8);
        assertEquals(5, headSet1.size());
        assertTrue(headSet1.contains(7));
        assertTrue(headSet1.contains(2));
        assertTrue(headSet1.contains(4));
        assertTrue(headSet1.contains(6));
        assertTrue(headSet1.contains(1));


        SortedSet<Integer> tailSet = tree.tailSet(4);
        assertEquals(4, tailSet.size());
        assertTrue(tailSet.contains(7));
        assertTrue(tailSet.contains(6));
        assertTrue(tailSet.contains(8));
        assertTrue(tailSet.contains(10));


        SortedSet<Integer> tailSet1 = tree1.tailSet(8);
        assertEquals(2, tailSet1.size());
        assertTrue(tailSet1.contains(11));
        assertTrue(tailSet1.contains(9));

    }

}
