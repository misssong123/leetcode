package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

class Interview100FindWords {
    Set<Integer> used = new HashSet<>();
    int m;

    /**
     * 超过时间限制
     * @param board
     * @param words
     * @return
     */
    public List<String> findWordsMy(char[][] board, String[] words) {
        int n = board.length;
        m= board[0].length;
        Map<Character,Set<Integer>> map = new HashMap<>();
        //记录每个字符的位置
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                char c =  board[i][j];
                if (!map.containsKey(c)){
                    map.put(c,new HashSet<>());
                }
                map.get(c).add(i*m+j);
            }
        }
        int max = n * m;
        List<String> res = new ArrayList<>();
        for(String word : words) {
            if(word.length() > max){
                continue;
            }
            boolean f = true;
            for (char c : word.toCharArray()) {
                if (!map.containsKey(c)) {
                    f = false;
                    break;
                }
            }
            used.clear();
            if(f&&dfs(word,map,0,-1)){
                res.add(word);
            }
        }
        return res;
    }

    private boolean dfs(String word, Map<Character, Set<Integer>> map,int index,int parent) {
        if (index == word.length()){
            return true;
        }
        //当前元素
        char c = word.charAt(index);
        if (index == 0){
            for (Integer i : map.get(c)) {
                used.add(i);
                if (dfs(word,map,index+1,i)){
                    return true;
                }
                used.remove(i);
            }
        }else {
            Set<Integer> temp = map.get(c);
            for (Integer cur : temp) {
                if (!used.contains(cur)){
                    //判断是否相邻
                    if (Math.abs(cur - parent) == m||(parent%m!=0 && parent - cur ==1)||((parent%m != m-1)&& cur - parent == 1)){
                        used.add(cur);
                        if (dfs(word,map,index+1,cur)){
                            return true;
                        }
                        used.remove(cur);
                    }
                }
            }
        }
        return false;
    }


    /**
     * 执行用时分布
     * 306
     * ms
     * 击败
     * 65.09%
     * 复杂度分析
     * 消耗内存分布
     * 43.86
     * MB
     * 击败
     * 60.23%
     * @param board
     * @param words
     * @return
     */
    int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    public List<String> findWords(char[][] board, String[] words) {
        Tire root = new Tire();
        for (String word : words) {
            root.insert(word);
        }
        Set<String> temp = new HashSet<>();
        for (int i = 0 ; i < board.length; i++) {
            for (int j = 0 ; j < board[i].length ; j++){
                dfs(board,root,i,j,temp);
            }
        }
        return new ArrayList<>(temp);
    }

    private void dfs(char[][] board, Tire root, int i, int j, Set<String> temp) {
        char c = board[i][j];
        int index = c - 'a';
        //不包含该字符
        if (root.child[index] ==null){
            return;
        }
        Tire tire = root.child[index];
        //找到单词
        if (tire.word != null){
            temp.add(tire.word);
        }
        board[i][j] = '#';
        for(int[] dir : dirs){
            int newX = i + dir[0];
            int newY = j + dir[1];
            if (newX >=0 &&newX < board.length && newY >= 0 && newY < board[0].length&& board[newX][newY] != '#'){
                dfs(board,tire,newX,newY,temp);
            }
        }
        board[i][j] = c;
    }

    class Tire{
        public String word;
        public Tire[] child;
        public Tire(){
            this.word = null;
            this.child = new Tire[26];
        }
        public void insert(String word){
            Tire trie = this;
            for (char c : word.toCharArray()) {
                int index = c-'a';
                if (trie.child[index] == null) {
                    trie.child[index] = new Tire();
                }
                trie = trie.child[index];
            }
            trie.word = word;
        }
    }
}
