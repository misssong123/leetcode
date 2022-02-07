package com.meng.origin;

import java.util.*;

/**
 * 1418. 点菜展示表
 *
 * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
 *
 * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
 *
 * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * 解释：
 * 点菜展示表如下所示：
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
 * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
 * 餐桌 10：Corina 点了 "Beef Burrito"
 *
 * 示例 2：
 *
 * 输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
 * 输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
 * 解释：
 * 对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
 * 而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
 *
 * 示例 3：
 *
 * 输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
 * 输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
 *
 *
 *
 * 提示：
 *
 *     1 <= orders.length <= 5 * 10^4
 *     orders[i].length == 3
 *     1 <= customerNamei.length, foodItemi.length <= 20
 *     customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
 *     tableNumberi 是 1 到 500 范围内的整数。
 * @author lenovo
 */
public class DisplayTable {
    /**
     * 执行用时：44 ms, 在所有 Java 提交中击败了61.45% 的用户
     * 内存消耗：63 MB, 在所有 Java 提交中击败了60.24% 的用户
     * @param orders
     * @return
     */
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> foodCache = new HashSet<>();
        List<Integer> tableQueue = new ArrayList<>();
        List<String> foodQueue = new ArrayList<>();
        Map<Integer, Map<String,Integer>> res = new HashMap<>();
        for(List<String> order : orders){
            Integer table = Integer.parseInt(order.get(1));
            String food = order.get(2);
            if (res.get(table) == null){
                res.put(table,new HashMap<>());
                tableQueue.add(table);
            }
            if (!foodCache.contains(food)){
                foodCache.add(food);
                foodQueue.add(food);
            }
            Map<String, Integer> cache = res.get(table);
            cache.put(food,cache.getOrDefault(food,0)+1);
            res.put(table,cache);
        }
        Collections.sort(tableQueue);
        Collections.sort(foodQueue);
        List<List<String>> result = new ArrayList<>();
        int len = foodQueue.size();
        int height = tableQueue.size();
        List<String> head = new ArrayList<>();
        head.add("Table");
        head.addAll(foodQueue);
        result.add(head);
        for(int i = 0 ; i < height ; i++){
            List<String> column = new ArrayList<>();
            Integer table = tableQueue.get(i);
            column.add(table+"");
            Map<String, Integer> foodMap = res.get(table);
            for(int j = 0 ; j < len ; j++){
                column.add(foodMap.getOrDefault(foodQueue.get(j),0)+"");
            }
            result.add(column);
        }
        return result;
    }

    /**
     * 方法一：哈希表
     *
     * 我们首先分析题目需要我们做些什么：
     *
     *     我们需要将订单信息进行汇总，存放在一张数据表中作为答案返回；
     *     数据表的第一行包含了所有的餐品名称，并且需要按照餐品名称的字典序排序，因此我们需要遍历订单信息，获取所有的餐品名称并对它们进行排序；
     *     数据表的第一列包含了所有的餐桌桌号，并且需要按照桌号排序，因此我们需要遍历订单信息，获取所有的桌号并对它们进行排序；
     *     数据表中间包含的信息为「某一桌下单的某一道菜的数量」。
     *
     * 我们可以使用两个哈希表来保存订单中的数据：
     *
     *     哈希表 nameSet\textit{nameSet}nameSet 保存所有的餐品名称；
     *     哈希表 foodsCnt\textit{foodsCnt}foodsCnt 保存桌号及该桌点餐数量，点餐数量也用一个哈希表保存。
     *
     * 遍历订单并保存信息后，从 nameSet\textit{nameSet}nameSet 中提取餐品名称，并按字母顺序排列；从 foodsCnt\textit{foodsCnt}foodsCnt 中提取桌号，并按桌号升序排列。然后将餐品名称和桌号分别填入点菜展示表的第一行和第一列。对于表中的餐品数量，我们逐行填入，对于每一行，我们遍历餐品名称，在 foodsCnt\textit{foodsCnt}foodsCnt 中查找对应的点餐数量，然后填入表格中对应位置。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant/solution/dian-cai-zhan-shi-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param orders
     * @return
     * 执行用时：35 ms, 在所有 Java 提交中击败了80.72% 的用户
     * 内存消耗：63.2 MB, 在所有 Java 提交中击败了59.04% 的用户
     */
    public List<List<String>> displayTable1(List<List<String>> orders) {
        // 从订单中获取餐品名称和桌号，统计每桌点餐数量
        Set<String> nameSet = new HashSet<String>();
        Map<Integer, Map<String, Integer>> foodsCnt = new HashMap<Integer, Map<String, Integer>>();
        for (List<String> order : orders) {
            nameSet.add(order.get(2));
            int id = Integer.parseInt(order.get(1));
            Map<String, Integer> map = foodsCnt.getOrDefault(id, new HashMap<String, Integer>());
            map.put(order.get(2), map.getOrDefault(order.get(2), 0) + 1);
            foodsCnt.put(id, map);
        }

        // 提取餐品名称，并按字母顺序排列
        int n = nameSet.size();
        List<String> names = new ArrayList<String>();
        for (String name : nameSet) {
            names.add(name);
        }
        Collections.sort(names);

        // 提取桌号，并按餐桌桌号升序排列
        int m = foodsCnt.size();
        List<Integer> ids = new ArrayList<Integer>();
        for (int id : foodsCnt.keySet()) {
            ids.add(id);
        }
        Collections.sort(ids);

        // 填写点菜展示表
        List<List<String>> table = new ArrayList<List<String>>();
        List<String> header = new ArrayList<String>();
        header.add("Table");
        for (String name : names) {
            header.add(name);
        }
        table.add(header);
        for (int i = 0; i < m; ++i) {
            int id = ids.get(i);
            Map<String, Integer> cnt = foodsCnt.get(id);
            List<String> row = new ArrayList<String>();
            row.add(Integer.toString(id));
            for (int j = 0; j < n; ++j) {
                row.add(Integer.toString(cnt.getOrDefault(names.get(j), 0)));
            }
            table.add(row);
        }
        return table;
    }
    public static void main(String[] args) {
        DisplayTable demo = new DisplayTable();
        List<String> head1 = Arrays.asList("David","3","Ceviche");
        List<String> head2 = Arrays.asList("Corina","10","Beef Burrito");
        List<String> head3 = Arrays.asList("David","3","Fried Chicken");
        List<String> head4 = Arrays.asList("Carla","5","Water");
        List<String> head5 = Arrays.asList("Carla","5","Ceviche");
        List<String> head6 = Arrays.asList("Rous","3","Ceviche");
        List<List<String>> orders = Arrays.asList(head1,head2,head3,head4,head5,head6);
        System.out.println(demo.displayTable(orders));
    }
}
