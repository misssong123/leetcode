package com.meng.oneday.leetcode.editor.cn;

class CountDaysTogether2409 {
    int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.4 MB,击败了14.29% 的Java用户
     * @param arriveAlice
     * @param leaveAlice
     * @param arriveBob
     * @param leaveBob
     * @return
     */
    public int countDaysTogether2409(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        if (leaveAlice.compareTo(arriveBob) < 0 || leaveBob.compareTo(arriveAlice) < 0) {
            return 0;
        }
        String arrive =  arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob ;
        String leave = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob ;
        String[] arriveStr = arrive.split("-");
        int arriveMonth = Integer.parseInt(arriveStr[0]);
        int arriveDay = Integer.parseInt(arriveStr[1]);
        String[] leaveStr = leave.split("-");
        int leaveMonth = Integer.parseInt(leaveStr[0]);
        int leaveDay = Integer.parseInt(leaveStr[1]);
        if (arriveMonth == leaveMonth) {
            return leaveDay - arriveDay + 1;
        }
        int sum = 0;
        //起始月剩余天数
        sum += days[arriveMonth - 1] - arriveDay + 1;
        //中间月份天数
        for(int i = arriveMonth + 1; i < leaveMonth; i++) {
            sum += days[i - 1];
        }
        //结束月天数
        sum += leaveDay;
        return sum;
    }
    // 每月天数的前缀和（注：最后一个数可以省略）
    private static final int[] daysPreSum = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了42.86% 的Java用户
     * @param S
     * @return
     */
    private int calcDays(String S) {
        char[] s = S.toCharArray();
        int month = (s[0] - '0') * 10 + s[1] - '0';
        int day = (s[3] - '0') * 10 + s[4] - '0';
        return daysPreSum[month - 1] + day;
    }

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int left = calcDays(arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob); // 直接比较字符串再计算
        int right = calcDays(leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob);
        return Math.max(right - left + 1, 0); // 答案不能为负数
    }

}
