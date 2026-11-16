package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class DigitSum2243 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.22% 的Java用户
     * 	内存消耗:42.4 MB,击败了47.22% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String digitSum2243(String s, int k) {
        if (s.length() <= k){
            return s;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < s.length();i+=k){
            int sum = 0;
            for (int j = 0 ; j < k && j + i < s.length();j++){
                sum += s.charAt(i+j)-'0';
            }
            if (sum >= 10){
                list.add(sum / 10);
                sum %= 10;
            }
            list.add(sum);
        }
        List<Integer> temp = null;
        while (list.size() > k){
            temp = new ArrayList<>();
            for (int i = 0 ; i < list.size();i+=k){
                int sum = 0;
                for (int j = 0 ; j < k && i+j < list.size();j++){
                    sum += list.get(i+j);
                }
                if (sum >= 10){
                    temp.add(sum / 10);
                    sum %= 10;
                }
                temp.add(sum);
            }
            list = temp;
        }
        StringBuilder sb = new StringBuilder();
        for (Integer integer : list) {
            sb.append(integer);
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.22% 的Java用户
     * 	内存消耗:42.6 MB,击败了13.89% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            int n = s.length();
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < n; i += k) { // k 个一组
                int sum = 0;
                for (int j = i; j < Math.min(i + k, n); j++) {
                    sum += s.charAt(j) - '0'; // 计算数字和
                }
                t.append(sum);
            }
            s = t.toString();
        }
        return s;
    }
}
