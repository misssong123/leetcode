package com.meng.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 412. Fizz Buzz
 * 难度
 * 简单
 *
 * 142
 *
 *
 *
 *
 *
 * 给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中：
 *
 * answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。
 * answer[i] == "Fizz" 如果 i 是 3 的倍数。
 * answer[i] == "Buzz" 如果 i 是 5 的倍数。
 * answer[i] == i 如果上述条件全不满足。
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["1","2","Fizz"]
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：["1","2","Fizz","4","Buzz"]
 * 示例 3：
 *
 * 输入：n = 15
 * 输出：["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
 *
 *
 * 提示：
 *
 * 1 <= n <= 104
 */
public class FizzBuzz {
    /**
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 39.58%
     * 的用户
     * 内存消耗：
     * 39.8 MB
     * , 在所有 Java 提交中击败了
     * 17.11%
     * 的用户
     * 通过测试用例：
     * 8 / 8
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList(n);
        for(int i = 1 ; i <= n ; i++){
            if(i % 15 == 0 ){
                res.add("FizzBuzz");
            }else if(i % 3 == 0){
                res.add("Fizz");
            }else if(i % 5 == 0){
                res.add("Buzz");
            }else{
                res.add(i+"");
            }
        }
        return res;
    }

    /**
     * 方法一：模拟 + 字符串拼接
     *
     * 题目要求返回数组
     * answer
     * answer，对于每个
     * 1
     * ≤
     * i
     * ≤
     * n
     * 1≤i≤n，
     * answer
     * [
     * i
     * ]
     * answer[i] 为
     * i
     * i 的转化。注意下标
     * i
     * i 从
     * 1
     * 1 开始。
     *
     * 根据题目描述，当
     * i
     * i 是
     * 3
     * 3 的倍数时
     * answer
     * [
     * i
     * ]
     * answer[i] 包含
     * “Fizz"
     * “Fizz"，当
     * i
     * i 是
     * 5
     * 5 的倍数时
     * answer
     * [
     * i
     * ]
     * answer[i] 包含
     * “Buzz"
     * “Buzz"，当
     * i
     * i 同时是
     * 3
     * 3 的倍数和
     * 5
     * 5 的倍数时
     * answer
     * [
     * i
     * ]
     * answer[i] 既包含
     * “Fizz"
     * “Fizz" 也包含
     * “Fuzz"
     * “Fuzz"，且
     * “Fizz"
     * “Fizz" 在
     * “Buzz"
     * “Buzz" 前面。
     *
     * 基于上述规则，对于每个
     * 1
     * ≤
     * i
     * ≤
     * n
     * 1≤i≤n，可以使用字符串拼接的方式得到
     * answer
     * [
     * i
     * ]
     * answer[i]，具体操作如下：
     *
     * 如果
     * i
     * i 是
     * 3
     * 3 的倍数，则将
     * “Fizz"
     * “Fizz" 拼接到
     * answer
     * [
     * i
     * ]
     * answer[i]；
     * 如果
     * i
     * i 是
     * 5
     * 5 的倍数，则将
     * “Buzz"
     * “Buzz" 拼接到
     * answer
     * [
     * i
     * ]
     * answer[i]；
     * 如果
     * answer
     * [
     * i
     * ]
     * answer[i] 为空，则
     * i
     * i 既不是
     * 3
     * 3 的倍数也不是
     * 5
     * 5 的倍数，将
     * i
     * i 拼接到
     * answer
     * [
     * i
     * ]
     * answer[i]。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/fizz-buzz/solution/fizz-buzz-by-leetcode-solution-s0s5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 49.63%
     * 的用户
     * 内存消耗：
     * 40 MB
     * , 在所有 Java 提交中击败了
     * 6.39%
     * 的用户
     * 通过测试用例：
     * 8 / 8
     */
    public List<String> fizzBuzz1(int n) {
        List<String> answer = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                sb.append("Fizz");
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }
            if (sb.length() == 0) {
                sb.append(i);
            }
            answer.add(sb.toString());
            sb.delete(0,10);
        }
        return answer;
    }


}
