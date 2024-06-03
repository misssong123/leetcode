package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class DistributeCandies1103 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了89.77% 的Java用户
     * 	内存消耗:40.2 MB,击败了12.50% 的Java用户
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandiesMy(int candies, int num_people) {
        int[] res = new int[num_people];
        int i = 1,j = 0;
        while(candies > 0){
            res[j] +=  Math.min(i,candies);
            candies -= i;
            j++;
            i++;
            j %= num_people;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.2 MB,击败了35.80% 的Java用户
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int n = num_people;
        // how many people received complete gifts
        int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
        int remaining = (int) (candies - (p + 1) * p * 0.5);
        int rows = p / n, cols = p % n;

        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            // complete rows
            d[i] = (i + 1) * rows + (int) (rows * (rows - 1) * 0.5) * n;
            // cols in the last row
            if (i < cols) {
                d[i] += i + 1 + rows * n;
            }
        }
        // remaining candies
        d[cols] += remaining;
        return d;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
