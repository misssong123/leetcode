package com.meng.hot100;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 76. 最小覆盖子串(困难)
 * 困难
 * 2.2K
 * 相关企业
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 *
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */
public class T032MinWindow {
    /**
     * 超出时间限制
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int sLen = s.length(),tLen = t.length();
        if (sLen < tLen){
            return "";
        }
        Map<Character,Integer> target = new HashMap<>();
        for(char c : t.toCharArray()){
            target.put(c,target.getOrDefault(c,0)+1);
        }
        int left = 0 , right = tLen - 1;
        Map<Character,Integer> curr = new HashMap<>();
        String answer = "";
        for(int i = left ; i <= right ; i++){
            char  c = s.charAt(i);
            curr.put(c,curr.getOrDefault(c,0)+1);
        }
        boolean isContain = true;
        while (right < sLen){
            if (isContain && check(curr,target)){
                System.out.println(s.substring(left,right+1));
                while (left < right && curr.get(s.charAt(left)) > target.getOrDefault(s.charAt(left),-1)){
                    char c = s.charAt(left);
                    curr.put(c,curr.get(c)-1);
                    left++;
                }
                if (answer.equals("") || answer.length() > (right - left)){
                    answer = s.substring(left,right+1);
                }
                left++;
                char c = s.charAt(left);
                curr.put(c,curr.get(c)-1);
            }
            right++;
            isContain = false;
            if (right < sLen){
                char c = s.charAt(right);
                if (target.get(c) != null){
                    isContain = true;
                }
                curr.put(c,curr.getOrDefault(c,0)+1);
            }
        }
        return answer;
    }

    private boolean check(Map<Character, Integer> curr, Map<Character, Integer> target) {
        for(Map.Entry<Character, Integer> entry: target.entrySet()){
            if (curr.getOrDefault(entry.getKey(),0)  < entry.getValue()){
                return false;
            }
        }
        System.out.println(curr);
        return true;
    }

    public static void main(String[] args) {
        T032MinWindow demo = new T032MinWindow();
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(demo.minWindow(s,t));
    }
    /**
     * 方法一：滑动窗口
     * 思路和算法
     *
     * 本问题要求我们返回字符串 sss 中包含字符串 ttt 的全部字符的最小窗口。我们称包含 ttt 的全部字母的窗口为「可行」窗口。
     *
     * 我们可以用滑动窗口的思想解决这个问题。在滑动窗口类型的问题中都会有两个指针，一个用于「延伸」现有窗口的 rrr 指针，和一个用于「收缩」窗口的 lll 指针。在任意时刻，只有一个指针运动，而另一个保持静止。我们在 sss 上滑动窗口，通过移动 rrr 指针不断扩张窗口。当窗口包含 ttt 全部所需的字符后，如果能收缩，我们就收缩窗口直到得到最小窗口。
     *
     *
     *
     * 如何判断当前的窗口包含所有 ttt 所需的字符呢？我们可以用一个哈希表表示 ttt 中所有的字符以及它们的个数，用一个哈希表动态维护窗口中所有的字符以及它们的个数，如果这个动态表中包含 ttt 的哈希表中的所有字符，并且对应的个数都不小于 ttt 的哈希表中各个字符的个数，那么当前的窗口是「可行」的。
     *
     * 注意：这里 ttt 中可能出现重复的字符，所以我们要记录字符的个数。
     *
     * 考虑如何优化？ 如果 s=XX⋯XABCXXXXs = {\rm XX \cdots XABCXXXX}s=XX⋯XABCXXXX，t=ABCt = {\rm ABC}t=ABC，那么显然 [XX⋯XABC]{\rm [XX \cdots XABC]}[XX⋯XABC] 是第一个得到的「可行」区间，得到这个可行区间后，我们按照「收缩」窗口的原则更新左边界，得到最小区间。我们其实做了一些无用的操作，就是更新右边界的时候「延伸」进了很多无用的 X\rm XX，更新左边界的时候「收缩」扔掉了这些无用的 X\rm XX，做了这么多无用的操作，只是为了得到短短的 ABC\rm ABCABC。没错，其实在 sss 中，有的字符我们是不关心的，我们只关心 ttt 中出现的字符，我们可不可以先预处理 sss，扔掉那些 ttt 中没有出现的字符，然后再做滑动窗口呢？也许你会说，这样可能出现 XXABXXC\rm XXABXXCXXABXXC 的情况，在统计长度的时候可以扔掉前两个 X\rm XX，但是不扔掉中间的 X\rm XX，怎样解决这个问题呢？优化后的时空复杂度又是多少？这里代码给出没有优化的版本，以上的三个问题留给读者思考，欢迎大家在评论区给出答案哟。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/minimum-window-substring/solutions/257359/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 时间
     * 161 ms
     * 击败
     * 5.5%
     * 内存
     * 42.4 MB
     * 击败
     * 21.54%
     */
    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();
    public String minWindow1(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

}
