package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class GoodSubSetOfBinaryMatrix2732 {
    /**
     * 解答成功:
     * 	执行耗时:329 ms,击败了4.76% 的Java用户
     * 	内存消耗:55.4 MB,击败了38.10% 的Java用户
     * @param grid
     * @return
     */
    public List<Integer> goodSubsetofBinaryMatrixMy(int[][] grid) {
        List<Integer> cache = new ArrayList<>();
        int m =  grid.length, n = grid[0].length;
        for (int i = 0 ; i < m ; i++ ){
            int ans = grid[i][n-1]&1;
            int num = 1;
            for (int j = n-2 ; j >=0 ; j--){
                num = num <<1;
                ans += grid[i][j]*num;
            }
            if (ans == 0){
                return Arrays.asList(i);
            }
            if (cache.size() >0){
                for (int index = 0 ; index < cache.size() ; index++ ){
                    if ((ans&cache.get(index)) == 0){
                        return Arrays.asList(index,i);
                    }
                }
            }
            cache.add(ans);
        }
        return  new ArrayList<>();
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了33.33% 的Java用户
     * 	内存消耗:54.6 MB,击败了76.19% 的Java用户
     * @param grid
     * @return
     */
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        List<Integer> ans = new ArrayList<Integer>();
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        int m = grid.length;
        int n = grid[0].length;

        for (int j = 0; j < m; j++) {
            int st = 0;
            for (int i = 0; i < n; i++) {
                st |= (grid[j][i] << i);
            }
            mp.put(st, j);
        }

        if (mp.containsKey(0)) {
            ans.add(mp.get(0));
            return ans;
        }

        for (Map.Entry<Integer, Integer> entry1 : mp.entrySet()) {
            int x = entry1.getKey(), i = entry1.getValue();
            for (Map.Entry<Integer, Integer> entry2 : mp.entrySet()) {
                int y = entry2.getKey(), j = entry2.getValue();
                if ((x & y) == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(Math.min(i, j));
                    list.add(Math.max(i, j));
                    return list;
                }
            }
        }

        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
