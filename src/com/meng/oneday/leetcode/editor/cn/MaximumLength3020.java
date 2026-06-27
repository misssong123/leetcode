package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class MaximumLength3020 {
    /**
     * 解答成功:
     * 	执行耗时:55 ms,击败了100.00% 的Java用户
     * 	内存消耗:68 MB,击败了27.78% 的Java用户
     * @param nums
     * @return
     */
    public int maximumLength3020(int[] nums) {
        Map<Integer,Integer> cnts = new HashMap<>();
        //计算元素个数
        for (int num : nums) {
            cnts.put(num,cnts.getOrDefault(num,0)+1);
        }
        int res = 1;
        for (Map.Entry<Integer, Integer> entry : cnts.entrySet()) {
            int key = entry.getKey();
            int cnt = entry.getValue();
            if (key == 1){
                res = Math.max(res,cnt % 2 == 0 ? cnt -1 : cnt);
                continue;
            }
            if (cnt == 1){
                continue;
            }
            //无满足数据
            if (cnts.getOrDefault(key*key,0) == 0){
                continue;
            }
            int sqrt = (int)Math.sqrt(key);
            //被子集覆盖
            if (sqrt*sqrt == key && cnts.getOrDefault(sqrt,0) > 1){
                continue;
            }
            int temp = 1;
            int num = key * key;
            while (cnts.getOrDefault(num,0) > 0){
                temp+=2;
                if (cnts.get(num) == 1){
                    break;
                }
                num = num * num;
            }
            res = Math.max(res,temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:57 ms,击败了77.78% 的Java用户
     * 	内存消耗:65.4 MB,击败了94.44% 的Java用户
     * @param nums
     * @return
     */
    public int maximumLength(int[] nums) {
        // 使用 HashMap 统计每个数字出现的频次 (等价于 Python 的 Counter)
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        // 1. 特殊处理数字 1
        // 如果有 1，由于 1 的任意次方都是 1，它们可以组成 [1, 1, ..., 1] 的序列。
        // 根据题目要求，长度必须是奇数。如果 1 的个数是偶数，必须减 1 变成最大奇数。
        int ans = 0;
        if (cnt.containsKey(1)) {
            int count1 = cnt.get(1);
            ans = (count1 % 2 == 0) ? count1 - 1 : count1;
            cnt.remove(1); // 移除 1，避免后续重复遍历
        } else {
            ans = 1; // 哪怕没有 1，任意单个数字也能组成长度为 1 的序列
        }

        // 2. 遍历其余数字作为序列的起点
        for (int x : cnt.keySet()) {
            // 剪枝优化：如果 x 是一个完全平方数，且它的平方根 rt 在 map 中数量 >= 2，
            // 那么从 rt 开始作为起点推导出的序列一定会比从 x 开始的更长。直接跳过 x。
            int rt = (int) Math.sqrt(x);
            if (rt * rt == x && cnt.getOrDefault(rt, 0) >= 2) {
                continue;
            }

            int res = 0;
            int cur = x;

            // 只要当前数字 cur 的数量 >= 2，就可以作为序列左右两对称位置的元素
            while (cnt.getOrDefault(cur, 0) >= 2) {
                res += 2;
                cur = cur * cur;
            }

            // 检查序列中心的峰值元素：如果最后的 cur 存在于 map 中（数量至少为 1），
            // 它可以放在正中间，此时长度 +1 (即 res + 1)；
            // 如果最后的 cur 已经不存在了，说明上一步错把只能提供 1 个的元素削减了，此时应该 -1 (即 res - 1)。
            if (cnt.containsKey(cur)) {
                ans = Math.max(ans, res + 1);
            } else {
                ans = Math.max(ans, res - 1);
            }
        }

        return ans;
    }
}
