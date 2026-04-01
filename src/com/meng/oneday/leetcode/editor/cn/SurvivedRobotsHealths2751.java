package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class SurvivedRobotsHealths2751 {
    /**
     * 解答成功:
     * 	执行耗时:154 ms,击败了5.71% 的Java用户
     * 	内存消耗:123.6 MB,击败了25.72% 的Java用户
     * @param positions
     * @param healths
     * @param directions
     * @return
     */
    public List<Integer> survivedRobotsHealths2751(int[] positions, int[] healths, String directions) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[0] - b[0]);
        int len = positions.length;
        for (int i = 0; i < len; i++) {
            pq.offer(new int[]{positions[i], healths[i], i, directions.charAt(i) == 'L' ? 0 : 1});
        }
        Stack<int[]> rStack = new Stack<>();
        List<int[]> lived = new ArrayList<>();
        while (!pq.isEmpty()){
            int[] poll = pq.poll();
            //向左
            if(poll[3] == 0){
                if(rStack.isEmpty()){
                    lived.add(poll);
                }else{
                    while (!rStack.isEmpty() && rStack.peek()[1] <= poll[1]){
                        poll[1] -= (rStack.pop()[1] == poll[1] ? poll[1] : 1);
                    }
                    if (poll[1] > 0){
                        if (rStack.isEmpty()){
                            lived.add(poll);
                        }else{
                            rStack.peek()[1] --;
                        }
                    }
                }
            }else{
                rStack.add(poll);
            }
        }
        while (!rStack.isEmpty()){
            lived.add(rStack.pop());
        }
        lived.sort((Comparator.comparingInt(o -> o[2])));
        List<Integer> res = new ArrayList<>();
        for (int[] ints : lived) {
            res.add(ints[1]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:27 ms,击败了94.29% 的Java用户
     * 	内存消耗:113.7 MB,击败了57.14% 的Java用户
     * @param positions
     * @param healths
     * @param directions
     * @return
     */
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        // 创建一个下标数组，对下标数组排序，这样不会打乱输入顺序
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> positions[i] - positions[j]);

        int[] st = new int[n];
        int top = -1;
        for (int i : idx) {
            if (directions.charAt(i) == 'R') { // 机器人 i 向右
                st[++top] = i;
                continue;
            }
            while (top >= 0) { // 栈顶机器人向右
                int j = st[top];
                if (healths[j] > healths[i]) { // 栈顶机器人的健康度大
                    healths[i] = 0; // 移除机器人 i
                    healths[j]--;
                    break;
                }
                if (healths[j] == healths[i]) { // 健康度一样大，都移除
                    healths[i] = 0;
                    healths[j] = 0;
                    top--;
                    break;
                }
                // 机器人 i 的健康度大
                healths[i]--;
                healths[j] = 0; // 移除机器人 j
                top--;
            }
        }

        // 返回幸存机器人的健康度
        List<Integer> ans = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) {
                ans.add(h);
            }
        }
        return ans;
    }

}
