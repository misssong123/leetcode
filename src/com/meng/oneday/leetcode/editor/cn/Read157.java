package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 解答成功:
 * 	执行耗时:0 ms,击败了100.00% 的Java用户
 * 	内存消耗:40.8 MB,击败了41.03% 的Java用户
 */
public class Read157 extends Reader4 {
    public int read(char[] buf, int n) {
        int index = 0 ;
        char[] temp = new char[4];
        List<Character> list = new ArrayList<>();
        while(n > 0){
            int count = read4(temp);
            if (count == 0){
                break;
            }
            for(int i = 0 ; i < count ; i++){
                buf[index + i] = temp[i];
            }
            index += count;

        }
        return Math.min(index,n);
    }

    /**
     * 无用代码,仅为了编译通过
     * @param temp
     * @return
     */
    private int read4(char[] temp) {
        return 0;
    }

}
abstract class Reader4{
    abstract int read(char[] buf, int n);
}
