package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionQueensAttackTheKing1222 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.4 MB,击败了98.72% 的Java用户
     * @param queens
     * @param king
     * @return
     */
    public List<List<Integer>> queensAttacktheKing1(int[][] queens, int[] king) {
        int[][] cache = new int[8][2];
        for(int i = 0 ; i < queens.length ; i++){
            int[] index = getIndex(queens[i], king);
            if (index.length == 0){
                continue;
            }
            if (cache[index[0]][1]==0 || (cache[index[0]][1] > index[1])){
                cache[index[0]][0] = i;
                cache[index[0]][1] = index[1];
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int[] num : cache){
            if (num[1] !=0){
                List<Integer> list = new ArrayList<>();
                list.add(queens[num[0]][0]);
                list.add(queens[num[0]][1]);
                res.add(list);
            }
        }
        return res;
    }
    private int[] getIndex(int[] queen,int[] king){
        int x = queen[0] - king[0];
        int y = queen[1] - king[1];
        if (x == 0){
            //同一行,左
            //同一行,右
            return new int[]{y<0?0:1,Math.abs(y)};
        }else if (y == 0){
            //同一列,上
            //同一列,下
            return new int[]{x<0?2:3,Math.abs(x)};
        }else if (x == y){
            //右对角线,上
            //右对角线,下
            return new int[]{x<0?4:5,Math.abs(x)};
        }else if (x == -y){
            //左对角线,上
            //左对角线,下
            return new int[]{x<0?6:7,Math.abs(x)};
        }else {
            return new int[0];
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了78.63% 的Java用户
     * 	内存消耗:40.7 MB,击败了63.25% 的Java用户
     * @param queens
     * @param king
     * @return
     */
    public List<List<Integer>> queensAttacktheKing2(int[][] queens, int[] king) {
        Set<Integer> queenPos = new HashSet<Integer>();
        for (int[] queen : queens) {
            int x = queen[0], y = queen[1];
            queenPos.add(x * 8 + y);
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int kx = king[0] + dx, ky = king[1] + dy;
                while (kx >= 0 && kx < 8 && ky >= 0 && ky < 8) {
                    int pos = kx * 8 + ky;
                    if (queenPos.contains(pos)) {
                        List<Integer> posList = new ArrayList<Integer>();
                        posList.add(kx);
                        posList.add(ky);
                        ans.add(posList);
                        break;
                    }
                    kx += dx;
                    ky += dy;
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了78.63% 的Java用户
     * 	内存消耗:40.7 MB,击败了74.36% 的Java用户
     * @param queens
     * @param king
     * @return
     */
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Map<Integer, int[]> candidates = new HashMap<Integer, int[]>();
        int kx = king[0], ky = king[1];
        for (int[] queen : queens) {
            int qx = queen[0], qy = queen[1];
            int x = qx - kx, y = qy - ky;
            if (x == 0 || y == 0 || Math.abs(x) == Math.abs(y)) {
                int dx = sgn(x), dy = sgn(y);
                int key = dx * 10 + dy;
                if (!candidates.containsKey(key) || candidates.get(key)[2] > Math.abs(x) + Math.abs(y)) {
                    candidates.put(key, new int[]{queen[0], queen[1], Math.abs(x) + Math.abs(y)});
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (Map.Entry<Integer, int[]> entry : candidates.entrySet()) {
            int[] value = entry.getValue();
            List<Integer> posList = new ArrayList<Integer>();
            posList.add(value[0]);
            posList.add(value[1]);
            ans.add(posList);
        }
        return ans;
    }

    public int sgn(int x) {
        return x > 0 ? 1 : (x == 0 ? 0 : -1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
