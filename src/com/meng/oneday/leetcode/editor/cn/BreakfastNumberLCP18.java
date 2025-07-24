package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class BreakfastNumberLCP18 {
    private static final int MOD = 1000000007;

    /**
     * 解答成功:
     * 	执行耗时:107 ms,击败了10.97% 的Java用户
     * 	内存消耗:63.4 MB,击败了71.31% 的Java用户
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumberLCP18(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int ans = 0;
        //寻找最大价值的主食
        int maxStapleIndex = binarySearch(staple,x, staple.length-1);
        int r = drinks.length-1;
        for(int i = 0 ; i<= maxStapleIndex ; i++){
            int maxDrinkIndex = binarySearch(drinks,x-staple[i],r);
            if (maxDrinkIndex == -1){
                break;
            }
            r = maxDrinkIndex;
            ans = (ans + maxDrinkIndex + 1)%MOD;
        }
        return ans;
    }
    private int binarySearch(int[] arr,int target,int r){
        int l = 0;
        int res = -1;
        while(l <= r){
            int mid = l + (r-l)/2;
            if(arr[mid]>target){
                r = mid -1;
            }else {
                res = mid;
                l = mid+1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:77 ms,击败了46.41% 的Java用户
     * 	内存消耗:63.8 MB,击败了6.75% 的Java用户
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumberOther1(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int cnt=0;
        int m=staple.length,n=drinks.length;
        int i=0,j=n-1;
        while(i<m&&j>=0){
            if(staple[i]+drinks[j]<=x){
                cnt=(cnt+j+1)%1000000007;
                i++;
            }else{
                j--;
            }
        }
        return cnt%1000000007;
    }

    /**
     * 解答成功:
     * 	执行耗时:112 ms,击败了6.75% 的Java用户
     * 	内存消耗:63.6 MB,击败了53.59% 的Java用户
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int cnt=0;
        int m=staple.length,n=drinks.length;
        for(int i=0;i<m;i++){
            int temp=x-staple[i];
            int idx=binarySearch(drinks,temp);
            if(idx==0){
                break;
            }
            cnt=(cnt+idx)%1000000007;
        }
        return cnt%1000000007;
    }
    public int binarySearch(int[] nums,int target){
        int i=0,j=nums.length;
        while(i<j){
            int mid=i+(j-i)/2;
            if(nums[mid]>target){
                j=mid;
            }else{
                i=mid+1;
            }
        }
        return i;
    }


}
