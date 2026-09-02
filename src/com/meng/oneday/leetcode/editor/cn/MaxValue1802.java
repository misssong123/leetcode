package com.meng.oneday.leetcode.editor.cn;

class MaxValue1802 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了88.24% 的Java用户
     * 	内存消耗:42 MB,击败了11.76% 的Java用户
     * @param n
     * @param index
     * @param maxSum
     * @return
     */
    public int maxValue1802(int n, int index, int maxSum) {
        if (n == 1){
            return maxSum;
        }
        int l = 1 ,r = maxSum-1;
        int res = l;
        while (l <= r){
            int mid = (l+r)/2;
            if (check(mid,index,n,maxSum)){
                res = mid;
                l = mid+1;
            }else {
                r = mid-1;
            }
        }
        return res;
    }

    private boolean check(int mid, int index, int n, int maxSum) {
        long total = mid;
        //左侧大小
        if (index <= mid - 1){
            total += ((long)(mid -1) + (mid -index)) * index /2;
        }else{
            total +=(long) mid * (mid -1) /2;
            total += index - (mid -1);
        }
       //右侧大小
        int cnt = n - index - 1;
        if(cnt <= mid - 1){
            total += ((long)(mid -1) + (mid -cnt)) * cnt /2;
        }else {
            total += (long)mid * (mid -1) /2;
            total += (cnt - mid +1);
        }
       return total <= maxSum;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了88.24% 的Java用户
     * 	内存消耗:41.7 MB,击败了55.88% 的Java用户
     * @param n
     * @param index
     * @param max
     * @return
     */
    public int maxValue(int n, int index, int max) {
        long l = 1, r = max;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (check(n, mid, index, max)) l = mid;
            else r = mid - 1;
        }
        return (int) r;
    }
    boolean check(int n, long x, int idx, int max) {
        long sum = x;
        if (idx > x - 1) {
            long an = x - 1, a1 = 1, cnt = x - 1;
            sum += cnt * (a1 + an) / 2;
            sum += idx - cnt;
        } else {
            long cnt = idx, an = x - 1, a1 = an - cnt + 1;
            sum += cnt * (a1 + an) / 2;
        }
        if (n - idx - 1 > x - 1) {
            long an = x - 1, a1 = 1, cnt = x - 1;
            sum += cnt * (a1 + an) / 2;
            sum += n - idx - 1 - cnt;
        } else {
            long cnt = n - idx - 1, an = x - 1, a1 = an - cnt + 1;
            sum += cnt * (a1 + an) / 2;
        }
        return sum <= max;
    }
}
