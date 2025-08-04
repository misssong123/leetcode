package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class TotalFruit904 {
    public int totalFruit904(int[] fruits) {
        int len = fruits.length;
        if (len < 3) {
            return len;
        }
        int res = 0;
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i < len ; i++){
            int fruit = fruits[i];
            if (cache.size() < 2 || cache.containsKey(fruit)){
                cache.put(fruit,cache.getOrDefault(fruit,0)+1);
            }else {
                int temp = 0;
                for(int val : cache.values()){
                    temp += val;
                }
                res = Math.max(res,temp);
                //删除最左侧的元素
                int index = i-1;
                int val = 0;
                while (index >=0 && fruits[index] == fruits[i-1]){
                    val++;
                    index--;
                }
                cache.clear();
                cache.put(fruits[i-1],val);
                cache.put(fruit,1);
            }
        }
        int temp = 0;
        for(int val : cache.values()){
            temp += val;
        }
        res = Math.max(res,temp);
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:45 ms,击败了69.77% 的Java用户
     * 	内存消耗:54 MB,击败了43.82% 的Java用户
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        int ans = 0;
        int left = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int right = 0; right < fruits.length; right++) {
            cnt.merge(fruits[right], 1, Integer::sum); // fruits[right] 进入窗口
            while (cnt.size() > 2) { // 不满足要求
                int out = fruits[left];
                cnt.merge(out, -1, Integer::sum); // fruits[left] 离开窗口
                if (cnt.get(out) == 0) {
                    cnt.remove(out);
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
