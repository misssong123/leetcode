
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.94% 的Java用户
     * 	内存消耗:48.8 MB,击败了63.09% 的Java用户
     * @param grid
     * @return
     */
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 1){
            return grid[0][0];
        }
        int firstMin = Integer.MAX_VALUE ,firstIndex = -1 ,secondMin = Integer.MAX_VALUE;
        //构建第一层结果
        for(int i = 0 ; i < n ; i++){
            if (grid[0][i] < firstMin){
                secondMin = firstMin;
                firstMin = grid[0][i];
                firstIndex = i;
            }else if (grid[0][i] < secondMin){
                secondMin = grid[0][i];
            }
        }
        for(int i = 1 ; i < n ; i++){
            int tempFirstMin = Integer.MAX_VALUE ,tempFirstIndex = -1 ,tempSecondMin = Integer.MAX_VALUE;
            for(int j = 0 ; j< n ; j++ ){
                int temp  = grid[i][j] + (j == firstIndex?secondMin:firstMin);
                if (temp < tempFirstMin){
                    tempSecondMin = tempFirstMin;
                    tempFirstMin = temp;
                    tempFirstIndex = j;
                }else if (temp < tempSecondMin){
                    tempSecondMin = temp;
                }
            }
            firstIndex = tempFirstIndex;
            firstMin = tempFirstMin;
            secondMin = tempSecondMin;
        }
        return Math.min(firstMin,secondMin);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
