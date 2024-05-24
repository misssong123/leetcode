package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class MostCompetitive1673 {
    /**
     * 超时
     * @param nums
     * @param k
     * @return
     */
    public int[] mostCompetitiveMy1(int[] nums, int k) {
        List<Integer> cache = new ArrayList<>(k+1);
        int len =  nums.length;
        for(int i = len -k ; i < len ; i++ ){
            cache.add(nums[i]);
        }
        boolean flag = false;
        for(int i = len -k -1 ; i >= 0 ; i--){
            if (flag&&cache.get(cache.size()-1)==0){
                break;
            }
            if(nums[i] <= cache.get(0)){
                cache.add(0,nums[i]);
                if (flag){
                    cache.remove(cache.size()-1);
                    continue;
                }
                int index = cache.size()-1;
                for(int j = 2 ; j < cache.size() ; j++){
                    if (cache.get(j-1) > cache.get(j)){
                        index = j-1;
                        break;
                    }
                }
                //非递减数组
                if (index == cache.size()-1){
                    flag = true;
                }
                cache.remove(index);
            }
        }
        int [] res = new int[k];
        for(int i = 0 ; i< k ; i++){
            res[i] = cache.get(i);
        }
        return res;
    }

    /**
     * 超时
     * @param nums
     * @param k
     * @return
     */
    public int[] mostCompetitive2(int[] nums, int k) {
        int[] ans = new int[k];
        int index = 0;
        int n = 0;
        for (int i = 0 ; i < k ; i++){
            if (nums[index]==0){
                ans[n++] = 0;
                index++;
                continue;
            }
            //寻找最小值
            for(int j = index+1 ; j <= nums.length - k + i ; j++){
                if (nums[j] == 0){
                    index=j;
                    break;
                }else if (nums[j] < nums[index]){
                    index = j;
                }
            }
            ans[n++] = nums[index];
            index++;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:22 ms,击败了73.45% 的Java用户
     * 	内存消耗:61.2 MB,击败了35.40% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && n - i + stack.size() > k && stack.peek() > nums[i]) {
                stack.pop();
            }
            stack.push(nums[i]);
        }
        int[] res = new int[k];
        while (stack.size() > k) {
            stack.pop();
        }
        for (int i = k - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
