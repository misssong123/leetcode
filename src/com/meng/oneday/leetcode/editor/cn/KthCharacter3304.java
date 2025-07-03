package com.meng.oneday.leetcode.editor.cn;

class KthCharacter3304 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了18.18% 的Java用户
     * 	内存消耗:41.1 MB,击败了51.82% 的Java用户
     * @param k
     * @return
     */
    public char kthCharacter3304(int k) {
        if (k == 1){
            return 'a';
        }
        StringBuilder sb = new StringBuilder("a");
        while (true){
            int len = sb.length();
            for(int i = 0 ; i < len ; i++){
                if (sb.charAt(i) == 'z'){
                    sb.append('a');
                }else {
                    sb.append((char)(sb.charAt(i) + 1));
                }
            }
            if (sb.length() >= k){
                return sb.charAt(k - 1);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.4 MB,击败了100.00% 的Java用户
     * @param k
     * @return
     */
    public char kthCharacterOther(int k) {
        return (char) ('a' + Integer.bitCount(k - 1));
    }

    /**
     * 执行用时分布
     * 1
     * ms
     * 击败
     * 74.55%
     * 复杂度分析
     * 消耗内存分布
     * 40.71
     * MB
     * 击败
     * 73.64%
     * @param k
     * @return
     */
    public char kthCharacter(int k) {
        int cnt = 0;
        while (k > 0){
            int num = Integer.toBinaryString(k).length() - 1;
            if (k == (1 << num)){
                cnt += num;
            }else {
                cnt ++;
            }
            k -= (1 << num);
        }
        return (char)('a' + cnt);
    }

    public static void main(String[] args) {
        System.out.println(Integer.bitCount(4));
        System.out.println(Integer.toBinaryString(11));
        int num = 11;
        System.out.println(num & ~(1 << Integer.toBinaryString(num).length() - 1));
    }
}
