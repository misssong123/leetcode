package com.meng.oneday.leetcode.editor.cn;

class Maximum69Number1323 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了35.49% 的Java用户
     * 	内存消耗:39.8 MB,击败了42.70% 的Java用户
     * @param num
     * @return
     */
    public int maximum69Number1323(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        for(int i = 0 ; i < chars.length; i++){
            if(chars[i] == '6'){
                chars[i] = '9';
                break;
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了17.99% 的Java用户
     * 	内存消耗:39.8 MB,击败了35.00% 的Java用户
     * @param num
     * @return
     */
    public int maximum69NumberOther(int num) {
        String s = String.valueOf(num).replaceFirst("6", "9"); // 替换第一个 6
        return Integer.parseInt(s);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了98.81% 的Java用户
     * @param num
     * @return
     */
    public int maximum69Number(int num) {
        int maxBase = 0;
        int base = 1;
        for (int x = num; x > 0; x /= 10) {
            if (x % 10 == 6) {
                maxBase = base;
            }
            base *= 10;
        }
        return num + maxBase * 3;
    }

}
