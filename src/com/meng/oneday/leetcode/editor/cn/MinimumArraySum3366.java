import java.util.*;

class Solution {
    /**
     * 想法有误
     * @param nums
     * @param k
     * @param op1
     * @param op2
     * @return
     */
    public int minArraySumErr(int[] nums, int k, int op1, int op2) {
        int len = nums.length;
        //除法操作
        PriorityQueue<Integer> divQueue = new PriorityQueue<>((a,b)->nums[b]-nums[a]);
        //减法操作
        PriorityQueue<Integer> subQueue = new PriorityQueue<>((a,b)->nums[b]-nums[a]);
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < len ; i++){
            divQueue.add(i);
        }
        //优先进行除法操作
        while (op1 > 0 && !divQueue.isEmpty()){
            int index = divQueue.peek();
            if ((nums[index] + 1) / 2 > k){
                nums[index] = (nums[index] + 1) / 2;
                set.add(index);
                op1--;
            }else{
                break;
            }
        }
        //剩余的除法操作全部进行
        for(int i = 0 ; i < len ; i++){
            subQueue.add(i);
        }
        //进行减法操作
        while(op2 > 0 && !subQueue.isEmpty() && nums[subQueue.peek()] >= k){
            int index = subQueue.poll();
            nums[index] -= k;
            op2--;
        }
        //二次进行除法操作
        if (op1 > 0){
            divQueue.clear();
            for(int i = 0 ; i < len ; i++){
                if(!set.contains(i)&&nums[i] != 0){
                    divQueue.add(i);
                }
            }
            while (op1 > 0 && !divQueue.isEmpty()){
                int index = divQueue.poll();
                nums[index] = (nums[index] + 1) / 2;
                op1--;
            }
        }
        int res = 0;
        for (int num : nums){
            res += num;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:51 ms,击败了39.13% 的Java用户
     * 	内存消耗:50.1 MB,击败了8.70% 的Java用户
     * @param nums
     * @param k
     * @param op1
     * @param op2
     * @return
     */
    public int minArraySum3366(int[] nums, int k, int op1, int op2) {
        int[][][] dp = new int[nums.length][op1 + 1][op2 + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= op1; j++) {
                Arrays.fill(dp[i][j],-1);
            }
        }
        return dfs(nums,k,op1,op2,0,dp);
    }

    private int dfs(int[] nums, int k, int op1, int op2, int index,int[][][] dp) {
        if (index == nums.length){
            return 0;
        }
        //如果没有操作了，直接返回当前索引后面的所有元素和
        if (op1 == 0 && op2 == 0){
            int temp  = 0;
            for(int i = index ; i < nums.length ; i++){
                temp += nums[i];
            }
            return temp;
        }
        if (dp[index][op1][op2] != -1){
            return dp[index][op1][op2];
        }
        //不使用操作
        int min = nums[index] + dfs(nums,k,op1,op2,index + 1,dp);
        //使用操作1
        if (op1 > 0){
            min = Math.min(min,(nums[index] + 1) / 2 + dfs(nums,k,op1 - 1,op2,index + 1,dp));
        }
        //使用操作2
        if (nums[index] >= k &&op2 > 0){
            min = Math.min(min,nums[index] - k + dfs(nums,k,op1,op2 - 1,index + 1,dp));
        }
        //使用操作1+操作2
        if (op1 > 0 && op2 > 0 && nums[index] >= k){
            int temp = (nums[index] + 1) / 2 >= k ? (nums[index] + 1) / 2 - k : (nums[index] - k + 1) / 2;
            min = Math.min(min,temp + dfs(nums,k,op1 - 1,op2 - 1,index + 1,dp));
        }
        return dp[index][op1][op2] = min;
    }

    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了82.61% 的Java用户
     * 	内存消耗:44.7 MB,击败了82.61% 的Java用户
     * @param nums
     * @param k
     * @param op1
     * @param op2
     * @return
     */
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int[][] f = new int[op1 + 1][op2 + 1];
        for (int x : nums) {
            for (int p = op1; p >= 0; p--) {
                for (int q = op2; q >= 0; q--) {
                    int res = f[p][q] + x;
                    if (p > 0) {
                        res = Math.min(res, f[p - 1][q] + (x + 1) / 2);
                    }
                    if (q > 0 && x >= k) {
                        res = Math.min(res, f[p][q - 1] + x - k);
                        if (p > 0) {
                            int y = (x + 1) / 2 >= k ? (x + 1) / 2 - k : (x - k + 1) / 2;
                            res = Math.min(res, f[p - 1][q - 1] + y);
                        }
                    }
                    f[p][q] = res;
                }
            }
        }
        return f[op1][op2];
    }
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.9 MB,击败了78.26% 的Java用户
     * @param nums
     * @param k
     * @param op1
     * @param op2
     * @return
     */
    public int minArraySumOther2(int[] nums, int k, int op1, int op2) {
        Arrays.sort(nums);
        int high = lowerBound(nums, k * 2 - 1);
        int low = lowerBound(nums, k);

        // 在 [2k-1,∞) 中的数，直接先除再减（从大到小操作）
        for (int i = nums.length - 1; i >= high; i--) {
            if (op1 > 0) {
                nums[i] = (nums[i] + 1) / 2;
                op1--;
            }
            if (op2 > 0) {
                nums[i] -= k;
                op2--;
            }
        }

        // 在 [k,2k-2] 中的数，先把小的数 -k
        Map<Integer, Integer> cnt = new HashMap<>();
        int odd = 0;
        for (int i = low; i < high; i++) {
            if (op2 > 0) {
                nums[i] -= k;
                if (k % 2 > 0 && nums[i] % 2 > 0) {
                    // nums[i] 原来是偶数，后面有机会把这次 -k 操作留给奇数，得到更小的答案
                    cnt.merge(nums[i], 1, Integer::sum); // cnt[nums[i]]++
                }
                op2--;
            } else {
                odd += nums[i] % 2; // 没有执行 -k 的奇数
            }
        }

        // 重新排序（注：这里可以改用合并两个有序数组的做法）
        Arrays.sort(nums, 0, high);

        int ans = 0;
        if (k % 2 > 0) {
            // 调整，对于 [k,2k-2] 中 -k 后还要再 /2 的数，如果原来是偶数，改成给奇数 -k 再 /2，这样答案可以减一
            for (int i = high - op1; i < high && odd > 0; i++) {
                int x = nums[i];
                if (cnt.containsKey(x)) {
                    if (cnt.merge(x, -1, Integer::sum) == 0) { // --cnt[x] == 0
                        cnt.remove(x);
                    }
                    odd--;
                    ans--;
                }
            }
        }

        // 最后，从大到小执行操作 1
        for (int i = high - 1; i >= 0 && op1 > 0; i--) {
            nums[i] = (nums[i] + 1) / 2;
            op1--;
        }

        for (int x : nums) {
            ans += x;
        }
        return ans;
    }

    // 见 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
