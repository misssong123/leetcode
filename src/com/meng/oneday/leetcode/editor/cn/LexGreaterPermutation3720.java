package com.meng.oneday.leetcode.editor.cn;
class LexGreaterPermutation3720 {
    /**
     * 借鉴其他人的思路
     * 1.统计s,target中的字符差异情况
     * 2.记录缺失的字符个数(neg)
     * 3.通过从target最后一个字符到首个字符查找
     * 4.找到首个neg为0的位置
     * 5.将target中该位置的字符替换为s中大于该字符的下标
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.3 MB,击败了61.11% 的Java用户
     * @param s
     * @param target
     * @return
     */
    public String lexGreaterPermutation3720(String s, String target) {
        int[] cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : target.toCharArray()) {
            cnts[c - 'a']--;
        }
        //统计负数个数
        int neg = 0;
        for (int cnt : cnts){
            neg += (cnt < 0 ? 1 : 0);
        }
        for (int i = target.length() - 1; i >= 0; i--) {
            int index = target.charAt(i) - 'a';
            cnts[index]++;
            if (cnts[index] == 0) {
                neg--;
            }
            if (neg > 0){
                continue;
            }
            int j = index + 1;
            while (j < 26 && cnts[j] == 0){
                j++;
            }
            if (j < 26){
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i).append((char)('a' + j));
                cnts[j]--;
                for (int k = 0; k < 26; k++) {
                    for (int l = 0; l < cnts[k]; l++) {
                        sb.append((char)('a' + k));
                    }
                }
                return sb.toString();
            }
        }
        return "";
    }
    /**
     * 思路有误
     * @param s
     * @param target
     * @return
     */
    public String lexGreaterPermutationError(String s, String target) {
        //统计个数
        int[] sCnt = new int[26];
        int[] tCnt = new int[26];
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            sCnt[index]++;
            tCnt[index]++;
        }
        for (char c : target.toCharArray()) {
            int index = c - 'a';
            arr[index]++;
        }
        char[] chars = target.toCharArray();
        for (char c : chars) {
            int index = c - 'a';

            boolean flag = false;
            for (int i = index + 1; i < 26; i++) {
                if (tCnt[i] > 0) {
                    flag = true;
                    break;
                }
            }
            if (flag){
                break;
            }
            if (tCnt[index] == 0){
                return "";
            }
            tCnt[index]--;
        }
        //判断是否完全相同
        boolean flag = true;
        for (int i = 0; i < 26; i++) {
            if (sCnt[i] != arr[i]){
                flag = false;
                break;
            }
        }
        if (flag){
            for (int i = chars.length -2 ; i >= 0; i--) {
                if (chars[i] < chars[i+1]){
                    char temp = chars[i];
                    chars[i] = chars[i+1];
                    chars[i+1] = temp;
                    return new String(chars);
                }
            }
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : chars){
            int index = c - 'a';
            if (sCnt[index] > 0){
                sb.append(c);
                sCnt[index]--;
            }else {
                //找到第一个大于target的字符
                for (int j =index + 1 ; j < 26 ; j++){
                    if (sCnt[j] > 0){
                        sb.append((char)(j + 'a'));
                        sCnt[j]--;
                        break;
                    }
                }
                break;
            }
        }
        for (int i = 0 ; i < 26 ; i++){
            for (int j = 0 ; j < sCnt[i] ; j++){
                sb.append((char)(i + 'a'));
            }
        }
        return sb.toString();
    }
    public String lexGreaterPermutationOther(String s, String target) {
        char[] t = target.toCharArray();
        int n = t.length;
        int[] left = new int[26];
        for (int i = 0; i < n; i++) {
            left[s.charAt(i) - 'a']++;
            left[t[i] - 'a']--; // 消耗 s 中的一个字母 t[i]
        }

        int neg = 0;
        int mx = 0;
        for (int i = 0; i < 26; i++) {
            if (left[i] < 0) {
                neg++; // 统计 left 中的负数个数
            } else if (left[i] > 0) {
                mx = Math.max(mx, i);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int b = t[i] - 'a';
            left[b]++; // 撤销消耗

            if (left[b] == 0) {
                neg--;
            } else if (left[b] == 1) {
                mx = Math.max(mx, b);
            }

            // left 有负数 or 没有大于 target[i] 的字母
            if (neg > 0 || b >= mx) {
                continue;
            }

            int j = b + 1;
            while (left[j] == 0) {
                j++;
            }

            // 把 target[i] 增大到 j
            left[j]--;
            StringBuilder ans = new StringBuilder(target.substring(0, i + 1));
            ans.setCharAt(i, (char) ('a' + j));

            for (int k = 0; k < 26; k++) {
                for (int count = 0; count < left[k]; count++) {
                    ans.append((char) ('a' + k));
                }
            }
            return ans.toString();
        }
        return "";
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.9 MB,击败了88.89% 的Java用户
     * @param s
     * @param target
     * @return
     */
    public String lexGreaterPermutationAi(String s, String target) {
        int n = target.length();
        int[] left = new int[26];

        // 统计 s 中各字符频次
        for (int i = 0; i < s.length(); i++) {
            left[s.charAt(i) - 'a']++;
        }

        // 先尝试完全匹配 target 的前缀
        for (int i = 0; i < n; i++) {
            left[target.charAt(i) - 'a']--;
        }

        // 检查 target 是否本身就是 s 的合法排列（若是，后续在 i=n-1 处也可处理）
        // 倒序枚举公共前缀的长度 i
        for (int i = n - 1; i >= 0; i--) {
            int b = target.charAt(i) - 'a';
            left[b]++; // 撤销第 i 位的匹配，准备替换为更大字符

            // 检查剩余的字符频次是否合法（不能有负数）
            if (hasNegative(left)) {
                continue;
            }

            // 寻找严格大于 target[i] 的最小可用字符 j
            int j = b + 1;
            while (j < 26 && left[j] == 0) {
                j++;
            }

            // 找到了可替换的更大字符
            if (j < 26) {
                left[j]--;
                StringBuilder ans = new StringBuilder(target.substring(0, i));
                ans.append((char) ('a' + j));

                // 剩余位置按字典序升序填充（贪心保证最小）
                for (int k = 0; k < 26; k++) {
                    for (int count = 0; count < left[k]; count++) {
                        ans.append((char) ('a' + k));
                    }
                }
                return ans.toString();
            }
        }
        return "";
    }

    private boolean hasNegative(int[] left) {
        for (int count : left) {
            if (count < 0) return true;
        }
        return false;
    }
}
