package jp.toastkid.script;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Script console.
 *
 * @author Toast kid
 *
 */
public class ScriptConsole {

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

    /** Application title. */
    private static final String TITLE = "ScriptRunner";

    /** FXML file. */
    private static final String FXML_PATH = "scenes/Main.fxml";

    /** Controller instance. */
    private Controller controller;

    /** Stage. */
    private final Stage stage;

    /**
     * Builder of {@link ScriptConsole}.
     *
     * @author Toast kid
     *
     */
    public static class Builder {

        /** For copy style to this stage. */
        private Stage owner;

        /**
         * Set owner window.
         * @param s stage
         * @return This builder
         */
        public Builder setOwner(final Stage s) {
            this.owner = s;
            return this;
        }

        /**
         * Make instance.
         * @return
         */
        public ScriptConsole build() {
            return new ScriptConsole(this);
        }
    }

    /**
     * Constructor.
     */
    private ScriptConsole(final Builder b) {
        final long start = System.currentTimeMillis();
        this.stage = new Stage(StageStyle.DECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(TITLE);
        final Scene scene = readScene(stage);
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(600);
        if (b.owner != null) {
            Optional.ofNullable(b.owner.getScene()).ifPresent(ownerScene ->
                stage.getScene().getStylesheets().addAll(ownerScene.getStylesheets()));
            stage.initOwner(b.owner);
        }
        controller.setStage(stage);
        LOGGER.info("Done. - {}[ms]", System.currentTimeMillis() - start);
    }

    /**
     * show this app.
     */
    public void show() {
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.showAndWait();
    }

    /**
     * Return root pane.
     * @return root pane.
     */
    public Pane getRoot() {
        return controller.getRoot();
    }

    /**
     * Focus on controller's input area.
     */
    public void requestFocus() {
        controller.requestFocus();
    }

    /**
     * Read scene file and set scene to passed stage.
     * @param stage Stage Object
     * @return Scene Object
     */
    private final Scene readScene(final Stage stage) {
        try {
            final FXMLLoader loader
                = new FXMLLoader(getClass().getClassLoader().getResource(FXML_PATH));
            final VBox loaded = (VBox) loader.load();
            controller = (Controller) loader.getController();
            return new Scene(loaded);
        } catch (final IOException e) {
            LOGGER.error("Scene Reading Error", e);
        }
        return null;
    }

}
