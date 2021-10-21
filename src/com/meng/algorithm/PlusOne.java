package com.meng.algorithm;

import java.util.Arrays;

/**
 * 66. 加一
 * 难度
 * 简单
 *
 * 801
 *
 *
 *
 *
 *
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 */
public class PlusOne {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 37.1 MB
     * , 在所有 Java 提交中击败了
     * 6.23%
     * 的用户
     * 通过测试用例：
     * 111 / 111
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int num = 0;
        for(int i = len - 1 ; i >= 0 ; i--){
            int n = num + digits[i];
            if (i == len -1){
                n++;
            }
            digits[i] = n % 10;
            num = n / 10;
            if (num == 0){
                break;
            }
        }
        if (num != 0){
            int [] nums = new int[len+1];
            nums[0] = num;
            for(int i = 1 ; i <= len ; i++){
                nums[i] = digits[i-1];
            }
            digits = nums;
        }
        return digits;
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.9 MB
     * , 在所有 Java 提交中击败了
     * 46.01%
     * 的用户
     * 通过测试用例：
     * 111 / 111
     * @param digits
     * @return
     * 方法一：找出最长的后缀
     * 9
     * 9
     *
     * 思路
     *
     * 当我们对数组
     * digits
     * digits 加一时，我们只需要关注
     * digits
     * digits 的末尾出现了多少个
     * 9
     * 9 即可。我们可以考虑如下的三种情况：
     *
     * 如果
     * digits
     * digits 的末尾没有
     * 9
     * 9，例如
     * [
     * 1
     * ,
     * 2
     * ,
     * 3
     * ]
     * [1,2,3]，那么我们直接将末尾的数加一，得到
     * [
     * 1
     * ,
     * 2
     * ,
     * 4
     * ]
     * [1,2,4] 并返回；
     * 如果
     * digits
     * digits 的末尾有若干个
     * 9
     * 9，例如
     * [
     * 1
     * ,
     * 2
     * ,
     * 3
     * ,
     * 9
     * ,
     * 9
     * ]
     * [1,2,3,9,9]，那么我们只需要找出从末尾开始的第一个不为
     * 9
     * 9 的元素，即
     * 3
     * 3，将该元素加一，得到
     * [
     * 1
     * ,
     * 2
     * ,
     * 4
     * ,
     * 9
     * ,
     * 9
     * ]
     * [1,2,4,9,9]。随后将末尾的
     * 9
     * 9 全部置零，得到
     * [
     * 1
     * ,
     * 2
     * ,
     * 4
     * ,
     * 0
     * ,
     * 0
     * ]
     * [1,2,4,0,0] 并返回。
     * 如果
     * digits
     * digits 的所有元素都是
     * 9
     * 9，例如
     * [
     * 9
     * ,
     * 9
     * ,
     * 9
     * ,
     * 9
     * ,
     * 9
     * ]
     * [9,9,9,9,9]，那么答案为
     * [
     * 1
     * ,
     * 0
     * ,
     * 0
     * ,
     * 0
     * ,
     * 0
     * ,
     * 0
     * ]
     * [1,0,0,0,0,0]。我们只需要构造一个长度比
     * digits
     * digits 多
     * 1
     * 1 的新数组，将首元素置为
     * 1
     * 1，其余元素置为
     * 0
     * 0 即可。
     * 算法
     *
     * 们只需要对数组
     * digits
     * digits 进行一次逆序遍历，找出第一个不为
     * 9
     * 9 的元素，将其加一并将后续所有元素置零即可。如果
     * digits
     * digits 中所有的元素均为
     * 9
     * 9，那么对应着「思路」部分的第三种情况，我们需要返回一个新的数组。
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/plus-one/solution/jia-yi-by-leetcode-solution-2hor/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] plusOne1(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }

        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }

    public static void main(String[] args) {

    }
}
