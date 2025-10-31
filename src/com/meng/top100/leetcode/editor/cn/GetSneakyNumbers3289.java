package com.meng.top100.leetcode.editor.cn;

class GetSneakyNumbers3289 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了90.73% 的Java用户
     * 	内存消耗:44.9 MB,击败了5.30% 的Java用户
     * @param nums
     * @return
     */
    public int[] getSneakyNumbers3289(int[] nums) {
        int [] res = new int[2];
        int index = 0;
        int n = nums.length-2;
        for (int i = 0 ; i < n ; i++){
            //找到自己的位置
            while (nums[i] != i){
                int temp = nums[i];
                if (nums[temp] == temp){
                    nums[i] = nums[n+index];
                    nums[n+index++] = temp;
                }else{
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
        }
        System.arraycopy(nums, n, res, 0, 2);
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了90.73% 的Java用户
     * 	内存消耗:45.1 MB,击败了5.30% 的Java用户
     * @param nums
     * @return
     */
    public int[] getSneakyNumbersOther(int[] nums) {
        int n = nums.length - 2;
        int xorAll = n ^ (n + 1); // n 和 n+1 多异或了
        for (int i = 0; i < nums.length; i++) {
            xorAll ^= i ^ nums[i];
        }
        int shift = Integer.numberOfTrailingZeros(xorAll);

        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (i < n) {
                ans[i >> shift & 1] ^= i;
            }
            ans[nums[i] >> shift & 1] ^= nums[i];
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了90.73% 的Java用户
     * 	内存消耗:44.7 MB,击败了5.30% 的Java用户
     * @param nums
     * @return
     */
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length - 2;
        int a = -n * (n - 1) / 2;
        int b = -n * (n - 1) * (n * 2 - 1) / 6;
        for (int x : nums) {
            a += x;
            b += x * x;
        }
        int x = (a - (int) Math.sqrt(b * 2 - a * a)) / 2;
        return new int[]{x, a - x};
    }
}
