import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int n;
    // construct an empty randomized queue
    public RandomizedQueue(){
        items = (Item[]) new Object[1];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return n;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null){
            throw new IllegalArgumentException("Item can not be empty!");
        }
        if(n==items.length){
            // Double the size of the queue!
            resizeQueue(n*2);
        }
        // Randomly select a position to insert the item!
        int random = StdRandom.uniform(0, n+1);
        //Swap
        if(random != n){
            Item oldItem = items[random];
            items[random] = item;
            items[n++] = oldItem;
        }else{
            items[n++] = item;
        }

    }

    private void resizeQueue(int newSize){
        Item[] copy = (Item[])new Object[newSize];
        for(int i=0; i<n; i++){
            copy[i] = items[i];
        }
        items = copy;
    }

    // remove and return a random item
    public Item dequeue(){
        if(n > 0){
            return items[--n];
        }
//        if(n <= items.length / 4){
//            // decrease array size by half
//            resizeQueue(items.length / 2);
//        }
        throw new NoSuchElementException("RandomizedQueue is empty!");
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(n > 0){
            // Select a random number between 0 to n
            int random = StdRandom.uniform(0, n);
            return items[random];
        }
        throw new NoSuchElementException("RandomizedQueue is empty!");
    }

    private class RandomizedIterator implements Iterator<Item>{
        int[] perm;
        int count = 0;

        public RandomizedIterator(){
            perm = StdRandom.permutation(n);
        }

        public Item next(){
            if(count < n){
                return items[perm[count++]];
            }else{
                throw new NoSuchElementException("No items left to iterate in the list");
            }
        }

        public boolean hasNext(){
            return count < n;
        }

        public void remove(){
            throw new UnsupportedOperationException("Remove operation is not supported on Iterator!");
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedIterator();
    }

    private class NonRandomIterator implements Iterator<Item>{
        int count = 0;

        public Item next(){
            if(count < n){
                return items[count++];
            }else{
                throw new NoSuchElementException("No items left to iterate in the list");
            }
        }

        public boolean hasNext(){
            return count < n;
        }

        public void remove(){
            throw new UnsupportedOperationException("Remove operation is not supported on Iterator!");
        }
    }

    private Iterator<Item> nonRandomIterator(){
        return new NonRandomIterator();
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");
        iterate(rq);
        rq.enqueue("E");
        iterate(rq);
        rq.enqueue("F");
        iterate(rq);
        rq.enqueue("G");
        iterate(rq);
        rq.enqueue("H");
        iterate(rq);
        rq.enqueue("I");

        iterate(rq);
        System.out.println("Sample "  + rq.sample());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        rq.enqueue("X");
        iterate(rq);
        System.out.println("Sample "  + rq.sample());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Sample "  + rq.sample());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        rq.enqueue("Y");
        iterate(rq);
        rq.enqueue("Z");
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        System.out.println("Dequeue "  + rq.dequeue());
        iterate(rq);
        for(String s : rq){
            System.out.print(s+" ");
        }
    }

    private static void iterate(RandomizedQueue<String> rq){
        Iterator<String> it = rq.nonRandomIterator();
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
        System.out.println("");
    }

}