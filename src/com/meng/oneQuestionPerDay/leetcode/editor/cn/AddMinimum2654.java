package com.meng.oneQuestionPerDay.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class AddMinimum2654 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.8 MB,击败了10.26% 的Java用户
     * @param word
     * @return
     */
    public int addMinimumMy(String word) {
        int res = 0;
        for(int i = 0 ;i < word.length() ; i++){
            res+=2;
            if (word.charAt(i)=='a'){
                if (i+1<word.length()){
                    if (word.charAt(i+1)=='b'){
                        res--;
                        i++;
                        if (i+1<word.length() && word.charAt(i+1)=='c'){
                            res--;
                            i++;
                        }
                    }else if (word.charAt(i+1)=='c'){
                        res--;
                        i++;
                    }
                }
            }else if (word.charAt(i)=='b'){
                if (i+1<word.length() && word.charAt(i+1)=='c'){
                    i++;
                    res--;
                }
            }
        }
        return res;
    }

    /**
     * 动态规划
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.7 MB,击败了14.53% 的Java用户
     * @param word
     * @return
     */
    public int addMinimum1(String word) {
        int n = word.length();
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 2;
            if (i > 1 && word.charAt(i - 1) > word.charAt(i - 2)) {
                d[i] = d[i - 1] - 1;
            }
        }
        return d[n];
    }

    /**
     * 直接拼接
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.7 MB,击败了14.53% 的Java用户
     * @param word
     * @return
     */
    public int addMinimum2(String word) {
        int n = word.length();
        int res = word.charAt(0) - word.charAt(n - 1) + 2;
        for (int i = 1; i < n; i++) {
            res += (word.charAt(i) - word.charAt(i - 1) + 2) % 3;
        }
        return res;
    }

    /**
     * 计算组数
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.7 MB,击败了14.53% 的Java用户
     * @param word
     * @return
     */
    public int addMinimum3(String word) {
        int n = word.length(), cnt = 1;
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) <= word.charAt(i - 1)) {
                cnt++;
            }
        }
        return cnt * 3 - n;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
