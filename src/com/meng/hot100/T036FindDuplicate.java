package com.meng.hot100;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *
 *
 * 进阶：
 *
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 */
public class T036FindDuplicate {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 98.79%
     * 的用户
     * 内存消耗：
     * 60.3 MB
     * , 在所有 Java 提交中击败了
     * 5.01%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int[] index = new int[n];
        for(int num : nums){
            if (index[num]==1){
                return num;
            }
            index[num]++;
        }
        return 0;
    }

    public static void main(String[] args) {
        T036FindDuplicate demo = new T036FindDuplicate();
        int[] nums = {2,2,2};
        System.out.println(demo.findDuplicate(nums));
    }

    /**
     * 方法一：二分查找
     * 思路和算法
     *
     * 我们定义 \textit{cnt}[i]cnt[i] 表示 \textit{nums}nums 数组中小于等于 ii 的数有多少个，假设我们重复的数是 \textit{target}target，那么 [1,\textit{target}-1][1,target−1]里的所有数满足 \textit{cnt}[i]\le icnt[i]≤i，[target,n][target,n] 里的所有数满足 \textit{cnt}[i]>icnt[i]>i，具有单调性。
     *
     * 以示例 1 为例，我们列出每个数字的 \textit{cnt}cnt 值：
     *
     * nums	1	2	3	4
     * cnt	1	3	4	5
     * 示例中重复的整数是 22，我们可以看到 [1,1][1,1] 中的数满足 \textit{cnt}[i]\le icnt[i]≤i，[2,4][2,4] 中的数满足 \textit{cnt}[i]>icnt[i]>i 。
     *
     * 如果知道 \textit{cnt}[]cnt[] 数组随数字 ii 逐渐增大具有单调性（即 \textit{target}target 前 \textit{cnt}[i]\le icnt[i]≤i，\textit{target}target 后 \textit{cnt}[i]>icnt[i]>i），那么我们就可以直接利用二分查找来找到重复的数。
     *
     * 但这个性质一定是正确的吗？考虑 \textit{nums}nums 数组一共有 n+1n+1 个位置，我们填入的数字都在 [1,n][1,n] 间，有且只有一个数重复放了两次以上。对于所有测试用例，考虑以下两种情况：
     *
     * 如果测试用例的数组中 \textit{target}target 出现了两次，其余的数各出现了一次，这个时候肯定满足上文提及的性质，因为小于 \textit{target}target 的数 ii 满足 \textit{cnt}[i]=icnt[i]=i，大于等于 \textit{target}target 的数 jj 满足 cnt[j]=j+1cnt[j]=j+1。
     *
     * 如果测试用例的数组中 \textit{target}target 出现了三次及以上，那么必然有一些数不在 \textit{nums}nums 数组中了，这个时候相当于我们用 \textit{target}target 去替换了这些数，我们考虑替换的时候对 \textit{cnt}[]cnt[] 数组的影响。如果替换的数 ii 小于 \textit{target}target ，那么 [i, \textit{target}-1][i,target−1] 的 \textit{cnt}cnt 值均减一，其他不变，满足条件。如果替换的数 jj 大于等于 \textit{target}target，那么 [\textit{target}, j-1][target,j−1] 的 \textit{cnt}cnt 值均加一，其他不变，亦满足条件。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 26 ms
     * , 在所有 Java 提交中击败了
     * 27.17%
     * 的用户
     * 内存消耗：
     * 59 MB
     * , 在所有 Java 提交中击败了
     * 28.08%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     */
    public int findDuplicate1(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    /**
     * 方法二：二进制
     * 思路和算法
     *
     * 这个方法我们来将所有数二进制展开按位考虑如何找出重复的数，如果我们能确定重复数每一位是 11 还是 00 就可以按位还原出重复的数是什么。
     *
     * 考虑到第 ii 位，我们记 \textit{nums}nums 数组中二进制展开后第 ii 位为 11 的数有 xx 个，数字 [1,n][1,n] 这 nn 个数二进制展开后第 ii 位为 11 的数有 yy 个，那么重复的数第 ii 位为 11 当且仅当 x>yx>y。
     *
     * 仍然以示例 1 为例，如下的表格列出了每个数字二进制下每一位是 11 还是 00 以及对应位的 xx 和 yy 是多少：
     *
     *  	1	3	4	2	2	x	y
     * 第 0 位	1	1	0	0	0	2	2
     * 第 1 位	0	1	0	1	1	3	2
     * 第 2 位	0	0	1	0	0	1	1
     * 那么按之前说的我们发现只有第 11 位 x>yx>y ，所以按位还原后 \textit{target}=(010)_2=(2)_{10}target=(010)
     * 2
     * ​
     *  =(2)
     * 10
     * ​
     *  ，符合答案。
     *
     * 正确性的证明其实和方法一类似，我们可以按方法一的方法，考虑不同示例数组中第 ii 位 11 的个数 xx 的变化：
     *
     * 如果测试用例的数组中 \textit{target}target 出现了两次，其余的数各出现了一次，且 \textit{target}target 的第 ii 位为 11，那么 \textit{nums}nums 数组中第 ii 位 11 的个数 xx 恰好比 yy 大一。如果\textit{target}target 的第 ii 位为 00，那么两者相等。
     * 如果测试用例的数组中 \textit{target}target 出现了三次及以上，那么必然有一些数不在 \textit{nums}nums 数组中了，这个时候相当于我们用 \textit{target}target 去替换了这些数，我们考虑替换的时候对 xx 的影响：
     * 如果被替换的数第 ii 位为 11，且 \textit{target}target 第 ii 位为 11：xx 不变，满足 x>yx>y。
     * 如果被替换的数第 ii 位为 00，且 \textit{target}target 第 ii 位为 11：xx 加一，满足 x>yx>y。
     * 如果被替换的数第 ii 位为 11，且 \textit{target}target 第 ii 位为 00：xx 减一，满足 x\le yx≤y。
     * 如果被替换的数第 ii 位为 00，且 \textit{target}target 第 ii 位为 00：xx 不变，满足 x\le yx≤y。
     * 也就是说如果 \textit{target}target 第 ii 位为 11，那么每次替换后只会使 xx 不变或增大，如果为 00，只会使 xx 不变或减小，始终满足 x>yx>y 时 \textit{target}target 第 ii 位为 11，否则为 00，因此我们只要按位还原这个重复的数即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 46 ms
     * , 在所有 Java 提交中击败了
     * 5.26%
     * 的用户
     * 内存消耗：
     * 58.8 MB
     * , 在所有 Java 提交中击败了
     * 49.79%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     */
    public int findDuplicate2(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    /**
     * 方法三：快慢指针
     * 预备知识
     *
     * 本方法需要读者对 「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解，它是一个检测链表是否有环的算法，LeetCode 中相关例题有 141. 环形链表，142. 环形链表 II。
     *
     * 思路和算法
     *
     * 我们对 \textit{nums}nums 数组建图，每个位置 ii 连一条 i\rightarrow \textit{nums}[i]i→nums[i] 的边。由于存在的重复的数字 \textit{target}target，因此 \textit{target}target 这个位置一定有起码两条指向它的边，因此整张图一定存在环，且我们要找到的 \textit{target}target 就是这个环的入口，那么整个问题就等价于 142. 环形链表 II。
     *
     * 我们先设置慢指针 \textit{slow}slow 和快指针 \textit{fast}fast ，慢指针每次走一步，快指针每次走两步，根据「Floyd 判圈算法」两个指针在有环的情况下一定会相遇，此时我们再将 \textit{slow}slow 放置起点 00，两个指针每次同时移动一步，相遇的点就是答案。
     *
     *
     * 1 / 25
     *
     * 这里简单解释为什么后面将 \textit{slow}slow 放置起点后移动相遇的点就一定是答案了。假设环长为 LL，从起点到环的入口的步数是 aa，从环的入口继续走 bb 步到达相遇位置，从相遇位置继续走 cc 步回到环的入口，则有 b+c=Lb+c=L，其中 LL、aa、bb、cc 都是正整数。根据上述定义，慢指针走了 a+ba+b 步，快指针走了 2(a+b)2(a+b) 步。从另一个角度考虑，在相遇位置，快指针比慢指针多走了若干圈，因此快指针走的步数还可以表示成 a+b+kLa+b+kL，其中 kk 表示快指针在环上走的圈数。联立等式，可以得到
     *
     * 2(a+b)=a+b+kL
     * 2(a+b)=a+b+kL
     *
     * 解得 a=kL-ba=kL−b，整理可得
     *
     * a=(k-1)L+(L-b)=(k-1)L+c
     * a=(k−1)L+(L−b)=(k−1)L+c
     *
     * 从上述等式可知，如果慢指针从起点出发，快指针从相遇位置出发，每次两个指针都移动一步，则慢指针走了 aa 步之后到达环的入口，快指针在环里走了 k-1k−1 圈之后又走了 cc 步，由于从相遇位置继续走 cc 步即可回到环的入口，因此快指针也到达环的入口。两个指针在环的入口相遇，相遇点就是答案。
     *
     * C++JavaScriptJavaGolang
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 92.70%
     * 的用户
     * 内存消耗：
     * 58.4 MB
     * , 在所有 Java 提交中击败了
     * 81.58%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     */
    public int findDuplicate3(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }



}
