package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class Router3508 {
    int size;
    List<int[]> vals;
    Map<Integer,List<int[]>> destMap;
    Map<Integer,List<int[]>> sourceMap;
    public Router3508(int memoryLimit) {
        this.size = memoryLimit;
        vals = new ArrayList<>();
        destMap = new HashMap<>();
        sourceMap = new HashMap<>();
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        if (sourceMap.containsKey(source)){
            for (int[] num : sourceMap.get(source)) {
                if(num[3]==0 &&num[1] == destination && num[2] == timestamp){
                    return false;
                }
            }
        }
        if (vals.size() >= size){
            int[] remove = vals.remove(0);
            remove[3] = 1;
        }
        int[] packet = new int[]{source,destination,timestamp,0};
        vals.add(packet);
        if(!destMap.containsKey(destination)){
            destMap.put(destination,new ArrayList<>());
        }
        destMap.get(destination).add(packet);
        if(!sourceMap.containsKey(source)){
            sourceMap.put(source,new ArrayList<>());
        }
        sourceMap.get(source).add(packet);
        return true;
    }
    
    public int[] forwardPacket() {
        if (vals.isEmpty()){
            return new int[]{};
        }
        int[] packet = vals.remove(0);
        packet[3] = 1;
        return new int[]{packet[0],packet[1],packet[2]};
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        if (!destMap.containsKey(destination)){
            return 0;
        }
        Iterator<int[]> iterator = destMap.get(destination).iterator();
        int count = 0;
        while (iterator.hasNext()){
            int[] next = iterator.next();
            if(next[3] == 1){
                iterator.remove();
            }else if (next[2] >= startTime && next[2] <= endTime){
                count++;
            }
        }
        return count;
    }
}

/**
 * 解答成功:
 * 	执行耗时:207 ms,击败了32.27% 的Java用户
 * 	内存消耗:116.1 MB,击败了78.87% 的Java用户
 */
/*
class RouterOther {
    private record Packet(int source, int destination, int timestamp) {
    }

    private record Pair(List<Integer> timestamps, int head) {
    }

    private final int memoryLimit;
    private final Queue<Packet> packetQ = new ArrayDeque<>(); // Packet 队列
    private final Set<Packet> packetSet = new HashSet<>(); // Packet 集合
    private final Map<Integer, Pair> destToTimestamps = new HashMap<>(); // destination -> ([timestamp], head)

    public RouterRouterOther(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if (!packetSet.add(packet)) { // packet 在 packetSet 中
            return false;
        }
        if (packetQ.size() == memoryLimit) { // 太多了
            forwardPacket();
        }
        packetQ.add(packet); // 入队
        destToTimestamps.computeIfAbsent(destination, k -> new Pair(new ArrayList<>(), 0)).timestamps.add(timestamp);
        return true;
    }

    public int[] forwardPacket() {
        if (packetQ.isEmpty()) {
            return new int[]{};
        }
        Packet packet = packetQ.poll(); // 出队
        packetSet.remove(packet);
        destToTimestamps.compute(packet.destination, (k, p) -> new Pair(p.timestamps, p.head + 1)); // 队首下标加一，模拟出队
        return new int[]{packet.source, packet.destination, packet.timestamp};
    }

    public int getCount(int destination, int startTime, int endTime) {
        Pair p = destToTimestamps.get(destination);
        if (p == null) {
            return 0;
        }
        int left = lowerBound(p.timestamps, startTime, p.head - 1);
        int right = lowerBound(p.timestamps, endTime + 1, p.head - 1);
        return right - left;
    }

    // https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(List<Integer> nums, int target, int left) {
        int right = nums.size();
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
*/

/**
 * 解答成功:
 * 	执行耗时:232 ms,击败了12.35% 的Java用户
 * 	内存消耗:114.1 MB,击败了86.75% 的Java用户
 */
/*
class Router {
    private record Packet(int source, int destination, int timestamp) {}

    private static class Pair {
        List<Integer> timestamps;
        int head;
        Pair() {
            this.timestamps = new ArrayList<>();
            this.head = 0;
        }
    }

    private final int memoryLimit;
    private final Queue<Packet> packetQ = new ArrayDeque<>();
    private final Set<Packet> packetSet = new HashSet<>();
    private final Map<Integer, Pair> destToTimestamps = new HashMap<>();

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if (!packetSet.add(packet)) return false;
        if (packetQ.size() == memoryLimit) forwardPacket();
        packetQ.add(packet);
        destToTimestamps.computeIfAbsent(destination, k -> new Pair())
                .timestamps.add(timestamp);
        return true;
    }

    public int[] forwardPacket() {
        if (packetQ.isEmpty()) return new int[]{};
        Packet packet = packetQ.poll();
        packetSet.remove(packet);
        Pair p = destToTimestamps.get(packet.destination);
        p.head++;
        return new int[]{packet.source, packet.destination, packet.timestamp};
    }

    public int getCount(int destination, int startTime, int endTime) {
        Pair p = destToTimestamps.get(destination);
        if (p == null) return 0;
        int left = lowerBound(p.timestamps, startTime, p.head);
        int right = lowerBound(p.timestamps, endTime + 1, p.head);
        return right - left;
    }

    private int lowerBound(List<Integer> nums, int target, int left) {
        int right = nums.size();
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}*/