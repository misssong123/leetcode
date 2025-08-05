package com.meng.oneday.leetcode.editor.cn;

class NumOfUnplacedFruits3477 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了72.48% 的Java用户
     * 	内存消耗:43.7 MB,击败了25.69% 的Java用户
     * @param fruits
     * @param baskets
     * @return
     */
    public int numOfUnplacedFruits3477(int[] fruits, int[] baskets) {
        int n = baskets.length,res = 0;
        boolean[] used = new boolean[n];
        for(int fruit : fruits){
            boolean flag = true;
            for(int i = 0 ; i < n ; i++){
                if(!used[i] && fruit <= baskets[i]){
                    used[i] = true;
                    flag = false;
                    break;
                }
            }
            if(flag){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了45.87% 的Java用户
     * @param fruits
     * @param baskets
     * @return
     */
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int count = 0;
        int n = baskets.length;
        for (int fruit : fruits) {
            int unset = 1;
            for (int i = 0; i < n; i++) {
                if (fruit <= baskets[i]) {
                    baskets[i] = 0;
                    unset = 0;
                    break;
                }
            }
            count += unset;
        }
        return count;
    }

}
