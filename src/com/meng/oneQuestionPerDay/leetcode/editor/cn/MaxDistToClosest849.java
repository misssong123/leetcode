package com.meng.oneQuestionPerDay.leetcode.editor.cn;



class MaxDistToClosest849 {
    /**
     * 时间
     * 详情
     * 2ms
     * 击败 91.01%使用 Java 的用户
     * 内存
     * 详情
     * 42.61MB
     * 击败 58.43%使用 Java 的用户
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {
        int ans = 0;
        int pre = -1;
        for(int i = 0 ; i < seats.length ; i++){
            if (seats[i] == 1){
                if (pre == -1){
                    ans = i;
                }else {
                    ans = Math.max(ans,(i-pre)/2);
                }
                pre = i;
            }
        }
        //避免最后一个位置是0
        ans = Math.max(ans,(seats.length-1-pre));
        return ans;
    }

    public static void main(String[] args) {
        MaxDistToClosest849 demo = new MaxDistToClosest849();
        System.out.println(demo.maxDistToClosest(new int[]{0,0,1}));
    }

    /**
     *时间
     * 详情
     * 2ms
     * 击败 91.01%使用 Java 的用户
     * 内存
     * 详情
     * 42.55MB
     * 击败 66.85%使用 Java 的用户
     * @param seats
     * @return
     */
    public int maxDistToClosest1(int[] seats) {
        int res = 0;
        int l = 0;
        while (l < seats.length && seats[l] == 0) {
            ++l;
        }
        res = Math.max(res, l);
        while (l < seats.length) {
            int r = l + 1;
            while (r < seats.length && seats[r] == 0) {
                ++r;
            }
            if (r == seats.length) {
                res = Math.max(res, r - l - 1);
            } else {
                res = Math.max(res, (r - l) / 2);
            }
            l = r;
        }
        return res;
    }
}
