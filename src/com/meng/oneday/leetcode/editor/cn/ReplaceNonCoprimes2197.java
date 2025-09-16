package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ReplaceNonCoprimes2197 {
    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了78.33% 的Java用户
     * 	内存消耗:62.1 MB,击败了56.67% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> replaceNonCoprimes2197(int[] nums) {
        if (nums.length < 2){
            return Arrays.stream(nums).boxed().collect(Collectors.toList());
        }
        List<Integer> res = new ArrayList<>();
        //计算前两个最小公倍数
        int lcm = lcm2197(nums[0],nums[1]);
        if (lcm == -1){
            res.add(nums[0]);
            res.add(nums[1]);
        }else {
            res.add(lcm);
        }
        int pre = res.get(res.size()-1);
        for(int i = 2 ; i < nums.length; i++){
            int n = lcm2197(pre,nums[i]);
            if(n == -1){
                res.add(nums[i]);
            }else if(n == pre){
                continue;
            }else{
                res.remove(res.size()-1);
                int size = res.size();
                while (size > 0){
                    int temp = res.get(res.size()-1);
                    int r = lcm2197(temp,n);
                    if(r != -1){
                        n = r;
                        res.remove(res.size()-1);
                        size--;
                    }else {
                        break;
                    }
                }
                res.add(n);
            }
            pre = res.get(res.size()-1);
        }
        return res;
    }

    private int lcm2197(int a, int b) {
        if (a == 1 || b == 1){
            return -1;
        }
        if (a % b == 0){
            return a;
        }
        if (b % a == 0){
            return b;
        }
        int oA = a,oB = b;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a == 1 ? -1 : oA /a * oB;
    }

    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了76.67% 的Java用户
     * 	内存消耗:63.1 MB,击败了43.33% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> st = new ArrayList<>();
        for (int x : nums) {
            while (!st.isEmpty()) {
                int gcd = gcd(x, st.get(st.size()-1));
                if (gcd ==1){
                    break;
                }
                x = x / gcd * st.remove(st.size()-1);
            }
            st.add(x);
        }
        return st;
    }
    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }


}
