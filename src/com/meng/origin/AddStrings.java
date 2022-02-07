package com.meng.origin;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 *
 *
 * 提示：
 *
 *     num1 和num2 的长度都小于 5100
 *     num1 和num2 都只包含数字 0-9
 *     num1 和num2 都不包含任何前导零
 *     你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 */
public class AddStrings {
    public static void main(String[] args) {
        AddStrings demo = new AddStrings();
        System.out.println(demo.add("1","9"));
    }
    public String addStrings(String num1, String num2) {
        if("0".equals(num1))
            return num2;
        if ("0".equals(num2))
            return num1;
        String result = "";
        String temp = "" ;
        if (num1.length() > num2.length()){
            temp = num1 ;
            num1 = num2 ;
            num2 = temp ;
        }
        int minLength = num1.length();
        int maxLength = num2.length();
        int a = -1;
        int b = -1 ;
        int scale = 0;
        int add = 0;
        for(int i = -1; i >= -minLength ; i--){
            a = Integer.parseInt(num1.charAt(minLength + i)+"");
            b = Integer.parseInt(num2.charAt(maxLength + i)+"");
            add = (a+b+scale)%10;
            scale = (a+b+scale)/10;
            result = add + result;
        }
        if (scale != 0){
            if (num2.length()>num1.length())
                result =  addStrings(num2.substring(0,maxLength-minLength),scale+"") + result;
            else
                result = scale+result;
        }else {
            result = num2.substring(0,maxLength-minLength) + result;
        }

        return result;
    }
    public String add(String num1,String num2){
        if("0".equals(num1))
            return num2;
        if ("0".equals(num2))
            return num1;
        int a = num1.length();
        int b = num2.length();
        int scale =0 ;
        int length = num1.length()>num2.length()?num1.length():num2.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i<length;i++){
            a--;b--;
            int k = a >=0?num1.charAt(a)-'0':0;
            int t = b >=0?num2.charAt(b)-'0':0;
            sb.append((k+t+scale)%10);
            scale = (k+t+scale)/10;
        }
        if (scale != 0)
            sb.append(scale);
        return sb.reverse().toString();
    }
}
