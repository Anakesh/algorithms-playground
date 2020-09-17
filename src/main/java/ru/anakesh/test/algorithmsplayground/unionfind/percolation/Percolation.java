package ru.anakesh.test.algorithmsplayground.unionfind.percolation;

public class Percolation {
    private final int n;
    private final int[] siteArray;
    private final int[] componentSizeArray;
    private final byte[] connectedArray;
    private int numOfOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("N can't be less than 1");

        this.n = n;
        siteArray = new int[n * n + 1];
        componentSizeArray = new int[n * n + 1];
        connectedArray = new byte[n * n + 1];
        for (int i = 0; i < siteArray.length; i++) {
            siteArray[i] = -1;
        }
        siteArray[0] = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = convertToIndex(row, col);
        int i = row - 1;
        int j = col - 1;

        if (siteArray[index] == -1) {
            siteArray[index] = index;
            numOfOpenSites++;
            if (i == 0) {
                connectedArray[index] |= 0x01;
                union(index, 0);
            }
            if (i == n - 1) {
                connectedArray[index] |= 0x02;
            }
            if (i > 0) {
                union(index, index - n);
            }
            if (i < n - 1) {
                union(index, index + n);
            }
            if (j > 0) {
                union(index, index - 1);
            }
            if (j < n - 1) {
                union(index, index + 1);
            }
        }

    }

    private int findRoot(final int index) {
        int root = index;
        while (siteArray[root] != root) {
            root = siteArray[root];
        }
        return root;
    }

    private void union(final int firstSite, final int secondSite) {
        if (siteArray[firstSite] == -1 || siteArray[secondSite] == -1) return;
        int firstRoot = findRoot(firstSite);
        int secondRoot = findRoot(secondSite);
        if (firstRoot == secondRoot) return;

        byte connected = (byte) (connectedArray[firstRoot] | connectedArray[secondRoot]);
        connectedArray[firstRoot] = connected;
        connectedArray[secondRoot] = connected;

        if (componentSizeArray[firstRoot] < componentSizeArray[secondRoot]) {
            siteArray[firstRoot] = secondRoot;
            componentSizeArray[secondRoot] += componentSizeArray[firstRoot];
        } else {
            siteArray[secondRoot] = firstRoot;
            componentSizeArray[firstRoot] += componentSizeArray[secondRoot];
        }
    }

    private int convertToIndex(int row, int col) {
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("Row can't be less than 1 or bigger than N (" + n + "). Input row: " + row);
        }
        if (col < 1 || col > n) {
            throw new IllegalArgumentException("Column can't be less than 1 or bigger than N (" + n + "). Input column: " + row);
        }
        return (row - 1) * n + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = convertToIndex(row, col);
        return siteArray[index] > -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int index = convertToIndex(row, col);
        if (siteArray[index] == -1) return false;
        int root = findRoot(index);
        return (connectedArray[root] & 0x01) == 0x01;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return connectedArray[findRoot(0)] == 0x03;
    }

}
