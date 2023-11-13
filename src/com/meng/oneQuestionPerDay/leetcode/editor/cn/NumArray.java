package com.meng.oneQuestionPerDay.leetcode.editor.cn;

/**
 * 解答成功:
 * 	执行耗时:866 ms,击败了20.29% 的Java用户
 * 	内存消耗:71.8 MB,击败了44.26% 的Java用户
 */
class NumArray {
    int[] cache;
    int[] origin;
    int len;
    public NumArray(int[] nums) {
        len = nums.length;
        origin = nums;
        cache = new int[len + 1];
        for(int i = 1 ; i <= len ; i++){
            cache[i] = cache[i-1] + nums[i-1];
        }
    }
    
    public void update(int index, int val) {
        if (origin[index] == val){
            return;
        }
        int diff = val - origin[index];
        origin[index] = val;
        for(int i = index+1 ; i <= len ; i++){
            cache[i] =  cache[i] + diff;
        }
    }
    
    public int sumRange(int left, int right) {
        return cache[right+1] - cache[left];
    }
}

/**
 * 解答成功:
 * 	执行耗时:102 ms,击败了48.09% 的Java用户
 * 	内存消耗:70.2 MB,击败了73.52% 的Java用户
 */
class NumArray1 {
    private int[] sum; // sum[i] 表示第 i 个块的元素和
    private int size; // 块的大小
    private int[] nums;

    public NumArray1(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        size = (int) Math.sqrt(n);
        sum = new int[(n + size - 1) / size]; // n/size 向上取整
        for (int i = 0; i < n; i++) {
            sum[i / size] += nums[i];
        }
    }

    public void update(int index, int val) {
        sum[index / size] += val - nums[index];
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        int b1 = left / size, i1 = left % size, b2 = right / size, i2 = right % size;
        if (b1 == b2) { // 区间 [left, right] 在同一块中
            int sum = 0;
            for (int j = i1; j <= i2; j++) {
                sum += nums[b1 * size + j];
            }
            return sum;
        }
        int sum1 = 0;
        for (int j = i1; j < size; j++) {
            sum1 += nums[b1 * size + j];
        }
        int sum2 = 0;
        for (int j = 0; j <= i2; j++) {
            sum2 += nums[b2 * size + j];
        }
        int sum3 = 0;
        for (int j = b1 + 1; j < b2; j++) {
            sum3 += sum[j];
        }
        return sum1 + sum2 + sum3;
    }
}

/**
 * 解答成功:
 * 	执行耗时:132 ms,击败了29.41% 的Java用户
 * 	内存消耗:73.5 MB,击败了16.46% 的Java用户
 */
class NumArray2 {
    private int[] segmentTree;
    private int n;

    public NumArray2(int[] nums) {
        n = nums.length;
        segmentTree = new int[nums.length * 4];
        build(0, 0, n - 1, nums);
    }

    public void update(int index, int val) {
        change(index, val, 0, 0, n - 1);
    }

    public int sumRange(int left, int right) {
        return range(left, right, 0, 0, n - 1);
    }

    private void build(int node, int s, int e, int[] nums) {
        if (s == e) {
            segmentTree[node] = nums[s];
            return;
        }
        int m = s + (e - s) / 2;
        build(node * 2 + 1, s, m, nums);
        build(node * 2 + 2, m + 1, e, nums);
        segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
    }

    private void change(int index, int val, int node, int s, int e) {
        if (s == e) {
            segmentTree[node] = val;
            return;
        }
        int m = s + (e - s) / 2;
        if (index <= m) {
            change(index, val, node * 2 + 1, s, m);
        } else {
            change(index, val, node * 2 + 2, m + 1, e);
        }
        segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
    }

    private int range(int left, int right, int node, int s, int e) {
        if (left == s && right == e) {
            return segmentTree[node];
        }
        int m = s + (e - s) / 2;
        if (right <= m) {
            return range(left, right, node * 2 + 1, s, m);
        } else if (left > m) {
            return range(left, right, node * 2 + 2, m + 1, e);
        } else {
            return range(left, m, node * 2 + 1, s, m) + range(m + 1, right, node * 2 + 2, m + 1, e);
        }
    }
}

/**
 * 解答成功:
 * 	执行耗时:94 ms,击败了63.09% 的Java用户
 * 	内存消耗:71.8 MB,击败了43.37% 的Java用户
 */
class NumArray3 {
    private int[] tree;
    private int[] nums;

    public NumArray3(int[] nums) {
        this.tree = new int[nums.length + 1];
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        add(index + 1, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    private int lowBit(int x) {
        return x & -x;
    }

    private void add(int index, int val) {
        while (index < tree.length) {
            tree[index] += val;
            index += lowBit(index);
        }
    }

    private int prefixSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= lowBit(index);
        }
        return sum;
    }
}
