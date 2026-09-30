package com.meng.oneday.leetcode.editor.cn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DaysBetweenDates1360 {
    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了6.25% 的Java用户
     * 	内存消耗:46.3 MB,击败了6.25% 的Java用户
     * @param date1
     * @param date2
     * @return
     */
    public int daysBetweenDates1360(String date1, String date2) {
        if (date1.equals(date2)) {
            return 0;
        }
        if (date1.compareTo(date2) > 0) {
            return daysBetweenDates1360(date2, date1);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse1 = sdf.parse(date1);
            Date parse2 = sdf.parse(date2);
            return (int) ((parse2.getTime() - parse1.getTime()) / (1000 * 3600 * 24));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public int toDay(String dateStr) {
        String[] temp = dateStr.split("-");
        int year = Integer.valueOf(temp[0]);
        int month = Integer.valueOf(temp[1]);
        int day = Integer.valueOf(temp[2]);

        if (month <= 2) {
            year--;
            month += 10;
        }
        else month -= 2;
        return 365 * year + year / 4 - year / 100 + year / 400 + 30 * month + (3 * month - 1) / 5 + day /*- 584418*/;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了93.75% 的Java用户
     * 	内存消耗:42.3 MB,击败了64.58% 的Java用户
     * @param date1
     * @param date2
     * @return
     */
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(toDay(date1) - toDay(date2));
    }

}
