package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class AccountsMergeMy721 {
    /**
     * 解答成功:
     * 	执行耗时:45 ms,击败了19.95% 的Java用户
     * 	内存消耗:48.3 MB,击败了16.13% 的Java用户
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMergeMy(List<List<String>> accounts) {
        int len = accounts.size();
        //初始化
        int[] cache = new int[len];
        for(int i = 0 ; i <len ; i++){
            cache[i] = i;
        }
        Map<String,Integer> numCache = new HashMap<>();
        //统计
        for(int i= 0 ; i <len ; i++){
            List<String> accountList = accounts.get(i);
            int parent = i;
            for (int j = 1 ; j < accountList.size() ; j++){
                if (numCache.containsKey(accountList.get(j))){
                    Integer num = numCache.get(accountList.get(j));
                    //寻找父节点
                    if (parent != i){
                        cache[find(cache,num)] = parent;
                    }else {
                        parent = find(cache,num);
                        cache[i] = parent;
                    }
                }else {
                    numCache.put(accountList.get(j),i);
                }
            }
        }
        //整合
        Map<String, Set<String>> map = new HashMap<>();
        for(int i = 0 ; i < len ; i++){
            int parent = find(cache, i);
            String s = accounts.get(parent).get(0);
            if (!map.containsKey(s+"#"+parent)){
                map.put(s+"#"+parent,new HashSet<>());
            }
            for(int j = 1 ; j < accounts.get(i).size() ; j++){
                map.get(s+"#"+parent).add(accounts.get(i).get(j));
            }
        }
        //
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : map.entrySet()){
            List<String> temp = new ArrayList<>(entry.getValue());
            Collections.sort(temp);
            temp.add(0,entry.getKey().split("#")[0]);
            res.add(temp);
        }
        return res;
    }
    public int find(int[] root,int x){
        int r = x;
        while (root[r] != r){
            r = root[r];
        }
        return r;
    }

    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了66.12% 的Java用户
     * 	内存消耗:47.5 MB,击败了51.64% 的Java用户
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<String, Integer>();
        Map<String, String> emailToName = new HashMap<String, String>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, emailsCount++);
                    emailToName.put(email, name);
                }
            }
        }
        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                uf.union(firstIndex, nextIndex);
            }
        }
        Map<Integer, List<String>> indexToEmails = new HashMap<Integer, List<String>>();
        for (String email : emailToIndex.keySet()) {
            int index = uf.find(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<String>());
            account.add(email);
            indexToEmails.put(index, account);
        }
        List<List<String>> merged = new ArrayList<List<String>>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<String>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }
}

class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int index1, int index2) {
        parent[find(index2)] = find(index1);
    }

    public int find(int index) {
        if (parent[index] != index) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }
}

