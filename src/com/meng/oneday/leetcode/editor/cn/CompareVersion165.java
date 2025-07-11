package com.meng.oneday.leetcode.editor.cn;

class CompareVersion165 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了74.73% 的Java用户
     * 	内存消耗:40.9 MB,击败了16.60% 的Java用户
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion165(String version1, String version2) {
        String[] version1Arr = version1.split("\\.");
        String[] version2Arr = version2.split("\\.");
        int len = Math.max(version1Arr.length,version2Arr.length);
        for (int i = 0; i < len; i++) {
            int v1 = i < version1Arr.length ? Integer.parseInt(version1Arr[i]) : 0;
            int v2 = i < version2Arr.length ? Integer.parseInt(version2Arr[i]) : 0;
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了60.48% 的Java用户
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        int n = version1.length(), m = version2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            int x = 0;
            for (; i < n && version1.charAt(i) != '.'; ++i) {
                x = x * 10 + version1.charAt(i) - '0';
            }
            ++i; // 跳过点号
            int y = 0;
            for (; j < m && version2.charAt(j) != '.'; ++j) {
                y = y * 10 + version2.charAt(j) - '0';
            }
            ++j; // 跳过点号
            if (x != y) {
                return x > y ? 1 : -1;
            }
        }
        return 0;
    }

}
