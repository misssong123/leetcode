package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumFuelCost2477 {
    long res = 0;

    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了94.17% 的Java用户
     * 	内存消耗:83.4 MB,击败了89.32% 的Java用户
     * @param roads
     * @param seats
     * @return
     */
    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length;
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] e : roads) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        dfs(0, -1, seats, g);
        return res;
    }

    public int dfs(int cur, int fa, int seats, List<Integer>[] g) {
        int peopleSum = 1;
        for (int ne : g[cur]) {
            if (ne != fa) {
                int peopleCnt = dfs(ne, cur, seats, g);
                peopleSum += peopleCnt;
                res += (peopleCnt + seats - 1) / seats;
            }
        }
        return peopleSum;
    }
    /**
     * 时间
     * 详情
     * 78ms
     * 击败 13.27%使用 Java 的用户
     * 内存
     * 详情
     * 86.95MB
     * 击败 50.71%使用 Java 的用户
     * @param roads
     * @param seats
     * @return
     */
    public long minimumFuelCostMy(int[][] roads, int seats) {
        int n = roads.length+1;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : roads) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        List<List<Integer>> cache = new ArrayList<>();
        int index = -1;
        int num = 1;
        boolean[] flags = new boolean[n];
        //计算相关分层
        Map<Integer,Integer> indexMap = new HashMap<>();
        while (num < n){
            List<Integer> temp;
            if (index == -1){
                temp = new ArrayList<>(g[0]);
                flags[0] =true;
            }else {
                temp = new ArrayList<>();
                for(int x : cache.get(index)){
                    flags[x] =true;
                    for(int m : g[x]){
                        if (!flags[m]){
                            flags[m] =true;
                            temp.add(m);
                            indexMap.put(m,x);
                        }
                    }
                }
            }
            cache.add(temp);
            num+=temp.size();
            index++;
        }
        long res = 0;
        int[] nums = new int[n];
        Arrays.fill(nums,1);
        for (int i = cache.size()-1;i>=0; i--){
            List<Integer> list = cache.get(i);
            for(int m : list){
                res +=(nums[m] + seats -1)/seats;
                if (i!=0){
                    nums[indexMap.get(m)]+=nums[m];
                }
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
