package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class FindPeaks2951 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.1 MB,击败了22.06% 的Java用户
     * @param mountain
     * @return
     */
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> res = new ArrayList<>();
        for(int i = 1; i < mountain.length -1 ; i++){
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]){
                res.add(i++);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
