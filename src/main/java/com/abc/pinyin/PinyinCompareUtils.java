package com.abc.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 按照汉语拼音顺序排序。
 *
 */
public class PinyinCompareUtils {
    public static int compare(String name1, String name2) {
        if (name1 == null && name2 == null) {
            return 0;
        }
        if (name1 == null && name2 != null) {
            return -1;
        }
        if (name1 != null && name2 == null) {
            return 1;
        }

        for (int i = 0; i < name1.length() && i < name2.length(); i++) {
            char codePoint1 = name1.charAt(i);
            char codePoint2 = name2.charAt(i);
            // 转换为拼音，非汉字为NULL
            String pinyin1 = pinyin(codePoint1);
            String pinyin2 = pinyin(codePoint2);
            // 都不是汉字
            if (pinyin1 == null && pinyin2 == null) {
                if (codePoint1 != codePoint2) {
                    return codePoint1 - codePoint2;
                } else {
                    continue;
                }
            }
            // 非汉字优先
            if (pinyin1 == null && pinyin2 != null) {
                return -1;
            }
            if (pinyin1 != null && pinyin2 == null) {
                return 1;
            }
            //忽略大小写比较
            if (!pinyin1.toLowerCase().equals(pinyin2.toLowerCase())) {
                return pinyin1.toLowerCase().compareTo(pinyin2.toLowerCase());
            } else {
                //不忽略大小写比较
                if (!pinyin1.equals(pinyin2)) {
                    return pinyin1.compareTo(pinyin2);
                }
            }
        }
        // 相同位置的字符都相等，则比较长度
        return name1.length() - name2.length();
    }

    /**
     * 得到汉字的拼音，多音字返回第一个拼音。非汉字返回null
     */
    private static String pinyin(char c) {
        String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyin == null) {
            return null;
        }
        return pinyin[0];
    }
}
