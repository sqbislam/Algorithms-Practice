
   /**
         *
         * @author Saqib Islam
         * CSE Undergraduate 
         * BRAC UNIVERSITY
         * 
         */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private double[] pThreshold; // stores threshold of each trial
 
      public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0) {
          throw new IllegalArgumentException("Enter Positive trial and grid numbers");}
         
      pThreshold = new double[trials];
         
      performCalculation(n);
          
      }   // perform trials independent experiments on an n-by-n grid
      
   private void performCalculation(int n){
       for (int i = 0; i < pThreshold.length; i++) {
       pThreshold[i] = findPThreshold(n);
       }
   
   
   }
   public double mean(){
       return StdStats.mean(pThreshold);
   }                          // sample mean of percolation threshold
   public double stddev() {
       return StdStats.stddev(pThreshold);
   }                       // sample standard deviation of percolation threshold
   
   public double confidenceLo(){
    return (mean()-confidence());
   }                  // low  endpoint of 95% confidence interval
   public double confidenceHi()   {
   return (mean()+confidence());
   }               // high endpoint of 95% confidence interval

     private double findPThreshold(int n) {
         
     Percolation p = new Percolation(n);  // make new Perlocation object
     double count = 0;
     
     while(!p.percolates()){
     
         if(count > n*10000){
             System.out.println("System does not percolate at n*10000 times");
         break;
     } // Safety for infinite loop
         
     int a=StdRandom.uniform(1,n+1);   //get two random row and columns
     int b=StdRandom.uniform(1,n+1); 
     
     if(!p.isOpen(a, b)){   // if site is not open then open site
     p.open(a, b);
     count+=1;
     }
     
     }
     
        
     return (count/(n*n));
    }
     
       private double confidence() {
       return (double)(1.96*stddev()) / pThreshold.length; //returns confidence
    }

     
     
   public static void main(String[] args){
    int n=Integer.parseInt(args[0]);
    int trials=Integer.parseInt(args[1]);
    PercolationStats pStats=new PercolationStats(n,trials);
       System.out.println("mean                    = "+pStats.mean());
       System.out.println("stddev                  = "+ pStats.stddev());
       System.out.println("95% confidence interval = ["+pStats.confidenceLo()+", "+pStats.confidenceHi()+"]");
    
    
       
       
       
   }        // test client (described below)

  
  
}
    

