package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.Arrays;

class Compress443 {
    /**
     * 时间
     * 详情
     * 1ms
     * 击败 74.05%使用 Java 的用户
     * 内存
     * 详情
     * 40.70mb
     * 击败 71.28%使用 Java 的用户
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        char pre = chars[0];
        int num = 0;
        int index = 0;
        for (int i = 0 ; i < chars.length ; i++){
            if (chars[i] == pre){
                num ++;
            }else {
                String s = String.valueOf(num);
                chars[index++] = pre;
                if (num > 1){
                    for(int j = 0 ; j < s.length() ; j++){
                        chars[index++] = s.charAt(j);
                    }
                }
                pre = chars[i];
                num=1;
            }
        }
        String s = String.valueOf(num);
        chars[index++] = pre;
        if (num > 1){
            for(int j = 0 ; j < s.length() ; j++){
                chars[index++] = s.charAt(j);
            }
        }
        return index;
    }

    public static void main(String[] args) {
        char[] chars1 = {'a','a','b','b','c','c','c'};
        char[] chars2 = {'a','a','b','b','c','c','c'};
        Compress443 demo = new Compress443();
        System.out.println(demo.compress(chars1));
        System.out.println(Arrays.toString(chars1));
        System.out.println(demo.compress1(chars2));
        System.out.println(Arrays.toString(chars2));
    }

    /**
     * 方法一：双指针
     *
     * 思路和算法
     *
     * 为了实现原地压缩，我们可以使用双指针分别标志我们在字符串中读和写的位置。每次当读指针 read\textit{read}read 移动到某一段连续相同子串的最右侧，我们就在写指针 write\textit{write}write 处依次写入该子串对应的字符和子串长度即可。
     *
     * 在实际代码中，当读指针 read\textit{read}read 位于字符串的末尾，或读指针 read\textit{read}read 指向的字符不同于下一个字符时，我们就认为读指针 read\textit{read}read 位于某一段连续相同子串的最右侧。该子串对应的字符即为读指针 read\textit{read}read 指向的字符串。我们使用变量 left\textit{left}left 记录该子串的最左侧的位置，这样子串长度即为 read−left+1\textit{read} - \textit{left} + 1read−left+1。
     *
     * 特别地，为了达到 O(1)O(1)O(1) 空间复杂度，我们需要自行实现将数字转化为字符串写入到原字符串的功能。这里我们采用短除法将子串长度倒序写入原字符串中，然后再将其反转即可。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/string-compression/solutions/948556/ya-suo-zi-fu-chuan-by-leetcode-solution-kbuc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param chars
     * @return
     * 时间
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 40.72mb
     * 击败 68.93%使用 Java 的用户
     */
    public int compress1(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                int num = read - left + 1;
                if (num > 1) {
                    int anchor = write;
                    while (num > 0) {
                        chars[write++] = (char) (num % 10 + '0');
                        num /= 10;
                    }
                    reverse(chars, anchor, write - 1);
                }
                left = read + 1;
            }
        }
        return write;
    }

    public void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

}

