package com.meng.top100.leetcode.editor.cn;

class MaxPower2528 {
    /**
     * 1.计算初始发电量
     * 2.二分计算最大发电量
     * 解答成功:
     * 	执行耗时:26 ms,击败了100.00% 的Java用户
     * 	内存消耗:77.1 MB,击败了5.09% 的Java用户
     * @param stations
     * @param r
     * @param k
     * @return
     */
    public long maxPower2528(int[] stations, int r, int k) {
        int n = stations.length;
        long[] suf = new long[n];
        //后缀和
        for(int i = n - 2; i >= 0 ; i-- ){
            suf[i] = suf[i + 1] + stations[i + 1];
            if (i + r + 1 < n){
                suf[i] -= stations[i + r + 1];
            }
        }
        //初始发电量
        long[] origin = new long[n];
        long sum = 0;
        long start = Long.MAX_VALUE;
        for(int i = 0 ; i < n ; i++){
            origin[i] = stations[i] + suf[i] + sum;
            start = Math.min(start,origin[i]);
            sum += stations[i];
            if (i >= r){
                sum -= stations[i - r];
            }
        }
        //二分计算
        long end = start + k;
        long res = start;
        while (start <= end){
            long middle = (start + end) >> 1;
            if (judge(origin,k,r,middle)){
                res = middle;
                start = middle + 1;
            }else {
                end = middle - 1;
            }
        }
        return res;
    }

    private boolean judge(long[] origin, int k,int r, long target) {
        int len = origin.length;
        long[] diffArr = new long[len];
        long diff = 0;
        for(int i = 0 ; i < len ; i++){
            diff += diffArr[i];
            if (origin[i] + diff < target){
                long d = target - origin[i] - diff;
                if(k >= d){
                    k -= (int) d;
                    diff += d;
                    if (i + r * 2 + 1  < len){
                        diffArr[i + r * 2 + 1] -= d;
                    }
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了86.44% 的Java用户
     * 	内存消耗:79.4 MB,击败了5.09% 的Java用户
     * @param stations
     * @param r
     * @param k
     * @return
     */
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        // 滑动窗口
        // 先计算 [0, r-1] 的发电量，为第一个窗口做准备
        long sum = 0;
        for (int i = 0; i < r; i++) {
            sum += stations[i];
        }
        long[] power = new long[n];
        long mn = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 右边进
            if (i + r < n) {
                sum += stations[i + r];
            }
            // 左边出
            if (i - r - 1 >= 0) {
                sum -= stations[i - r - 1];
            }
            power[i] = sum;
            mn = Math.min(mn, sum);
        }

        // 开区间二分
        long left = mn + k / n;
        long right = mn + k + 1;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (check(mid, power, r, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(long low, long[] power, int r, int k) {
        int n = power.length;
        long[] diff = new long[n + 1];
        long sumD = 0;
        long built = 0;
        for (int i = 0; i < n; i++) {
            sumD += diff[i]; // 累加差分值
            long m = low - (power[i] + sumD);
            if (m <= 0) {
                continue;
            }
            // 需要在 i+r 额外建造 m 个供电站
            built += m;
            if (built > k) { // 不满足要求
                return false;
            }
            // 把区间 [i, i+2r] 加一
            sumD += m; // 由于 diff[i] 后面不会再访问，我们直接加到 sumD 中
            diff[Math.min(i + r * 2 + 1, n)] -= m;
        }
        return true;
    }

}
