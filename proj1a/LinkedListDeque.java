/**
 * @auther YLF
 */
public class LinkedListDeque<T> {

    private TNode sentinel;
    private int length;

    /**
     * Sort of "Naked data structure"
     */
    private class TNode {
        private TNode prev;
        private TNode next;
        private T item;

        TNode(TNode p, T content, TNode n) {
            prev = p;
            item = content;
            next = n;
        }
    }


    /**
     * Start from a circular sentinel TNode, both prev and next point to itself.
     */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        length = 0;
    }


    /**
     * create a deep copy of other
     */
    /*public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        length = 0;
        TNode p = other.sentinel.next;
        TNode slow = sentinel;
        while (p != other.sentinel) {
            TNode freshTNode = new TNode(null, p.item, null);
            freshTNode.prev = slow;
            slow.next = freshTNode;
            slow = slow.next;
            p = p.next;
        }
        length = other.length;
        slow.next = sentinel;
        slow = sentinel.prev;
    }*/


    /**
     * Create a deep copy of other
     *
     * @Source youtube
     */
    /*public LinkedListDeque(LinkedListDeque other) {
        this.sentinel = new TNode(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        this.length = 0;

        for (int i = 0; i < other.size(); i += 1) {
            addLast((T) other.get(i));
        }
    }*/

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T it) {
        TNode freshTNode = new TNode(null, it, null);
        TNode p = sentinel.next;
        sentinel.next = freshTNode;
        freshTNode.prev = sentinel;
        freshTNode.next = p;
        p.prev = freshTNode;
        length += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T it) {
        TNode freshTNode = new TNode(null, it, null);
        TNode p = sentinel.prev;
        sentinel.prev = freshTNode;
        freshTNode.next = sentinel;
        freshTNode.prev = p;
        p.next = freshTNode;
        length += 1;
    }

    /**
     * To determine the deque is empty or not.
     */
    public boolean isEmpty() {
        return ((sentinel.next == sentinel)
                && (sentinel.prev == sentinel));
    }

    /**
     * return the size of deque.
     */
    public int size() {
        return length;
    }

    /**
     * print the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        TNode curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println(" ");
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (sentinel.next != sentinel) {
            TNode p = sentinel.next;
            sentinel.next = p.next;
            p.next.prev = sentinel;
            p.next = null;
            p.prev = null;
            length -= 1;
            return p.item;
        } else {
            return null;
        }
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (sentinel.prev != sentinel) {
            TNode p = sentinel.prev;
            sentinel.prev = p.prev;
            p.prev.next = sentinel;
            p.prev = null;
            p.next = null;
            length -= 1;
            return p.item;
        } else {
            return null;
        }
    }

    /**
     * Get the item at given index
     */
    public T get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        if (index > this.size()) {
            return null;
        }
        int forwardIdx = 0;
        int backwardIdx = length - 1;
        TNode forwardPivot = sentinel.next;
        TNode backwardPivot = sentinel.prev;
        while ((forwardIdx != index) && (backwardIdx != index)) {
            forwardPivot = forwardPivot.next;
            backwardPivot = backwardPivot.prev;
            forwardIdx++;
            backwardIdx--;
        }
        return forwardIdx == index ? forwardPivot.item : backwardPivot.item;
    }

    /**
     * Get the item at given index recursively
     */
    public T getRecursive(int index) {
        if (this.isEmpty()) {
            return null;
        }
        if (index > this.size()) {
            return null;
        }

        //exclude empty input, then searching recursively.
        TNode recursivePivot = sentinel.next;
        return recursiveHelper(index, 0, recursivePivot);
    }

    private T recursiveHelper(int idx, int currIdx, TNode curr) {
        if (currIdx == idx) {
            return curr.item;
        }
        curr = curr.next;
        currIdx++;
        return recursiveHelper(idx, currIdx, curr);
    }
}
