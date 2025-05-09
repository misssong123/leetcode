package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution3343 {
    int[] nums = null;
    boolean[] visited = null;
    int mod = 1000000007;
    Map<String,Integer> cache = new HashMap<>();
    StringBuilder sb = new StringBuilder();

    /**
     * 超时
     * @param num
     * @return
     */
    public int countBalancedPermutations3343(String num) {
        int len = num.length();
        //转换成整数数组
        nums = new int[len];
        visited = new boolean[len];
        for(int i = 0 ; i < len; i++){
            nums[i] = num.charAt(i) - '0';
        }
        //计算总和
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0){
            return 0;
        }
        //排序
        Arrays.sort(nums);
        //计算所有可能的排序
        return dfs(0,len,0,sum/2,sb);
    }

    private int dfs(int n,int len, int sum,int target,StringBuilder sb) {
        if(n == len){
            if(sum == target){
                return 1;
            }
            return 0;
        }
        int temp = 0;
        for(int i = 0 ; i < len ; i++){
            //当前元素被访问过或者当前元素和前一个元素相同且前一个元素没有被访问过
            if(visited[i] || (i > 0 && nums[i] == nums[i-1] && !visited[i-1])){
                continue;
            }
            //当前元素的值大于目标值
            int ele = n % 2 == 0 ? nums[i] :0;
            if( sum + ele > target){
                continue;
            }
            //选中当前元素
            visited[i] = true;
            sb.append(nums[i]);
            temp =(temp + dfs(n+1,len,sum + ele,target,sb))% mod;
            //回溯
            visited[i] = false;
            sb.deleteCharAt(sb.length()-1);
        }
        cache.put(sb.toString(),temp);
        return temp;
    }

    /**
     * 解答成功:
     * 	执行耗时:35 ms,击败了82.20% 的Java用户
     * 	内存消耗:44.7 MB,击败了22.03% 的Java用户
     */
    private static final int MOD = 1_000_000_007;
    private static final int MX = 41;

    private static final long[] F = new long[MX]; // f[i] = i!
    private static final long[] INV_F = new long[MX]; // inv_f[i] = i!^-1

    static {
        F[0] = 1;
        for (int i = 1; i < MX; i++) {
            F[i] = F[i - 1] * i % MOD;
        }
        INV_F[MX - 1] = pow(F[MX - 1], MOD - 2);
        for (int i = MX - 1; i > 0; i--) {
            INV_F[i - 1] = INV_F[i] * i % MOD;
        }
    }

    public int countBalancedPermutationsOther(String num) {
        int[] cnt = new int[10];
        int total = 0;
        for (char c : num.toCharArray()) {
            cnt[c - '0']++;
            total += c - '0';
        }

        if (total % 2 > 0) {
            return 0;
        }

        for (int i = 1; i < 10; i++) {
            cnt[i] += cnt[i - 1];
        }

        int n = num.length();
        int n1 = n / 2;
        int[][][] memo = new int[10][n1 + 1][total / 2 + 1];
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        return (int) (F[n1] * F[n - n1] % MOD * dfs(9, n1, total / 2, cnt, memo) % MOD);
    }

    private int dfs(int i, int left1, int leftS, int[] cnt, int[][][] memo) {
        if (i < 0) {
            return leftS == 0 ? 1 : 0;
        }
        if (memo[i][left1][leftS] != -1) {
            return memo[i][left1][leftS];
        }
        long res = 0;
        int c = cnt[i] - (i > 0 ? cnt[i - 1] : 0);
        int left2 = cnt[i] - left1;
        for (int k = Math.max(c - left2, 0); k <= Math.min(c, left1) && k * i <= leftS; k++) {
            long r = dfs(i - 1, left1 - k, leftS - k * i, cnt, memo);
            res = (res + r * INV_F[k] % MOD * INV_F[c - k]) % MOD;
        }
        return memo[i][left1][leftS] = (int) res;
    }

    private static long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }

    //------------------------------------------------------------------------------------------------------------------------
    /**
     * 解答成功:
     * 	执行耗时:80 ms,击败了27.12% 的Java用户
     * 	内存消耗:44.8 MB,击败了9.32% 的Java用户
     */
    private long[][][] memo;
    private int[] cnt;
    private int[] psum;
    private int target;
    private long[][] comb;

    public int countBalancedPermutations(String num) {
        int tot = 0, n = num.length();
        cnt = new int[10];
        for (char ch : num.toCharArray()) {
            int d = ch - '0';
            cnt[d]++;
            tot += d;
        }
        if (tot % 2 != 0) {
            return 0;
        }

        target = tot / 2;
        int maxOdd = (n + 1) / 2;

        /* 预计算组合数 */
        comb = new long[maxOdd + 1][maxOdd + 1];
        for (int i = 0; i <= maxOdd; i++) {
            comb[i][i] = comb[i][0] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
            }
        }

        psum = new int[11];
        for (int i = 9; i >= 0; i--) {
            psum[i] = psum[i + 1] + cnt[i];
        }

        memo = new long[10][target + 1][maxOdd + 1];
        for (long[][] arr2 : memo) {
            for (long[] arr1 : arr2) {
                Arrays.fill(arr1, -1);
            }
        }

        return (int)dfs(0, 0, maxOdd);
    }

    private long dfs(int pos, int curr, int oddCnt) {
        /* 如果剩余位置无法合法填充，或者当前奇数位置元素和大于目标值 */
        if (oddCnt < 0 || psum[pos] < oddCnt || curr > target) {
            return 0;
        }
        if (pos > 9) {
            return curr == target && oddCnt == 0 ? 1 : 0;
        }
        if (memo[pos][curr][oddCnt] != -1) {
            return memo[pos][curr][oddCnt];
        }
        /* 偶数位剩余需要填充的位数 */
        int evenCnt = psum[pos] - oddCnt;
        long res = 0;
        for (int i = Math.max(0, cnt[pos] - evenCnt); i <= Math.min(cnt[pos], oddCnt); i++) {
            /* 当前数字在奇数位填充 i 位，偶数位填充 cnt[pos] - i 位 */
            long ways = comb[oddCnt][i] * comb[evenCnt][cnt[pos] - i] % MOD;
            res = (res + ways * dfs(pos+1, curr + i * pos, oddCnt - i) % MOD) % MOD;
        }
        memo[pos][curr][oddCnt] = res;
        return res;
    }

}
