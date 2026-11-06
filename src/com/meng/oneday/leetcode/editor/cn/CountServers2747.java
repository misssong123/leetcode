package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class CountServers2747 {
    /**
     * 超时
     *
     * @param n
     * @param logs
     * @param x
     * @param queries
     * @return
     */
    public int[] countServersTimeOut(int n, int[][] logs, int x, int[] queries) {
        //统计请求
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int[] log : logs) {
            if (!map.containsKey(log[1])) {
                map.put(log[1], new ArrayList<>());
            }
            map.get(log[1]).add(log[0]);
        }
        //计算结果
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            Collection<List<Integer>> values = map.subMap(query - x, true, query, true).values();
            if (values.isEmpty()) {
                res[i] = n;
            } else {
                Set<Integer> set = new HashSet<>();
                for (List<Integer> value : values) {
                    set.addAll(value);
                }
                res[i] = n - set.size();
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:62 ms,击败了0.00% 的Java用户
     * 	内存消耗:165.5 MB,击败了10.00% 的Java用户
     * @param n
     * @param logs
     * @param x
     * @param queries
     * @return
     */
    public int[] countServers2747(int n, int[][] logs, int x, int[] queries) {
        //统计请求
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            if (!map.containsKey(log[1])) {
                map.put(log[1], new ArrayList<>());
            }
            map.get(log[1]).add(log[0]);
        }
        //计算结果
        int[] cnts = new int[n + 1];
        //时间点排序
        ArrayList<Integer> timeList = new ArrayList<>(map.keySet());
        timeList.sort(Integer::compareTo);
        int[] res = new int[queries.length];
        Integer[] query = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            query[i] = i;
        }
        Arrays.sort(query, Comparator.comparingInt(a -> queries[a]));
        int left = 0 , right = 0,cnt = 0;
        for (int index : query) {
            int num = queries[index];
            int l = num - x;
            //添加
            while (right < timeList.size() && timeList.get(right) <= num) {
                for (int t : map.get(timeList.get(right))) {
                    cnts[t]++;
                    if (cnts[t] == 1) {
                        cnt++;
                    }
                }
                right++;
            }
            //清除
            while (left <= right && left < timeList.size() && timeList.get(left) < l) {
                for (int t : map.get(timeList.get(left))) {
                    cnts[t]--;
                    if (cnts[t] == 0) {
                        cnt--;
                    }
                }
                left++;
            }
            res[index] = n - cnt;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:27 ms,击败了40.00% 的Java用户
     * 	内存消耗:133.9 MB,击败了10.00% 的Java用户
     * @param n
     * @param logs
     * @param x
     * @param queries
     * @return
     */
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1])); // 按照 time 排序
        Integer[] idx = new Integer[queries.length];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, Comparator.comparingInt(i -> queries[i]));
        int[] cnt = new int[n + 1]; // 每个服务器收到了多少个请求
        int notReceives = n; // 没有收到请求的服务器数目
        int left = 0;
        int right = 0;

        for (int qid : idx) {
            int q = queries[qid];
            // 进入窗口
            while (right < logs.length && logs[right][1] <= q) {
                int i = logs[right][0];
                if (cnt[i] == 0) {
                    notReceives--;
                }
                cnt[i]++;
                right++;
            }

            // 离开窗口
            while (left < logs.length && logs[left][1] < q - x) {
                int i = logs[left][0];
                cnt[i]--;
                if (cnt[i] == 0) {
                    notReceives++;
                }
                left++;
            }

            queries[qid] = notReceives;
        }

        return queries;
    }

}