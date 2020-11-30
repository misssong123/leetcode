package com.meng;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ReorganizeString {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了58.78% 的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了70.36% 的用户
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        int len = S.length();
        if (len<2)
            return S;
        int [][]counts = new int[26][2];
        for(int i = 0 ; i < len ; i++){
            int index = S.charAt(i)-'a';
            counts[index][0] = index;
            counts[index][1]++;
        }
        Arrays.sort(counts, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });
        if (counts[0][1]>(len+1)/2)
            return "";
        char[] chars = new char[len];
        int index = 0;
        for(int i = 0 ; i < 26 ; i++){
            if (counts[i][1]==0)
                break;
            for (int j = 0 ; j < counts[i][1];j++){
                if (index>len-1){
                    index = 1;
                }
                chars[index]=(char)(counts[i][0]+'a');
                index+=2;
            }
        }
        return new String(chars);
    }
    /**
     * 官方解法1
     * 方法一：基于最大堆的贪心算法
     *
     * 维护最大堆存储字母，堆顶元素为出现次数最多的字母。首先统计每个字母的出现次数，然后将出现次数大于 000 的字母加入最大堆。
     *
     * 当最大堆的元素个数大于 111 时，每次从最大堆取出两个字母，拼接到重构的字符串，然后将两个字母的出现次数分别减 111，并将剩余出现次数大于 000 的字母重新加入最大堆。由于最大堆中的元素都是不同的，因此取出的两个字母一定也是不同的，将两个不同的字母拼接到重构的字符串，可以确保相邻的字母都不相同。
     *
     * 如果最大堆变成空，则已经完成字符串的重构。如果最大堆剩下 111 个元素，则取出最后一个字母，拼接到重构的字符串。
     *
     * 对于长度为 nnn 的字符串，共有 n/2n/2n/2 次每次从最大堆取出两个字母的操作，当 nnn 是奇数时，还有一次从最大堆取出一个字母的操作，因此重构的字符串的长度一定是 nnn。
     *
     * 当 nnn 是奇数时，是否可能出现重构的字符串的最后两个字母相同的情况？如果最后一个字母在整个字符串中至少出现了 222 次，则在最后一次从最大堆取出两个字母时，该字母会先被选出，因此不会成为重构的字符串的倒数第二个字母，也不可能出现重构的字符串最后两个字母相同的情况。
     *
     * 因此，在重构字符串可行的情况下，基于最大堆的贪心算法可以确保得到正确答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reorganize-string/solution/zhong-gou-zi-fu-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：3 ms, 在所有 Java 提交中击败了48.41% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了72.91% 的用户
     */
    public String reorganizeString1(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>() {
            public int compare(Character letter1, Character letter2) {
                return counts[letter2 - 'a'] - counts[letter1 - 'a'];
            }
        });
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (queue.size() > 1) {
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }
        }
        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }
    /**
     * 官方解法2
     * 方法二：基于计数的贪心算法
     *
     * 首先统计每个字母的出现次数，然后根据每个字母的出现次数重构字符串。
     *
     * 当 nnn 是奇数且出现最多的字母的出现次数是 (n+1)/2(n+1)/2(n+1)/2 时，出现次数最多的字母必须全部放置在偶数下标，否则一定会出现相邻的字母相同的情况。其余情况下，每个字母放置在偶数下标或者奇数下标都是可行的。
     *
     * 维护偶数下标 evenIndex\textit{evenIndex}evenIndex 和奇数下标 oddIndex\textit{oddIndex}oddIndex，初始值分别为 000 和 111。遍历每个字母，根据每个字母的出现次数判断字母应该放置在偶数下标还是奇数下标。
     *
     * 首先考虑是否可以放置在奇数下标。根据上述分析可知，只要字母的出现次数不超过字符串的长度的一半（即出现次数小于或等于 n/2n/2n/2），就可以放置在奇数下标，只有当字母的出现次数超过字符串的长度的一半时，才必须放置在偶数下标。字母的出现次数超过字符串的长度的一半只可能发生在 nnn 是奇数的情况下，且最多只有一个字母的出现次数会超过字符串的长度的一半。
     *
     * 因此通过如下操作在重构的字符串中放置字母。
     *
     *     如果字母的出现次数大于 000 且小于或等于 n/2n/2n/2，且 oddIndex\textit{oddIndex}oddIndex 没有超出数组下标范围，则将字母放置在 oddIndex\textit{oddIndex}oddIndex，然后将 oddIndex\textit{oddIndex}oddIndex 的值加 222。
     *
     *     如果字母的出现次数大于 n/2n/2n/2，或 oddIndex\textit{oddIndex}oddIndex 超出数组下标范围，则将字母放置在 evenIndex\textit{evenIndex}evenIndex，然后将 evenIndex\textit{evenIndex}evenIndex 的值加 222。
     *
     * 如果一个字母出现了多次，则重复上述操作，直到该字母全部放置完毕。
     *
     * 那么上述做法是否可以确保相邻的字母都不相同？考虑以下三种情况。
     *
     *     如果 nnn 是奇数且存在一个字母的出现次数为 (n+1)/2(n+1)/2(n+1)/2，则该字母全部被放置在偶数下标，其余的 (n−1)/2(n-1)/2(n−1)/2 个字母都被放置在奇数下标，因此相邻的字母一定不相同。
     *
     *     如果同一个字母全部被放置在奇数下标或全部被放置在偶数下标，则该字母不可能在相邻的下标出现。
     *
     *     如果同一个字母先被放置在奇数下标直到奇数下标超出数组下标范围，然后被放置在偶数下标，由于该字母的出现次数不会超过 n/2n/2n/2，因此该字母的最小奇数下标与最大偶数下标之差不小于 333，不可能在相邻的下标出现。证明如下：
     *         当 nnn 是偶数时，如果该字母的出现次数为 n/2n/2n/2，包括 ppp 个奇数下标和 qqq 个偶数下标，满足 p+q=n/2p+q=n/2p+q=n/2，最小奇数下标是 n−2p+1n-2p+1n−2p+1，最大偶数下标是 2(q−1)2(q-1)2(q−1)，最小奇数下标与最大偶数下标之差为：
     *
     *     (n−2p+1)−2(q−1)= n−2p+1−2q+2= n−2(p+q)+3= n−2×n2+3= n−n+3= 3\begin{aligned} & (n-2p+1)-2(q-1) \\ = &\ n-2p+1-2q+2 \\ = &\ n-2(p+q)+3 \\ = &\ n-2 \times \frac{n}{2}+3 \\ = &\ n-n+3 \\ = &\ 3 \end{aligned} =====​(n−2p+1)−2(q−1) n−2p+1−2q+2 n−2(p+q)+3 n−2×2n​+3 n−n+3 3​
     *         当 nnn 是奇数时，如果该字母的出现次数为 (n−1)/2(n-1)/2(n−1)/2，包括 ppp 个奇数下标和 qqq 个偶数下标，满足 p+q=(n−1)/2p+q=(n-1)/2p+q=(n−1)/2，最小奇数下标是 n−2pn-2pn−2p，最大偶数下标是 2(q−1)2(q-1)2(q−1)，最小奇数下标与最大偶数下标之差为：
     *
     *     (n−2p)−2(q−1)= n−2p−2q+2= n−2(p+q)+2= n−2×n−12+2= n−(n−1)+2= 3\begin{aligned} & (n-2p)-2(q-1) \\ = &\ n-2p-2q+2 \\ = &\ n-2(p+q)+2 \\ = &\ n-2 \times \frac{n-1}{2}+2 \\ = &\ n-(n-1)+2 \\ = &\ 3 \end{aligned} =====​(n−2p)−2(q−1) n−2p−2q+2 n−2(p+q)+2 n−2×2n−1​+2 n−(n−1)+2 3​
     *
     * 因此，在重构字符串可行的情况下，基于计数的贪心算法可以确保相邻的字母都不相同，得到正确答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reorganize-string/solution/zhong-gou-zi-fu-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.65% 的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了92.63% 的用户
     */
    public String reorganizeString2(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
    }
}
