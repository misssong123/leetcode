package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class CanPlaceFlowers605 {
    /**
     * 单次遍历
     * @param flowerbed
     * @param n
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 75.53%
     * 的用户
     * 内存消耗：
     * 42.6 MB
     * , 在所有 Java 提交中击败了
     * 42.39%
     * 的用户
     * 通过测试用例：
     * 127 / 127
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        //长度
        int len = flowerbed.length;
        for(int i = 0 ; i < len ; i ++){
            //当前位置有花，直接跳过
            if (flowerbed[i] == 1){
                continue;
            }
            //目标为0，表示符合条件
            if (n == 0 ){
                break;
            }
            int left = i - 1 >= 0 ? flowerbed[i-1] : 0;
            int right = i + 1 < len ? flowerbed[i+1] : 0;
            if (left == 0 && right == 0){
                flowerbed[i] = 1 ;
                n--;
            }
        }
        return n == 0;
    }

    public static void main(String[] args) {
        CanPlaceFlowers605 demo = new CanPlaceFlowers605();
        int[] flowerbed = {1,0,0,0,0,1};
        int n = 2;
        System.out.println(demo.canPlaceFlowers(flowerbed,n));
    }

    /**
     * 贪心
     * @param flowerbed
     * @param n
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 75.53%
     * 的用户
     * 内存消耗：
     * 42.7 MB
     * , 在所有 Java 提交中击败了
     * 40.45%
     * 的用户
     * 通过测试用例：
     * 127 / 127
     */
    public boolean canPlaceFlowers1(int[] flowerbed, int n) {
        int count = 0;
        int m = flowerbed.length;
        int prev = -1;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) {
                if (prev < 0) {
                    count += i / 2;
                } else {
                    count += (i - prev - 2) / 2;
                }
                if (count >= n) {
                    return true;
                }
                prev = i;
            }
        }
        if (prev < 0) {
            count += (m + 1) / 2;
        } else {
            count += (m - prev - 1) / 2;
        }
        return count >= n;
    }

}

