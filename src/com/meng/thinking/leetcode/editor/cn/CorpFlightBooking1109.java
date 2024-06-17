package com.meng.thinking.leetcode.editor.cn;

class CorpFlightBooking1109 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:58.1 MB,击败了56.64% 的Java用户
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookingMy(int[][] bookings, int n) {
        int[] temp = new int[n];
        for (int[] booking : bookings){
            temp[booking[0]-1]+=booking[2];
            if (booking[1] <n){
                temp[booking[1]]-=booking[2];
            }
        }
        int[] ans =  new int[n];
        ans[0] = temp[0];
        for (int i = 1; i < n; i++){
            ans[i] = ans[i-1] + temp[i];
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:58.6 MB,击败了12.40% 的Java用户
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        for (int[] booking : bookings) {
            nums[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                nums[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
