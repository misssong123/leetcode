package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class Solution {

    /**
     * 广度搜索
     * 1.超过家的位置不能超过a*b
     * @param forbidden
     * @param a
     * @param b
     * @param x
     * @return
     * 时间
     * 详情
     * 79ms
     * 击败 5.56%使用 Java 的用户
     * 内存
     * 详情
     * 43.62MB
     * 击败 5.56%使用 Java 的用户
     */
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        if(x == 0){
            return 0;
        }
        int ans = -1;
        int max = x + 6000;
        //禁止跳跃，1 禁止该位置前跳，2 禁止该位置后跳，3 禁止跳跃
        Set<Integer> forbiddenIndex = new HashSet<>();
        //已经跳跃的位置和方向
        Set<String> used = new HashSet<>();
        for(int num : forbidden){
            forbiddenIndex.add(num);
        }
        forbiddenIndex.add(0);
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();
        list1.add(new int[]{0,1});
        int index = 0;
        while (list1.size()>0){
            list2.clear();
            index++;
            for(int[] nums : list1){
                //可以后退
                int pre = -1;
                int next = -1;
                if (nums[1] == 1){
                    pre = nums[0] - b;
                }
                next = nums[0] + a;
                if (pre == x || next == x){
                    return index;
                }
                if (!used.contains(pre+":"+2) && !forbiddenIndex.contains(pre) && pre >0){
                    list2.add(new int []{pre,2});
                    used.add(pre+":"+2);
                }
                if (!used.contains(next+":"+1) && !forbiddenIndex.contains(next) && next < max){
                    list2.add(new int []{next,1});
                    used.add(next+":"+1);
                }
            }
            list1.clear();
            list1.addAll(list2);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] forbidden = {1998};
        int a = 1999 , b = 2000 ,x = 2000;
        System.out.println(solution.minimumJumps(forbidden,a,b,x));
    }

    /**
     * 时间
     * 详情
     * 24ms
     * 击败 47.78%使用 Java 的用户
     * 内存
     * 详情
     * 41.85MB
     * 击败 16.67%使用 Java 的用户
     * @param forbidden
     * @param a
     * @param b
     * @param x
     * @return
     */
    public int minimumJumps1(int[] forbidden, int a, int b, int x) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        Set<Integer> visited = new HashSet<Integer>();
        queue.offer(new int[]{0, 1, 0});
        visited.add(0);
        int lower = 0, upper = Math.max(Arrays.stream(forbidden).max().getAsInt() + a, x) + b;
        Set<Integer> forbiddenSet = new HashSet<Integer>();
        for (int position : forbidden) {
            forbiddenSet.add(position);
        }
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int position = arr[0], direction = arr[1], step = arr[2];
            if (position == x) {
                return step;
            }
            int nextPosition = position + a;
            int nextDirection = 1;
            if (lower <= nextPosition && nextPosition <= upper && !visited.contains(nextPosition * nextDirection) && !forbiddenSet.contains(nextPosition)) {
                visited.add(nextPosition * nextDirection);
                queue.offer(new int[]{nextPosition, nextDirection, step + 1});
            }
            if (direction == 1) {
                nextPosition = position - b;
                nextDirection = -1;
                if (lower <= nextPosition && nextPosition <= upper && !visited.contains(nextPosition * nextDirection) && !forbiddenSet.contains(nextPosition)) {
                    visited.add(nextPosition * nextDirection);
                    queue.offer(new int[]{nextPosition, nextDirection, step + 1});
                }
            }
        }
        return -1;
    }
}

