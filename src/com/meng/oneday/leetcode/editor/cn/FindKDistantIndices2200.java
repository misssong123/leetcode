package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class FindKDistantIndices2200 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了95.45% 的Java用户
     * 	内存消耗:43.7 MB,击败了92.42% 的Java用户
     * @param nums
     * @param key
     * @param k
     * @return
     */
    public List<Integer> findKDistantIndices2200(int[] nums, int key, int k) {
        List<Integer> res = new ArrayList<>();
        int l = 0 ,r = 0;
        while(r < nums.length){
            if (r - l > k){
                l++;
            }
            if (nums[r] == key){
                while (l <=r+k && l < nums.length){
                    res.add(l++);
                    //更新过程中的key坐标
                    if (l < nums.length && nums[l] == key&&l > r){
                        r = l;
                    }
                }
            }
            r = Math.max(r+1,l);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了93.94% 的Java用户
     * @param nums
     * @param key
     * @param k
     * @return
     */
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int last = -k - 1; // 保证 key 不存在时 last < i-k
        for (int i = k - 1; i >= 0; i--) {
            if (nums[i] == key) {
                last = i;
                break;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i + k < nums.length && nums[i + k] == key) {
                last = i + k;
            }
            if (last >= i - k) { // key 在窗口中
                ans.add(i);
            }
        }
        return ans;
    }

}
