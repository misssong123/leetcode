package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.TreeSet;

class MaxDistance1552 {
    /**
     * 解答成功:
     * 	执行耗时:258 ms,击败了5.13% 的Java用户
     * 	内存消耗:57.7 MB,击败了5.13% 的Java用户
     * @param position
     * @param m
     * @return
     */
    public int maxDistanceMy(int[] position, int m) {
        Arrays.sort(position);
        if (m == 2){
            return position[position.length-1] - position[0];
        }
        int max = (position[position.length-1] - position[0])/(m-1);
        if (max == 1){
            return 1;
        }
        //存储位置便于查找
        TreeSet<Integer> cache = new TreeSet<>();
        for (int j : position) {
            cache.add(j);
        }
        int left = 1 ,right = max;
        while (left <= right){
            int mid = (left+right)/2;
            if(check(cache,mid,m)){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return right;
    }
    private boolean check(TreeSet<Integer> cache, int mid, int m) {
        int pre = cache.first();
        while (m-- > 1){
            Integer next = cache.ceiling(pre+mid);
            if (next == null){
                return false;
            }
            pre = next;
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:46 ms,击败了78.21% 的Java用户
     * 	内存消耗:55 MB,击败了13.46% 的Java用户
     * @param position
     * @param m
     * @return
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1, right = position[position.length - 1] - position[0], ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, position, m)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public boolean check(int x, int[] position, int m) {
        int pre = position[0], cnt = 1;
        for (int i = 1; i < position.length; ++i) {
            if (position[i] - pre >= x) {
                pre = position[i];
                cnt += 1;
            }
        }
        return cnt >= m;
    }
}

