package com.meng;

import java.util.ArrayList;
import java.util.List;

/**
 * 1018. 可被 5 整除的二进制前缀
 *
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 *
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 *
 * 示例 2：
 *
 * 输入：[1,1,1]
 * 输出：[false,false,false]
 *
 * 示例 3：
 *
 * 输入：[0,1,1,1,1,1]
 * 输出：[true,false,false,false,true,false]
 *
 * 示例 4：
 *
 * 输入：[1,1,1,0,1]
 * 输出：[false,false,false,false,false]
 *
 *
 *
 * 提示：
 *
 *     1 <= A.length <= 30000
 *     A[i] 为 0 或 1
 */
public class PrefixesDivBy5 {
    /**
     * 长度太长时，会报错
     * @param A
     * @return
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        long pre = 0;
        for(int i = 0 ; i < A.length ; i++){
          /*  pre = (pre<<1) + A[i];
            if (pre % 5 == 0){
                list.add(true);
                continue;
            }
            list.add(false);*/
          //修改后
            pre = ((pre<<1) + A[i])%5;
            list.add(pre==0);
        }
        return list;
    }

    /**
     *方法一：模拟
     *
     * 令 NiN_iNi​ 为数组 AAA 从下标 000 到下标 iii 的前缀表示的二进制数，则有 N0=A[0]N_0=A[0]N0​=A[0]。当 i>0i>0i>0 时，有 Ni=Ni−1×2+A[i]N_i=N_{i-1} \times 2+A[i]Ni​=Ni−1​×2+A[i]。令 nnn 为数组 AAA 的长度，则对于 0≤i<n0 \le i<n0≤i<n，可以依次计算每个 NiN_iNi​ 的值。对于每个 NiN_iNi​ 的值，判断其是否可以被 555 整除，即可得到答案。
     *
     * 考虑到数组 AAA 可能很长，如果每次都保留 NiN_iNi​ 的值，则可能导致溢出。由于只需要知道每个 NiN_iNi​ 是否可以被 555 整除，因此在计算过程中只需要保留余数即可。
     *
     * 令 MiM_iMi​ 表示计算到下标 iii 时的除以 555 的余数，则有 M0=A[0]M_0=A[0]M0​=A[0]（显然 A[0]A[0]A[0] 一定小于 555），当 i>0i>0i>0 时，有 Mi=(Mi−1×2+A[i]) mod 5M_i=(M_{i-1} \times 2+A[i])\bmod 5Mi​=(Mi−1​×2+A[i])mod5，判断每个 MiM_iMi​ 是否为 000 即可。由于 MiM_iMi​ 一定小于 555，因此不会溢出。
     *
     * 如何证明判断 MiM_iMi​ 是否为 000 可以得到正确的结果？可以通过数学归纳法证明。
     *
     * 当 i=0i=0i=0 时，由于 N0=A[0]<5N_0=A[0]<5N0​=A[0]<5，因此 M0=N0M_0=N_0M0​=N0​，M0=N0 mod 5M_0=N_0\bmod 5M0​=N0​mod5 成立。
     *
     * 当 i>0i>0i>0 时，假设 Mi−1=Ni−1 mod 5M_{i-1}=N_{i-1}\bmod 5Mi−1​=Ni−1​mod5 成立，考虑 Ni mod 5N_i\bmod 5Ni​mod5 和 MiM_iMi​ 的值：
     *
     * Ni mod 5=(Ni−1×2+A[i]) mod 5=(Ni−1×2) mod 5+A[i] mod 5Mi=(Mi−1×2+A[i]) mod 5=(Ni−1 mod 5×2+A[i]) mod 5=(Ni−1 mod 5×2) mod 5+A[i] mod 5=(Ni−1×2) mod 5+A[i] mod 5\begin{aligned} N_i\bmod 5=&(N_{i-1} \times 2+A[i])\bmod 5 \\ =&(N_{i-1} \times 2)\bmod 5+A[i]\bmod 5 \\ \\ M_i=&(M_{i-1} \times 2+A[i])\bmod 5 \\ =&(N_{i-1}\bmod 5 \times 2+A[i])\bmod 5 \\ =&(N_{i-1}\bmod 5 \times 2)\bmod 5+A[i]\bmod 5 \\ =&(N_{i-1} \times 2)\bmod 5+A[i]\bmod 5 \end{aligned} Ni​mod5==Mi​====​(Ni−1​×2+A[i])mod5(Ni−1​×2)mod5+A[i]mod5(Mi−1​×2+A[i])mod5(Ni−1​mod5×2+A[i])mod5(Ni−1​mod5×2)mod5+A[i]mod5(Ni−1​×2)mod5+A[i]mod5​
     *
     * 因此有 Mi=Ni mod 5M_i=N_i\bmod 5Mi​=Ni​mod5 成立。
     *
     * 由此可得，对任意 0≤i<n0 \le i<n0≤i<n，都有 Mi=Ni mod 5M_i=N_i\bmod 5Mi​=Ni​mod5，因此计算 MiM_iMi​ 即可得到正确的结果。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5/solution/ke-bei-5-zheng-chu-de-er-jin-zhi-qian-zh-asih/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @return
     * 执行用时：4 ms, 在所有 Java 提交中击败了92.76% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了26.32% 的用户
     */
    public List<Boolean> prefixesDivBy51(int[] A) {
        List<Boolean> list = new ArrayList<Boolean>();
        int prefix = 0;
        int length = A.length;
        for (int i = 0; i < length; i++) {
            prefix = ((prefix << 1) + A[i]) % 5;
            list.add(prefix == 0);
        }
        return list;
    }

}