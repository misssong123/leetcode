package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

class Interview133KSmallestPairs {
    /**
     * 解答成功:
     * 	执行耗时:53 ms,击败了11.76% 的Java用户
     * 	内存消耗:56.8 MB,击败了68.75% 的Java用户
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairsMy(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length,len2 = nums2.length;
        Set<Integer> cache = new HashSet<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->{
            if (a[0] != b[0]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        cache.add(0);
        queue.add(new int[]{nums1[0] + nums2[0],0});
        List<List<Integer>> res = new ArrayList<>();
        while (k > 0 && !queue.isEmpty()){
            //取出当前最小的元素
            int[] cur = queue.poll();
            int x = cur[1] / len2;
            int y = cur[1] % len2;
            //将当前元素加入结果集
            res.add(Arrays.asList(nums1[x],nums2[y]));
            //计算下次可能的值
            //x+1
            int num1 = (x+1)*len2 +y;
            if (x +1 < len1 && ! cache.contains(num1)){
                queue.add(new int[]{nums1[x+1]+nums2[y],num1});
                cache.add(num1);
            }
            //y+1
            int num2 = cur[1] +1;
            if (y +1 < len2 && ! cache.contains(num2)){
                queue.add(new int[]{nums1[x]+nums2[y+1],num2});
                cache.add(num2);
            }
            k--;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了58.14% 的Java用户
     * 	内存消耗:56.6 MB,击败了81.31% 的Java用户
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2)->{
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i,0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了98.42% 的Java用户
     * 	内存消耗:61.5 MB,击败了10.13% 的Java用户
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        /*二分查找第 k 小的数对和的大小*/
        int left = nums1[0] + nums2[0];
        int right = nums1[m - 1] + nums2[n - 1];
        int pairSum = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long cnt = 0;
            int start = 0;
            int end = n - 1;
            while (start < m && end >= 0) {
                if (nums1[start] + nums2[end] > mid) {
                    end--;
                } else {
                    cnt += end + 1;
                    start++;
                }
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                pairSum = mid;
                right = mid - 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int pos = n - 1;
        /*找到小于目标值 pairSum 的数对*/
        for (int i = 0; i < m; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] >= pairSum) {
                pos--;
            }
            for (int j = 0; j <= pos && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                ans.add(list);
            }
        }

        /*找到等于目标值 pairSum 的数对*/
        pos = n - 1;
        for (int i = 0; i < m && k > 0; i++) {
            int start1 = i;
            while (i < m - 1 && nums1[i] == nums1[i + 1]) {
                i++;
            }
            while (pos >= 0 && nums1[i] + nums2[pos] > pairSum) {
                pos--;
            }
            int start2 = pos;
            while (pos > 0 && nums2[pos] == nums2[pos - 1]) {
                pos--;
            }
            if (nums1[i] + nums2[pos] != pairSum) {
                continue;
            }
            int count = (int) Math.min(k, (long) (i - start1 + 1) * (start2 - pos + 1));
            for (int j = 0; j < count && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[pos]);
                ans.add(list);
            }
        }
        return ans;
    }

}
