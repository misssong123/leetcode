package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class EatenApples1705 {
    /**
     * 解答成功:
     * 	执行耗时:62 ms,击败了63.64% 的Java用户
     * 	内存消耗:46.3 MB,击败了5.06% 的Java用户
     * @param apples
     * @param days
     * @return
     */
    public int eatenApplesMy(int[] apples, int[] days) {
        Map<Integer,Integer> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);
        int res = 0;
        int curDay = 0;
        for(int apple : apples){
            if(apple != 0){
                int expireDay = curDay + days[curDay]-1;
                if (map.containsKey(expireDay)){
                    map.put(expireDay,map.get(expireDay)+apple);
                }else {
                    map.put(expireDay,apple);
                    queue.add(expireDay);
                }
            }
            while(!queue.isEmpty()&&queue.peek() < curDay){
                map.remove(queue.poll());
            }
            //非空
            if (!queue.isEmpty()){
                res++;
                int num = map.get(queue.peek());
                if(num == 1){
                    map.remove(queue.poll());
                }else {
                    map.put(queue.peek(),num-1);
                }
            }
            curDay++;
        }
        while (!queue.isEmpty()){
            int expireDay = queue.poll();
            int expireApple = map.get(expireDay);
            if(expireDay >= curDay){
                int len = Math.min(expireApple,expireDay-curDay+1);
                res+=len;
                curDay+=len;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:35 ms,击败了97.27% 的Java用户
     * 	内存消耗:45.1 MB,击败了51.90% 的Java用户
     * @param apples
     * @param days
     * @return
     */
    public int eatenApples(int[] apples, int[] days) {
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int i = 0;
        for (; i < apples.length; i++) {
            while (!pq.isEmpty() && pq.peek()[0] == i) { // 已腐烂
                pq.poll();
            }
            if (apples[i] > 0) {
                pq.offer(new int[]{i + days[i], apples[i]});
            }
            if (!pq.isEmpty()) {
                // 吃一个最早腐烂的苹果
                ans++;
                if (--pq.peek()[1] == 0) {
                    pq.poll();
                }
            }
        }

        while (true) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) { // 已腐烂
                pq.poll();
            }
            if (pq.isEmpty()) {
                return ans;
            }
            int[] top = pq.poll();
            int k = Math.min(top[1], top[0] - i);
            ans += k;
            i += k;
        }
    }
}
