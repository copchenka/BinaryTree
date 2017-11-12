import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.SortedSet;


public class Tests {

    @org.junit.Test
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
        SortedSet<Integer> hSet = tree.headSet(4);
        assertEquals(3, hSet.size());
        assertTrue(hSet.contains(3));
        assertTrue(hSet.contains(1));
        assertTrue(hSet.contains(2));


        tree1.add(8);
        tree1.add(2);
        tree1.add(4);
        tree1.add(7);
        tree1.add(11);
        tree1.add(6);
        tree1.add(9);
        tree1.add(1);
        SortedSet<Integer> hSet1 = tree1.headSet(8);
        assertEquals(5, hSet1.size());
        assertTrue(hSet1.contains(7));
        assertTrue(hSet1.contains(2));
        assertTrue(hSet1.contains(4));
        assertTrue(hSet1.contains(6));
        assertTrue(hSet1.contains(1));
    }

}
