package ru.anakesh.test.algorithmsplayground.unionfind;

public class WeightedQuickUnion extends QuickUnion {
    protected final int[] sz;

    protected WeightedQuickUnion(int n) {
        super(n);
        sz = new int[n];
    }

    @Override
    void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot]) {
            idArray[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            idArray[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
