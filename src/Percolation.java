import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    /**
     * 0 - represents blocked site
     * 1 - represents open site
     */
    int[][] grid;
    int count = 0;
    int n;
    WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n<=0){
            throw new IllegalArgumentException("n should be at least 1");
        }
        grid = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                grid[i][j] = 0; // 0 represents blocked site
            }
        }
        this.n = n;
        uf = new WeightedQuickUnionUF((n*n)+2);

        runPercolation();
    }

    private void runPercolation(){
        while(!percolates()){
            int site = StdRandom.uniform(0,  n*n);
            int row = site / n;
            int col = site % n;
            open(row+1, col+1);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(row < 1 || row > grid.length || col < 1 || col > grid.length){
            throw new IllegalArgumentException("Array access out of bound!");
        }

        row = row-1;
        col = col-1;

        if(grid[row][col] != 1){
            grid[row][col] = 1;
            count++;
        }

        int pos = (row * n + col) + 1;

        // Check Top
        if(row == 0){
            // TOP row, connect with 0
            uf.union(0, pos);
        }

        // Check Bottom
        if(row == n-1){
            // Bottom row, connect with n*n+1
            uf.union(n*n+1, pos);
        }

        // Check up
        if(row-1 >= 0 && grid[row-1][col] == 1){
            int up = ((row-1) * n + col) + 1;
            uf.union(up, pos);
        }

        // check down
        if(row+1 < n && grid[row+1][col] == 1){
            int down = ((row+1) * n + col) + 1;
            uf.union(down, pos);
        }

        // check left
        if(col-1 >=0 && grid[row][col-1] == 1){
            int left = (row * n + (col-1)) + 1;
            uf.union(left, pos);
        }

        // check right
        if(col+1<n && grid[row][col+1] == 1){
            int right = (row * n + (col+1)) + 1;
            uf.union(right, pos);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row < 1 || row > grid.length || col < 1 || col > grid.length){
            throw new IllegalArgumentException("Array access out of bound!");
        }
        return grid[row-1][col-1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(row < 1 || row > grid.length || col < 1 || col > grid.length){
            throw new IllegalArgumentException("Array access out of bound!");
        }
        int pos =  ((row-1)*n + (col-1)) + 1;
        return uf.find(0) == uf.find(pos);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return count;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.find(0) == uf.find(n*n+1);
    }

    // test client (optional)
//    public static void main(String[] args){
//        Percolation p = new Percolation(5);
//        System.out.println(p.percolates());
//        System.out.println(p.count);
//    }
}