package jp.toastkid.script.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Language's test.
 * @author Toast kid
 *
 */
public class LanguageTest {

    /**
     * Test of {@link Language#extension()}.
     */
    @Test
    public final void test_extension() {
        assertEquals(".groovy", Language.extension((String) null));
        assertEquals(".groovy", Language.extension((Language) null));
        assertEquals(".groovy", Language.extension(""));
        assertEquals(".groovy", Language.extension("Groovy"));
        assertEquals(".groovy", Language.extension("groovy"));
        assertEquals(".groovy", Language.extension("GROOVY"));
        assertEquals(".js",     Language.extension("JavaScript"));
        assertEquals(".py",     Language.extension("Python"));
    }


    /**
     * Test of {@link Language#valueOf(String)}.
     */
    @Test
    public final void testValueOf() {
        assertEquals(Language.GROOVY,     Language.valueOf("GROOVY"));
        assertEquals(Language.JAVASCRIPT, Language.valueOf("JAVASCRIPT"));
        assertEquals(Language.PYTHON,     Language.valueOf("PYTHON"));
    }

    /**
     * Test of {@link Language#values()}.
     */
    @Test
    public final void testValues() {
        assertTrue(0 < Language.values().length);
    }

    /**
     * {@link Language#valueOf(String)} is not able to accept lower cases.
     */
    @Test(expected=IllegalArgumentException.class)
    public final void notDefined() {
        Language.valueOf("python");
    }
}
