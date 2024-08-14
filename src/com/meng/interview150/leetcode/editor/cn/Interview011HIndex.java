package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;

class Interview011HIndex {
    /**
     * 排序
     * 解答成功:
     * 	执行耗时:1 ms,击败了81.96% 的Java用户
     * 	内存消耗:40.6 MB,击败了33.78% 的Java用户
     * @param citations
     * @return
     */
    public int hIndexMy(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        for(int i = 0 ; i < len ; i++){
            if (citations[i] >= len - i){
                return len - i;
            }
        }
        return 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了81.96% 的Java用户
     * 	内存消耗:40.5 MB,击败了60.74% 的Java用户
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int left=0,right=citations.length;
        int mid=0,cnt=0;
        while(left<right){
            // +1 防止死循环
            mid=(left+right+1)>>1;
            cnt=0;
            for(int i=0;i<citations.length;i++){
                if(citations[i]>=mid){
                    cnt++;
                }
            }
            if(cnt>=mid){
                // 要找的答案在 [mid,right] 区间内
                left=mid;
            }else{
                // 要找的答案在 [0,mid) 区间内
                right=mid-1;
            }
        }
        return left;
    }

}

