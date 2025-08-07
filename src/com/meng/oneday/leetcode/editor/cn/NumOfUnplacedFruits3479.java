package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class NumOfUnplacedFruits3479 {
    /**
     * 解答成功:
     * 	执行耗时:272 ms,击败了14.19% 的Java用户
     * 	内存消耗:59.4 MB,击败了25.34% 的Java用户
     * @param fruits
     * @param baskets
     * @return
     */
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = baskets.length;
        int m = (int) Math.sqrt(n);
        int section = (n + m - 1) / m;
        int count = 0;
        int[] maxV = new int[section];
        Arrays.fill(maxV, 0);

        for (int i = 0; i < n; i++) {
            maxV[i / m] = Math.max(maxV[i / m], baskets[i]);
        }

        for (int fruit : fruits) {
            int sec;
            int unset = 1;
            for (sec = 0; sec < section; sec++) {
                if (maxV[sec] < fruit) {
                    continue;
                }
                int choose = 0;
                maxV[sec] = 0;
                for (int i = 0; i < m; i++) {
                    int pos = sec * m + i;
                    if (pos < n && baskets[pos] >= fruit && choose == 0) {
                        baskets[pos] = 0;
                        choose = 1;
                    }
                    if (pos < n) {
                        maxV[sec] = Math.max(maxV[sec], baskets[pos]);
                    }
                }
                unset = 0;
                break;
            }
            count += unset;
        }
        return count;
    }
    public int numOfUnplacedFruitsTimeOut(int[] fruits, int[] baskets) {
        int count = 0;
        int index =0,len = baskets.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->{
            if (a[0] == b[0]){
                return a[1] - b[1];
            }else {
                return b[0] - a[0];
            }
        });
        Stack<int[]> stack = new Stack<>();
        for (int fruit : fruits) {
            boolean flag = true;
            if (queue.isEmpty()||queue.peek()[0] < fruit){
                while (index < len && baskets[index] < fruit) {
                    queue.offer(new int[]{baskets[index],index});
                    index++;
                }
                if (index >= len){
                    flag = false;
                }
                index++;
            }else{
                while (!queue.isEmpty() && queue.peek()[0] >= fruit){
                    stack.push(queue.poll());
                }
                //弹出第一个符合条件的数据
                int[] min = null;
                while (!stack.isEmpty()){
                    int[] pop = stack.pop();
                    if (min == null || pop[1] < min[1]){
                        min = pop;
                    }
                    queue.offer(pop);
                }
                queue.remove(min);
            }
            if (!flag){
                count++;
            }
        }
        return count;
    }
    /**
     * 解答成功:
     * 	执行耗时:69 ms,击败了33.93% 的Java用户
     * 	内存消耗:56 MB,击败了91.07% 的Java用户
     * @param fruits
     * @param baskets
     * @return
     */
    public int numOfUnplacedFruitsOther(int[] fruits, int[] baskets) {
        SegmentTree3479 t = new SegmentTree3479(baskets);
        int n = baskets.length;
        int ans = 0;
        for (int x : fruits) {
            if (t.findFirstAndUpdate(1, 0, n - 1, x) < 0) {
                ans++;
            }
        }
        return ans;
    }
}
class SegmentTree3479 {
    private final int[] max;

    public SegmentTree3479(int[] a) {
        int n = a.length;
        max = new int[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        build(a, 1, 0, n - 1);
    }

    // 找区间内的第一个 >= x 的数，并更新为 -1，返回这个数的下标（没有则返回 -1）
    public int findFirstAndUpdate(int o, int l, int r, int x) {
        if (max[o] < x) { // 区间没有 >= x 的数
            return -1;
        }
        if (l == r) {
            max[o] = -1; // 更新为 -1，表示不能放水果
            return l;
        }
        int m = (l + r) / 2;
        int i = findFirstAndUpdate(o * 2, l, m, x); // 先递归左子树
        if (i < 0) { // 左子树没找到
            i = findFirstAndUpdate(o * 2 + 1, m + 1, r, x); // 再递归右子树
        }
        maintain(o);
        return i;
    }

    private void maintain(int o) {
        max[o] = Math.max(max[o * 2], max[o * 2 + 1]);
    }

    // 初始化线段树
    private void build(int[] a, int o, int l, int r) {
        if (l == r) {
            max[o] = a[l];
            return;
        }
        int m = (l + r) / 2;
        build(a, o * 2, l, m);
        build(a, o * 2 + 1, m + 1, r);
        maintain(o);
    }
}
