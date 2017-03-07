package jp.toastkid.script.runner;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jp.toastkid.script.models.Language;
import jp.toastkid.script.runner.GroovyRunner;
import jp.toastkid.script.runner.JavaScriptRunner;
import jp.toastkid.script.runner.ScriptRunner;

/**
 * {@link ScriptRunner}'s test.
 * @author Toast kid
 *
 */
public class ScriptRunnerTest {

    /**
     * check {@link ScriptRunner#find(Language)}.
     */
    @Test
    public void testFind() {
        assertTrue(ScriptRunner.find(Language.GROOVY)     instanceof GroovyRunner);
        assertTrue(ScriptRunner.find(Language.JAVASCRIPT) instanceof JavaScriptRunner);
    }

}
