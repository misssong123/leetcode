package com.meng.top100.leetcode.editor.cn;

import java.util.*;

class KSmallestPairsLCR061 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了28.57% 的Java用户
     * 	内存消耗:46.2 MB,击败了19.05% 的Java用户
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairsLCR061(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        List<List<Integer>> res = new ArrayList<>(k);
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        //初始化
        for(int i = 0 ; i < Math.min(k,len1) ; i++){
            queue.add(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        while(k-- > 0 && !queue.isEmpty()){
            int[] cur = queue.poll();
            res.add(Arrays.asList(nums1[cur[0]], nums2[cur[1]]));
            if (cur[1] + 1 < len2){
                queue.add(new int[]{cur[0], cur[1] + 1, nums1[cur[0]] + nums2[cur[1] + 1] });
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了48.57% 的Java用户
     * 	内存消耗:46.1 MB,击败了20.00% 的Java用户
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{nums1[0] + nums2[0], 0, 0});

        List<List<Integer>> ans = new ArrayList<>(k); // 预分配空间
        while (k-- > 0 && !pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[1];
            int j = top[2];
            ans.add(Arrays.asList(nums1[i], nums2[j]));
            if (j == 0 && i + 1 < nums1.length) {
                pq.add(new int[]{nums1[i + 1] + nums2[0], i + 1, 0});
            }
            if (j + 1 < nums2.length) {
                pq.add(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }
        return ans;
    }
}
