import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {
        }

        private Node<T> findNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        SortedSet<T> subtree = new TreeSet<>();
        Node<T> node = root;
        if (toElement == null) throw new IllegalArgumentException();
        else {
            if (node == null) throw new NoSuchElementException();
            if (node.value.compareTo(toElement) < 0) {
                subtree.add(node.value);
                if (node.left != null) {
                    subtree.add(node.left.value);
                    subH(node, subtree);
                }
                if (node.right != null)
                    node = node.right;
                subH(node, subtree);
            } else if (node.value.compareTo(toElement) > 0) {
                if (node.left != null)
                    node = node.left;
            } else if (node.value.compareTo(toElement) == 0) {
                node = node.left;
                subtree.add(node.value);
                subH(node, subtree);
            }
        }
        return subtree;
    }

    private void subH(Node<T> node1, SortedSet<T> set) {
        if (node1.left != null) {
            set.add((node1.left).value);
            subH(node1.left, set);
        }
        if (node1.right != null) {
            set.add((node1.right).value);
            subH(node1.right, set);
        }
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        SortedSet<T> subtree = new TreeSet<>();
        Node<T> node = root;
        if (fromElement == null) throw new NullPointerException();
        else {
            if (node.value == null) throw new NoSuchElementException();
            else {
                if (node.value.compareTo(fromElement) > 0) {
                    subtree.add(node.value);
                    if (node.right != null) {
                        subtree.add((node.right).value);
                        subH(node.right, subtree);
                    }
                    if (node.left != null)
                        node = node.left;
                } else if (node.value.compareTo(fromElement) < 0) {
                    if (node.right != null)
                        node = node.right;
                } else if (node.value.compareTo(fromElement) == 0) {
                    if (node.right != null) {
                        subtree.add((node.right).value);
                        subH(node.right, subtree);
                    }
                }
            }
        }
        return subtree;
    }


    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}

