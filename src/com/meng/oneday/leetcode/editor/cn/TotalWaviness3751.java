package com.meng.oneday.leetcode.editor.cn;

class TotalWaviness3751 {
    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了65.12% 的Java用户
     * 	内存消耗:45.9 MB,击败了13.95% 的Java用户
     * @param num1
     * @param num2
     * @return
     */
    public int totalWaviness3751(int num1, int num2) {
        if (num2 < 100){
            return 0;
        }
        int res = 0;
        for (int num = Math.max(100,num1); num <= num2; num++) {
            res += cal(num);
        }
        return res;
    }

    private int cal(int num) {
        int temp = 0;
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 1 ; i < chars.length-1; i++) {
            if (chars[i] > chars[i-1] && chars[i] > chars[i+1]){
                temp++;
            }else if (chars[i] < chars[i-1] && chars[i] < chars[i+1]){
                temp++;
            }
        }
        return temp;
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了95.35% 的Java用户
     * 	内存消耗:42.1 MB,击败了76.74% 的Java用户
     * @param num1
     * @param num2
     * @return
     */
    public int totalWavinessOther1(int num1, int num2) {
        if (num2 < 100) {
            return 0;
        }
        int res = 0;
        int start = Math.max(100, num1);
        for (int num = start; num <= num2; num++) {
            res += calOptimized(num);
        }
        return res;
    }

    private int calOptimized(int num) {
        int temp = 0;
        // 从低位向高位检查
        int last2 = num % 10;
        num /= 10;
        int last1 = num % 10;
        num /= 10;

        while (num > 0) {
            int curr = num % 10;
            // last1 是中间位置，curr 是高位，last2 是低位
            if ((last1 > curr && last1 > last2) || (last1 < curr && last1 < last2)) {
                temp++;
            }
            // 滚动更新
            last2 = last1;
            last1 = curr;
            num /= 10;
        }
        return temp;
    }

}
