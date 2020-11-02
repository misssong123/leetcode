package com.meng;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intersection_349 {
    /**
     * 使用set特性进行解答
     * @param nums1
     * @param nums2
     * @return
     * 执行用时：3 ms, 在所有 Java 提交中击败了95.82% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了87.97% 的用户
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> temp = new HashSet<>();
        for(int num : nums1)
            set.add(num);
        for (int num : nums2){
            if (set.add(num)){
                set.remove(num);
            }else {
                temp.add(num);
            }
        }
        int [] ans = new int[temp.size()];
        int index = 0;
        for (int num : temp)
            ans[index++] = num;
        return ans;
    }
    /**
     * 官方解法1
     * 方法一：两个集合
     *
     * 计算两个数组的交集，直观的方法是遍历数组 nums1，对于其中的每个元素，遍历数组 nums2 判断该元素是否在数组 nums2 中，如果存在，则将该元素添加到返回值。假设数组 nums1 和 nums2 的长度分别是 mmm 和 nnn，则遍历数组 nums1 需要 O(m)O(m)O(m) 的时间，判断 nums1 中的每个元素是否在数组 nums2 中需要 O(n)O(n)O(n) 的时间，因此总时间复杂度是 O(mn)O(mn)O(mn)。
     *
     * 如果使用哈希集合存储元素，则可以在 O(1)O(1)O(1) 的时间内判断一个元素是否在集合中，从而降低时间复杂度。
     *
     * 首先使用两个集合分别存储两个数组中的元素，然后遍历较小的集合，判断其中的每个元素是否在另一个集合中，如果元素也在另一个集合中，则将该元素添加到返回值。该方法的时间复杂度可以降低到 O(m+n)O(m+n)O(m+n)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays/solution/liang-ge-shu-zu-de-jiao-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：3 ms, 在所有 Java 提交中击败了95.82% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了68.14% 的用户
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }

    public int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) {
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet = new HashSet<Integer>();
        for (int num : set1) {
            if (set2.contains(num)) {
                intersectionSet.add(num);
            }
        }
        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            intersection[index++] = num;
        }
        return intersection;
    }
    /**
     * 官方解法2
     * 方法二：排序 + 双指针
     *
     * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
     *
     * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。可以预见的是加入答案的数组的元素一定是递增的，为了保证加入元素的唯一性，我们需要额外记录变量 pre\textit{pre}pre 表示上一次加入答案数组的元素。
     *
     * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不相等，则将指向较小数字的指针右移一位，如果两个数字相等，且该数字不等于 pre\textit{pre}pre ，将该数字添加到答案并更新 pre\textit{pre}pre 变量，同时将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays/solution/liang-ge-shu-zu-de-jiao-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.97% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了89.35% 的用户
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

}
