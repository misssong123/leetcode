package com.meng.oneday.leetcode.editor.cn;

class ConvertDateToBinary3280 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了95.99% 的Java用户
     * 	内存消耗:41.8 MB,击败了68.98% 的Java用户
     * @param date
     * @return
     */
    public String convertDateToBinaryMy(String date) {
        String[] dateArr = date.split("-");
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < dateArr.length ; i++){
            sb.append(Integer.toBinaryString(Integer.parseInt(dateArr[i])));
            if (i != dateArr.length-1){
                sb.append("-");
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了84.12% 的Java用户
     * 	内存消耗:41.6 MB,击败了86.03% 的Java用户
     * @param date
     * @return
     */
    public String convertDateToBinary(String date) {
        String[] a = date.split("-");
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.toBinaryString(Integer.parseInt(a[i]));
        }
        return String.join("-", a);
    }

}
