package com.meng.algorithm;

/**
 * 1894. 找到需要补充粉笔的学生编号
 * 难度
 * 中等
 *
 * 43
 *
 * 收藏
 *
 * 分享
 * 切换为英文
 * 接收动态
 * 反馈
 * 一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，直到编号为 n - 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。
 *
 * 给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，他会消耗 chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。
 *
 * 请你返回需要 补充 粉笔的学生 编号 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：chalk = [5,1,5], k = 22
 * 输出：0
 * 解释：学生消耗粉笔情况如下：
 * - 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
 * - 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
 * - 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
 * - 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
 * - 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
 * - 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
 * 编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。
 * 示例 2：
 *
 * 输入：chalk = [3,4,1,2], k = 25
 * 输出：1
 * 解释：学生消耗粉笔情况如下：
 * - 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。
 * - 编号为 1 的学生使用 4 支粉笔，然后 k = 18 。
 * - 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。
 * - 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。
 * - 编号为 0 的学生使用 3 支粉笔，然后 k = 12 。
 * - 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。
 * - 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。
 * - 编号为 3 的学生使用 2 支粉笔，然后 k = 5 。
 * - 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。
 * 编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
 *
 *
 * 提示：
 *
 * chalk.length == n
 * 1 <= n <= 105
 * 1 <= chalk[i] <= 105
 * 1 <= k <= 109
 */
public class ChalkReplacer {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了84.92%的用户
     * 内存消耗：54.5 MB, 在所有 Java 提交中击败了40.95%的用户
     * @param chalk
     * @param k
     * @return
     */
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        if (len == 1){
            return 0;
        }
        int sum = 0 ;
        for(int i = 0 ; i < len ; i++){
            if (chalk[i] <= k){
                k -= chalk[i];
                sum += chalk[i];
            }else {
                return i;
            }
        }
        k = k % sum;
        for(int i = 0 ; i < len ; i++){
            if (chalk[i] <= k){
                k -= chalk[i];
                sum += chalk[i];
            }else {
                return i;
            }
        }
        return 0;
    }

    /**
     * 方法一：优化的模拟
     *
     * 思路与算法
     *
     * 学生消耗粉笔的过程是重复的。记每一轮消耗粉笔的总量为
     * total
     * total，它等于数组
     * chalk
     * chalk 的元素之和。因此，我们可以将粉笔数量
     * k
     * k 对
     * total
     * total 进行取模，求得余数
     * k
     * ′
     * k
     * ′
     *   以方便后续计算。由于
     * k
     * ′
     * k
     * ′
     *   一定小于
     * total
     * total，因此我们只需要至多遍历一遍数组
     * chalk
     * chalk，同时模拟
     * k
     * ′
     * k
     * ′
     *   减小的过程，即可以得到需要补充粉笔的学生编号。
     *
     * 细节
     *
     * 由于
     * total
     * total 可能会超过
     * 32
     * 32 位有符号整数的范围，因此对于一些整数类型有范围的语言，为了避免溢出，需要使用
     * 64
     * 64 位整数存储
     * total
     * total。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk/solution/zhao-dao-xu-yao-bu-chong-fen-bi-de-xue-s-qrn1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param chalk
     * @param k
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.90%
     * 的用户
     * 内存消耗：
     * 54.4 MB
     * , 在所有 Java 提交中击败了
     * 44.82%
     * 的用户
     */
    public int chalkReplacer1(int[] chalk, int k) {
        int n = chalk.length;
        long total = 0;
        for (int num : chalk) {
            total += num;
        }
        k %= total;
        int res = -1;
        for (int i = 0; i < n; ++i) {
            if (chalk[i] > k) {
                res = i;
                break;
            }
            k -= chalk[i];
        }
        return res;
    }

    /**
     * 方法二：前缀和 + 二分查找
     *
     * 思路与算法
     *
     * 对于方法一中的第二次遍历，我们也可以使用二分查找进行加速。
     *
     * 在对数组
     * chalk
     * chalk 的遍历过程中，我们可以求出其前缀和，记为数组
     * pre
     * pre。那么需要补充粉笔的学生编号
     * i
     * ′
     * i
     * ′
     *   是最小的满足
     * pre
     * [
     * i
     * ]
     * >
     * k
     * ′
     * pre[i]>k
     * ′
     *   的下标
     * i
     * ′
     * i
     * ′
     *  ，可以通过二分查找在
     * O
     * (
     * log
     * ⁡
     * n
     * )
     * O(logn) 的时间内找出。
     *
     * 细节
     *
     * 由于前缀和数组中的元素可能会超过
     * 32
     * 32 位整数的范围，因此不能直接在原数组上计算并更新前缀和。但可以注意到的是，本题中
     * k
     * ≤
     * 1
     * 0
     * 9
     * k≤10
     * 9
     *  ，因此在计算前缀和数组的过程中，如果超过了
     * k
     * k，说明我们找到了需要补充粉笔的学生编号，此时就无需继续计算下去，那么也就不会超过
     * 32
     * 32 位整数的范围了。
     *
     * 代码
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk/solution/zhao-dao-xu-yao-bu-chong-fen-bi-de-xue-s-qrn1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param chalk
     * @param k
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 84.92%
     * 的用户
     * 内存消耗：
     * 54.1 MB
     * , 在所有 Java 提交中击败了
     * 76.76%
     * 的用户
     */
    public int chalkReplacer2(int[] chalk, int k) {
        int n = chalk.length;
        if (chalk[0] > k) {
            return 0;
        }
        for (int i = 1; i < n; ++i) {
            chalk[i] += chalk[i - 1];
            if (chalk[i] > k) {
                return i;
            }
        }

        k %= chalk[n - 1];
        return binarySearch(chalk, k);
    }

    public int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
