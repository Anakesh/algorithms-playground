package ru.anakesh.test.algorithmsplayground.unionfind;

public class UnionFindWithSpecificCanonicalElem extends WeightedQuickUnionWithPathCompression {
    private final int[] largestElemArray;

    protected UnionFindWithSpecificCanonicalElem(int n) {
        super(n);
        largestElemArray = new int[n];
        for (int i = 0; i < largestElemArray.length; i++) {
            largestElemArray[i] = i;
        }
    }

    @Override
    void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot]) {
            idArray[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
            updateLargestElemArray(pRoot, qRoot);
        } else {
            idArray[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
            updateLargestElemArray(qRoot, pRoot);
        }
    }

    private void updateLargestElemArray(int oldRoot, int newRoot) {
        if (largestElemArray[oldRoot] > largestElemArray[newRoot]) {
            largestElemArray[newRoot] = largestElemArray[oldRoot];
        }
    }

    public int find(int p) {
        return largestElemArray[findRoot(p)];
    }
}
