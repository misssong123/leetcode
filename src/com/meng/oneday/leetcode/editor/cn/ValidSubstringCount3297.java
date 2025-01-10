package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class ValidSubstringCount3297 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了75.46% 的Java用户
     * 	内存消耗:44.4 MB,击败了54.27% 的Java用户
     * @param word1
     * @param word2
     * @return
     */
    public long validSubstringCountMy(String word1, String word2) {
        long res = 0L;
        int pre = -1;
        int len = word1.length();
        if (len < word2.length()){
            return res;
        }
        int target = 0;
        int[] pos = new int[26];
        //存储word2中每个字符的位置
        for(char c : word2.toCharArray()){
            int index = c-'a';
            pos[index]++;
            if (pos[index] == 1){
                target |= (1 << index);
            }
        }
        int start = 0, end = 0;
        int[] temp = new int[26];
        int cur = 0;
        while(end < len && start + word2.length() <= len){
            //符合条件坐标位置
            while(end < len){
                int index = word1.charAt(end)-'a';
                temp[index]++;
                if (pos[index] > 0 && temp[index] == pos[index]){
                    cur |= (1 << index);
                }
                if (cur == target){
                    break;
                }
                end++;
            }
            //计算最短符合条件的数据
            if (cur == target){
                while (start <= end){
                    int index = word1.charAt(start)-'a';
                    if(temp[index] -1 < pos[index]){
                        break;
                    }
                    temp[index]--;
                    start++;
                }
                //计算符合条件的数据
                res += (long)(start - pre) * (len - end);
                //避免重复计算
                pre = start;
                //重新计算下标
                int index = word1.charAt(start)-'a';
                temp[index]--;
                cur ^= (1 << index);
                start++;
                end++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了93.25% 的Java用户
     * 	内存消耗:44.4 MB,击败了72.36% 的Java用户
     * @param S
     * @param T
     * @return
     */
    public long validSubstringCount1(String S, String T) {
        if (S.length() < T.length()) {
            return 0;
        }
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[] diff = new int[26]; // t 的字母出现次数与 s 的字母出现次数之差
        for (char c : t) {
            diff[c - 'a']++;
        }
        // 统计窗口内有多少个字母的出现次数比 t 的少
        int less = 0;
        for (int d : diff) {
            if (d > 0) {
                less++;
            }
        }
        long ans = 0;
        int left = 0;
        for (char c : s) {
            diff[c - 'a']--;
            if (diff[c - 'a'] == 0) {
                // c 移入窗口后，窗口内 c 的出现次数和 t 的一样
                less--;
            }
            while (less == 0) { // 窗口符合要求
                char outChar = s[left++]; // 准备移出窗口的字母
                if (diff[outChar - 'a'] == 0) {
                    // outChar 移出窗口之前检查出现次数，
                    // 如果窗口内 outChar 的出现次数和 t 的一样，
                    // 那么 outChar 移出窗口后，窗口内 outChar 的出现次数比 t 的少
                    less++;
                }
                diff[outChar - 'a']++;
            }
            ans += left;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了57.67% 的Java用户
     * 	内存消耗:44.4 MB,击败了62.81% 的Java用户
     * @param word1
     * @param word2
     * @return
     */
    public long validSubstringCount(String word1, String word2) {
        int[] diff = new int[26];
        for (char c : word2.toCharArray()) {
            diff[c - 'a']--;
        }
        long res = 0;
        int[] cnt = { (int) Arrays.stream(diff).filter(c -> c < 0).count() };
        int l = 0, r = 0;
        while (l < word1.length()) {
            while (r < word1.length() && cnt[0] > 0) {
                update(diff, word1.charAt(r) - 'a', 1, cnt);
                r++;
            }
            if (cnt[0] == 0) {
                res += word1.length() - r + 1;
            }
            update(diff, word1.charAt(l) - 'a', -1, cnt);
            l++;
        }
        return res;
    }

    private void update(int[] diff, int c, int add, int[] cnt) {
        diff[c] += add;
        if (add == 1 && diff[c] == 0) {
            // 表明 diff[c] 由 -1 变为 0
            cnt[0]--;
        } else if (add == -1 && diff[c] == -1) {
            // 表明 diff[c] 由 0 变为 -1
            cnt[0]++;
        }
    }

}
