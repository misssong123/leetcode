package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 解答成功:
 * 	执行耗时:111 ms,击败了36.11% 的Java用户
 * 	内存消耗:56.6 MB,击败了66.67% 的Java用户
 */
class UndergroundSystem1396 {
    Map<String,int[]> times;
    Map<Integer, String> checkInStationMap;
    Map<Integer, Integer> checkInTimeMap;
    public UndergroundSystem1396() {
        times = new HashMap<>();
        checkInStationMap = new HashMap<>();
        checkInTimeMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInStationMap.put(id,stationName);
        checkInTimeMap.put(id,t);
    }
    
    public void checkOut(int id, String stationName, int t) {
        String startStationName = checkInStationMap.remove(id);
        int startStationTime = checkInTimeMap.remove(id);
        String key = startStationName + "-" + stationName;
        if (!times.containsKey(key)){
            times.put(key,new int[]{0,0});
        }
        times.get(key)[0] += t - startStationTime;
        times.get(key)[1]++;
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "-" + endStation;
        return (double)times.get(key)[0]/times.get(key)[1];
    }
}

/**
 * 解答成功:
 * 	执行耗时:111 ms,击败了36.11% 的Java用户
 * 	内存消耗:57.4 MB,击败了33.33% 的Java用户
 */
class UndergroundSystem {
    class Start {
        String station;
        int time;

        public Start(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    class StartEnd {
        String start;
        String end;

        public StartEnd(String start, String end) {
            this.start = start;
            this.end = end;
        }

        public int hashCode() {
            return (start + end).hashCode();
        }

        public boolean equals(Object obj2) {
            if (obj2 instanceof StartEnd) {
                StartEnd startEnd2 = (StartEnd) obj2;
                return this.start.equals(startEnd2.start) && this.end.equals(startEnd2.end);
            }
            return false;
        }
    }

    class SumAmount {
        int sum;
        int amount;

        public SumAmount(int sum, int amount) {
            this.sum = sum;
            this.amount = amount;
        }
    }

    Map<Integer, Start> startInfo;
    Map<StartEnd, SumAmount> table;

    public UndergroundSystem() {
        startInfo = new HashMap<Integer, Start>();
        table = new HashMap<StartEnd, SumAmount>();
    }

    public void checkIn(int id, String stationName, int t) {
        startInfo.put(id, new Start(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Start start = startInfo.get(id);
        String startStation = start.station;
        int startTime = start.time;
        StartEnd startEnd = new StartEnd(startStation, stationName);
        SumAmount sumAmount = table.getOrDefault(startEnd, new SumAmount(0, 0));
        sumAmount.sum += t - startTime;
        sumAmount.amount++;
        table.put(startEnd, sumAmount);
    }

    public double getAverageTime(String startStation, String endStation) {
        StartEnd index = new StartEnd(startStation, endStation);
        SumAmount sumAmount = table.get(index);
        int sum = sumAmount.sum, amount = sumAmount.amount;
        return 1.0 * sum / amount;
    }
}