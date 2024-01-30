package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class FindRepeatDocumentLCR120 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:54.2 MB,击败了39.34% 的Java用户
     * @param documents
     * @return
     */
    public int findRepeatDocument(int[] documents) {
        int len = documents.length;
        for (int i = 0 ; i < len ; i++){
            while (documents[i] != i){
                if (documents[documents[i]]==documents[i]){
                    return documents[i];
                }
                int temp = documents[i];
                documents[i] =documents[temp];
                documents[temp] = temp;
            }
        }
        return -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
