package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class RotateTheBox1861 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了100.00% 的Java用户
     * 	内存消耗:122.3 MB,击败了33.33% 的Java用户
     * @param boxGrid
     * @return
     */
    public char[][] rotateTheBox1861(char[][] boxGrid) {
        int m = boxGrid.length , n = boxGrid[0].length;
        char[][] res = new char[n][m];
        for(char [] r : res){
            Arrays.fill(r,'.');
        }
        for (int i = 0 ; i < m ; i++){
            int k = n - 1;
            for (int j = n -1 ; j >= 0 ; j--){
                if (boxGrid[i][j] == '*'){//障碍物
                    k = j - 1;
                    res[j][m - 1 - i] = '*';
                }else if(boxGrid[i][j] == '#'){//石头
                    res[k][m - 1 - i] = '#';
                    k--;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了100.00% 的Java用户
     * 	内存消耗:122.3 MB,击败了41.18% 的Java用户
     * @param boxGrid
     * @return
     */
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        char[][] ans = new char[n][m];
        for (char[] row : ans) {
            Arrays.fill(row, '.');
        }

        for (int i = 0; i < m; i++) {
            char[] row = boxGrid[i];
            int k = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (row[j] == '*') { // 障碍物
                    ans[j][m - 1 - i] = '*';
                    k = j - 1; // 障碍物左边最近的石头，在旋转后掉落到 j-1
                } else if (row[j] == '#') { // 石头
                    ans[k][m - 1 - i] = '#'; // 旋转后，石头掉落到 k
                    k--;
                }
            }
        }

        return ans;
    }

}
