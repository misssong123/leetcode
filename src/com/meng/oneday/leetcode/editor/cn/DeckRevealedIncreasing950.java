package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class DeckRevealedIncreasing950 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了36.84% 的Java用户
     * 	内存消耗:45 MB,击败了45.34% 的Java用户
     * @param deck
     * @return
     */
    public int[] deckRevealedIncreasing950(int[] deck) {
        //排序
        Arrays.sort(deck);
        //
        Deque<Integer> deque = new LinkedList<>();
        for(int i = deck.length - 1 ; i >= 0 ; i--){
            if(deque.size() == 0){
                deque.addFirst(deck[i]);
            }else{
                deque.addFirst(deque.pollLast());
                deque.addFirst(deck[i]);
            }
        }
        for(int i = 0 ; i < deck.length ; i++){
            deck[i] = deque.pollFirst();
        }
        return deck;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了76.52% 的Java用户
     * 	内存消耗:45.4 MB,击败了12.95% 的Java用户
     * @param deck
     * @return
     */
    public int[] deckRevealedIncreasing(int[] deck) {
        int N = deck.length;
        Deque<Integer> index = new LinkedList<>();
        for (int i = 0; i < N; ++i)
            index.add(i);

        int[] ans = new int[N];
        Arrays.sort(deck);
        for (int card: deck) {
            ans[index.pollFirst()] = card;
            if (!index.isEmpty())
                index.add(index.pollFirst());
        }

        return ans;
    }

}
