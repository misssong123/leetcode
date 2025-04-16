package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class GoodTriplets2179 {
    /**
     * 超时
     * @param nums1
     * @param nums2
     * @return
     */
    public long goodTripletsTimeOut1(int[] nums1, int[] nums2) {
        Map<Integer,Integer> cache2 = new HashMap<>();
        //计算当前坐标的右侧元素个数
        for(int i = 0 ; i < nums1.length ; i++){
            cache2.put(nums2[i],i);
        }
        long res = 0;
        Map<Integer, List<Integer>> cache = new HashMap<>();
        for(int i = 0 ; i < nums1.length ; i++){
            List<Integer> list = new ArrayList<>();
            for(int j = i+1 ; j < nums1.length ; j++){
                if (cache2.get(nums1[j]) > cache2.get(nums1[i])){
                    list.add(nums1[j]);
                }
            }
            cache.put(nums1[i],list);
        }
        for(int i = 0 ; i < nums1.length ; i++){
            List<Integer> nums = cache.get(i);
            if (!nums.isEmpty()){
                for(int num : nums){
                    res += cache.get(num).size();
                }
            }
        }
        return res;
    }
    public long goodTripletsTimeOut2(int[] nums1, int[] nums2) {
        Map<Integer,Integer> cache = new HashMap<>();
        //计算当前坐标的右侧元素个数
        for(int i = 0 ; i < nums2.length ; i++){
            cache.put(nums2[i],i);
        }
        long res = 0;
        for(int i = 0 ; i < nums1.length ; i++){
            for (int j = i+1 ; j < nums1.length ; j++){
                if (cache.get(nums1[j]) > cache.get(nums1[i])){
                    for (int k = j+1 ; k < nums1.length ; k++){
                        if (cache.get(nums1[k]) > cache.get(nums1[j])){
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了100.00% 的Java用户
     * 	内存消耗:57.1 MB,击败了28.00% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public long goodTripletsTreeArr(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[nums1[i]] = i;
        }

        long ans = 0;
        FenwickTree t = new FenwickTree(n);
        for (int i = 0; i < n - 1; i++) {
            int y = p[nums2[i]];
            int less = t.pre(y);
            ans += (long) less * (n - 1 - y - (i - less));
            t.update(y + 1, 1);
        }
        return ans;
    }

    /**
     * 超出时间限制
     * @param nums1
     * @param nums2
     * @return
     */
    public long goodTripletsTimeOut3(int[] nums1, int[] nums2) {
        Map<Integer,Integer> cache = new HashMap<>();
        int len = nums1.length;
        //计算数值对应的坐标
        for(int i = 0 ; i < len ; i++){
            cache.put(nums1[i],i);
        }
        int[] middle = new int[len];
        //置换数据
        for (int i = 0; i < len; i++) {
            middle[i] = cache.get(nums2[i]);
        }
        //计算结果
        long res = 0L;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(middle[0]);
        for(int i = 1 ; i < len -1 ; i++){
            int left = set.headSet(middle[i]).size();
            if (left > 0){
                int right = (len - 1 - middle[i]) - (i - left);
                res += (long)left * right;
            }
            set.add(middle[i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了100.00% 的Java用户
     * 	内存消耗:56.7 MB,击败了38.14% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public long goodTriplets2179(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] index = new int[n];
        for(int i = 0 ; i < n ; i++){
            index[nums1[i]] = i;
        }
        long res = 0;
        NumPreArray arr = new NumPreArray(n);
        for(int i = 0 ; i < n -1  ; i++){
            int left = arr.pre(index[nums2[i]]);
            if (left > 0) {
                int right = n - 1 - index[nums2[i]] - (i - left);
                res += (long)left*right;
            }
            arr.update(index[nums2[i]]+1,1);
        }
        return res;
    }
}
class FenwickTree {
    private final int[] tree;

    public FenwickTree(int n) {
        tree = new int[n + 1]; // 使用下标 1 到 n
    }

    // a[i] 增加 val
    // 1 <= i <= n
    public void update(int i, long val) {
        for (; i < tree.length; i += i & -i) {
            tree[i] += val;
        }
    }

    // 求前缀和 a[1] + ... + a[i]
    // 1 <= i <= n
    public int pre(int i) {
        int res = 0;
        for (; i > 0; i &= i - 1) {
            res += tree[i];
        }
        return res;
    }
}
class NumPreArray{
    int[] trees;
    public NumPreArray(int n){
        trees = new int[n+1];
    }
    public void update(int index,int val){
        //计算差值
        for(int i = index;i < trees.length ; i += i&-i){
            trees[i] += val;
        }
    }
    public int pre(int index){
        int sum = 0;
        for(;index > 0;index -= index&-index){
            sum += trees[index];
        }
        return sum;
    }
}
