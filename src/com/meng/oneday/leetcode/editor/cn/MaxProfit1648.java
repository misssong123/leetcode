package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

class MaxProfit1648 {
    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了25.53% 的Java用户
     * 	内存消耗:63 MB,击败了59.57% 的Java用户
     * @param inventory
     * @param orders
     * @return
     */
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        long MOD = 1_000_000_007L;

        int i = inventory.length - 1;
        long ordersLeft = orders;
        long profit = 0;

        int colors = 0;
        int curr = inventory[i];

        while (ordersLeft > 0) {

            // 吃掉所有等于 curr 的颜色
            while (i >= 0 && inventory[i] == curr) {
                colors++;
                i--;
            }

            int next = (i >= 0) ? inventory[i] : 0;
            long diff = curr - next;
            long canSell = diff * colors;

            if (ordersLeft >= canSell) {
                long sum = (long)(curr + next + 1) * diff / 2;
                profit = (profit + sum * colors) % MOD;
                ordersLeft -= canSell;
                curr = next;   // ⭐关键：继续往下掉
            } else {
                long full = ordersLeft / colors;
                long rem = ordersLeft % colors;

                long sum = (long)(curr + curr - full + 1) * full / 2;
                profit = (profit + sum * colors) % MOD;
                profit = (profit + (curr - full) * rem) % MOD;
                break;
            }
        }

        return (int) profit;
    }
    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了25.53% 的Java用户
     * 	内存消耗:63 MB,击败了63.83% 的Java用户
     * @param inventory
     * @param orders
     * @return
     */
    public int maxProfitPro(int[] inventory, int orders) {
        long res = 0L;
        Arrays.sort(inventory);
        int index = inventory.length -1;
        int max = inventory[index],cnt = 0;
        while (orders > 0) {
            //寻找相同个数
            while (index >= 0 && inventory[index] == max) {
                cnt++;
                index--;
            }
            //计算差值
            int next = (index >= 0) ? inventory[index] : 0;
            int diff = max - next;
            //计算可以卖出的个数
            long sell = (long)diff * cnt;
            //满足差值
            if(sell <= orders) {
                res += (long)cnt * (max + max - diff + 1) * diff / 2;
                orders -= sell;
                max = index < 0 ? 0 : inventory[index];
                res %= 1000000007;
            }else{//部分满足
                int v1 = orders / cnt;
                int v2 = orders % cnt;
                res += (long)v1 * (max + max - v1 + 1) * cnt / 2;
                res += (long)v2 * (max - v1);
                res %= 1000000007;
                break;
            }
        }
        return (int)res;
    }
    /**
     * 解答成功:
     * 	执行耗时:62 ms,击败了6.12% 的Java用户
     * 	内存消耗:67.9 MB,击败了6.12% 的Java用户
     * @param inventory
     * @param orders
     * @return
     */
    public int maxProfit1648(int[] inventory, int orders) {
        long res = 0L;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //初始化
        for (int num : inventory) {
            queue.add(num);
        }
        int max = queue.poll(),cnt = 1;
        while (orders > 0) {
            //寻找相同个数
            if (!queue.isEmpty() && queue.peek() == max) {
                cnt++;
                queue.poll();
            }
            //计算差值
            int diff = max - (queue.isEmpty()?0 : queue.peek());
            //满足差值
            if((long)diff * cnt <= orders) {
                res += (long)cnt * (max + max - diff + 1) * diff / 2;
            }else{
                int v1 = orders / cnt;
                int v2 = orders % cnt;
                res += (long)v1 * (max + max - v1 + 1) * cnt / 2;
                res += (long)v2 * (max - v1);
                break;
            }
            orders -= diff * cnt;
            max -= diff;
        }
        return (int)(res % 1000000007);
    }

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了67.35% 的Java用户
     * 	内存消耗:66.4 MB,击败了18.37% 的Java用户
     * @param inventory
     * @param orders
     * @return
     */
    public int maxProfitOther(int[] inventory, int orders) {
        int mod = (int)1e9+7;
        long l = 0;
        long r = 0;
        for(int x : inventory){
            r = Math.max(r, x);
        }
        while(l <= r){
            long mid = (l + r) >> 1;
            if(check(inventory, orders, mid)){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        long k = l;
        long sum = 0;
        long ans = 0;
        for(int x : inventory){
            if(x > k){
                ans += rangeSum(k+1, x);
                sum += x - k;
            }
        }
        ans += (orders - sum) * k;
        return (int)(ans % mod);
    }

    public boolean check(int[] inventory, int orders, long k){
        long sum = 0;
        for(int x : inventory){
            if(x > k){
                sum += x - k;
            }
        }
        return sum > orders;
    }

    public long rangeSum(long x, long y){
        return (x + y) * (y - x + 1) / 2;
    }
}
