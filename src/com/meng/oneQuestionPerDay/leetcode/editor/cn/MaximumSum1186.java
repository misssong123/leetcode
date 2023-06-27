package com.meng.oneQuestionPerDay.leetcode.editor.cn;


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

    public static void main(String[] args) {
        int[] arr = new int[]{2,1,-2,-5,-2};
        MaximumSum1186 demo = new MaximumSum1186();
        System.out.println(demo.maximumSum(arr));
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

}

