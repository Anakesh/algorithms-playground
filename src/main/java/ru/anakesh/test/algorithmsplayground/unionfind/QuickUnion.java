package ru.anakesh.test.algorithmsplayground.unionfind;

public class QuickUnion extends UF {
    protected final int[] idArray;

    protected QuickUnion(int n) {
        super(n);
        idArray = new int[n];
        for (int i = 0; i < idArray.length; i++) {
            idArray[i] = i;
        }
    }

    @Override
    void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        idArray[pRoot] = qRoot;
    }

    protected int findRoot(final int i) {
        int root = i;
        while (idArray[root] != root) {
            root = idArray[root];
        }
        return root;
    }

    @Override
    boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    @Override
    int count() {
        return idArray.length;
    }
}
