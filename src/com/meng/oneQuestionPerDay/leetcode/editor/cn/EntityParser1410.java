package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class EntityParser1410 {
    static Map<String,String> cache = new HashMap<>();
    static {
        cache.put("&quot;","\"");
        cache.put("&apos;","'");
        cache.put("&amp;","&");
        cache.put("&gt;",">");
        cache.put("&lt;","<");
        cache.put("&frasl;","/");
    }

    /**
     * 解答成功:
     * 	执行耗时:39 ms,击败了22.03% 的Java用户
     * 	内存消耗:43 MB,击败了94.92% 的Java用户
     * @param text
     * @return
     */
    public String entityParserMy(String text) {
        StringBuffer res = new StringBuffer();
        int length = text.length();
        int pos = 0;
        while (pos < length) {
            boolean isEntity = false;
            if (text.charAt(pos) == '&') {
                for (Map.Entry<String,String> entry : cache.entrySet()) {
                    String e = entry.getKey();
                    String c = entry.getValue();
                    if (pos + e.length() <= text.length() && text.startsWith(e, pos)) {
                        res.append(c);
                        pos += e.length();
                        isEntity = true;
                        break;
                    }
                }
            }
            if (!isEntity) {
                res.append(text.charAt(pos++));
            }
        }
        return res.toString();
    }
    class EntityChar {
        String entity;
        char character;

        public EntityChar(String entity, char character) {
            this.entity = entity;
            this.character = character;
        }
    }

    List<EntityChar> entityList = new ArrayList<EntityChar>();

    /**
     * 解答成功:
     * 	执行耗时:37 ms,击败了30.51% 的Java用户
     * 	内存消耗:43.2 MB,击败了71.19% 的Java用户
     * @param text
     * @return
     */
    public String entityParser(String text) {
        entityList.add(new EntityChar("&quot;", '"'));
        entityList.add(new EntityChar("&apos;", '\''));
        entityList.add(new EntityChar("&amp;", '&'));
        entityList.add(new EntityChar("&gt;", '>'));
        entityList.add(new EntityChar("&lt;", '<'));
        entityList.add(new EntityChar("&frasl;", '/'));
        StringBuffer res = new StringBuffer();
        int length = text.length();
        int pos = 0;
        while (pos < length) {
            boolean isEntity = false;
            if (text.charAt(pos) == '&') {
                for (EntityChar entityChar : entityList) {
                    String e = entityChar.entity;
                    char c = entityChar.character;
                    if (pos + e.length() <= text.length() && text.substring(pos, pos + e.length()).equals(e)) {
                        res.append(c);
                        pos += e.length();
                        isEntity = true;
                        break;
                    }
                }
            }
            if (!isEntity) {
                res.append(text.charAt(pos++));
                continue;
            }
        }
        return res.toString();
    }

}
//leetcode submit region end(Prohibit modification and deletion)
