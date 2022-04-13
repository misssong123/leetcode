package com.meng.dynamicprogramming.three03;

import java.util.*;
/**
 * 740. 删除并获得点数
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 *
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class DeleteAndEarn {
    /**
     * 执行用时：
     * 14 ms
     * , 在所有 Java 提交中击败了
     * 5.08%
     * 的用户
     * 内存消耗：
     * 42.3 MB
     * , 在所有 Java 提交中击败了
     * 5.03%
     * 的用户
     * 通过测试用例：
     * 49 / 49
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int num : nums){
            cache.put(num,cache.getOrDefault(num,0)+num);
        }
        Integer[] ints = cache.keySet().toArray(new Integer[cache.size()]);
        Arrays.sort(ints);
        int selected = cache.get(ints[0]),unselected = 0;
        for(int i = 1 ; i < ints.length ; i++){
            int num = ints[i];
            if (num == ints[i - 1] +1){
                int temp = selected;
                selected = Math.max(unselected + cache.get(num),selected-cache.get(ints[i-1]) + cache.get(num));
                unselected = Math.max(temp,unselected);
            }else {
                unselected = Math.max(selected,unselected);
                selected = unselected + cache.get(num);
            }
        }
        return Math.max(selected,unselected);
    }

    /**
     *执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 6.86%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 5.03%
     * 的用户
     * 通过测试用例：
     * 49 / 49
     * @param nums
     * @return
     */
    public int deleteAndEarnProve(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i = 1 ; i < nums.length ; i++){
            int num = nums[i];
            if (num == nums[i-1]){
                list.set(list.size()-1,list.get(list.size()-1)+num);
            }else if (num == nums[i-1] + 1){
                list.add(num);
            }else {
                list.add(0);
                list.add(num);
            }
        }
        if (list.size() ==1){
            return list.get(0);
        }
        int first = list.get(0), second = Math.max(list.get(0), list.get(1));
        for (int i = 2; i < list.size(); i++) {
            int temp = second;
            second = Math.max(first + list.get(i), second);
            first = temp;
        }
        return second;
    }
    public static void main(String[] args) {
        DeleteAndEarn demo = new DeleteAndEarn();
        int res = demo.deleteAndEarn(new int[]{2,3,4});
        System.out.println(res);
    }

    /**
     * 方法一：动态规划
     * 思路
     *
     * 根据题意，在选择了元素 xx 后，该元素以及所有等于 x-1x−1 或 x+1x+1 的元素会从数组中删去。若还有多个值为 xx 的元素，由于所有等于 x-1x−1 或 x+1x+1 的元素已经被删除，我们可以直接删除 xx 并获得其点数。因此若选择了 xx，所有等于 xx 的元素也应一同被选择，以尽可能多地获得点数。
     *
     * 记元素 xx 在数组中出现的次数为 c_xc
     * x
     * ​
     *  ，我们可以用一个数组 sumsum 记录数组 \textit{nums}nums 中所有相同元素之和，即 \textit{sum}[x]=x\cdot c_xsum[x]=x⋅c
     * x
     * ​
     *  。若选择了 xx，则可以获取 \textit{sum}[x]sum[x] 的点数，且无法再选择 x-1x−1 和 x+1x+1。这与「198. 打家劫舍」是一样的，在统计出 \textit{sum}sum 数组后，读者可参考「198. 打家劫舍的官方题解」中的动态规划过程计算出答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/delete-and-earn/solution/shan-chu-bing-huo-de-dian-shu-by-leetcod-x1pu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 64.82%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 27.36%
     * 的用户
     * 通过测试用例：
     * 49 / 49
     */
    public int deleteAndEarn1(int[] nums) {
        int maxVal = 0;
        for (int val : nums) {
            maxVal = Math.max(maxVal, val);
        }
        int[] sum = new int[maxVal + 1];
        for (int val : nums) {
            sum[val] += val;
        }
        return rob(sum);
    }

    public int rob(int[] nums) {
        int size = nums.length;
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < size; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    /**
     * 方法二：排序 + 动态规划
     * 注意到若 \textit{nums}nums 中不存在某个元素 xx，则选择任一小于 xx 的元素不会影响到大于 xx 的元素的选择。因此我们可以将 \textit{nums}nums 排序后，将其划分成若干连续子数组，子数组内任意相邻元素之差不超过 11。对每个子数组按照方法一的动态规划过程计算出结果，累加所有结果即为答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/delete-and-earn/solution/shan-chu-bing-huo-de-dian-shu-by-leetcod-x1pu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 11.84%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 11.50%
     * 的用户
     * 通过测试用例：
     * 49 / 49
     */
    public int deleteAndEarn2(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Arrays.sort(nums);
        List<Integer> sum = new ArrayList<Integer>();
        sum.add(nums[0]);
        int size = 1;
        for (int i = 1; i < n; ++i) {
            int val = nums[i];
            if (val == nums[i - 1]) {
                sum.set(size - 1, sum.get(size - 1) + val);
            } else if (val == nums[i - 1] + 1) {
                sum.add(val);
                ++size;
            } else {
                ans += rob(sum);
                sum.clear();
                sum.add(val);
                size = 1;
            }
        }
        ans += rob(sum);
        return ans;
    }

    public int rob(List<Integer> nums) {
        int size = nums.size();
        if (size == 1) {
            return nums.get(0);
        }
        int first = nums.get(0), second = Math.max(nums.get(0), nums.get(1));
        for (int i = 2; i < size; i++) {
            int temp = second;
            second = Math.max(first + nums.get(i), second);
            first = temp;
        }
        return second;
    }

}
