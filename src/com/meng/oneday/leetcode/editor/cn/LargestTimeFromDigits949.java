package com.meng.oneday.leetcode.editor.cn;

class LargestTimeFromDigits949 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了78.05% 的Java用户
     * @param arr
     * @return
     */
    public String largestTimeFromDigits949(int[] arr) {
        int[] nums = new int[10];
        for (int num : arr) {
            nums[num]++;
        }
        int[] res = new int[4];
        return dfs(nums,res,0);
    }

    private String dfs(int[] nums,int[] res, int index) {
        if(index == 4){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                sb.append(res[j]);
                if (j == 1){
                    sb.append(":");
                }
            }
            return sb.toString();
        }
        if (index == 0){
            for(int i = 2 ; i >=0 ; i--){
                if (nums[i] > 0){
                    res[index] = i;
                    nums[i]--;
                    String s = dfs(nums,res,index+1);
                    if (s.length() == 5){
                        return s;
                    }
                    nums[i]++;
                }
            }
            return "";
        }else if (index == 1){
            if (res[0] == 2){
                for (int i = 3; i >= 0; i--) {
                    if (nums[i] > 0){
                        res[index] = i;
                        nums[i]--;
                        String s = dfs(nums,res,index+1);
                        if (s.length() == 5){
                            return s;
                        }
                        nums[i]++;
                    }
                }
            }else {
                for (int i = 9; i >= 0; i--) {
                    if (nums[i] > 0){
                        res[index] = i;
                        nums[i]--;
                        String s = dfs(nums,res,index+1);
                        if (s.length() == 5){
                            return s;
                        }
                        nums[i]++;
                    }
                }
            }
            return "";
        }else if (index == 2){
            for (int i = 5; i >= 0; i--) {
                if (nums[i] > 0){
                    res[index] = i;
                    nums[i]--;
                    String s = dfs(nums,res,index+1);
                    if (s.length() == 5){
                        return s;
                    }
                    nums[i]++;
                }
            }
            return "";
        }else {
            for (int i = 9; i >= 0; i--) {
                if (nums[i] > 0){
                    res[index] = i;
                    nums[i]--;
                    String s = dfs(nums,res,index+1);
                    if (s.length() == 5){
                        return s;
                    }
                    nums[i]++;
                }
            }
            return "";
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了87.80% 的Java用户
     * 	内存消耗:42.7 MB,击败了31.71% 的Java用户
     * @param A
     * @return
     */
    public String largestTimeFromDigitsOfficial(int[] A) {
        int ans = -1;
        // Choose different indices i, j, k, l as a permutation of 0, 1, 2, 3
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 4; ++j) if (j != i)
                for (int k = 0; k < 4; ++k) if (k != i && k != j) {
                    int l = 6 - i - j - k;

                    // For each permutation of A[i], read out the time and
                    // record the largest legal time.
                    int hours = 10 * A[i] + A[j];
                    int mins = 10 * A[k] + A[l];
                    if (hours < 24 && mins < 60)
                        ans = Math.max(ans, hours * 60 + mins);
                }

        return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60) : "";
    }
}
