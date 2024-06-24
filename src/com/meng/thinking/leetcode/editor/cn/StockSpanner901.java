package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class StockSpanner901 {
    Deque<Integer> st ;
    Map<Integer,Integer> cache;

    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了34.53% 的Java用户
     * 	内存消耗:54.2 MB,击败了42.95% 的Java用户
     */
    public StockSpanner901() {
       st = new ArrayDeque<>();
       cache =  new HashMap<>();
    }
    public int next(int price) {
        int ans = 1;
        while (!st.isEmpty() && st.peek() <= price) {
            ans += cache.get(st.pop());
        }
        st.push(price);
        cache.put(price,ans);
        return ans;
    }
}

/**
 * 解答成功:
 * 	执行耗时:22 ms,击败了91.77% 的Java用户
 * 	内存消耗:53.9 MB,击败了87.70% 的Java用户
 */
class StockSpannerOfficial {
    Deque<int[]> stack;
    int idx;

    public StockSpannerOfficial() {
        stack = new ArrayDeque<int[]>();
        stack.push(new int[]{-1, Integer.MAX_VALUE});
        idx = -1;
    }

    public int next(int price) {
        idx++;
        while (price >= stack.peek()[1]) {
            stack.pop();
        }
        int ret = idx - stack.peek()[0];
        stack.push(new int[]{idx, price});
        return ret;
    }


}
