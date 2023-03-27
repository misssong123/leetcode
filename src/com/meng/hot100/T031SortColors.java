package com.meng.hot100;

import java.util.Arrays;

/**
 * 75. 颜色分类(中等)
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 必须在不使用库的sort函数的情况下解决这个问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 *
 * 进阶：
 *
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class T031SortColors {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.9 MB
     * , 在所有 Java 提交中击败了
     * 54.21%
     * 的用户
     * 通过测试用例：
     * 87 / 87
     * @param nums
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return;
        }
        int left = 0 , right = len - 1;
        for (int i = 0 ; i <= right ; i++){
            if (nums[i] == 0){
                while (nums[left]==0&&left<i){
                    left++;
                }
                nums[i] = nums[left];
                nums[left] = 0;
            }else if(nums[i]==2){
                while (nums[right] == 2&&right>i){
                    right--;
                }
                nums[i] = nums[right];
                nums[right]=2;
                if (nums[i]==0){
                    i--;
                }
            }
        }
    }

    public static void main(String[] args) {
        T031SortColors demo = new T031SortColors();
        int[] nums = {1,2,0};
        demo.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 方法二：双指针
     * 思路与算法
     *
     * 方法一需要进行两次遍历，那么我们是否可以仅使用一次遍历呢？我们可以额外使用一个指针，即使用两个指针分别用来交换 00 和 11。
     *
     * 具体地，我们用指针 p_0p
     * 0
     * ​
     *   来交换 00，p_1p
     * 1
     * ​
     *   来交换 11，初始值都为 00。当我们从左向右遍历整个数组时：
     *
     * 如果找到了 11，那么将其与 \textit{nums}[p_1]nums[p
     * 1
     * ​
     *  ] 进行交换，并将 p_1p
     * 1
     * ​
     *   向后移动一个位置，这与方法一是相同的；
     *
     * 如果找到了 00，那么将其与 \textit{nums}[p_0]nums[p
     * 0
     * ​
     *  ] 进行交换，并将 p_0p
     * 0
     * ​
     *   向后移动一个位置。这样做是正确的吗？我们可以注意到，因为连续的 00 之后是连续的 11，因此如果我们将 00 与 \textit{nums}[p_0]nums[p
     * 0
     * ​
     *  ] 进行交换，那么我们可能会把一个 11 交换出去。当 p_0 < p_1p
     * 0
     * ​
     *  <p
     * 1
     * ​
     *   时，我们已经将一些 11 连续地放在头部，此时一定会把一个 11 交换出去，导致答案错误。因此，如果 p_0 < p_1p
     * 0
     * ​
     *  <p
     * 1
     * ​
     *  ，那么我们需要再将 \textit{nums}[i]nums[i] 与 \textit{nums}[p_1]nums[p
     * 1
     * ​
     *  ] 进行交换，其中 ii 是当前遍历到的位置，在进行了第一次交换后，\textit{nums}[i]nums[i] 的值为 11，我们需要将这个 11 放到「头部」的末端。在最后，无论是否有 p_0 < p_1p
     * 0
     * ​
     *  <p
     * 1
     * ​
     *  ，我们需要将 p_0p
     * 0
     * ​
     *   和 p_1p
     * 1
     * ​
     *   均向后移动一个位置，而不是仅将 p_0p
     * 0
     * ​
     *   向后移动一个位置。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/sort-colors/solution/yan-se-fen-lei-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.1 MB
     * , 在所有 Java 提交中击败了
     * 22.46%
     * 的用户
     * 通过测试用例：
     * 87 / 87
     */
    public void sortColors1(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }

    /**
     * 方法三：双指针
     * 思路与算法
     *
     * 与方法二类似，我们也可以考虑使用指针 p_0p
     * 0
     * ​
     *   来交换 00，p_2p
     * 2
     * ​
     *   来交换 22。此时，p_0p
     * 0
     * ​
     *   的初始值仍然为 00，而 p_2p
     * 2
     * ​
     *   的初始值为 n-1n−1。在遍历的过程中，我们需要找出所有的 00 交换至数组的头部，并且找出所有的 22 交换至数组的尾部。
     *
     * 由于此时其中一个指针 p_2p
     * 2
     * ​
     *   是从右向左移动的，因此当我们在从左向右遍历整个数组时，如果遍历到的位置超过了 p_2p
     * 2
     * ​
     *  ，那么就可以直接停止遍历了。
     *
     * 具体地，我们从左向右遍历整个数组，设当前遍历到的位置为 ii，对应的元素为 \textit{nums}[i]nums[i]；
     *
     * 如果找到了 00，那么与前面两种方法类似，将其与 \textit{nums}[p_0]nums[p
     * 0
     * ​
     *  ] 进行交换，并将 p_0p
     * 0
     * ​
     *   向后移动一个位置；
     *
     * 如果找到了 22，那么将其与 \textit{nums}[p_2]nums[p
     * 2
     * ​
     *  ] 进行交换，并将 p_2p
     * 2
     * ​
     *   向前移动一个位置。
     *
     * 这样做是正确的吗？可以发现，对于第二种情况，当我们将 \textit{nums}[i]nums[i] 与 \textit{nums}[p_2]nums[p
     * 2
     * ​
     *  ] 进行交换之后，新的 \textit{nums}[i]nums[i] 可能仍然是 22，也可能是 00。然而此时我们已经结束了交换，开始遍历下一个元素 \textit{nums}[i+1]nums[i+1]，不会再考虑 \textit{nums}[i]nums[i] 了，这样我们就会得到错误的答案。
     *
     * 因此，当我们找到 22 时，我们需要不断地将其与 \textit{nums}[p_2]nums[p
     * 2
     * ​
     *  ] 进行交换，直到新的 \textit{nums}[i]nums[i] 不为 22。此时，如果 \textit{nums}[i]nums[i] 为 00，那么对应着第一种情况；如果 \textit{nums}[i]nums[i] 为 11，那么就不需要进行任何后续的操作。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/sort-colors/solution/yan-se-fen-lei-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.7 MB
     * , 在所有 Java 提交中击败了
     * 91.76%
     * 的用户
     * 通过测试用例：
     * 87 / 87
     */
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }

}
