package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class SmallestBeautifulString2663 {
    //无思路,写一遍正确答案加深印象印象
    public String smallestBeautifulString(String s, int k) {
        //需要保证nums[i]!=nums[i-1] 并且 nums[i]!=nums[i-2]
        //从最后一位一次改变，直达满足条件
        char max = (char)('a' + k);
        char[] chars = s.toCharArray();
        int n = s.length(),index = n -1;
        //改变坐标
        chars[index]++;
        while (index < n){
            if(chars[index] ==max){//进位
                if (index == 0){
                    return "";
                }
                chars[index] = 'a';
                //进位后需要将前一位加1
                chars[--index]++;
            }//查看是否导致回文串
            else if((index > 0 && chars[index] == chars[index-1]) ||(index > 1 && chars[index] == chars[index-2])){
                //当前位置➕1
                chars[index]++;
            }else {
                //查找后面的元素是否存在回文串
                index++;
            }
        }

        return  new String(chars);
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.6 MB,击败了42.86% 的Java用户
     * @param S
     * @param k
     * @return
     */
    public String smallestBeautifulStringOther(String S, int k) {
        k += 'a';
        char[] s = S.toCharArray();
        int n = s.length;
        int i = n - 1; // 从最后一个字母开始
        s[i]++; // 先加一
        while (i < n) {
            if (s[i] == k) { // 需要进位
                if (i == 0) { // 无法进位
                    return "";
                }
                // 进位
                s[i] = 'a';
                s[--i]++;
            } else if (i > 0 && s[i] == s[i - 1] || i > 1 && s[i] == s[i - 2]) {
                s[i]++; // 如果 s[i] 和左侧的字符形成回文串，就继续增加 s[i]
            } else {
                i++; // 反过来检查后面是否有回文串
            }
        }
        return new String(s);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
