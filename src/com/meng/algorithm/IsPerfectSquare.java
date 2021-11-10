package com.meng.algorithm;

/**
 * 367. 有效的完全平方数
 * 难度
 * 简单
 *
 * 282
 *
 * 收藏
 *
 * 分享
 * 切换为英文
 * 接收动态
 * 反馈
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 *
 * 输入：num = 14
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= num <= 2^31 - 1
 */
public class IsPerfectSquare {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 35.3 MB
     * , 在所有 Java 提交中击败了
     * 20.15%
     * 的用户
     * 通过测试用例：
     * 70 / 70
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 1){
            return true;
        }
        int left = 1 ,right = num > 46340 ? 46340 : num;
        while (left <= right){
            int mid = (left + right) /2;
            int sum  = mid * mid;
            if (sum > num){
                right = mid-1;
            }else if (sum == num){
                return true;
            }else {
                left = mid+1;
            }
        }
      return false;
    }

    /**
     *方法四：牛顿迭代法
     *
     * 前置知识
     *
     * 牛顿迭代法。牛顿迭代法是一种近似求解方程（近似求解函数零点）的方法。其本质是借助泰勒级数，从初始值开始快速向函数零点逼近。
     *
     *
     *
     * 对于函数
     * f
     * (
     * x
     * )
     * f(x)，我们任取
     * x
     * 0
     * x
     * 0
     * ​
     *   作为初始值。在每一次迭代中，我们根据当前值
     * x
     * i
     * x
     * i
     * ​
     *   找到函数图像上的点
     * (
     * x
     * i
     * ,
     * f
     * (
     * x
     * i
     * )
     * )
     * (x
     * i
     * ​
     *  ,f(x
     * i
     * ​
     *  ))，过该点做一条斜率为该点导数
     * f
     * ′
     * (
     * x
     * 0
     * )
     * f
     * ′
     *  (x
     * 0
     * ​
     *  ) 的直线，该直线与横轴（
     * X
     * X 轴）的交点记作
     * (
     * x
     * i
     * +
     * 1
     * ,
     * 0
     * )
     * (x
     * i+1
     * ​
     *  ,0)。
     * x
     * i
     * +
     * 1
     * x
     * i+1
     * ​
     *   相较于
     * x
     * i
     * x
     * i
     * ​
     *   而言，距离函数零点更近。在经过多次迭代后，我们就可以得到距离函数零点非常近的交点。
     *
     * 思路
     *
     * 如果
     * num
     * num 为完全平方数，那么一定存在正整数
     * x
     * x 满足
     * x
     * ×
     * x
     * =
     * num
     * x×x=num。于是我们写出如下方程：
     *
     * y
     * =
     * f
     * (
     * x
     * )
     * =
     * x
     * 2
     * −
     * num
     * y=f(x)=x
     * 2
     *  −num
     *
     * 如果方程能够取得整数解，则说明存在满足
     * x
     * ×
     * x
     * =
     * num
     * x×x=num 的正整数
     * x
     * x。这个方程可以通过牛顿迭代法求解。
     *
     * 算法
     *
     * 在算法实现中，我们需要解决以下四个问题：
     *
     * 如何选取初始值？
     * 因为
     * num
     * num 是正整数，所以
     * y
     * =
     * x
     * 2
     * −
     * num
     * y=x
     * 2
     *  −num 有两个零点
     * −
     * num
     * −
     * num
     * ​
     *   和
     * num
     * num
     * ​
     *  ，其中
     * 1
     * ≤
     * num
     * ≤
     * num
     * 1≤
     * num
     * ​
     *  ≤num。我们只需要判断
     * num
     * num
     * ​
     *   是否为正整数即可。又因为
     * y
     * =
     * x
     * 2
     * −
     * num
     * y=x
     * 2
     *  −num 是凸函数，所以只要我们选取的初始值大于等于
     * num
     * num
     * ​
     *  ，那么每次迭代得到的结果也都会大于等于
     * num
     * num
     * ​
     *  。
     *
     * 因此，我们可以选择
     * num
     * num 作为初始值。
     *
     * 如何进行迭代？
     * 对
     * f
     * (
     * x
     * )
     * f(x) 求导，得到
     *
     * f
     * ′
     * (
     * x
     * )
     * =
     * 2
     * x
     * f
     * ′
     *  (x)=2x
     *
     * 假设当前值为
     * x
     * i
     * x
     * i
     * ​
     *  ，将
     * x
     * i
     * x
     * i
     * ​
     *   代入
     * f
     * (
     * x
     * )
     * f(x) 得到函数图像上的点
     * (
     * x
     * i
     * ,
     * x
     * i
     * 2
     * −
     * num
     * )
     * (x
     * i
     * ​
     *  ,x
     * i
     * 2
     * ​
     *  −num)，过该点做一条斜率为
     * f
     * ′
     * (
     * x
     * i
     * )
     * =
     * 2
     * x
     * i
     * f
     * ′
     *  (x
     * i
     * ​
     *  )=2x
     * i
     * ​
     *   的直线，则直线的方程为
     *
     * y
     * −
     * (
     * x
     * i
     * 2
     * −
     * num
     * )
     * =
     * 2
     * x
     * i
     * (
     * x
     * −
     * x
     * i
     * )
     * y−(x
     * i
     * 2
     * ​
     *  −num)=2x
     * i
     * ​
     *  (x−x
     * i
     * ​
     *  )
     *
     * 直线与横轴（
     * X
     * X 轴）交点的横坐标为上式中的
     * y
     * =
     * 0
     * y=0 时
     * x
     * x 的解。于是令上式中
     * y
     * =
     * 0
     * y=0，得到
     *
     * 2
     * x
     * i
     * x
     * −
     * x
     * i
     * 2
     * −
     * num
     * =
     * 0
     * 2x
     * i
     * ​
     *  x−x
     * i
     * 2
     * ​
     *  −num=0
     *
     * 整理上式即可得到下一次迭代的值：
     *
     * x
     * i
     * +
     * 1
     * =
     * x
     * i
     * 2
     * +
     * num
     * 2
     * x
     * i
     * =
     * 1
     * 2
     * (
     * x
     * i
     * +
     * num
     * x
     * i
     * )
     * (1)
     * x
     * i+1
     * ​
     *  =
     * 2x
     * i
     * ​
     *
     * x
     * i
     * 2
     * ​
     *  +num
     * ​
     *  =
     * 2
     * 1
     * ​
     *  (x
     * i
     * ​
     *  +
     * x
     * i
     * ​
     *
     * num
     * ​
     *  )(1)
     *
     * 如何判断迭代是否可以结束？
     * 每一次迭代后，我们都会距离零点更近一步，所以当相邻两次迭代的结果非常接近时，我们就可以断定，此时的结果已经足够我们得到答案了。一般来说，可以判断相邻两次迭代的结果的差值是否小于一个极小的非负数
     * ϵ
     * ϵ，其中
     * ϵ
     * ϵ 一般可以取
     * 1
     * 0
     * −
     * 6
     * 10
     * −6
     *   或
     * 1
     * 0
     * −
     * 7
     * 10
     * −7
     *  。
     *
     * 如何通过迭代得到的近似零点得到最终的答案？
     * 因为初始值的选择以及
     * y
     * =
     * x
     * 2
     * −
     * num
     * y=x
     * 2
     *  −num 凸函数的性质，我们通过迭代得到的
     * x
     * i
     * x
     * i
     * ​
     *   一定是
     * num
     * num
     * ​
     *   的近似零点，且满足
     * x
     * i
     * ≥
     * num
     * x
     * i
     * ​
     *  ≥
     * num
     * ​
     *  。
     *
     * 当
     * num
     * num 是完全平方数时，
     * num
     * num
     * ​
     *   为正整数，则有
     * ⌊
     * x
     * i
     * ⌋
     * 2
     * =
     * (
     * num
     * )
     * 2
     * =
     * num
     * ⌊x
     * i
     * ​
     *  ⌋
     * 2
     *  =(
     * num
     * ​
     *  )
     * 2
     *  =num，其中符号
     * ⌊
     * x
     * ⌋
     * ⌊x⌋ 表示
     * x
     * x 的向下取整。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-perfect-square/solution/you-xiao-de-wan-quan-ping-fang-shu-by-le-wkee/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 35.4 MB
     * , 在所有 Java 提交中击败了
     * 6.60%
     * 的用户
     * 通过测试用例：
     * 70 / 70
     */
    public boolean isPerfectSquare4(int num) {
        double x0 = num;
        while (true) {
            double x1 = (x0 + num / x0) / 2;
            if (x0 - x1 < 1e-6) {
                break;
            }
            x0 = x1;
        }
        int x = (int) x0;
        return x * x == num;
    }

    /**
     *方法二：暴力
     *
     * 思路和算法
     *
     * 如果
     * num
     * num 为完全平方数，那么一定存在正整数
     * x
     * x 满足
     * x
     * ×
     * x
     * =
     * num
     * x×x=num。于是我们可以从
     * 1
     * 1 开始，从小到大遍历所有正整数，寻找是否存在满足
     * x
     * ×
     * x
     * =
     * num
     * x×x=num 的正整数
     * x
     * x。在遍历中，如果出现正整数
     * x
     * x 使
     * x
     * ×
     * x
     * >
     * num
     * x×x>num，那么更大的正整数也不可能满足
     * x
     * ×
     * x
     * =
     * num
     * x×x=num，不需要继续遍历了。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-perfect-square/solution/you-xiao-de-wan-quan-ping-fang-shu-by-le-wkee/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 8.56%
     * 的用户
     * 内存消耗：
     * 35.3 MB
     * , 在所有 Java 提交中击败了
     * 10.82%
     * 的用户
     * 通过测试用例：
     * 70 / 70
     */
    public boolean isPerfectSquare2(int num) {
        long x = 1, square = 1;
        while (square <= num) {
            if (square == num) {
                return true;
            }
            ++x;
            square = x * x;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Math.sqrt(Integer.MAX_VALUE));
    }
}