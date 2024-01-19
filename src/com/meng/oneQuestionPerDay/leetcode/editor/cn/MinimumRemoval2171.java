package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class MinimumRemoval2171 {
    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了44.88% 的Java用户
     * 	内存消耗:62.7 MB,击败了22.25% 的Java用户
     * @param beans
     * @return
     */
    public long minimumRemovalMy(int[] beans) {
        long ans = 0L;
        Arrays.sort(beans);
        for(int i = 0 ; i < beans.length ; i++){
            ans += beans[i];
        }
        long sum = ans;
        for (int i = 0 ; i < beans.length ; i++){
            ans =  Math.min(ans,sum - (long)beans[i] * (beans.length - i));
        }
        return ans;
    }

    /**
     *解答成功:
     * 	执行耗时:38 ms,击败了44.88% 的Java用户
     * 	内存消耗:62.6 MB,击败了41.69% 的Java用户
     * @param beans
     * @return
     */
    public long minimumRemoval(int[] beans) {
        int n = beans.length;
        Arrays.sort(beans);
        long total = 0; // 豆子总数
        for (int i = 0; i < n; i++) {
            total += beans[i];
        }
        long res = total; // 最少需要移除的豆子数
        for (int i = 0; i < n; i++) {
            res = Math.min(res, total - (long) beans[i] * (n - i));
        }
        return res;
    }


}
