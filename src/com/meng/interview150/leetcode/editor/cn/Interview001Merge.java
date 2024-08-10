package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview001Merge {
    /**
     * 依次倒序对比nums1和nums2中的元素，将较大的数据放到nums1的末尾
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了73.54% 的Java用户
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n -1;
        while (m>0||n>0){
            int mVal = m -1 >=0?nums1[m-1]:Integer.MIN_VALUE;
            int nVal = n -1 >=0?nums2[n-1]:Integer.MIN_VALUE;
            nums1[index--] = Math.max(mVal,nVal);
            if (mVal >= nVal){
                m--;
            }else {
                n--;
            }
        }
    }
}
