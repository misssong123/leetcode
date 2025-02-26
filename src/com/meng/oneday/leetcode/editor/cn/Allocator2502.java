package com.meng.oneday.leetcode.editor.cn;

import java.util.*;
/**
 * 解答成功:
 * 	执行耗时:9 ms,击败了99.29% 的Java用户
 * 	内存消耗:44.3 MB,击败了98.58% 的Java用户
 */
class Allocator2502 {
    Map<Integer, List<int[]>> cache;
    int[] memory;
    int remnant;
    public Allocator2502(int n) {
        cache = new HashMap<>();
        memory = new int[n];
        remnant = n;
    }

    public int allocate(int size, int mID) {
        if (remnant < size) {
            return -1;
        }
        for(int i = 0 ; i < memory.length; i++) {
            if(memory[i] == 0) {
                for(int j = i; j < i + size; j++) {
                    if (j >= memory.length) {
                        return -1;
                    }
                    if (memory[j] != 0) {
                        i = j;
                        break;
                    }
                }
                if (memory[i] == 0) {
                    Arrays.fill(memory, i, i + size, mID);
                    remnant -= size;
                    if (!cache.containsKey(mID)) {
                        cache.put(mID, new ArrayList<>());
                    }
                    cache.get(mID).add(new int[]{i, size});
                    return i;
                }
            }
        }
        return -1;
    }

    public int freeMemory(int mID) {
        if(cache.containsKey(mID)){
            int free = 0;
            List<int[]> indexList = cache.remove(mID);
            for(int[] index : indexList) {
                Arrays.fill(memory, index[0], index[0] + index[1], 0);
                free += index[1];
            }
            remnant+=free;
            return free;
        }
        return 0;
    }
}
/**
 * 解答成功:
 * 	执行耗时:31 ms,击败了28.95% 的Java用户
 * 	内存消耗:44.8 MB,击败了21.05% 的Java用户
 */
class AllocatorOther {
    private final int[] memory;

    public AllocatorOther(int n) {
        memory = new int[n];
    }

    public int allocate(int size, int mID) {
        int free = 0;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] > 0) { // 已分配
                free = 0; // 重新计数
                continue;
            }
            free++;
            if (free == size) { // 找到了
                Arrays.fill(memory, i - size + 1, i + 1, mID);
                return i - size + 1;
            }
        }
        return -1; // 无法分配内存
    }

    public int freeMemory(int mID) {
        int ans = 0;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == mID) {
                ans++;
                memory[i] = 0; // 标记为空闲内存
            }
        }
        return ans;
    }
}

/**
 * 解答成功:
 * 	执行耗时:11 ms,击败了94.33% 的Java用户
 * 	内存消耗:44.8 MB,击败了9.22% 的Java用户
 */
class SegTree {
    private final int[] pre0; // 区间前缀连续 0 的个数
    private final int[] suf0; // 区间后缀连续 0 的个数
    private final int[] max0; // 区间最长连续 0 的个数
    private final int[] todo; // 懒标记

    public SegTree(int n) {
        int size = 2 << (32 - Integer.numberOfLeadingZeros(n - 1));
        pre0 = new int[size];
        suf0 = new int[size];
        max0 = new int[size];
        todo = new int[size];
        build(1, 0, n - 1);
    }

    // 把 [ql, qr] 都置为 v
    public void update(int o, int l, int r, int ql, int qr, int v) {
        if (ql <= l && r <= qr) {
            do_(o, l, r, v);
            return;
        }
        spread(o, l, r);
        int m = (l + r) / 2;
        int lo = o * 2;
        int ro = lo + 1;
        if (ql <= m) {
            update(lo, l, m, ql, qr, v);
        }
        if (m < qr) {
            update(ro, m + 1, r, ql, qr, v);
        }

        // 合并左右子树的信息
        pre0[o] = pre0[lo];
        if (pre0[lo] == m - l + 1) {
            pre0[o] += pre0[ro]; // 和右子树的 pre0 拼起来
        }
        suf0[o] = suf0[ro];
        if (suf0[ro] == r - m) {
            suf0[o] += suf0[lo]; // 和左子树的 suf0 拼起来
        }
        max0[o] = Math.max(Math.max(max0[lo], max0[ro]), suf0[lo] + pre0[ro]);
    }

    // 线段树二分，找最左边的区间左端点，满足区间全为 0 且长度 >= size
    // 如果不存在这样的区间，返回 -1
    public int findFirst(int o, int l, int r, int size) {
        if (max0[o] < size) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        spread(o, l, r);
        int m = (l + r) / 2;
        int lo = o * 2;
        int ro = lo + 1;
        int idx = findFirst(lo, l, m, size); // 递归左子树
        if (idx < 0) {
            // 左子树的后缀 0 个数 + 右子树的前缀 0 个数 >= size
            if (suf0[lo] + pre0[ro] >= size) {
                return m - suf0[lo] + 1;
            }
            idx = findFirst(ro, m + 1, r, size); // 递归右子树
        }
        return idx;
    }

    // 初始化线段树
    private void build(int o, int l, int r) {
        do_(o, l, r, -1);
        if (l == r) {
            return;
        }
        int m = (l + r) / 2;
        build(o * 2, l, m);
        build(o * 2 + 1, m + 1, r);
    }

    private void do_(int i, int l, int r, int v) {
        int size = v <= 0 ? r - l + 1 : 0;
        pre0[i] = suf0[i] = max0[i] = size;
        todo[i] = v;
    }

    // 下传懒标记
    private void spread(int o, int l, int r) {
        int v = todo[o];
        if (v != -1) {
            int m = (l + r) / 2;
            do_(o * 2, l, m, v);
            do_(o * 2 + 1, m + 1, r, v);
            todo[o] = -1;
        }
    }
}

class Allocator {
    private final int n;
    private final SegTree tree;
    private final Map<Integer, List<int[]>> blocks = new HashMap<>();

    public Allocator(int n) {
        this.n = n;
        this.tree = new SegTree(n);
    }

    public int allocate(int size, int mID) {
        int i = tree.findFirst(1, 0, n - 1, size);
        if (i < 0) { // 无法分配内存
            return -1;
        }
        // 分配内存 [i, i+size-1]
        blocks.computeIfAbsent(mID, k -> new ArrayList<>()).add(new int[]{i, i + size - 1});
        tree.update(1, 0, n - 1, i, i + size - 1, 1);
        return i;
    }

    public int freeMemory(int mID) {
        int ans = 0;
        List<int[]> list = blocks.get(mID);
        if (list != null) {
            for (int[] range : list) {
                ans += range[1] - range[0] + 1;
                tree.update(1, 0, n - 1, range[0], range[1], 0); // 释放内存
            }
            blocks.remove(mID);
        }
        return ans;
    }
}
