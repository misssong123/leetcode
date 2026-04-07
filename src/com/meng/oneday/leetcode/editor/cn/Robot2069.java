package com.meng.oneday.leetcode.editor.cn;

/**
 * 解答成功:
 * 	执行耗时:56 ms,击败了100.00% 的Java用户
 * 	内存消耗:57 MB,击败了66.67% 的Java用户
 */
class Robot2069 {
    private String[] dirStr = {"East", "North", "West", "South"};
    private int x, y, width, height, index;
    private int total;
    private boolean moved = false; // 标记是否移动过

    public Robot2069(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.index = 0; // 初始 East
        this.total = (width + height - 2) * 2;
    }

    public void step(int num) {
        num %= total;
        while (num > 0) {
            if (index == 0) {
                int step = Math.min(num, width - 1 - x);
                x += step;
                num -= step;
                if (num > 0) index = 1;
            } else if (index == 1) {
                int step = Math.min(num, height - 1 - y);
                y += step;
                num -= step;
                if (num > 0) index = 2;
            } else if (index == 2) {
                int step = Math.min(num, x);
                x -= step;
                num -= step;
                if (num > 0) index = 3;
            } else if (index == 3) {
                int step = Math.min(num, y);
                y -= step;
                num -= step;
                if (num > 0) index = 0;
            }
        }
        // 【关键修正】移动结束后，如果停在拐角，需要根据位置二次校准方向
        // 这一步确保了即使 num 刚好减为 0 停在拐角，方向也能正确切换
        if (x == 0 && y == 0) index = 3; // South
        else if (x == width - 1 && y == 0) index = 0; // East
        else if (x == width - 1 && y == height - 1) index = 1; // North
        else if (x == 0 && y == height - 1) index = 2; // West
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        // 初始状态下（从未移动过），即使在(0,0)也是East
        // 如果移动了一圈回到(0,0)，在step里已经被修正为South了
        return dirStr[index];
    }
}

/**
 * 解答成功:
 * 	执行耗时:59 ms,击败了71.43% 的Java用户
 * 	内存消耗:62.5 MB,击败了9.52% 的Java用户
 */
class RobotOther {
    private int w, h, s;

    public RobotOther(int width, int height) {
        w = width;
        h = height;
        s = 0;
    }

    public void step(int num) {
        // 由于机器人只能走外圈，那么走 (w+h-2)*2 步后会回到起点
        // 把 s 取模调整到 [1, (w+h-2)*2]，这样不需要特判 s == 0 时的方向
        s = (s + num - 1) % ((w + h - 2) * 2) + 1;
    }

    public int[] getPos() {
        Object[] t = getState();
        return new int[]{(int) t[0], (int) t[1]};
    }

    public String getDir() {
        Object[] t = getState();
        return (String) t[2];
    }

    private Object[] getState() {
        if (s < w) {
            return new Object[]{s, 0, "East"};
        } else if (s < w + h - 1) {
            return new Object[]{w - 1, s - w + 1, "North"};
        } else if (s < w * 2 + h - 2) {
            return new Object[]{w * 2 + h - s - 3, h - 1, "West"};
        } else {
            return new Object[]{0, (w + h) * 2 - s - 4, "South"};
        }
    }
}
