package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

class Interview096MinMutation {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了73.81% 的Java用户
     * 	内存消耗:40.5 MB,击败了46.76% 的Java用户
     * @param startGene
     * @param endGene
     * @param bank
     * @return
     */
    public int minMutationMy(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)){
            return 0;
        }
        //已经出现的字符
        Set<String> cache = new HashSet<>();
        //基因库
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(endGene)){
            return -1;
        }
        cache.add(startGene);
        Queue<String> queue = new LinkedList<>();
        queue.add(startGene);
        int res = 0;
        char[] chars = {'A','C','G','T'};
        while (!queue.isEmpty()){
            res++;
            int size = queue.size();
            for(int i = 0 ; i < size ; i++){
                String s = queue.poll();
                for(int j = 0; j < 8; j++){
                    char[] charArray = s.toCharArray();
                    char c = s.charAt(j);
                    for (char aChar : chars) {
                        if (c == aChar) {
                            continue;
                        }
                        charArray[j] = aChar;
                        String newStr = new String(charArray);
                        if (newStr.equals(endGene)){
                            return res;
                        }
                        if (!cache.contains(newStr) && bankSet.contains(newStr)){
                            queue.add(newStr);
                            cache.add(newStr);
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutationOfficial1(String start, String end, String[] bank) {
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

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了77.75% 的Java用户
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        int m = start.length();
        int n = bank.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        int endIndex = -1;
        for (int i = 0; i < n; i++) {
            if (end.equals(bank[i])) {
                endIndex = i;
            }
            for (int j = i + 1; j < n; j++) {
                int mutations = 0;
                for (int k = 0; k < m; k++) {
                    if (bank[i].charAt(k) != bank[j].charAt(k)) {
                        mutations++;
                    }
                    if (mutations > 1) {
                        break;
                    }
                }
                if (mutations == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        if (endIndex == -1) {
            return -1;
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[n];
        int step = 1;
        for (int i = 0; i < n; i++) {
            int mutations = 0;
            for (int k = 0; k < m; k++) {
                if (start.charAt(k) != bank[i].charAt(k)) {
                    mutations++;
                }
                if (mutations > 1) {
                    break;
                }
            }
            if (mutations == 1) {
                queue.offer(i);
                visited[i] = true;
            }
        }
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int curr = queue.poll();
                if (curr == endIndex) {
                    return step;
                }
                for (int next : adj[curr]) {
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            step++;
        }
        return -1;
    }
}
