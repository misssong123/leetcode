package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;
import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class MinRectanglesToCoverPoints3111 {
    /**
     * 解答成功:
     * 	执行耗时:40 ms,击败了5.01% 的Java用户
     * 	内存消耗:79.2 MB,击败了95.51% 的Java用户
     * @param points
     * @param w
     * @return
     */
    public int minRectanglesToCoverPointsMy(int[][] points, int w) {
        TreeSet<Integer>  set = new TreeSet<>();
        for (int[] point : points){
            set.add(point[0]);
        }
        if (w == 0){
            return  set.size();
        }
        int first = set.first();
        int res = 1;
        for(int num : set){
            if(num - first > w){
                first = num;
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了59.10% 的Java用户
     * 	内存消耗:95.2 MB,击败了46.17% 的Java用户
     * @param points
     * @param w
     * @return
     */
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int res = 0;
        int bound = -1;
        for (int[] p : points) {
            if (p[0] > bound) {
                bound = p[0] + w;
                res++;
            }
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
