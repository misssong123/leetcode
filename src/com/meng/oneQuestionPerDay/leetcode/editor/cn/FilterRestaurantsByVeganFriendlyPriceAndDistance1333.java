package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class SolutionFilterRestaurants1333 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了67.16% 的Java用户
     * 	内存消耗:48.9 MB,击败了86.57% 的Java用户
     * @param restaurants
     * @param veganFriendly
     * @param maxPrice
     * @param maxDistance
     * @return
     */
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<int[]> temp = new ArrayList<>();
        for(int[] restaurant : restaurants){
            if (restaurant[2]<veganFriendly || restaurant[3]>maxPrice || restaurant[4] > maxDistance){
                continue;
            }
            temp.add(restaurant);
        }
        if (temp.size()==0){
            return new ArrayList<>();
        }
        temp.sort((a,b)->{
            if (a[1]==b[1]){
                return b[0] - a[0];
            }
            return b[1] - a[1];
        });
        return temp.stream().map(a->a[0]).collect(Collectors.toList());
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了89.55% 的Java用户
     * 	内存消耗:49.5 MB,击败了29.85% 的Java用户
     * @param restaurants
     * @param veganFriendly
     * @param maxPrice
     * @param maxDistance
     * @return
     */
    public List<Integer> filterRestaurants1(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        int n = restaurants.length;
        List<int[]> filtered = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            if (restaurants[i][3] <= maxPrice && restaurants[i][4] <= maxDistance && !(veganFriendly == 1 && restaurants[i][2] == 0)) {
                filtered.add(restaurants[i]);
            }
        }
        Collections.sort(filtered, (a, b) -> {
            if (a[1] != b[1]) {
                return b[1] - a[1];
            } else {
                return b[0] - a[0];
            }
        });
        List<Integer> res = new ArrayList<Integer>();
        for (int[] v : filtered) {
            res.add(v[0]);
        }
        return res;
    }

}