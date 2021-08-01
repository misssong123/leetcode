package com.meng.algorithm;

import java.util.*;

/**
 * 1337. 矩阵中战斗力最弱的 K 行
 *
 * 给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
 *
 * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
 *
 * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
 *
 * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
 *
 *
 *
 * 示例 1：
 *
 * 输入：mat =
 * [[1,1,0,0,0],
 *  [1,1,1,1,0],
 *  [1,0,0,0,0],
 *  [1,1,0,0,0],
 *  [1,1,1,1,1]],
 * k = 3
 * 输出：[2,0,3]
 * 解释：
 * 每行中的军人数目：
 * 行 0 -> 2
 * 行 1 -> 4
 * 行 2 -> 1
 * 行 3 -> 2
 * 行 4 -> 5
 * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
 *
 * 示例 2：
 *
 * 输入：mat =
 * [[1,0,0,0],
 *  [1,1,1,1],
 *  [1,0,0,0],
 *  [1,0,0,0]],
 * k = 2
 * 输出：[0,2]
 * 解释：
 * 每行中的军人数目：
 * 行 0 -> 1
 * 行 1 -> 4
 * 行 2 -> 1
 * 行 3 -> 1
 * 从最弱到最强对这些行排序后得到 [0,2,3,1]
 *
 *
 *
 * 提示：
 *
 *     m == mat.length
 *     n == mat[i].length
 *     2 <= n, m <= 100
 *     1 <= k <= m
 *     matrix[i][j] 不是 0 就是 1
 */
public class KWeakestRows {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了75.35% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了38.48% 的用户
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        List<int[]> list = new ArrayList<>();
        int n = mat.length;
        int m = mat[0].length;
        for(int i = 0 ; i < n ; i++){
            int j = 0;
            int num = 0;
            while (j < m && mat[i][j]==1){
                j++;
                num++;
            }
            list.add(new int[]{n,num});
        }
        list.stream().forEach(e -> System.out.println(Arrays.toString(e)));
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]){
                    return o1[1] - o2[1];
                }else {
                    return o1[0] - o2[0];
                }
            }
        });
        list.stream().forEach(e -> System.out.println(Arrays.toString(e)));
        int[] ans = new int[k];
        for(int i = 0 ; i < k ; i++){
            ans[i] = list.get(i)[0];
        }
        return ans;
    }

    /**
     * 前言
     *
     * 由于本题中的矩阵行数 mmm 和列数 nnn 均不超过 100100100，数据规模较小，因此我们可以设计出一些时间复杂度较高的方法，例如直接对整个矩阵进行一次遍历，计算出每一行的战斗力，再进行排序并返回最弱的 kkk 行的索引。
     *
     * 下面我们根据矩阵的性质，给出一种时间复杂度较为优秀的方法。
     * 方法一：二分查找 + 堆
     *
     * 思路与算法
     *
     * 题目描述中有一条重要的保证：
     *
     *     军人总是排在一行中的靠前位置，也就是说 111 总是出现在 000 之前。
     *
     * 因此，我们可以通过二分查找的方法，找出一行中最后的那个 111 的位置。如果其位置为 pos\textit{pos}pos，那么这一行 111 的个数就为 pos+1\textit{pos} + 1pos+1。特别地，如果这一行没有 111，那么令 pos=−1\textit{pos}=-1pos=−1。
     *
     * 当我们得到每一行的战斗力后，我们可以将它们全部放入一个小根堆中，并不断地取出堆顶的元素 kkk 次，这样我们就得到了最弱的 kkk 行的索引。
     *
     * 需要注意的是，如果我们依次将每一行的战斗力以及索引（因为如果战斗力相同，索引较小的行更弱，所以我们需要在小根堆中存放战斗力和索引的二元组）放入小根堆中，那么这样做的时间复杂度是 O(mlog⁡m)O(m \log m)O(mlogm) 的。一种更好的方法是使用这 mmm 个战斗力值直接初始化一个小根堆，时间复杂度为 O(m)O(m)O(m)。读者可以参考《算法导论》的 6.3\text{6.3}6.3 节或者「堆排序中建堆过程时间复杂度 O(n)O(n)O(n) 怎么来的？」了解该过程时间复杂度的证明方法。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/solution/fang-zhen-zhong-zhan-dou-li-zui-ruo-de-k-xing-by-l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param mat
     * @param k
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.77% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了5.30% 的用户
     */
    public int[] kWeakestRows1(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        List<int[]> power = new ArrayList<int[]>();
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n - 1, pos = -1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (mat[i][mid] == 0) {
                    r = mid - 1;
                } else {
                    pos = mid;
                    l = mid + 1;
                }
            }
            power.add(new int[]{pos + 1, i});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                if (pair1[0] != pair2[0]) {
                    return pair1[0] - pair2[0];
                } else {
                    return pair1[1] - pair2[1];
                }
            }
        });
        for (int[] pair : power) {
            pq.offer(pair);
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = pq.poll()[1];
        }
        return ans;
    }

    /**
     * 方法二：二分查找 + 快速选择
     *
     * 思路与算法
     *
     * 我们也可以通过快速选择算法，在平均 O(m)O(m)O(m) 的时间内不计顺序地内找出 kkk 个最小的元素，再使用排序算法在 O(klog⁡k)O(k \log k)O(klogk) 的时间对这 kkk 个最小的元素进行升序排序，就可以得到最终的答案。读者可以参考「剑指 Offer 40. 最小的k个数」官方题解的方法三或者「215. 数组中的第K个最大元素」的官方题解中的方法一了解快速选择算法，下面的代码将上述题解中的快速选择算法封装成一个 Helper\text{Helper}Helper 类进行使用。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/solution/fang-zhen-zhong-zhan-dou-li-zui-ruo-de-k-xing-by-l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param mat
     * @param k
     * @return
     * 执行用时：2 ms, 在所有 Java 提交中击败了75.35% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了67.97% 的用户
     */
    public int[] kWeakestRows2(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] power = new int[m][2];
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n - 1, pos = -1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (mat[i][mid] == 0) {
                    r = mid - 1;
                } else {
                    pos = mid;
                    l = mid + 1;
                }
            }
            power[i][0] = pos + 1;
            power[i][1] = i;
        }

        int[][] minimum = Helper.getLeastNumbers(power, k);
        Arrays.sort(minimum, new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                if (pair1[0] != pair2[0]) {
                    return pair1[0] - pair2[0];
                } else {
                    return pair1[1] - pair2[1];
                }
            }
        });
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = minimum[i][1];
        }
        return ans;
    }
}

class Helper {
    public static int[][] getLeastNumbers(int[][] arr, int k) {
        randomizedSelected(arr, 0, arr.length - 1, k);
        int[][] vec = new int[k][2];
        for (int i = 0; i < k; ++i) {
            vec[i][0] = arr[i][0];
            vec[i][1] = arr[i][1];
        }
        return vec;
    }

    private static void randomizedSelected(int[][] arr, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int pos = randomizedPartition(arr, l, r);
        int num = pos - l + 1;
        if (k == num) {
            return;
        } else if (k < num) {
            randomizedSelected(arr, l, pos - 1, k);
        } else {
            randomizedSelected(arr, pos + 1, r, k - num);
        }
    }

    // 基于随机的划分
    private static int randomizedPartition(int[][] nums, int l, int r) {
        int i = (int) (Math.random() * (r - l + 1)) + l;
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    private static int partition(int[][] nums, int l, int r) {
        int[] pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (compare(nums[j], pivot) <= 0) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private static void swap(int[][] nums, int i, int j) {
        int[] temp = new int[nums[i].length];
        System.arraycopy(nums[i], 0, temp, 0, nums[i].length);
        System.arraycopy(nums[j], 0, nums[i], 0, nums[i].length);
        System.arraycopy(temp, 0, nums[j], 0, nums[i].length);
    }

    private static int compare(int[] pair, int[] pivot) {
        if (pair[0] != pivot[0]) {
            return pair[0] - pivot[0];
        } else {
            return pair[1] - pivot[1];
        }
    }
}
