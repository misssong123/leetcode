package com.meng.oneday.leetcode.editor.cn;

class FurthestDistanceFromOrigin2833 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了93.75% 的Java用户
     * @param moves
     * @return
     */
    public int furthestDistanceFromOrigin2833(String moves) {
        int dis = 0,medium = 0;
        for(char c : moves.toCharArray()){
            if (c == 'R'){
                dis--;
            }else if (c == 'L'){
                dis++;
            }else{
                medium++;
            }
        }
        return Math.abs(dis)+medium;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.3 MB,击败了45.83% 的Java用户
     * @param moves
     * @return
     */
    public int furthestDistanceFromOrigin(String moves) {
        int cntR = 0;
        int cntL = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'R') {
                cntR++;
            } else if (c == 'L') {
                cntL++;
            }
        }
        return Math.abs(cntR - cntL) + moves.length() - cntR - cntL;
    }
}
