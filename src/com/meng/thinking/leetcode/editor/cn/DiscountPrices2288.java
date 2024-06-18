package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class DiscountPrices2288 {
    /**
     * 解答成功:
     * 	执行耗时:134 ms,击败了57.89% 的Java用户
     * 	内存消耗:53.4 MB,击败了28.07% 的Java用户
     * @param sentence
     * @param discount
     * @return
     */
    public String discountPricesMy(String sentence, int discount) {
        StringBuffer sb = new StringBuffer();
        for (String word : sentence.split(" ")){
            if(word.length()>=2&&word.charAt(0)=='$'){
                boolean flag = true;
                for (int i= 1; i < word.length(); i++){
                    if (word.charAt(i)<'0'||word.charAt(i)>'9'){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    long price = Long.parseLong(word.substring(1));
                    double sub =price*1.0 * (100-discount)/100;
                    sb.append(String.format("$%.2f",sub)).append(" ");
                    continue;
                }
            }
            sb.append(word).append(" ");
        }
        return  sb.toString().trim();
    }

    /**
     * 解答成功:
     * 	执行耗时:134 ms,击败了57.89% 的Java用户
     * 	内存消耗:51.6 MB,击败了50.88% 的Java用户
     * @param sentence
     * @param discount
     * @return
     */
    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.charAt(0) == '$' && isNumeric(word.substring(1))) {
                double price = Long.parseLong(word.substring(1)) * (1 - discount / 100.0);
                words[i] = String.format("$%.2f", price);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(words[i]);
        }
        return sb.toString();
    }

    public boolean isNumeric(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
