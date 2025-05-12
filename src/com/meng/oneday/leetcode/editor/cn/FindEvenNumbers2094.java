package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class FindEvenNumbers2094 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了74.49% 的Java用户
     * 	内存消耗:43.7 MB,击败了92.35% 的Java用户
     * @param digits
     * @return
     */
    public int[] findEvenNumbers2094(int[] digits) {
        List<Integer> list = new ArrayList<>();
        int[] count = new int[10];
        for(int digit : digits){
            count[digit]++;
        }
        for(int i = 0 ; i < 10 ; i+=2){
            if (count[i] == 0) {
                continue;
            }
            for(int j  = 1 ; j < 10 ; j++){
                if (count[j] == 0 || (j == i && count[j] == 1)) {
                    continue;
                }
                for(int k = 0 ; k < 10 ; k++){
                    if (count[k] == 0) {
                        continue;
                    }
                    if ( (k == j || k == i) && count[k] == 1) {
                        continue;
                    }
                    if ( j == i && k == i && count[k] == 2) {
                        continue;
                    }
                    list.add(j*100+k*10+i);
                }
            }
        }
        Collections.sort(list);
        int[] res = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i++){
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了79.08% 的Java用户
     * 	内存消耗:44 MB,击败了69.39% 的Java用户
     * @param digits
     * @return
     */
    public int[] findEvenNumbersOther(int[] digits) {
        int[] cnt = new int[10];
        for (int d : digits) {
            cnt[d]++;
        }

        List<Integer> ans = new ArrayList<>();
        next:
        for (int i = 100; i < 1000; i += 2) { // 枚举所有三位数偶数 i
            int[] c = new int[10];
            for (int x = i; x > 0; x /= 10) { // 枚举 i 的每一位 d
                int d = x % 10;
                // 如果 i 中 d 的个数比 digits 中的还多，那么 i 无法由 digits 中的数字组成
                if (++c[d] > cnt[d]) {
                    continue next; // 枚举下一个偶数
                }
            }
            ans.add(i);
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了95.41% 的Java用户
     * 	内存消耗:43.8 MB,击败了81.63% 的Java用户
     * @param digits
     * @return
     */
    public int[] findEvenNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int d : digits) {
            cnt[d]++;
        }

        List<Integer> ans = new ArrayList<>();
        dfs(0, 0, cnt, ans);
        return ans.stream().mapToInt(i -> i).toArray();
    }

    // i=0 百位，i=1 十位，i=2 个位，x 表示当前正在构造的数字
    private void dfs(int i, int x, int[] cnt, List<Integer> ans) {
        if (i == 3) {
            ans.add(x);
            return;
        }
        for (int d = 0; d < 10; d++) {
            if (cnt[d] > 0 && (i == 0 && d > 0 || i == 1 || i == 2 && d % 2 == 0)) {
                cnt[d]--; // 消耗一个数字 d
                dfs(i + 1, x * 10 + d, cnt, ans);
                cnt[d]++; // 复原
            }
        }
    }

}
