package com.meng.oneday.leetcode.editor.cn;

class Search33 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.2 MB,击败了37.59% 的Java用户
     * @param nums 旋转排序数组
     * @param target 目标值
     * @return 目标值在数组中的索引，若不存在则返回-1
     */
    public int search33(int[] nums, int target) {
        int l = 0 , r = nums.length - 1;  // 初始化左右边界
        while (l <= r){  // 当左边界不大于右边界时继续循环
            int mid = (l + r) >> 1;  // 计算中间位置，使用位运算代替除法提高效率
            if (nums[mid] == target){  // 如果中间元素等于目标值，直接返回索引
                return mid;
            }else if (nums[mid] >= nums[l]){  // 如果中间元素大于等于左边界元素，说明左半部分是有序的
                if(nums[l] > target || nums[mid] < target){  // 如果目标值不在左半部分的有序范围内
                    l = mid + 1 ;  // 调整左边界，在右半部分继续查找
                }else {  // 如果目标值在左半部分的有序范围内
                    r = mid - 1;  // 调整右边界，在左半部分继续查找
                }
            }else{  // 如果中间元素小于左边界元素，说明右半部分是有序的
                if (nums[mid] > target || nums[r] < target){  // 如果目标值不在右半部分的有序范围内
                    r = mid - 1;  // 调整右边界，在左半部分继续查找
                }else{  // 如果目标值在右半部分的有序范围内
                    l = mid + 1;  // 调整左边界，在右半部分继续查找
                }
            }
        }
        return -1;  // 未找到目标值，返回-1
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了77.96% 的Java用户
     * @param nums 旋转排序数组
     * @param target 目标值
     * @return 目标值在数组中的索引，若不存在则返回-1
     */
    public int search(int[] nums, int target) {
        int last = nums[nums.length - 1];  // 获取数组最后一个元素作为基准
        int left = -1;  // 初始化左边界为-1，使用开区间
        int right = nums.length - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = (left + right) >>> 1;
            int x = nums[mid];
            if (target > last && x <= last) { // target 在第一段，x 在第二段
                right = mid; // 下轮循环去左边找
            } else if (x > last && target <= last) { // x 在第一段，target 在第二段
                left = mid; // 下轮循环去右边找
            } else if (x >= target) { // 否则，x 和 target 在同一段，这就和方法一的 lowerBound 一样了
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }

}
