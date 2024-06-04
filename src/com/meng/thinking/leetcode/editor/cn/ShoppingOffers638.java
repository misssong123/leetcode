package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class ShoppingOffers638 {
    Map<List<Integer>,Integer> minPriceCache = new HashMap<>();
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了81.66% 的Java用户
     * 	内存消耗:43.2 MB,击败了52.66% 的Java用户
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special,
                                List<Integer> needs) {
        int len = needs.size();
        //过滤不符合比原价买还贵的礼包
        List<List<Integer>> filterSpecial = new ArrayList<>();
        for (List<Integer> spe : special){
            int sum = 0;
            for(int i = 0 ; i < len ; i++){
                sum += spe.get(i) * price.get(i);
            }
            if (sum > spe.get(len)){
                filterSpecial.add(spe);
            }
        }
        return dfsMy(price,filterSpecial,needs,len);
    }

    /**
     * 购买指定货物最低的价格
     * @param price 价格
     * @param filterSpecial 礼包
     * @param needs 所需货物
     * @param len 长度
     * @return
     */
    private int dfsMy(List<Integer> price, List<List<Integer>> filterSpecial, List<Integer> needs, int len) {
        if (!minPriceCache.containsKey(needs)){
            int minPrice = 0;
            for(int i = 0 ; i < len ; i++){
                minPrice += needs.get(i) * price.get(i);
            }
            for (List<Integer> spe : filterSpecial){
                int specialPrice = spe.get(len);
                List<Integer> newNeeds = new ArrayList<>();
                for(int i = 0 ; i < len ; i++){
                    if (spe.get(i) > needs.get(i)){
                        break;
                    }
                    newNeeds.add(needs.get(i) - spe.get(i));
                }
                if (newNeeds.size() == len){
                    minPrice = Math.min(minPrice,dfsMy(price,filterSpecial,newNeeds,len) + specialPrice);
                }
            }
            minPriceCache.put(needs,minPrice);
        }
        return minPriceCache.get(needs);
    }
    int ans = 0x3f3f3f3f;
    List<Integer> price, needs;
    List<List<Integer>> special;
    Map<Integer, Integer> map = new HashMap<>();
    int n, m;

    /**
     * 解答成功:
     * 	执行耗时:167 ms,击败了13.61% 的Java用户
     * 	内存消耗:43.8 MB,击败了13.02% 的Java用户
     * @param _price
     * @param _special
     * @param _needs
     * @return
     */
    public int shoppingOffers1(List<Integer> _price, List<List<Integer>> _special, List<Integer> _needs) {
        price = _price; special = _special; needs = _needs;
        n = price.size();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) temp.add(0);
        for (int i = 0; i < n; i++) {
            List<Integer> clone = new ArrayList<>(temp);
            clone.set(i, 1);
            clone.add(price.get(i));
            special.add(clone);
        }
        m = special.size();
        for (int i = 0; i < m; i++) {
            List<Integer> x = special.get(i);
            int k = 0;
            for (int j = 0; j < n; j++) {
                int a = x.get(j), b = needs.get(j);
                if (a == 0 || b == 0) continue;
                if (a > b) {
                    k = 0;
                    break;
                }
                k = k == 0 ? b / a : Math.min(k, b / a);
            }
            map.put(i, k);
        }
        dfs(0, needs, 0);
        return ans;
    }
    void dfs(int u, List<Integer> list, int cur) {
        if (cur >= ans) return ;
        int cnt = 0;
        for (int i = 0; i < n; i++) cnt += list.get(i);
        if (cnt == 0) {
            ans = cur;
            return ;
        }
        if (u == m) return;
        List<Integer> x = special.get(u);
        out:for (int k = 0; k <= map.get(u); k++) {
            List<Integer> clist = new ArrayList<>(list);
            for (int i = 0; i < n; i++) {
                int a = x.get(i), b = clist.get(i);
                if (a * k > b) break out;
                clist.set(i, b - a * k);
            }
            dfs(u + 1, clist, cur + k * x.get(n));
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:767 ms,击败了6.51% 的Java用户
     * 	内存消耗:43.9 MB,击败了7.10% 的Java用户
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers2(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        int[] g = new int[n + 1];
        g[0] = 1;
        for (int i = 1; i <= n; i++) {
            g[i] = g[i - 1] * (needs.get(i - 1) + 1);
        }
        int mask = g[n];
        int[] f = new int[mask];
        int[] cnt = new int[n];
        for (int state = 1; state < mask; state++) {
            f[state] = 0x3f3f3f3f;
            Arrays.fill(cnt, 0);
            for (int i = 0; i < n; i++) {
                cnt[i] = state % g[i + 1] / g[i];
            }
            for (int i = 0; i < n; i++) {
                if (cnt[i] > 0) f[state] = Math.min(f[state], f[state - g[i]] + price.get(i));
            }
            out:for (List<Integer> x : special) {
                int cur = state;
                for (int i = 0; i < n; i++) {
                    if (cnt[i] < x.get(i)) continue out;
                    cur -= x.get(i) * g[i];
                }
                f[state] = Math.min(f[state], f[cur] + x.get(n));
            }

        }
        return f[mask - 1];
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了60.95% 的Java用户
     * 	内存消耗:43.4 MB,击败了23.67% 的Java用户
     * @param price
     * @param special
     * @param needs
     * @return
     */
    Map<List<Integer>, Integer> memo = new HashMap<List<Integer>, Integer>();
    public int shoppingOffers3(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        // 过滤不需要计算的大礼包，只保留需要计算的大礼包
        List<List<Integer>> filterSpecial = new ArrayList<List<Integer>>();
        for (List<Integer> sp : special) {
            int totalCount = 0, totalPrice = 0;
            for (int i = 0; i < n; ++i) {
                totalCount += sp.get(i);
                totalPrice += sp.get(i) * price.get(i);
            }
            if (totalCount > 0 && totalPrice > sp.get(n)) {
                filterSpecial.add(sp);
            }
        }

        return dfs(price, needs, filterSpecial, n);
    }

    // 记忆化搜索计算满足购物清单所需花费的最低价格
    public int dfs(List<Integer> price, List<Integer> curNeeds, List<List<Integer>> filterSpecial, int n) {
        if (!memo.containsKey(curNeeds)) {
            int minPrice = 0;
            for (int i = 0; i < n; ++i) {
                minPrice += curNeeds.get(i) * price.get(i); // 不购买任何大礼包，原价购买购物清单中的所有物品
            }
            for (List<Integer> curSpecial : filterSpecial) {
                int specialPrice = curSpecial.get(n);
                List<Integer> nxtNeeds = new ArrayList<>();
                for (int i = 0; i < n; ++i) {
                    if (curSpecial.get(i) > curNeeds.get(i)) { // 不能购买超出购物清单指定数量的物品
                        break;
                    }
                    nxtNeeds.add(curNeeds.get(i) - curSpecial.get(i));
                }
                if (nxtNeeds.size() == n) { // 大礼包可以购买
                    minPrice = Math.min(minPrice, dfs(price, nxtNeeds, filterSpecial, n) + specialPrice);
                }
            }
            memo.put(curNeeds, minPrice);
        }
        return memo.get(curNeeds);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
