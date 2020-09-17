package ru.anakesh.test.algorithmsplayground.unionfind.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final double[] openSitesArray;
    private double mean = -1;
    private double stddev = -1;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0)
            throw new IllegalArgumentException("N can't be less than 1");

        if (trials <= 0)
            throw new IllegalArgumentException("Number of trials can't be less than 1");

        this.trials = trials;
        openSitesArray = new double[trials];
        for (int i = 0; i < openSitesArray.length; i++) {
            openSitesArray[i] = percolate(n);
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, t);
        System.out.println("" +
                "mean\t\t\t\t\t= " + percolationStats.mean() + "\n" +
                "stddev\t\t\t\t\t= " + percolationStats.stddev() + "\n" +
                "95% confidence interval\t= [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }

    private double percolate(int n) {
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            percolation.open(row, col);
        }
        return ((double) percolation.numberOfOpenSites()) / (n * n);
    }

    // sample mean of percolation threshold
    public double mean() {
        if (mean == -1) {
            mean = StdStats.mean(openSitesArray);
        }
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (stddev == -1) {
            stddev = StdStats.stddev(openSitesArray);
        }
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }
}
