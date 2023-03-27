package com.meng.graphtheory.day12;

import java.util.*;

/**
 * 127. 单词接龙
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 *
 * 每一对相邻的单词只差一个字母。
 *  对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 *
 *
 * 示例 1：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *
 *
 * 提示：
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 */
public class LadderLength {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return -1;
    }

    /**
     *执行用时：
     * 26 ms
     * , 在所有 Java 提交中击败了
     * 80.39%
     * 的用户
     * 内存消耗：
     * 49 MB
     * , 在所有 Java 提交中击败了
     * 12.29%
     * 的用户
     * 通过测试用例：
     * 50 / 50
     */
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
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

    /**
     *执行用时：
     * 23 ms
     * , 在所有 Java 提交中击败了
     * 87.59%
     * 的用户
     * 内存消耗：
     * 49.3 MB
     * , 在所有 Java 提交中击败了
     * 6.19%
     * 的用户
     * 通过测试用例：
     * 50 / 50
     */
    Map<String, Integer> wordId2 = new HashMap<String, Integer>();
    List<List<Integer>> edge2 = new ArrayList<List<Integer>>();
    int nodeNum2 = 0;

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge2(word);
        }
        addEdge2(beginWord);
        if (!wordId2.containsKey(endWord)) {
            return 0;
        }

        int[] disBegin = new int[nodeNum2];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = wordId2.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queBegin = new LinkedList<Integer>();
        queBegin.offer(beginId);

        int[] disEnd = new int[nodeNum2];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = wordId2.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queEnd = new LinkedList<Integer>();
        queEnd.offer(endId);

        while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; ++i) {
                int nodeBegin = queBegin.poll();
                if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                    return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                }
                for (int it : edge2.get(nodeBegin)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[nodeBegin] + 1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize = queEnd.size();
            for (int i = 0; i < queEndSize; ++i) {
                int nodeEnd = queEnd.poll();
                if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                }
                for (int it : edge2.get(nodeEnd)) {
                    if (disEnd[it] == Integer.MAX_VALUE) {
                        disEnd[it] = disEnd[nodeEnd] + 1;
                        queEnd.offer(it);
                    }
                }
            }
        }
        return 0;
    }

    public void addEdge2(String word) {
        addWord2(word);
        int id1 = wordId2.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord2(newWord);
            int id2 = wordId2.get(newWord);
            edge2.get(id1).add(id2);
            edge2.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord2(String word) {
        if (!wordId2.containsKey(word)) {
            wordId2.put(word, nodeNum2++);
            edge2.add(new ArrayList<Integer>());
        }
    }

}
