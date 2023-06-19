package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MaxSumDivThree1262 {
    /**
     * 1.将数组切割成三部分
     * a.余数为0
     * b.余数为1
     * c.余数为2
     * @param nums
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 83.38%
     * 的用户
     * 内存消耗：
     * 43.4 MB
     * , 在所有 Java 提交中击败了
     * 94.91%
     * 的用户
     * 通过测试用例：
     * 42 / 42
     */
    public int maxSumDivThree(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 3 == 0){
            return sum;
        }else if (sum % 3 ==1){//移除两个余数为2的最小值或者移除一个余数为1的最小值
            int a = Integer.MAX_VALUE,b = Integer.MAX_VALUE,c = Integer.MAX_VALUE;
            for(int num : nums){
                if(num % 3 == 1){
                    a = Math.min(a,num);
                }else if (num % 3 == 2){
                    if (num < c){
                        b= c;
                        c =num;
                    }else if (num < b){
                        b = num;
                    }
                }
            }
            int sum1 = sum;
            int sum2 = sum;
            if (a != Integer.MAX_VALUE ){
                sum1 = sum - a;
            }
            if (b != Integer.MAX_VALUE ){
                sum2 = sum - (b+c);
            }
            if (sum1 != sum || sum2 != sum){
                return (sum1 != sum && sum2 !=sum) ? Math.max(sum1,sum2) : Math.min(sum1,sum2);
            }
        }else{//移除两个余数为1的最小值或者移除一个余数为2的最小值
            int a = Integer.MAX_VALUE,b = Integer.MAX_VALUE,c = Integer.MAX_VALUE;
            for(int num : nums){
                if(num % 3 == 2){
                    a = Math.min(a,num);
                }else if (num % 3 == 1){
                    if (num < c){
                        b= c;
                        c =num;
                    }else if (num < b){
                        b = num;
                    }
                }
            }
            int sum1 = sum;
            int sum2 = sum;
            if (a != Integer.MAX_VALUE ){
                sum1 = sum - a;
            }
            if (b != Integer.MAX_VALUE ){
                sum2 = sum - (b+c);
            }
            if (sum1 != sum || sum2 != sum){
                return sum1 != sum && sum2 !=sum ? Math.max(sum1,sum2) : Math.min(sum1,sum2);
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {4};
        MaxSumDivThree1262 demo = new MaxSumDivThree1262();
        System.out.println(demo.maxSumDivThree2(nums));
    }

    /**
     * 方法一：贪心 + 正向思维
     * @param nums
     * @return
     */
    public int maxSumDivThree1(int[] nums) {
        // 使用 v[0], v[1], v[2] 分别表示 a, b, c
        List<Integer>[] v = new List[3];
        for (int i = 0; i < 3; ++i) {
            v[i] = new ArrayList<Integer>();
        }
        for (int num : nums) {
            v[num % 3].add(num);
        }
        Collections.sort(v[1], (a, b) -> b - a);
        Collections.sort(v[2], (a, b) -> b - a);

        int ans = 0;
        int lb = v[1].size(), lc = v[2].size();
        for (int cntb = lb - 2; cntb <= lb; ++cntb) {
            if (cntb >= 0) {
                for (int cntc = lc - 2; cntc <= lc; ++cntc) {
                    if (cntc >= 0 && (cntb - cntc) % 3 == 0) {
                        ans = Math.max(ans, getSum(v[1], 0, cntb) + getSum(v[2], 0, cntc));
                    }
                }
            }
        }
        return ans + getSum(v[0], 0, v[0].size());
    }

    public int getSum(List<Integer> list, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; ++i) {
            sum += list.get(i);
        }
        return sum;
    }

    /**
     * 方法二：贪心 + 逆向思维
     * @param nums
     * @return
     */
    public int maxSumDivThree2(int[] nums) {
        // 使用 v[0], v[1], v[2] 分别表示 a, b, c
        List<Integer>[] v = new List[3];
        for (int i = 0; i < 3; ++i) {
            v[i] = new ArrayList<Integer>();
        }
        for (int num : nums) {
            v[num % 3].add(num);
        }
        Collections.sort(v[1], (a, b) -> b - a);
        Collections.sort(v[2], (a, b) -> b - a);

        int tot = Arrays.stream(nums).sum();
        int remove = Integer.MAX_VALUE;

        if (tot % 3 == 0) {
            remove = 0;
        } else if (tot % 3 == 1) {
            if (v[1].size() >= 1) {
                remove = Math.min(remove, v[1].get(v[1].size() - 1));
            }
            if (v[2].size() >= 2) {
                remove = Math.min(remove, v[2].get(v[2].size() - 2) + v[2].get(v[2].size() - 1));
            }
        } else {
            if (v[1].size() >= 2) {
                remove = Math.min(remove, v[1].get(v[1].size() - 2) + v[1].get(v[1].size() - 1));
            }
            if (v[2].size() >= 1) {
                remove = Math.min(remove, v[2].get(v[2].size() - 1));
            }
        }

        return tot - remove;
    }

    /**
     * 方法三：动态规划
     * @param nums
     * @return
     */
    public int maxSumDivThree3(int[] nums) {
        int[] f = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int num : nums) {
            int[] g = new int[3];
            System.arraycopy(f, 0, g, 0, 3);
            for (int i = 0; i < 3; ++i) {
                g[(i + num % 3) % 3] = Math.max(g[(i + num % 3) % 3], f[i] + num);
            }
            f = g;
        }
        return f[0];
    }

}

