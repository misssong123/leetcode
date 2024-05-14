package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumRefill2105 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了88.72% 的Java用户
     * 	内存消耗:56.3 MB,击败了88.15% 的Java用户
     * @param plants
     * @param capacityA
     * @param capacityB
     * @return
     */
    public int minimumRefillMy(int[] plants, int capacityA, int capacityB) {
        int left = 0 , right =  plants.length - 1,ans = 0;
        int  a = capacityA,b = capacityB;
        while (left <= right){
            if (left == right){
                ans +=Math.max(a,b) >= plants[left]?0:1;
            }else {
                if (a<plants[left]){
                    a = capacityA;
                    ans++;
                }
                a -= plants[left];
                if (b<plants[right]){
                    b = capacityB;
                    ans++;
                }
                b -= plants[right];

            }
            left++;
            right--;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了88.72% 的Java用户
     * 	内存消耗:56.4 MB,击败了69.78% 的Java用户
     * @param plants
     * @param capacityA
     * @param capacityB
     * @return
     */
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int res = 0; // 灌满水罐次数
        int n = plants.length;
        int posa = 0, posb = n - 1;  // 两人位置
        int vala = capacityA, valb = capacityB; // 两人剩余水量
        // 模拟相遇前的浇水过程
        while (posa < posb) {
            if (vala < plants[posa]) {
                ++res;
                vala = capacityA - plants[posa];
            } else {
                vala -= plants[posa];
            }
            ++posa;
            if (valb < plants[posb]) {
                ++res;
                valb = capacityB - plants[posb];
            } else {
                valb -= plants[posb];
            }
            --posb;
        }
        // 模拟相遇后可能的浇水过程
        if (posa == posb) {
            if (vala >= valb && vala < plants[posa]) {
                ++res;
            }
            if (vala < valb && valb < plants[posb]) {
                ++res;
            }
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
