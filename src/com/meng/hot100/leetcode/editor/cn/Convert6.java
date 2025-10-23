package com.meng.hot100.leetcode.editor.cn;

class Convert6 {
    private static final int[][] dirs = {{1,0},{-1,1},{-1,0}};

    /**
     * 解答成功:
     * 	执行耗时:54 ms,击败了5.76% 的Java用户
     * 	内存消耗:46.7 MB,击败了5.01% 的Java用户
     * @param s
     * @param numRows
     * @return
     */
    public String convert6(String s, int numRows) {
        if (numRows == 1){
            return s;
        }
        char[][] chars = new char[numRows][s.length()];
        int x = 0 , y = 0,index = 0;
        for(char c : s.toCharArray()){
            chars[x][y] = c;
            while (x + dirs[index][0] >= numRows || x + dirs[index][0] < 0 ||
                    y + dirs[index][1] < 0 || y + dirs[index][1] >= s.length()){
                index = (index + 1) % 3;
            }
            x += dirs[index][0];
            y += dirs[index][1];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < numRows ; i++){
            for (int j = 0 ; j < s.length() ; j++){
                if (chars[i][j] != '\0'){
                    sb.append(chars[i][j]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:35 ms,击败了14.56% 的Java用户
     * 	内存消耗:46.2 MB,击败了5.94% 的Java用户
     * @param s
     * @param numRows
     * @return
     */
    public String convertOfficial(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.52% 的Java用户
     * 	内存消耗:43.8 MB,击败了94.97% 的Java用户
     * @param s
     * @param numRows
     * @return
     */
    public String convertPro(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int t = numRows * 2 - 2;
        int pre = 0;
        //首行
        while (pre < s.length()){
            sb.append(s.charAt(pre));
            pre += t;
        }
        //中间行
        for(int i = 1 ; i < numRows - 1 ; i++){
            pre = i;
            int step = 0;
            int num = 1;
            while (pre < s.length()){
                sb.append(s.charAt(pre));
                if (step == 0){
                    pre = num * t - i;
                    step = 1;
                }else{
                    pre = num * t + i;
                    step = 0;
                    num++;
                }
            }
        }
        //尾行
        pre = numRows - 1;
        while (pre < s.length()){
            sb.append(s.charAt(pre));
            pre += t;
        }
        return sb.toString();
    }
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了88.38% 的Java用户
     * 	内存消耗:44 MB,击败了75.72% 的Java用户
     */
    public String convert(String s, int numRows) {
        // 特殊情况：行数为1时，直接返回原字符串
        if (numRows == 1) {
            return s;
        }

        int n = s.length();
        // 计算周期长度：2*numRows - 2（竖列+斜列的总长度）
        int cycle = 2 * numRows - 2;
        // 用StringBuilder存储每行的字符
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        for (int i = 0; i < n; i++) {
            // 计算当前字符在周期内的偏移量
            int offset = i % cycle;
            // 根据偏移量确定所在行（斜列部分用对称位置计算）
            int row = offset < numRows ? offset : cycle - offset;
            // 将字符加入对应行
            rows[row].append(s.charAt(i));
        }

        // 拼接所有行的字符，得到最终结果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }
}
