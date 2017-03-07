package jp.toastkid.script;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.fxmisc.richtext.CodeArea;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * {@link Controller}'s test.
 *
 * @author Toast kid
 *
 */
public class ControllerTest extends ApplicationTest {

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerTest.class);

    /**
     * UI test.
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        final CodeArea input = (CodeArea) lookup("#scripterInput").query();
        assertTrue(input.isFocused());
        Platform.runLater(() -> {
            final String text = "println 'Hello world.'";
            input.replaceText(text);
            assertEquals(text, input.getText());
            lookup("#runButton").query().fireEvent(new ActionEvent());
            assertEquals("Hello world.", ((CodeArea) lookup("#scripterOutput").query()).getText().trim());
        });
    }

    @Override
    public void start(final Stage stage) throws Exception {
        try {
            final FXMLLoader loader
                = new FXMLLoader(getClass().getClassLoader().getResource("scenes/Main.fxml"));
            final VBox loaded = (VBox) loader.load();
            final Scene scene = new Scene(loaded);
            stage.setScene(scene);
        } catch (final IOException e) {
            LOGGER.error("Scene Reading Error", e);
        }
        stage.show();
    }

}
