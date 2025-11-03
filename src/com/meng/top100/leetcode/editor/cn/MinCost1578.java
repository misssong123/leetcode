package com.meng.top100.leetcode.editor.cn;

class MinCost1578 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了98.05% 的Java用户
     * 	内存消耗:92.7 MB,击败了5.84% 的Java用户
     * @param colors
     * @param neededTime
     * @return
     */
    public int minCost1578(String colors, int[] neededTime) {
        int len = neededTime.length;
        char[] colorArr = colors.toCharArray();
        int res = 0, l = 0 ,r = 0;
        while ( r < len) {
            if(colorArr[l] != colorArr[r]){
                if (r - l > 1){
                    int max = 0,sum = 0;
                    for(int i = l ; i < r ; i++){
                        max = Math.max(max,neededTime[i]);
                        sum += neededTime[i];
                    }
                    res += sum - max;
                }
                l = r;
            }
            r++;
        }
        if (r - l > 1){
            int max = 0,sum = 0;
            for(int i = l ; i < r ; i++){
                max = Math.max(max,neededTime[i]);
                sum += neededTime[i];
            }
            res += sum - max;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了32.47% 的Java用户
     * 	内存消耗:91.2 MB,击败了5.84% 的Java用户
     * @param colors
     * @param neededTime
     * @return
     */
    public int minCost(String colors, int[] neededTime) {
        int n = neededTime.length;
        int ans = 0;
        int maxT = 0;
        for (int i = 0; i < n; i++) {
            int t = neededTime[i];
            ans += t;
            maxT = Math.max(maxT, t);
            if (i == n - 1 || colors.charAt(i) != colors.charAt(i + 1)) {
                // 遍历到了连续同色段的末尾
                ans -= maxT; // 保留耗时最大的气球
                maxT = 0; // 准备计算下一段的最大耗时
            }
        }
        return ans;
    }

}
