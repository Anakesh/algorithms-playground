package ru.anakesh.test.algorithmsplayground.unionfind;

public class WeightedQuickUnionWithPathCompression extends WeightedQuickUnion {
    protected WeightedQuickUnionWithPathCompression(int n) {
        super(n);
    }

    @Override
    protected int findRoot(int i) {
        int root = super.findRoot(i);
        for (int j = i; idArray[j] != root; ) {
            int next = idArray[j];
            idArray[j] = root;
            j = next;
        }
        return root;
    }
}
