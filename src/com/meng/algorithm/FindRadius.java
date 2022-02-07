package com.meng.algorithm;

import java.util.Arrays;

/**
 * 475. 供暖器
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 *
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 *
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 *
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 *
 *
 *
 * 示例 1:
 *
 * 输入: houses = [1,2,3], heaters = [2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * 示例 2:
 *
 * 输入: houses = [1,2,3,4], heaters = [1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 * 示例 3：
 *
 * 输入：houses = [1,5], heaters = [2]
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= houses.length, heaters.length <= 3 * 104
 * 1 <= houses[i], heaters[i] <= 109
 */
public class FindRadius {
    public int findRadius(int[] houses, int[] heaters) {
        return 0;
    }

    /**
     * 方法一：排序 + 二分查找
     * 思路和算法
     *
     * 为了使供暖器可以覆盖所有房屋且供暖器的加热半径最小，对于每个房屋，应该选择离该房屋最近的供暖器覆盖该房屋，最近的供暖器和房屋的距离即为该房屋需要的供暖器的最小加热半径。所有房屋需要的供暖器的最小加热半径中的最大值即为可以覆盖所有房屋的最小加热半径。
     *
     * 为了得到距离每个房屋最近的供暖器，可以将供暖器数组 \textit{heaters}heaters 排序，然后通过二分查找得到距离最近的供暖器。
     *
     * 具体而言，对于每个房屋 \textit{house}house，需要在有序数组 \textit{heaters}heaters 中找到最大的下标 ii，使得 \textit{heaters}[i] \le \textit{house}heaters[i]≤house，特别地，当 \textit{heaters}[0] > \textit{house}heaters[0]>house 时，i = -1i=−1。在得到下标 ii 之后，令 j = i + 1j=i+1，则 jj 是满足 \textit{heaters}[j] > \textit{house}heaters[j]>house 的最小下标。特别地，当 \textit{heaters}[n - 1] \le \textit{house}heaters[n−1]≤house 时，j = nj=n，其中 nn 是数组 \textit{heaters}heaters 的长度。
     *
     * 得到下标 ii 和 jj 之后，离房屋 \textit{house}house 最近的供暖器为 \textit{heaters}[i]heaters[i] 或 \textit{heaters}[j]heaters[j]，分别计算这两个供暖器和房屋 \textit{house}house 的距离，其中的最小值即为该房屋需要的供暖器的最小加热半径。对于 i = -1i=−1 或 j = nj=n 的下标越界情况，只要将对应的距离设为 +\infty+∞ 即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/heaters/solution/gong-nuan-qi-by-leetcode-solution-rwui/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param houses
     * @param heaters
     * @return
     * 执行用时：
     * 18 ms
     * , 在所有 Java 提交中击败了
     * 28.21%
     * 的用户
     * 内存消耗：
     * 41.6 MB
     * , 在所有 Java 提交中击败了
     * 60.74%
     * 的用户
     * 通过测试用例：
     * 30 / 30
     */
    public int findRadius1(int[] houses, int[] heaters) {
        int ans = 0;
        Arrays.sort(heaters);
        for (int house : houses) {
            int i = binarySearch(heaters, house);
            int j = i + 1;
            int leftDistance = i < 0 ? Integer.MAX_VALUE : house - heaters[i];
            int rightDistance = j >= heaters.length ? Integer.MAX_VALUE : heaters[j] - house;
            int curDistance = Math.min(leftDistance, rightDistance);
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums[left] > target) {
            return -1;
        }
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    /**
     * 方法二：排序 + 双指针
     * 思路和算法
     *
     * 也可以使用双指针寻找和每个房屋距离最近的供暖器。首先对房屋数组 \textit{houses}houses 和供暖器数组 \textit{heaters}heaters 排序，然后同时遍历两个数组。
     *
     * 分别用 mm 和 nn 表示数组 \textit{houses}houses 和 \textit{heaters}heaters 的长度。对于每个 0 \le i < m0≤i<m，需要找到下标 jj 使得 \Big|\textit{houses}[i] - \textit{heaters}[j]\Big|
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  houses[i]−heaters[j]
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *   最小，则 \textit{heaters}[j]heaters[j] 是和 \textit{houses}[i]houses[i] 距离最近的供暖器。初始时，i = j = 0i=j=0。
     *
     * 从左到右依次遍历数组 \textit{houses}houses，对于每个下标 ii，需要维护离 \textit{houses}[i]houses[i] 最近的供暖器的距离，将距离初始化为 \textit{houses}[i] - \textit{heaters}[j]houses[i]−heaters[j]。只要 \textit{heaters}[j]heaters[j] 和当前房屋的距离大于等于 \textit{heaters}[j + 1]heaters[j+1] 和当前房屋的距离，则将 jj 加 11，直到 j = n - 1j=n−1 或者 \textit{heaters}[j]heaters[j] 和当前房屋的距离小于 \textit{heaters}[j + 1]heaters[j+1] 和当前房屋的距离，此时 \textit{heaters}[j]heaters[j] 为离 \textit{houses}[i]houses[i] 最近的供暖器，\textit{heaters}[j]heaters[j] 和当前房屋的距离即为当前房屋和最近的供暖器的距离。
     *
     * 遍历完所有房屋之后，即可得到可以覆盖所有房屋的最小加热半径。
     *
     * 证明
     *
     * 上述做法中，假设在 \textit{houses}[i]houses[i] 处的 jj 初始值是 j_0j
     * 0
     * ​
     *  ，则可以保证得到的 \textit{heaters}[j]heaters[j] 是在 j \ge j_0j≥j
     * 0
     * ​
     *   的情况下和 \textit{houses}[i]houses[i] 距离最近的供暖器。为了确保和 \textit{houses}[i]houses[i] 距离最近的供暖器是 \textit{heaters}[j]heaters[j]，还需要证明对任意 j' < j_0j
     * ′
     *  <j
     * 0
     * ​
     *   都有 \Big|\textit{houses}[i] - \textit{heaters}[j']\Big| \ge \Big|\textit{houses}[i] - \textit{heaters}[j]\Big|
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  houses[i]−heaters[j
     * ′
     *  ]
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  ≥
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  houses[i]−heaters[j]
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  。可以通过数学归纳法证明。
     *
     * 当 i = 0i=0 时，j_0 = 0j
     * 0
     * ​
     *  =0，因此不存在 j' < j_0j
     * ′
     *  <j
     * 0
     * ​
     *   使得 \Big|\textit{houses}[i] - \textit{heaters}[j']\Big| < \Big|\textit{houses}[i] - \textit{heaters}[j]\Big|
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  houses[i]−heaters[j
     * ′
     *  ]
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  <
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  houses[i]−heaters[j]
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  。
     *
     * 当 i > 0i>0 时，假设和 \textit{houses}[i - 1]houses[i−1] 距离最近的供暖器是 \textit{heaters}[j_0]heaters[j
     * 0
     * ​
     *  ] 且不存在 j' < j_0j
     * ′
     *  <j
     * 0
     * ​
     *   使得 \textit{heaters}[j']heaters[j
     * ′
     *  ] 和 \textit{houses}[i - 1]houses[i−1] 的距离更小，则对于 \textit{houses}[i]houses[i]，jj 从 j_0j
     * 0
     * ​
     *   开始向右遍历。
     *
     * 如果 \textit{houses}[i] \ge \textit{heaters}[j]houses[i]≥heaters[j]，则 \Big|\textit{houses}[i] - \textit{heaters}[j]\Big| = \textit{houses}[i] - \textit{heaters}[j]
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  houses[i]−heaters[j]
     * ∣
     * ∣
     * ∣
     * ∣
     * ​
     *  =houses[i]−heaters[j]。由于 j' < j_0 \le jj
     * ′
     *  <j
     * 0
     * ​
     *  ≤j，因此 \textit{heaters}[j'] \le \textit{heaters}[j]heaters[j
     * ′
     *  ]≤heaters[j]，\textit{houses}[i] - \textit{heaters}[j'] \ge \textit{houses}[i] - \textit{heaters}[j]houses[i]−heaters[j
     * ′
     *  ]≥houses[i]−heaters[j]，即 \textit{heaters}[j']heaters[j
     * ′
     *  ] 和 \textit{houses}[i]houses[i] 的距离不可能小于 \textit{heaters}[j]heaters[j] 和 \textit{houses}[i]houses[i] 的距离。
     *
     *
     * 如果 \textit{houses}[i] < \textit{heaters}[j]houses[i]<heaters[j]，则一定有 j = j_0j=j
     * 0
     * ​
     *   或 \textit{houses}[i] > \textit{heaters}[j_0]houses[i]>heaters[j
     * 0
     * ​
     *  ]，否则 \textit{heaters}[j_0]heaters[j
     * 0
     * ​
     *  ] 和 \textit{houses}[i]houses[i] 的距离更近，矛盾。
     *
     * 如果 j = j_0j=j
     * 0
     * ​
     *  ，则根据 \textit{heaters}[j_0]heaters[j
     * 0
     * ​
     *  ] 是和 \textit{houses}[i - 1]houses[i−1] 距离最近的供暖器可知，对于任意 j' < j_0j
     * ′
     *  <j
     * 0
     * ​
     *  ，一定有 \textit{houses}[i] \ge \textit{houses}[i - 1] > \textit{heaters}[j']houses[i]≥houses[i−1]>heaters[j
     * ′
     *  ]，\textit{houses}[i - 1] - \textit{heaters}[j'] \ge \textit{heaters}[j_0] - \textit{houses}[i - 1]houses[i−1]−heaters[j
     * ′
     *  ]≥heaters[j
     * 0
     * ​
     *  ]−houses[i−1]，由于 \textit{houses}[i - 1] \le \textit{houses}[i]houses[i−1]≤houses[i] 因此有 \textit{houses}[i] - \textit{heaters}[j'] \ge \textit{heaters}[j_0] - \textit{houses}[i]houses[i]−heaters[j
     * ′
     *  ]≥heaters[j
     * 0
     * ​
     *  ]−houses[i]。
     *
     * 如果 \textit{houses}[i] > \textit{heaters}[j_0]houses[i]>heaters[j
     * 0
     * ​
     *  ]，则对于任意 j' < j_0j
     * ′
     *  <j
     * 0
     * ​
     *  ，\textit{heaters}[j']heaters[j
     * ′
     *  ] 和 \textit{houses}[i]houses[i] 的距离不可能小于 \textit{heaters}[j_0]heaters[j
     * 0
     * ​
     *  ] 和 \textit{houses}[i]houses[i] 的距离，因此 \textit{heaters}[j']heaters[j
     * ′
     *  ] 和 \textit{houses}[i]houses[i] 的距离不可能小于 \textit{heaters}[j]heaters[j] 和 \textit{houses}[i]houses[i] 的距离。
     *
     *
     *
     * 因此对于 \textit{houses}[i]houses[i]，不存在 j' < j_0j
     * ′
     *  <j
     * 0
     * ​
     *   使得 \textit{heaters}[j']heaters[j
     * ′
     *  ] 和 \textit{houses}[i]houses[i] 的距离更小。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/heaters/solution/gong-nuan-qi-by-leetcode-solution-rwui/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0, j = 0; i < houses.length; i++) {
            int curDistance = Math.abs(houses[i] - heaters[j]);
            while (j < heaters.length - 1 && Math.abs(houses[i] - heaters[j]) >= Math.abs(houses[i] - heaters[j + 1])) {
                j++;
                curDistance = Math.min(curDistance, Math.abs(houses[i] - heaters[j]));
            }
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }
}
