package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class PunishmentNumber2698 {
    /**
     * 24ms
     * 击败 35.62%使用 Java 的用户
     * 内存
     * 详情
     * 40.33MB
     * 击败 63.19%使用 Java 的用户
     * @param n
     * @return
     */
    public int punishmentNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            String s = Integer.toString(i * i);
            if (dfs(s, 0, 0, i)) {
                res += i * i;
            }
        }
        return res;
    }

    public boolean dfs(String s, int pos, int tot, int target) {
        if (pos == s.length()) {
            return tot == target;
        }
        int sum = 0;
        for (int i = pos; i < s.length(); i++) {
            sum = sum * 10 + s.charAt(i) - '0';
            if (sum + tot > target) {
                break;
            }
            if (dfs(s, i + 1, sum + tot, target)) {
                return true;
            }
        }
        return false;
    }
}
