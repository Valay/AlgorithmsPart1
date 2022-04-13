package sortalgorithms;

import edu.princeton.cs.algs4.StdRandom;

import java.util.List;

/**
 * 1. Set the startingValue to 0 and endingValue to n-1.
 * 2. Traverse the array.
 *     1. Check if the current array element is less than the value of lowValue
 *          if true then swap the arr[i] and arr[startingValue] and increase both startingValues and i by 1.
 *
 *     2. Else check if the current array is greater than the highValue,
 *          swap the arr[i] and arr[endingValue] and decrease the value of endingValue by 1.
 *
 *     3. Else increase the value of i by 1.
 * 3. Print the array.
 *
 *
 * procedure three-way-partition(A : array of values, mid : value):
 *     i ← 0
 *     j ← 0
 *     k ← size of A - 1
 *
 *     while j <= k:
 *         if A[j] < mid:
 *             swap A[i] and A[j]
 *             i ← i + 1
 *             j ← j + 1
 *         else if A[j] > mid:
 *             swap A[j] and A[k]
 *             k ← k - 1
 *         else:
 *             j ← j + 1
 */
public class ThreeWayPartition {

    private static void threeWayPartition(int[] arr){
        int s=0;
        int e=arr.length-1;
        int i=0;
        while(i <= e){
            if(arr[i] < 1){
                exch(arr, s, i);
                s++;
                i++;
            } else if (arr[i] > 1){
                exch(arr, i, e);
                e--;
            } else{
                i++;
            }
        }
    }

    private static void exch(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);

        int[] arr = new int[n];
        for(int i=0; i<n ; i++){
            int rad = StdRandom.uniform(100);
            if(rad < 33){
                arr[i] = 0;
            } else if (rad > 66){
                arr[i] = 2;
            } else{
                arr[i] = 1;
            }
        }
        print(arr);
        threeWayPartition(arr);
        print(arr);
    }

    private static void print(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
}
