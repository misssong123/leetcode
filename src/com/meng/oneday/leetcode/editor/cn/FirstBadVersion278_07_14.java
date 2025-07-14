package com.meng.oneday.leetcode.editor.cn;

public class FirstBadVersion278_07_14 extends VersionControl {
    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了99.63% 的Java用户
     * 	内存消耗:39.6 MB,击败了47.88% 的Java用户
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int left = 1,right = n;
        while(left < right){
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return right;
    }
}
class VersionControl{
    boolean isBadVersion(int version){
        return false;
    }
}