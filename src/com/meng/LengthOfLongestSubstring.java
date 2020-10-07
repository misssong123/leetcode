package com.meng;

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s ="abbbbbabbbba";
        System.out.println(s.indexOf("a"));
        System.out.println(s.indexOf("a",1));
    }
    public int lengthOfLongestSubstring(String s) {
        if (s.length()<2)
            return s.length();
        int n = s.length();
        int max = 0;
        for (int i = 0 ; i<s.length();i++){
            if (s.length()-i<=max)
                return max;

        }
        return max;
    }
}
