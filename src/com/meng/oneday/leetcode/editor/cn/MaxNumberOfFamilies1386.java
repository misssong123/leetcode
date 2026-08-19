package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class MaxNumberOfFamilies1386 {
    /**
     * 内存受限
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamiliesMemoryLimit(int n, int[][] reservedSeats) {
        //初始化
        boolean[][] canAssign = new boolean[n][3];
        for (boolean[] flags : canAssign) {
            Arrays.fill(flags, true);
        }
        //计算占用位置
        for (int[] seat : reservedSeats) {
            int row = seat[0] - 1;
            int col = seat[1];
            if (col >= 2 && col <= 5) {
                canAssign[row][0] = false;
            }
            if (col >= 4 && col <= 7) {
                canAssign[row][1] = false;
            }
            if (col >= 6 && col <= 9) {
                canAssign[row][2] = false;
            }
        }
        //计算可分配结果
        int res = 0;
        for (boolean[] flags : canAssign) {
            if (flags[0] && flags[1] && flags[2]) {
                res += 2;
            }else if (flags[0] || flags[1] || flags[2]) {
                res += 1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了13.64% 的Java用户
     * 	内存消耗:50.7 MB,击败了68.18% 的Java用户
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamilies1386(int n, int[][] reservedSeats) {
        //排序
        Arrays.sort(reservedSeats, Comparator.comparingInt(a -> a[0]));
        int res = -2 ;
        int pre = 0;
        boolean[] flags = new boolean[3];
        Arrays.fill(flags, true);
        for (int[] seat : reservedSeats) {
            if (seat[0] != pre) {
                //上一行情况
                if (flags[0] && flags[1] && flags[2]) {
                    res += 2;
                }else if (flags[0] || flags[1] || flags[2]) {
                    res += 1;
                }
                //当前行的间隔
                res += (seat[0] - pre - 1) * 2;
                Arrays.fill(flags, true);
                pre = seat[0];
            }
            //计算占用情况
            int col = seat[1];
            if (col >= 2 && col <= 5) {
                flags[0] = false;
            }
            if (col >= 4 && col <= 7) {
                flags[1] = false;
            }
            if (col >= 6 && col <= 9) {
                flags[2] = false;
            }
        }
        //计算最后一行
        if (flags[0] && flags[1] && flags[2]) {
            res += 2;
        }else if (flags[0] || flags[1] || flags[2]) {
            res += 1;
        }
        res += (n - pre) * 2;
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了100.00% 的Java用户
     * 	内存消耗:51.5 MB,击败了45.45% 的Java用户
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> seats = new HashMap<>(); // 2~9 有预定座位的行 -> 这一行具体哪些座位被预定
        for (int[] r : reservedSeats) {
            int seat = r[1];
            if (2 <= seat && seat <= 9) {
                seats.merge(r[0], 1 << (seat - 2), (a, b) -> a | b); // 把二进制数的 seat-2 这一位变成 1
            }
        }

        // 注意：如果某一行只有 1 和 10 被预定，那么这一行不会插到哈希表中（相当于这一行是空的）
        // 示例 1 只有第 1 行和第 2 行插到哈希表中
        int emptyRows = n - seats.size();
        int ans = emptyRows * 2; // 一个空行可以容量 2 个四人小组
        for (int x : seats.values()) {
            // 在哈希表中的行，由于 2~9 至少一个座位被预定，所以至多容纳 1 个四人小组，ans 至多增加 1
            if ((x & 0b1111) == 0 || (x & 0b111100) == 0 || (x & 0b11110000) == 0) {
                ans++;
            }
        }
        return ans;
    }

}
