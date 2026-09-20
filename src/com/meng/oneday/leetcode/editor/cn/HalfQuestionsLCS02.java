package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class HalfQuestionsLCS02 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了82.86% 的Java用户
     * 	内存消耗:63.2 MB,击败了48.57% 的Java用户
     * @param questions
     * @return
     */
    public int halfQuestionsLCS02(int[] questions) {
        int[] arr = new int[1001];
        for (int question : questions) {
            arr[question]++;
        }
        List<Integer> list = new ArrayList<>();
        for (int j : arr) {
            if (j > 0) {
                list.add(j);
            }
        }
        list.sort((a,b)->b-a);
        int N = questions.length /2;
        int ans = 0;
        for (int num : list) {
            if (N <= 0){
                break;
            }
            ans++;
            if (num >= N){
                break;
            }
            N -= num;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了37.14% 的Java用户
     * 	内存消耗:63 MB,击败了77.14% 的Java用户
     * @param questions
     * @return
     */
    public int halfQuestions(int[] questions) {
        int[] cnt = new int[1001];
        for (int v : questions) {
            cnt[v]++;
        }

        Arrays.sort(cnt);

        int ans = 0;
        int n = questions.length / 2;

        for (int i = 1000; n > 0; i--) {
            ans++;
            n -= cnt[i];
        }

        return ans;
    }
}
