package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class GetLargestOutlier3371 {
    /**
     * 解答成功:
     * 	执行耗时:89 ms,击败了76.71% 的Java用户
     * 	内存消耗:108 MB,击败了31.74% 的Java用户
     * @param nums
     * @return
     */
    public int getLargestOutlier3371(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        for (int num : nums) {
            sum += num;
            map.merge(num,1,Integer::sum);
        }
        int res = -1000;
        for(int num : nums){
            int cnt = sum - num;
            if(cnt % 2 == 0 && map.containsKey(cnt / 2)
                    && (cnt / 2 != num || map.get(cnt/2) > 1 )){
                res = Math.max(res,num);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:177 ms,击败了25.80% 的Java用户
     * 	内存消耗:114.7 MB,击败了8.22% 的Java用户
     * @param nums
     * @return
     */
    public int getLargestOutlierOther(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int total = 0;
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
            total += x;
        }

        int ans = Integer.MIN_VALUE;
        for (int x : nums) {
            cnt.merge(x, -1, Integer::sum);
            if ((total - x) % 2 == 0 && cnt.getOrDefault((total - x) / 2, 0) > 0) {
                ans = Math.max(ans, x);
            }
            cnt.merge(x, 1, Integer::sum);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:87 ms,击败了83.11% 的Java用户
     * 	内存消耗:102.1 MB,击败了41.56% 的Java用户
     * @param nums
     * @return
     */
    public int getLargestOutlier(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int total = 0;
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
            total += x;
        }

        int ans = Integer.MIN_VALUE;
        for (int y : nums) {
            int t = total - y * 2;
            if (cnt.containsKey(t) && (t != y || cnt.get(t) > 1)) {
                ans = Math.max(ans, t);
            }
        }
        return ans;
    }

}
