package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class ClearStars3170 {
    /**
     * 解答成功:
     * 	执行耗时:204 ms,击败了20.38% 的Java用户
     * 	内存消耗:47.6 MB,击败了22.75% 的Java用户
     * @param s
     * @return
     */
    public String clearStars3170(String s) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        boolean[] isFilter = new boolean[s.length()];
        for(int i = 0 ; i < s.length() ; i++){
            if (s.charAt(i) == '*'){
                isFilter[i]= true;
                if (!pq.isEmpty()){
                    isFilter[pq.poll()[1]]= true;
                }
            }else {
                pq.offer(new int[]{s.charAt(i)-'a',i});
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++){
            if (!isFilter[i]){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:71 ms,击败了76.78% 的Java用户
     * 	内存消耗:46.9 MB,击败了56.40% 的Java用户
     * @param S
     * @return
     */
    public String clearStarsOther1(String S) {
        char[] s = S.toCharArray();
        List<Integer>[] stacks = new ArrayList[26];
        Arrays.setAll(stacks, i -> new ArrayList<>());
        for (int i = 0; i < s.length; i++) {
            if (s[i] != '*') {
                stacks[s[i] - 'a'].add(i);
                continue;
            }
            // 找第一个非空栈，即为最小字母
            for (List<Integer> st : stacks) {
                if (!st.isEmpty()) {
                    st.remove(st.size() - 1);
                    break;
                }
            }
        }

        List<Integer> idx = new ArrayList<>();
        for (List<Integer> st : stacks) {
            idx.addAll(st);
        }
        Collections.sort(idx);

        StringBuilder ans = new StringBuilder(idx.size());
        for (int i : idx) {
            ans.append(s[i]);
        }
        return ans.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:96 ms,击败了37.92% 的Java用户
     * 	内存消耗:46.3 MB,击败了80.10% 的Java用户
     * @param S
     * @return
     */
    public String clearStarsOther2(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        List<Integer>[] stacks = new ArrayList[26];
        Arrays.setAll(stacks, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (s[i] != '*') {
                stacks[s[i] - 'a'].add(i);
                continue;
            }
            for (List<Integer> st : stacks) {
                if (!st.isEmpty()) {
                    s[st.remove(st.size()-1)] = '*';
                    break;
                }
            }
        }

        // 原地修改，去掉 s 中的 '*'
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] != '*') {
                s[idx++] = s[i];
            }
        }
        return new String(s, 0, idx);
    }

    /**
     * 解答成功:
     * 	执行耗时:37 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.4 MB,击败了79.62% 的Java用户
     * @param S
     * @return
     */
    public String clearStars(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        List<Integer>[] stacks = new ArrayList[26];
        Arrays.setAll(stacks, i -> new ArrayList<>());
        int mask = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] != '*') {
                stacks[s[i] - 'a'].add(i);
                mask |= 1 << (s[i] - 'a'); // 标记第 s[i]-'a' 个栈为非空
            } else {
                int k = Integer.numberOfTrailingZeros(mask);
                List<Integer> st = stacks[k];
                s[st.remove(st.size()-1)] = '*';
                if (st.isEmpty()) {
                    mask ^= 1 << k; // 标记第 k 个栈为空
                }
            }
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] != '*') {
                s[idx++] = s[i];
            }
        }
        return new String(s, 0, idx);
    }

}
