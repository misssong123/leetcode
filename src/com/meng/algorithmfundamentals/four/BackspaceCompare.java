package com.meng.algorithmfundamentals.four;

/**
 * 844. 比较含退格的字符串
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 *
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ab#c", t = "ad#c"
 * 输出：true
 * 解释：s 和 t 都会变成 "ac"。
 * 示例 2：
 *
 * 输入：s = "ab##", t = "c#d#"
 * 输出：true
 * 解释：s 和 t 都会变成 ""。
 * 示例 3：
 *
 * 输入：s = "a#c", t = "b"
 * 输出：false
 * 解释：s 会变成 "c"，但 t 仍然是 "b"。
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 200
 * s 和 t 只含有小写字母以及字符 '#'
 *
 *
 * 进阶：
 *
 * 你可以用 O(n) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 */
public class BackspaceCompare {
    int s1 = 0;
    int t1 = 0;

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 45.83%
     * 的用户
     * 通过测试用例：
     * 113 / 113
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        s1 = s.length()-1;
        t1 = t.length()-1;

        while (s1 >=0 || t1 >=0){
            char sChar = getChar(s1, s,true);
            char tChar = getChar(t1, t,false);
            if (sChar != tChar){
                return false;
            }
            s1--;
            t1--;
        }
        return true;
    }
    private char getChar(int index,String s,boolean flag){
        int temp = 0;
        while (index>=0 && (temp >0 || s.charAt(index)=='#')){
            if (s.charAt(index) == '#'){
                temp++;
            }else {
                temp--;
            }
            index--;
        }
        if (flag){
            s1 = index;
        }else {
            t1 = index;
        }
        if (index>=0){
            return s.charAt(index);
        }
        return ' ';
    }

    public static void main(String[] args) {
        BackspaceCompare demo = new BackspaceCompare();
        System.out.println(demo.backspaceCompare("ab#d","ac#d"));
    }
    /**
     * 方法一：重构字符串
     * 思路及算法
     *
     * 最容易想到的方法是将给定的字符串中的退格符和应当被删除的字符都去除，还原给定字符串的一般形式。然后直接比较两字符串是否相等即可。
     *
     * 具体地，我们用栈处理遍历过程，每次我们遍历到一个字符：
     *
     * 如果它是退格符，那么我们将栈顶弹出；
     *
     * 如果它是普通字符，那么我们将其压入栈中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/backspace-string-compare/solution/bi-jiao-han-tui-ge-de-zi-fu-chuan-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param S
     * @param T
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 52.44%
     * 的用户
     * 内存消耗：
     * 39.6 MB
     * , 在所有 Java 提交中击败了
     * 12.62%
     * 的用户
     * 通过测试用例：
     * 113 / 113
     */
    public boolean backspaceCompare1(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String str) {
        StringBuffer ret = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            char ch = str.charAt(i);
            if (ch != '#') {
                ret.append(ch);
            } else {
                if (ret.length() > 0) {
                    ret.deleteCharAt(ret.length() - 1);
                }
            }
        }
        return ret.toString();
    }

    /**
     * 方法二：双指针
     * 思路及算法
     *
     * 一个字符是否会被删掉，只取决于该字符后面的退格符，而与该字符前面的退格符无关。因此当我们逆序地遍历字符串，就可以立即确定当前字符是否会被删掉。
     *
     * 具体地，我们定义 \textit{skip}skip 表示当前待删除的字符的数量。每次我们遍历到一个字符：
     *
     * 若该字符为退格符，则我们需要多删除一个普通字符，我们让 \textit{skip}skip 加 11；
     *
     * 若该字符为普通字符：
     *
     * 若 \textit{skip}skip 为 00，则说明当前字符不需要删去；
     *
     * 若 \textit{skip}skip 不为 00，则说明当前字符需要删去，我们让 \textit{skip}skip 减 11。
     *
     * 这样，我们定义两个指针，分别指向两字符串的末尾。每次我们让两指针逆序地遍历两字符串，直到两字符串能够各自确定一个字符，然后将这两个字符进行比较。重复这一过程直到找到的两个字符不相等，或遍历完字符串为止。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/backspace-string-compare/solution/bi-jiao-han-tui-ge-de-zi-fu-chuan-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param S
     * @param T
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.7 MB
     * , 在所有 Java 提交中击败了
     * 11.37%
     * 的用户
     * 通过测试用例：
     * 113 / 113
     */
    public boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }

}
