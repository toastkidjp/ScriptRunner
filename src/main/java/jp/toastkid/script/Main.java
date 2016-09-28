package jp.toastkid.script;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX Script Runner.
 * @author Toast kid
 * @version 0.0.1
 */
public final class Main extends Application {

    /** Application title. */
    private static final String TITLE = "ScriptRunner";

    /** fxml file. */
    private static final String FXML_PATH = "scenes/Main.fxml";

    /** Controller instance. */
    private Controller controller;

    /** stage. */
    private final Stage stage;

    /**
     * Constructor.
     */
    public Main() {
        //final long start = System.currentTimeMillis();
        this.stage = new Stage(StageStyle.DECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(TITLE);
        final Scene scene = readScene(stage);
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(600);
        controller.setStage(stage);
        //controller.setStatus("完了 - " + (System.currentTimeMillis() - start) + "[ms]");
    }

    /**
     * show this app with passed stage.
     * @param owner
     */
    public void show(final Stage owner) {
        this.stage.getScene().getStylesheets().addAll(owner.getScene().getStylesheets());
        this.stage.initOwner(owner);
        show();
    }

    /**
     * show this app.
     */
    public void show() {
        stage.showAndWait();
    }

    @Override
    public void start(final Stage stage) {
        new Main().show();
    }

    /**
     * コントローラに stage を渡しシーンファイルを読込.
     * @param stage Stage オブジェクト
     * @return Scene オブジェクト
     */
    private final Scene readScene(final Stage stage) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FXML_PATH));
            final VBox loaded = (VBox) loader.load();
            controller = (Controller) loader.getController();
            return new Scene(loaded);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        controller = null;
    }

    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(Main.class);
    }
}
