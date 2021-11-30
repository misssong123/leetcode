package com.meng.algorithm;

/**
 * 400. 第 N 位数字
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 *
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 */
public class FindNthDigit400 {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 35.1 MB
     * , 在所有 Java 提交中击败了
     * 72.85%
     * 的用户
     * 通过测试用例：
     * 71 / 71
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        if (n <= 9){
            return n;
        }
        n = n - 9;
        int len = 2 , num = 90;
        while (n - (long)len * num > 0){
            n = n - len * num;
            len++;
            num*=10;
        }
        int nums =  n % len == 0 ? n / len : n / len +1;
        int index = n%len == 0 ? 0 : len - n % len;
        int res = 1;
        for(int i = 1 ; i < len ; i++){
            res *= 10;
        }
        res = res + nums-1;
        System.out.println(index + ","+ res);
        for(int i = 0 ; i <= index ; i++){
            num = res % 10;
            res /= 10;
        }
        return  num;
    }

    /**
     * 方法一：二分查找
     * 为了得到无限整数序列中的第 nn 位数字，需要知道第 nn 位数字是哪一个整数的第几位。如果知道第 nn 位数字所在整数是几位数，就能计算得到第 nn 位数字是哪一个整数的第几位。
     *
     * 假设第 nn 位数字所在整数是 dd 位数，则所有位数不超过 d - 1d−1 的整数的位数之和小于 nn，且所有位数不超过 dd 的整数的位数之和大于等于 nn。由于所有位数不超过 xx 的整数的位数之和关于 xx 单调递增，因此可以使用二分查找确定上述 dd 的值。对于某个 xx，如果所有位数不超过 xx 的整数的位数之和小于 nn，则 d > xd>x，否则 d \le xd≤x，以此调整二分查找的上下界。
     *
     * 由于任何整数都至少是一位数，因此 dd 的最小值是 11。对于 dd 的上界，可以通过找规律的方式确定。
     *
     * 11 位数的范围是 11 到 99，共有 99 个数，所有 11 位数的位数之和是 1 \times 9 = 91×9=9。
     * 22 位数的取值范围是 1010 到 9999，共有 9090 个数，所有 22 位数的位数之和是 2 \times 90 = 1802×90=180。
     * 33 位数的取值范围是 100100 到 999999，共有 900900 个数，所有 33 位数的位数之和是 3 \times 900 = 27003×900=2700。
     * ……
     * 推广到一般情形，xx 位数的范围是 10^{x - 1}10
     * x−1
     *   到 10^x - 110
     * x
     *  −1，共有 10^x - 1 - 10^{x - 1} + 1 = 9 \times 10^{x - 1}10
     * x
     *  −1−10
     * x−1
     *  +1=9×10
     * x−1
     *   个数，所有 xx 位数的位数之和是 x \times 9 \times 10^{x - 1}x×9×10
     * x−1
     *  。
     *
     * 由于 nn 的最大值为 2^{31} - 12
     * 31
     *  −1，约为 2 \times 10^92×10
     * 9
     *  ，当 x = 9x=9 时，x \times 9 \times 10^{x - 1} = 8.1 \times 10^9 > 2^{31} - 1x×9×10
     * x−1
     *  =8.1×10
     * 9
     *  >2
     * 31
     *  −1，因此第 nn 位数字所在整数最多是 99 位数，令 dd 的上界为 99 即可。
     *
     * 在得到 dd 的值之后，可以根据上述规律计算得到所有位数不超过 d - 1d−1 的整数的位数之和，然后得到第 nn 位数在所有 dd 位数的序列中的下标，为了方便计算，将下标转换成从 00 开始记数。具体而言，用 \textit{prevDigits}prevDigits 表示所有位数不超过 d - 1d−1 的整数的位数之和，则第 nn 位数在所有 dd 位数的序列中的下标是 \textit{index} = n - \textit{prevDigits} - 1index=n−prevDigits−1，\textit{index}index 的最小可能取值是 00。
     *
     * 得到下标 \textit{index}index 之后，可以得到无限整数序列中的第 nn 位数字是第 \Big\lfloor \dfrac{\textit{index}}{d} \Big\rfloor⌊
     * d
     * index
     * ​
     *  ⌋ 个 dd 位数的第 \textit{index} \bmod dindexmodd 位，注意编号都从 00 开始。
     *
     * 由于最小的 dd 位数是 10^{d - 1}10
     * d−1
     *  ，因此第 nn 位数字所在的整数是 10^{d - 1} + \Big\lfloor \dfrac{\textit{index}}{d} \Big\rfloor10
     * d−1
     *  +⌊
     * d
     * index
     * ​
     *  ⌋，该整数的右边第 d - (\textit{index} \bmod d) - 1d−(indexmodd)−1 位（计数从 00 开始）即为无限整数序列中的第 nn 位数字。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/nth-digit/solution/di-n-wei-shu-zi-by-leetcode-solution-mdl2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 35.2 MB
     * , 在所有 Java 提交中击败了
     * 58.90%
     * 的用户
     * 通过测试用例：
     * 71 / 71
     */
    public int findNthDigit1(int n) {
        int low = 1, high = 9;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (totalDigits(mid) < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        int d = low;
        int prevDigits = totalDigits(d - 1);
        int index = n - prevDigits - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;
        int digitIndex = index % d;
        int digit = (num / (int) (Math.pow(10, d - digitIndex - 1))) % 10;
        return digit;
    }

    public int totalDigits(int length) {
        int digits = 0;
        int curLength = 1, curCount = 9;
        while (curLength <= length) {
            digits += curLength * curCount;
            curLength++;
            curCount *= 10;
        }
        return digits;
    }

    /**
     * 方法二：直接计算
     * 方法一使用二分查找得到第 nn 位数字所在整数是几位数。也可以不使用二分查找，而是直接根据规律计算。
     *
     * 已知 xx 位数共有 9 \times 10^{x - 1}9×10
     * x−1
     *   个，所有 xx 位数的位数之和是 x \times 9 \times 10^{x - 1}x×9×10
     * x−1
     *  。使用 dd 和 \textit{count}count 分别表示当前遍历到的位数和当前位数下的所有整数的位数之和，初始时 d = 1d=1，\textit{count} = 9count=9。每次将 nn 减去 d \times \textit{count}d×count，然后将 dd 加 11，将 \textit{count}count 乘以 1010，直到 n \le d \times \textit{count}n≤d×count，此时的 dd 是目标数字所在整数的位数，nn 是所有 dd 位数中从第一位到目标数字的位数。
     *
     * 为了方便计算目标数字，使用目标数字在所有 dd 位数中的下标进行计算，下标从 00 开始计数。令 \textit{index} = n - 1index=n−1，则 \textit{index}index 即为目标数字在所有 dd 位数中的下标，\textit{index}index 的最小可能取值是 00。
     *
     * 得到下标 \textit{index}index 之后，即可使用方法一的做法得到无限整数序列中的第 nn 位数字。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/nth-digit/solution/di-n-wei-shu-zi-by-leetcode-solution-mdl2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 35.2 MB
     * , 在所有 Java 提交中击败了
     * 57.29%
     * 的用户
     * 通过测试用例：
     * 71 / 71
     */
    public int findNthDigit2(int n) {
        int d = 1, count = 9;
        while (n > (long) d * count) {
            n -= d * count;
            d++;
            count *= 10;
        }
        int index = n - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;

        int digitIndex = index % d;
        System.out.println(digitIndex+","+num);
        int digit = (num / (int)(Math.pow(10, d - digitIndex - 1))) % 10;
        return digit;
    }

    public static void main(String[] args) {
        FindNthDigit400 demo = new FindNthDigit400();
        System.out.println(demo.findNthDigit(1000000000));
        System.out.println("--------------------------");
        System.out.println(demo.findNthDigit2(1000000000));
    }
}
