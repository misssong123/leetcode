package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 解答成功:
 * 	执行耗时:54 ms,击败了61.29% 的Java用户
 * 	内存消耗:50.5 MB,击败了45.48% 的Java用户
 */
class BrowserHistory1472 {
    List<String> elements;
    int size,index;
    public BrowserHistory1472(String homepage) {
        elements = new ArrayList<>();
        elements.add(homepage);
        size = 1;
        index = 0;
    }
    public void visit(String url) {
        //存放元素
        elements.add(index+1,url);
        index ++;
        size = index+1;
    }

    public String back(int steps) {
        index = Math.max(0,index-steps);
        return elements.get(index);
    }

    public String forward(int steps) {
        index = Math.min(size-1,index+steps);
        return elements.get(index);
    }
}

/**
 * 解答成功:
 * 	执行耗时:54 ms,击败了61.29% 的Java用户
 * 	内存消耗:49.2 MB,击败了90.64% 的Java用户
 */
class BrowserHistory {
    private final List<String> history = new ArrayList<>();
    private int cur = 0; // 当前页面是 history[cur]

    public BrowserHistory(String homepage) {
        history.add(homepage);
    }

    public void visit(String url) {
        cur++;
        history.subList(cur, history.size()).clear(); // 把浏览历史前进的记录全部删除
        history.add(url); // 从当前页跳转访问 url 对应的页面
    }

    public String back(int steps) {
        cur = Math.max(cur - steps, 0); // 后退 steps 步
        return history.get(cur);
    }

    public String forward(int steps) {
        cur = Math.min(cur + steps, history.size() - 1); // 前进 steps 步
        return history.get(cur);
    }
}

