package com.meng.algorithm;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 *
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 *
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 *
 * 注意:
 *
 *     数组长度不超过1000。
 *     数组里整数的范围为 [0, 1000]。
 */
public class TriangleNumber {
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        if(len < 3){
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        for(int i = 0 ; i < len ; i++){
            if(nums[i] == 0){
                continue;
            }
            for(int j = i+1 ; j < len ; j ++){
                for(int k = j + 1 ; k < len ; k++){
                    if(nums[k] >= nums[i] + nums[j]){
                        break;
                    }
                    res ++;
                }
            }
        }
        return res;
    }

    /**
     * 方法一：排序 + 二分查找
     *
     * 思路与算法
     *
     * 对于正整数 a,b,ca, b, ca,b,c，它们可以作为三角形的三条边，当且仅当：
     *
     * {a+b>ca+c>bb+c>a\begin{cases} a + b > c \\ a + c > b \\ b + c > a \end{cases} ⎩⎪⎪⎨⎪⎪⎧​a+b>ca+c>bb+c>a​
     *
     * 均成立。如果我们将三条边进行升序排序，使它们满足 a≤b≤ca \leq b \leq ca≤b≤c，那么 a+c>ba + c > ba+c>b 和 b+c>ab + c > ab+c>a 使一定成立的，我们只需要保证 a+b>ca + b > ca+b>c。
     *
     * 因此，我们可以将数组 nums\textit{nums}nums 进行升序排序，随后使用二重循环枚举 aaa 和 bbb。设 a=nums[i],b=nums[j]a = \textit{nums}[i], b = \textit{nums}[j]a=nums[i],b=nums[j]，为了防止重复统计答案，我们需要保证 i<ji < ji<j。剩余的边 ccc 需要满足 c<nums[i]+nums[j]c < \textit{nums}[i] + \textit{nums}[j]c<nums[i]+nums[j]，我们可以在 [j+1,n−1][j + 1, n - 1][j+1,n−1] 的下标范围内使用二分查找（其中 nnn 是数组 nums\textit{nums}nums 的长度），找出最大的满足 nums[k]<nums[i]+nums[j]\textit{nums}[k] < \textit{nums}[i] + \textit{nums}[j]nums[k]<nums[i]+nums[j] 的下标 kkk，这样一来，在 [j+1,k][j + 1, k][j+1,k] 范围内的下标都可以作为边 ccc 的下标，我们将该范围的长度 k−jk - jk−j 累加入答案。
     *
     * 当枚举完成后，我们返回累加的答案即可。
     *
     * 细节
     *
     * 注意到题目描述中 nums\textit{nums}nums 包含的元素为非负整数，即除了正整数以外，nums\textit{nums}nums 还会包含 000。但如果我们将 nums\textit{nums}nums 进行升序排序，那么在枚举 aaa 和 bbb 时出现了 000，那么 nums[i]\textit{nums}[i]nums[i] 一定为 000。此时，边 ccc 需要满足 c<nums[i]+nums[j]=nums[j]c < \textit{nums}[i] + \textit{nums}[j] = \textit{nums}[j]c<nums[i]+nums[j]=nums[j]，而下标在 [j+1,n−1][j + 1, n - 1][j+1,n−1] 范围内的元素一定都是大于等于 nums[j]\textit{nums}[j]nums[j] 的，因此二分查找会失败。若二分查找失败，我们可以令 k=jk = jk=j，此时对应的范围长度 k−j=0k - j = 0k−j=0，我们也就保证了答案的正确性。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-triangle-number/solution/you-xiao-san-jiao-xing-de-ge-shu-by-leet-t2td/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：238 ms, 在所有 Java 提交中击败了32.78% 的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了81.27% 的用户
     */
    public int triangleNumber1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int left = j + 1, right = n - 1, k = j;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                ans += k - j;
            }
        }
        return ans;
    }
    /**
     * 方法二：排序 + 双指针
     *
     * 思路与算法
     *
     * 我们可以对方法一进行优化。
     *
     * 我们将当 a=nums[i],b=nums[j]a = \textit{nums}[i], b = \textit{nums}[j]a=nums[i],b=nums[j] 时，最大的满足 nums[k]<nums[i]+nums[j]\textit{nums}[k] < \textit{nums}[i] + \textit{nums}[j]nums[k]<nums[i]+nums[j] 的下标 kkk 记为 ki,jk_{i, j}ki,j​。可以发现，如果我们固定 iii，那么随着 jjj 的递增，不等式右侧 nums[i]+nums[j]\textit{nums}[i] + \textit{nums}[j]nums[i]+nums[j] 也是递增的，因此 ki,jk_{i, j}ki,j​ 也是递增的。
     *
     * 这样一来，我们就可以将 jjj 和 kkk 看成两个同向（递增）移动的指针，将方法一进行如下的优化：
     *
     *     我们使用一重循环枚举 iii。当 iii 固定时，我们使用双指针同时维护 jjj 和 kkk，它们的初始值均为 iii；
     *
     *     我们每一次将 jjj 向右移动一个位置，即 j←j+1j \leftarrow j+1j←j+1，并尝试不断向右移动 kkk，使得 kkk 是最大的满足 nums[k]<nums[i]+nums[j]\textit{nums}[k] < \textit{nums}[i] + \textit{nums}[j]nums[k]<nums[i]+nums[j] 的下标。我们将 max⁡(k−j,0)\max(k - j, 0)max(k−j,0) 累加入答案。
     *
     * 当枚举完成后，我们返回累加的答案即可。
     *
     * 细节
     *
     * 与方法一中「二分查找的失败」类似，方法二的双指针中，也会出现不存在满足 nums[k]<nums[i]+nums[j]\textit{nums}[k] < \textit{nums}[i] + \textit{nums}[j]nums[k]<nums[i]+nums[j] 的下标的情况。此时，指针 kkk 不会出现在指针 jjj 的右侧，即 k−j≤0k - j \leq 0k−j≤0，因此我们需要将 k−jk - jk−j 与 000 中的较大值累加入答案，防止错误的负数出现。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-triangle-number/solution/you-xiao-san-jiao-xing-de-ge-shu-by-leet-t2td/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *执行用时：37 ms, 在所有 Java 提交中击败了79.32% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了29.37% 的用户
     */
    public int triangleNumber2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int k = i;
            for (int j = i + 1; j < n; ++j) {
                while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                    ++k;
                }
                ans += Math.max(k - j, 0);
            }
        }
        return ans;
    }

}
