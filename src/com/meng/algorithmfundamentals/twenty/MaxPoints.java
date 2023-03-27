package com.meng.algorithmfundamentals.twenty;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. 直线上最多的点数
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * 示例 2：
 *
 *
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 *
 *
 * 提示：
 *
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * points 中的所有点 互不相同
 */
public class MaxPoints {
    public int maxPoints(int[][] points) {
        return -1;
    }

    /**
     * 方法一：哈希表
     * 思路及解法
     *
     * 我们可以考虑枚举所有的点，假设直线经过该点时，该直线所能经过的最多的点数。
     *
     * 假设我们当前枚举到点 ii，如果直线同时经过另外两个不同的点 jj 和 kk，那么可以发现点 ii 和点 jj 所连直线的斜率恰等于点 ii 和点 kk 所连直线的斜率。
     *
     * 于是我们可以统计其他所有点与点 ii 所连直线的斜率，出现次数最多的斜率即为经过点数最多的直线的斜率，其经过的点数为该斜率出现的次数加一（点 ii 自身也要被统计）。
     *
     * 如何记录斜率
     *
     * 需要注意的是，浮点数类型可能因为精度不够而无法足够精确地表示每一个斜率，因此我们需要换一种方法来记录斜率。
     *
     * 一般情况下，斜率可以表示为 \textit{slope} = \dfrac{\Delta y}{\Delta x}slope=
     * Δx
     * Δy
     * ​
     *   的形式，因此我们可以用分子和分母组成的二元组来代表斜率。但注意到存在形如 \dfrac{1}{2}=\dfrac{2}{4}
     * 2
     * 1
     * ​
     *  =
     * 4
     * 2
     * ​
     *   这样两个二元组不同，但实际上两分数的值相同的情况，所以我们需要将分数 \dfrac{\Delta y}{\Delta x}
     * Δx
     * Δy
     * ​
     *   化简为最简分数的形式。
     *
     * 将分子和分母同时除以二者绝对值的最大公约数，可得二元组 \Big(\dfrac{\Delta x}{\gcd(|\Delta x|,|\Delta y|)},\dfrac{\Delta y}{\gcd(|\Delta x|,|\Delta y|)}\Big)(
     * gcd(∣Δx∣,∣Δy∣)
     * Δx
     * ​
     *  ,
     * gcd(∣Δx∣,∣Δy∣)
     * Δy
     * ​
     *  )。令 \textit{mx}=\dfrac{\Delta x}{\gcd(|\Delta x|,|\Delta y|)}mx=
     * gcd(∣Δx∣,∣Δy∣)
     * Δx
     * ​
     *  ，\textit{my}=\dfrac{\Delta y}{\gcd(|\Delta x|,|\Delta y|)}my=
     * gcd(∣Δx∣,∣Δy∣)
     * Δy
     * ​
     *  ，则上述化简后的二元组为 (\textit{mx},\textit{my})(mx,my)。
     *
     * 此外，因为分子分母可能存在负数，为了防止出现形如 \dfrac{-1}{2}=\dfrac{1}{-2}
     * 2
     * −1
     * ​
     *  =
     * −2
     * 1
     * ​
     *   的情况，我们还需要规定分子为非负整数，如果 \textit{my}my 为负数，我们将二元组中两个数同时取相反数即可。
     *
     * 特别地，考虑到 \textit{mx}mx 和 \textit{my}my 两数其中有一个为 00 的情况（因为题目中不存在重复的点，因此不存在两数均为 00 的情况），此时两数不存在数学意义上的最大公约数，因此我们直接特判这两种情况。当 \textit{mx}mx 为 00 时，我们令 \textit{my}=1my=1；当 \textit{my}my 为 00 时，我们令 \textit{mx}=1mx=1 即可。
     *
     * 经过上述操作之后，即可得到最终的二元组 (\textit{mx},\textit{my})(mx,my)。在本题中，因为点的横纵坐标取值范围均为 [-10^4, 10^4][−10
     * 4
     *  ,10
     * 4
     *  ]，所以斜率 \textit{slope} = \dfrac{\textit{my}}{\textit{mx}}slope=
     * mx
     * my
     * ​
     *   中，\textit{mx}mx 落在区间 [- 2 \times 10^4, 2 \times 10^4][−2×10
     * 4
     *  ,2×10
     * 4
     *  ] 内，\textit{my}my 落在区间 [0, 2 \times 10^4][0,2×10
     * 4
     *  ] 内。注意到 3232 位整数的范围远超这两个区间，因此我们可以用单个 3232 位整型变量来表示这两个整数。具体地，我们令 \textit{val} = \textit{my} + (2 \times 10^4 + 1) \times \textit{mx}val=my+(2×10
     * 4
     *  +1)×mx 即可。
     *
     * 优化
     *
     * 最后我们再加四个小优化：
     *
     * 在点的总数量小于等于 22 的情况下，我们总可以用一条直线将所有点串联，此时我们直接返回点的总数量即可；
     * 当我们枚举到点 ii 时，我们只需要考虑编号大于 ii 的点到点 ii 的斜率，因为如果直线同时经过编号小于点 ii 的点 jj，那么当我们枚举到 jj 时就已经考虑过该直线了；
     * 当我们找到一条直线经过了图中超过半数的点时，我们即可以确定该直线即为经过最多点的直线；
     * 当我们枚举到点 ii（假设编号从 00 开始）时，我们至多只能找到 n-in−i 个点共线。假设此前找到的共线的点的数量的最大值为 kk，如果有 k \geq n-ik≥n−i，那么此时我们即可停止枚举，因为不可能再找到更大的答案了。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/max-points-on-a-line/solution/zhi-xian-shang-zui-duo-de-dian-shu-by-le-tq8f/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param points
     * @return
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 68.47%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 25.78%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     */
    public int maxPoints1(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1);
            }
            ret = Math.max(ret, maxn);
        }
        return ret;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }


}
