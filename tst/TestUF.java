import java.util.Scanner;

public class TestUF {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        UnionFind uf = new UnionFind(n);
        uf.union(4, 3);
        uf.count();
        uf.union(3, 8);
        uf.count();
        uf.union(6, 5);
        uf.count();
        uf.union(9, 4);
        uf.count();
        uf.union(2, 1);
        uf.count();
        uf.union(5, 4);
        uf.count();
        uf.union(5, 0);
        uf.count();
        uf.union(7, 2);
        uf.count();
        uf.union(6, 1);
        uf.count();
        uf.union(7, 3);
        uf.count();
    }
}
