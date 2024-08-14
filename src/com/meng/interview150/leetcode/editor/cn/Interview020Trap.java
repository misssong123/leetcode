import java.util.Deque;
import java.util.LinkedList;

class Interview020Trap {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了69.35% 的Java用户
     * 	内存消耗:45.3 MB,击败了39.35% 的Java用户
     * 1.分别求取0到当前位置的最大高度，以及最后以为到当前位置的最大高度
     * 2.求取两个值的最小值
     * 3.减去当前位置的高度，即为当前位置可以存储的水量
     * @param height
     * @return
     */
    public int trapMy(int[] height) {
        int len = height.length;
        int[] preMax = new int[len];
        int ans = 0;
        preMax[0] = height[0];
        for(int i = 1;i < len;i++){
            preMax[i] = Math.max(preMax[i-1],height[i]);
        }
        int max = height[len-1];
        for (int i = len-2; i >= 0 ; i--) {
            max = Math.max(max,height[i]);
            ans += Math.min(preMax[i],max) - height[i];
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了22.68% 的Java用户
     * 	内存消耗:44.8 MB,击败了69.65% 的Java用户
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

}
