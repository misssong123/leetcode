package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class RepeatLimitedString2182 {
    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了54.76% 的Java用户
     * 	内存消耗:44.4 MB,击败了9.52% 的Java用户
     * @param s
     * @param repeatLimit
     * @return
     */
    public String repeatLimitedStringMy(String s, int repeatLimit) {
        StringBuilder sb = new StringBuilder();
        int[] chars = new int[26];
        for(char c : s.toCharArray()){
            chars[c-'a']++;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0 ; i < 26 ; i++){
            if (chars[i] > 0){
                list.push(i);
            }
        }
        int first = list.pop();
        int count = 0;
        while (true){
            //当前待填充字符数量大于0
            if (chars[first] >0){
                //未超过限制
                if (count < repeatLimit){
                    sb.append((char)(first+'a'));
                    chars[first]--;
                    count++;
                }else {
                    if (!list.isEmpty()){
                        int temp = list.pop();
                        sb.append((char)(temp+'a'));
                        chars[temp]--;
                        if (chars[temp]> 0){
                            list.push(temp);
                        }
                        count = 0;
                    }else {//跳出
                        break;
                    }
                }
            }else {//当前待填充字符等于0
                if (!list.isEmpty()){
                    first = list.pop();
                    count = 0;
                }else{
                    break;
                }
            }
        }
        return sb.toString();
    }
    static public int N = 26;

    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了54.76% 的Java用户
     * 	内存消耗:44.3 MB,击败了14.28% 的Java用户
     * @param s
     * @param repeatLimit
     * @return
     */
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] count = new int[N];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder ret = new StringBuilder();
        int m = 0;
        for (int i = N - 1, j = N - 2; i >= 0 && j >= 0;) {
            if (count[i] == 0) { // 当前字符已经填完，填入后面的字符，重置 m
                m = 0;
                i--;
            } else if (m < repeatLimit) { // 当前字符未超过限制
                count[i]--;
                ret.append((char)('a' + i));
                m++;
            } else if (j >= i || count[j] == 0) { // 当前字符已经超过限制，查找可填入的其他字符
                j--;
            } else { // 当前字符已经超过限制，填入其他字符，并且重置 m
                count[j]--;
                ret.append((char)('a' + j));
                m = 0;
            }
        }
        return ret.toString();
    }


}
//leetcode submit region end(Prohibit modification and deletion)
