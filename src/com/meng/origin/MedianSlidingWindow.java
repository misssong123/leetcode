package com.meng.origin;

import java.util.*;

/**
 * 480. 滑动窗口中位数
 *
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 *
 * 例如：
 *
 *     [2,3,4]，中位数是 3
 *     [2,3]，中位数是 (2 + 3) / 2 = 2.5
 *
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *
 *
 *
 * 示例：
 *
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 *
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7      -1
 *  1  3 [-1  -3  5] 3  6  7      -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 *
 *  因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 *
 *
 *
 * 提示：
 *
 *     你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
 *     与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 */
public class MedianSlidingWindow {
    /**
     * 暴力求解
     * @param nums
     * @param k
     * @return
     * 执行用时：2532 ms, 在所有 Java 提交中击败了6.50% 的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了9.46% 的用户
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] result = new double[len - k + 1];
        int[] temp = new int[k];
        int start = k /2 ,end = (k-1)/2;
        for(int i = 0 ; i < len - k + 1 ; i++){
            System.arraycopy(nums,i,temp,0,k);
            Arrays.sort(temp);
            result[i] = ((double)temp[start] + temp[end])/2;
        }
        return result;
    }
    /**
     * 方法一：双优先队列 + 延迟删除
     *
     * 思路与算法
     *
     * 我们可以使用两个优先队列（堆）维护所有的元素，第一个优先队列 small\textit{small}small 是一个大根堆，它负责维护所有元素中较小的那一半；第二个优先队列 large\textit{large}large 是一个小根堆，它负责维护所有元素中较大的那一半。具体地，如果当前需要维护的元素个数为 xxx，那么 small\textit{small}small 中维护了 ⌈x2⌉\lceil \frac{x}{2} \rceil⌈2x​⌉ 个元素，large\textit{large}large 中维护了 ⌊x2⌋\lfloor \frac{x}{2} \rfloor⌊2x​⌋ 个元素，其中 ⌈y⌉\lceil y \rceil⌈y⌉ 和 ⌊y⌋\lfloor y \rfloor⌊y⌋ 分别表示将 yyy 向上取整和向下取整。也就是说：
     *
     *     small\textit{small}small 中的元素个数要么与 large\textit{large}large 中的元素个数相同，要么比 large\textit{large}large 中的元素个数恰好多 111 个。
     *
     * 这样设计的好处在于：当二者包含的元素个数相同时，它们各自的堆顶元素的平均值即为中位数；而当 small\textit{small}small 包含的元素多了一个时，small\textit{small}small 的堆顶元素即为中位数。这样 getMedian()\texttt{getMedian()}getMedian() 就设计完成了。
     *
     * 而对于 insert(num)\texttt{insert(num)}insert(num) 而言，如果当前两个优先队列都为空，那么根据元素个数的要求，我们必须将这个元素加入 small\textit{small}small；如果 small\textit{small}small 非空（显然不会存在 small\textit{small}small 空而 large\textit{large}large 非空的情况），我们就可以将 num\textit{num}num 与 small\textit{small}small 的堆顶元素 top\textit{top}top 比较：
     *
     *     如果 num≤top\textit{num} \leq \textit{top}num≤top，我们就将其加入 small\textit{small}small 中；
     *
     *     如果 num>top\textit{num} > \textit{top}num>top，我们就将其加入 large\textit{large}large 中。
     *
     * 在成功地加入元素 num\textit{num}num 之后，两个优先队列的元素个数可能会变得不符合要求。由于我们只加入了一个元素，那么不符合要求的情况只能是下面的二者之一：
     *
     *     small\textit{small}small 比 large\textit{large}large 的元素个数多了 222 个；
     *
     *     small\textit{small}small 比 large\textit{large}large 的元素个数少了 111 个。
     *
     * 对于第一种情况，我们将 small\textit{small}small 的堆顶元素放入 large\textit{large}large；对于第二种情况，我们将 large\textit{large}large 的堆顶元素放入 small\textit{small}small，这样就可以解决问题了，insert(num)\texttt{insert(num)}insert(num) 也就设计完成了。
     *
     * 然而对于 erase(num)\texttt{erase(num)}erase(num) 而言，设计起来就不是那么容易了，因为我们知道，优先队列是不支持移出非堆顶元素这一操作的，因此我们可以考虑使用「延迟删除」的技巧，即：
     *
     *     当我们需要移出优先队列中的某个元素时，我们只将这个删除操作「记录」下来，而不去真的删除这个元素。当这个元素出现在 small\textit{small}small 或者 large\textit{large}large 的堆顶时，我们再去将其移出对应的优先队列。
     *
     * 「延迟删除」使用到的辅助数据结构一般为哈希表 delayed\textit{delayed}delayed，其中的每个键值对 (num,freq)(\textit{num}, \textit{freq})(num,freq)，表示元素 num\textit{num}num 还需要被删除 freq\textit{freq}freq 次。「优先队列 + 延迟删除」有非常多种设计方式，体现在「延迟删除」的时机选择。在本题解中，我们使用一种比较容易编写代码的设计方式，即：
     *
     *     我们保证在任意操作 insert(num)\texttt{insert(num)}insert(num)，erase(num)\texttt{erase(num)}erase(num)，getMedian()\texttt{getMedian()}getMedian() 完成之后（或者说任意操作开始之前），small\textit{small}small 和 large\textit{large}large 的堆顶元素都是不需要被「延迟删除」的。这样设计的好处在于：我们无需更改 getMedian()\texttt{getMedian()}getMedian() 的设计，只需要略加修改 insert(num)\texttt{insert(num)}insert(num) 即可。
     *
     * 我们首先设计一个辅助函数 prune(heap)\texttt{prune(heap)}prune(heap)，它的作用很简单，就是对 heap\textit{heap}heap 这个优先队列（small\textit{small}small 或者 large\textit{large}large 之一），不断地弹出其需要被删除的堆顶元素，并且减少 delayed\textit{delayed}delayed 中对应项的值。在 prune(heap)\texttt{prune(heap)}prune(heap) 完成之后，我们就可以保证 heap\textit{heap}heap 的堆顶元素是不需要被「延迟删除」的。
     *
     * 这样我们就可以在 prune(heap)\texttt{prune(heap)}prune(heap) 的基础上设计另一个辅助函数 makeBalance()\texttt{makeBalance()}makeBalance()，它的作用即为调整 small\textit{small}small 和 large\textit{large}large 中的元素个数，使得二者的元素个数满足要求。由于有了 erase(num)\texttt{erase(num)}erase(num) 以及「延迟删除」，我们在将一个优先队列的堆顶元素放入另一个优先队列时，第一个优先队列的堆顶元素可能是需要删除的。因此我们就可以用 makeBalance()\texttt{makeBalance()}makeBalance() 将 prune(heap)\texttt{prune(heap)}prune(heap) 封装起来，它的逻辑如下：
     *
     *     如果 small\textit{small}small 和 large\textit{large}large 中的元素个数满足要求，则不进行任何操作；
     *
     *     如果 small\textit{small}small 比 large\textit{large}large 的元素个数多了 222 个，那么我们我们将 small\textit{small}small 的堆顶元素放入 large\textit{large}large。此时 small\textit{small}small 的对应元素可能是需要删除的，因此我们调用 prune(small)\texttt{prune(small)}prune(small)；
     *
     *     如果 small\textit{small}small 比 large\textit{large}large 的元素个数少了 111 个，那么我们将 large\textit{large}large 的堆顶元素放入 small\textit{small}small。此时 large\textit{large}large 的对应的元素可能是需要删除的，因此我们调用 prune(large)\texttt{prune(large)}prune(large)。
     *
     * 此时，我们只需要在原先 insert(num)\texttt{insert(num)}insert(num) 的设计的最后加上一步 makeBalance()\texttt{makeBalance()}makeBalance() 即可。然而对于 erase(num)\texttt{erase(num)}erase(num)，我们还是需要进行一些思考的：
     *
     *     如果 num\textit{num}num 与 small\textit{small}small 和 large\textit{large}large 的堆顶元素都不相同，那么 num\textit{num}num 是需要被「延迟删除」的，我们将其在哈希表中的值增加 111；
     *
     *     否则，例如 num\textit{num}num 与 small\textit{small}small 的堆顶元素相同，那么该元素是可以理解被删除的。虽然我们没有实现「立即删除」这个辅助函数，但只要我们将 num\textit{num}num 在哈希表中的值增加 111，并且调用「延迟删除」的辅助函数 prune(small)\texttt{prune(small)}prune(small)，那么就相当于实现了「立即删除」的功能。
     *
     * 无论是「立即删除」还是「延迟删除」，其中一个优先队列中的元素个数发生了变化（减少了 111），因此我们还需要用 makeBalance()\texttt{makeBalance()}makeBalance() 调整元素的个数。
     *
     * 此时，所有的接口都已经设计完成了。由于 insert(num)\texttt{insert(num)}insert(num) 和 erase(num)\texttt{erase(num)}erase(num) 的最后一步都是 makeBalance()\texttt{makeBalance()}makeBalance()，而 makeBalance()\texttt{makeBalance()}makeBalance() 的最后一步是 prune(heap)\texttt{prune(heap)}prune(heap)，因此我们就保证了任意操作完成之后，small\textit{small}small 和 large\textit{large}large 的堆顶元素都是不需要被「延迟删除」的。
     *
     * 具体实现的细节相对较多，读者可以参考下面的代码和注释进一步理解。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sliding-window-median/solution/hua-dong-chuang-kou-zhong-wei-shu-by-lee-7ai6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：28 ms, 在所有 Java 提交中击败了86.78% 的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了63.36% 的用户
     */
    public double[] medianSlidingWindow1(int[] nums, int k) {
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; ++i) {
            dh.insert(nums[i]);
        }
        double[] ans = new double[nums.length - k + 1];
        ans[0] = dh.getMedian();
        for (int i = k; i < nums.length; ++i) {
            dh.insert(nums[i]);
            dh.erase(nums[i - k]);
            ans[i - k + 1] = dh.getMedian();
        }
        return ans;
    }
}

class DualHeap {
    // 大根堆，维护较小的一半元素
    private PriorityQueue<Integer> small;
    // 小根堆，维护较大的一半元素
    private PriorityQueue<Integer> large;
    // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
    private Map<Integer, Integer> delayed;

    private int k;
    // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
    private int smallSize, largeSize;

    public DualHeap(int k) {
        this.small = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2.compareTo(num1);
            }
        });
        this.large = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num1.compareTo(num2);
            }
        });
        this.delayed = new HashMap<Integer, Integer>();
        this.k = k;
        this.smallSize = 0;
        this.largeSize = 0;
    }

    public double getMedian() {
        return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
    }

    public void insert(int num) {
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            ++smallSize;
        } else {
            large.offer(num);
            ++largeSize;
        }
        makeBalance();
    }

    public void erase(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= small.peek()) {
            --smallSize;
            if (num == small.peek()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.peek()) {
                prune(large);
            }
        }
        makeBalance();
    }

    // 不断地弹出 heap 的堆顶元素，并且更新哈希表
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) {
                    delayed.remove(num);
                }
                heap.poll();
            } else {
                break;
            }
        }
    }

    // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
    private void makeBalance() {
        if (smallSize > largeSize + 1) {
            // small 比 large 元素多 2 个
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            // small 堆顶元素被移除，需要进行 prune
            prune(small);
        } else if (smallSize < largeSize) {
            // large 比 small 元素多 1 个
            small.offer(large.poll());
            ++smallSize;
            --largeSize;
            // large 堆顶元素被移除，需要进行 prune
            prune(large);
        }
    }

    /**
     优先队列（堆）解法

     从朴素解法中我们可以发现，其实我们需要的就是滑动窗口中的第 k / 2 小的值和第 (k - 1) / 2 小的值。

     我们知道滑动窗口求最值的问题，可以使用优先队列来做。

     但这里我们求的是第 k 小的数，而且是需要两个值。还能不能使用优先队列来做呢？

     我们可以维护两个堆：

     一个大根堆维护着滑动窗口中一半较小的值（此时堆顶元素为滑动窗口中的第 (k - 1) / 2 小的值）
     一个小根堆维护着滑动窗口中一半较大的值（此时堆顶元素为滑动窗口中的第 k / 2 小的值）

     滑动窗口的中位数就是两个堆的堆顶元素的平均值。

     实现细节：

     初始化时，先让 k 个元素直接入 right，再从 right 中倒出 k / 2 个到 left 中。这时候可以根据 left 和 right 得到第一个滑动窗口的中位值。

     开始滑动窗口，每次滑动都有一个待添加和待移除的数：

     2.1 根据与右堆的比较觉得是插入哪个堆和从哪个堆移除

     2.2 之后调整两堆的大小（确保只会出现 left.size() == right.size() 和 right.size() - left.size() == 1，对应了窗口长度为偶数或者奇数的情况）

     2.3 根据 left 和 right 得到当前滑动窗口的中位值

     作者：AC_OIer
     链接：https://leetcode-cn.com/problems/sliding-window-median/solution/xiang-jie-po-su-jie-fa-you-xian-dui-lie-mo397/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     * 执行用时：85 ms, 在所有 Java 提交中击败了51.51% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了13.24% 的用户
     *
     */
    public double[] medianSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        int cnt = n - k + 1;
        double[] ans = new double[cnt];
        // 如果是奇数滑动窗口，让 right 的数量比 left 多一个
        PriorityQueue<Integer> left  = new PriorityQueue<>((a,b)->Integer.compare(b,a)); // 滑动窗口的左半部分
        PriorityQueue<Integer> right = new PriorityQueue<>((a,b)->Integer.compare(a,b)); // 滑动窗口的右半部分
        for (int i = 0; i < k; i++) right.add(nums[i]);
        for (int i = 0; i < k / 2; i++) left.add(right.poll());
        ans[0] = getMid(left, right);
        for (int i = k; i < n; i++) {
            // 人为确保了 right 会比 left 多，因此，删除和添加都与 right 比较（left 可能为空）
            int add = nums[i], del = nums[i - k];
            if (add >= right.peek()) {
                right.add(add);
            } else {
                left.add(add);
            }
            if (del >= right.peek()) {
                right.remove(del);
            } else {
                left.remove(del);
            }
            adjust(left, right);
            ans[i - k + 1] = getMid(left, right);
        }
        return ans;
    }
    void adjust(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        while (left.size() > right.size()) right.add(left.poll());
        while (right.size() - left.size() > 1) left.add(right.poll());
    }
    double getMid(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == right.size()) {
            return (left.peek() / 2.0) + (right.peek() / 2.0);
        } else {
            return right.peek() * 1.0;
        }
    }
}
