package com.meng.oneday.leetcode.editor.cn;

class NumberOfBeams2125 {
    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了26.44% 的Java用户
     * 	内存消耗:44.4 MB,击败了14.94% 的Java用户
     * @param bank
     * @return
     */
    public int numberOfBeams2125(String[] bank) {
        int res = 0,pre = 0,num = 0;
        for (String s : bank) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    num++;
                    res += pre;
                }
            }
            if (num != 0) {
                pre = num;
                num = 0;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了91.95% 的Java用户
     * 	内存消耗:44.4 MB,击败了14.94% 的Java用户
     * @param bank
     * @return
     */
    public int numberOfBeams(String[] bank) {

        int ans = 0;
        int preCnt = 0;
        for (String row : bank) {
            int cnt = 0;
            for (char ch : row.toCharArray()) {
                cnt += ch - '0';
            }
            if (cnt > 0) {
                ans += preCnt * cnt;
                preCnt = cnt;
            }
        }
        return ans;
    }
}
