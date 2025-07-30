package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

class FindMode501 {
    int val = 0 ,n = 0,max = 0;
    List<Integer> list ;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了24.20% 的Java用户
     * @param root
     * @return
     */
    public int[] findMode501(TreeNode root) {
       list = new ArrayList<>();
       dfs(root);
        if (n > max){//替换数据
            list.clear();
            list.add(val);
            max = n;
        }else if(n == max){
            list.add(val);
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private void dfs(TreeNode root) {
        if (root == null){
            return;
        }
        dfs(root.left);
        if(root.val == val){
            n++;
        }else {//新的元素出现
            if (n > max){//替换数据
                list.clear();
                list.add(val);
                max = n;
            }else if(n == max){
                list.add(val);
            }
            val = root.val;
            n = 1;
        }
        dfs(root.right);
    }


    int base, count, maxCount;
    List<Integer> answer = new ArrayList<Integer>();

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了51.09% 的Java用户
     * 	内存消耗:44.2 MB,击败了40.20% 的Java用户
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.left == null) {
                update(cur.val);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if (pre.right == null) {
                pre.right = cur;
                cur = cur.left;
            } else {
                pre.right = null;
                update(cur.val);
                cur = cur.right;
            }
        }
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void update(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }

}
