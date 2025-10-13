package com.meng.oneday.leetcode.editor.cn;

class ReverseOnlyLetters917 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了60.00% 的Java用户
     * @param s
     * @return
     */
    public String reverseOnlyLetters917(String s) {
        char[] chars = s.toCharArray();
        int l = 0 , r = chars.length - 1;
        while (l < r){
            while (l < r && !Character.isLetter(chars[l])){
                l++;
            }
            while (r > l && !Character.isLetter(chars[r])){
                r--;
            }
            if (l < r){
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
                l++;
                r--;
            }
        }
        return new String(chars);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了89.44% 的Java用户
     * @param s
     * @return
     */
    public String reverseOnlyLetters(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int left = 0, right = n - 1;
        while (true) {
            while (left < right && !Character.isLetter(s.charAt(left))) { // 判断左边是否扫描到字母
                left++;
            }
            while (right > left && !Character.isLetter(s.charAt(right))) { // 判断右边是否扫描到字母
                right--;
            }
            if (left >= right) {
                break;
            }
            swap(arr, left, right);
            left++;
            right--;
        }
        return new String(arr);
    }

    public void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
