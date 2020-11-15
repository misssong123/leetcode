package com.meng;

import java.util.Deque;
import java.util.LinkedList;

public class RemoveKdigits {
    /**
     * 1.获取第一个顶峰值的下标
     * 2.移除该元素
     * 3.判断该下标是否为0
     *    若为0，则继续寻找顶峰值
     *    若不为0，则从下标减一的位置继续寻找顶峰值
     * 注意:每次字符串的长度会发生变化
     *      得到的结果是否包含前导零
     * @param num
     * @param k
     * @return
     * 执行用时：3 ms, 在所有 Java 提交中击败了97.04% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了93.33% 的用户
     */
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (num.length()-k==0)
            return "0";
        int index = 0 ;
        StringBuffer sb = new StringBuffer(num);
        while (k!=0){
            while (index+1<len && sb.charAt(index+1)-sb.charAt(index)>=0 ){
                index++;
            }
            sb.delete(index,index+1);
            if (index>0)
                index--;
            k--;
            len--;
        }
        while (sb.indexOf("0")==0)
            sb.delete(0,1);
        if (sb.length()==0)
            return "0";
        return sb.toString();
    }

    /**
     * 官方解法
     * 方法一：贪心 + 单调栈
     *
     * 对于两个相同长度的数字序列，最左边不同的数字决定了这两个数字的大小，例如，对于 A=1axxx，B=1bxxx，如果 a>b 则 A>B。
     *
     * 基于此，我们可以知道，若要使得剩下的数字最小，需要保证靠前的数字尽可能小。
     让我们从一个简单的例子开始。给定一个数字序列，例如 425，如果要求我们只删除一个数字，那么从左到右，我们有 4、2 和 5 三个选择。我们将每一个数字和它的左邻居进行比较。从 2 开始，2小于它的左邻居 4。假设我们保留数字 4，那么所有可能的组合都是以数字 4（即 42，45）开头的。相反，如果移掉 4，留下 222，我们得到的是以 2 开头的组合（即 25），这明显小于任何留下数字 4 的组合。因此我们应该移掉数字 4。如果不移掉数字 4，则之后无论移掉什么数字，都不会得到最小数。

     基于上述分析，我们可以得出「删除一个数字」的贪心策略：

     给定一个长度为 n 的数字序列 [D0D1D2D3…Dn−1][D_0D_1D_2D_3\ldots D_{n-1}][D0​D1​D2​D3​…Dn−1​]，从左往右找到第一个位置 i（i>0）使得 Di<Di−1D_i<D_{i-1}Di​<Di−1​，并删去 Di−1D_{i-1}Di−1​；如果不存在，说明整个数字序列单调不降，删去最后一个数字即可。

     基于此，我们可以每次对整个数字序列执行一次这个策略；删去一个字符后，剩下的 n−1n-1n−1 长度的数字序列就形成了新的子问题，可以继续使用同样的策略，直至删除 kkk 次。

     然而暴力的实现复杂度最差会达到 O(nk)O(nk)O(nk)（考虑整个数字序列是单调不降的），因此我们需要加速这个过程。

     考虑从左往右增量的构造最后的答案。我们可以用一个栈维护当前的答案序列，栈中的元素代表截止到当前位置，删除不超过 kkk 次个数字后，所能得到的最小整数。根据之前的讨论：在使用 kkk 个删除次数之前，栈中的序列从栈底到栈顶单调不降。

     因此，对于每个数字，如果该数字小于栈顶元素，我们就不断地弹出栈顶元素，直到

     栈为空
     或者新的栈顶元素不大于当前数字
     或者我们已经删除了 k位数字

     上述步骤结束后我们还需要针对一些情况做额外的处理：

     如果我们删除了 m个数字且 m<km<km<k，这种情况下我们需要从序列尾部删除额外的 k−mk-mk−m 个数字。
     如果最终的数字序列存在前导零，我们要删去前导零。
     如果最终数字序列为空，我们应该返回 000。

     最终，从栈底到栈顶的答案序列即为最小数。

     考虑到栈的特点是后进先出，如果通过栈实现，则需要将栈内元素依次弹出然后进行翻转才能得到最小数。为了避免翻转操作，可以使用双端队列代替栈的实现。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *执行用时：8 ms, 在所有 Java 提交中击败了74.97% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了78.49% 的用户
     */
    public String removeKdigits1(String num, int k) {
        Deque<Character> deque = new LinkedList<Character>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }

        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }

    public static void main(String[] args) {
        RemoveKdigits demo = new RemoveKdigits();
        String num ="5337" ;
        int k =2;
        System.out.println(demo.removeKdigits(num,k));
    }
}
