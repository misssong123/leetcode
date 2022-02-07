package com.meng.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373. 查找和最小的K对数字
 * 给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 *
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 *
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 *
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 *
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 *
 *
 * 提示:
 *
 * 1 <= nums1.length, nums2.length <= 105
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1 和 nums2 均为升序排列
 * 1 <= k <= 1000
 */
public class KSmallestPairs {
    /**
     * 方法一：优先队列
     * 思路
     *
     * 本题与「719. 找出第 k 小的距离对」相似，可以参考该题的解法。对于已经按升序排列的两个数组 \textit{nums}_1,\textit{nums}_2nums
     * 1
     * ​
     *  ,nums
     * 2
     * ​
     *  ，长度分别为 \textit{length}_1,\textit{length}_2length
     * 1
     * ​
     *  ,length
     * 2
     * ​
     *  ，我们可以知道和最小的数对一定为 (\textit{nums}_1[0], \textit{nums}_2[0])(nums
     * 1
     * ​
     *  [0],nums
     * 2
     * ​
     *  [0])，和最大的数对一定为 (\textit{nums}_1[\textit{length}_1-1], \textit{nums}_2[\textit{length}_2-1])(nums
     * 1
     * ​
     *  [length
     * 1
     * ​
     *  −1],nums
     * 2
     * ​
     *  [length
     * 2
     * ​
     *  −1])。本题要求找到最小的 kk 个数对，最直接的办法是可以将所有的数对求出来，然后利用排序或者 \texttt{TopK}TopK 解法求出最小的 kk 个数对即可。实际求解时可以不用求出所有的数对，只需从所有符合待选的数对中选出最小的即可，假设当前已选的前 nn 小数对的索引分别为 (a_1,b_1),(a_2,b_2),(a_3,b_3),\ldots,(a_n,b_n)(a
     * 1
     * ​
     *  ,b
     * 1
     * ​
     *  ),(a
     * 2
     * ​
     *  ,b
     * 2
     * ​
     *  ),(a
     * 3
     * ​
     *  ,b
     * 3
     * ​
     *  ),…,(a
     * n
     * ​
     *  ,b
     * n
     * ​
     *  )，由于两个数组都是按照升序进行排序的，则可以推出第 n+1n+1 小的数对的索引选择范围为 (a_1+1,b_1),(a_1,b_1+1),(a_2+1,b_2),(a_2,b_2+1),(a_3+1,b_3),(a_3,b_3+1),\ldots,(a_n+1,b_n),(a_n,b_n+1)(a
     * 1
     * ​
     *  +1,b
     * 1
     * ​
     *  ),(a
     * 1
     * ​
     *  ,b
     * 1
     * ​
     *  +1),(a
     * 2
     * ​
     *  +1,b
     * 2
     * ​
     *  ),(a
     * 2
     * ​
     *  ,b
     * 2
     * ​
     *  +1),(a
     * 3
     * ​
     *  +1,b
     * 3
     * ​
     *  ),(a
     * 3
     * ​
     *  ,b
     * 3
     * ​
     *  +1),…,(a
     * n
     * ​
     *  +1,b
     * n
     * ​
     *  ),(a
     * n
     * ​
     *  ,b
     * n
     * ​
     *  +1)，假设我们利用堆的特性可以求出待选范围中最小数对的索引为 (a_{i},b_{i})(a
     * i
     * ​
     *  ,b
     * i
     * ​
     *  )，同时将新的待选的数对 (a_{i}+1,b_{i}),(a_{i},b_{i}+1)(a
     * i
     * ​
     *  +1,b
     * i
     * ​
     *  ),(a
     * i
     * ​
     *  ,b
     * i
     * ​
     *  +1) 加入到堆中，直到我们选出 kk 个数对即可。
     *
     * 如果我们每次都将已选的数对 (a_{i},b_{i})(a
     * i
     * ​
     *  ,b
     * i
     * ​
     *  ) 的待选索引 (a_{i}+1,b_{i}),(a_{i},b_{i}+1)(a
     * i
     * ​
     *  +1,b
     * i
     * ​
     *  ),(a
     * i
     * ​
     *  ,b
     * i
     * ​
     *  +1) 加入到堆中则可能出现重复的问题，一般需要设置标记位解决去重的问题。我们可以将 \textit{nums}_1nums
     * 1
     * ​
     *   的前 kk 个索引数对 (0,0),(1,0),\ldots,(k-1,0)(0,0),(1,0),…,(k−1,0) 加入到队列中，每次从队列中取出元素 (x,y)(x,y) 时，我们只需要将 \textit{nums}_2nums
     * 2
     * ​
     *   的索引增加即可，这样避免了重复加入元素的问题。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/solution/cha-zhao-he-zui-xiao-de-kdui-shu-zi-by-l-z526/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @param k
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 89.85%
     * 的用户
     * 内存消耗：
     * 49.8 MB
     * , 在所有 Java 提交中击败了
     * 54.77%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     */
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2)->{
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i,0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }

    /**
     * 方法二：二分查找
     * 思路
     *
     * 参考「378. 有序矩阵中第 K 小的元素」的二分查找的解法，我们利用二分查找找到第 kk 小的数对和为 \textit{pairSum}pairSum。利用滑动窗口即可计算出两个数组中满足数对和小于等于目标值 \textit{target}target 的数对有多少个，我们找到最小的 \textit{target}target 且满足小于等于它的数对数目刚好大于等于 kk 即为目标值 \textit{pairSum}pairSum，然后在数组中找到最小的 kk 个数对满足数对和小于等于 \textit{pairSum}pairSum。
     *
     * 由于题目中数组 \textit{nums}_1,\textit{nums}_2nums
     * 1
     * ​
     *  ,nums
     * 2
     * ​
     *   中的元素存在重复，因此我们不能简单的利用滑动窗口找到所有满足小于等于 \textit{pairSum}pairSum 的数对。因为存在小于等于 \textit{pairSum}pairSum 的数对和的数目大于 kk，因此数对和等于 \textit{pairSum}pairSum 的数对不一定就属于最小的 kk 个数对。
     * 首先利用滑动窗口找到所有小于 \textit{pairSum}pairSum 的数对，假设数对和小于 \textit{pairSum}pairSum 的数目为 xx 个，然后再利用二分查找在数组中找到 k-xk−x 个和等于 \textit{pairSum}pairSum 的数对即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/solution/cha-zhao-he-zui-xiao-de-kdui-shu-zi-by-l-z526/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @param k
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 89.85%
     * 的用户
     * 内存消耗：
     * 49.9 MB
     * , 在所有 Java 提交中击败了
     * 53.48%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     */
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        /*二分查找第 k 小的数对和的大小*/
        int left = nums1[0] + nums2[0];
        int right = nums1[m - 1] + nums2[n - 1];
        int pairSum = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long cnt = 0;
            int start = 0;
            int end = n - 1;
            while (start < m && end >= 0) {
                if (nums1[start] + nums2[end] > mid) {
                    end--;
                } else {
                    cnt += end + 1;
                    start++;
                }
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                pairSum = mid;
                right = mid - 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int pos = n - 1;
        /*找到小于目标值 pairSum 的数对*/
        for (int i = 0; i < m; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] >= pairSum) {
                pos--;
            }
            for (int j = 0; j <= pos && k > 0; j++, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                ans.add(list);
            }
        }

        /*找到等于目标值 pairSum 的数对*/
        pos = n - 1;
        for (int i = 0; i < m && k > 0; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] > pairSum) {
                pos--;
            }
            for (int j = i; k > 0 && j >= 0 && nums1[j] + nums2[pos] == pairSum; j--, k--) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[j]);
                list.add(nums2[pos]);
                ans.add(list);
            }
        }
        return ans;
    }

}
