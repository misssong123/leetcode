package com.meng.origin;

import java.util.*;

/**
 * 740. 删除并获得点数
 *
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
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
 *
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
 *
 * 提示：
 *
 *     1 <= nums.length <= 2 * 104
 *     1 <= nums[i] <= 104
 */
public class DeleteAndEarn {
    /**
     * 排序后使用递归进行状态转移
     * @param nums
     * @return
     * 执行用时：11 ms, 在所有 Java 提交中击败了9.67% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了48.36% 的用户
     */
    public int deleteAndEarn(int[] nums) {
        TreeMap<Integer,Integer> cache = new TreeMap<Integer, Integer>();
        for(int num : nums){
            cache.put(num,cache.getOrDefault(num,0)+num);
        }
        /*System.out.println(cache);*/
        int first = -1 , second = -1 ;
        int sum1 = 0 , sum2 = 0;
        for(Map.Entry<Integer,Integer> entry : cache.entrySet()){
            //获取第一个状态
            if (first == -1){
                first = entry.getKey();
                sum1 = entry.getValue();
                continue;
            }
            //根据first与second是否临近确定对应的second
            if (second == -1){
                second = entry.getKey();
                sum2 = entry.getValue();
                if (first != second-1){
                    sum2 += sum1;
                    continue;
                }
                sum2 = Math.max(sum1,sum2);
                continue;
            }
            /*System.out.println(first+","+sum1);
            System.out.println(second+","+sum2);
            System.out.println("----------------------------------");*/
            /**
             * 状态转移
             */
            int temp = second;
            int tempSum = sum2;
            if (second == entry.getKey()-1){
                sum2 = sum1 + entry.getValue();
            }else {
                sum2 = Math.max(sum1 ,sum2) + entry.getValue();
            }
            second = entry.getKey();
            first = temp;
            sum1 = Math.max(tempSum,sum1);
        }
        return Math.max(sum1,sum2);
    }
    /**
     * 方法一：动态规划
     *
     * 思路
     *
     * 根据题意，在选择了元素 xxx 后，该元素以及所有等于 x−1x-1x−1 或 x+1x+1x+1 的元素会从数组中删去。若还有多个值为 xxx 的元素，由于所有等于 x−1x-1x−1 或 x+1x+1x+1 的元素已经被删除，我们可以直接删除 xxx 并获得其点数。因此若选择了 xxx，所有等于 xxx 的元素也应一同被选择，以尽可能多地获得点数。
     *
     * 记元素 xxx 在数组中出现的次数为 cxc_xcx​，我们可以用一个数组 sumsumsum 记录数组 nums\textit{nums}nums 中所有相同元素之和，即 sum[x]=x⋅cx\textit{sum}[x]=x\cdot c_xsum[x]=x⋅cx​。若选择了 xxx，则可以获取 sum[x]\textit{sum}[x]sum[x] 的点数，且无法再选择 x−1x-1x−1 和 x+1x+1x+1。这与「198. 打家劫舍」是一样的，在统计出 sum\textit{sum}sum 数组后，读者可参考「198. 打家劫舍的官方题解」中的动态规划过程计算出答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/delete-and-earn/solution/shan-chu-bing-huo-de-dian-shu-by-leetcod-x1pu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param nums
     * 执行用时：2 ms, 在所有 Java 提交中击败了77.98% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了29.47% 的用户
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
        return rob1(sum);
    }

    public int rob1(int[] nums) {
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
     *
     * 注意到若 nums\textit{nums}nums 中不存在某个元素 xxx，则选择任一小于 xxx 的元素不会影响到大于 xxx 的元素的选择。因此我们可以将 nums\textit{nums}nums 排序后，将其划分成若干连续子数组，子数组内任意相邻元素之差不超过 111。对每个子数组按照方法一的动态规划过程计算出结果，累加所有结果即为答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/delete-and-earn/solution/shan-chu-bing-huo-de-dian-shu-by-leetcod-x1pu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * @param nums
     * 执行用时：8 ms, 在所有 Java 提交中击败了13.99% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了58.04% 的用户
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
                ans += rob2(sum);
                sum.clear();
                sum.add(val);
                size = 1;
            }
        }
        ans += rob2(sum);
        return ans;
    }

    public int rob2(List<Integer> nums) {
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

    /**
     * 改良自己的做法
     * @param nums
     * @return
     * 执行用时：2 ms, 在所有 Java 提交中击败了77.98% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了25.45% 的用户
     */
    public int deleteAndEarn3(int[] nums) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int first = -1 ,sum1= 0;
        int second = -1 ,sum2 = 0;
        int len = nums.length,index = 0;
        while (index < len && (first == -1 || second == -1 || second == nums[index])){
            int val = nums[index++];
            if (first == -1 || first == val){
                first = val;
                sum1+=val;
                continue;
            }
            if (second == -1 && val != first + 1){
                sum2 += sum1;
            }
            second = val;
            sum2 += val;
        }
       /* System.out.println(first+","+sum1);
        System.out.println(second+","+sum2);
        System.out.println("----------------------------------");*/
        while (index < len){
            int val = nums[index++];
            if (second == val){
                sum2 += val;
            }else {
                int temp = sum2;
                if (val == second +1){
                    sum2 = sum1 + val;
                }else {
                    sum2 = Math.max(sum1,sum2) + val;
                }
                sum1 = Math.max(sum1,temp);
                first = second;
                second = val;
               /* System.out.println(first+","+sum1);
                System.out.println(second+","+sum2);
                System.out.println("----------------------------------");*/
            }
        }
        return Math.max(sum1,sum2);
    }


    public static void main(String[] args) {
        int[] nums = {1,6,3,3,8,4,8,10,1,3};
        DeleteAndEarn demo = new DeleteAndEarn();
        System.out.println(demo.deleteAndEarn3(nums));
    }
}
