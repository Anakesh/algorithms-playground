package ru.anakesh.test.algorithmsplayground.unionfind.percolation;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class PercolationVisualizer {

    // delay in miliseconds (controls animation speed)
    private static final int DELAY = 100;

    // draw n-by-n percolation system
    public static void draw(Percolation percolation, int n) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-0.05 * n, 1.05 * n);
        StdDraw.setYscale(-0.05 * n, 1.05 * n);   // leave a border to write text
        StdDraw.filledSquare(n / 2.0, n / 2.0, n / 2.0);

        // draw n-by-n grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (percolation.isFull(i + 1, j + 1)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                } else if (percolation.isOpen(i + 1, j + 1)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                StdDraw.filledSquare(j + 0.5, n - i - 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.25 * n, -0.025 * n, percolation.numberOfOpenSites() + " open sites");
        if (percolation.percolates()) StdDraw.text(0.75 * n, -0.025 * n, "percolates");
        else StdDraw.text(0.75 * n, -0.025 * n, "does not percolate");

    }

    private static void simulateFromFile(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        Percolation percolation = new Percolation(n);

        // turn on animation mode
        StdDraw.enableDoubleBuffering();

        // repeatedly read in sites to open and draw resulting system
        draw(percolation, n);
        StdDraw.show();
        StdDraw.pause(DELAY);

        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            percolation.open(row, col);
            draw(percolation, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String filename = "input20.txt";
        simulateFromFile(filename);
    }
}