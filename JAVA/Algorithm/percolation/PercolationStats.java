import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
  private Percolation percolation;
  private int count;
  private double[] fractions;
  
  public PercolationStats(int n, int trials) {
    // perform trials independent experiments on an n-by-n grid
    if (n <= 0||trials<=0)
      throw new IllegalArgumentException("Illegal Argument");
    this.count=trials;
    this.fractions=new double[count];
    for(int i=0;i<count;++i) {
      this.percolation=new Percolation(n);
      while(!this.percolation.percolates()) {
        int row = StdRandom.uniform(1,n+1);
        int col = StdRandom.uniform(1,n+1);
        this.percolation.open(row,col);
      }
      fractions[i]=(double) this.percolation.numberOfOpenSites()/(n*n);
    }
    

  }
  public double mean() {
    // sample mean of percolation threshold
    return StdStats.mean(this.fractions);
  }
  public double stddev() {
    // sample standard deviation of percolation threshold
    return StdStats.stddev(this.fractions);
  }
  public double confidenceLo() {
    // low  endpoint of 95% confidence interval
    return this.mean() - ((1.96 * this.stddev()) / Math.sqrt(count));
  }
  public double confidenceHi() {
    // high endpoint of 95% confidence interval
    return this.mean() + ((1.96 * this.stddev()) / Math.sqrt(count));
  }
  
  public static void main(String[] args) {
    // test client (described below)
    int n=Integer.parseInt(args[0]);
    int trials=Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(n,trials);
    System.out.println("mean                    = "+ps.mean());
    System.out.println("stddev                  = "+ps.stddev());
    System.out.println("95% confidence interval = ["+ps.confidenceLo()+", "+ps.confidenceLo()+"]");

  }
}