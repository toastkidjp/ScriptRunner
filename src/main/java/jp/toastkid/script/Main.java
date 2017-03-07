package jp.toastkid.script;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX Script Runner.
 * @author Toast kid
 * @version 0.0.1
 */
public final class Main extends Application {

    @Override
    public void start(final Stage stage) {
        new ScriptConsole.Builder().build().show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(Main.class);
    }
}
