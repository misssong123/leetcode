package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionTopStudents2512 {
    /**
     * 解答成功:
     * 	执行耗时:81 ms,击败了18.71% 的Java用户
     * 	内存消耗:53.4 MB,击败了69.01% 的Java用户
     * @param positive_feedback
     * @param negative_feedback
     * @param report
     * @param student_id
     * @param k
     * @return
     */
    public List<Integer> topStudents1(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]){
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });
        Set<String> positiveSet = Arrays.stream(positive_feedback).collect(Collectors.toSet());
        Set<String> negativeSet = Arrays.stream(negative_feedback).collect(Collectors.toSet());
        for(int i = 0 ; i < student_id.length ; i++){
            String[] strs = report[i].split(" ");
            int num = 0;
            for(String s : strs){
                if (positiveSet.contains(s)){
                    num += 3;
                }else if (negativeSet.contains(s)){
                    num -= 1;
                }
            }
            queue.add(new int[]{num,student_id[i]});
            if (queue.size()>k){
                queue.poll();
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()){
            res.add(0,queue.poll()[1]);
        }
        return res;
    }

    /**
     *解答成功:
     * 	执行耗时:59 ms,击败了71.93% 的Java用户
     * 	内存消耗:53.6 MB,击败了25.15% 的Java用户
     * @param positive_feedback
     * @param negative_feedback
     * @param report
     * @param student_id
     * @param k
     * @return
     */
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Map<String, Integer> words = new HashMap<>();
        for (String word : positive_feedback) {
            words.put(word, 3);
        }
        for (String word : negative_feedback) {
            words.put(word, -1);
        }
        int n = report.length;
        int[][] A = new int[n][2];
        for (int i = 0; i < n; i++) {
            int score = 0;
            for (String word : report[i].split(" ")) {
                score += words.getOrDefault(word, 0);
            }
            A[i] = new int[]{-score, student_id[i]};
        }
        Arrays.sort(A, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<Integer> topK = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topK.add(A[i][1]);
        }
        return topK;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
