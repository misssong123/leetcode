package com.meng.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1610. 可见点的最大数目
 * 给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。
 *
 * 最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。设 d 为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
 *
 * 对于每个点，如果由该点、你的位置以及从你的位置直接向东的方向形成的角度 位于你的视野中 ，那么你就可以看到它。
 *
 * 同一个坐标上可以有多个点。你所在的位置也可能存在一些点，但不管你的怎么旋转，总是可以看到这些点。同时，点不会阻碍你看到其他点。
 *
 * 返回你能看到的点的最大数目。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 * 输出：3
 * 解释：阴影区域代表你的视野。在你的视野中，所有的点都清晰可见，尽管 [2,2] 和 [3,3]在同一条直线上，你仍然可以看到 [3,3] 。
 * 示例 2：
 *
 * 输入：points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 * 输出：4
 * 解释：在你的视野中，所有的点都清晰可见，包括你所在位置的那个点。
 * 示例 3：
 *
 *
 *
 * 输入：points = [[1,0],[2,1]], angle = 13, location = [1,1]
 * 输出：1
 * 解释：如图所示，你只能看到两点之一。
 *
 *
 * 提示：
 *
 * 1 <= points.length <= 105
 * points[i].length == 2
 * location.length == 2
 * 0 <= angle < 360
 * 0 <= posx, posy, xi, yi <= 100
 */
public class VisiblePoints {
    /**
     * 方法一：二分查找
     * 思路
     *
     * 题目本身为几何问题，需要求出在视角范围内 [d - \textit{angle} / 2, d + \textit{angle} / 2][d−angle/2,d+angle/2] 内最多的点的覆盖数。在本题中视角可转换为相对于 \textit{location}location 的「极角」。首先将所有点 pp 的坐标转化为相对于 \textit{location}location 的极角，设点 pp 相对于 \textit{location}location 的极角为 d_{p}d
     * p
     * ​
     *  ，找到坐标的极角处于区间 [d_{p},d_{p} + \textit{angle}][d
     * p
     * ​
     *  ,d
     * p
     * ​
     *  +angle] 的最大数量即可。
     *
     * 极角转换时，已知两点的坐标可以通过反三角函数来进行转换，一般可以通过反余弦 \texttt{acos}acos，反正弦 \texttt{asin}asin，反正切 \texttt{atan}atan 等函数来确定，但以上函数的返回值范围最多只能覆盖 \piπ，可以利用函数 \texttt{atan2}atan2，不同的语言实现可以参考不同语言的标准库函数。以 \texttt{C++}C++ 为例，「\texttt{atan2}atan2」的返回值范围为 [-\pi,\pi][−π,π]，它的覆盖范围为 2\pi2π。
     *
     * 我们将所有坐标的相对于 \textit{location}location 极角全部求出，并按照极角的大小进行进心排序，我们遍历每个坐标 p_i = (x_i,y_i)p
     * i
     * ​
     *  =(x
     * i
     * ​
     *  ,y
     * i
     * ​
     *  )，我们设 p_ip
     * i
     * ​
     *   的相对于 \textit{location}location 的极角为 d_{p_i}d
     * p
     * i
     * ​
     *
     * ​
     *  ，此时需要求出所有满足坐标的极角大小处在 [d_{p_i},d_{p_i} + \textit{angle}][d
     * p
     * i
     * ​
     *
     * ​
     *  ,d
     * p
     * i
     * ​
     *
     * ​
     *  +angle] 范围内的最大数目，可以利用二分查找快速的统计出处在 [d_{p_i},d_{p_i} + \textit{angle}][d
     * p
     * i
     * ​
     *
     * ​
     *  ,d
     * p
     * i
     * ​
     *
     * ​
     *  +angle] 的元素数目。特别注意的是，由于存在 d_{p_i} + \textit{angle} > 180\degreed
     * p
     * i
     * ​
     *
     * ​
     *  +angle>180° 的情况，可以在原数组中将每个元素 d_{p_i} + 360\degreed
     * p
     * i
     * ​
     *
     * ​
     *  +360° 添加到原数组的后面，这样即可防止反转的问题。
     *
     * 在求极角时，对于坐标刚好处于 \textit{location}location 的元素需要单独进行统计，因为当 \texttt{atan2}atan2 的两个参数都为 00 时，\texttt{atan2}atan2 的返回值可能是未定义的，因此我们要尽量避免这种情况发生，所以需要将位于 \textit{location}location 的坐标进行单独统计。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-number-of-visible-points/solution/you-xiao-ke-jian-dian-de-zui-da-shu-mu-b-r1qz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param points
     * @param angle
     * @param location
     * @return
     * 执行用时：
     * 201 ms
     * , 在所有 Java 提交中击败了
     * 62.07%
     * 的用户
     * 内存消耗：
     * 93.7 MB
     * , 在所有 Java 提交中击败了
     * 51.73%
     * 的用户
     * 通过测试用例：
     * 119 / 119
     */
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int sameCnt = 0;
        List<Double> polarDegrees = new ArrayList<>();
        int locationX = location.get(0);
        int locationY = location.get(1);
        for (int i = 0; i < points.size(); ++i) {
            int x = points.get(i).get(0);
            int y = points.get(i).get(1);
            if (x == locationX && y == locationY) {
                sameCnt++;
                continue;
            }
            Double degree = Math.atan2(y - locationY, x - locationX);
            polarDegrees.add(degree);
        }
        Collections.sort(polarDegrees);

        int m = polarDegrees.size();
        for (int i = 0; i < m; ++i) {
            polarDegrees.add(polarDegrees.get(i) + 2 * Math.PI);
        }

        int maxCnt = 0;
        Double toDegree = angle * Math.PI / 180;
        for (int i = 0; i < m; ++i) {
            int iteration = binarySearch(polarDegrees, polarDegrees.get(i) + toDegree, false);
            maxCnt = Math.max(maxCnt, iteration - i);
        }
        return maxCnt + sameCnt;
    }

    public int binarySearch(List<Double> nums, Double target, boolean lower) {
        int left = 0, right = nums.size() - 1;
        int ans = nums.size();
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums.get(mid) > target || (lower && nums.get(mid) >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 方法二：滑动窗口
     * 思路
     *
     * 整体解题思路跟方法一类似，在进行区间查找时，可以利用滑动窗口对每个坐标的极角区间 [d_{p_i},d_{p_i} + \textit{angle}][d
     * p
     * i
     * ​
     *
     * ​
     *  ,d
     * p
     * i
     * ​
     *
     * ​
     *  +angle] 查找的时间复杂度由 O(2n \log 2n)O(2nlog2n) 优化为 O(2n + 2n)O(2n+2n)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-number-of-visible-points/solution/you-xiao-ke-jian-dian-de-zui-da-shu-mu-b-r1qz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param points
     * @param angle
     * @param location
     * @return
     * 执行用时：
     * 133 ms
     * , 在所有 Java 提交中击败了
     * 93.10%
     * 的用户
     * 内存消耗：
     * 93.5 MB
     * , 在所有 Java 提交中击败了
     * 51.73%
     * 的用户
     * 通过测试用例：
     * 119 / 119
     */
    public int visiblePoints1(List<List<Integer>> points, int angle, List<Integer> location) {
        int sameCnt = 0;
        List<Double> polarDegrees = new ArrayList<>();
        int locationX = location.get(0);
        int locationY = location.get(1);
        for (int i = 0; i < points.size(); ++i) {
            int x = points.get(i).get(0);
            int y = points.get(i).get(1);
            if (x == locationX && y == locationY) {
                sameCnt++;
                continue;
            }
            Double degree = Math.atan2(y - locationY, x - locationX);
            polarDegrees.add(degree);
        }
        Collections.sort(polarDegrees);

        int m = polarDegrees.size();
        for (int i = 0; i < m; ++i) {
            polarDegrees.add(polarDegrees.get(i) + 2 * Math.PI);
        }

        int maxCnt = 0;
        int right = 0;
        double toDegree = angle * Math.PI / 180;
        for (int i = 0; i < m; ++i) {
            Double curr = polarDegrees.get(i) + toDegree;
            while (right < polarDegrees.size() && polarDegrees.get(right) <= curr) {
                right++;
            }
            maxCnt = Math.max(maxCnt, right - i);
        }
        return maxCnt + sameCnt;
    }

}
