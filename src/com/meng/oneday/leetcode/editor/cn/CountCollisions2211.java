package com.meng.oneday.leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

class CountCollisions2211 {
    /**
     * 解答成功:
     * 	执行耗时:39 ms,击败了17.65% 的Java用户
     * 	内存消耗:47.5 MB,击败了5.88% 的Java用户
     * @param directions
     * @return
     */
    public int countCollisions2211(String directions) {
        int res = 0;
        Deque<Character> quque = new LinkedList<>();
        for(char c : directions.toCharArray()){
            if (c == 'R'){
                while (!quque.isEmpty() && quque.peek() != 'R'){
                    quque.pop();
                }
                quque.add(c);
            }else if (c == 'L'){
                if (quque.isEmpty()){
                    continue;
                }else if(quque.peek() == 'S'){
                    res += 1;
                }else {
                    quque.pop();
                    res+=2;
                    while (!quque.isEmpty() && quque.peek() == 'R'){
                        quque.pop();
                        res++;
                    }
                    quque.add('S');
                }
            }else if (c == 'S'){
                if (quque.isEmpty()){
                    quque.add(c);
                }else if (quque.peek() == 'S'){
                    continue;
                }else if (quque.peek() == 'R'){
                    while (!quque.isEmpty() && quque.peek() == 'R'){
                        quque.pop();
                        res++;
                    }
                    quque.add('S');
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了98.04% 的Java用户
     * 	内存消耗:46.8 MB,击败了27.45% 的Java用户
     * @param s
     * @return
     */
    public int countCollisions(String s) {
        int n = s.length();

        // 前缀向左的车不会发生碰撞
        int l = 0;
        while (l < n && s.charAt(l) == 'L') {
            l++;
        }

        // 后缀向右的车不会发生碰撞
        int r = n;
        while (r > l && s.charAt(r - 1) == 'R') {
            r--;
        }

        // 剩下非静止的车必然会碰撞
        int cnt = 0;
        for (int i = l; i < r; i++) {
            if (s.charAt(i) != 'S') {
                cnt++;
            }
        }
        return cnt;
    }

}
