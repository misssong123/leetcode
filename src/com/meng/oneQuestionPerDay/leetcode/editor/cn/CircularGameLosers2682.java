package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class CircularGameLosers2682 {
    /**
     * 时间
     * 详情
     * 2ms
     * 击败 69.08%使用 Java 的用户
     * 内存
     * 详情
     * 41.56mb
     * 击败 79.76%使用 Java 的用户
     * @param n
     * @param k
     * @return
     */
    public int[] circularGameLosers(int n, int k) {
        Set<Integer> cache = new HashSet<>();
        cache.add(0);
        int num = 1,pre = 0;
        while (true){
            int next = (num * k + pre) %n;
            System.out.println(next);
            if (!cache.add(next)) {
               break;
            }
            pre = next;
            num++;
        }
        if (cache.size() == n){
            return new int[]{};
        }
        int[] res = new int[n - cache.size()];
        int index = 0;
        for(int i = 0 ; i < n ; i++){
            if (!cache.contains(i)){
                res[index++] = i+1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CircularGameLosers2682 demo = new CircularGameLosers2682();
        System.out.println(Arrays.toString(demo.circularGameLosers(5, 2)));
    }

    /**
     * 时间
     * 详情
     * 2ms
     * 击败 69.08%使用 Java 的用户
     * 内存
     * 详情
     * 41.57mb
     * 击败 78.48%使用 Java 的用户
     * @param n
     * @param k
     * @return
     */
    public int[] circularGameLosers1(int n, int k) {
        boolean[] visit = new boolean[n];
        for (int i = k, j = 0; !visit[j]; i += k) {
            visit[j] = true;
            j = (j + i) % n;
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                list.add(i + 1);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }


}

