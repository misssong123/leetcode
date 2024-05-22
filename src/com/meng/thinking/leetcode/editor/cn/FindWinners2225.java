package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class FindWinners2225 {
    /**
     * 解答成功:
     * 	执行耗时:98 ms,击败了21.87% 的Java用户
     * 	内存消耗:84.8 MB,击败了82.29% 的Java用户
     * @param matches
     * @return
     */
    public List<List<Integer>> findWinnersMy(int[][] matches) {
        Map<Integer,Integer> loser = new HashMap<>();
        Set<Integer> user = new HashSet<>();
        for(int[] match:matches){
            user.add(match[0]);
            user.add(match[1]);
            loser.put(match[1],loser.getOrDefault(match[1],0)+1);
        }
        List<Integer> winner = new ArrayList<>();
        List<Integer> loserOne = new ArrayList<>();
        for(int u:user){
            if (!loser.containsKey(u)){
                winner.add(u);
            }else if (loser.get(u)==1){
                loserOne.add(u);
            }
        }
        Collections.sort(winner);
        Collections.sort(loserOne);
        return Arrays.asList(winner,loserOne);
    }

    /**
     * 解答成功:
     * 	执行耗时:74 ms,击败了57.29% 的Java用户
     * 	内存消耗:78.9 MB,击败了93.75% 的Java用户
     * @param matches
     * @return
     */
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int[] match : matches) {
            int winner = match[0], loser = match[1];
            freq.putIfAbsent(winner, 0);
            freq.put(loser, freq.getOrDefault(loser, 0) + 1);
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; ++i) {
            ans.add(new ArrayList<Integer>());
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int key = entry.getKey(), value = entry.getValue();
            if (value < 2) {
                ans.get(value).add(key);
            }
        }

        Collections.sort(ans.get(0));
        Collections.sort(ans.get(1));
        return ans;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
