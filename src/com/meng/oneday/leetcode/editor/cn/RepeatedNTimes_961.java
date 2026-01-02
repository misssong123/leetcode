package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

class RepeatedNTimes_961 {
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了17.13% 的Java用户
     * 	内存消耗:46.8 MB,击败了20.51% 的Java用户
     * @param nums
     * @return
     */
    public int repeatedNTimes961(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0 ; i < nums.length ; i++){
            if(nums[i] == nums[i+1]){
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.8 MB,击败了20.51% 的Java用户
     * @param nums
     * @return
     */
    public int repeatedNTimesOther1(int[] nums) {

        HashSet<Integer> seen = new HashSet<>();

        for (int x : nums) {

            if (!seen.add(x)) { // x 在 seen 中

                return x;

            }

        }

        return -1; // 代码不会执行到这里

    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了44.94% 的Java用户
     * 	内存消耗:47 MB,击败了5.06% 的Java用户
     * @param nums
     * @return
     */
    public int repeatedNTimesOther2(int[] nums) {

        int ans = 0;

        int hp = 0;

        for (int i = 1; i < nums.length; i++) {

            int x = nums[i];

            if (x == nums[0]) {

                return x;

            }

            if (hp == 0) { // x 是初始擂主，生命值为 1

                ans = x;

                hp = 1;

            } else { // 比武，同门加血，否则扣血

                hp += x == ans ? 1 : -1;

            }

        }

        return ans;

    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.7 MB,击败了39.04% 的Java用户
     * @param nums
     * @return
     */
    public int repeatedNTimesOther3(int[] nums) {

        for (int i = 1; ; i++) {

            int x = nums[i];
            if (x == nums[i - 1] ||
                    i > 1 && x == nums[i - 2] ||
                    i > 2 && x == nums[i - 3]) {

                return x;

            }

        }

    }

    private static final Random rand = new Random();


    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.9 MB,击败了12.08% 的Java用户
     * @param nums
     * @return
     */
    public int repeatedNTimes(int[] nums) {

        int n = nums.length;

        while (true) {

            // 在 [0, n-1] 中随机生成两个不同下标

            int i = rand.nextInt(n);

            int j = rand.nextInt(n - 1);

            if (j >= i) {

                j++;

            }

            if (nums[i] == nums[j]) {

                return nums[i];

            }

        }

    }
}
