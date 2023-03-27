package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class GetFolderNames1487 {
    /**
     * 执行用时：
     * 85 ms
     * , 在所有 Java 提交中击败了
     * 5.17%
     * 的用户
     * 内存消耗：
     * 53.5 MB
     * , 在所有 Java 提交中击败了
     * 82.76%
     * 的用户
     * 通过测试用例：
     * 33 / 33
     * @param names
     * @return
     */
    public String[] getFolderNames(String[] names) {
        int len = names.length;
        String[] res = new String[len];
        Map<String,Integer> cache = new HashMap<>();
        int index = 0;
        for(String name : names){
            if (cache.get(name)==null){
                res[index] = name;
                cache.put(name,1);
            }else {
                Integer num = cache.get(name);
                while (cache.get(name+"("+num+")")!=null){
                    num++;
                }
                cache.put(name+"("+num+")",1);
                cache.put(name,num+1);
                res[index] = name+"("+num+")";
            }
            index++;
        }
        return res;
    }

    /**
     *执行用时：
     * 59 ms
     * , 在所有 Java 提交中击败了
     * 42.24%
     * 的用户
     * 内存消耗：
     * 53.8 MB
     * , 在所有 Java 提交中击败了
     * 80.17%
     * 的用户
     * 通过测试用例：
     * 33 / 33
     * @param names
     * @return
     */
    public String[] getFolderNames1(String[] names) {
        Map<String, Integer> index = new HashMap<String, Integer>();
        int n = names.length;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            String name = names[i];
            if (!index.containsKey(name)) {
                res[i] = name;
                index.put(name, 1);
            } else {
                int k = index.get(name);
                while (index.containsKey(addSuffix(name, k))) {
                    k++;
                }
                res[i] = addSuffix(name, k);
                index.put(name, k + 1);
                index.put(addSuffix(name, k), 1);
            }
        }
        return res;
    }

    public String addSuffix(String name, int k) {
        return name + "(" + k + ")";
    }

}

