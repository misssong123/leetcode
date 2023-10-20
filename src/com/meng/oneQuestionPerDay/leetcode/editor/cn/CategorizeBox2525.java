package com.meng.oneQuestionPerDay.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class CategorizeBox2525 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.7 MB,击败了19.20% 的Java用户
     * @param length
     * @param width
     * @param height
     * @param mass
     * @return
     */
    public String categorizeBoxMy(int length, int width, int height, int mass) {
        int n = 10000,m=1000000000;
        long area = (long) length * width * height;
        boolean bulky = (length >=n || width >= n || height >= n ||area >= m);
        boolean heavy = mass >= 100;
        if (heavy && bulky){
            return "Both";
        }else if (heavy){
            return "Heavy";
        }else if (bulky){
            return "Bulky";
        }else {
            return "Neither";
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.7 MB,击败了23.20% 的Java用户
     * @param length
     * @param width
     * @param height
     * @param mass
     * @return
     */
    public String categorizeBox(int length, int width, int height, int mass) {
        long maxd = Math.max(length, Math.max(width, height)), vol = 1L * length * width * height;
        boolean isBulky = maxd >= 10000 || vol >= 1000000000, isHeavy = mass >= 100;
        if (isBulky && isHeavy) {
            return "Both";
        } else if (isBulky) {
            return "Bulky";
        } else if (isHeavy) {
            return "Heavy";
        } else {
            return "Neither";
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
