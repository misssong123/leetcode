package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class FindMinDifference539 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了82.61% 的Java用户
     * 	内存消耗:44.2 MB,击败了89.85% 的Java用户
     * @param timePoints
     * @return
     */
    public int findMinDifference539(List<String> timePoints) {
        Set<String> cache = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for(String time: timePoints){
            if(cache.contains(time)){
                return 0;
            }
            cache.add(time);
            int hour = Integer.parseInt(time.substring(0,2));
            int minute = Integer.parseInt(time.substring(3,5));
            list.add(hour*60+minute);
        }
        Collections.sort(list);
        int min = Integer.MAX_VALUE;
        for(int i = 1 ; i < list.size();i++){
            min = Math.min(min,list.get(i)-list.get(i-1));
        }
        min = Math.min(min,list.get(0)+24*60-list.get(list.size()-1));
        return min;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了44.44% 的Java用户
     * 	内存消耗:45.5 MB,击败了22.22% 的Java用户
     * @param timePoints
     * @return
     */
    public int findMinDifferenceOther(List<String> timePoints) {
        int n = timePoints.size() * 2;
        int[] nums = new int[n];
        for (int i = 0, idx = 0; i < n / 2; i++, idx += 2) {
            String[] ss = timePoints.get(i).split(":");
            int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
            nums[idx] = h * 60 + m;
            nums[idx + 1] = nums[idx] + 1440;
        }
        Arrays.sort(nums);
        int ans = nums[1] - nums[0];
        for (int i = 0; i < n - 1; i++) ans = Math.min(ans, nums[i + 1] - nums[i]);
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了73.43% 的Java用户
     * 	内存消耗:43.5 MB,击败了96.14% 的Java用户
     * @param timePoints
     * @return
     */
    public int findMinDifferenceOther2(List<String> timePoints) {
        int n = timePoints.size();
        if (n > 1440) return 0;
        int[] cnts = new int[1440 * 2 + 10];
        for (String s : timePoints) {
            String[] ss = s.split(":");
            int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
            cnts[h * 60 + m]++;
            cnts[h * 60 + m + 1440]++;
        }
        int ans = 1440, last = -1;
        for (int i = 0; i <= 1440 * 2 && ans != 0; i++) {
            if (cnts[i] == 0) continue;
            if (cnts[i] > 1) ans = 0;
            else if (last != -1) ans = Math.min(ans, i - last);
            last = i;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了94.69% 的Java用户
     * 	内存消耗:44.6 MB,击败了56.52% 的Java用户
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        if (n > 1440) {
            return 0;
        }
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int t0Minutes = getMinutes(timePoints.get(0));
        int preMinutes = t0Minutes;
        for (int i = 1; i < n; ++i) {
            int minutes = getMinutes(timePoints.get(i));
            ans = Math.min(ans, minutes - preMinutes); // 相邻时间的时间差
            preMinutes = minutes;
        }
        ans = Math.min(ans, t0Minutes + 1440 - preMinutes); // 首尾时间的时间差
        return ans;
    }

    public int getMinutes(String t) {
        return ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) * 60 + (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');
    }

}
