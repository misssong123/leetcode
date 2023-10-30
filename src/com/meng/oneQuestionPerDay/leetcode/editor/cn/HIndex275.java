package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class HIndex275 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:49.5 MB,击败了20.15% 的Java用户
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int len = citations.length;
        if (citations[len-1]==0){
            return 0;
        }
        int res = 1;
        int left = 1 , right = len;
        while (left<=right){
            int mid = (right + left) /2;
            int index = len - mid;
            if (citations[index] >= mid){
                left = mid + 1;
                res = mid;
            }else {
                right = mid-1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:49.8 MB,击败了5.04% 的Java用户
     * @param citations
     * @return
     */
    public int hIndex1(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }

}
