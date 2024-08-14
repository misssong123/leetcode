package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview014CanCompleteCircuit {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了46.63% 的Java用户
     * 	内存消耗:53.8 MB,击败了94.65% 的Java用户
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitMy(int[] gas, int[] cost) {
        int len =  gas.length;
        int[] diff = new int[len];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            diff[i] = gas[i] - cost[i];
            sum = sum + diff[i];
        }
        if (sum < 0){
            return -1;
        }
        for(int i = 0 ; i < len ; i++){
            if (diff[i] >= 0){
                //前一个大于等于0未满足条件，则从当前开始也不会满足条件
                if(i>0&&diff[i-1] >= 0){
                    continue;
                }
                int temp = 0;
                for (int j = i; j < len + i; j++) {
                    temp += diff[j % len];
                    if (temp < 0){
                        break;
                    }
                    if (j == len + i - 1){
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了46.63% 的Java用户
     * 	内存消耗:56.6 MB,击败了11.15% 的Java用户
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }


}
