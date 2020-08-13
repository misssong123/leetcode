package com.meng;

public class Multiply {
    public static void main(String[] args) {
        Multiply demo = new Multiply();
        System.out.println(demo.multiply("52","60"));
    }
    public String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2))
            return "0";
        String result = "";
        String temp = null;
        StringBuffer sb = new StringBuffer();
        if (num1.length()>num2.length()){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }
        for (int i = num1.length()-1 ; i>=0;i--){
            if (i==num1.length()-1){
                result = multiply(num2,Integer.parseInt(num1.charAt(i)+""));
            }
            else{
                sb.append("0");
                result = add(result,multiply(num2,Integer.parseInt(num1.charAt(i)+""))+sb.toString());
            }

        }
        return result;
    }
    public String multiply(String num1,int factor){
        if ("0".equals(factor))
            return "0";
        String result = "";
        int scale = 0;
        int length = num1.length();
        int a = 0;
        int add = 0;
        for(int i = length -1;i>=0;i--){
            a=Integer.parseInt(num1.charAt(i)+"");
            add =(a*factor+scale)%10;
            scale=(a*factor+scale)/10;
            result = add +result;
        }
        if (scale != 0)
            result = scale + result;
        return result;
    }
    public String add(String num1,String num2){
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
               result =  add(num2.substring(0,maxLength-minLength),scale+"") + result;
            else
                result = scale+result;
        }else {
            result = num2.substring(0,maxLength-minLength) + result;
        }

        return result;
    }
}
