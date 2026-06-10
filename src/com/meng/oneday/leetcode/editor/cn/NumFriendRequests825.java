package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class NumFriendRequests825 {
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了34.07% 的Java用户
     * 	内存消耗:48.3 MB,击败了5.49% 的Java用户
     * @param ages
     * @return
     */
    public int numFriendRequests825(int[] ages) {
        Arrays.sort(ages);
        int res = 0;
        int pre = 0;
        for (int i = ages.length - 1; i >= 0; i--) {
            if (i < ages.length - 1 && ages[i] == ages[i + 1]){
                res += pre;
                continue;
            }
            pre = cal(i,ages);
            res += pre;
        }
        return res;
    }

    private int cal( int end,int[] ages) {
        int l = 0 , r = end,val = ages[end],temp=end;
        while (l <= r){
            int mid = (l + r) >> 1;
            if(val * 0.5 + 7 >= ages[mid]){
                l = mid + 1;
            }else{
                temp = mid;
                r = mid - 1;
            }
        }
        return end - temp ;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:47.3 MB,击败了45.05% 的Java用户
     * @param ages
     * @return
     */
    public int numFriendRequestsOther(int[] ages) {
        int[] cnt = new int[121];
        for (int age : ages) {
            cnt[age]++;
        }

        int ans = 0;
        int ageY = 0;
        int cntWindow = 0;
        for (int ageX = 0; ageX < cnt.length; ageX++) {
            cntWindow += cnt[ageX];
            if (ageY * 2 <= ageX + 14) { // 不能发送好友请求
                cntWindow -= cnt[ageY];
                ageY++;
            }
            if (cntWindow > 0) { // 存在可以发送好友请求的用户
                ans += cnt[ageX] * cntWindow - cnt[ageX];
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了67.03% 的Java用户
     * 	内存消耗:47.2 MB,击败了68.13% 的Java用户
     * @param ages
     * @return
     */
    public int numFriendRequests(int[] ages) {
        int[] cnt = new int[121];
        for (int age : ages) {
            cnt[age]++;
        }
        int[] pre = new int[122];
        for (int i = 1; i < 121; i++) {
            pre[i] = pre[i - 1] + cnt[i];
        }
        int ans = 0;
        for (int y = cnt.length - 1 ; y >= 15 ;y--){
            if (cnt[y] == 0){
                continue;
            }
            ans += (pre[y] - pre[y/2 + 7] - 1) * cnt[y];
        }
        return ans;
    }
}
