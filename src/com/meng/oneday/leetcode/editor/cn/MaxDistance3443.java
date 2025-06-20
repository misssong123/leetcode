package com.meng.oneday.leetcode.editor.cn;

class MaxDistance3443 {
    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.7 MB,击败了79.17% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public int maxDistance3443(String s, int k) {
        int[] arr = new int[26];
        int xE = 'E'-'A',xW = 'W'-'A',yN = 'N'-'A',yS = 'S'-'A';
        int res = 0;
        for(char c : s.toCharArray()){
            int temp = 0;
            arr[c - 'A']++;
            //计算横坐标最最小值
            int changeNum = Math.min(Math.min(arr[xE],arr[xW]),k);
            temp += Math.abs(arr[xE] - arr[xW]) + 2*changeNum;
            //计算纵坐标坐标最最小值
            changeNum = Math.min(Math.min(arr[yN],arr[yS]),k-changeNum);
            temp += Math.abs(arr[yN] - arr[yS]) + 2*changeNum;
            res = Math.max(res,temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:45 ms,击败了81.25% 的Java用户
     * 	内存消耗:44.8 MB,击败了37.50% 的Java用户
     */
    private int left;
    public int maxDistanceOther1(String s, int k) {
        int ans = 0;
        int[] cnt = new int['X']; // 'W' + 1 = 'X'
        for (char ch : s.toCharArray()) {
            cnt[ch]++;
            left = k;
            ans = Math.max(ans, f(cnt['N'], cnt['S']) + f(cnt['E'], cnt['W']));
        }
        return ans;
    }

    private int f(int a, int b) {
        int d = Math.min(Math.min(a, b), left);
        left -= d;
        return Math.abs(a - b) + d * 2;
    }

    /**
     * 解答成功:
     * 	执行耗时:58 ms,击败了72.92% 的Java用户
     * 	内存消耗:44.8 MB,击败了45.83% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public int maxDistanceOther2(String s, int k) {
        int ans = 0, x = 0, y = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'N') y++;
            else if (c == 'S') y--;
            else if (c == 'E') x++;
            else x--;
            ans = Math.max(ans, Math.min(Math.abs(x) + Math.abs(y) + k * 2, i + 1));
        }
        return ans;
    }

}
