package ru.anakesh.test.algorithmsplayground.unionfind;

public class QuickFind extends UF {
    private final int[] idArray;

    protected QuickFind(int n) {
        super(n);
        idArray = new int[n];
        for (int i = 0; i < idArray.length; i++) {
            idArray[i] = i;
        }
    }

    @Override
    void union(int p, int q) {
        if (!connected(p, q)) {
            changeId(idArray[p], idArray[q]);
        }
    }

    private void changeId(int oldId, int newId) {
        for (int i = 0; i < idArray.length; i++) {
            if (idArray[i] == oldId) {
                idArray[i] = newId;
            }
        }
    }

    @Override
    boolean connected(int p, int q) {
        return idArray[p] == idArray[q];
    }

    @Override
    int count() {
        return idArray.length;
    }
}
