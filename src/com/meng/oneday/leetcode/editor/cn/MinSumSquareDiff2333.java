package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class MinSumSquareDiff2333 {
    /**
     * 超时
     * @param nums1
     * @param nums2
     * @param k1
     * @param k2
     * @return
     */
    public long minSumSquareDiffTimeOut(int[] nums1, int[] nums2, int k1, int k2) {
        PriorityQueue<Integer> list = new PriorityQueue<>((o1, o2) -> o2-o1);
        int n = nums1.length;
        for (int i = 0; i < n ; i++) {
            list.add(Math.abs(nums1[i]-nums2[i]));
        }
        int k = k1+k2;
        while (k>0 && !list.isEmpty()){
            int cur = list.poll();
            if (list.isEmpty()){
                cur = Math.max(cur-k,0);
                list.add(cur);
                break;
            }else{
                int next = list.peek();
                int step = Math.min(k,cur - next + 1);
                if (cur-step>0){
                    list.add(cur-step);
                }
                k -= step;
            }
        }
        long res = 0;
        while (!list.isEmpty()){
            Integer poll = list.poll();
            res += (long)poll*poll;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:63 ms,击败了9.09% 的Java用户
     * 	内存消耗:126.2 MB,击败了9.09% 的Java用户
     * @param nums1
     * @param nums2
     * @param k1
     * @param k2
     * @return
     */
    public long minSumSquareDiff2333(int[] nums1, int[] nums2, int k1, int k2) {
        Map<Integer,Integer> cntMap = new HashMap<>();
        PriorityQueue<int[]> list = new PriorityQueue<>((o1, o2) -> o2[0]-o1[0]);
        int n = nums1.length;
        for (int i = 0; i < n ; i++) {
            int diff = Math.abs(nums1[i]-nums2[i]);
            cntMap.put(diff,cntMap.getOrDefault(diff,0)+1);
        }
        for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
            list.add(new int[]{entry.getKey(),entry.getValue()});
        }
        int k = k1+k2;
        while (k>0 && !list.isEmpty()){
            int[] cur = list.poll();
            int num = cur[0];
            int cnt = cur[1];
            if (list.isEmpty()){
                if(k < (long)num * cnt){
                    int common =  k / cnt ;
                    int remain = k % cnt;
                    list.add(new int[]{num-common -1,remain});
                    list.add(new int[]{num - common,cnt-remain});
                }
                break;
            }else{
                int[] peek = list.peek();
                int diff = num - peek[0];
                if((long)diff * cnt <= k){
                    k -= diff * cnt;
                    peek[1] += cnt;
                }else {
                    int common =  k / cnt ;
                    int remain = k % cnt;
                    list.add(new int[]{num-common -1,remain});
                    list.add(new int[]{num - common,cnt-remain});
                    break;
                }
            }
        }
        long res = 0;
        while (!list.isEmpty()){
            int[] poll = list.poll();
            res += (long)poll[0]*poll[0]*poll[1];
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了45.45% 的Java用户
     * 	内存消耗:110.9 MB,击败了77.27% 的Java用户
     * @param a
     * @param nums2
     * @param k1
     * @param k2
     * @return
     */
    public long minSumSquareDiff(int[] a, int[] nums2, int k1, int k2) {
        int n = a.length;
        int k = k1 + k2;
        long ans = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Math.abs(a[i] - nums2[i]);
            sum += a[i];
            ans += (long) a[i] * a[i];
        }
        if (sum <= k) {
            return 0; // 所有 a[i] 均可为 0
        }
        Arrays.sort(a);
        for (int i = n - 1; ; i--) {
            int m = n - i;
            long v = a[i];
            long c = m * (v - (i > 0 ? a[i - 1] : 0));
            ans -= v * v; // 撤销上面的 ans += a[i] * a[i]
            if (c < k) {
                k -= c;
                continue;
            }
            v -= k / m;
            return ans + k % m * (v - 1) * (v - 1) + (m - k % m) * v * v;
        }
    }
}
