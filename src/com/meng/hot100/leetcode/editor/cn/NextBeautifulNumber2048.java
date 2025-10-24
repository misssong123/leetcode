package com.meng.hot100.leetcode.editor.cn;

import java.util.Arrays;

class NextBeautifulNumber2048 {
    /**
     * 解答成功:
     * 	执行耗时:36 ms,击败了76.92% 的Java用户
     * 	内存消耗:43.6 MB,击败了42.31% 的Java用户
     * @param n
     * @return
     */
    public int nextBeautifulNumber2048(int n) {
        for(int i = n + 1 ; i <= 1224444 ; i++){
            if(isBeautifulNumber(i)){
                return i;
            }
        }
        return -1;
    }

    private boolean isBeautifulNumber(int i) {
        int[] nums = new int[7];
        while (i > 0){
            int num = i % 10;
            if (num == 0 || num >= 7){
                return false;
            }
            nums[num]++;
            i /= 10;
        }
        for (int j = 0 ; j < 7 ; j++){
            if (nums[j] != 0 && nums[j] != j ){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:98 ms,击败了57.69% 的Java用户
     * 	内存消耗:43.7 MB,击败了19.23% 的Java用户
     * @param n
     * @return
     */
    public int nextBeautifulNumberOfficial(int n) {
        for (int i = n + 1; i <= 1224444; ++i) {
            if (isBalance(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isBalance(int x) {
        int[] count = new int[10];
        while (x > 0) {
            count[x % 10]++;
            x /= 10;
        }
        for (int d = 0; d < 10; ++d) {
            if (count[d] > 0 && count[d] != d) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.7 MB,击败了88.46% 的Java用户
     */
    private static final int[] balance = new int[] {
            1, 22, 122, 212, 221, 333, 1333, 3133, 3313, 3331, 4444,
            14444, 22333, 23233, 23323, 23332, 32233, 32323, 32332,
            33223, 33232, 33322, 41444, 44144, 44414, 44441, 55555,
            122333, 123233, 123323, 123332, 132233, 132323, 132332,
            133223, 133232, 133322, 155555, 212333, 213233, 213323,
            213332, 221333, 223133, 223313, 223331, 224444, 231233,
            231323, 231332, 232133, 232313, 232331, 233123, 233132,
            233213, 233231, 233312, 233321, 242444, 244244, 244424,
            244442, 312233, 312323, 312332, 313223, 313232, 313322,
            321233, 321323, 321332, 322133, 322313, 322331, 323123,
            323132, 323213, 323231, 323312, 323321, 331223, 331232,
            331322, 332123, 332132, 332213, 332231, 332312, 332321,
            333122, 333212, 333221, 422444, 424244, 424424, 424442,
            442244, 442424, 442442, 444224, 444242, 444422, 515555,
            551555, 555155, 555515, 555551, 666666, 1224444
    };

    public int nextBeautifulNumberOfficial2(int n) {
        int i = Arrays.binarySearch(balance, n + 1);
        if (i < 0) {
            i = -i - 1;
        }
        return balance[i];
    }

}
