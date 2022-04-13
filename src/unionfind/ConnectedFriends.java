package unionfind;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConnectedFriends {

    private static long earliestConnectionTimestamp(List<Integer> members, List<Integer> a, List<Integer> b, List<Long> times) {
        QuickUnionFind unionFind = new QuickUnionFind(members.size());
        int i = 0;
        for (; i < a.size(); i++) {
            int f1 = a.get(i);
            int f2 = b.get(i);
            unionFind.union(f1, f2);
            System.out.println("max : " + unionFind.findMax(f1));
            if (unionFind.count() == 1) {
                return times.get(i);
            }
        }
        return times.get(i - 1);
    }

    private static class QuickUnionFind{

        int count;
        int[] items;
        int[] size;
        int[] max;
        public QuickUnionFind(int capacity){
            items = new int[capacity];
            size = new int[capacity];
            max = new int[capacity];
            count = capacity;
            for(int i=0; i<capacity; i++){
                items[i] = i;
                size[i] = 1;
                max[i] = i;
            }
        }

        public void union(int a, int b){
            int p = find(a);
            int q = find(b);

            if(p == q) return;

            if(size[p] > size[q]){
                items[q] = p;
                max[p] = Math.max(max[q], max[p]);
                size[q] += size[p];
            } else {
                items[p] = q;
                max[q] = Math.max(max[q], max[p]);
                size[p] += size[q];
            }
            count--;
        }

        public int find(int a){
            while(a != items[a]){
                a = items[a];
            }
            return a;
        }

        public int findMax(int a){
            return max[find(a)];
        }

        public int count(){
            return count;
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        List<Integer> members = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            members.add(i);
        }
        long[] connectedTimes = new long[n * n];
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < n * n; i++) {
            connectedTimes[i] = timestamp();
            a.add(StdRandom.uniform(n));
            b.add(StdRandom.uniform(n));
        }
        Arrays.sort(connectedTimes);

        List<Long> times = Arrays.stream(connectedTimes).boxed().collect(Collectors.toList());

        print(a, b, times);
        System.out.println("Earliest time : " + earliestConnectionTimestamp(members, a, b, times));

    }

    private static void print(List<Integer> a, List<Integer> b, List<Long> times) {
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i) + "---" + b.get(i) + " at " + times.get(i));
        }
    }

    private static long timestamp() {
        return StdRandom.uniform(10000);
    }
}
