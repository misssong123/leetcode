package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class MyPow50 {
    /**
     * 栈溢出
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (x == 1){
            return 1;
        }
        boolean flag = n < 0 ? true : false;
        if (flag){
            n = -n;
        }
        double res = dfs(x,n);
        return flag?1/res:res;
    }

    private double dfs(double x, int n) {
        if (n == 0){
            return 1;
        }
        if (n == 1){
            return x;
        }
        return x * dfs(x,n-1);
    }

    /**
     * 超时
     * @param x
     * @param n
     * @return
     */
    public double myPow1(double x, int n) {
        if (n == 0){
            return 1;
        }
        if (n == 1){
            return x;
        }
        if (x == 1){
            return 1;
        }
        boolean flag = n < 0 ? true : false;
        long N = n;
        if (flag){
            N = -N;
        }
        double res = 1.0;
        System.out.println(N);
        for(int i = 0 ; i < N ; i++){
            res *= x;
        }
        return flag?1.0/res:res;
    }
    public static void main(String[] args) {
        MyPow50 demo = new MyPow50();
        System.out.println(demo.myPow1(2,-2147483648));
        System.out.println(demo.myPow2(2,-2147483648));
        System.out.println(demo.myPow3(2,-2147483648));
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 48.99%
     * 的用户
     * 通过测试用例：
     * 305 / 305
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }


    public double myPow3(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul3(x, N) : 1.0 / quickMul3(x, -N);
    }

    public double quickMul3(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

}
