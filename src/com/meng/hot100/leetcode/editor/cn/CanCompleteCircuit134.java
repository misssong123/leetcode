package com.meng.hot100.leetcode.editor.cn;

class CanCompleteCircuit134 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了85.72% 的Java用户
     * 	内存消耗:55.3 MB,击败了36.85% 的Java用户
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit134(int[] gas, int[] cost) {
        int len = gas.length,start = -1;
        int pre =0 ,sum = 0;
        for (int i = 0; i < len; i++) {
            int diff = gas[i] - cost[i];
            sum += diff;
            pre += diff;
            if(diff >= pre){
                pre = diff;
                start = i;
            }
        }
        return sum>=0 ?start:-1;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:55.4 MB,击败了15.73% 的Java用户
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitOther(int[] gas, int[] cost) {
        int ans = 0;
        int minS = 0; // 最小油量
        int s = 0; // 油量
        for (int i = 0; i < gas.length; i++) {
            s += gas[i] - cost[i]; // 在 i 处加油，然后从 i 到 i+1
            if (s < minS) {
                minS = s; // 更新最小油量
                ans = i + 1; // 注意 s 减去 cost[i] 之后，汽车在 i+1 而不是 i
            }
        }
        // 循环结束后，s 即为 gas 之和减去 cost 之和
        return s < 0 ? -1 : ans;
    }
}
