package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class GetGoodIndices2961 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了21.40% 的Java用户
     * 	内存消耗:43.6 MB,击败了71.91% 的Java用户
     * @param variables
     * @param target
     * @return
     */
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> res = new ArrayList<>();
        int index = 0;
        for (int var[] : variables){
            if(powModMy(powMod(var[0],var[1],10),var[2],var[3])==target){
                res.add(index);
            }
            index++;
        }
        return res;
    }
    public int powModMy(int x, int y, int mod) {
        int res = 1;
        while (y != 0) {
            res = res * x % mod;
            y--;
        }
        return res;
    }
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了37.82% 的Java用户
     * @param variables
     * @param target
     * @return
     */
    public List<Integer> getGoodIndicesOfficial(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < variables.length; i++) {
            int[] v = variables[i];
            if (powMod(powMod(v[0], v[1], 10), v[2], v[3]) == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    public int powMod(int x, int y, int mod) {
        int res = 1;
        while (y != 0) {
            if ((y & 1) != 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
            y >>= 1;
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
