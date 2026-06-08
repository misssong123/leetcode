package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class OddString2451 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了65.22% 的Java用户
     * @param words
     * @return
     */
    public String oddString2451(String[] words) {
        int n = words.length;
        int m = words[0].length();
        int[] diff = new int[m-1];
        //确定基准
        for (int i = 1 ; i < m ; i++ ){
            diff[i-1] = words[0].charAt(i) - words[0].charAt(i-1);
        }
        int index = 0;
        for(int i = 1 ; i < n ; i++){
            for (int j = 1 ; j < m ; j++){
                if (words[i].charAt(j) - words[i].charAt(j-1) != diff[j-1]){
                    if (i == 1){//首次不同
                        index = i;
                    }else{
                        return index == 0 ?words[i] : words[0];
                    }
                }
            }
            if (index != 0 && index != i){
                return words[index];
            }
        }
        return words[0];
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了73.91% 的Java用户
     * 	内存消耗:41.9 MB,击败了100.00% 的Java用户
     * @param words
     * @return
     */
    public String oddString(String[] words) {
        int[] diff0 = get(words[0]);
        int[] diff1 = get(words[1]);
        if (Arrays.equals(diff0, diff1)) {
            for (int i = 2; i < words.length; i++) {
                if (!Arrays.equals(diff0, get(words[i]))) {
                    return words[i];
                }
            }
        }
        return Arrays.equals(diff0, get(words[2])) ? words[1] : words[0];
    }

    public int[] get(String word) {
        int[] diff = new int[word.length() - 1];
        for (int i = 0; i + 1 < word.length(); i++) {
            diff[i] = word.charAt(i + 1) - word.charAt(i);
        }
        return diff;

    }

}
