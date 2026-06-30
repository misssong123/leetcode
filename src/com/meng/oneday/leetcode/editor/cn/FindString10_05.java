package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class FindString10_05 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了8.62% 的Java用户
     * 	内存消耗:45.5 MB,击败了5.17% 的Java用户
     * @param words
     * @param s
     * @return
     */
    public int findString10_05(String[] words, String s) {
        List<String> temp = new ArrayList<>(words.length/2);
        List<Integer> index = new ArrayList<>(words.length/2);
        for (int i = 0 ; i < words.length ; i++){
            if(words[i].isEmpty()){
                continue;
            }
            temp.add(words[i]);
            index.add(i);
        }
        int l = 0 , r = temp.size()-1;
        while (l <= r){
            int mid = (l+r)/2;
            int compare = temp.get(mid).compareTo(s);
            if (compare == 0 ){
                return index.get(mid);
            }else if (compare > 0){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了82.76% 的Java用户
     * @param words
     * @param s
     * @return
     */
    public int findString(String[] words, String s) {
        int len=words.length;
        int l=0;
        int r=len-1;
        while(l<=r){
            while(l<=r&& words[l].isEmpty()) l++;
            while(l<=r&& words[r].isEmpty()) r--;
            int mid=l+(r-l)/2;
            while(mid<=r&& words[mid].isEmpty()) mid++;
            if(words[mid].compareTo(s)==0){
                return mid;
            }
            if(words[mid].compareTo(s)>0){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return -1;
    }

}
