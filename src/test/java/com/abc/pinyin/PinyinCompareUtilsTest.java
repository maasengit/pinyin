package com.abc.pinyin;

import org.junit.Test;

import java.text.Collator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PinyinCompareUtilsTest {

    @Test
    public void compare() {
        assertEquals(-1L, PinyinCompareUtils.compare("怡情", "中国"));
        assertEquals(0L, PinyinCompareUtils.compare("中国", "中国"));
        assertEquals(-1L, PinyinCompareUtils.compare("1中国", "中国"));
        assertTrue(PinyinCompareUtils.compare("a中国", "A中国") > 0);
        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
        // 排序错误：怡情 > 中国
        assertEquals(1L,cmp.compare("怡情", "中国"));
    }
}