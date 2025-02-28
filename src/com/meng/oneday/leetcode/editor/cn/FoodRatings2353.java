package com.meng.oneday.leetcode.editor.cn;

import javafx.util.Pair;

import java.util.*;

/**
 * 解答成功:
 * 	执行耗时:187 ms,击败了82.61% 的Java用户
 * 	内存消耗:73 MB,击败了52.17% 的Java用户
 */
class FoodRatings2353 {
    Map<String,PriorityQueue<FoodRating>> cuisinesMap;
    Map<String,Integer> ratingMap;
    Map<String,String> foodCuisineMap;
    public FoodRatings2353(String[] foods, String[] cuisines, int[] ratings) {
        ratingMap = new HashMap<>();
        cuisinesMap = new HashMap<>();
        foodCuisineMap = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            foodCuisineMap.put(food,cuisine);
            ratingMap.put(food, rating);
            if (!cuisinesMap.containsKey(cuisine)) {
                cuisinesMap.put(cuisine, new PriorityQueue<>((a, b) -> {
                    //评分大的放到前面
                    if (!Objects.equals(a.rating,b.rating)) {
                        return b.rating - a.rating;
                    }
                    //评分相同，按字典序排序
                    return a.food.compareTo(b.food);
                }
                ));
            }
            cuisinesMap.get(cuisine).add(new FoodRating(food, rating));
        }
    }
    public void changeRating(String food, int newRating) {
        ratingMap.put(food, newRating);
        cuisinesMap.get(foodCuisineMap.get(food)).add(new FoodRating(food, newRating));
    }

    public String highestRated(String cuisine) {
        PriorityQueue<FoodRating> foodRatings = cuisinesMap.get(cuisine);
        while (!foodRatings.isEmpty()&&foodRatings.peek().rating != ratingMap.get(foodRatings.peek().food)) {
            foodRatings.poll();
        }
        return foodRatings.peek().food;
    }
}
class FoodRating{
    public String food;
    public int rating;
    public FoodRating(String food, int rating) {
        this.food = food;
        this.rating = rating;
    }
}

/**
 * 执行用时分布
 * 238
 * ms
 * 击败
 * 32.61%
 * 复杂度分析
 * 消耗内存分布
 * 73.27
 * MB
 * 击败
 * 27.17%
 */
class FoodRatingsOther {
    private final Map<String, Pair<Integer, String>> foodMap = new HashMap<>();
    private final Map<String, TreeSet<Pair<Integer, String>>> cuisineMap = new HashMap<>();

    public FoodRatingsOther(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            foodMap.put(food, new Pair<>(rating, cuisine));
            cuisineMap.computeIfAbsent(cuisine, k -> new TreeSet<>((a, b) ->
                    !Objects.equals(a.getKey(), b.getKey()) ? b.getKey() - a.getKey() : a.getValue().compareTo(b.getValue())
            )).add(new Pair<>(rating, food));
        }
    }

    public void changeRating(String food, int newRating) {
        Pair<Integer, String> p = foodMap.get(food);
        TreeSet<Pair<Integer, String>> st = cuisineMap.get(p.getValue());
        st.remove(new Pair<>(p.getKey(), food)); // 移除旧数据
        st.add(new Pair<>(newRating, food)); // 添加新数据
        foodMap.put(food, new Pair<>(newRating, p.getValue()));
    }

    public String highestRated(String cuisine) {
        return cuisineMap.get(cuisine).first().getValue();
    }
}

/**
 * 执行用时分布
 * 178
 * ms
 * 击败
 * 89.13%
 * 复杂度分析
 * 消耗内存分布
 * 73.48
 * MB
 * 击败
 * 18.48%
 */
class FoodRatings {
    private final Map<String, Pair<Integer, String>> foodMap = new HashMap<>();
    private final Map<String, PriorityQueue<Pair<Integer, String>>> cuisineMap = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            foodMap.put(food, new Pair<>(rating, cuisine));
            cuisineMap.computeIfAbsent(cuisine, k -> new PriorityQueue<>((a, b) ->
                    !Objects.equals(a.getKey(), b.getKey()) ? b.getKey() - a.getKey() : a.getValue().compareTo(b.getValue())
            )).offer(new Pair<>(rating, food));
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodMap.get(food).getValue();
        // 直接添加新数据，后面 highestRated 再删除旧的
        cuisineMap.get(cuisine).offer(new Pair<>(newRating, food));
        foodMap.put(food, new Pair<>(newRating, cuisine));
    }

    public String highestRated(String cuisine) {
        PriorityQueue<Pair<Integer, String>> pq = cuisineMap.get(cuisine);
        // 堆顶的食物评分不等于其实际值
        while (!Objects.equals(pq.peek().getKey(), foodMap.get(pq.peek().getValue()).getKey())) {
            pq.poll();
        }
        return pq.peek().getValue();
    }
}
