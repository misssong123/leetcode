package com.meng.oneday.leetcode.editor.cn;

class MaxScore1422 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.82% 的Java用户
     * 	内存消耗:42.3 MB,击败了29.85% 的Java用户
     * @param s 输入的字符串，由'0'和'1'组成
     * @return 返回分割字符串后能得到的最高的分数
     */
    public int maxScore1422(String s) {
        int len = s.length();  // 获取字符串长度
        int zeroNum = 0;  // 统计字符串中'0'的总数
        char[] charArray = s.toCharArray();  // 将字符串转换为字符数组
        for (char c : charArray) {
            if (c == '0') {
                zeroNum++;  // 统计'0'的个数
            }
        }
        int ans = 0;  // 初始化最大分数为0
        int zeroLeft = 0;  // 记录左侧'0'的数量
        for (int i = 0 ; i < len -1; i++){  // 遍历字符串，最后一个字符不作为分割点
            if (charArray[i] == '0'){
                zeroLeft++;  // 左侧'0'数量增加
            }
            // 计算当前分割点处的分数：左侧'0'数 + 右侧'1'数
            int num = zeroLeft + (len - i -1) - (zeroNum - zeroLeft);
            ans = Math.max(ans,num);  // 更新最大分数
        }
        return ans;  // 返回最高分数
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.82% 的Java用户
     * 	内存消耗:42.4 MB,击败了22.77% 的Java用户
     * @param S 输入的字符串，由'0'和'1'组成
     * @return 返回分割字符串后能得到的最高的分数
     */
    public int maxScore(String S) {
        char[] s = S.toCharArray();  // 将字符串转换为字符数组
        int n = s.length;  // 获取字符串长度
        int total1 = 0;  // 统计字符串中'1'的总数
        int extra = 0;  // 记录额外的分数
        int maxExtra = -1;  // 记录最大额外分数
        for (int i = 0; i < n - 1; i++) {  // 遍历字符串，最后一个字符不作为分割点
            total1 += s[i] - '0';  // 统计左侧'1'的数量
            // 如果当前字符是'0'，增加额外分数；如果是'1'，减少额外分数
            extra += s[i] == '0' ? 1 : -1;
            maxExtra = Math.max(maxExtra, extra);  // 更新最大额外分数
        }
        total1 += s[n - 1] - '0';  // 加上最后一个字符的'1'数量
        return total1 + maxExtra;  // 返回总分数（'1'总数 + 最大额外分数）
    }


}
