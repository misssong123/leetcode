package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class FindMaximumElegance2813 {
    /**
     * 解答成功:
     * 	执行耗时:797 ms,击败了5.08% 的Java用户
     * 	内存消耗:102.2 MB,击败了5.09% 的Java用户
     * @param items
     * @param k
     * @return
     */
    public long findMaximumEleganceMy(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> {
            if (b[0]!=a[0]){
               return b[0] - a[0];
            }
           return  b[1] - a[1];
        });
        Map<Integer, List<Integer>> cache = new HashMap<>();
        Set<Integer> allKey =  new HashSet<>();
        //存储不同类别的价格
        for(int[] item : items){
            if (!cache.containsKey(item[1])){
                cache.put(item[1], new ArrayList<>());
            }
            cache.get(item[1]).add(item[0]);
            allKey.add(item[1]);
        }
        int keySize = cache.size();
        long ans = 0;
        Map<Integer,List<Integer>> temp = new HashMap<>();
        for(int i = 0 ; i < k ;i++){
            int[] item = items[i];
            if (!temp.containsKey(item[1])){
                temp.put(item[1], new ArrayList<>());
            }
            temp.get(item[1]).add(item[0]);
            ans+=item[0];
            allKey.remove(item[1]);
        }
        long pre = ans;
        ans+=(long)temp.size()*temp.size();
        while (temp.size()<keySize &&temp.size()<k){
            //移除当前已保留的最小值
            int deleteVal = Integer.MAX_VALUE;
            int deleteKey = -1;
            for (Map.Entry<Integer,List<Integer>> entry : temp.entrySet()){
                List<Integer> value = entry.getValue();
                if (value.size()>1&&value.get(value.size()-1)<deleteVal){
                    deleteKey =  entry.getKey();
                    deleteVal = value.get(value.size()-1);
                }
            }
            pre-=deleteVal;
            temp.get(deleteKey).remove(temp.get(deleteKey).size()-1);
            //添加未保存key的最大值
            int addKey = -1;
            int addVal = Integer.MIN_VALUE;
            for(Integer key :allKey){
                if (cache.get(key).get(0)>addVal){
                    addKey = key;
                    addVal = cache.get(key).get(0);
                }
            }
            pre += addVal;
            temp.put(addKey,new ArrayList<>());
            temp.get(addKey).add(addVal);
            allKey.remove(addKey);
            //
            ans = Math.max(ans,pre+(long)temp.size()*temp.size());
        }
        return ans;
    }

    /**
     *解答成功:
     * 	执行耗时:47 ms,击败了94.35% 的Java用户
     * 	内存消耗:82.9 MB,击败了42.92% 的Java用户
     * @param items
     * @param k
     * @return
     */
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (item0, item1) -> item1[0] - item0[0]);
        Set<Integer> categorySet = new HashSet<Integer>();
        long profit = 0, res = 0;
        Deque<Integer> st = new ArrayDeque<Integer>();
        for (int i = 0; i < items.length; i++) {
            if (i < k) {
                profit += items[i][0];
                if (!categorySet.add(items[i][1])) {
                    st.push(items[i][0]);
                }
            } else if (!st.isEmpty() && categorySet.add(items[i][1])) {
                profit += items[i][0] - st.pop();
            }
            res = Math.max(res, profit + (long)categorySet.size() * categorySet.size());
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
