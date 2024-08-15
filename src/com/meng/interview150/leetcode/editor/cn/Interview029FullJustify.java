package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Interview029FullJustify {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了87.37% 的Java用户
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        int low = 0,fast=0;
        int len = 0;
        StringBuffer sb = new StringBuffer();
        List<String> res = new ArrayList<>();
        while (fast < words.length){
            //无法填充当前字符
            if (len + words[fast].length()> maxWidth){
                //计算待填充的元素个数
                int num = fast - low;
                if (num==1){
                    sb.append(words[low]);
                    for (int i = 0; i < maxWidth - words[low].length(); i++) {
                        sb.append(" ");
                    }
                }else {
                    //待填充的空格数量,最后一个元素不需要占用元素
                    int space = maxWidth - len+num;
                    //计算平均填充空格个数
                    int avgSpace = space / (num - 1);
                    //计算额外填充空格的个数
                    int extraSpace = space % (num - 1);
                    for(int i = 1 ;i < num; i++){
                        sb.append(words[low+i-1]);
                        for (int j = 0; j < avgSpace; j++) {
                            sb.append(" ");
                        }
                        if (extraSpace>=i){
                            sb.append(" ");
                        }
                    }
                    sb.append(words[low+num-1]);
                }
                res.add(sb.toString());
                sb.delete(0,sb.length());
                low = fast;
                len = 0;
            }//当前元素为最后一个元素
            else if (len + words[fast].length() == maxWidth){
                for(int i = low; i < fast; i++){
                    sb.append(words[i]).append(" ");
                }
                sb.append(words[fast]);
                res.add(sb.toString());
                sb.delete(0,sb.length());
                fast++;
                low = fast;
                len = 0;
            }else {//当前元素可以填充
                //非最后一个元素需要填充一个空格
                len += words[fast].length()+1;
                fast++;
                //最后一个元素
                if (fast == words.length){
                    for(int i = low; i < fast; i++){
                        sb.append(words[i]).append(" ");
                    }
                    for (int i = sb.length(); i < maxWidth; i++){
                        sb.append(" ");
                    }
                    res.add(sb.toString());
                }
            }
        }
        return res;
    }
}
