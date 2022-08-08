package com.meng.level2.day18;

import java.util.*;

/**
 * 735. 行星碰撞(中等)
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 *
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 *
 *
 * 示例 1：
 *
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 *
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 *
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 *
 *
 * 提示：
 *
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */
public class AsteroidCollision {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 95.68%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 92.97%
     * 的用户
     * 通过测试用例：
     * 275 / 275
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
       List<Integer> list = new ArrayList<>();
       for(int num : asteroids){
           boolean flag = true;
           while (flag &&list.size() >0 && num < 0 && list.get(list.size()-1) >0){
               int index = list.size()-1;
               if (list.get(index) > Math.abs(num)){
                   flag = false;
               }else if (list.get(index) == Math.abs(num)) {
                   list.remove(index);
                   flag = false;
               }else {
                   list.remove(index);
               }
           }
           if (flag){
               list.add(num);
           }
       }
       int [] res = new int[list.size()];
        if (list.size() > 0){
           int index = 0;
           for(int num : list){
               res[index++] = num;
           }
       }
        return res;
    }

    public static void main(String[] args) {
        AsteroidCollision demo = new AsteroidCollision();
        System.out.println(Arrays.toString(demo.asteroidCollision(new int[]{-5,5,10})));
    }
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 96.89%
     * 的用户
     * 内存消耗：
     * 42.7 MB
     * , 在所有 Java 提交中击败了
     * 11.12%
     * 的用户
     * 通过测试用例：
     * 275 / 275
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision1(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int aster : asteroids) {
            boolean alive = true;
            while (alive && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
                alive = stack.peek() < -aster; // aster 是否存在
                if (stack.peek() <= -aster) {  // 栈顶行星爆炸
                    stack.pop();
                }
            }
            if (alive) {
                stack.push(aster);
            }
        }
        int size = stack.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

}
