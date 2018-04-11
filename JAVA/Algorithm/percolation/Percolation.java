import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean [][] grid;
  private WeightedQuickUnionUF adjMatrix;
  private int gridN;
  
  public Percolation(int n) {
    // create n-by-n grid, with all sites blocked
    if (n<=0) {
      throw new IllegalArgumentException("Illegal Argument");
    }
    this.grid = new boolean[n][n];
    this.gridN = n;
    this.adjMatrix = new WeightedQuickUnionUF(n*n);
//    for(int i=0;i<this.gridN;++i){
//      this.adjMatrix.union(n*n,i);
//      this.adjMatrix.union(n*n+1,n*(n-1)+i);
//    }
    
//    for(int i=0;i<this.gridN;++i){
//      for(int j=0;j<this.gridN;++j){
//        System.out.print(grid[i][j]);
//      }
//      System.out.print("\n");
//    }
  }
  
  private int Left(int row, int col) {
    if(col>0) {
      return row*this.gridN+col-1;
    }
    else{
      return -1;
    }
  }
  
  private int Right(int row, int col) {
    if(col<this.gridN-1) {
      return row*this.gridN+col+1;
    }
    else{
      return -1;
    }
  }
  
  private int Up(int row, int col) {
    if(row>0) {
      return (row-1)*this.gridN+col;
    }
    else{
      return -1;
    }
  }
  
  private int Down(int row, int col) {
    if(row<this.gridN-1) {
      return (row+1)*this.gridN+col;
    }
    else{
      return -1;
    }
  }
  
  public void open(int row, int col) {
    // open site (row, col) if it is not open already
    if (row<1 || col<1 || row>this.gridN || col>this.gridN) {
      throw new IllegalArgumentException("Illegal Argument");
    }
    --row;
    --col;
    this.grid[row][col]=true;
    int left=this.Left(row, col);
    int right=this.Right(row, col);
    int up=this.Up(row, col);
    int down=this.Down(row,col);
    if(left!=-1&&grid[row][col-1]) this.adjMatrix.union(row*this.gridN+col,left);
    if(right!=-1&&grid[row][col+1]) this.adjMatrix.union(row*this.gridN+col,right);
    if(up!=-1&&grid[row-1][col]) this.adjMatrix.union(row*this.gridN+col,up);
    if(down!=-1&&grid[row+1][col]) this.adjMatrix.union(row*this.gridN+col,down);
  }
  
  public boolean isOpen(int row, int col) {
    // is site (row, col) open?
    if (row<1 || col<1 || row>this.gridN || col>this.gridN) {
      throw new IllegalArgumentException("Illegal Argument");
    }
    --row;
    --col;
    return grid[row][col];
  }
  
  public boolean isFull(int row, int col) {
    // is site (row, col) full?
    if (row<1 || col<1 || row>this.gridN || col>this.gridN) {
      throw new IllegalArgumentException("Illegal Argument");
    }
    --row;
    --col;
    for(int i=0;i<this.gridN;++i){
      if (grid[row][col]&&this.adjMatrix.connected(i,row*this.gridN+col)) 
        return true;
    }
    return false;
  }
  
  public int numberOfOpenSites() {
    // number of open sites
    int count=0;
    for(int i=0;i<this.gridN;++i){
      for(int j=0;j<this.gridN;++j){
        if(grid[i][j]) ++count;
      }
    }
    return count;
  }
  
  public boolean percolates() {
    // does the system percolate?
    if(this.gridN==1){
      return this.grid[0][0];
    }
    else{      
      for(int i=0;i<this.gridN;++i){
        for(int j=0;j<this.gridN;++j){
          if (this.adjMatrix.connected(i,(this.gridN-1)*this.gridN+j)) 
            return true;
        }
      }
      return false;
    }
  }
  
  public static void main(String[] args) {
    // test client (optional)
    Percolation p = new Percolation(0);
    p.open(1,1);
    p.open(2,2);
//    System.out.print("----");
//    System.out.print(p.percolates());
  }
}