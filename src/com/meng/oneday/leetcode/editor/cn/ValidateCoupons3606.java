package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class ValidateCoupons3606 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了67.21% 的Java用户
     * 	内存消耗:46.7 MB,击败了11.48% 的Java用户
     * @param code
     * @param businessLine
     * @param isActive
     * @return
     */
    public List<String> validateCoupons3606(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;
        List<int[]> cache = new ArrayList<>(n);
        Map<String,Integer> lineCache = new HashMap<>();
        lineCache.put("electronics",1);
        lineCache.put("grocery",2);
        lineCache.put("pharmacy",3);
        lineCache.put("restaurant",4);
        for(int i = 0 ; i < n ; i++){
            if(isActive[i] && lineCache.containsKey(businessLine[i])){
                //校验code[i]是否符合规则
                boolean addFlag = !code[i].isEmpty();
                if (addFlag){
                    for(char c : code[i].toCharArray()){
                        if (!Character.isLetter(c) && !Character.isDigit(c) && c != '_'){
                            addFlag = false;
                            break;
                        }
                    }
                }
                if (addFlag){
                    cache.add(new int[]{i,lineCache.get(businessLine[i])});
                }
            }
        }
        cache.sort((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return code[a[0]].compareTo(code[b[0]]);
        });
        List<String> res = new ArrayList<>(cache.size());
        for(int[] arr : cache){
            res.add(code[arr[0]]);
        }
        return res;
    }
    private static final Map<String, Integer> BUSINESS_LINE_TO_CATEGORY = new HashMap<>();
        static {
            BUSINESS_LINE_TO_CATEGORY.put("electronics", 0);
            BUSINESS_LINE_TO_CATEGORY.put("grocery", 1);
            BUSINESS_LINE_TO_CATEGORY.put("pharmacy", 2);
            BUSINESS_LINE_TO_CATEGORY.put("restaurant", 3);
        }
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了85.25% 的Java用户
     * 	内存消耗:46.3 MB,击败了36.07% 的Java用户
     * @param code
     * @param businessLine
     * @param isActive
     * @return
     */
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String>[] groups = new ArrayList[BUSINESS_LINE_TO_CATEGORY.size()];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (int i = 0; i < code.length; i++) {
            String s = code[i];
            Integer category = BUSINESS_LINE_TO_CATEGORY.get(businessLine[i]);
            if (category != null && isActive[i] && isValid(s)) {
                groups[category].add(s); // 相同类别的优惠码分到同一组
            }
        }

        List<String> ans = new ArrayList<>();
        for (List<String> g : groups) {
            Collections.sort(g); // 每一组内部排序
            ans.addAll(g);
        }
        return ans;
    }

    // 检查字符串是否非空，只包含字母、数字和下划线
    private boolean isValid(String s) {
        for (char c : s.toCharArray()) {
            if (c != '_' && !Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return !s.isEmpty();
    }

}
