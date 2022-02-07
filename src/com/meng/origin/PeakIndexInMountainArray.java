package com.meng.origin;

/**
 * 852. 山脉数组的峰顶索引
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 *
 *     arr.length >= 3
 *     存在 i（0 < i < arr.length - 1）使得：
 *         arr[0] < arr[1] < ... arr[i-1] < arr[i]
 *         arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 *
 * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [0,1,0]
 * 输出：1
 *
 * 示例 2：
 *
 * 输入：arr = [0,2,1,0]
 * 输出：1
 *
 * 示例 3：
 *
 * 输入：arr = [0,10,5,2]
 * 输出：1
 *
 * 示例 4：
 *
 * 输入：arr = [3,4,5,1]
 * 输出：2
 *
 * 示例 5：
 *
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 *
 *
 *
 * 提示：
 *
 *     3 <= arr.length <= 104
 *     0 <= arr[i] <= 106
 *     题目数据保证 arr 是一个山脉数组
 *
 *
 * @author lenovo
 */
public class PeakIndexInMountainArray {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了42.00% 的用户
     * @param arr
     * @return
     */
    public int peakIndexInMountainArraySelf1(int[] arr) {
        int len = arr.length;
        if(len == 3){
            return 1;
        }
        int index = 0;
        for(int i = 1 ; i < len-1 ; i++){
            if(arr[i] > arr[i+1]){
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了6.30% 的用户
     * @param arr
     * @return
     */
    public int peakIndexInMountainArraySelf2(int[] arr) {
        int right = arr.length -1;
        int left = 1;
        int index = 0;
        while (left <= right){
            int mid = (right + left)/2;
            if (arr[mid] < arr[mid+1]){
                left = mid;
            }else {
                if (arr[mid] > arr[mid - 1]){
                    index = mid;
                    break;
                }else {
                    right = mid;
                }
            }
        }
        return index;
    }
    /**
     * 方法一：枚举
     *
     * 思路与算法
     *
     * 我们可以对数组 arr\textit{arr}arr 进行一次遍历。
     *
     * 当我们遍历到下标 iii 时，如果有 arri−1<arri>arri+1\textit{arr}_{i-1} < \textit{arr}_i > \textit{arr}_{i+1}arri−1​<arri​>arri+1​，那么 iii 就是我们需要找出的下标。
     *
     * 更简单地，我们只需要让 iii 满足 arri>arri+1\textit{arr}_i > \textit{arr}_{i+1}arri​>arri+1​ 即可。在遍历的过程中，我们最先遍历到的满足 arri>arri+1\textit{arr}_i > \textit{arr}_{i+1}arri​>arri+1​ 的下标 iii 一定也满足 arri−1<arri\textit{arr}_{i-1} < \textit{arr}_iarri−1​<arri​。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/solution/shan-mai-shu-zu-de-feng-ding-suo-yin-by-dtqvv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：1 ms, 在所有 Java 提交中击败了16.35% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了45.63% 的用户
     */
    public int peakIndexInMountainArray1(int[] arr) {
        int n = arr.length;
        int ans = -1;
        for (int i = 1; i < n - 1; ++i) {
            if (arr[i] > arr[i + 1]) {
                ans = i;
                break;
            }
        }
        return ans;
    }
    /**
     * 方法二：二分查找
     *
     * 思路与算法
     *
     * 记满足题目要求的下标 iii 为 iansi_\textit{ans}ians​。我们可以发现：
     *
     *     当 i<iansi < i_\textit{ans}i<ians​ 时，arri<arri+1\textit{arr}_i < \textit{arr}_{i+1}arri​<arri+1​ 恒成立；
     *
     *     当 i≥iansi \geq i_\textit{ans}i≥ians​ 时，arri>arri+1\textit{arr}_i > \textit{arr}_{i+1}arri​>arri+1​ 恒成立。
     *
     * 这与方法一的遍历过程也是一致的，因此 iansi_\textit{ans}ians​ 即为「最小的满足 arri>arri+1\textit{arr}_i > \textit{arr}_{i+1}arri​>arri+1​ 的下标 iii」，我们可以用二分查找的方法来找出 iansi_\textit{ans}ians​。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/solution/shan-mai-shu-zu-de-feng-ding-suo-yin-by-dtqvv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了91.03% 的用户
     */
    public int peakIndexInMountainArray2(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
