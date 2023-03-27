package com.meng.competition.years2021.day01;

import java.util.*;

/**
 * LCP 40. 心算挑战(简单)
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，
 * 若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。
 * 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 *
 * 示例 1：
 *
 * 输入：cards = [1,2,8,9], cnt = 3
 *
 * 输出：18
 *
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 *
 * 示例 2：
 *
 * 输入：cards = [3,3,1], cnt = 1
 *
 * 输出：0
 *
 * 解释：不存在获取有效得分的卡牌方案。
 *
 * 提示：
 *
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 */
public class MaxmiumScore {
    /**
     * 执行用时：
     * 2285 ms
     * , 在所有 Java 提交中击败了
     * 5.24%
     * 的用户
     * 内存消耗：
     * 58.1 MB
     * , 在所有 Java 提交中击败了
     * 5.24%
     * 的用户
     * 通过测试用例：
     * 104 / 104
     * @param cards
     * @param cnt
     * @return
     */
    public int maxmiumScore(int[] cards, int cnt) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for(int num : cards){
            if (num % 2 == 0){
                even.add(num);
            }else {
                odd.add(num);
            }
        }
        Collections.sort(odd, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        Collections.sort(even, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        //不存在偶数，并且cnt为奇数，则返回0
        if (even.size()==0 && cnt % 2 == 1){
            return 0;
        }
        int sum = 0;
        //不存在奇数
        if (odd.size() == 0){
            for(int i = 0 ; i < cnt ; i++){
                sum+=even.get(i);
            }
        } else if (even.size() == 0){//不存在偶数
            for(int i = 0 ; i < cnt ; i++){
                sum+=odd.get(i);
            }
        }else {
            int count = 0 ;
            if (cnt % 2 == 1){
                sum += even.get(0);
                even.remove(0);
                count++;
            }
            while (count < cnt){
                int o = 0;
                int e = 0;
                if (even.size()>1){
                    e = even.get(0) + even.get(1);
                }
                if (odd.size()>1){
                    o = odd.get(0) + odd.get(1);
                }
                if (o>e){
                    sum += o;
                    odd.remove(0);
                    odd.remove(0);
                }else {
                    sum += e;
                    even.remove(0);
                    even.remove(0);
                }
                count += 2;
                if (count != cnt && even.size()<2 && odd.size()<2){
                    return 0;
                }
            }

        }
        return sum;
    }
    public static void main(String[] args) {
        MaxmiumScore demo = new MaxmiumScore();
        int[] cards = {1,3,4,5};
        int cnt = 4;
        System.out.println(demo.maxmiumScore(cards,cnt));
    }
    /**
     * 解题思路
     * 取最大的cnt相加，如果是偶数，就返回。
     * 如果是奇数，在剩下的数中，找最大的奇数和偶数。对第一步求和中的最小偶数和奇数进行替换。
     * 两种替换中，能换就有一种答案，不能换答案就是0，两种替换中最大的值即为所求。
     *
     * 作者：wa-pian-d
     * 链接：https://leetcode.cn/problems/uOAnQW/solution/lcp-40-xin-suan-tiao-zhan-java-by-wa-pia-7or4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param cards
     * @param cnt
     * @return
     * 执行用时：
     * 77 ms
     * , 在所有 Java 提交中击败了
     * 65.95%
     * 的用户
     * 内存消耗：
     * 53.2 MB
     * , 在所有 Java 提交中击败了
     * 40.00%
     * 的用户
     * 通过测试用例：
     * 104 / 104
     */
    public int maxmiumScore1(int[] cards, int cnt) {
        Arrays.sort(cards);
        int len = cards.length;
        if (len < cnt) {
            return 0;
        }
        int sum = 0;
        int minOdd = 1001, minEven = 1001;
        for (int i = len - 1; i >= len - cnt; i--) {
            int cur = cards[i];
            if ((cur & 1) == 1) {
                minOdd = Math.min(minOdd, cur);
            } else {
                minEven = Math.min(minEven, cur);
            }
            sum += cur;
        }
        if ((sum & 1) == 0) {
            return sum;
        } else {
            int maxOdd = -1, maxEven = -1;
            for (int i = len - cnt - 1; i >= 0; i--) {
                int cur = cards[i];
                if ((cur & 1) == 1) {
                    maxOdd = Math.max(maxOdd, cur);
                } else {
                    maxEven = Math.max(maxEven, cur);
                }
            }
            int ans1 = maxEven == -1 ? 0 : minOdd == 1001 ? 0 : sum - minOdd + maxEven;
            int ans2 = maxOdd == -1 ? 0 : minEven == 1001 ? 0 : sum - minEven + maxOdd;
            return Math.max(ans1, ans2);
        }
    }
    /**
     * 执行用时：
     * 84 ms
     * , 在所有 Java 提交中击败了
     * 41.19%
     * 的用户
     * 内存消耗：
     * 51 MB
     * , 在所有 Java 提交中击败了
     * 75.71%
     * 的用户
     * 通过测试用例：
     * 104 / 104
     * @param cards
     * @param cnt
     * @return
     */
    public int maxmiumScore2(int[] cards, int cnt) {
        int[] two = new int[cards.length];
        int t = -1;
        int[] one = new int[cards.length];
        int o = -1;
        int sum = 0;
        //往one和two中存奇数和偶数
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] % 2 == 0) {
                two[++t] = cards[i];
            } else {
                one[++o] = cards[i];
            }
        }
        if(t>0) {
            Arrays.sort(two, 0, t+1);
        }
        if(o>0) {
            Arrays.sort(one, 0, o+1);
        }
        while (cnt > 0) {
            if (cnt % 2 != 0) {
                if(t==-1){
                    return 0;
                }
                sum += two[t--];
                cnt--;
            }else if(o<1&&t<1){
                return 0;
            }
            else if (o < 1) {
                sum += two[t--];
                sum += two[t--];
                cnt-=2;
            } else if (t < 1 ) {
                sum += one[o--];
                sum += one[o--];
                cnt-=2;
            }else if(two[t] + two[t - 1] >= one[o] + one[o - 1]){
                sum += two[t--];
                sum += two[t--];
                cnt-=2;
            }else if(two[t] + two[t - 1] < one[o] + one[o - 1]){
                sum += one[o--];
                sum += one[o--];
                cnt-=2;
            }
        }
        return sum;
    }
}
