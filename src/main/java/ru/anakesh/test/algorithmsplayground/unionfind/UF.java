package ru.anakesh.test.algorithmsplayground.unionfind;

public abstract class UF {
    protected final int n;

    protected UF(int n) {
        this.n = n;
    }

    abstract void union(int p, int q);

    abstract boolean connected(int p, int q);

    abstract int count();
}
