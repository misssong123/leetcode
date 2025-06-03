package com.meng.oneday.leetcode.editor.cn;

/**
 * 解答成功:
 * 	执行耗时:4 ms,击败了100.00% 的Java用户
 * 	内存消耗:44.9 MB,击败了16.95% 的Java用户
 */
class StreamRank {
    int[] arr;
    int n;
    public StreamRank() {
        n = 50002;
        arr = new int[n];
    }
    
    public void track(int x) {
        x++;
        for(int i = x ; i < n ; i+=lowBit(i)){
            arr[i]++;
        }
    }
    
    public int getRankOfNumber(int x) {
        x++;
        int temp = 0;
        for(int i = x ; i > 0 ; i-=lowBit(i)){
            temp += arr[i];
        }
        return temp;
    }
    int lowBit(int x) {
        return x & -x;
    }

    public static void main(String[] args) {
        StreamRank demo = new StreamRank();
        demo.track(4);
        demo.track(3);
        demo.track(5);
        for (int i = 0 ; i < 10; i++){
            System.out.print(demo.arr[i] + " ");
        }
        System.out.println();
        System.out.println(demo.getRankOfNumber(8));
    }
}
