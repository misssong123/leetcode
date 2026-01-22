package com.meng.oneday.leetcode.editor.cn;

import java.util.TreeSet;

class MinimumPairRemoval3507 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了73.77% 的Java用户
     * 	内存消耗:43.6 MB,击败了52.46% 的Java用户
     * @param nums
     * @return
     */
    public int minimumPairRemoval3507(int[] nums) {
        boolean[] disableIndex = new boolean[nums.length];
        int cnt = 0 ;
        while (cnt < nums.length - 1){
            //判断是否为非递减数组
            if (check(nums,disableIndex)){
                break;
            }
            //获取最小和下标
            int[] index = getMin(nums,disableIndex);
            disableIndex[index[1]] = true;
            nums[index[0]] += nums[index[1]];
            //次数+1
            cnt++;
        }
        return cnt;
    }
    private int[] getMin(int[] nums, boolean[] disableIndex) {
        int[] res = new int[2];
        int index = 0;
        int pre = -1;
        for (int i = 0 ; i < nums.length ; i++){
            if (disableIndex[i]){
                continue;
            }
            if(index < 2){
                res[index++] = i;
            }else{
                int preVal = nums[res[0]] + nums[res[1]];
                int cnrVal = nums[i] + nums[pre];
                if (preVal > cnrVal){
                    res[0] = pre;
                    res[1] = i;
                }
            }
            pre = i;
        }
        return res;
    }
    private boolean check(int[] nums, boolean[] disableIndex) {
        int preIndex = -1;
        for (int i = 0 ; i < nums.length ; i++){
            if (disableIndex[i]){
                continue;
            }
            if (preIndex != -1 && nums[i] < nums[preIndex]) {
                //不符合要求
                return false;
            }
            preIndex = i;
        }
        return true;
    }
    private static class Pair {
        long s;
        int i;
        public Pair(long s, int i){
            this.s = s;
            this.i = i;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了8.20% 的Java用户
     * 	内存消耗:46.5 MB,击败了6.56% 的Java用户
     * @param nums
     * @return
     */
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        // (相邻元素和，左边那个数的下标)
        TreeSet<Pair> pairs = new TreeSet<>((a, b) -> a.s != b.s ? Long.compare(a.s, b.s) : a.i - b.i);
        int dec = 0; // 递减的相邻对的个数
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            int y = nums[i + 1];
            if (x > y) {
                dec++;
            }
            pairs.add(new Pair(x + y, i));
        }

        // 剩余下标
        TreeSet<Integer> idx = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            idx.add(i);
        }

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nums[i];
        }

        int ans = 0;
        while (dec > 0) {
            ans++;

            // 删除相邻元素和最小的一对
            Pair p = pairs.pollFirst();
            long s = p.s;
            int i = p.i;

            // (当前元素，下一个数)
            int nxt = idx.higher(i);
            if (a[i] > a[nxt]) { // 旧数据
                dec--;
            }

            // (前一个数，当前元素)
            Integer pre = idx.lower(i);
            if (pre != null) {
                if (a[pre] > a[i]) { // 旧数据
                    dec--;
                }
                if (a[pre] > s) { // 新数据
                    dec++;
                }
                pairs.remove(new Pair(a[pre] + a[i], pre));
                pairs.add(new Pair(a[pre] + s, pre));
            }

            // (下一个数，下下一个数)
            Integer nxt2 = idx.higher(nxt);
            if (nxt2 != null) {
                if (a[nxt] > a[nxt2]) { // 旧数据
                    dec--;
                }
                if (s > a[nxt2]) { // 新数据（当前元素，下下一个数）
                    dec++;
                }
                pairs.remove(new Pair(a[nxt] + a[nxt2], nxt));
                pairs.add(new Pair(s + a[nxt2], i));
            }

            a[i] = s; // 把 a[nxt] 加到 a[i] 中
            idx.remove(nxt); // 删除 nxt
        }
        return ans;
    }
}
