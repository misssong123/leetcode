package com.meng.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 299. 猜数字游戏
 * 难度
 * 中等
 *
 * 180
 *
 * 收藏
 *
 * 分享
 * 切换为英文
 * 接收动态
 * 反馈
 * 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
 *
 * 写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：
 *
 * 猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls", 公牛），
 * 有多少位属于数字猜对了但是位置不对（称为 "Cows", 奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
 * 给你一个秘密数字 secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。
 *
 * 提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。
 *
 * 请注意秘密数字和朋友猜测的数字都可能含有重复数字。
 *
 *
 *
 * 示例 1:
 *
 * 输入: secret = "1807", guess = "7810"
 * 输出: "1A3B"
 * 解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
 * "1807"
 *   |
 * "7810"
 * 示例 2:
 *
 * 输入: secret = "1123", guess = "0111"
 * 输出: "1A1B"
 * 解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
 * "1123"        "1123"
 *   |      or     |
 * "0111"        "0111"
 * 注意，两个不匹配的 1 中，只有一个会算作奶牛（数字猜对位置不对）。通过重新排列非公牛数字，其中仅有一个 1 可以成为公牛数字。
 * 示例 3：
 *
 * 输入：secret = "1", guess = "0"
 * 输出："0A0B"
 * 示例 4：
 *
 * 输入：secret = "1", guess = "1"
 * 输出："1A0B"
 *
 *
 * 提示：
 *
 * 1 <= secret.length, guess.length <= 1000
 * secret.length == guess.length
 * secret 和 guess 仅由数字组成
 */
public class GetHint {
    /**
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 17.36%
     * 的用户
     * 内存消耗：
     * 38.9 MB
     * , 在所有 Java 提交中击败了
     * 7.05%
     * 的用户
     * 通过测试用例：
     * 152 / 152
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        Map<Character, Set<Integer>> cache = new HashMap<>();
        int aNum = 0;
        int bNum = 0;
        int index = 0;
        char[] chars = secret.toCharArray();
        int[] nums = new int[10];
        for(char c : chars){
            Set<Integer> set = cache.getOrDefault(c, new HashSet<>());
            set.add(index++);
            nums[c-'0']++;
            cache.put(c,set);
        }
        index = 0;
        chars = guess.toCharArray();
        for(char c : chars){
            Set<Integer> set = cache.get(c);
            if (set != null && set.size()>0){
                if (set.contains(index)){
                    if (nums[c-'0']>0){
                        aNum++;
                        nums[c-'0']--;
                        set.remove(index);
                    }else {
                        bNum--;
                        aNum++;
                        set.remove(index);
                    }
                }else {
                    if (nums[c-'0']>0){
                        bNum++;
                        nums[c-'0']--;
                    }
                }
            }
            index++;
        }
        return aNum+"A"+bNum+"B";
    }

    /**
     * 方法一：遍历
     *
     * 根据题意，对于公牛，需要满足数字和确切位置都猜对。我们可以遍历
     * secret
     * secret 和
     * guess
     * guess，统计满足
     * secret
     * [
     * i
     * ]
     * =
     * guess
     * [
     * i
     * ]
     * secret[i]=guess[i] 的下标个数，即为公牛的个数。
     *
     * 对于奶牛，需要满足数字猜对但是位置不对。我们可以在
     * secret
     * [
     * i
     * ]
     * ≠
     * guess
     * [
     * i
     * ]
     * secret[i]
     * 
     * ​
     *  =guess[i] 时，分别统计
     * secret
     * secret 和
     * guess
     * guess 的各个字符的出现次数，记在两个长度为
     * 10
     * 10 的数组中。根据题目所述的「这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字」，由于多余的数字无法匹配，对于
     * 0
     * 0 到
     * 9
     * 9 的每位数字，应取其在
     * secret
     * secret 和
     * guess
     * guess 中的出现次数的最小值。将每位数字出现次数的最小值累加，即为奶牛的个数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/bulls-and-cows/solution/cai-shu-zi-you-xi-by-leetcode-solution-q9lz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param secret
     * @param guess
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 56.85%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 77.28%
     * 的用户
     * 通过测试用例：
     * 152 / 152
     */
    public String getHint1(String secret, String guess) {
        int bulls = 0;
        int[] cntS = new int[10];
        int[] cntG = new int[10];
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++bulls;
            } else {
                ++cntS[secret.charAt(i) - '0'];
                ++cntG[guess.charAt(i) - '0'];
            }
        }
        int cows = 0;
        for (int i = 0; i < 10; ++i) {
            cows += Math.min(cntS[i], cntG[i]);
        }
        return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
    }


}
