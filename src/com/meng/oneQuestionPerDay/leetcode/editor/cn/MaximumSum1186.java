package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.Arrays;

class MaximumSum1186 {
    /**
     * 1.判断当前集合最大值，若小于等于0，直接返回最大值
     * 2.判断当前集合最小值，若大于等于0，直接返回sum
     * 3.依次将原数组中的负数替换为0，计算最大值
     * @param arr
     * @return
     * 超时
     */
    public int maximumSum(int[] arr) {
        int arrMax = Integer.MIN_VALUE,arrMin = Integer.MAX_VALUE, sum = 0;
        for(int num : arr){
            //计算数组最大值
            arrMax = Math.max(arrMax, num);
            //计算数组最小值
            arrMin = Math.min(arrMin, num);
            //计算数组和
            sum += num;
        }
        if (arrMax <= 0)
            return arrMax;
        if (arrMin >= 0)
            return sum;
        //将原数组中的负数替换为0，计算最大值
        int res = Integer.MIN_VALUE;
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] < 0){
                int temp = arr[i];
                arr[i] = 0;
                res = Math.max(res, getMaxSubArr(arr));
                arr[i] = temp;
            }
        }
        return res;
    }

    private int getMaxSubArr(int[] arr) {
        int sum = arr[0],res = arr[0];
        for(int i = 1 ; i < arr.length ; i++){
            sum = Math.max(arr[i], sum + arr[i]);
            res = Math.max(sum, res);
        }
        return res;
    }


    /**
     * 动态规划
     * @param arr
     * @return
     *执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 88.89%
     * 的用户
     * 内存消耗：
     * 50.7 MB
     * , 在所有 Java 提交中击败了
     * 52.78%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     */
    public int maximumSum1(int[] arr) {
        int dp0 = arr[0], dp1 = 0, res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp1 = Math.max(dp0, dp1 + arr[i]);
            dp0 = Math.max(dp0, 0) + arr[i];
            res = Math.max(res, Math.max(dp0, dp1));
        }
        return res;
    }
    //----------------------推理过程-------------------------------------
    /**
     * 递归
     * 以右节点i为例
     * 分为两种情况
     * 1.不删除数据
     * a.仅选取右侧数据arr[i]
     * b.选取右侧数据+左侧f(0,i-1)最大值
     * 2.删除数据
     * a.删除右侧数据，选取左侧数据f(0,i-1)
     * b.删除左侧数据,f(1,i-1)+arr[i]
     * 超时
     */
    public int maxSubArr(int[] arr) {
        int res = Integer.MIN_VALUE;
        for(int i = 0 ; i < arr.length ; i++){
            int max = Math.max(dfs(arr, i, 0), dfs(arr, i, 1));
            res = Math.max(res,max);
        }
        return res;
    }

    private int dfs(int[] arr, int index, int num) {
        if (index < 0 ){
            return Integer.MIN_VALUE / 2;
        }
        if (num == 0){
            return arr[index] + Math.max(0,dfs(arr,index-1,0));
        }
        return Math.max(dfs(arr,index-1,0),dfs(arr,index-1,1)+arr[index]);
    }

    /**
     * 递归优化
     * @param arr
     * @return
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 29.72%
     * 的用户
     * 内存消耗：
     * 50.3 MB
     * , 在所有 Java 提交中击败了
     * 69.88%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     */
    int [][] cache;
    public int maxSubArr2(int[] arr) {
        int len =  arr.length;
        cache = new int[len][2];
        int res = Integer.MIN_VALUE;
        for(int i = 0 ; i <  len ; i++){
            Arrays.fill(cache[i],Integer.MIN_VALUE);
        }
        for(int i = 0 ; i < arr.length ; i++){
            int max = Math.max(dfs2(arr, i, 0), dfs2(arr, i, 1));
            res = Math.max(res,max);
        }
        return res;
    }
    private int dfs2(int[] arr, int index, int num) {
        if (index < 0 ){
            return Integer.MIN_VALUE / 2;
        }
        if (cache[index][num] != Integer.MIN_VALUE){
            return cache[index][num];
        }
        if (num == 0){
            cache[index][num] = arr[index] + Math.max(0,dfs2(arr,index-1,0));
            return cache[index][num];
        }
        cache[index][num] = Math.max(dfs2(arr,index-1,0),dfs2(arr,index-1,1)+arr[index]);
        return cache[index][num];
    }

    /**
     * 将上述递归转换为动态规划
     * 因为依赖于前一个值，需要增加数组的长度
     * @param arr
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 42.85%
     * 的用户
     * 内存消耗：
     * 50.3 MB
     * , 在所有 Java 提交中击败了
     * 69.69%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     */
    public int maxSubArr3(int[] arr) {
        int len = arr.length;
        int[][] cache = new int[len + 1][2];
        int res = Integer.MIN_VALUE/2;
        for(int i = 0 ; i <  cache.length ; i++){
            Arrays.fill(cache[i],Integer.MIN_VALUE/2);//避免负数越界
        }
        for(int i = 0 ; i < len ; i++){
            cache[i+1][0] = arr[i] + Math.max(0,cache[i][0]);
            cache[i+1][1] = Math.max(cache[i][0],cache[i][1]+arr[i]);
            res = Math.max(res,Math.max(cache[i+1][0],cache[i+1][1]));
        }
        return res;
    }

    /**
     * 优化空间复杂度
     * @param arr
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 90.18%
     * 的用户
     * 内存消耗：
     * 50.5 MB
     * , 在所有 Java 提交中击败了
     * 49.32%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     */
    public int maxSubArr4(int[] arr) {
        int res = arr[0],p0 = res,p1=0;
        for (int i = 1 ; i < arr.length ; i++){
            p1 = Math.max(p0,p1+arr[i]);
            p0 = arr[i] + Math.max(0,p0);
            res = Math.max(res,Math.max(p0,p1));
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,-2,0,3};
        MaximumSum1186 demo = new MaximumSum1186();
        System.out.println(demo.maximumSum1(arr));
        System.out.println(demo.maxSubArr4(arr));
    }
}

