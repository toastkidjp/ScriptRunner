package jp.toastkid.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jp.toastkid.script.Language;

/**
 * Language's test.
 * @author Toast kid
 *
 */
public class LanguageTest {

    /**
     * すべて大文字の時だけオブジェクトを返す.
     */
    @Test
    public final void testValueOf() {
        assertEquals(Language.GROOVY, Language.valueOf("GROOVY"));
        assertEquals(Language.JAVASCRIPT, Language.valueOf("JAVASCRIPT"));
        assertEquals(Language.PYTHON, Language.valueOf("PYTHON"));
    }

    /**
     * 小文字は不可.
     */
    @Test(expected=IllegalArgumentException.class)
    public final void notDefined() {
        Language.valueOf("python");
    }
}
