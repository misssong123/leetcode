package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 解答成功:
 * 	执行耗时:4 ms,击败了71.81% 的Java用户
 * 	内存消耗:41.7 MB,击败了5.37% 的Java用户
 */
class PeekingIterator284 implements Iterator<Integer> {
	List<Integer> cache;
	int index = 0;
	public PeekingIterator284(Iterator<Integer> iterator) {
		cache = new ArrayList<>();
		while (iterator.hasNext()){
			cache.add(iterator.next());
		}
	}
	
	public Integer peek() {
        return cache.get(index);
	}

	@Override
	public Integer next() {
	    return cache.get(index++);
	}
	
	@Override
	public boolean hasNext() {
	    return index < cache.size();
	}
}

/**
 * 解答成功:
 * 	执行耗时:4 ms,击败了71.81% 的Java用户
 * 	内存消耗:41.5 MB,击败了42.95% 的Java用户
 */
class PeekingIterator implements Iterator<Integer> {
	Iterator<Integer> iter;
	Integer next;
	public PeekingIterator(Iterator<Integer> iterator) {
		iter = iterator;
		if (iter.hasNext()) next = iter.next();
	}

	public Integer peek() {
		return next;
	}

	@Override
	public Integer next() {
		Integer ans = next;
		next = iter.hasNext() ? iter.next() : null;
		return ans;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}
}