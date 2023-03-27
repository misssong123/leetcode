package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.*;
import java.util.stream.Collectors;

class AlertNames1604 {
    /**
     * 执行用时：
     * 87 ms
     * , 在所有 Java 提交中击败了
     * 38.75%
     * 的用户
     * 内存消耗：
     * 60.3 MB
     * , 在所有 Java 提交中击败了
     * 73.33%
     * 的用户
     * 通过测试用例：
     * 77 / 77
     * @param keyName
     * @param keyTime
     * @return
     */
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String,List<String>> map = new HashMap<>();
        int index = 0;
        for(String name : keyName){
            List<String> list = map.getOrDefault(name, new ArrayList<>());
            list.add(keyTime[index++]);
            map.put(name,list);
        }
        List<String> res = new ArrayList<>();

        for(Map.Entry<String,List<String>> entry : map.entrySet()){
            String name = entry.getKey();
            List<String> list = entry.getValue();
            Collections.sort(list);
            int len = list.size();
            int left = 0 ,right = 2;
            while (right < len){
                if (compare(list.get(left),list.get(right))){
                    res.add(name);
                    break;
                }
                left++;
                right++;
            }
        }
        Collections.sort(res);
        return res;
    }

    private boolean compare(String start, String end) {
        String[] strTime = start.split(":");
        String[] endTime = end.split(":");
        int strHour = Integer.parseInt(strTime[0]);
        int strMin = Integer.parseInt(strTime[1]);
        int endHour = Integer.parseInt(endTime[0]);
        int endMin = Integer.parseInt(endTime[1]);
        if (strHour == endHour || (strHour + 1 == endHour && strMin >= endMin)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] keyName = {"a","a","a","a","a","b","b","b","b","b","b"};
        String[] keyTime = {"04:48","23:53","06:36","07:45","12:16","00:52","10:59","17:16","00:36","01:26","22:42"};
        AlertNames1604 demo = new AlertNames1604();

    }

    /**
     * 执行用时：
     * 43 ms
     * , 在所有 Java 提交中击败了
     * 96.67%
     * 的用户
     * 内存消耗：
     * 59.4 MB
     * , 在所有 Java 提交中击败了
     * 82.50%
     * 的用户
     * 通过测试用例：
     * 77 / 77
     * @param keyName
     * @param keyTime
     * @return
     */
    public List<String> alertNames1(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> timeMap = new HashMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            timeMap.putIfAbsent(name, new ArrayList<Integer>());
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            timeMap.get(name).add(hour * 60 + minute);
        }
        List<String> res = new ArrayList<>();
        Set<String> keySet = timeMap.keySet();
        for (String name : keySet) {
            List<Integer> list = timeMap.get(name);
            Collections.sort(list);
            int size = list.size();
            for (int i = 2; i < size; i++) {
                int time1 = list.get(i - 2), time2 = list.get(i);
                int difference = time2 - time1;
                if (difference <= 60) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}
