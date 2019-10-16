package queues;

        import java.util.Iterator;
        import java.util.NoSuchElementException;

/**
 * class Queue which implements Iterable. Objects of type Queue manage items in a singly linked list
 * where we can enqueue() from the front and dequeue() items from the end of the queue.
 * Created by Fu on 29/1/2018.
 */
public class Queue<T> {
    private String name;
    private Node head;
    private Node tail;
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
            current = head;
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
     * Constructs an empty Queue object.
     */
    public Queue() {
        this.head = null;
        this.tail = null;
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
     * Accessor methods
     * @return name of the list.
     */
    public String getName() {
        return name;
    }

    /**
     * Takes a generic item as the argument and adds the item to the end of the stack.
     * @param newData  a generic item
     */
    public void enqueue(T newData) {
        Node<T> current = new Node<T>(newData);
        if (this.isEmpty()) {
            head = current;
            tail = current;
            this.size++;
        } else {
            Node<T> walker = head;
            while (walker.getNext() != null) {
                walker = walker.getNext();
            }
            walker.setNext(current);
            this.size++;
            tail = current;
        }
    }

    /**
     * Receives no arguments and removes the item from the front of the queue.
     * @return  a generic item
     */
    public T dequeue() throws NoSuchElementException {
        Node<T> current;
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node after = head.getNext();
            current = head;
            head = after;
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
            current = tail;
            return current.getData();
        }
    }

    /**
     * Checks if top is pointing to any node.
     */
    public boolean isEmpty() {
        if (this.head == null)
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
        String toString = name + " with " + size + " songs:\n[";
        Node<T> walker = head;
        if (walker != null) {
            toString += walker.getData();
            walker = walker.getNext();
        }
        while (walker != null) {
            toString += ",\n" + walker.getData();
            walker = walker.getNext();
        }
        toString += "]";
        return toString;
    }
}
