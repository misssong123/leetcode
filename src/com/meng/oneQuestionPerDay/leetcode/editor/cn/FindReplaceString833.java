package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class FindReplaceString833 {
    /**
     * 详情
     * 1ms
     * 击败 95.15%使用 Java 的用户
     * 内存
     * 详情
     * 39.37mb
     * 击败 50.49%使用 Java 的用户
     * @param s
     * @param indices
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int len = s.length();
        int[] index = new int[len];
        Arrays.fill(index, -1);
        //记录顺序避免覆盖
        for(int i = 0 ; i < indices.length ; i++){
            index[indices[i]] = i;
        }
        StringBuffer sb = new StringBuffer();
        int pre = 0;
        int n = 0;
        while (n < len){
            //寻找匹配的位置
            while (n < len && index[n] == -1){
                n++;
            }
            if (n == len){
                break;
            }
            //计算开始操作的字符位置
            int start = indices[index[n]];
            int num = index[n];
            //添加不需要替换的字符
            sb.append(s, pre, start);
            //查询当前位置字符是否可以替换
            if(s.startsWith(sources[num], start)){
                sb.append(targets[num]);
            }else {
                sb.append(s, start, start+sources[num].length());
            }
            pre = start + sources[num].length();
            n++;
        }
        sb.append(s, pre, len);
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "jjievdtjfb";
        int [] indexes = {4,6,1};
        String [] sources = {"md","tjgb","jf"};
        String [] targets = {"foe","oov","e"};
        //jjjievievdtevdtjfb
        //jjievdtjfb
        FindReplaceString833 demo = new FindReplaceString833();
        System.out.println(demo.findReplaceString(s,indexes,sources,targets));
    }

    /**
     * 详情
     * 3ms
     * 击败 28.16%使用 Java 的用户
     * 内存
     * 详情
     * 39.22mb
     * 击败 79.61%使用 Java 的用户
     * @param s
     * @param indices
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceString1(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length(), m = indices.length;
        List<Integer> ops = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ops.add(i);
        }
        ops.sort((i, j) -> indices[i] - indices[j]);
        StringBuilder ans = new StringBuilder();
        int pt = 0;
        for (int i = 0; i < n;) {
            while (pt < m && indices[ops.get(pt)] < i) {
                pt++;
            }
            boolean succeed = false;
            while (pt < m && indices[ops.get(pt)] == i) {
                if (s.substring(i, Math.min(i + sources[ops.get(pt)].length(), n)).equals(sources[ops.get(pt)])) {
                    succeed = true;
                    break;
                }
                pt++;
            }
            if (succeed) {
                ans.append(targets[ops.get(pt)]);
                i += sources[ops.get(pt)].length();
            } else {
                ans.append(s.charAt(i));
                i++;
            }
        }
        return ans.toString();
    }

    /**
     *详情
     * 2ms
     * 击败 69.90%使用 Java 的用户
     * 内存
     * 详情
     * 39.62mb
     * 击败 15.53%使用 Java 的用户
     * @param s
     * @param indices
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceString2(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length(), m = indices.length;

        Map<Integer, List<Integer>> ops = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < m; ++i) {
            ops.putIfAbsent(indices[i], new ArrayList<Integer>());
            ops.get(indices[i]).add(i);
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n;) {
            boolean succeed = false;
            if (ops.containsKey(i)) {
                for (int pt : ops.get(i)) {
                    if (s.substring(i, Math.min(i + sources[pt].length(), n)).equals(sources[pt])) {
                        succeed = true;
                        ans.append(targets[pt]);
                        i += sources[pt].length();
                        break;
                    }
                }
            }
            if (!succeed) {
                ans.append(s.charAt(i));
                ++i;
            }
        }
        return ans.toString();
    }

}

