package com.meng.hot100.leetcode.editor.cn;

import java.util.Stack;

class T42LargestRectangleArea {
    /**
     * 解答成功:
     * 	执行耗时:321 ms,击败了5.02% 的Java用户
     * 	内存消耗:53.8 MB,击败了97.44% 的Java用户
     * @param heights
     * @return
     */
    public int largestRectangleAreaMy(int[] heights) {
        int max = 0;
        int len = heights.length;
        for(int i = 0 ; i < len ; i++){
            if (i > 0 && heights[i] == heights[i-1]){
                continue;
            }
            //以i为高度，查找最大的矩形
            int l = i ,r = i;
            while (l -1 >=0 && heights[l-1] >= heights[i]){
                l--;
            }
            while (r +1 < len && heights[r+1] >= heights[i]){
                r++;
            }
            max = Math.max(max,(r-l+1)*heights[i]);
        }
        return max;
    }

    /**
     * 解答成功:
     * 	执行耗时:174 ms,击败了26.66% 的Java用户
     * 	内存消耗:56.4 MB,击败了47.73% 的Java用户
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> queue = new Stack<>();
        int max = heights[0],len = heights.length;
        queue.add(0);
        for(int i = 1; i < len ; i++){
            while(!queue.isEmpty()&&heights[queue.peek()] > heights[i]){
                Integer preIndex = queue.pop();
                int l = -1;
                if(!queue.isEmpty()){
                    l = queue.peek();
                }
                max = Math.max((i - l-1)*heights[preIndex],max);
            }
            queue.add(i);
        }
        while (!queue.isEmpty()){
            Integer preIndex = queue.pop();
            if (queue.isEmpty()){
                max = Math.max(max,len*heights[preIndex]);
            }else {
                max = Math.max(max,(len - queue.peek()-1)*heights[preIndex]);
            }
        }
        return max;
    }
}
