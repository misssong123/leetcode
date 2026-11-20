package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MaxSelectedElements3041 {
    /**
     * 超时
     * @param nums
     * @return
     */
    public int maxSelectedElementsTimeOut(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> used = new HashSet<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 1;
        for (int num : nums) {
            //包含在更长的子串中
            if (map.containsKey(num - 1) ||
                    used.contains(num)) {
                continue;
            }
            //两种可能
            //1.使用自身
            res = Math.max(res,maxSelectedElements(num,true,map));
            used.add(num);
            //2.使用自身加1
            if (!used.contains(num+1)){
                res = Math.max(res,maxSelectedElements(num+1,false,map));
                used.add(num+1);
            }
        }
        return res;
    }
    private int maxSelectedElements(int cur,
                                     boolean flag ,Map<Integer, Integer> map ) {
        int step = 1;
        while (true){
            //优先使用自身
            if (map.containsKey(cur) && (map.get(cur) > 1 || !flag)){
                step++;
                flag = false;
                cur++;
            }else if (map.containsKey(cur + 1)){
                step++;
                flag = true;
                cur++;
            }else {
                break;
            }
        }
        return step;
    }

    /**
     * 解答成功:
     * 	执行耗时:106 ms,击败了12.50% 的Java用户
     * 	内存消耗:116.2 MB,击败了12.50% 的Java用户
     * @param nums
     * @return
     */
    public int maxSelectedElementsAi(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 1;
        for (int num : nums) {
            // 1. 先用 num 尝试变成 num + 1，接在 num 后面
            int nextLen = dp.getOrDefault(num, 0) + 1;
            dp.put(num + 1, Math.max(dp.getOrDefault(num + 1, 0), nextLen));
            ans = Math.max(ans, dp.get(num + 1));

            // 2. 再用 num 保持不变，接在 num - 1 后面
            int curLen = dp.getOrDefault(num - 1, 0) + 1;
            dp.put(num, Math.max(dp.getOrDefault(num, 0), curLen));
            ans = Math.max(ans, dp.get(num));
        }

        return ans;
    }

    /**
     *
     * @param nums
     * @return
     */
    public int maxSelectedElementsOther(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> f = new HashMap<>();
        for (int x : nums) {
            f.put(x + 1, f.getOrDefault(x, 0) + 1);
            f.put(x, f.getOrDefault(x - 1, 0) + 1);
        }
        return Collections.max(f.values());
    }

    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了100.00% 的Java用户
     * 	内存消耗:71.4 MB,击败了100.00% 的Java用户
     * @param nums
     * @return
     */
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int f0 = 0; // f[x-1]
        int f1 = 0; // f[x]
        int f2 = 0; // f[x+1]
        int pre = 0; // pre 初始值随意，不影响计算
        int ans = 0;
        for (int x : nums) {
            if (x == pre + 1) {
                // 类似 70. 爬楼梯 的空间优化
                f0 = f1;
                f1 = f2;
            } else if (x == pre + 2) {
                f0 = f2;
                f1 = 0;
            } else if (x > pre + 2) {
                f0 = 0;
                f1 = 0;
            }

            f2 = f1 + 1;
            f1 = f0 + 1;
            ans = Math.max(ans, Math.max(f1, f2));
            pre = x;
        }
        return ans;
    }

}
