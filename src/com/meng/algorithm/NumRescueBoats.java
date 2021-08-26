package com.meng.algorithm;

import java.util.Arrays;

/**
 * 881. 救生艇
 *
 * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
 *
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 *
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 *
 *
 *
 * 示例 1：
 *
 * 输入：people = [1,2], limit = 3
 * 输出：1
 * 解释：1 艘船载 (1, 2)
 *
 * 示例 2：
 *
 * 输入：people = [3,2,2,1], limit = 3
 * 输出：3
 * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
 *
 * 示例 3：
 *
 * 输入：people = [3,5,3,4], limit = 5
 * 输出：4
 * 解释：4 艘船分别载 (3), (3), (4), (5)
 *
 * 提示：
 *
 *     1 <= people.length <= 50000
 *     1 <= people[i] <= limit <= 30000
 */
public class NumRescueBoats {
    /**
     * 执行用时：16 ms, 在所有 Java 提交中击败了95.52% 的用户
     * 内存消耗：47.3 MB, 在所有 Java 提交中击败了53.52% 的用户
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0 , right = people.length - 1,ans = 0;
        while (left <= right){
            //尽可能的保证体重较重的和体重较轻的放在一起
            if (people[left] + people[right] <= limit) {
                left++;
                right--;
                ans++;
            }else {
                right--;
                ans++;
            }
        }
        return ans;
    }

    /**
     * 方法一：贪心
     *
     * 要使需要的船数尽可能地少，应当使载两人的船尽可能地多。
     *
     * 设 people\textit{people}people 的长度为 nnn。考虑体重最轻的人：
     *
     *     若他不能与体重最重的人同乘一艘船，那么体重最重的人无法与任何人同乘一艘船，此时应单独分配一艘船给体重最重的人。从 people\textit{people}people 中去掉体重最重的人后，我们缩小了问题的规模，变成求解剩余 n−1n-1n−1 个人所需的最小船数，将其加一即为原问题的答案。
     *     若他能与体重最重的人同乘一艘船，那么他能与其余任何人同乘一艘船，为了尽可能地利用船的承载重量，选择与体重最重的人同乘一艘船是最优的。从 people\textit{people}people 中去掉体重最轻和体重最重的人后，我们缩小了问题的规模，变成求解剩余 n−2n-2n−2 个人所需的最小船数，将其加一即为原问题的答案。
     *
     * 在代码实现时，我们可以先对 people\textit{people}people 排序，然后用两个指针分别指向体重最轻和体重最重的人，按照上述规则来移动指针，并统计答案。
     *
     * class Solution {
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/boats-to-save-people/solution/jiu-sheng-ting-by-leetcode-solution-0nsp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param people
     * @param limit
     * @return
     * 执行用时：17 ms, 在所有 Java 提交中击败了64.29% 的用户
     * 内存消耗：47.5 MB, 在所有 Java 提交中击败了11.20% 的用户
     */
    public int numRescueBoats1(int[] people, int limit) {
        int ans = 0;
        Arrays.sort(people);
        int light = 0, heavy = people.length - 1;
        while (light <= heavy) {
            if (people[light] + people[heavy] <= limit) {
                ++light;
            }
            --heavy;
            ++ans;
        }
        return ans;
    }

}
