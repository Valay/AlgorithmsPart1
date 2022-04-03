import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node{
        Item item;
        Node next;
        Node prev;
    }

    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null){
            throw new IllegalArgumentException("Item can not be null!");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;
        if(oldFirst != null){
            oldFirst.prev = first;
        }else{
            last = first;
        }

        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null){
            throw new IllegalArgumentException("Item can not be null!");
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        newNode.prev = last;
        if(last != null){
            last.next = newNode;
        }else{
            first = last;
        }
        last = newNode;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(first == null){
            throw new NoSuchElementException("Queue is empty!");
        }
        Node node = first;
        first = first.next;
        if(first != null){
            first.prev = null;
        }else{
            last = first;
        }
        size--;
        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(last == null){
            throw new NoSuchElementException("Queue is empty!");
        }
        Node node = last;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }else{
            first = last;
        }
        size--;
        return node.item;
    }

    private class DequeIterator implements Iterator<Item>{

        Node current = first;

        public Item next(){
            if(current == null){
                throw new NoSuchElementException("No items left to iterate in the list");
            }
            Node node = current;
            current = current.next;
            return node.item;
        }

        public boolean hasNext(){
            return current != null;
        }

        public void remove(){
            throw new UnsupportedOperationException("Remove operation is not supported on Iterator!");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<String> dq = new Deque<>();
        dq.addFirst("D");
        dq.addFirst("C");
        dq.addLast("X");
        dq.addFirst("B");
        dq.addLast("Y");
        dq.addFirst("A");
        dq.addLast("Z");

        iterate(dq);

        dq.removeFirst();
        iterate(dq);
        dq.removeFirst();
        iterate(dq);
        dq.removeLast();
        iterate(dq);
        dq.removeFirst();
        iterate(dq);
        dq.removeLast();
        iterate(dq);
        dq.removeLast();
        iterate(dq);
        dq.removeLast();
        iterate(dq);

        System.out.println("IsEmpty?" + dq.isEmpty());
        System.out.println("size" + dq.size());

        dq.addFirst("D");
        dq.addFirst("C");
        dq.addFirst("B");
        dq.addFirst("A");
        dq.removeLast();
        iterate(dq);
        dq.removeLast();
        iterate(dq);

        System.out.println("IsEmpty?" + dq.isEmpty());
        System.out.println("size" + dq.size());
    }

    private static void iterate(Deque<String> dq){
        for(String i : dq){
            System.out.print(i + "->");
        }
        System.out.println("");
    }

}
