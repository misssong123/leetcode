package com.meng.oneday.leetcode.editor.cn;

import java.util.NoSuchElementException;

/**
 * 解答成功:
 * 	执行耗时:8 ms,击败了100.00% 的Java用户
 * 	内存消耗:47.4 MB,击败了38.30% 的Java用户
 */
class Vector2D251 {
    int[][] vec;
    int size;
    int index;
    int innerIndex;
    public Vector2D251(int[][] vec) {
        this.vec = vec;
        this.size = vec.length;
        this.index = 0;
        this.innerIndex = 0;
        while (index < size && vec[index].length == 0){
            index++;
        }
    }
    
    public int next() {
        int val = vec[index][innerIndex];
        if(innerIndex + 1 < vec[index].length){
            innerIndex++;
        }else {
            innerIndex = 0;
            do {
                index++;
            } while (index < size && vec[index].length == 0);
        }
        return val;
    }
    
    public boolean hasNext() {
        return index < size;
    }
}

/**
 * 解答成功:
 * 	执行耗时:8 ms,击败了100.00% 的Java用户
 * 	内存消耗:47.2 MB,击败了91.49% 的Java用户
 */
class Vector2D {
    private int[][] vector;
    private int inner = 0;
    private int outer = 0;
    public Vector2D(int[][] v) {
        vector = v;
    }

    private void advanceToNext() {
        while (outer < vector.length && inner == vector[outer].length) {
            inner = 0;
            outer++;
        }
    }

    public int next() {
        if (!hasNext()) throw new NoSuchElementException();
        return vector[outer][inner++];
    }

    public boolean hasNext() {
        advanceToNext();
        return outer < vector.length;
    }
}


