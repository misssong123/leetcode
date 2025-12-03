package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class FindRightInterval436 {
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了36.95% 的Java用户
     * 	内存消耗:48.6 MB,击败了60.10% 的Java用户
     * @param intervals
     * @return
     */
    public int[] findRightInterval436(int[][] intervals) {
        int len = intervals.length;
        int[] res = new int[len];
        int[] left = new int[len];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < len ; i++){
            left[i] = intervals[i][0];
            map.put(left[i],i);
        }
        Arrays.sort(left);
        for(int i = 0 ; i < len ; i++){
            int target = search(left,intervals[i][1]);
            res[i] =  target == -1 ? -1 : map.get(left[target]);
        }
        return res;
    }
    public int search(int[] nums,int target){
        int left = 0 , right = nums.length - 1,res = -1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target){
                res = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了32.02% 的Java用户
     * 	内存消耗:49.1 MB,击败了52.71% 的Java用户
     * @param its
     * @return
     */
    public int[] findRightIntervalOther(int[][] its) {
        int n = its.length;
        int[][] ss = new int[n][2], es = new int[n][2];
        for (int i = 0; i < n; i++) {
            ss[i] = new int[]{its[i][0], i};
            es[i] = new int[]{its[i][1], i};
        }
        Arrays.sort(ss, (a,b)->a[0]-b[0]);
        Arrays.sort(es, (a,b)->a[0]-b[0]);
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            int[] cur = es[i];
            int loc = cur[0], idx = cur[1];
            while (j < n && ss[j][0] < loc) j++;
            ans[idx] = j == n ? -1 : ss[j][1];
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了36.95% 的Java用户
     * 	内存消耗:50.3 MB,击败了23.15% 的Java用户
     * @param intervals
     * @return
     */
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        int[][] endIntervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
            endIntervals[i][0] = intervals[i][1];
            endIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(endIntervals, (o1, o2) -> o1[0] - o2[0]);

        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            while (j < n && endIntervals[i][0] > startIntervals[j][0]) {
                j++;
            }
            if (j < n) {
                ans[endIntervals[i][1]] = startIntervals[j][1];
            } else {
                ans[endIntervals[i][1]] = -1;
            }
        }
        return ans;
    }

}
