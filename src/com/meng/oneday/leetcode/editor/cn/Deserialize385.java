package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
class Deserialize385 {
    static NestedInteger ph = new NestedInteger(0);
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了51.04% 的Java用户
     * 	内存消耗:44.9 MB,击败了67.71% 的Java用户
     * @param s
     * @return
     */
    public NestedInteger deserializeOther(String s) {
        Deque<NestedInteger> d = new ArrayDeque<>();
        char[] cs = s.toCharArray();
        int n = cs.length, i = 0;
        while (i < n) {
            if (cs[i] == ',') {
                ++i;
                continue;
            }
            if (cs[i] == '-' || (cs[i] >= '0' && cs[i] <= '9')) {
                int j = cs[i] == '-' ? i + 1 : i, num = 0;
                while (j < n && (cs[j] >= '0' && cs[j] <= '9')){
                    num = num * 10 + (cs[j++] - '0');
                }
                d.addLast(new NestedInteger(cs[i] == '-' ? -num : num));
                i = j;
            } else if (cs[i] == '[') {
                d.addLast(new NestedInteger());
                d.addLast(ph);
                i++;
            } else {
                List<NestedInteger> list = new ArrayList<>();
                while (!d.isEmpty()) {
                    NestedInteger poll = d.pollLast();
                    if (poll == ph) break;
                    list.add(poll);
                }
                for (int j = list.size() - 1; !d.isEmpty()&&j >= 0; j--) {
                    d.peekLast().add(list.get(j));
                }
                i++;
            }
        }
        return d.peekLast();
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了19.79% 的Java用户
     * 	内存消耗:44.9 MB,击败了53.12% 的Java用户
     */
    private static final NestedInteger FLAG = new NestedInteger();
    public NestedInteger deserialize385(String s) {
        int index = 0,len = s.length();
        ArrayDeque<NestedInteger> stack = new ArrayDeque<>();
        while (index < len){
            char c = s.charAt(index);
            if (c == '['){
                index++;
                stack.addLast(new NestedInteger());
                stack.addLast(FLAG);
            }else if (c == '-' || (c >= '0' && c <= '9')){
                int num = 0 ,sign = c == '-' ? 1 : 0;
                index += sign;
                while (index < len && (c = s.charAt(index)) >= '0' && c <= '9'){
                    num = num * 10 + c - '0';
                    index++;
                }
                stack.addLast(new NestedInteger(sign == 1 ? -num : num));
            }else if (c == ']'){
                List<NestedInteger> lists = new ArrayList<>();
                while (!stack.isEmpty()){
                    NestedInteger node = stack.removeLast();
                    if (node == FLAG){
                        break;
                    }
                    lists.add(node);
                }
                for(int i = lists.size() - 1;!stack.isEmpty()&&i >= 0;i--){
                    stack.peekLast().add(lists.get(i));
                }
                index++;
            }else {
                index++;
            }
        }
        return stack.removeLast();
    }
    static class NestedInteger {
        Integer val;
        List<NestedInteger> lists;
        public NestedInteger(){
            lists = new ArrayList<>();
        }
        public NestedInteger(int value){
            this.val = value;
            lists = new ArrayList<>();
        }
        public boolean isInteger(){
            return val != null;
        }

        public Integer getInteger(){
            return val;
        }
        public void setInteger(int value){
            this.val = value;
        }
        public void add(NestedInteger ni){
            lists.add(ni);
        }
    }

}
