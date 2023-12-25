package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class NumOfBurgers1276 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了5.13% 的Java用户
     * @param tomatoSlices
     * @param cheeseSlices
     * @return
     */
    public List<Integer> numOfBurgersMy(int tomatoSlices, int cheeseSlices) {
        if (cheeseSlices == 0 && tomatoSlices == 0){
            return Arrays.asList(0,0);
        }
        if (cheeseSlices * 4 < tomatoSlices ||cheeseSlices * 2 >tomatoSlices){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int left = 0 , right = cheeseSlices;
        while (left <= right){
            int mid = (left + right) >> 1;
            int num = 4 *  mid + 2 * (cheeseSlices-mid);
            if (num == tomatoSlices){
                res.add(mid);
                res.add(cheeseSlices - mid);
                break;
            }else if (num < tomatoSlices){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     *解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.3 MB,击败了5.13% 的Java用户
     * @param tomatoSlices
     * @param cheeseSlices
     * @return
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices % 2 != 0 || tomatoSlices < cheeseSlices * 2 || cheeseSlices * 4 < tomatoSlices) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<Integer>();
        ans.add(tomatoSlices / 2 - cheeseSlices);
        ans.add(cheeseSlices * 2 - tomatoSlices / 2);
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
