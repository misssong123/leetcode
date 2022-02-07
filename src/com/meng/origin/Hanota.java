package com.meng.origin;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.06. 汉诺塔问题
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 *
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 *
 * 你需要原地修改栈。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hanota-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Hanota {
    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        A.add(0);
        A.add(1);
        A.add(2);
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        Hanota demo = new Hanota();
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        demo.hanota(A,B,C);
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
    }
    /**
     * 解题思路
     * 将A中塔看成两部分最后一块（L)和非最后一块(R)
     * 将A中的R移动到B中
     * 将A中的L移动到C中
     * 将B中的R移动到C中
     * @param A
     * @param B
     * @param C
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(),A,B,C);
    }

    private void move(int size, List<Integer> a, List<Integer> b, List<Integer> c) {
        if (size==1){
            c.add(a.remove(a.size()-1));
            return;
        }
        //将A中的R移动到B中
        move(size-1,a,c,b);
        //将A中的L移动到C中
        c.add(a.remove(a.size()-1));
        //将B中的R移动到C中
        move(size-1,b,a,c);
    }
}
