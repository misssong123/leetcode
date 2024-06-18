package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NumTeams1395 {
    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了71.51% 的Java用户
     * 	内存消耗:43.6 MB,击败了13.37% 的Java用户
     * @param rating
     * @return
     */
    public int numTeamsMy(int[] rating) {
        NumArray1395 numArray = new NumArray1395(rating);
        int res = 0;
        for(int i = 0 ; i < rating.length;i++){
            for(int j = i+1 ; j < rating.length;j++){
                if (rating[i] > rating[j]){
                    res+= numArray.getMin(j);
                }else if (rating[i] < rating[j]){
                    res+= numArray.getMax(j);
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2699 ms,击败了8.14% 的Java用户
     * 	内存消耗:41.2 MB,击败了91.28% 的Java用户
     * @param rating
     * @return
     */
    public int numTeamsOfficial1(int[] rating) {
        int n = rating.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if ((rating[i] < rating[j] && rating[j] < rating[k]) || (rating[i] > rating[j] && rating[j] > rating[k])) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了70.93% 的Java用户
     * 	内存消耗:41.4 MB,击败了66.28% 的Java用户
     * @param rating
     * @return
     */
    public int numTeamsOfficial2(int[] rating) {
        int n = rating.length;
        int ans = 0;
        // 枚举三元组中的 j
        for (int j = 1; j < n - 1; ++j) {
            int iless = 0, imore = 0;
            int kless = 0, kmore = 0;
            for (int i = 0; i < j; ++i) {
                if (rating[i] < rating[j]) {
                    ++iless;
                }
                // 注意这里不能直接写成 else
                // 因为可能有评分相同的情况
                else if (rating[i] > rating[j]) {
                    ++imore;
                }
            }
            for (int k = j + 1; k < n; ++k) {
                if (rating[k] < rating[j]) {
                    ++kless;
                } else if (rating[k] > rating[j]) {
                    ++kmore;
                }
            }
            ans += iless * kmore + imore * kless;
        }
        return ans;
    }

    int maxN;
    int[] c;
    List<Integer> disc;
    int[] iLess;
    int[] iMore;
    int[] kLess;
    int[] kMore;

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了88.95% 的Java用户
     * 	内存消耗:42.3 MB,击败了22.09% 的Java用户
     * @param rating
     * @return
     */
    public int numTeams(int[] rating) {
        int n = rating.length;
        maxN = n + 2;
        c = new int[maxN];
        disc = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            disc.add(rating[i]);
        }
        disc.add(-1);
        Collections.sort(disc);
        iLess = new int[n];
        iMore = new int[n];
        kLess = new int[n];
        kMore = new int[n];

        for (int i = 0; i < n; ++i) {
            int id = getId(rating[i]);
            iLess[i] = get(id);
            iMore[i] = get(n + 1) - get(id);
            add(id, 1);
        }

        c = new int[maxN];
        for (int i = n - 1; i >= 0; --i) {
            int id = getId(rating[i]);
            kLess[i] = get(id);
            kMore[i] = get(n + 1) - get(id);
            add(id, 1);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += iLess[i] * kMore[i] + iMore[i] * kLess[i];
        }

        return ans;
    }

    public int getId(int target) {
        int low = 0, high = disc.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (disc.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int get(int p) {
        int r = 0;
        while (p > 0) {
            r += c[p];
            p -= lowbit(p);
        }
        return r;
    }

    public void add(int p, int v) {
        while (p < maxN) {
            c[p] += v;
            p += lowbit(p);
        }
    }

    public int lowbit(int x) {
        return x & (-x);
    }

}
class NumArray1395 {
    int[] nums;
    int[] tree;
    int[] max;
    int[] min;
    int n;
    public  NumArray1395(int[] nums) {
        n = nums.length;
        max = new int[n];
        min = new int[n];
        this.nums = nums;
        tree = new int[100001];
        for(int i = n-1; i >=0; i--){
            add(nums[i], 1);
            //小于当前位置
            min[i] = query(nums[i]-1);
            //大于当前位置
            max[i] = sumRange(nums[i],Math.max(tree.length-1,nums[i]+1));
        }
    }
    int getMax(int index){
        return max[index];
    }
    int getMin(int index){
        return min[index];
    }
    //单点新增
    void add(int x, int u) {
        for (int i = x; i < tree.length; i += lowBit(i)) {
            tree[i] += u;
        }
    }
    //单点求和
    int query(int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowBit(i)) {
            ans += tree[i];
        }
        return ans;
    }
    //区间求和
    public int sumRange(int l, int r) {
        if (r>=tree.length){
            return 0;
        }
        return query(r) - query(l);
    }
    int lowBit(int x) {
        return x & -x;
    }

}
