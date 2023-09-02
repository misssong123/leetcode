package com.meng.route.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class SolutionCountRoutes1575 {
    /**
     * dfs
     * @param locations
     * @param start
     * @param finish
     * @param fuel
     * @return
     */
    long ans = 0;
    int num = 1000000007;
    Map<String,Long> cache = new HashMap<>();

    /**
     * 解答成功:
     * 	执行耗时:1472 ms,击败了5.18% 的Java用户
     * 	内存消耗:49.4 MB,击败了5.18% 的Java用户
     * @param locations
     * @param start
     * @param finish
     * @param fuel
     * @return
     */
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        if (Math.abs(locations[finish] - locations[start])>fuel){
            return 0;
        }
        ans = dfs(locations,start,finish,fuel);
        return (int)ans % num;
    }
    private long dfs(int[] locations, int start, int finish, int fuel) {
        if (cache.containsKey(start+"-"+fuel)){
            return cache.get(start+"-"+fuel);
        }
        if (start == finish){
            long cur = 1;
            if (fuel >0){
                for(int i = 0 ; i < locations.length ; i++){
                    if (i == start || (Math.abs(locations[i] - locations[start])) > fuel){
                        continue;
                    }
                    cur += dfs(locations,i,finish,fuel-(Math.abs(locations[i] - locations[start])));
                    cur %= num;
                }
            }
            return cur;
        }
        long temp = 0;
        for(int i = 0 ; i < locations.length ; i++){
            if (i == start || (Math.abs(locations[i] - locations[start])) > fuel){
                continue;
            }
            temp += dfs(locations,i,finish,fuel-(Math.abs(locations[i] - locations[start])));
            temp %= num;
        }
        cache.put(start+"-"+fuel,temp);
        return temp;
    }
}
