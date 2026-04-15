package com.meng.oneday.leetcode.editor.cn;

class ClosestTarget2515 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了33.33% 的Java用户
     * 	内存消耗:46 MB,击败了100.00% 的Java用户
     * @param words
     * @param target
     * @param startIndex
     * @return
     */
    public int closestTarget2515(String[] words, String target, int startIndex) {
        int len = words.length;
        int res = len;
        for (int i = 0 ; i <len ; i++){
            if (words[i].equals(target)){
                int dis = Math.abs(i-startIndex);
                dis = Math.min(dis,len-dis);
                res = Math.min(res,dis);
            }
        }
        return res ==len ? -1 : res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.1 MB,击败了100.00% 的Java用户
     * @param words
     * @param target
     * @param startIndex
     * @return
     */
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        for (int k = 0; k <= n / 2; k++) {
            if (words[(startIndex - k + n) % n].equals(target) || words[(startIndex + k) % n].equals(target)) {
                return k;
            }
        }
        return -1;
    }
}
