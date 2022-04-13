package analysis;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSUM {

    public static class Triplets{
        int a;
        int b;
        int c;

        public Triplets(int p, int q, int r){
            a = p;
            b = q;
            c = r;
        }

        public String toString(){
            return a +" "+b+ " " +c+ " \n";
        }
    }

    private static int threeSUM(int[] arr, int k){

        Arrays.sort(arr);

        List<Triplets> triplets = new ArrayList<>();
        int sum;
        for(int i=0; i<arr.length-2; i++){
            int f = i+1;
            int l = arr.length-1;

            while(f < l){
                sum = arr[i] + arr[f] + arr[l];
                if(sum > k){
                    l--;
                    continue;
                } else if (sum < k){
                    f++;
                    continue;
                }
                // Add triplets
                triplets.add(new Triplets(arr[i], arr[f], arr[l]));

                // Check equality from front
                while(arr[f] == arr[f+1] && f+1<l){
                    triplets.add(new Triplets(arr[i], arr[f+1], arr[l]));
                    f++;
                }

                while(arr[l] == arr[l-1] && l-1>f){
                    triplets.add(new Triplets(arr[i], arr[f], arr[l-1]));
                    l--;
                }
                break;
            }
        }
        for(Triplets t : triplets){
            System.out.println(t);
        }
        return triplets.size();
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = StdRandom.uniform(20);
        }

        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println("");
        System.out.println("Number of three sum pairs : " + threeSUM(arr, k));
    }
}
