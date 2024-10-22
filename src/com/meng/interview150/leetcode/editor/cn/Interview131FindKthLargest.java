package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;

class Interview131FindKthLargest {
    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了71.19% 的Java用户
     * 	内存消耗:55.9 MB,击败了77.19% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    /**
     * 解答成功:
     * 	执行耗时:2626 ms,击败了5.00% 的Java用户
     * 	内存消耗:57.3 MB,击败了25.83% 的Java用户
     * @param arr
     * @param k
     * @return
     */
    public int findKthLargest2(int[] arr, int k) {
        int n = arr.length;
        return quickselect(arr, 0, n - 1, n - k);
    }
    public int quickselect(int[] arr, int left, int right, int k) {
        //如果左/右指针相同，直接返回该位置的元素
        if (left == right){
            return arr[left];
        }
        //选择最右边的元素作为基准值
        int pivot = arr[right],r = right,l = left;
        while (left < right){
            //从左侧选择大于基准值的元素
            while(left < right && arr[left] < pivot){
                left++;
            }
            //从右侧选择小于基准值的元素
            while (right > left && arr[right] >= pivot){
                right--;
            }
            if (left != right){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        //保证基准值在中间
        if (left != r){
            int temp = arr[left];
            arr[left] = arr[r];
            arr[r] = temp;
        }
        if(left == k){
            return arr[left];
        }else if(left > k){
            return quickselect(arr,l,left-1,k);
        }else {
            return quickselect(arr,left+1,r,k);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了94.40% 的Java用户
     * 	内存消耗:55.6 MB,击败了88.88% 的Java用户
     * @param _nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect1(_nums, 0, n - 1, n - k);
    }
    int quickselect1(int[] nums, int l, int r, int k) {
        if (l == r) return nums[l];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) return quickselect(nums, l, j, k);
        else return quickselect(nums, j + 1, r, k);
    }
}
