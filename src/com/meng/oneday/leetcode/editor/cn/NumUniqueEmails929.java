package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class NumUniqueEmails929 {
    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了18.80% 的Java用户
     * 	内存消耗:43.6 MB,击败了63.16% 的Java用户
     * @param emails
     * @return
     */
    public int numUniqueEmails929(String[] emails) {
        Set<String> validEmails = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        for(String email : emails){
            int index = email.indexOf('@');
            for(int i = 0 ; i < index ; i++){
                if (email.charAt(i) == '.'){
                    continue;
                }else if (email.charAt(i) == '+'){
                    break;
                }else {
                    sb.append(email.charAt(i));
                }
            }
            sb.append(email.substring(index));
            validEmails.add(sb.toString());
            sb.setLength(0);
        }
        return validEmails.size();
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了62.41% 的Java用户
     * 	内存消耗:44 MB,击败了40.60% 的Java用户
     * @param emails
     * @return
     */
    public int numUniqueEmails(String[] emails) {
        Set<String> emailSet = new HashSet<String>();
        for (String email : emails) {
            int i = email.indexOf('@');
            String local = email.substring(0, i).split("\\+")[0]; // 去掉本地名第一个加号之后的部分
            local = local.replace(".", ""); // 去掉本地名中所有的句点
            emailSet.add(local + email.substring(i));
        }
        return emailSet.size();
    }

}
