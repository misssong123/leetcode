package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NumberOfPairs3027 {
    /**
     * 解答成功:
     * 	执行耗时:189 ms,击败了11.32% 的Java用户
     * 	内存消耗:44.2 MB,击败了81.13% 的Java用户
     * @param points
     * @return
     */
    public int numberOfPairs3027(int[][] points) {
        Arrays.sort(points,(a, b)->{
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        int res = 0;
        List<List<int[]>> list = new ArrayList<>();
        int level = 1;
        for(int[] point : points){
            //存放当前元素
            if (list.size() < level || list.get(level - 1).get(0)[1] != point[1]){
                list.add(new ArrayList<>());
                level = list.size();
            }
            list.get(level - 1).add(point);
            if (list.get(level-1).size() > 1){
                res++;
            }
            //判断是否存在符合条件的数据
            if(level > 1){
                int preLevel = level - 2;
                int size = list.get(level-1).size();
                int limit = size > 1 ? list.get(level-1).get(size-2)[0] : Integer.MIN_VALUE;
                boolean flag = true;
                while (preLevel >= 0&&flag){
                    int preSize = list.get(preLevel).size();
                    for(int i = preSize - 1; i >= 0; i--){
                        int[] cur = list.get(preLevel).get(i);
                        //同一条线
                        if (cur[0] == point[0]){
                            res++;
                            flag = false;
                            break;
                        }else if(cur[0] <= limit){
                            break;
                        }else if (cur[0] > point[0]){
                            continue;
                        }else {
                            res++;
                            limit = cur[0];
                            break;
                        }
                    }
                    preLevel--;
                }
            }
        }
        return res;
    }
}
