package queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

public class StackWithMax {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        MaxStack ms = new MaxStack(n);

        for (int i = 0; i < n; i++) {
            ms.push(StdRandom.uniform(20));
            ms.print();
            System.out.println("max : " + ms.max());
        }
    }

    public static class MaxStack {

        int[] stack;
        int[] max;
        int n;

        public MaxStack(int capacity) {
            stack = new int[capacity];
            max = new int[capacity];
            n = -1;
        }

        public void push(int a) {
            if (isEmpty()) {
                max[n + 1] = n + 1;
            } else {
                if (a >= stack[max[n]]) {
                    max[n + 1] = n + 1;
                } else {
                    max[n + 1] = max[n];
                }
            }
            stack[++n] = a;
        }

        public int pop() {
            if (!isEmpty()) {
                return stack[n--];
            }
            throw new NoSuchElementException("Stack is empty!");
        }

        public int max() {
            return stack[max[n]];
        }

        public boolean isEmpty() {
            return n == -1;
        }

        public void print() {
            for (int i = 0; i <= n; i++) {
                System.out.print(stack[i] + " ");
            }
            System.out.println();
        }
    }
}
