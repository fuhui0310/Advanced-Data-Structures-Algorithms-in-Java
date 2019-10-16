package stacks;

import java.util.Iterator;


/**
 * Class which implements Iterable. Objects of type StackList manage items in a singly linked list
 * where we can only push() and pop() items from top of the stack.
 * Created by Fu on 24/1/2018.
 */
public class StackList<T> {
    private String name;
    private Node top;
    private int size = 0;

    private class Node<T> {
        private T data;
        private Node next;

        /**
         * Constructs an object to hold a object.
         * and point to null as the next node.
         *
         * @param data
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        /**
         * Constructs an object to hold a object.
         * and point to another data as the next node.
         *
         * @param data        The data portion of this node.
         * @param anotherNode The node following this node.
         */
        public Node(T data, Node<T> anotherNode) {
            this.data = data;
            this.next = anotherNode;
        }

        /**
         * Mutator method sets the data.
         *
         * @param anotherData The node replacing this node.
         */
        public void setData(T anotherData) {
            this.data = anotherData;
        }

        /**
         * Mutator method sets the next node.
         *
         * @param anotherNode The node following this node.
         */
        public void setNext(Node<T> anotherNode) {
            this.next = anotherNode;
        }

        /**
         * Mutator method returns the data portion of the node.
         *
         * @return the data object
         */
        public T getData() {
            return this.data;
        }

        /**
         * Mutator method get the next node.
         *
         * @return the next node
         */
        public Node<T> getNext() {
            return this.next;
        }

        /**
         * Returns a representation in type String.
         *
         * @return the data.
         */
        @Override
        public String toString() {
            String result = "";
            result += this.data;
            return result;
        }
    }

    /**
     * Nested class, which iterates over the elements of the outer class.
     */
    private class ListIterator implements Iterator<T> {
        private Node current;

        public ListIterator() {
            current = top;
        }

        public boolean hasNext() {
            if (current == null)
                return false;
            return true;
        }

        public T next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            T data = (T) current.getData();
            current = current.getNext();
            return data;
        }
    }

    /**
     * Constructs an empty StackList object.
     */
    public StackList() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Sets the name of the list.
     * @param name the name of the list
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Takes a generic item as the argument and adds the item to the top of the stack.
     * @param newData  a generic item
     */
    public void push(T newData) {
        Node<T> current = new Node<T>(newData);
        Node after = top;
        if (this.isEmpty()) {
            top = current;
            this.size++;
        } else {
            top = current;
            top.setNext(after);
            this.size++;
        }
    }

    /**
     * Receives no arguments and removes the item from the top of the stack.
     * This method should return the generic item popped.
     * @return  a generic item
     */
    public T pop() {
        Node<T> current;
        Node after = top.getNext();
        if (this.isEmpty()) {
            return null;
        } else {
            current = top;
            top = after;
            size--;
            return current.getData();
        }
    }

    /**
     * Looks at the top of the stack and returns a generic type for the data seen at the top of the stack.
     * @return  a generic item
     */
    public T peek() {
        Node<T> current;
        if (this.isEmpty()) {
            return null;
        } else {
            current = top;
            return current.getData();
        }
    }

    /**
     * Checks if top is pointing to any node.
     */
    public boolean isEmpty() {
        if (this.top == null)
            return true;
        return false;
    }


    /**
     * Returns the number of nodes in the list.
     *
     * @return size
     */
    public int size() {
        return this.size;
    }


    public Iterator<T> iterator() {
        return new ListIterator();
    }

    /**
     * Returns a representation  in type String.
     *
     * @return the list of countries.
     */
    @Override
    public String toString() {
        String toString = name + " with " + size + " links:\n[";
        Node<T> walker = top;
        if (walker != null) {
            toString += walker.getData();
            walker = walker.getNext();
        }
        while (walker != null) {
            toString += ", " + walker.getData();
            walker = walker.getNext();
        }
        toString += "]";
        return toString;
    }
}
