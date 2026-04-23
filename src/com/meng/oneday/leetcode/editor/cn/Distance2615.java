package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Distance2615 {
    /**
     * 超时
     * @param nums
     * @return
     */
    public long[] distanceTimeOut(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0 ; i < nums.length ; i++) {
            if (!map.containsKey(nums[i])){
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        long[] res = new long[nums.length];
        for (int i = 0 ; i < nums.length ; i++) {
            long dis = 0;
            if (map.get(nums[i]).size() > 1){
                for (int n : map.get(nums[i])) {
                    dis += Math.abs(n - i);
                }
            }
            res[i] = dis;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:72 ms,击败了5.69% 的Java用户
     * 	内存消耗:125.5 MB,击败了5.69% 的Java用户
     * @param nums
     * @return
     */
    public long[] distance2615(int[] nums) {
        int len = nums.length;
        long[] res = new long[len];
        Map<Integer,Integer> cnts = new HashMap<>(len);
        Map<Integer,Long> sums = new HashMap<>(len);
        //左侧记录
        for (int i = 0 ; i < len ; i++){
            int val = nums[i];
            if (cnts.containsKey(val)){
                res[i] = (long)i * cnts.get(val) - sums.get(val);
            }
            cnts.merge(val,1,Integer::sum);
            sums.merge(val,(long)i,Long::sum);
        }
        //重置
        cnts.clear();
        sums.clear();
        //右侧记录
        for (int i = len-1 ; i >= 0 ; i--){
            int val = nums[i];
            if (cnts.containsKey(val)){
                res[i] += ((long)(len - i) * cnts.get(val) - sums.get(val));
            }
            cnts.merge(val,1,Integer::sum);
            sums.merge(val,(long)len - i,Long::sum);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了52.03% 的Java用户
     * 	内存消耗:118.5 MB,击败了34.15% 的Java用户
     * @param nums
     * @return
     */
    public long[] distance(int[] nums) {
        int len = nums.length;
        long[] res = new long[len];
        Map<Integer,List<Integer>> indexMap = new HashMap<>();
        for (int i = 0 ; i < len ; i++){
            indexMap.computeIfAbsent(nums[i],k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> indexList : indexMap.values()){
            //左侧记录
            long dis = 0;
            for (int i = 0 ; i < indexList.size() ; i++){
                int index = indexList.get(i);
                res[index] = (long)i *  index - dis;
                dis += index;
            }
            //右侧记录
            dis = 0;
            for (int i = indexList.size() - 1 ; i >= 0 ; i--){
                int index = indexList.get(i);
                res[index] += (long)(indexList.size() - i -1) * (len -index) - dis;
                dis += (len - index);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了67.48% 的Java用户
     * 	内存消耗:114 MB,击败了91.06% 的Java用户
     * @param nums
     * @return
     */
    public long[] distanceOther(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> groups = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; ++i) // 相同元素分到同一组，记录下标
            groups.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);

        long[] ans = new long[n];
        long[] s = new long[n + 1];
        for (List<Integer> a : groups.values()) {
            int m = a.size();
            for (int i = 0; i < m; ++i)
                s[i + 1] = s[i] + a.get(i); // 前缀和
            for (int i = 0; i < m; ++i) {
                int target = a.get(i);
                long left = (long) target * i - s[i]; // 蓝色面积
                long right = s[m] - s[i] - (long) target * (m - i); // 绿色面积
                ans[target] = left + right;
            }
        }
        return ans;
    }

}
