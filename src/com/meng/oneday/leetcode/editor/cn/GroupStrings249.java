package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class GroupStrings249 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了37.59% 的Java用户
     * 	内存消耗:42.2 MB,击败了96.99% 的Java用户
     * @param strings
     * @return
     */
    public List<List<String>> groupStrings249(String[] strings) {
        Map<String,Integer> map = new HashMap<>();
        List<List<String>> list = new ArrayList<>();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for(String str : strings){
            for(int i = 1 ; i < str.length() ; i++){
                int num = (str.charAt(i) - str.charAt(i - 1)+26)%26;
                sb.append(num);
                sb.append(",");
            }
            int cur = map.getOrDefault(sb.toString(),-1);
            if (cur == -1){
                map.put(sb.toString(),index++);
                list.add(new ArrayList<>());
                list.get(index - 1).add(str);
            }else {
                list.get(cur).add(str);
            }
            sb.delete(0,sb.length());
        }
        return list;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了87.22% 的Java用户
     * 	内存消耗:42.4 MB,击败了65.41% 的Java用户
     * @param strings
     * @return
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String string : strings) {
            int[] group = getGroup(string);
            String groupStr = Arrays.toString(group);
            List<String> groupList = groupMap.getOrDefault(groupStr, new ArrayList<String>());
            groupList.add(string);
            groupMap.put(groupStr, groupList);
        }
        return new ArrayList<>(groupMap.values());
    }

    public int[] getGroup(String string) {
        int length = string.length() - 1;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            int difference = string.charAt(i + 1) - string.charAt(i);
            if (difference < 0) {
                difference += 26;
            }
            array[i] = difference;
        }
        return array;
    }

}
