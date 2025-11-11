package com.meng.hot100.leetcode.editor.cn;

import java.util.*;

class MinMutation433 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.9 MB,击败了8.19% 的Java用户
     * @param startGene
     * @param endGene
     * @param bank
     * @return
     */
    public int minMutation433(String startGene, String endGene, String[] bank) {
        int len = bank.length;
        boolean[] visited = new boolean[len];
        Deque<String> queue = new ArrayDeque<>(len);
        int step = 0;
        queue.offer(startGene);
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            for(int i = 0 ; i < size ; i++){
                String cur = queue.poll();
                for(int j = 0 ; j < len ; j++){
                    if (!visited[j] && isMatch(cur,bank[j])){
                        if (bank[j].equals(endGene)){
                            return step;
                        }
                        visited[j] = true;
                        queue.offer(bank[j]);
                    }
                }
            }
        }
        return -1;
    }

    private boolean isMatch(String cur, String s) {
        int diff = 0;
        for(int i = 0 ; i < cur.length() ; i++){
            if (cur.charAt(i) != s.charAt(i)){
                diff++;
            }
        }
        return diff == 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了80.40% 的Java用户
     * 	内存消耗:42 MB,击败了5.84% 的Java用户
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        Set<String> cnt = new HashSet<String>();
        Set<String> visited = new HashSet<String>();
        char[] keys = {'A', 'C', 'G', 'T'};
        for (String w : bank) {
            cnt.add(w);
        }
        if (start.equals(end)) {
            return 0;
        }
        if (!cnt.contains(end)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<String>();
        queue.offer(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String curr = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != curr.charAt(j)) {
                            StringBuffer sb = new StringBuffer(curr);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (!visited.contains(next) && cnt.contains(next)) {
                                if (next.equals(end)) {
                                    return step;
                                }
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
