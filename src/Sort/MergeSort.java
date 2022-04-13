package Sort;

import edu.princeton.cs.algs4.StdRandom;

public class MergeSort<Item> {

    public void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi){
        for(int i=lo; i<=hi; i++){
            aux[i] = arr[i];
        }

        int p = lo;
        int q = mid+1;
        for(int i=lo; i<=hi; i++){
            if(p>mid){
                arr[i] = aux[q++];
            } else if (q > hi){
                arr[i] = aux[p++];
            } else if (aux[p].compareTo(aux[q]) <= 0){
                arr[i] = aux[p++];
            } else{
                arr[i] = aux[q++];
            }
        }
    }

    public void sort(Comparable[] arr, Comparable[] aux, int lo, int hi){
        if(lo >= hi){
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid+1, hi);

        merge(arr, aux, lo, mid, hi);
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
