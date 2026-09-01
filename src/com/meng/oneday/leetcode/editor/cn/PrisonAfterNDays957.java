package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class PrisonAfterNDays957 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了51.52% 的Java用户
     * 	内存消耗:43.3 MB,击败了90.91% 的Java用户
     * @param cells
     * @param n
     * @return
     */
    public int[] prisonAfterNDays957(int[] cells, int n) {
        int day = 0;
        Map<Integer,Integer> map = new HashMap<>();
        List<int[]> cellList = new ArrayList<>();
        map.put(getNum(cells),day);
        cellList.add(cells);
        while (day < n){
            cells = nextDay(cells);
            day++;
            int num = getNum(cells);
            if (map.containsKey(num)){
                int start = map.get(num);
                int cycle = day - start;
                return cellList.get(start + (n - map.get(num)) % cycle);
            }
            map.put(num,day);
            cellList.add(cells);
        }
        return cells;
    }
    public int[] nextDay(int[] cells) {
        int[] res = new int[cells.length];
        res[0]  = 0;
        res[cells.length-1] = 0;
        for (int i = 1; i < cells.length-1; i++) {
            res[i] = cells[i-1] == cells[i+1] ? 1 : 0;
        }
        return res;
    }
    //获取对应的数字
    public int getNum(int[] cells){
        int res = 0;
        int step = 1;
        for (int cell : cells) {
            res += cell * step;
            step *= 2;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了51.52% 的Java用户
     * 	内存消耗:43.5 MB,击败了75.76% 的Java用户
     * @param cells
     * @param n
     * @return
     */
    public int[] prisonAfterNDaysAi(int[] cells, int n) {
        int day = 0;
        Map<Integer, Integer> map = new HashMap<>();
        List<int[]> cellList = new ArrayList<>();

        map.put(getNum(cells), day);
        cellList.add(cells);

        while (day < n) {
            cells = nextDay(cells);
            day++;
            int num = getNum(cells);

            if (map.containsKey(num)) {
                int start = map.get(num);       // 第一次遇到该状态的天数
                int cycle = day - start;        // 周期长度
                int targetIndex = start + (n - start) % cycle; // 一行统一计算目标索引
                return cellList.get(targetIndex);
            }

            map.put(num, day);
            cellList.add(cells);
        }

        return cells;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了72.73% 的Java用户
     * 	内存消耗:43.6 MB,击败了63.64% 的Java用户
     * @param cells
     * @param N
     * @return
     */
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> seen = new HashMap();

        // state  = integer representing state of prison
        int state = 0;
        for (int i = 0; i < 8; ++i) {
            if (cells[i] > 0)
                state ^= 1 << i;
        }

        // While days remaining, simulate a day
        while (N > 0) {
            // If this is a cycle, fast forward by
            // seen.get(state) - N, the period of the cycle.
            if (seen.containsKey(state)) {
                N %= seen.get(state) - N;
            }
            seen.put(state, N);

            if (N >= 1) {
                N--;
                state = nextDay(state);
            }
        }

        // Convert the state back to the required answer.
        int[] ans = new int[8];
        for (int i = 0; i < 8; ++i) {
            if (((state >> i) & 1) > 0) {
                ans[i] = 1;
            }
        }

        return ans;
    }

    public int nextDay(int state) {
        int ans = 0;
        for (int i = 1; i <= 6; ++i) {
            if (((state >> (i-1)) & 1) == ((state >> (i+1)) & 1)) {
                ans ^= 1 << i;
            }
        }

        return ans;
    }

}
