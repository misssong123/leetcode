package com.meng.origin;

import java.util.*;

/**
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 *
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 *
 * 请你返回排序后的数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [0,1,2,3,4,5,6,7,8]
 * 输出：[0,1,2,4,8,3,5,6,7]
 * 解释：[0] 是唯一一个有 0 个 1 的数。
 * [1,2,4,8] 都有 1 个 1 。
 * [3,5,6] 有 2 个 1 。
 * [7] 有 3 个 1 。
 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 *
 * 示例 2：
 *
 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 *
 * 示例 3：
 *
 * 输入：arr = [10000,10000]
 * 输出：[10000,10000]
 *
 * 示例 4：
 *
 * 输入：arr = [2,3,5,7,11,13,17,19]
 * 输出：[2,3,5,17,7,11,13,19]
 *
 * 示例 5：
 *
 * 输入：arr = [10,100,1000,10000]
 * 输出：[10,100,10000,1000]
 *
 *
 *
 * 提示：
 *
 *     1 <= arr.length <= 500
 *     0 <= arr[i] <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortByBits {
    public static void main(String[] args) {
        int [] arr ={0,1,2,3,4,5,6,7,8};
        SortByBits demo = new SortByBits();
        demo.sortByBits(arr);
    }
    /**
     * 利用 Collections中的排序进行解决
     * @param arr
     * @return
     * 执行用时：19 ms, 在所有 Java 提交中击败了19.27% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了75.34% 的用户
     */
    public int[] sortByBits(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int x : arr){
            list.add(x);
            map.put(x,getNum(x));
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (map.get(o1)!= map.get(o2)){
                    return map.get(o1)- map.get(o2);
                }else {
                    return o1 - o2;
                }
            }
        });
        int [] ans = new int[arr.length];
        for (int i = 0 ; i < arr.length ; i++)
            ans[i] = list.get(i);
        return ans;
    }

    /**
     * 获取转换成二进制后的位数
     * @param x
     * @return
     */
    public int getNum(int x){
        int ans =0;
        while (x!=0){
            ans += x%2;
            x /= 2;
        }
        return ans;
    }
    /**
     * 官方解法1
     *方法一：暴力
     *
     * 对每个十进制的数转二进制的时候统计一下 1 的个数即可。
     * 执行用时：11 ms, 在所有 Java 提交中击败了37.33% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了69.31% 的用户
     */
    public int[] sortByBits1(int[] arr) {
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
            bit[x] = get(x);
        }
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 2;
            x /= 2;
        }
        return res;
    }
/**
 * 官方解法2
 * 方法二：递推预处理
 *
 * 我们定义 bit[i]bit[i]bit[i] 为数字 iii 二进制表示下数字 1 的个数，则可以列出递推式：
 *
 * bit[i]=bit[i>>1]+(i&1)bit[i] = bit[i>>1] + (i \& 1) bit[i]=bit[i>>1]+(i&1)
 *
 * 所以我们线性预处理 bitbitbit 数组然后去排序即可
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/solution/gen-ju-shu-zi-er-jin-zhi-xia-1-de-shu-mu-pai-xu-by/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：9 ms, 在所有 Java 提交中击败了47.58% 的用户
 * 内存消耗：39 MB, 在所有 Java 提交中击败了69.31% 的用户
 */
public int[] sortByBits2(int[] arr) {
    List<Integer> list = new ArrayList<Integer>();
    for (int x : arr) {
        list.add(x);
    }
    int[] bit = new int[10001];
    for (int i = 1; i <= 10000; ++i) {
        bit[i] = bit[i >> 1] + (i & 1);
    }
    Collections.sort(list, new Comparator<Integer>() {
        public int compare(Integer x, Integer y) {
            if (bit[x] != bit[y]) {
                return bit[x] - bit[y];
            } else {
                return x - y;
            }
        }
    });
    for (int i = 0; i < arr.length; ++i) {
        arr[i] = list.get(i);
    }
    return arr;
}

    /**
     * 其他做法
     * 循环并使用 Integer.bitCount 计算数字中1的个数，乘以10000000（题目中不会大于 10^4）然后加上原数字，放入数组 map 中，并对 map 进行排序，最后 % 10000000 获取原来的数组，填充到原数组返回即可。
     *
     * 作者：yourtion
     * 链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/solution/javaliang-ci-xun-huan-da-bai-100-by-yourtion/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param arr
     * @return
     * 执行用时：3 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了88.95% 的用户
     */
    public int[] sortByBits3(int[] arr) {
        int[] array = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] % 10000000;
        }
        return array;
    }
}
