package com.meng.oneday.leetcode.editor.cn;

class LicenseKeyFormatting482 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了80.97% 的Java用户
     * 	内存消耗:43.8 MB,击败了61.94% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String licenseKeyFormatting482(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = s.length()-1;i>=0;i--){
            if(s.charAt(i) != '-'){
                count++;
                sb.append(Character.toUpperCase(s.charAt(i)));
                if (count == k){
                    if (i > 0){
                        sb.append('-');
                    }
                    count = 0;
                }
            }
        }
        if(sb.length() > 0 && sb.charAt(sb.length()-1) == '-'){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.reverse().toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了49.80% 的Java用户
     * 	内存消耗:44 MB,击败了21.05% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1, cnt = 0; i >= 0; i--) {
            if (s.charAt(i) == '-')
                continue;
            if (cnt == k && (cnt = 0) >= 0)
                sb.append("-");
            sb.append(s.charAt(i));
            cnt++;
        }
        return sb.reverse().toString().toUpperCase();
    }
}
