package com.meng;

import java.util.Deque;
import java.util.LinkedList;

public class EvalRPN {
    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了57.67% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了60.98% 的用户
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> deque = new LinkedList<>();
        int len = tokens.length;
        int a , b;
        for(int i = 0 ; i < len ; i++){
            if (tokens[i].equals("+")){
                b = deque.pop();
                a = deque.pop();
                deque.push(a+b);
            }else if (tokens[i].equals("-")){
                b = deque.pop();
                a = deque.pop();
                deque.push(a-b);
            }else if (tokens[i].equals("/")){
                b = deque.pop();
                a = deque.pop();
                deque.push(a/b);
            }else if (tokens[i].equals("*")){
                b = deque.pop();
                a = deque.pop();
                deque.push(a*b);
            }else {
                deque.push(Integer.parseInt(tokens[i]));
            }
        }
        return deque.pop();
    }
    /**
     * 方法一：栈
     *
     * 逆波兰表达式严格遵循「从左到右」的运算。计算逆波兰表达式的值时，使用一个栈存储操作数，从左到右遍历逆波兰表达式，进行如下操作：
     *
     *     如果遇到操作数，则将操作数入栈；
     *
     *     如果遇到运算符，则将两个操作数出栈，其中先出栈的是右操作数，后出栈的是左操作数，使用运算符对两个操作数进行运算，将运算得到的新操作数入栈。
     *
     * 整个逆波兰表达式遍历完毕之后，栈内只有一个元素，该元素即为逆波兰表达式的值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/solution/ni-bo-lan-biao-da-shi-qiu-zhi-by-leetcod-wue9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：7 ms, 在所有 Java 提交中击败了57.67% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了47.99% 的用户
     */
    public int evalRPN1(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
    /**
     * 方法二：数组模拟栈
     *
     * 方法一使用栈存储操作数。也可以使用一个数组模拟栈操作。
     *
     * 如果使用数组代替栈，则需要预先定义数组的长度。对于长度为 nnn 的逆波兰表达式，显然栈内元素个数不会超过 nnn，但是将数组的长度定义为 nnn 仍然超过了栈内元素个数的上界。那么，栈内元素最多可能有多少个？
     *
     * 对于一个有效的逆波兰表达式，其长度 nnn 一定是奇数，且操作数的个数一定比运算符的个数多 111 个，即包含 n+12\frac{n+1}{2}2n+1​ 个操作数和 n−12\frac{n-1}{2}2n−1​ 个运算符。考虑遇到操作数和运算符时，栈内元素个数分别会如何变化：
     *
     *     如果遇到操作数，则将操作数入栈，因此栈内元素增加 111 个；
     *
     *     如果遇到运算符，则将两个操作数出栈，然后将一个新操作数入栈，因此栈内元素先减少 222 个再增加 111 个，结果是栈内元素减少 111 个。
     *
     * 由此可以得到操作数和运算符与栈内元素个数变化的关系：遇到操作数时，栈内元素增加 111 个；遇到运算符时，栈内元素减少 111 个。
     *
     * 最坏情况下，n+12\frac{n+1}{2}2n+1​ 个操作数都在表达式的前面，n−12\frac{n-1}{2}2n−1​ 个运算符都在表达式的后面，此时栈内元素最多为 n+12\frac{n+1}{2}2n+1​ 个。在其余情况下，栈内元素总是少于 n+12\frac{n+1}{2}2n+1​ 个。因此，在任何情况下，栈内元素最多可能有 n+12\frac{n+1}{2}2n+1​ 个，将数组的长度定义为 n+12\frac{n+1}{2}2n+1​ 即可。
     *
     * 具体实现方面，创建数组 stack\textit{stack}stack 模拟栈，数组下标 000 的位置对应栈底，定义 index\textit{index}index 表示栈顶元素的下标位置，初始时栈为空，index=−1\textit{index}=-1index=−1。当遇到操作数和运算符时，进行如下操作：
     *
     *     如果遇到操作数，则将 index\textit{index}index 的值加 111，然后将操作数赋给 stack[index]\textit{stack}[\textit{index}]stack[index]；
     *
     *     如果遇到运算符，则将 index\textit{index}index 的值减 111，此时 stack[index]\textit{stack}[\textit{index}]stack[index] 和 stack[index+1]\textit{stack}[\textit{index}+1]stack[index+1] 的元素分别是左操作数和右操作数，使用运算符对两个操作数进行运算，将运算得到的新操作数赋给 stack[index]\textit{stack}[\textit{index}]stack[index]。
     *
     * 整个逆波兰表达式遍历完毕之后，栈内只有一个元素，因此 index=0\textit{index}=0index=0，此时 stack[index]\textit{stack}[\textit{index}]stack[index] 即为逆波兰表达式的值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/solution/ni-bo-lan-biao-da-shi-qiu-zhi-by-leetcod-wue9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.81% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了15.07% 的用户
     *
     */
    public int evalRPN2(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[(n + 1) / 2];
        int index = -1;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            switch (token) {
                case "+":
                    index--;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                default:
                    index++;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }
}
