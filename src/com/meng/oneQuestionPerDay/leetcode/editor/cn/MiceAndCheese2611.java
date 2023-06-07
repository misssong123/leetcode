package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

class MiceAndCheese2611 {
    /**
     * 1.计算两个的数组的差值
     * 2.老鼠1选取差值最大K个的数据
     * 3.然后与reward2的总和相加
     * @param reward1
     * @param reward2
     * @param k
     * @return
     * 执行用时：
     * 11 ms
     * , 在所有 Java 提交中击败了
     * 99.76%
     * 的用户
     * 内存消耗：
     * 54.4 MB
     * , 在所有 Java 提交中击败了
     * 26.36%
     * 的用户
     * 通过测试用例：
     * 564 / 564
     */
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int [] diff = new int[n];
        for(int i = 0 ;i < n ; i++){
            diff[i] = reward1[i] - reward2[i];
        }
        Arrays.sort(diff);
        int res = 0;
        for(int i = n - 1 ; i >= n - k ; i-- ){
            res += diff[i];
        }
        for(int i = 0 ;i < n ; i++){
            res += reward2[i];
        }
        return res;
    }

    /**
     * 执行用时：
     * 11 ms
     * , 在所有 Java 提交中击败了
     * 99.76%
     * 的用户
     * 内存消耗：
     * 54.6 MB
     * , 在所有 Java 提交中击败了
     * 21.44%
     * 的用户
     * 通过测试用例：
     * 564 / 564
     * @param reward1
     * @param reward2
     * @param k
     * @return
     */
    public int miceAndCheese1(int[] reward1, int[] reward2, int k) {
        int ans = 0;
        int n = reward1.length;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            ans += reward2[i];
            diffs[i] = reward1[i] - reward2[i];
        }
        Arrays.sort(diffs);
        for (int i = 1; i <= k; i++) {
            ans += diffs[n - i];
        }
        return ans;
    }

    /**
     * 执行用时：
     * 59 ms
     * , 在所有 Java 提交中击败了
     * 21.10%
     * 的用户
     * 内存消耗：
     * 55 MB
     * , 在所有 Java 提交中击败了
     * 15.16%
     * 的用户
     * 通过测试用例：
     * 564 / 564
     * @param reward1
     * @param reward2
     * @param k
     * @return
     */
    public int miceAndCheese2(int[] reward1, int[] reward2, int k) {
        int ans = 0;
        int n = reward1.length;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            ans += reward2[i];
            pq.offer(reward1[i] - reward2[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }
    int res = 0;

    /**
     * 递归
     * 超出时间限制
     * @param reward1
     * @param reward2
     * @param k
     * @return
     */
    public int miceAndCheese3(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        dfs(0,0,reward1,reward2,k,n);
        return res ;
    }

    private void dfs(int index, int sum, int[] reward1, int[] reward2, int k, int n) {
        if (index >= n){
            res = Math.max(sum,res);
            return;
        }
        //如果当前 n - index 等于 k，只能选择reward1
        if (n - index == k){
            dfs(index+1,sum+reward1[index],reward1,reward2,k-1,n);
        }else {
            //不选择reward1
            dfs(index+1,sum+reward2[index],reward1,reward2,k,n);
            //选择reward1
            if (k>0){
                dfs(index+1,sum + reward1[index],reward1,reward2,k-1,n);
            }
        }
    }

    public static void main(String[] args) {
        int[]  reward1 =  {1,1,3,4}, reward2 ={4,4,1,1};
        int k = 2;
        MiceAndCheese2611 demo = new MiceAndCheese2611();
        System.out.println(demo.miceAndCheese3(reward1,reward2,k));
    }
}
