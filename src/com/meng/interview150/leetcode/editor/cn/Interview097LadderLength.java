package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

class Interview097LadderLength {
    /**
     * 解答成功:
     * 	执行耗时:1009 ms,击败了14.57% 的Java用户
     * 	内存消耗:44.4 MB,击败了75.70% 的Java用户
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthMy(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)){
            return 0;
        }
        int m = beginWord.length(),n = wordList.size();
        List<Integer>[] cache = new List[n];
        for (int i = 0; i < n; i++) {
            cache[i] = new ArrayList<>();
        }
        int index = -1;
        //记录异位元素，并判断是否存在endWord
        for (int i = 0; i < n; i++) {
            if (wordList.get(i).equals(endWord)){
                index = i;
            }
            for (int j = i +1 ; j < n ; j++){
                int diff = 0;
                for (int k = 0; k < m; k++) {
                    if (wordList.get(i).charAt(k) != wordList.get(j).charAt(k)){
                        diff++;
                    }
                    if (diff > 1){
                        break;
                    }
                }
                if (diff == 1){
                    cache[i].add(j);
                    cache[j].add(i);
                }
            }
        }
        if (index == -1){
            return 0;
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int step = 2;
        //查找符合条件的数据
        for (int i = 0 ; i < n ; i++){
            int diff = 0;
            for (int k = 0; k < m; k++) {
                if (wordList.get(i).charAt(k) != beginWord.charAt(k)){
                    diff++;
                }
                if (diff > 1){
                    break;
                }
            }
            if (diff == 1){
                if (i== index){
                    return step;
                }
                queue.add(i);
                visited[i] =true;
            }
        }
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            for (int i = 0 ; i < size ; i++){
                int x = queue.poll();
                for (int y : cache[x]){
                    if (!visited[y]){
                        if(y == index){
                            return step;
                        }
                        queue.add(y);
                        visited[y] = true;
                    }
                }
            }
        }
        return 0;
    }


    /**
     * 解答成功:
     * 	执行耗时:58 ms,击败了71.82% 的Java用户
     * 	内存消耗:56.6 MB,击败了7.12% 的Java用户
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;
    public int ladderLengthOfficial(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }



}
