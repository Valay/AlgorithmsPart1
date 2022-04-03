import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] means;
    private final int n;
    private final int trials;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n<=0 || trials<=0){
            throw new IllegalArgumentException("");
        }
        this.n = n;
        this.trials = trials;
        means = new double[trials];

        for(int i=0; i<this.trials; i++){
            means[i] = runExperiment(this.n);
        }
    }

    private double runExperiment(int n){
        Percolation p = new Percolation(n);
        while(!p.percolates()){
            int site = StdRandom.uniform(0,  n*n);
            int row = site / n;
            int col = site % n;
            p.open(row+1, col+1);
            // validate
            /*
            if(!p.isOpen(row+1, col+1)){
                throw new RuntimeException("Expected site to be open!");
            }
            */
        }
        return Double.valueOf(p.numberOfOpenSites()) / Double.valueOf(n*n);
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(means);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(means);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - (1.96d * stddev() / Math.sqrt(this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + (1.96d * stddev() / Math.sqrt(this.trials));
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }

}
