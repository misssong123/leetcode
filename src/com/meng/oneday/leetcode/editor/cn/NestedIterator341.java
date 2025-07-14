package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

/*
*
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
*/

/**
 * 解答成功:
 * 	执行耗时:5 ms,击败了12.68% 的Java用户
 * 	内存消耗:44.9 MB,击败了91.71% 的Java用户
 */
public class NestedIterator341 implements Iterator<Integer> {
    List <NestedInteger> nestedList;
    Deque<Integer> indexQueue;
    Deque<List<NestedInteger>> valueQueue;
    public NestedIterator341(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        indexQueue = new LinkedList<>();
        valueQueue = new LinkedList<>();
        indexQueue.offerFirst(0);
        valueQueue.offerFirst(nestedList);
        findNextValidNum(true);
    }

    @Override
    public Integer next() {
        return findNextValidNum(false);
    }

    @Override
    public boolean hasNext() {
        return findNextValidNum(true) != Integer.MIN_VALUE;
    }
    //[[]]
    public Integer findNextValidNum(boolean init) {
        if (valueQueue.isEmpty() || indexQueue.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        List<NestedInteger> cur = valueQueue.peek();
        int index = indexQueue.peek();
        //当前数据不符合规范
        if (index >= cur.size()) {
            indexQueue.poll();
            valueQueue.poll();
            if(!indexQueue.isEmpty()){
                indexQueue.offerFirst(indexQueue.poll() + 1);
            }
            return findNextValidNum(init);
        }
        if (cur.isEmpty()){
            indexQueue.offerFirst(indexQueue.poll() + 1);
            return findNextValidNum(init);
        }
        //数字退出
        NestedInteger val = cur.get(index);
        if (val.isInteger()){
            if (!init&&!indexQueue.isEmpty()){
                indexQueue.offerFirst(indexQueue.poll() + 1);
            }
            return val.getInteger();
        }
        //有效列表
        indexQueue.offerFirst(0);
        valueQueue.offerFirst(val.getList());
        return findNextValidNum(init);
    }
}

/**
 * 解答成功:
 * 	执行耗时:2 ms,击败了100.00% 的Java用户
 * 	内存消耗:44.9 MB,击败了83.41% 的Java用户
 */
class NestedIteratorOfficial implements Iterator<Integer> {
    private List<Integer> vals;
    private Iterator<Integer> cur;

    public NestedIteratorOfficial(List<NestedInteger> nestedList) {
        vals = new ArrayList<Integer>();
        dfs(nestedList);
        cur = vals.iterator();
    }

    @Override
    public Integer next() {
        return cur.next();
    }

    @Override
    public boolean hasNext() {
        return cur.hasNext();
    }

    private void dfs(List<NestedInteger> nestedList) {
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()) {
                vals.add(nest.getInteger());
            } else {
                dfs(nest.getList());
            }
        }
    }
}

/**
 * 解答成功:
 * 	执行耗时:3 ms,击败了51.22% 的Java用户
 * 	内存消耗:44.7 MB,击败了99.51% 的Java用户
 */
class NestedIterator implements Iterator<Integer> {
    // 存储列表的当前遍历位置
    private Deque<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<Iterator<NestedInteger>>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        // 由于保证调用 next 之前会调用 hasNext，直接返回栈顶列表的当前元素
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> it = stack.peek();
            if (!it.hasNext()) { // 遍历到当前列表末尾，出栈
                stack.pop();
                continue;
            }
            // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
            NestedInteger nest = it.next();
            if (nest.isInteger()) {
                List<NestedInteger> list = new ArrayList<NestedInteger>();
                list.add(nest);
                stack.push(list.iterator());
                return true;
            }
            stack.push(nest.getList().iterator());
        }
        return false;
    }
}
class NestedInteger {
    public Integer val;
    public List<NestedInteger> lists;
    public boolean isInteger(){
        return val != null;
    }
    public Integer getInteger(){
        return val;
    }
    public List<NestedInteger> getList(){
        return lists;
    }
}


