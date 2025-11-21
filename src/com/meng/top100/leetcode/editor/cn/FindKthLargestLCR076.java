package com.meng.top100.leetcode.editor.cn;

class FindKthLargestLCR076 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.44% 的Java用户
     * 	内存消耗:45.8 MB,击败了5.23% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestLCR076(int[] nums, int k) {
        int len = nums.length;
        //构建大顶堆
        for(int i = (len - 1) / 2 ; i>=0 ; i--){
            adjustHeap(nums,i,len);
        }
        for(int i = len -1 ; i >=0 ; i--){
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            if (i == len - k){
                return nums[i];
            }
            adjustHeap(nums,0,i);
        }
        return -1;
    }

    private void adjustHeap(int[] nums, int parent, int end) {
        int temp = nums[parent];
        for(int child = parent * 2 + 1 ; child < end ; child = child * 2 + 1){
            if(child + 1 < end && nums[child] < nums[child + 1]){
                child++;
            }
            if (temp < nums[child]){
                nums[parent] = nums[child];
                parent = child;
            }else{
                break;
            }
        }
        nums[parent] = temp;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了82.50% 的Java用户
     * 	内存消耗:45.8 MB,击败了5.23% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
