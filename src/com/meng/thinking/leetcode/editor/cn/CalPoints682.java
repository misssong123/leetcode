package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class CalPoints682 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了19.73% 的Java用户
     * 	内存消耗:40.7 MB,击败了55.33% 的Java用户
     * @param operations
     * @return
     */
    public int calPointsMy(String[] operations) {
        List<Integer> cache = new ArrayList<>();
        for(String  operation : operations){
            switch (operation) {
                case "+":
                    cache.add(cache.get(cache.size() - 1) + cache.get(cache.size() - 2));
                    break;
                case "D":
                    cache.add(cache.get(cache.size() - 1) * 2);
                    break;
                case "C":
                    cache.remove(cache.size() - 1);
                    break;
                default:
                    cache.add(Integer.valueOf(operation));
                    break;
            }
        }
        return cache.stream()
                .mapToInt(Integer::intValue).sum();
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了79.20% 的Java用户
     * 	内存消耗:40.7 MB,击败了63.33% 的Java用户
     * @param operations
     * @return
     */
    public int calPoints(String[] operations) {
        List<Integer> st = new ArrayList<>();
        for (String op : operations) {
            switch (op.charAt(0)) {
                case '+':
                    st.add(st.get(st.size() - 2) + st.get(st.size() - 1));
                    break;
                case 'D':
                    st.add(st.get(st.size() - 1) * 2);
                    break;
                case 'C':
                    st.remove(st.size() - 1);
                    break;
                default:
                    st.add(Integer.parseInt(op));
            }
        }
        int sum = 0;
        for (int x : st) {
            sum += x;
        }
        return sum;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
