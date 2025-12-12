package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CountMentions3433 {
    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了93.94% 的Java用户
     * 	内存消耗:46.8 MB,击败了12.12% 的Java用户
     * @param numberOfUsers
     * @param events
     * @return
     */
    public int[] countMentions3433(int numberOfUsers, List<List<String>> events) {
        int[] res = new int[numberOfUsers];
        Map<Integer,List<int[]>> offline = new HashMap<>();
        int num = 0;
        for (List<String> event : events){
            if(event.get(0).equals("OFFLINE")){
                int time = Integer.parseInt(event.get(1));
                int id = Integer.parseInt(event.get(2));
                offline.putIfAbsent(id,new ArrayList<>());
                offline.get(id).add(new int[]{time,time + 59});
            }
        }
        for (List<String> event : events){
            if(event.get(0).equals("MESSAGE")){
                int time = Integer.parseInt(event.get(1));
                if (event.get(2).equals("ALL")){
                    num++;
                }else if (event.get(2).equals("HERE")){
                    num++;
                    for (Map.Entry<Integer, List<int[]>> entry : offline.entrySet()) {
                        for (int[] ints : entry.getValue()) {
                            if (time >= ints[0] && time <= ints[1]) {
                                res[entry.getKey()]--;
                            }
                        }
                    }
                }else {
                    String[] ids = event.get(2).split(" ");
                    for (String idStr : ids){
                        int id = Integer.parseInt(idStr.substring(2));
                        res[id]++;
                    }

                }
            }
        }
        for (int i = 0 ; i < numberOfUsers ; i++){
            res[i] += num;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了81.82% 的Java用户
     * 	内存消耗:46.6 MB,击败了24.24% 的Java用户
     * @param numberOfUsers
     * @param events
     * @return
     */
    public int[] countMentionsOther(int numberOfUsers, List<List<String>> events) {
        // 按照时间戳从小到大排序，时间戳相同的，离线事件排在前面
        events.sort((a, b) -> {
            int ta = Integer.parseInt(a.get(1));
            int tb = Integer.parseInt(b.get(1));
            return ta != tb ? ta - tb : b.get(0).charAt(0) - a.get(0).charAt(0);
        });

        int[] ans = new int[numberOfUsers];
        int[] onlineT = new int[numberOfUsers];
        for (List<String> e : events) {
            int curT = Integer.parseInt(e.get(1)); // 当前时间
            String mention = e.get(2);
            if (e.get(0).charAt(0) == 'O') { // 离线
                onlineT[Integer.parseInt(mention)] = curT + 60; // 下次在线时间
            } else if (mention.charAt(0) == 'A') { // @所有人
                for (int i = 0; i < numberOfUsers; i++) {
                    ans[i]++;
                }
            } else if (mention.charAt(0) == 'H') { // @所有在线用户
                for (int i = 0; i < numberOfUsers; i++) {
                    if (onlineT[i] <= curT) { // 在线
                        ans[i]++;
                    }
                }
            } else { // @id
                for (String s : mention.split(" ")) {
                    int i = Integer.parseInt(s.substring(2));
                    ans[i]++;
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.6 MB,击败了24.24% 的Java用户
     * @param numberOfUsers
     * @param events
     * @return
     */
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] ans = new int[numberOfUsers];
        List<int[]> es = new ArrayList<>(); // (timestamp, type, id)
        int all = 0;
        for (List<String> e : events) {
            int curT = Integer.parseInt(e.get(1)); // 当前时间
            String mention = e.get(2);
            if (e.get(0).charAt(0) == 'O') { // 离线
                int i = Integer.parseInt(mention);
                es.add(new int[]{curT, 1, i});
                es.add(new int[]{curT + 60, -1, i});
            } else if (mention.charAt(0) == 'A') { // @所有人
                all++;
            } else if (mention.charAt(0) == 'H') { // @所有在线用户
                all++;
                es.add(new int[]{curT, 2, -1});
            } else { // @id
                for (String s : mention.split(" ")) {
                    int i = Integer.parseInt(s.substring(2));
                    ans[i]++;
                }
            }
        }

        es.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        int here = 0;
        for (int[] e : es) {
            int type = e[1];
            if (type == 2) {
                here++;
            } else {
                // 注意 HERE 排在后面，还没有计入发生在此刻的 HERE 消息
                ans[e[2]] += type * here; // type=1 是加，-1 是减
            }
        }

        for (int i = 0; i < numberOfUsers; i++) {
            ans[i] += all;
        }
        return ans;
    }

}
