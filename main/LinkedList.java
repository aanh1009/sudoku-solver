/*
 * Tuan Anh Ngo
 * LinkedList.java: create a LinkedList class that implements the Iterator and Queue interfaces with methods to get, add, and delete certain items in the data structure
 */
import java.util.Iterator;
public class LinkedList<T> implements Iterable<T>, Stack<T>{
    /**
     * define the Iterator interface with hasNext() anf next() methods
     */
    private class LinkedListIterator implements Iterator<T> {
        private Node current;
        public LinkedListIterator() {
            this.current = head;
        }
        public boolean hasNext() {
            return current != null;
        }
        public T next() {
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }

    /**
     * create an Iterator object by calling the LinkedListIterator constructor
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * define a Node class with getters and setters for each Node's data and their linked Node
     */
    private class Node{
        //initialize the instance fields, including the data of the current Node and the following Node
        Node next;
        T data;

        /**
         * create a Constructor for a Node 
         * @param next the next Node
         * @param item the data stored in the Node
         */
        public Node(Node next, T item){
            this.next = next;
            this.data = item;
        }

        /**
         * create a Constructor for a Node with no next Node
         */
        public Node(T item){
            this(null, item);
        }

        /**
         * get the data in the current Node
         * @return the data in the current Node
         */
        public T getData(){
            return this.data;
        }

        /**
         * set the next Node
         * @param n the next Node
         */
        public void setNext(Node n){
            this.next = n;
        }

        /**
         * get the next Node
         * @return the next Node
         */
        public Node getNext(){
            return this.next;
        }
    }

    //initialize the instance fields for a LinkedList, including a head, a tail, and the size
    private Node head;
    private int size;
    private Node tail;

    /**
     * create a Constructor for the LinkedList with size of 0 and null head and tail
     */
    public LinkedList(){
        this.head = null;
        this.size = 0;
        this.tail = null;
    }

    /**
     * get the size of the LinkedList
     * @return the size of the LinkedList
     */
    public int size(){
        return this.size;
    }

    /**
     * clear everything in the LinkedList
     * @return 
     */
    public void clear(){
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * check if the LinkedList is empty
     * @return whether the LinkedList is empty
     */
    public boolean isEmpty(){
        return (size==0);
    }

    /**
     * create a String presentation for the LinkedList
     * @return the String presentation of the LinkedList
     */
    public String toString(){
        if (isEmpty()){
            return "";
        }
        String output = "";
        Node walker = head;
        int i = 0;
        while (i<size){
            output += walker.getData() + " ";
            walker=walker.getNext();
            i++;
        }
        return output;
    }

    /**
     * add a Node at the beginning of the LinkedList
     * @param item the data of the added Node
     */
    public void add(T item){
        Node firstNode = new Node(item);
        firstNode.setNext(head);
        head = firstNode;
        if (size==0){
            tail = firstNode;
        }
        size++;
    }
    
    /**
     * check if a Node is in the LinkedList
     * @param o the object of the Node that needs to be found
     * @return whether the Node is in the LinkedList or not
     */
    public boolean contains(Object o){
        if (size==0){
            return false;
        }
        Node walker = head; 
        int i=0;
        while (i<size){
            if (walker.getData().equals(o)){
                return true;
            }
            walker=walker.getNext();
            i++;
        }
        return false;
    }

    /**
     * compare this LinkedList to another LinkedList object
     * @param o the LinkedList object that needs to be compared with
     * @return whether each Node in the two LinkedLists are equal
     */
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)){
            return false;
        }
        LinkedList<T> oAsALinkedList = (LinkedList) o;
        Node walker1 = head;
        Node walker2 = oAsALinkedList.head;
        int i = 0;
        while (i<size){
            if (!walker1.getData().equals(walker2.getData())){
                return false;
            }
            walker1 = walker1.getNext();
            walker2 = walker2.getNext();
            i++;
        }
        return true;
    }

    /**
     * get the data of the Node at a specified index
     * @param index the index of the desired Node
     * @return the data at the index
     */
    public T get(int index){
        if(size==0){
            return null;
        }
        if (index==0){
            return head.data;
        }
        if (index==size-1){
            return this.getLast();
        }
        Node walker = head;
        int i = 0; 
        T answer = null; 
        while (i<size){
            walker = walker.getNext();
            i++;
            if (i==index){
                answer = walker.getData();
                break;
            }
        }
        return answer;
    }

    /**
     * remove the first Node
     * @return the data in the first Node
     */
    public T remove(){
        T firstItem = head.getData();
        Node newHead = head.getNext();
        head = newHead;
        size--;
        if (size==0){
            tail = null;
        }
        return firstItem;
    }

    /**
     * add a Node at a specified index
     * @param index the index to add
     * @param item the data of the Node to add
     */
    public void add(int index, T item){
        if (index==0){
            this.add(item);
        }
        else{
            if (index==size){
                this.addLast(item);
            }
            else {
                Node newNode = new Node(item);
                Node walker = head;
                int i = 0;
                while (i<this.size){
                    if (i==index-1){
                        Node nextNode = walker.getNext();
                        walker.setNext(newNode);
                        newNode.setNext(nextNode);
                        break;
                    }
                    else{
                        walker = walker.getNext();
                    }
                    i++;
                }
                size++;
            }
        }
    }

    /**
     * add a Node at the end of the LinkedList
     * @param item the data of the Node to add
     */
    public void addLast(T item){
        if (size==0){
            this.add(item);
        }
        else{
            Node lastNode = new Node(item);
            tail.next = lastNode;
            tail = tail.next;
            size++;
        }
    }

    /**
     * remove a Node at a specified index
     * @param index the index of the Node to remove
     * @return the data of the removed Node
     */
    public T remove(int index){
        if(size==0){
            return null;
        }
        if (index==0){
            return this.remove();
        }
        if (index==size-1){
            return this.removeLast();
        }
        Node walker = head;
        int i = 0;
        T output = null;
        while (i<size){
            if (i==index-1){
                Node removedNode = walker.getNext();
                output = removedNode.getData();
                Node newNextNode = removedNode.getNext();
                walker.setNext(newNextNode);
                size--;
                break;
            }
            else{
                walker = walker.getNext();
                i++;
            }
        }
        return output;
    }

    /**
     * remove the last Node 
     * @return the data in the last Node
     */
    public T removeLast(){
        Node walker = head;
        T lastItem = null;
        for (int i =0; i<size-1;++i){
            if (i==size-2){
                lastItem = walker.getNext().getData();
                walker.setNext(null);
                tail = walker;
            }
            else{
                walker = walker.getNext();
            }
        } 
        size--;
        return lastItem;
    }

    /**
     * get the data of the last Node
     * @return the data of the last Node
     */
    public T getLast(){
        return tail.getData();
    }

    /**
     * offer a Node to the end of the Queue 
     * @param data the data of the Node to offer
     */
    public void offer(T data){
        this.addLast(data);
    }

    /**
     * remove the first Node in the Queue
     * @return the data of the first Node
     */
    public T poll(){
        return this.remove();
    }

    /**
     * return the data the first Node in the Queue
     * @return the data of the first Node
     */
    public T peek(){
        return this.get(0);
    }

    /**
     * add item to the top of the stack
     * @param item the added item
     */
    public void push(T item){
        this.add(item);
    }

    /**
     * remove the uppermost item of the stack
     * @return the uppermost item
     */
    public T pop(){
        return this.remove();
    }
}
