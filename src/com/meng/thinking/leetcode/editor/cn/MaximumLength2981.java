package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

class MaximumLength2981 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了21.43% 的Java用户
     * 	内存消耗:43.6 MB,击败了35.71% 的Java用户
     * @param s
     * @return
     */
    public int maximumLengthMy(String s) {
        Map<Character, PriorityQueue<String>> cache = new HashMap<>();
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(chars[0]);
        for (int i = 1; i < chars.length; i++){
            if (chars[i] != chars[i-1]){
                if (!cache.containsKey(chars[i-1])){
                    cache.put(chars[i-1],new PriorityQueue<>(Comparator.comparingInt(String::length)));
                }
                cache.get(chars[i-1]).add(sb.toString());
                sb.delete(0,sb.length());
            }
            sb.append(chars[i]);
        }
        if (!cache.containsKey(chars[chars.length-1])){
            cache.put(chars[chars.length-1],new PriorityQueue<>(Comparator.comparingInt(String::length)));
        }
        cache.get(chars[chars.length-1]).add(sb.toString());
        int ans = -1;
        for (PriorityQueue<String> queue : cache.values()){
            if (queue.size() >=3){
                while (queue.size()>3){
                    queue.poll();
                }
                int first = queue.poll().length();
                int second = queue.poll().length();
                int third = queue.poll().length();
                if (first != third && second == third){
                    second--;
                }
                ans = Math.max(ans,Math.max(second,third-2));

            }else {
                if (queue.size() == 2){
                    int first = queue.poll().length();
                    int third = queue.poll().length();
                    if (first == third){
                        first --;
                    }
                    ans = Math.max(ans,Math.max(first,third-2));
                }else if (queue.size() == 1){
                    ans = Math.max(ans,queue.poll().length()-2);
                }
            }
        }
        return ans>0?ans:-1;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了87.50% 的Java用户
     * 	内存消耗:42.1 MB,击败了83.93% 的Java用户
     * @param s
     * @return
     */
    public int maximumLengthOfficial(String s) {
        int ans = -1;
        int len = s.length();

        List<Integer>[] chs = new List[26];
        for (int i = 0; i < 26; i++) {
            chs[i] = new ArrayList<Integer>();
        }
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            cnt++;
            if (i + 1 == len || s.charAt(i) != s.charAt(i + 1)) {
                int ch = s.charAt(i) - 'a';
                chs[ch].add(cnt);
                cnt = 0;

                for (int j = chs[ch].size() - 1; j > 0; j--) {
                    if (chs[ch].get(j) > chs[ch].get(j - 1)) {
                        Collections.swap(chs[ch], j, j - 1);
                    } else {
                        break;
                    }
                }

                if (chs[ch].size() > 3) {
                    chs[ch].remove(chs[ch].size() - 1);
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            if (chs[i].size() > 0 && chs[i].get(0) > 2) {
                ans = Math.max(ans, chs[i].get(0) - 2);
            }
            if (chs[i].size() > 1 && chs[i].get(0) > 1) {
                ans = Math.max(ans, Math.min(chs[i].get(0) - 1, chs[i].get(1)));
            }
            if (chs[i].size() > 2) {
                ans = Math.max(ans, chs[i].get(2));
            }
        }

        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了57.14% 的Java用户
     * 	内存消耗:42.3 MB,击败了57.14% 的Java用户
     * @param S
     * @return
     */
    public int maximumLength(String S) {
        char[] s = S.toCharArray();
        List<Integer>[] groups = new ArrayList[26];
        Arrays.setAll(groups, i -> new ArrayList<>());
        int cnt = 0;
        for (int i = 0; i < s.length; i++) {
            cnt++;
            if (i + 1 == s.length || s[i] != s[i + 1]) {
                groups[s[i] - 'a'].add(cnt); // 统计连续字符长度
                cnt = 0;
            }
        }

        int ans = 0;
        for (List<Integer> a : groups) {
            if (a.isEmpty()) continue;
            a.sort(Collections.reverseOrder());
            a.add(0);
            a.add(0); // 假设还有两个空串
            ans = Math.max(ans, Math.max(a.get(0) - 2, Math.max(Math.min(a.get(0) - 1, a.get(1)), a.get(2))));
        }

        return ans > 0 ? ans : -1;
    }

}
