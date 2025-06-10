package com.meng.oneday.leetcode.editor.cn;

class AnswerString3403 {
    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了77.50% 的Java用户
     * 	内存消耗:44.4 MB,击败了75.00% 的Java用户
     * @param word
     * @param numFriends
     * @return
     */
    public String answerString3403(String word, int numFriends) {
        if (numFriends == 1){
            return word;
        }
        String res = "";
        int len = word.length() - numFriends+1,n = word.length();
        for(int i = 0 ; i < n ; i++){
            String sub = word.substring(i,Math.min(i+len,n));
            if (sub.compareTo(res) > 0){
                res = sub;
            }
        }
        return res;
    }

    /**
     * 以word = "adcbdcc", numFriends = 2为例
     * 遍历到a时，答案更新为a
     * d开头比a开头字典序大，因此遍历到d时，答案就必须更新为d
     * 遍历到c和b时，都比d小，那就接在答案后边，此时的答案为dcb
     * 遍历到第二个d时，此时无法保证以这个d开头的字符串字典序是否比当前的答案更大
     * 因此需要继续遍历查看，遍历到第二个c，仍然无法分辨
     * 遍历到第三个c，dcc比dcb字典序更大，答案必须更新为dcc
     * 但如果word = "adcbdcb"，那么答案就不会被换掉，新的这个字符串dcb会接在原答案的末尾
     * 此时的最终答案就是dcbdcb
     * @param word
     * @param numFriends
     * @return
     * 执行用时分布
     * 1
     * ms
     * 击败
     * 100.00%
     * 复杂度分析
     * 消耗内存分布
     * 42.44
     * MB
     * 击败
     * 99.56%
     */
    public String answerString(String word, int numFriends) {
        if (numFriends == 1){
            return word;
        }
        int len = word.length(),first = 0,second = 1,k=0;
        while(second < len){
            k = 0;
            //寻找首次不同的位置
            while(second + k < len && word.charAt(first+k) == word.charAt(second+k)){
                k++;
            }
            //如果second大于first
            if(second + k < len && word.charAt(first+k) < word.charAt(second+k)){
                int temp = first;
                first = second;
                second = Math.max(temp + k + 1,second + 1);
            }else {
                second = second + k + 1;
            }
        }
        return word.substring(first,Math.min(len,first+len - numFriends +1));
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了77.50% 的Java用户
     * 	内存消耗:44.5 MB,击败了45.00% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String answerStringOther1(String s, int k) {
        if (k == 1) {
            return s;
        }
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            String sub = s.substring(i, Math.min(i + n - k + 1, n));
            if (sub.compareTo(ans) > 0) {
                ans = sub;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.5 MB,击败了100.00% 的Java用户
     * @param s
     * @param numFriends
     * @return
     */
    public String answerStringOther2(String s, int numFriends) {
        if (numFriends == 1) {
            return s;
        }
        int n = s.length();
        int i = 0;
        int j = 1;
        while (j < n) {
            int k = 0;
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                int t = i;
                i = j;
                j = Math.max(j + 1, t + k + 1);
            } else {
                j += k + 1;
            }
        }
        return s.substring(i, Math.min(i + n - numFriends + 1, n));
    }
}
