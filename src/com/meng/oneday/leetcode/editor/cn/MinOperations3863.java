package com.meng.oneday.leetcode.editor.cn;

class MinOperations3863 {
    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了58.82% 的Java用户
     * 	内存消耗:46.9 MB,击败了100.00% 的Java用户
     * @param s
     * @return
     */
    public int minOperations3863(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();
        char minC = chars[0];
        int minIndex = 0;
        char maxC = chars[0];
        int maxIndex = 0;
        boolean flag = true;
        for(int i = 1 ; i < len ; i++){
            if (chars[i] < chars[i-1]){
                flag = false;
                if (len == 2){
                    return -1;
                }
            }
            if (chars[i] < minC){
                minC = chars[i];
                minIndex = i;
            }else if (chars[i] >= maxC){
                maxC = chars[i];
                maxIndex = i;
            }
        }
        if (flag){
            return 0;
        }
        if (minIndex == 0 || maxIndex == len-1){
            return 1;
        }
        if (minIndex == len-1 && maxIndex == 0){
            return 3;
        }
        return 2;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了100.00% 的Java用户
     * 	内存消耗:47.2 MB,击败了29.41% 的Java用户
     * @param S
     * @return
     */
    public int minOperationsOther(String S) {
        char[] s = S.toCharArray();
        int n = s.length;

        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (s[i - 1] > s[i]) {
                isSorted = false;
                break;
            }
        }
        // s 已经是升序
        if (isSorted) {
            return 0;
        }

        // 长为 2，无法排序
        if (n == 2) {
            return -1;
        }

        char mn = s[0];
        char mx = s[0];
        for (char ch : s) {
            mn = (char) Math.min(mn, ch);
            mx = (char) Math.max(mx, ch);
        }

        // 如果 s[0] 是最小值，排序 [1,n-1] 即可
        // 如果 s[n-1] 是最大值，排序 [0,n-2] 即可
        if (s[0] == mn || s[n - 1] == mx) {
            return 1;
        }

        // 如果 [1,n-2] 中有最小值，那么先排序 [0,n-2]，把最小值排在最前面，然后排序 [1,n-1] 即可
        // 如果 [1,n-2] 中有最大值，那么先排序 [1,n-1]，把最大值排在最后面，然后排序 [0,n-2] 即可
        for (int i = 1; i < n - 1; i++) {
            if (s[i] == mn || s[i] == mx) {
                return 2;
            }
        }

        // 现在只剩下一种情况：s[0] 是最大值，s[n-1] 是最小值，且 [1,n-2] 不含最小值和最大值
        // 先排序 [0,n-2]，把最大值排到 n-2
        // 然后排序 [1,n-1]，把最大值排在最后面，且最小值排在 1
        // 最后排序 [0,n-2]，把最小值排在最前面
        return 3;
    }

}
