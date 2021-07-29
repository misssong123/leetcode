package com.meng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1104. 二叉树寻路
 *
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
 *
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 *
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 *
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：label = 14
 * 输出：[1,3,4,14]
 *
 * 示例 2：
 *
 * 输入：label = 26
 * 输出：[1,2,6,10,26]
 *
 *
 *
 * 提示：
 *
 *     1 <= label <= 10^6
 */
public class PathInZigZagTree {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了76.65% 的用户
     * @param label
     * @return
     */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        int temp = 2;
        int index = 1;
        while (temp < label + 1){
            temp *= 2;
            index++;
        }
        res.add(label);
        if (index % 2 == 0){
            label = (temp - label - 1) + temp / 2;
        }
        label = label / 2 ;
        while (label > 0){
            res.add(0,label);
            label = label / 2 ;
        }
        if (res.size() > 1){
            for(int i = 0 ,j = 2; i < res.size() - 1; i++ , j=j*2){
                if (i % 2 != 0){
                    int ans =  j / 2 + j - res.get(i) - 1;
                    res.set(i,ans);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int label = 1 ;
        PathInZigZagTree demo = new PathInZigZagTree();
        System.out.println(demo.pathInZigZagTree(label));
    }

    /**
     *方法一：数学
     *
     * 我们先来研究一个简单的情形：二叉树的每一行都是按从左到右的顺序进行标记。此时二叉树满足以下性质：
     *
     *     根节点位于第 111 行；
     *
     *     第 iii 行有 2i−12^{i-1}2i−1 个节点，最左边的节点标号为 2i−12^{i-1}2i−1，最右边的节点标号为 2i−12^i-12i−1；
     *
     *     对于标号为 val\textit{val}val 的节点，其左子节点的标号为 2×val2 \times \textit{val}2×val，右子节点的标号为 2×val+12 \times \textit{val} + 12×val+1，当 val>1\textit{val}>1val>1 时，其父节点的标号为 ⌊val2⌋\lfloor \frac{\textit{val}}{2} \rfloor⌊2val​⌋。
     *
     * 对于给定节点的标号 label\textit{label}label，可以根据上述性质得到从该节点到根节点的路径，将路径反转后，即为从根节点到标号 label\textit{label}label 的节点的路径。
     *
     * 回到这题，对于偶数行按从右到左的顺序进行标记的情况，可以转换成按从左到右的顺序进行标记的情况，然后按照上述思路得到路径，只要对偶数行的标号进行转换即可。为了表述简洁，下文将按从左到右的顺序进行标记时的节点的标号称为「从左到右标号」。
     *
     * 首先找到标号为 label\textit{label}label 的节点所在的行和该节点的「从左到右标号」。为了找到节点所在行，需要找到 iii 满足 2i−1≤label<2i2^{i-1} \le \textit{label} < 2^i2i−1≤label<2i，则该节点在第 iii 行。该节点的「从左到右标号」需要根据 iii 的奇偶性计算：
     *
     *     当 iii 是奇数时，第 iii 行为按从左到右的顺序进行标记，因此该节点的「从左到右标号」即为 label\textit{label}label；
     *
     *     当 iii 是偶数时，第 iii 行为按从右到左的顺序进行标记，将整行的标号左右翻转之后得到按从左到右的顺序进行标记的标号，对于同一个节点，其翻转前后的标号之和为 2i−1+2i−12^{i-1} + 2^i - 12i−1+2i−1，因此标号为 label\textit{label}label 的节点的「从左到右标号」为 2i−1+2i−1−label2^{i-1} + 2^i - 1 - \textit{label}2i−1+2i−1−label。
     *
     * 得到标号为 label\textit{label}label 的节点的「从左到右标号」之后，即可得到从该节点到根节点的路径，以及路径上的每个节点的「从左到右标号」。对于路径上的每个节点，需要根据节点所在行的奇偶性，得到该节点的实际标号：
     *
     *     当 iii 是奇数时，第 iii 行的每个节点的「从左到右标号」即为该节点的实际标号；
     *
     *     当 iii 是偶数时，如果第 iii 行的一个节点的「从左到右标号」为 val\textit{val}val，则该节点的实际标号为 2i−1+2i−1−val2^{i-1} + 2^i - 1 - \textit{val}2i−1+2i−1−val。
     *
     * 最后，将路径反转，即可得到从根节点到标号 label\textit{label}label 的节点的路径。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/solution/er-cha-shu-xun-lu-by-leetcode-solution-ryx0/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param label
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了92.95% 的用户
     */
    public List<Integer> pathInZigZagTree1(int label) {
        int row = 1, rowStart = 1;
        while (rowStart * 2 <= label) {
            row++;
            rowStart *= 2;
        }
        if (row % 2 == 0) {
            label = getReverse(label, row);
        }
        List<Integer> path = new ArrayList<Integer>();
        while (row > 0) {
            if (row % 2 == 0) {
                path.add(getReverse(label, row));
            } else {
                path.add(label);
            }
            row--;
            label >>= 1;
        }
        Collections.reverse(path);
        return path;
    }

    public int getReverse(int label, int row) {
        return (1 << row - 1) + (1 << row) - 1 - label;
    }
}
