package com.meng.oneday.leetcode.editor.cn;

class CapitalizeTitle2129 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了57.69% 的Java用户
     * 	内存消耗:41.6 MB,击败了57.69% 的Java用户
     * @param title
     * @return
     */
    public String capitalizeTitle2129(String title) {
        StringBuilder sb = new StringBuilder();
        String[] arr = title.split(" ");
        for (String s : arr) {
            if (s.length() > 2) {
                sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase()).append(" ");
            } else {
                sb.append(s.toLowerCase()).append(" ");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了57.69% 的Java用户
     * 	内存消耗:41.8 MB,击败了44.87% 的Java用户
     * @param title
     * @return
     */
    public String capitalizeTitle(String title) {
        StringBuilder ans = new StringBuilder();
        for (String s : title.split(" ")) {
            if (ans.length()>0) {
                ans.append(' ');
            }
            if (s.length() > 2) {
                ans.append(s.substring(0, 1).toUpperCase()); // 首字母大写
                s = s.substring(1);
            }
            ans.append(s.toLowerCase());
        }
        return ans.toString();
    }

}
