package com.meng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIpAddresses {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        RestoreIpAddresses demo = new RestoreIpAddresses();
        list=demo.restoreIpAddresses("010010");
        System.out.println(list);
    }
    /**
     * 满足条件
     * 每个整数最高为三位,最低为一位,且需要小于256
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        String [] temp = new String[4];
        int length = s.length();
        judge(s,s.length(),0,temp,result);
        return  result;
    }
    private void judge(String s,int length,int n,String[] temp,List<String> result){
        if (n==4){
            add(result,temp);
            return;
        }
        //当前的最大长度
        int max = length - (3-n);
        max = max >3 ? 3 : max ;
        //当前的最小长度
        int min = length- (3-n)*3;
        min = min < 1 ? 1 :min;
        for (int i = min ; i< max +1 ;i++){
            String substring = s.substring(s.length() - length, s.length() - length + i);
            if (check(substring)){
                temp[n]=substring;
                judge(s,length-i,n+1,temp,result);
            }
        }
    }
    private boolean check(String s){
        //判断当前字符是否小于256 并且不能为以0开头的非长度不为1的字符串
        if (Integer.parseInt(s) >= 256 || (s.startsWith("0") && s.length() != 1))
            return false;
        return true;
    }
    private void add (List<String> list,String[] result){
        String temp = "";
        for(String s : result)
            temp = temp +s+".";
        temp = temp.substring(0,temp.length()-1);
        list.add(temp);
    }
}
