package Sort;

import edu.princeton.cs.algs4.StdRandom;

public class MergeSort<Item> {

    private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){

        for(int i=0; i<=hi; i++){
            aux[i] = a[i];
        }

        int i=lo, j=mid+1;
        for(int k=lo; k<=hi; k++){
            if(i>mid) a[k] = aux[j++];
            else if(j>hi) a[k] = aux[i++];
            else if(aux[i].compareTo(aux[j]) <=0 ) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    private void sort(Comparable[] array, Comparable[] aux, int lo, int hi){
        int mid = lo + (hi - lo) / 2;
        if(lo >= hi) return;

        sort(array, aux, 0, mid);
        sort(array, aux, mid+1, hi);

        merge(array, aux, lo, mid, hi);
    }

    public void sort(Comparable[] array){
        Comparable[] aux = new Comparable[array.length];
        sort(array, aux, 0, array.length-1);
    }

    public static void main(String[] args){
        Integer[] array = new Integer[20];
        for(int i=0; i<20; i++){
            array[i] = StdRandom.uniform(0, 100);
        }

        print(array);

        MergeSort<Integer> ms = new MergeSort<>();
        ms.sort(array);
        print(array);
    }

    private static void print(Integer[] array){
        for(int i : array){
            System.out.print(i+ " ");
        }
        System.out.println("");
    }
}
