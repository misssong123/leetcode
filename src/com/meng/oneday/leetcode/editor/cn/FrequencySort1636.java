package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class FrequencySort1636 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了70.19% 的Java用户
     * 	内存消耗:45 MB,击败了57.69% 的Java用户
     * @param nums
     * @return
     */
    public int[] frequencySort1636(int[] nums) {
        //统计详情
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //抽取统计
        int[][] arr = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            arr[index++] = new int[]{entry.getKey(),entry.getValue()};
        }
        //排序
        Arrays.sort(arr,(a,b)->{
            if (a[1] != b[1]){
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        //结果输出
        int[] res = new int[nums.length];
        index = 0;
        for (int [] a : arr) {
            for (int i = 0; i < a[1]; i++) {
                res[index++] = a[0];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了70.19% 的Java用户
     * 	内存消耗:45.1 MB,击败了44.23% 的Java用户
     * @param nums
     * @return
     */
    public int[] frequencySort(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        List<int[]> list = new ArrayList<>();
        for (int key : map.keySet()) list.add(new int[]{key, map.get(key)});
        Collections.sort(list, (a, b)->{
            return a[1] != b[1] ? a[1] - b[1] : b[0] - a[0];
        });
        int[] ans = new int[n];
        int idx = 0;
        for (int[] info : list) {
            while (info[1]-- > 0) ans[idx++] = info[0];
        }
        return ans;
    }

}
