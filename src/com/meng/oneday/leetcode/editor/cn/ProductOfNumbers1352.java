package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 解答成功:
 * 	执行耗时:22 ms,击败了17.05% 的Java用户
 * 	内存消耗:85 MB,击败了7.95% 的Java用户
 */
class ProductOfNumbers1352 {
    List<Integer> pre;
    Set<Integer> zero;
    int size;
    public ProductOfNumbers1352() {
        pre = new ArrayList<>(1024);
        zero = new HashSet<>();
        size = 0;
    }
    
    public void add(int num) {
        if(num == 0){
            zero.add(size);
        }
        if (size == 0 || pre.get(size - 1) == 0){
            pre.add(num);
        }else {
            pre.add(pre.get(size - 1) * num);
        }
        size++;
    }
    
    public int getProduct(int k) {
        int end = size - 1;
        int start = size - k;
        //是否包含0
        if(!zero.isEmpty()){
            for (int i = start; i <= end; i++) {
                if (zero.contains(i)){
                    return 0;
                }
            }
        }
        if(start == 0 || pre.get(start - 1) == 0){
            return pre.get(end);
        }
        return pre.get(end) / pre.get(start - 1);
    }

}

/**
 * 解答成功:
 * 	执行耗时:18 ms,击败了77.27% 的Java用户
 * 	内存消耗:83.4 MB,击败了28.41% 的Java用户
 */
class ProductOfNumbers {
    private final List<Integer> p = new ArrayList<>();

    public ProductOfNumbers() {
        p.add(1);
    }

    public void add(int num) {
        if (num != 0) {
            p.add(p.getLast() * num);
        } else {
            p.clear();
            p.add(1);
        }
    }

    public int getProduct(int k) {
        int n = p.size();
        if (n <= k) {
            return 0;
        }
        return p.getLast() / p.get(n - 1 - k);
    }
}


