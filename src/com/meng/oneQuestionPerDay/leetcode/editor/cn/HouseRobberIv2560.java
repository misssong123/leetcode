
import java.util.Arrays;
import java.util.TreeSet;

class Solution {
    int res = Integer.MAX_VALUE;
    public int minCapability1(int[] nums, int k) {
        dfs(-2,0,0,nums,k);
        return res;
    }

    private void dfs(int pre,int index, int max, int[] nums, int k) {
        //k==0表示结束
        if (k == 0){
            res = Math.min(res,max);
            return;
        }
        //后续数字不支撑到结束，直接返回
        if (index >=nums.length ||k<=0 || nums.length - index < (k-1)*2){
            return;
        }
        //当前位置可选
        if (pre + 1 != index){
            dfs(index,index+1,Math.max(nums[index],max),nums,k-1);
        }
        //不选择当前位置
        dfs(pre,index+1,max,nums,k);
    }

    public static void main(String[] args) {
        Solution demo = new Solution();
        int[] nums = {2,7,9,3,1};
        int k = 2;
        System.out.println(demo.minCapability(nums,k));
    }

    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了35.44% 的Java用户
     * 	内存消耗:61.7 MB,击败了54.67% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int minCapability2(int[] nums, int k) {
        int lower = Arrays.stream(nums).min().getAsInt();
        int upper = Arrays.stream(nums).max().getAsInt();
        while (lower <= upper) {
            int middle = (lower + upper) / 2;
            int count = 0;
            boolean visited = false;
            for (int x : nums) {
                if (x <= middle && !visited) {
                    count++;
                    visited = true;
                } else {
                    visited = false;
                }
            }
            if (count >= k) {
                upper = middle - 1;
            } else {
                lower = middle + 1;
            }
        }
        return lower;
    }

    /**
     * 解答成功:
     * 	执行耗时:93 ms,击败了5.22% 的Java用户
     * 	内存消耗:54.7 MB,击败了77.47% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int minCapability(int[] nums, int k) {
        TreeSet<Integer> cache = new TreeSet<>();
        int lower = nums[0],upper = nums[0];
        for(int x : nums){
            lower = Math.min(x,lower);
            upper = Math.max(x,upper);
            cache.add(x);
        }
        while (lower <= upper) {
            int middle = (lower + upper) / 2;
            int count = 0;
            boolean visited = false;
            for (int x : nums) {
                if (x <= middle && !visited) {
                    count++;
                    visited = true;
                } else {
                    visited = false;
                }
            }
            if (count >= k) {
                Integer next = cache.lower(middle);
                upper = (next == null)?middle-1:next;
            } else {
                Integer next = cache.higher(middle);
                lower = (next == null)?middle+1:next;
            }

        }
        return lower;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
