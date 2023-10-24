package com.meng.leetcode75;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Stack;

public class AsteroidCollision735 {
    /**
     * 时间
     * 详情
     * 12ms
     * 击败 21.85%使用 Java 的用户
     * 内存
     * 详情
     * 42.05MB
     * 击败 42.43%使用 Java 的用户
     * @param asteroids
     * @return
     */
    public int[] asteroidCollisionMy(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        boolean minZeroFlag = false;
        for(int asteroid : asteroids){
            if (stack.isEmpty()){
                stack.push(asteroid);
                minZeroFlag = asteroid < 0;
            }else {
                //相同方向
                if (Objects.equals(minZeroFlag,asteroid < 0)){
                    stack.push(asteroid);
                }else {//不同方向
                    if (stack.peek() <0 && asteroid > 0){
                        stack.push(asteroid);
                        minZeroFlag = false;
                        continue;
                    }
                    //是否相同标识
                    boolean sameFlag = false;
                    while (!stack.isEmpty()){
                        if (Objects.equals(stack.peek() <0 ,asteroid <0)){
                            stack.push(asteroid);
                            break;
                        }
                       int peek =  Math.abs(stack.peek());
                       int selected = Math.abs(asteroid);
                        if ( peek > selected ){
                            break;
                        }else if (peek == selected){
                            stack.pop();
                            sameFlag = true;
                            break;
                        }else {
                            stack.pop();
                        }
                    }
                    if (stack.isEmpty()&&!sameFlag){
                        stack.push(asteroid);
                        minZeroFlag = asteroid < 0;
                    }
                }
            }
        }
        int[] res = new int[stack.size()];
        for(int i = res.length - 1 ; i >=0 ; i--){
            res[i] = stack.pop();
        }
        return res;
    }

    /**
     *时间
     * 详情
     * 2ms
     * 击败 95.34%使用 Java 的用户
     * 内存
     * 详情
     * 41.94MB
     * 击败 60.54%使用 Java 的用户
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
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
