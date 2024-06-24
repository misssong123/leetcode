package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class CarFleet853 {
    /**
     * 解答成功:
     * 	执行耗时:58 ms,击败了98.34% 的Java用户
     * 	内存消耗:59.6 MB,击败了5.98% 的Java用户
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleetMy(int target, int[] position, int[] speed) {
        int n = position.length;
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i < n ; i++){
            cache.put(position[i],speed[i]);
        }
        Arrays.sort(position);
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{position[n-1],cache.get(position[n-1])});
        for(int i = n-2 ; i >= 0 ; i--){
            //前一个车队消耗
            int firstSpeed = list.get(list.size()-1)[1];
            int firstPosition = list.get(list.size()-1)[0];
            //当前车队消耗
            int currentSpeed = cache.get(position[i]);
            int currentPosition = position[i];
            if (firstSpeed >=currentSpeed ||
                    ((target-firstPosition)*1.0/firstSpeed <
                    (target-currentPosition)*1.0/currentSpeed)){
                list.add(new int[]{currentPosition,currentSpeed});
            }
        }
        return list.size();
    }

    /**
     * 54
     * ms
     * 击败
     * 98.67%
     * 复杂度分析
     * 消耗内存分布
     * 59.76
     * MB
     * 击败
     * 5.65%
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i < n ; i++){
            cache.put(position[i],speed[i]);
        }
        Arrays.sort(position);
        List<Double> list = new ArrayList<>();
        list.add((target-position[n-1])*1.0/cache.get(position[n-1]));
        for(int i = n-2 ; i >= 0 ; i--){
            //当前车队消耗
            double time = (target-position[i])*1.0/cache.get(position[i]);
            if (time > list.get(list.size()-1)){
                list.add(time);
            }
        }
        return list.size();
    }
    /**
     * 解答成功:
     * 	执行耗时:69 ms,击败了77.08% 的Java用户
     * 	内存消耗:56.7 MB,击败了49.17% 的Java用户
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleetOfficial(int target, int[] position, int[] speed) {
        int N = position.length;
        Car[] cars = new Car[N];
        for (int i = 0; i < N; ++i)
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
        Arrays.sort(cars, (a, b) -> Integer.compare(a.position, b.position));

        int ans = 0, t = N;
        while (--t > 0) {
            if (cars[t].time < cars[t-1].time) ans++; //if cars[t] arrives sooner, it can't be caught
            else cars[t-1] = cars[t]; //else, cars[t-1] arrives at same time as cars[t]
        }

        return ans + (t == 0 ? 1 : 0); //lone car is fleet (if it exists)
    }
}

class Car {
    int position;
    double time;
    Car(int p, double t) {
        position = p;
        time = t;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
