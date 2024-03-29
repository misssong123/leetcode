package com.meng.origin;

/**
 * 925. 长按键入
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 *
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 *
 *
 *
 * 示例 1：
 *
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 *
 * 示例 2：
 *
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 *
 * 示例 3：
 *
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 *
 * 示例 4：
 *
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 *
 *
 *
 * 提示：
 *
 *     name.length <= 1000
 *     typed.length <= 1000
 *     name 和 typed 的字符都是小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/long-pressed-name
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsLongPressedName_925 {
    /**
     * 1.依次截取name和typed的元素
     * 2.若截取的元素相同，则继续截取name和typed的下一个元素
     * 3.若不相同，则比较typed截取的元素是否与name截取的上一个元素相同
     * 4.若相同,则截取typed的下一个元素继续与name截取的元素比较，重复步骤3
     * 5.若不相同，则返回false
     * 6.若最后name的元素未被截取完，返回false
     * 7.若typed的元素未被截取完，则比较是否与name最后一个元素相同，不相同则返回false
     * 8.其余情况返回true
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int indexN =0 , indexT =0;
        char pre=' ',curN,curT;
        while(indexN<name.length() && indexT<typed.length()){
            curN = name.charAt(indexN);
            curT = typed.charAt(indexT);
            if (curN == curT){
                indexN ++;
                indexT ++;
                pre = curN;
            }else if(curT == pre){
                indexT++;
            }else {
                return false;
            }
        }
        if (indexN < name.length())
            return false;
        if (indexT < typed.length()){
            while (indexT < typed.length()){
                if (typed.charAt(indexT) != pre)
                    return false;
                indexT ++;
            }
        }
        return true;
    }
    /**
     * 官方解法
     * 方法一：双指针
     *
     * 思路与算法
     *
     * 根据题意能够分析得到：字符串 typed\textit{typed}typed 的每个字符，有且只有两种「用途」：
     *
     *     作为 name\textit{name}name 的一部分。此时会「匹配」name\textit{name}name 中的一个字符
     *
     *     作为长按键入的一部分。此时它应当与前一个字符相同。
     *
     * 如果 typed\textit{typed}typed 中存在一个字符，它两个条件均不满足，则应当直接返回 false\textit{false}false；否则，当 typed\textit{typed}typed 扫描完毕后，我们再检查 name\textit{name}name 的每个字符是否都被「匹配」了。
     *
     * 实现上，我们使用两个下标 i,ji,ji,j 追踪 name\textit{name}name 和 typed\textit{typed}typed 的位置。
     *
     *     当 name[i]=typed[j]\textit{name}[i]=\textit{typed}[j]name[i]=typed[j] 时，说明两个字符串存在一对匹配的字符，此时将 i,ji,ji,j 都加 111。
     *
     *     否则，如果 typed[j]=typed[j−1]\textit{typed}[j]=\textit{typed}[j-1]typed[j]=typed[j−1]，说明存在一次长按键入，此时只将 jjj 加 111。
     *
     * 最后，如果 i=name.lengthi=\textit{name}.\textit{length}i=name.length，说明 name\textit{name}name 的每个字符都被「匹配」了。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/long-pressed-name/solution/chang-an-jian-ru-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isLongPressedName1(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
