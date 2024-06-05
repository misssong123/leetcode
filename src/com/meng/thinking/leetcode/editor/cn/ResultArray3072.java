package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class ResultArray3072 {
    public int[] resultArrayTimeOut(int[] nums) {
        TreeSet<Integer>  treeSet1 = new TreeSet<>();
        TreeSet<Integer>  treeSet2 = new TreeSet<>();
        Map<Integer,Integer> map1 = new HashMap<>();
        Map<Integer,Integer> map2 = new HashMap<>();
        List<Integer>[] cache = new List[2];
        for(int i = 0; i < 2; i++){
            cache[i] = new ArrayList<>();
        }
        cache[0].add(nums[0]);
        cache[1].add(nums[1]);
        treeSet1.add(nums[0]);
        treeSet2.add(nums[1]);
        map1.put(nums[0],1);
        map2.put(nums[1],1);
        for(int i = 2; i < nums.length ; i++){
            int val = nums[i];
            int count1 =  getCount(treeSet1,map1,val);
            int count2 =  getCount(treeSet2,map2,val);
            if(count1 > count2){
                treeSet1.add(val);
                map1.put(val,map1.getOrDefault(val,0)+1);
                cache[0].add(val);
            }else if (count1 < count2){
                treeSet2.add(val);
                map2.put(val,map2.getOrDefault(val,0)+1);
                cache[1].add(val);
            }else {
                if(cache[0].size()<=cache[1].size()){
                    treeSet1.add(val);
                    map1.put(val,map1.getOrDefault(val,0)+1);
                    cache[0].add(val);
                }else{
                    treeSet2.add(val);
                    map2.put(val,map2.getOrDefault(val,0)+1);
                    cache[1].add(val);
                }
            }
        }
        int[] res = new int[nums.length];
        int index = 0;
        for(int x : cache[0]){
            res[index++] = x;
        }
        for(int x : cache[1]){
            res[index++] = x;
        }
        return res;
    }
    private int getCount(TreeSet<Integer> treeSet,Map<Integer,Integer> map,int val){
        int count = 0;
        for (Integer integer : treeSet.tailSet(val, false)) {
            count += map.get(integer);
        }
        return count;
    }

    /**
     * 解答成功:
     * 	执行耗时:739 ms,击败了16.20% 的Java用户
     * 	内存消耗:59.1 MB,击败了98.00% 的Java用户
     * @param nums
     * @return
     */
    public int[] resultArrayMy(int[] nums) {
        int n = nums.length;
        List<Integer> cache1 = new ArrayList<>(n);
        List<Integer> cache2 = new ArrayList<>(n);
        List<Integer>[] cache = new List[2];
        for(int i = 0; i < 2; i++){
            cache[i] = new ArrayList<>();
        }
        cache[0].add(nums[0]);
        cache[1].add(nums[1]);
        cache1.add(nums[0]);
        cache2.add(nums[1]);
        for (int i = 2; i < n; i++){
            int val = nums[i];
            int index1 =  highBound(cache1,val);
            int index2 =  highBound(cache2,val);
            int count1 = cache1.size()-index1;
            int count2 = cache2.size()-index2;
            if(count1 > count2){
                cache1.add(index1,val);
                cache[0].add(val);
            }else if (count1 < count2){
                cache2.add(index2,val);
                cache[1].add(val);
            }else {
                if(cache[0].size()<=cache[1].size()){
                    cache1.add(index1,val);
                    cache[0].add(val);
                }else{
                    cache2.add(index2,val);
                    cache[1].add(val);
                }
            }
        }
        int[] res = new int[nums.length];
        int index = 0;
        for(int x : cache[0]){
            res[index++] = x;
        }
        for(int x : cache[1]){
            res[index++] = x;
        }
        return res;
    }
    public int highBound(List<Integer> nums, int val) {
        int left = 0, right = nums.size()-1;
        while (left <= right) {
            int mid = left + (right - left)/ 2;
            if (nums.get(mid)>val){
                right = mid - 1;
            }else {
                left = mid+1;
            }
        }
        return left;
    }

    /**
     * 解答成功:
     * 	执行耗时:75 ms,击败了98.33% 的Java用户
     * 	内存消耗:60.6 MB,击败了94.83% 的Java用户
     * @param nums
     * @return
     */
    public int[] resultArray(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted); // 只排序不去重
        int n = nums.length;

        List<Integer> a = new ArrayList<>(n); // 预分配空间
        List<Integer> b = new ArrayList<>();
        a.add(nums[0]);
        b.add(nums[1]);

        Fenwick t = new Fenwick(n + 1);
        t.add(n - Arrays.binarySearch(sorted, nums[0]), 1);
        t.add(n - Arrays.binarySearch(sorted, nums[1]), -1);

        for (int i = 2; i < nums.length; i++) {
            int x = nums[i];
            int v = n - Arrays.binarySearch(sorted, x);
            int d = t.pre(v - 1); // 转换成 < v 的元素个数之差
            if (d > 0 || d == 0 && a.size() <= b.size()) {
                a.add(x);
                t.add(v, 1);
            } else {
                b.add(x);
                t.add(v, -1);
            }
        }
        a.addAll(b);
        for (int i = 0; i < n; i++) {
            nums[i] = a.get(i);
        }
        return nums;
    }



}
class Fenwick {
    private final int[] tree;

    public Fenwick(int n) {
        tree = new int[n];
    }

    // 把下标为 i 的元素增加 v
    public void add(int i, int v) {
        while (i < tree.length) {
            tree[i] += v;
            i += i & -i;
        }
    }

    // 返回下标在 [1,i] 的元素之和
    public int pre(int i) {
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i &= i - 1;
        }
        return res;
    }
}
