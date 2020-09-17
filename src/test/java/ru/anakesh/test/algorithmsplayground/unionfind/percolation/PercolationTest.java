package ru.anakesh.test.algorithmsplayground.unionfind.percolation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    @Test
    void percolationTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Percolation(0);
        });
        Percolation percolation = new Percolation(10);

        int[][] openArray = new int[][]{
                {1, 1},
                {2, 1},
                {2, 2},
                {3, 2},
                {4, 2},
                {6, 2},
                {7, 2},
                {7, 1},
                {8, 1},
                {9, 1},
                {5, 2}
        };


        assertThrows(IllegalArgumentException.class, () -> {
            percolation.open(0, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            percolation.open(1, 0);
        });


        assertThrows(IllegalArgumentException.class, () -> {
            percolation.open(11, 2);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            percolation.open(3, 11);
        });

        assertFalse(percolation.percolates());
        assertDoesNotThrow(() -> {
            for (int[] it : openArray) {
                percolation.open(it[0], it[1]);
            }
        });


        for (int[] it : openArray) {
            assertTrue(percolation.isOpen(it[0], it[1]));
        }

        assertEquals(11, percolation.numberOfOpenSites());

        assertFalse(percolation.isOpen(5, 5));
        percolation.open(5, 5);
        assertTrue(percolation.isOpen(5, 5));
        assertFalse(percolation.isFull(5, 5));

        percolation.open(5, 6);
        assertFalse(percolation.isFull(5, 5));
        assertFalse(percolation.isFull(5, 6));

        assertEquals(13, percolation.numberOfOpenSites());

        percolation.open(10, 1);

        assertTrue(percolation.percolates());

        for (int[] it : openArray) {
            assertTrue(percolation.isFull(it[0], it[1]));
        }

        assertEquals(14, percolation.numberOfOpenSites());

//        Percolation.PercolationSite[][] array = percolation.siteArray;
//        for (Percolation.PercolationSite[] percolationSites : array) {
//            for (Percolation.PercolationSite percolationSite : percolationSites) {
//                char c = percolationSite.isOpen()? 'X': '0';
//                System.out.print(c+"\t");
//            }
//            System.out.println();
//        }
    }
}