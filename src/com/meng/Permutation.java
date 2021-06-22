package com.meng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 *
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 *
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 *
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 *
 *
 * 限制：
 *
 * 1 <= s 的长度 <= 8
 */
public class Permutation {
    List<String> rec;
    boolean[] vis;

    /**
     *方法一：回溯
     *
     * 思路及解法
     *
     * 我们将这个问题看作有 nnn 个排列成一行的空位，我们需要从左往右依次填入题目给定的 nnn 个字符，每个字符只能使用一次。首先可以想到穷举的算法，即从左往右每一个空位都依次尝试填入一个字符，看是否能填完这 nnn 个空位，编程实现时，我们可以用「回溯法」来模拟这个过程。
     *
     * 定义递归函数 backtrack(i,perm)\texttt{backtrack}(i, \textit{perm})backtrack(i,perm) 表示当前排列为 perm\textit{perm}perm，下一个待填入的空位是第 iii 个空位（下标从 000 开始）。那么该递归函数分为两个情况：
     *
     *     如果 i=ni=ni=n，说明我们已经填完了 nnn 个空位，找到了一个可行的解，我们将 perm\textit{perm}perm 放入答案数组中，递归结束。
     *
     *     如果 i<ni<ni<n，此时需要考虑第 iii 个空位填哪个字符。根据题目要求我们肯定不能填已经填过的字符，因此很容易想到的一个处理手段是我们定义一个标记数组 vis\textit{vis}vis 来标记已经填过的字符，那么在填第 iii 个字符的时候我们遍历题目给定的 nnn 个字符，如果这个字符没有被标记过，我们就尝试填入，并将其标记，继续尝试填下一个空位，即调用函数 backtrack(i+1,perm)\texttt{backtrack}(i + 1, \textit{perm})backtrack(i+1,perm)。回溯时，我们需要要撤销该空位填的字符以及对该字符的标记，并继续向当前空位尝试填入其他没被标记过的字符。
     *
     * 但是该递归函数并没有满足「全排列不重复」的要求，在重复的字符较多的情况下，该递归函数会生成大量重复的排列。对于任意一个空位，如果存在重复的字符，该递归函数会将它们重复填上去并继续尝试导致最后答案的重复。
     *
     * 解决该问题的一种较为直观的思路是，我们首先生成所有的排列，然后进行去重。而另一种思路是我们通过修改递归函数，使得该递归函数只会生成不重复的序列。
     *
     * 具体地，我们只要在递归函数中设定一个规则，保证在填每一个空位的时候重复字符只会被填入一次。具体地，我们首先对原字符串排序，保证相同的字符都相邻，在递归函数中，我们限制每次填入的字符一定是这个字符所在重复字符集合中「从左往右第一个未被填入的字符」，即如下的判断条件：
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/zi-fu-chuan-de-pai-lie-by-leetcode-solut-hhvs/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param s
     * @return
     * 执行用时：26 ms, 在所有 Java 提交中击败了40.12% 的用户
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了67.65% 的用户
     */
    public String[] permutation1(String s) {
        int n = s.length();
        rec = new ArrayList<String>();
        vis = new boolean[n];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuffer perm = new StringBuffer();
        backtrack(arr, 0, n, perm);
        int size = rec.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = rec.get(i);
        }
        return recArr;
    }

    public void backtrack(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) {
            rec.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            if (vis[j] || (j > 0 && !vis[j - 1] && arr[j - 1] == arr[j])) {
                continue;
            }
            vis[j] = true;
            perm.append(arr[j]);
            backtrack(arr, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            vis[j] = false;
        }
    }

    /**
     *方法二：下一个排列
     *
     * 思路及解法
     *
     * 我们可以这样思考：当我们已知了当前的一个排列，我们能不能快速得到字典序中下一个更大的排列呢？
     *
     * 答案是肯定的，参见「31. 下一个排列的官方题解」，当我们已知了当前的一个排列，我们可以在 O(n)O(n)O(n) 的时间内计算出字典序下一个中更大的排列。这与 C++\texttt{C++}C++ 中的 next_permutation\texttt{next\_permutation}next_permutation 函数功能相同。
     *
     * 具体地，我们首先对给定的字符串中的字符进行排序，即可得到当前字符串的第一个排列，然后我们不断地计算当前字符串的字典序中下一个更大的排列，直到不存在更大的排列为止即可。
     *
     * 这个方案的优秀之处在于，我们得到的所有排列都不可能重复，这样我们就无需进行去重的操作。同时因为无需使用回溯法，没有栈的开销，算法时间复杂度的常数较小。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/zi-fu-chuan-de-pai-lie-by-leetcode-solut-hhvs/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * @param s
     * @return
     * 执行用时：7 ms, 在所有 Java 提交中击败了98.80% 的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了81.58% 的用户
     */
    public String[] permutation2(String s) {
        List<String> ret = new ArrayList<String>();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        do {
            ret.add(new String(arr));
        } while (nextPermutation(arr));
        int size = ret.size();
        String[] retArr = new String[size];
        for (int i = 0; i < size; i++) {
            retArr[i] = ret.get(i);
        }
        return retArr;
    }

    public boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = arr.length - 1;
        while (j >= 0 && arr[i] >= arr[j]) {
            j--;
        }
        swap(arr, i, j);
        reverse(arr, i + 1);
        return true;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void reverse(char[] arr, int start) {
        int left = start, right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

}
