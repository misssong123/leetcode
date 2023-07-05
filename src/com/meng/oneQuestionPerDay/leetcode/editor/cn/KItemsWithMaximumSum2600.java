package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class KItemsWithMaximumSum2600 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 82.01%
     * 的用户
     * 内存消耗：
     * 38.9 MB
     * , 在所有 Java 提交中击败了
     * 64.66%
     * 的用户
     * 通过测试用例：
     * 1310 / 1310
     * @param numOnes
     * @param numZeros
     * @param numNegOnes
     * @param k
     * @return
     */
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int res = 0 ;
        if (numOnes >= k){
            return k;
        }else {
            res += numOnes;
            k -= numOnes;
        }
        if (numZeros >= k){
            return res;
        }else {
            k -= numZeros;
        }
        if (numNegOnes >= k){
            res -= k;
        }
        return res;
    }

    public static void main(String[] args) {
        int numOnes = 3, numZeros = 0,  numNegOnes = 5, k = 8;
        KItemsWithMaximumSum2600 demo = new KItemsWithMaximumSum2600();
        System.out.println(demo.kItemsWithMaximumSum(numOnes,numZeros,numNegOnes,k));
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 20.53%
     * 的用户
     * 通过测试用例：
     * 1310 / 1310
     * @param numOnes
     * @param numZeros
     * @param numNegOnes
     * @param k
     * @return
     */
    public int kItemsWithMaximumSum1(int numOnes, int numZeros, int numNegOnes, int k) {
        if (k <= numOnes) {
            return k;
        } else if (k <= numOnes + numZeros) {
            return numOnes;
        } else {
            return numOnes - (k - numOnes - numZeros);
        }
    }

}

