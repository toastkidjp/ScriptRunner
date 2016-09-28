package jp.toastkid.libs;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jp.toastkid.script.ClojureRunner;
import jp.toastkid.script.GroovyRunner;
import jp.toastkid.script.JavaScriptRunner;
import jp.toastkid.script.Language;
import jp.toastkid.script.PythonRunner;
import jp.toastkid.script.ScriptRunner;

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
        assertTrue(ScriptRunner.find(Language.CLOJURE)    instanceof ClojureRunner);
        assertTrue(ScriptRunner.find(Language.GROOVY)     instanceof GroovyRunner);
        assertTrue(ScriptRunner.find(Language.PYTHON)     instanceof PythonRunner);
        assertTrue(ScriptRunner.find(Language.JAVASCRIPT) instanceof JavaScriptRunner);
    }

}
