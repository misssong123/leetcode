package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class RobotSim874 {
    /**
     * 执行用时：
     * 23 ms
     * , 在所有 Java 提交中击败了
     * 59.57%
     * 的用户
     * 内存消耗：
     * 53.9 MB
     * , 在所有 Java 提交中击败了
     * 5.11%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, Set<Integer>> xCache = new HashMap<>();
        Map<Integer, Set<Integer>> yCache = new HashMap<>();
        //存储障碍
        for(int [] obstacle : obstacles){
            xCache.putIfAbsent(obstacle[0],new HashSet<>());
            xCache.get(obstacle[0]).add(obstacle[1]);
            yCache.putIfAbsent(obstacle[1],new HashSet<>());
            yCache.get(obstacle[1]).add(obstacle[0]);
        }
        int [][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
        int index = 0;
        int x = 0 , y = 0,res =0;
        for(int command : commands){
            boolean flag = (index % 2 == 0);
            if (command >= 0){
                for(int i = 1 ; i <= command ; i++){
                    x += direction[index][0];
                    y += direction[index][1];
                    if (flag){//沿着y轴运动
                        if (yCache.get(y) != null && yCache.get(y).contains(x)){
                            y -= direction[index][1];
                            break;
                        }
                    }else {//沿着x轴运动
                        if (xCache.get(x) != null && xCache.get(x).contains(y)){
                            x -= direction[index][0];
                            break;
                        }
                    }
                }
                res = Math.max(res,x * x + y * y);
            }else if (command == -1){
                index = (index + 1) % 4;
            }else {
                index = (index + 3) % 4;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] commands = {4,-1,4,-2,4};
        int[][] obstacles = {{2,4}};
        RobotSim874 demo = new RobotSim874();
        System.out.println(demo.robotSim(commands,obstacles));
    }

    /**
     * 执行用时：
     * 13 ms
     * , 在所有 Java 提交中击败了
     * 80.43%
     * 的用户
     * 内存消耗：
     * 47.1 MB
     * , 在所有 Java 提交中击败了
     * 81.28%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim1(int[] commands, int[][] obstacles) {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int px = 0, py = 0, d = 1;
        Set<Integer> set = new HashSet<Integer>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] * 60001 + obstacle[1]);
        }
        int res = 0;
        for (int c : commands) {
            if (c < 0) {
                d += c == -1 ? 1 : -1;
                d %= 4;
                if (d < 0) {
                    d += 4;
                }
            } else {
                for (int i = 0; i < c; i++) {
                    if (set.contains((px + dirs[d][0]) * 60001 + py + dirs[d][1])) {
                        break;
                    }
                    px += dirs[d][0];
                    py += dirs[d][1];
                }
                res = Math.max(res, px * px + py * py);
            }
        }
        return res;
    }

}

