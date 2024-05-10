package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class CountTestedDevices2960 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了96.48% 的Java用户
     * @param batteryPercentages
     * @return
     */
    public int countTestedDevicesMy(int[] batteryPercentages) {
        int num = 0;
        for(int battery : batteryPercentages){
            if (battery>num){
                num++;
            }
        }
        return num;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了37.69% 的Java用户
     * 	内存消耗:42.2 MB,击败了50.75% 的Java用户
     * @param batteryPercentages
     * @return
     */
    public int countTestedDevices(int[] batteryPercentages) {
        int n = batteryPercentages.length;
        int need = 0;
        for (int i = 0; i < n; i++) {
            if (batteryPercentages[i] > 0) {
                need++;
                for (int j = i + 1; j < n; j++) {
                    batteryPercentages[j] = Math.max(batteryPercentages[j] - 1, 0);
                }
            }
        }
        return need;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
