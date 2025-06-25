package com.meng.oneday.leetcode.editor.cn;

class IsOneEditDistance161 {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了10.67% 的Java用户
     * 	内存消耗:44.3 MB,击败了10.11% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance161(String s, String t) {
        if(Math.abs(s.length()-t.length()) > 1){
            return false;
        }
        if (s.length()==t.length()&&s.equals(t)) {
            return false;
        }
        if (s.isEmpty() || t.isEmpty()) {
            return true;
        }
        if(s.length() == t.length()){
            int cnt = 0;
            for(int i = 0 ; i < s.length();i++){
                if(s.charAt(i) != t.charAt(i)){
                    cnt++;
                }
                if (cnt > 1) {
                    return false;
                }
            }
            return true;
        }else {
            String min = s.length() > t.length() ? t : s;
            String max = s.length() > t.length() ? s : t;
            for(int i = 0; i < max.length() ; i++){
                if(i == 0){
                    if(max.substring(1).equals(min)){
                        return true;
                    }
                }else {
                    if(max.substring(0,i).equals(min.substring(0,i))&&
                    max.substring(i+1).equals(min.substring(i))){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了12.36% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        // 确保 s 比 t 短。
        if (ns > nt)
            return isOneEditDistance(t, s);

        // 如果长度差异大于1，则字符串不是一个编辑距离。

        if (nt - ns > 1)
            return false;

        for (int i = 0; i < ns; i++)
            if (s.charAt(i) != t.charAt(i))
                // 如果字符串具有相同的长度
                if (ns == nt)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                    // 如果字符串具有不同的长度
                else
                    return s.substring(i).equals(t.substring(i + 1));

        // 如果在 ns 距离上没有差异，则仅当 t 有多一个字符时，字符串才有一次编辑。
        return (ns + 1 == nt);
    }

}
