package com.meng.origin;

import java.util.*;

/**
 * 给你两个数组，arr1 和 arr2，
 *
 *     arr2 中的元素各不相同
 *     arr2 中的每个元素都出现在 arr1 中
 *
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 *
 *
 * 示例：
 *
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 *
 *
 * 提示：
 *
 *     arr1.length, arr2.length <= 1000
 *     0 <= arr1[i], arr2[i] <= 1000
 *     arr2 中的元素 arr2[i] 各不相同
 *     arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-sort-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RelativeSortArray {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了39.49% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了58.39% 的用户
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1==null || arr1.length<2)
            return arr1;
        if (arr2 == null || arr2.length == 0){
            Arrays.sort(arr1);
            return arr1;
        }
        int index = 0;
        List<Integer> list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i : arr1)
            map.put(i,map.getOrDefault(i,0)+1);
        for(int i : arr2){
            for(int j = 0 ; j<map.get(i) ; j++){
                arr1[index++] = i;
            }
            map.remove(i);
        }
        if (map.size()!=0){
            for(int key : map.keySet()){
                for (int j = 0 ; j< map.get(key);j++)
                    list.add(key);
            }
            Collections.sort(list);
            for(int i : list)
                arr1[index++] = i;
        }
        return arr1;
    }

    /**
     * 执行用时：35 ms, 在所有 Java 提交中击败了5.14% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了50.31% 的用户
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for(int i : arr2)
            list.add(i);
        for(int i : arr1)
            ans.add(i);
        Collections.sort(ans, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                int m = list.indexOf(x),n=list.indexOf(y);
                if (m!=-1 || n!=-1){
                    if (m!=-1 &&  n!=-1)
                        return m-n;
                    else if(m!=-1)
                        return -1;
                    else
                        return 1;
                }else
                    return x-y;
            }
        });
        int index = 0;
        for(int i : ans)
            arr1[index++]= i;
        return arr1;
    }
    /**
     * 官方解法1
     * 方法一：自定义排序
     *
     * 一种容易想到的方法是使用排序并自定义比较函数。
     *
     * 由于数组 arr2\textit{arr}_2arr2​ 规定了比较顺序，因此我们可以使用哈希表对该顺序进行映射：即对于数组 arr2\textit{arr}_2arr2​ 中的第 iii 个元素，我们将 (arr2[i],i)(\textit{arr}_2[i], i)(arr2​[i],i) 这一键值对放入哈希表 rank\textit{rank}rank 中，就可以很方便地对数组 arr1\textit{arr}_1arr1​ 中的元素进行比较。
     *
     * 比较函数的写法有很多种，例如我们可以使用最基础的比较方法，对于元素 xxx 和 yyy：
     *
     *     如果 xxx 和 yyy 都出现在哈希表中，那么比较它们对应的值 rank[x]\textit{rank}[x]rank[x] 和 rank[y]\textit{rank}[y]rank[y]；
     *
     *     如果 xxx 和 yyy 都没有出现在哈希表中，那么比较它们本身；
     *
     *     对于剩余的情况，出现在哈希表中的那个元素较小。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/relative-sort-array/solution/shu-zu-de-xiang-dui-pai-xu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    /**
     * 官方解法1
     * 方法二：计数排序
     *
     * 思路与算法
     *
     * 注意到本题中元素的范围为 [0,1000][0, 1000][0,1000]，这个范围不是很大，我们也可以考虑不基于比较的排序，例如「计数排序」。
     *
     * 具体地，我们使用一个长度为 100110011001（下标从 000 到 100010001000）的数组 frequency\textit{frequency}frequency，记录每一个元素在数组 arr1\textit{arr}_1arr1​ 中出现的次数。随后我们遍历数组 arr2\textit{arr}_2arr2​，当遍历到元素 xxx 时，我们将 frequency[x]\textit{frequency}[x]frequency[x] 个 xxx 加入答案中，并将 frequency[x]\textit{frequency}[x]frequency[x] 清零。当遍历结束后，所有在 arr2\textit{arr}_2arr2​ 中出现过的元素就已经有序了。
     *
     * 此时还剩下没有在 arr2\textit{arr}_2arr2​ 中出现过的元素，因此我们还需要对整个数组 frequency\textit{frequency}frequency 进行一次遍历。当遍历到元素 xxx 时，如果 frequency[x]\textit{frequency}[x]frequency[x] 不为 000，我们就将 frequency[x]\textit{frequency}[x]frequency[x] 个 xxx 加入答案中。
     *
     * 细节
     *
     * 我们可以对空间复杂度进行一个小优化。实际上，我们不需要使用长度为 100110011001 的数组，而是可以找出数组 arr1\textit{arr}_1arr1​ 中的最大值 upper\textit{upper}upper，使用长度为 upper+1\textit{upper}+1upper+1 的数组即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/relative-sort-array/solution/shu-zu-de-xiang-dui-pai-xu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了49.69% 的用户
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) {
            upper = Math.max(upper, x);
        }
        int[] frequency = new int[upper + 1];
        for (int x : arr1) {
            ++frequency[x];
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; ++x) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
        }
        return ans;
    }
}
