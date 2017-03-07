package jp.toastkid.script;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * {@link ScriptConsole}'s smoke test.
 *
 * @author Toast kid
 *
 */
public class ScriptConsoleTest extends ApplicationTest {

    /** Test object. */
    private ScriptConsole console;

    /**
     * Smoke test.
     */
    @Test
    public void test() {
        Platform.runLater(console::show);
    }

    /**
     * For test coverage.
     */
    @Test
    public void test_for_coverage() {
        Platform.runLater(() -> assertNotNull(new ScriptConsole.Builder().build()));
    }

    @Override
    public void start(final Stage stage) throws Exception {
        console = new ScriptConsole.Builder().setOwner(stage).build();
    }

}
