package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class SolutionAvoidFlood1488 {
    /**
     * 解答成功:
     * 	执行耗时:89 ms,击败了33.33% 的Java用户
     * 	内存消耗:71.3 MB,击败了17.24% 的Java用户
     * @param rains
     * @return
     */
    public int[] avoidFlood(int[] rains) {
        int [] res = new int[rains.length];
        Map<Integer, Queue<Integer>> cache = new HashMap<>();
        Set<Integer> used = new HashSet<>();
        PriorityQueue<Integer> nextPollIndex = new PriorityQueue<>(Integer::compareTo);
        //计算每个湖泊下雨的天数
        for(int i = 0 ; i < rains.length ; i++){
            cache.computeIfAbsent(rains[i], k -> new LinkedList<>());
            cache.get(rains[i]).add(i);
        }
        //弹出第一个位置
        for(Queue<Integer> value : cache.values()){
            value.poll();
        }
        int index = 0;
        for(int num : rains){
            if (num == 0){
                res[index] = 1;
                if (!nextPollIndex.isEmpty()){
                    //从已存满水的湖泊中找到下一个下雨的湖泊
                    Integer nextIndex = nextPollIndex.poll();
                    used.remove(rains[nextIndex]);
                    res[index] = rains[nextIndex];
                }
            }else {
                if (!used.add(num)){
                    return new int[0];
                }
                res[index] = -1;
                //移除已经下过雨的天数
                if (!cache.get(num).isEmpty()){
                    nextPollIndex.add(cache.get(num).poll());
                }
            }
            index++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:61 ms,击败了67.82% 的Java用户
     * 	内存消耗:57.4 MB,击败了93.10% 的Java用户
     * @param rains
     * @return
     */
    public int[] avoidFlood1(int[] rains) {
        int[] ans = new int[rains.length];
        Arrays.fill(ans, 1);
        TreeSet<Integer> st = new TreeSet<Integer>();
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        for (int i = 0; i < rains.length; ++i) {
            if (rains[i] == 0) {
                st.add(i);
            } else {
                ans[i] = -1;
                if (mp.containsKey(rains[i])) {
                    Integer it = st.ceiling(mp.get(rains[i]));
                    if (it == null) {
                        return new int[0];
                    }
                    ans[it] = rains[i];
                    st.remove(it);
                }
                mp.put(rains[i], i);
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
