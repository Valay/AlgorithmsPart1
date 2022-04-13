package Sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SelectionSort<Item extends Comparable> {

    public void sort(List<Item> itemList){

        for(int i=0; i<itemList.size()-1; i++){
            int min = i;
            for(int j=i+1; j<itemList.size(); j++){
                if(less(itemList.get(j), itemList.get(min))){
                    min = j;
                }
            }
            exch(itemList, i, min);
        }
    }

    private boolean less(Item a, Item b){
        return a.compareTo(b) < 0;
    }

    private void exch(List<Item> items, int a, int b){
        Item temp = items.get(a);
        items.set(a, items.get(b));
        items.set(b, temp);
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        List<Integer> items = new ArrayList<>();
        for(int i=0; i<n; i++){
            items.add(StdRandom.uniform(1000));
        }
        InsertionSort<Integer> insertionSort = new InsertionSort<>();
        System.out.println("Before Sort!");
        System.out.println(items);

        // Sort
        insertionSort.sort(items);

        System.out.println("After Sort!");
        System.out.println(items);
    }
}
