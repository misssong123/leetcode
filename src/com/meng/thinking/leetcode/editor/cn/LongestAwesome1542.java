package com.meng.thinking.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LongestAwesome1542 {
    /**
     * 超时
     * @param s
     * @return
     */
    public int longestAwesomeMy(String s) {
        int len =  s.length();
        int[][] cache = new int[len+1][10];
        for (int i= 0; i < len; i++){
            System.arraycopy(cache[i], 0, cache[i+1], 0, 10);
            cache[i+1][s.charAt(i) - '0']++;
        }
        for (int i = len-1 ; i >= 0 ; i--){
            for(int j = 0 ; j + i < len ;j++){
                int[] head = cache[j];
                int[] end = cache[j+i+1];
                int  count = 0;
                for(int index = 0 ; index < 10 ; index++){
                    if ((end[index] - head[index])%2 != 0){
                        count++;
                    }
                    if (count > 1){
                        break;
                    }
                }
                if (count <= 1){
                    return i+1;
                }
            }
        }
        return 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:107 ms,击败了63.10% 的Java用户
     * 	内存消耗:44.2 MB,击败了70.24% 的Java用户
     * @param s
     * @return
     */
    public int longestAwesome(String s) {
        int n = s.length();
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, -1);
        int ans = 0;
        int sequence = 0;
        for (int j = 0; j < n; ++j) {
            int digit = s.charAt(j) - '0';
            sequence ^= (1 << digit);
            if (prefix.containsKey(sequence)) {
                //中间字符个数全部为偶数
                ans = Math.max(ans, j - prefix.get(sequence));
            } else {
                prefix.put(sequence, j);
            }
            for (int k = 0; k < 10; ++k) {
                if (prefix.containsKey(sequence ^ (1 << k))) {
                    //中间字符个数存在一个奇数
                    ans = Math.max(ans, j - prefix.get(sequence ^ (1 << k)));
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
