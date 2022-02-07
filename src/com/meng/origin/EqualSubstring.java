package com.meng.origin;

/**
 * 1208. 尽可能使字符串相等
 *
 * 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abcd", t = "bcdf", cost = 3
 * 输出：3
 * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 *
 * 示例 2：
 *
 * 输入：s = "abcd", t = "cdef", cost = 3
 * 输出：1
 * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 *
 * 示例 3：
 *
 * 输入：s = "abcd", t = "acde", cost = 0
 * 输出：1
 * 解释：你无法作出任何改动，所以最大长度为 1。
 *
 *
 *
 * 提示：
 *
 *     1 <= s.length, t.length <= 10^5
 *     0 <= maxCost <= 10^6
 *     s 和 t 都只含小写英文字母。
 */
public class EqualSubstring {
    /**
     *1.右指针先移动,每移动依次maxCost减去差值，直到maxCost小于差值
     * 2.此时移动左指针，每移动依次maxCost加上left所在位置的差值，直到left等于right或者maxCost大于等于差值为止
     * 注意 若左指针等于右指针且maxCost小于差值，表示当前位置不符合条件，左指针的位置还需要向后移动移动
     * 否则count++，maxCost减去差值
     * 别忘记right++
     * @param s
     * @param t
     * @param maxCost
     * @return
     *执行用时：7 ms, 在所有 Java 提交中击败了63.13% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了75.69% 的用户
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length() ,max  = 0 ,count = 0,left = 0 ,right = 0;
        while (right < len){
            int diff = Math.abs(s.charAt(right) - t.charAt(right));
            if (maxCost >= diff){
                maxCost -= diff;
                right ++;
                count++;
            }else {
                max = Math.max(max,count);
                while (maxCost< diff && left<right){
                    count--;
                    maxCost+=Math.abs(s.charAt(left) - t.charAt(left));
                    left++;
                }
                if (left == right && maxCost < diff)
                    left++;
                else {
                    count++;
                    maxCost-=diff;
                }
                right++;
            }
        }
        max = Math.max(max,count);
        return max;
    }

    /**
     * 方法一：前缀和 + 二分查找
     *
     * 首先计算数组 diff\textit{diff}diff 的前缀和，创建长度为 n+1n+1n+1 的数组 accDiff\textit{accDiff}accDiff，其中 accDiff[0]=0\textit{accDiff}[0]=0accDiff[0]=0，对于 0≤i<n0 \le i< n0≤i<n，有 accDiff[i+1]=accDiff[i]+diff[i]\textit{accDiff}[i+1]=\textit{accDiff}[i]+\textit{diff}[i]accDiff[i+1]=accDiff[i]+diff[i]。
     *
     * 即当 1≤i≤n1 \le i \le n1≤i≤n 时，accDiff[i]\textit{accDiff}[i]accDiff[i] 为 diff\textit{diff}diff 从下标 000 到下标 i−1i-1i−1 的元素和：
     *
     * accDiff[i]=∑j=0i−1diff[j]\textit{accDiff}[i]=\sum\limits_{j=0}^{i-1} \textit{diff}[j] accDiff[i]=j=0∑i−1​diff[j]
     *
     * 当 diff\textit{diff}diff 的子数组以下标 jjj 结尾时，需要找到最小的下标 kkk（k≤jk \le jk≤j），使得 diff\textit{diff}diff 从下标 kkk 到 jjj 的元素和不超过 maxCost\textit{maxCost}maxCost，此时子数组的长度是 j−k+1j-k+1j−k+1。由于已经计算出前缀和数组 accDiff\textit{accDiff}accDiff，因此可以通过 accDiff\textit{accDiff}accDiff 得到 diff\textit{diff}diff 从下标 kkk 到 jjj 的元素和：
     *
     *  ∑i=kjdiff[k]=∑i=0jdiff[i]−∑i=0k−1diff[i]=accDiff[j+1]−accDiff[k]\begin{aligned} &\quad \ \sum\limits_{i=k}^j \textit{diff}[k] \\ &= \sum\limits_{i=0}^j \textit{diff}[i] - \sum\limits_{i=0}^{k-1} \textit{diff}[i] \\ &= \textit{accDiff}[j+1] - \textit{accDiff}[k] \end{aligned} ​ i=k∑j​diff[k]=i=0∑j​diff[i]−i=0∑k−1​diff[i]=accDiff[j+1]−accDiff[k]​
     *
     * 因此，找到最小的下标 kkk（k≤jk \le jk≤j），使得 diff\textit{diff}diff 从下标 kkk 到 jjj 的元素和不超过 maxCost\textit{maxCost}maxCost，等价于找到最小的下标 kkk（k≤jk \le jk≤j），使得 accDiff[j+1]−accDiff[k]≤maxCost\textit{accDiff}[j+1] - \textit{accDiff}[k] \le \textit{maxCost}accDiff[j+1]−accDiff[k]≤maxCost。
     *
     * 由于 diff\textit{diff}diff 的的每个元素都是非负的，因此 accDiff\textit{accDiff}accDiff 是递增的，对于每个下标 jjj，可以通过在 accDiff\textit{accDiff}accDiff 内进行二分查找的方法找到符合要求的最小的下标 kkk。
     *
     * 以下是具体实现方面的细节。
     *
     *     不需要计算数组 diff\textit{diff}diff 的元素值，而是可以根据字符串 sss 和 ttt 的对应位置字符直接计算 accDiff\textit{accDiff}accDiff 的元素值。
     *
     *     对于下标范围 [1,n][1,n][1,n] 内的每个 iii，通过二分查找的方式，在下标范围 [0,i][0,i][0,i] 内找到最小的下标 start\textit{start}start，使得 accDiff[start]≥accDiff[i]−maxCost\textit{accDiff}[\textit{start}] \ge \textit{accDiff}[i]-\textit{maxCost}accDiff[start]≥accDiff[i]−maxCost，此时对应的 diff\textit{diff}diff 的子数组的下标范围是从 start\textit{start}start 到 i−1i-1i−1，子数组的长度是 i−starti-\textit{start}i−start。
     *
     *     遍历下标范围 [1,n][1,n][1,n] 内的每个 iii 之后，即可得到符合要求的最长子数组的长度，即字符串可以转化的最大长度。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget/solution/jin-ke-neng-shi-zi-fu-chuan-xiang-deng-b-higz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：22 ms, 在所有 Java 提交中击败了6.95% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了16.79% 的用户
     *
     */
    public int equalSubstring1(String s, String t, int maxCost) {
        int n = s.length();
        int[] accDiff = new int[n + 1];
        for (int i = 0; i < n; i++) {
            accDiff[i + 1] = accDiff[i] + Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        for (int i = 1; i <= n; i++) {
            int start = binarySearch(accDiff, i, accDiff[i] - maxCost);
            maxLength = Math.max(maxLength, i - start);
        }
        return maxLength;
    }

    public int binarySearch(int[] accDiff, int endIndex, int target) {
        int low = 0, high = endIndex;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (accDiff[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    /**
     * 方法二：双指针
     *
     * 由于 diff\textit{diff}diff 的的每个元素都是非负的，因此可以用双指针的方法得到符合要求的最长子数组的长度。
     *
     * 双指针法的思想是，维护两个指针 start\textit{start}start 和 end\textit{end}end 表示数组 diff\textit{diff}diff 的子数组的开始下标和结束下标，满足子数组的元素和不超过 maxCost\textit{maxCost}maxCost，子数组的长度是 end−start+1\textit{end}-\textit{start}+1end−start+1。初始时，start\textit{start}start 和 end\textit{end}end 的值都是 000。
     *
     * 另外还要维护子数组的元素和 sum\textit{sum}sum，初始值为 000。在移动两个指针的过程中，更新 sum\textit{sum}sum 的值，判断子数组的元素和是否大于 maxCost\textit{maxCost}maxCost，并决定应该如何移动指针。
     *
     * 为了得到符合要求的最长子数组的长度，应遵循以下两点原则：
     *
     *     当 start\textit{start}start 的值固定时，end\textit{end}end 的值应尽可能大；
     *
     *     当 end\textit{end}end 的值固定时，start\textit{start}start 的值应尽可能小。
     *
     * 基于上述原则，双指针的做法如下：
     *
     *     将 diff[end]\textit{diff}[\textit{end}]diff[end] 的值加到 sum\textit{sum}sum；
     *
     *     如果 sum≤maxCost\textit{sum} \le \textit{maxCost}sum≤maxCost，则子数组的元素和不超过 maxCost\textit{maxCost}maxCost，使用当前子数组的长度 end−start+1\textit{end}-\textit{start}+1end−start+1 更新最大子数组的长度；
     *
     *     如果 sum>maxCost\textit{sum}>\textit{maxCost}sum>maxCost，则子数组的元素和大于 maxCost\textit{maxCost}maxCost，需要向右移动指针 start\textit{start}start 并同时更新 sum\textit{sum}sum 的值，直到 sum≤maxCost\textit{sum} \le \textit{maxCost}sum≤maxCost，此时子数组的元素和不超过 maxCost\textit{maxCost}maxCost，使用子数组的长度 end−start+1\textit{end}-\textit{start}+1end−start+1 更新最大子数组的长度；
     *
     *     将指针 end\textit{end}end 右移一位，重复上述步骤，直到 end\textit{end}end 超出数组下标范围。
     *
     * 遍历结束之后，即可得到符合要求的最长子数组的长度，即字符串可以转化的最大长度。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget/solution/jin-ke-neng-shi-zi-fu-chuan-xiang-deng-b-higz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：5 ms, 在所有 Java 提交中击败了94.05% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了81.01% 的用户
     */
    public int equalSubstring2(String s, String t, int maxCost) {
        int n = s.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += diff[end];
            while (sum > maxCost) {
                sum -= diff[start];
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;
    }


    public static void main(String[] args) {
        String s = "krpgjbjjznpzdfy";
        String t = "nxargkbydxmsgby";
        int maxCost = 14;
        EqualSubstring demo = new EqualSubstring();
        System.out.println(demo.equalSubstring(s,t,maxCost));
    }
}
