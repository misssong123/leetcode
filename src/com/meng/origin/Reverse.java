package com.meng.origin;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 *
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Reverse {
    /**
     * 思路
     * 记录上一次的结果 res
     * 计算每次与10的余数
     * 若（(res*10+余数)-余数）/10不等于res表示溢出
     * 每次更新x的值和res的值即可
     * @param x
     * @return
     */
    public int reverse(int x) {
        //最终结果
        int res =0;
        while (x!=0){
            //取余数
            int t =x%10;
            //本次应该反转后的结果
            int val = res*10+t;
            //不相等，表示溢出
            if ((val-t)/10 != res)
                return 0;
            //更新x值
            x = x/10;
            //更新res值
            res = val;
        }
        return res;
    }

    /**
     * 官方解法
     *方法：弹出和推入数字 & 溢出前进行检查
     *
     * 思路
     *
     * 我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。
     *
     * 算法
     *
     * 反转整数的方法可以与反转字符串进行类比。
     *
     * 我们想重复“弹出” xxx 的最后一位数字，并将它“推入”到 rev\text{rev}rev 的后面。最后，rev\text{rev}rev 将与 xxx 相反。
     *
     * 要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。
     *
     * //pop operation:
     * pop = x % 10;
     * x /= 10;
     *
     * //push operation:
     * temp = rev * 10 + pop;
     * rev = temp;
     *
     * 但是，这种方法很危险，因为当 temp=rev⋅10+pop\text{temp} = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop 时会导致溢出。
     *
     * 幸运的是，事先检查这个语句是否会导致溢出很容易。
     *
     * 为了便于解释，我们假设 rev\text{rev}rev 是正数。
     *
     *     如果 temp=rev⋅10+poptemp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop 导致溢出，那么一定有 rev≥INTMAX10\text{rev} \geq \frac{INTMAX}{10}rev≥10INTMAX​。
     *     如果 rev>INTMAX10\text{rev} > \frac{INTMAX}{10}rev>10INTMAX​，那么 temp=rev⋅10+poptemp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop 一定会溢出。
     *     如果 rev==INTMAX10\text{rev} == \frac{INTMAX}{10}rev==10INTMAX​，那么只要 pop>7\text{pop} > 7pop>7，temp=rev⋅10+poptemp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop 就会溢出。
     *
     * 当 rev\text{rev}rev 为负时可以应用类似的逻辑。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/reverse-integer/solution/zheng-shu-fan-zhuan-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param x
     * @return
     */
    public int reverse1(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

}
