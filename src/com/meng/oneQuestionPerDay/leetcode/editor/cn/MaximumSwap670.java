package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class MaximumSwap670 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.6 MB,击败了5.39% 的Java用户
     * @param num
     * @return
     */
    public int maximumSwapMy(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int[] nums = new int[10];
        for(int i = 0 ; i < chars.length ; i++){
            nums[chars[i]-'0']++;
        }
        int index = 9;
        for(int i = 0 ; i < chars.length ; i++){
            while (index >= 0 && nums[index] == 0){
                index--;
            }
            if ((chars[i] - '0')==index){
                nums[index]--;
            }else {
                for (int j = chars.length - 1 ; j > i ; j--){
                    if (chars[j] - '0' == index){
                        char temp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = temp;
                        return new Integer(new String(chars));
                    }
                }
            }
        }
        return num;
    }

    /**
     *解答成功:
     * 	执行耗时:1 ms,击败了43.93% 的Java用户
     * 	内存消耗:39.6 MB,击败了5.20% 的Java用户
     * @param num
     * @return
     */
    public int maximumSwap1(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxNum = num;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(charArray, i, j);
                maxNum = Math.max(maxNum, Integer.parseInt(new String(charArray)));
                swap(charArray, i, j);
            }
        }
        return maxNum;
    }

    public void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了13.87% 的Java用户
     * @param num
     * @return
     */
    public int maximumSwap2(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxIdx = n - 1;
        int idx1 = -1, idx2 = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (charArray[i] > charArray[maxIdx]) {
                maxIdx = i;
            } else if (charArray[i] < charArray[maxIdx]) {
                idx1 = i;
                idx2 = maxIdx;
            }
        }
        if (idx1 >= 0) {
            swap(charArray, idx1, idx2);
            return Integer.parseInt(new String(charArray));
        } else {
            return num;
        }
    }

}
