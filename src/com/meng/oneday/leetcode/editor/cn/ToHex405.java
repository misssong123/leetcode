package com.meng.oneday.leetcode.editor.cn;

class ToHex405 {
    private static final String[] HEX = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了61.16% 的Java用户
     * @param num
     * @return
     */
    public String toHex405(int num) {
        if (num == 0){
            return "0";
        }
        String str = Integer.toBinaryString(num);
        StringBuilder sb = new StringBuilder();
        int temp = 0,index = 0;
        for(int i = str.length()-1 ; i >=0 ; i--){
            char c = str.charAt(i);
            if (c == '1'){
                temp += 1<<index;
            }
            if (index == 3){
                sb.append(HEX[temp]);
                index=-1;
                temp = 0;
            }
            index++;
        }
        if (temp != 0){
            sb.append(HEX[temp]);
        }
        return sb.reverse().toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.2 MB,击败了22.32% 的Java用户
     * @param num
     * @return
     */
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 7; i >= 0; i --) {
            int val = (num >> (4 * i)) & 0xf;
            if (sb.length() > 0 || val > 0) {
                char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + val - 10);
                sb.append(digit);
            }
        }
        return sb.toString();
    }

}
