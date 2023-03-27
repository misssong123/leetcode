package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class KthGrammar779 {
    public int kthGrammar(int n, int k) {
        if (k == 1){
            return 0;
        }
        if (k == 2){
            return 1;
        }

        return dfs(n,k,k%2+1);
    }

    private int dfs(int n, int k,int index) {
        if (n == 2){
            return k -1;
        }
        int res = dfs(n - 1, (k + 1) / 2, k % 2 + 1);
        return index == 1 ? res : res^1;
    }

    public static void main(String[] args) {
        KthGrammar779 demo = new KthGrammar779();
        System.out.println(demo.kthGrammar(3,3));
    }
    /**
     * 递归
     * @param n
     * @param k
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 56.65%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */
    public int kthGrammar1(int n, int k) {
        if (n == 1) {
            return 0;
        }
        return (k & 1) ^ 1 ^ kthGrammar1(n - 1, (k + 1) / 2);
    }

    /**
     * 规律+递归
     * @param n
     * @param k
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 61.70%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */
    public int kthGrammar2(int n, int k) {
        if (k == 1) {
            return 0;
        }
        if (k > (1 << (n - 2))) {
            return 1 ^ kthGrammar2(n - 1, k - (1 << (n - 2)));
        }
        return kthGrammar2(n - 1, k);
    }

    /**
     * 递归+位运算
     * @param n
     * @param k
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38 MB
     * , 在所有 Java 提交中击败了
     * 92.29%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */
    public int kthGrammar3(int n, int k) {
        k--;
        int res = 0;
        while (k > 0) {
            k &= k - 1;
            res ^= 1;
        }
        return res;
    }


}
