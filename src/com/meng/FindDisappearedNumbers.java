package com.meng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 *
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 */
public class FindDisappearedNumbers {
    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了98.85% 的用户
     * 内存消耗：47.5 MB, 在所有 Java 提交中击败了28.20% 的用户
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int [] index = new int[nums.length];
        for(int i = 0 ; i < nums .length ; i++)
            ++index[nums[i]-1];
        for(int i = 0  ; i < nums.length ; i++){
            if (index[i]==0)
                list.add(i+1);
        }
        return list;
    }

    /**
     * 方法一：原地修改
     *
     * 思路及解法
     *
     * 我们可以用一个哈希表记录数组 nums\textit{nums}nums 中的数字，由于数字范围均在 [1,n][1,n][1,n] 中，记录数字后我们再利用哈希表检查 [1,n][1,n][1,n] 中的每一个数是否出现，从而找到缺失的数字。
     *
     * 由于数字范围均在 [1,n][1,n][1,n] 中，我们也可以用一个长度为 nnn 的数组来代替哈希表。这一做法的空间复杂度是 O(n)O(n)O(n) 的。我们的目标是优化空间复杂度到 O(1)O(1)O(1)。
     *
     * 注意到 nums\textit{nums}nums 的长度恰好也为 nnn，能否让 nums\textit{nums}nums 充当哈希表呢？
     *
     * 由于 nums\textit{nums}nums 的数字范围均在 [1,n][1,n][1,n] 中，我们可以利用这一范围之外的数字，来表达「是否存在」的含义。
     *
     * 具体来说，遍历 nums\textit{nums}nums，每遇到一个数 xxx，就让 nums[x−1]\textit{nums}[x-1]nums[x−1] 增加 nnn。由于 nums\textit{nums}nums 中所有数均在 [1,n][1,n][1,n] 中，增加以后，这些数必然大于 nnn。最后我们遍历 nums\textit{nums}nums，若 nums[i]\textit{nums}[i]nums[i] 未大于 nnn，就说明没有遇到过数 i+1i+1i+1。这样我们就找到了缺失的数字。
     *
     * 注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 nnn 取模来还原出它本来的值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/zhao-dao-suo-you-shu-zu-zhong-xiao-shi-d-mabl/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：4 ms, 在所有 Java 提交中击败了98.85% 的用户
     * 内存消耗：47.3 MB, 在所有 Java 提交中击败了56.25% 的用户
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
