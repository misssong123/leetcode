package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GetFactors254 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp;
    Map<Integer,List<Integer>> cache;

    /**
     * 存在重复的情况
     * @param n
     * @return
     */
    public List<List<Integer>> getFactors254(int n) {
        if (n <= 3){
            return res;
        }
        temp = new ArrayList<>();
        cache = new HashMap<>();
        dfs(n,2);
        return res;
    }

    private void dfs(int n,int start) {
        if (!cache.containsKey(n)){
            cache.put(n,new ArrayList<>());
        }
        for(int i = start ; i <= n /2 ; i++){
            if(n % i == 0){
                if(cache.get(n).contains(i)){
                    continue;
                }
                temp.add(i);
                cache.get(n).add(i);
                int j = n / i;
                cache.get(n).add(j);
                temp.add(j);
                res.add(new ArrayList<>(temp));
                temp.remove(temp.size()-1);
                dfs(j,i);
                temp.remove(temp.size()-1);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.5 MB,击败了35.53% 的Java用户
     * @param n
     * @return
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> factorsList = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        getFactors(n, 2, factorsList, temp);
        return factorsList;
    }

    public void getFactors(int num, int start, List<List<Integer>> factorsList, List<Integer> temp) {
        int size = temp.size();
        if (num == 1) {
            if (size > 1) {
                List<Integer> list = new ArrayList<Integer>(temp);
                factorsList.add(list);
            }
        } else {
            int sqrt = (int) Math.sqrt(num);
            for (int i = start; i <= sqrt; i++) {
                if (num % i == 0) {
                    temp.add(i);
                    //存放因子元素过滤
                    getFactors(num / i, i, factorsList, temp);
                    temp.remove(size);
                }
            }
            //存放当前元素
            temp.add(num);
            getFactors(1, num, factorsList, temp);
            temp.remove(size);
        }
    }

}
