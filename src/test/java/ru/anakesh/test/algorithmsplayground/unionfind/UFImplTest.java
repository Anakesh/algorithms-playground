package ru.anakesh.test.algorithmsplayground.unionfind;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UFImplTest {
    private final URL tinyUF = getClass().getClassLoader().getResource("file/union_find/tinyUF.txt");

    private UF initUF(UfCreationFunction function) {
        assertNotNull(tinyUF);
        UF uf = null;
        try (InputStream tinyIs = tinyUF.openStream()) {
            Scanner scanner = new Scanner(tinyIs);
            uf = function.create(scanner.nextInt());
            while (scanner.hasNextInt()) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                if (!uf.connected(p, q)) {
                    uf.union(p, q);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uf;
    }

    void testTinyUFData(UF uf) {
        assertEquals(10, uf.count());

        assertFalse(uf.connected(0, 9));
        assertFalse(uf.connected(7, 3));
        assertFalse(uf.connected(6, 4));
        assertFalse(uf.connected(1, 9));

        assertTrue(uf.connected(0, 1));
        assertTrue(uf.connected(1, 7));
        assertTrue(uf.connected(2, 2));
        assertTrue(uf.connected(3, 9));
        assertTrue(uf.connected(8, 4));
    }

    @Test
    void quickFindTest() {
        UF quickFind = initUF(QuickFind::new);
        testTinyUFData(quickFind);
    }

    @Test
    void quickUnionTest() {
        UF quickFind = initUF(QuickUnion::new);
        testTinyUFData(quickFind);
    }

    @Test
    void weightedQuickUnionTest() {
        UF quickFind = initUF(WeightedQuickUnion::new);
        testTinyUFData(quickFind);
    }

    @Test
    void weightedQuickUnionWithPathCompressionTest() {
        UF quickFind = initUF(WeightedQuickUnionWithPathCompression::new);
        testTinyUFData(quickFind);
    }

    @Test
    void specificCanonicalElemTest() {
        UnionFindWithSpecificCanonicalElem sp = new UnionFindWithSpecificCanonicalElem(10);
        sp.union(1, 6);
        sp.union(2, 8);
        sp.union(1, 8);

        assertEquals(sp.find(1), sp.find(2));
        assertEquals(sp.find(1), sp.find(6));
        assertEquals(sp.find(1), sp.find(8));
        assertEquals(8, sp.find(2));

        sp.union(1, 9);

        assertEquals(9, sp.find(2));
    }

    private interface UfCreationFunction {
        UF create(int n);
    }


}