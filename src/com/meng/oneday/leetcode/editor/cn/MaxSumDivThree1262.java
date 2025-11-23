package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MaxSumDivThree1262 {
    /**
     * 思路有误
     * @param nums
     * @return
     */
    public int maxSumDivThreeError(int[] nums) {
        int sum = 0;
        PriorityQueue<int[]> qp = new PriorityQueue<>((a,b)->(b[0]-a[0]));
        for (int num : nums){
            if (num % 3 == 0){
                sum += num;
            }else if (num % 3 == 1){
                qp.add(new int[]{num,1});
            }else{
                qp.add(new int[]{num,2});
            }
        }
        while (qp.size() > 1){
            //第一个元素
            int[] first = qp.poll();
            //第二个元素
            int[] second = qp.poll();
            if (first[1] != second[1]){
                sum += first[0] + second[0];
            }else if(!qp.isEmpty()){
                int[] third = qp.poll();
                if (first[1] != third[1]){
                    sum += first[0] + third[0];
                    qp.add(second);
                }else{
                    sum += first[0] + second[0] + third[0];
                }
            }else {
                break;
            }
        }
        return sum;
    }
    int max;

    /**
     * 超时
     * @param nums
     * @return
     */
    public int maxSumDivThreeTimeOut(int[] nums) {
        int sum = 0;
        List<Integer> oneList = new ArrayList<>();
        List<Integer> twoList = new ArrayList<>();
        for (int num : nums){
            if (num % 3 == 0){
                sum += num;
            }else if (num % 3 == 1){
                oneList.add(num);
            }else{
                twoList.add(num);
            }
        }
        max = 0;
        oneList.sort((o1, o2) -> o2 - o1);
        twoList.sort((o1, o2) -> o2 - o1);
        dfs(oneList,twoList,0,0,0);
        return sum+max;
    }

    private void dfs(List<Integer> oneList, List<Integer> twoList, int oneIndex, int twoIndex,int sum) {
        if (oneIndex == oneList.size() && twoIndex == twoList.size()){
            max = Math.max(max,sum);
            return;
        }
        //选择oneList中一个元素+ twoList中一个元素
        if (oneIndex < oneList.size() && twoIndex < twoList.size()){
            dfs(oneList,twoList,oneIndex+1,twoIndex+1,sum+oneList.get(oneIndex)+twoList.get(twoIndex));
        }
        //选择oneList中的三个元素
        if (oneIndex + 2 < oneList.size()){
            dfs(oneList,twoList,oneIndex+3,twoIndex,sum+oneList.get(oneIndex)+oneList.get(oneIndex+1)+oneList.get(oneIndex+2));
        }
        //选择twoList中的三个元素
        if (twoIndex + 2 < twoList.size()){
            dfs(oneList,twoList,oneIndex,twoIndex+3,sum+twoList.get(twoIndex)+twoList.get(twoIndex+1)+twoList.get(twoIndex+2));
        }
        max = Math.max(max,sum);
    }
    private static final int MAX = 10001;

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了54.55% 的Java用户
     * 	内存消耗:48.6 MB,击败了30.58% 的Java用户
     * @param nums
     * @return
     */
    public int maxSumDivThree1262(int[] nums) {
        int sum = 0;
        for (int num : nums){
           sum += num;
        }
        if (sum % 3 == 0){
            return sum;
        }
        Arrays.sort(nums);
        if (sum % 3 == 1){
            //删除一个余数为1或者两个余数为2的元素
            int oneFirstMin = MAX;
            int twoFirstMin = MAX, twoSecondMin = MAX;
            for(int num : nums){
                if (num % 3 == 1){
                    if (oneFirstMin == MAX){
                        oneFirstMin = num;
                    }
                }else if (num % 3 == 2){
                    if (twoFirstMin == MAX){
                        twoFirstMin = num;
                    }else if (twoSecondMin == MAX){
                        twoSecondMin = num;
                    }
                }
            }
            return Math.max(sum - oneFirstMin,sum - twoFirstMin - twoSecondMin);
        }else {
            //删除一个余数为2或者两个余数为1的元素
            int oneFirstMin = MAX, oneSecondMin = MAX;
            int twoFirstMin = MAX;
            for(int num : nums){
                if (num % 3 == 1){
                    if (oneFirstMin == MAX){
                        oneFirstMin = num;
                    }else if (oneSecondMin == MAX){
                        oneSecondMin = num;
                    }
                }else if (num % 3 == 2){
                    if (twoFirstMin == MAX){
                        twoFirstMin = num;
                    }
                }
            }
            return Math.max(sum - twoFirstMin,sum - oneFirstMin - oneSecondMin);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了25.34% 的Java用户
     * 	内存消耗:49.6 MB,击败了9.09% 的Java用户
     * @param nums
     * @return
     */
    public int maxSumDivThreeOther(int[] nums) {
        int s = 0;
        for (int x : nums)
            s += x;
        if (s % 3 == 0)
            return s;

        List<Integer> a1 = new ArrayList<Integer>();
        List<Integer> a2 = new ArrayList<Integer>();
        for (int x : nums) {
            if (x % 3 == 1) a1.add(x);
            else if (x % 3 == 2) a2.add(x);
        }
        Collections.sort(a1);
        Collections.sort(a2);

        if (s % 3 == 2) { // swap(a1,a2)
            List<Integer> tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        int ans = a1.isEmpty() ? 0 : s - a1.get(0);
        if (a2.size() > 1)
            ans = Math.max(ans, s - a2.get(0) - a2.get(1));
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了90.63% 的Java用户
     * 	内存消耗:48.5 MB,击败了31.13% 的Java用户
     * @param nums
     * @return
     */
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] f = new int[2][3];
        f[0][1] = f[0][2] = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                f[(i + 1) % 2][j] = Math.max(f[i % 2][j], f[i % 2][(j + nums[i]) % 3] + nums[i]);
        return f[n % 2][0];
    }
}
