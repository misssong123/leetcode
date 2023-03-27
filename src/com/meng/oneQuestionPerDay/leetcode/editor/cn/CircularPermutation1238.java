package com.meng.oneQuestionPerDay.leetcode.editor.cn;



import java.util.ArrayList;
import java.util.List;

class CircularPermutation1238 {
    /**
     * 递归
     * @param n
     * @param start
     * @return
     * 执行用时：
     * 701 ms
     * , 在所有 Java 提交中击败了
     * 5.43%
     * 的用户
     * 内存消耗：
     * 91.6 MB
     * , 在所有 Java 提交中击败了
     * 5.43%
     * 的用户
     * 通过测试用例：
     * 47 / 47
     */
    boolean [] cache;
    List<Integer> res;
    boolean flag = true;
    int N ;
    public List<Integer> circularPermutation(int n, int start) {
        N = n ;
        int len = (int)Math.pow(2,n);
        cache = new boolean[len];
        res = new ArrayList<>();
        cache[start] = true;
        res.add(start);
        dfs(start,1,len);
        return res;
    }

    private void dfs(int i,int index, int len) {
        if (flag){
            if (index == len){
                if (Integer.bitCount(res.get(0)^res.get(len-1))==1){
                    flag = false;
                }
                return;
            }
            List<Integer> nums = getNext(i);
            if (nums.size()==0){
                return;
            }
            for (int num : nums){
                if (flag){
                    res.add(num);
                    cache[num] = true;
                    dfs(num,index+1,len);
                    if (flag){
                        res.remove(res.size()-1);
                        cache[num] = false;
                    }
                }
            }
        }
    }

    private List<Integer> getNext(int start) {
        String s = Integer.toBinaryString(start);
        for(int i = s.length() ; i < N ; i++){
            s = "0" + s;
        }
        char[] chars = s.toCharArray();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0 ; i < N ; i++){
            if(chars[i] == '1'){
                chars[i] = '0';
                int num = Integer.parseInt(new String(chars), 2);
                if (!cache[num]){
                    temp.add(num);
                }
                chars[i] = '1';
            }else {
                chars[i] = '1';
                int num = Integer.parseInt(new String(chars), 2);
                if (!cache[num]){
                    temp.add(num);
                }
                chars[i] = '0';
            }
        }
        return temp;
    }

    public static void main(String[] args) {
       CircularPermutation1238 demo = new CircularPermutation1238();
        System.out.println(demo.circularPermutation(2,3));
    }

    /**
     * 归纳法
     * @param n
     * @param start
     * @return
     * 执行用时: 8 ms
     * 内存消耗: 48.9 MB
     */
    public List<Integer> circularPermutation1(int n, int start) {
        List<Integer> ret = new ArrayList<Integer>();
        ret.add(start);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            for (int j = m - 1; j >= 0; j--) {
                ret.add(((ret.get(j) ^ start) | (1 << (i - 1))) ^ start);
            }
        }
        return ret;
    }

    /**
     * 公式法
     * @param n
     * @param start
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 82.61%
     * 的用户
     * 内存消耗：
     * 48.6 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 47 / 47
     */
    public List<Integer> circularPermutation2(int n, int start) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < 1 << n; i++) {
            ret.add((i >> 1) ^ i ^ start);
        }
        return ret;
    }

}

