package com.meng.origin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 989. 数组形式的整数加法
 *
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 *
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 *
 *
 *
 * 示例 1：
 *
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 *
 * 示例 2：
 *
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 *
 * 示例 3：
 *
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 *
 * 示例 4：
 *
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *
 *
 *
 * 提示：
 *
 *     1 <= A.length <= 10000
 *     0 <= A[i] <= 9
 *     0 <= K <= 10000
 *     如果 A.length > 1，那么 A[0] != 0
 */
public class AddToArrayForm {
    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了56.68% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了52.94% 的用户
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        int len = A.length,temp = 0 ,sum =0;
        for(int i = len -1 ;i>=0 ; i--){
            sum = A[i] + K%10 + temp;
            temp = sum / 10;
            sum %= 10;
            K /= 10;
            list.add(sum);
        }
        while (K>0){
            sum = K%10 + temp;
            temp = sum / 10;
            sum %= 10;
            K /= 10;
            list.add(sum);
        }
        if (temp>0)
            list.add(temp);
        Collections.reverse(list);
        return list;
    }
    /**
     * 方法一：逐位相加
     *
     * 思路
     *
     * 让我们逐位将数字加在一起。例如计算 123+912123+912123+912，我们从低位到高位依次计算 3+23+23+2、2+12+12+1 和 1+91+91+9。任何时候，若加法的结果大于等于 101010，把进位的 111 加入到下一位的计算中，所以最终结果为 103510351035。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer/solution/shu-zu-xing-shi-de-zheng-shu-jia-fa-by-l-jljp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：6 ms, 在所有 Java 提交中击败了31.03% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了50.06% 的用户
     */
    public List<Integer> addToArrayForm1(int[] A, int K) {
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for (int i = n - 1; i >= 0; --i) {
            int sum = A[i] + K % 10;
            K /= 10;
            if (sum >= 10) {
                K++;
                sum -= 10;
            }
            res.add(sum);
        }
        for (; K > 0; K /= 10) {
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }
    /**另一个思路是将整个加数 KKK 加入数组表示的数的最低位。

     上面的例子 123+912123+912123+912，我们把它表示成 [1,2,3+912][1,2,3+912][1,2,3+912]。然后，我们计算 3+912=9153+912=9153+912=915。555 留在当前这一位，将 910/10=91910/10=91910/10=91 以进位的形式加入下一位。

     然后，我们再重复这个过程，计算 [1,2+91,5][1,2+91,5][1,2+91,5]。我们得到 939393，333 留在当前位，将 90/10=990/10=990/10=9 以进位的形式加入下一位。继而又得到 [1+9,3,5][1+9,3,5][1+9,3,5]，重复这个过程之后，最终得到结果 [1,0,3,5][1,0,3,5][1,0,3,5]。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer/solution/shu-zu-xing-shi-de-zheng-shu-jia-fa-by-l-jljp/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：4 ms, 在所有 Java 提交中击败了86.63% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了57.27% 的用户
     */
    public List<Integer> addToArrayForm2(int[] A, int K) {
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for (int i = n - 1; i >= 0 || K > 0; --i, K /= 10) {
            if (i >= 0) {
                K += A[i];
            }
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }
}
