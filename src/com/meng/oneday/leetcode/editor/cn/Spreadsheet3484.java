package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 解答成功:
 * 	执行耗时:129 ms,击败了27.59% 的Java用户
 * 	内存消耗:54.8 MB,击败了68.97% 的Java用户
 */
class Spreadsheet3484 {
    int[][] nums;
    public Spreadsheet3484(int rows) {
        nums = new int[26][rows];
    }
    public void setCell(String cell, int value) {
        int row = cell.charAt(0) - 'A';
        int col = Integer.parseInt(cell.substring(1)) - 1;
        nums[row][col] = value;
    }
    
    public void resetCell(String cell) {
        int row = cell.charAt(0) - 'A';
        int col = Integer.parseInt(cell.substring(1)) - 1;
        nums[row][col] = 0;
    }

    public int getValue(String formula) {
        formula = formula.substring(1);
        String[] strs = formula.split("\\+");
        int sum = 0;
        for (String str : strs) {
            if (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z') {
                int row = str.charAt(0) - 'A';
                int col = Integer.parseInt(str.substring(1)) - 1;
                sum += nums[row][col];
            }else {
                sum += Integer.parseInt(str);
            }
        }
        return sum;
    }
}

/**
 * 解答成功:
 * 	执行耗时:128 ms,击败了27.59% 的Java用户
 * 	内存消耗:55.3 MB,击败了13.79% 的Java用户
 */
class Spreadsheet {
    private final Map<String, Integer> data = new HashMap<>();

    public Spreadsheet(int rows) {
    }

    public void setCell(String cell, int value) {
        data.put(cell, value);
    }

    public void resetCell(String cell) {
        data.remove(cell);
    }

    public int getValue(String formula) {
        int ans = 0;
        for (String cell : formula.substring(1).split("\\+")) {
            if (Character.isUpperCase(cell.charAt(0))) {
                ans += data.getOrDefault(cell, 0);
            } else {
                ans += Integer.parseInt(cell);
            }
        }
        return ans;
    }
}
