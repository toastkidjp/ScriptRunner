package jp.toastkid.script;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * {@link Main}'s smoke test.
 *
 * @author Toast kid
 *
 */
public class MainTest extends ApplicationTest {

    /** Test object. */
    private Main main;

    /**
     * Smoke test.
     */
    @Test
    public void test() {
        Platform.runLater(main::show);
    }

    @Override
    public void start(Stage stage) throws Exception {
        main = new Main();
    }

}
