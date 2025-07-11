package com.meng.oneday.leetcode.editor.cn;

class CrackNumberLCR165 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.6 MB,击败了54.97% 的Java用户
     * @param ciphertext
     * @return
     */
    public int crackNumberLCR165(int ciphertext) {
        int n = String.valueOf(ciphertext).length();
        int[] count = new int[n+2];
        count[n] = 1;
        int after = 0 ;
        for(int i = n -1 ; i >=0 ; i--){
            int num = ciphertext%10;
            count[i] = count[i+1];
            if(num != 0 &&num * 10 + after <=25){
                count[i] += count[i+2];
            }
            ciphertext /= 10;
            after = num;
        }
        return count[0];
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了96.69% 的Java用户
     * @param ciphertext
     * @return
     */
    public int crackNumber(int ciphertext) {
        String src = String.valueOf(ciphertext);
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < src.length(); ++i) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }

}
