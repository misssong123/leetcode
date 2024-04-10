package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumBinaryString1702 {
    /**
     * 解答成功:
     * 	执行耗时:108 ms,击败了5.45% 的Java用户
     * 	内存消耗:44.4 MB,击败了89.09% 的Java用户
     * @param binary
     * @return
     */
    public String maximumBinaryString(String binary) {
        int index = binary.indexOf('0');
        if (index < 0 || index == binary.length()-1) {
            return binary;
        }
        StringBuffer sb = new StringBuffer();
        int num=0;
        for (int i = 0 ; i < binary.length() ; i++){
            sb.append('1');
            if (binary.charAt(i)=='0'){
                num++;
                index = index == -1 ?i :index;
            }
        }
        if (num>=1){
            sb.setCharAt(index+num-1,'0');
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:58 ms,击败了16.36% 的Java用户
     * 	内存消耗:44.7 MB,击败了27.27% 的Java用户
     * @param binary
     * @return
     */
    public String maximumBinaryString1(String binary) {
        int n = binary.length(), i = binary.indexOf('0');
        if (i < 0) {
            return binary;
        }
        int zeros = 0;
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n; j++) {
            if (binary.charAt(j) == '0') {
                zeros++;
            }
            s.append('1');
        }
        s.setCharAt(i + zeros - 1, '0');
        return s.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:39 ms,击败了40.00% 的Java用户
     * 	内存消耗:44.6 MB,击败了40.00% 的Java用户
     * @param binary
     * @return
     */
    public String maximumBinaryString2(String binary) {
        int n = binary.length();
        char[] s = binary.toCharArray();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == '0') {
                while (j <= i || (j < n && s[j] == '1')) {
                    j++;
                }
                if (j < n) {
                    s[j] = '1';
                    s[i] = '1';
                    s[i + 1] = '0';
                }
            }
        }
        return new String(s);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
