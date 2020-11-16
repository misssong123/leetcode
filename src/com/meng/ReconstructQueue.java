package com.meng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReconstructQueue {
    /**
     * 思路：排序+插入
     * 1.将原数据按照身高降序，人数升序进行排列
     * 2.只需要将排序后的数据依次插入到对应人数的下标位置即可
     * @param people
     * @return
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.62% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了87.34% 的用户
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o2[0]-o1[0];
                else
                    return o1[1] - o2[1];
            }
        });
        List<int[]> list = new ArrayList<>();
        for(int[] p : people)
            list.add(p[1],p);
        for(int i = 0 ; i < list.size();i++)
            people[i]= list.get(i);
        return people;
    }
    /**
     * 官方解法
     * 方法一：从低到高考虑
     *
     * 思路与算法
     *
     * 当每个人的身高都不相同时，如果我们将他们按照身高从小到大进行排序，那么就可以很方便地还原出原始的队列了。
     *
     * 为了叙述方便，我们设人数为 nnn，在进行排序后，它们的身高依次为 h0,h1,⋯ ,hn−1h_0, h_1, \cdots, h_{n-1}h0​,h1​,⋯,hn−1​，且排在第 iii 个人前面身高大于 hih_ihi​ 的人数为 kik_iki​。如果我们按照排完序后的顺序，依次将每个人放入队列中，那么当我们放入第 iii 个人时：
     *
     *     第 0,⋯ ,i−10, \cdots, i-10,⋯,i−1 个人已经在队列中被安排了位置，并且他们无论站在哪里，对第 iii 个人都没有任何影响，因为他们都比第 iii 个人矮；
     *
     *     而第 i+1,⋯ ,n−1i+1, \cdots, n-1i+1,⋯,n−1 个人还没有被放入队列中，但他们只要站在第 iii 个人的前面，就会对第 iii 个人产生影响，因为他们都比第 iii 个人高。
     *
     * 如果我们在初始时建立一个包含 nnn 个位置的空队列，而我们每次将一个人放入队列中时，会将一个「空」位置变成「满」位置，那么当我们放入第 iii 个人时，我们需要给他安排一个「空」位置，并且这个「空」位置前面恰好还有 kik_iki​ 个「空」位置，用来安排给后面身高更高的人。也就是说，第 iii 个人的位置，就是队列中从左往右数第 ki+1k_i+1ki​+1 个「空」位置。
     *
     * 那么如果有身高相同的人，上述 kik_iki​ 定义中的大于就与题目描述中要求的大于等于不等价了，此时应该怎么修改上面的方法呢？我们可以这样想，如果第 iii 个人和第 jjj 个人的身高相同，即 hi=hjh_i = h_jhi​=hj​，那么我们可以把在队列中处于较后位置的那个人的身高减小一点点。换句话说，对于某一个身高值 hhh，我们将队列中第一个身高为 hhh 的人保持不变，第二个身高为 hhh 的人的身高减少 δ\deltaδ，第三个身高为 hhh 的人的身高减少 2δ2\delta2δ，以此类推，其中 δ\deltaδ 是一个很小的常数，它使得任何身高为 hhh 的人不会与其它（身高不为 hhh 的）人造成影响。
     *
     * 如何找到第一个、第二个、第三个身高为 hhh 的人呢？我们可以借助 kkk 值，可以发现：当 hi=hjh_i=h_jhi​=hj​ 时，如果 ki>kjk_i > k_jki​>kj​，那么说明 iii 一定相对于 jjj 在队列中处于较后的位置（因为在第 jjj 个人之前比他高的所有人，一定都比第 iii 个人要高），按照修改之后的结果，hih_ihi​ 略小于 hjh_jhj​，第 iii 个人在排序后应该先于第 jjj 个人被放入队列。因此，我们不必真的去对身高进行修改，而只需要按照 hih_ihi​ 为第一关键字升序，kik_iki​ 为第二关键字降序进行排序即可。此时，具有相同身高的人会按照它们在队列中的位置逆序进行排列，也就间接实现了上面将身高减少 δ\deltaδ 这一操作的效果。
     *
     * 这样一来，我们只需要使用一开始提到的方法，将第 iii 个人放入队列中的第 ki+1k_i+1ki​+1 个空位置，即可得到原始的队列。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/gen-ju-shen-gao-zhong-jian-dui-lie-by-leetcode-sol/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：22 ms, 在所有 Java 提交中击败了9.38% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了91.11% 的用户
     */
    public int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];
                } else {
                    return person2[1] - person1[1];
                }
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < n; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }
    /**
     * 官方解法2
     * 方法二：从高到低考虑
     *
     * 思路与算法
     *
     * 同样地，我们也可以将每个人按照身高从大到小进行排序，处理身高相同的人使用的方法类似，即：按照 hih_ihi​ 为第一关键字降序，kik_iki​ 为第二关键字升序进行排序。如果我们按照排完序后的顺序，依次将每个人放入队列中，那么当我们放入第 iii 个人时：
     *
     *     第 0,⋯ ,i−10, \cdots, i-10,⋯,i−1 个人已经在队列中被安排了位置，他们只要站在第 iii 个人的前面，就会对第 iii 个人产生影响，因为他们都比第 iii 个人高；
     *
     *     而第 i+1,⋯ ,n−1i+1, \cdots, n-1i+1,⋯,n−1 个人还没有被放入队列中，并且他们无论站在哪里，对第 iii 个人都没有任何影响，因为他们都比第 iii 个人矮。
     *
     * 在这种情况下，我们无从得知应该给后面的人安排多少个「空」位置，因此就不能沿用方法一。但我们可以发现，后面的人既然不会对第 iii 个人造成影响，我们可以采用「插空」的方法，依次给每一个人在当前的队列中选择一个插入的位置。也就是说，当我们放入第 iii 个人时，只需要将其插入队列中，使得他的前面恰好有 kik_iki​ 个人即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/gen-ju-shen-gao-zhong-jian-dui-lie-by-leetcode-sol/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.62% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了81.55% 的用户
     */
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
