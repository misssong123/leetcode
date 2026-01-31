package com.meng.oneday.leetcode.editor.cn;

class NextGreatestLetter744 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.6 MB,击败了38.91% 的Java用户
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter744(char[] letters, char target) {
        char val = 'z' + 1;
        char res = val;
        for(char c : letters){
            if(c > target && c < res){
                res = c;
            }
        }
        return res == val ? letters[0] : res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.6 MB,击败了32.80% 的Java用户
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetterOther(char[] letters, char target) {
        int n = letters.length;
        int left = -1;
        int right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right < n ? letters[right] : letters[0];
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.4 MB,击败了85.52% 的Java用户
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0 ,right = letters.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (letters[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left < letters.length ? letters[left] : letters[0];
    }

}
