package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.stream.IntStream;

class SortByBits1356 {
    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了51.59% 的Java用户
     * 	内存消耗:46 MB,击败了32.94% 的Java用户
     * @param arr
     * @return
     */
    public int[] sortByBits1356(int[] arr) {
        Integer[] arrBox = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(arrBox, (a, b) -> {
            int bitA = Integer.bitCount(a);
            int bitB = Integer.bitCount(b);
            return bitA == bitB ? a - b : bitA - bitB;
        });
        return Arrays.stream(arrBox).mapToInt(Integer::intValue).toArray();
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了51.59% 的Java用户
     * 	内存消耗:46 MB,击败了35.71% 的Java用户
     * @param arr
     * @return
     */
    public int[] sortByBits(int[] arr) {
        return IntStream.of(arr)
                .boxed()
                .sorted((a, b) -> {
                    int ca = Integer.bitCount(a);
                    int cb = Integer.bitCount(b);
                    return ca != cb ? ca - cb : a - b;
                })
                .mapToInt(a -> a)
                .toArray();
    }

}
