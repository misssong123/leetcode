package com.meng.oneday.leetcode.editor.cn;

class TrainingPlanLCR139 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了80.06% 的Java用户
     * 	内存消耗:55.9 MB,击败了17.09% 的Java用户
     * @param actions
     * @return
     */
    public int[] trainingPlanLCR139(int[] actions) {
        int l = 0 , r = actions.length - 1;
        int[] res = new int[actions.length];
        for (int action : actions) {
            if (action % 2 == 0){
                res[r--] = action;
            }else {
                res[l++] = action;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:55.7 MB,击败了60.44% 的Java用户
     * @param actions
     * @return
     */
    public int[] trainingPlan(int[] actions) {
        int i = 0, j = actions.length - 1, tmp;
        while(i < j) {
            while(i < j && (actions[i] & 1) == 1) i++;
            while(i < j && (actions[j] & 1) == 0) j--;
            tmp = actions[i];
            actions[i] = actions[j];
            actions[j] = tmp;
        }
        return actions;
    }

}
