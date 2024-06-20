package com.meng.thinking.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class CountBeautifulPairs2748 {
    /**
     * 解答成功:
     * 	执行耗时:226 ms,击败了5.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了5.00% 的Java用户
     * @param nums
     * @return
     */
    public int countBeautifulPairsMy(int[] nums) {
        Map<String, Boolean> cnt = new HashMap<>();
        int n = nums.length;
        int res = 0;
        for (int i = 0 ; i < n ; i++){
            for (int j = i+1 ; j < n ; j++){
                int a = nums[j]%10;
                int b = (nums[i]+"").charAt(0)-'0';
                boolean gcd = cnt.getOrDefault(a+""+b,gcdMy(a, b));
                if(gcd){
                    res++;
                }
                cnt.put(a+""+b,gcd);
                cnt.put(b+""+a,gcd);
            }
        }
        return res;
    }
    public boolean gcdMy(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a <= 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了64.55% 的Java用户
     * 	内存消耗:43.5 MB,击败了36.36% 的Java用户
     * @param nums
     * @return
     */
    public int countBeautifulPairs(int[] nums) {
        int res = 0;
        int[] cnt = new int[10];
        for (int x : nums) {
            for (int y = 1; y <= 9; y++) {
                if (gcd(x % 10, y) == 1) {
                    res += cnt[y];
                }
            }
            while (x >= 10) {
                x /= 10;
            }
            cnt[x]++;
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


}
//leetcode submit region end(Prohibit modification and deletion)
