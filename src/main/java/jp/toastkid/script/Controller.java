package jp.toastkid.script;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.reactfx.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Script area's controller.
 * @author Toast kid
 *
 */
public class Controller implements Initializable {

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /** run script keyboard shortcut. */
    private static final KeyCodeCombination RUN_SCRIPT
        = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);

    /** Script file name. */
    @FXML
    private TextField scriptName;

    /** script input. */
    @FXML
    private CodeArea scripterInput;

    /** script output. */
    @FXML
    private CodeArea scripterOutput;

    /** names of script language. */
    @FXML
    private ComboBox<String> scriptLanguage;

    /** in script area. */
    @FXML
    private VBox root;

    /** Run button. */
    @FXML
    private Button runButton;

    /** parent's stage. */
    private Stage stage;

    /** Subscription. */
    private Subscription subscription;

    /**
     * Set stage to this controller and apply style.
     * @param stage
     */
    public void setStage(final Stage stage) {
        this.stage = stage;
        final ObservableList<String> stylesheets = this.stage.getScene().getStylesheets();
        if (stylesheets != null) {
            stylesheets.clear();
        }
        stylesheets.addAll(stage.getScene().getStylesheets());
        stylesheets.add(getClass().getClassLoader().getResource("keywords.css").toExternalForm());
    }

    /**
     * Hide article search box area.
     */
    @FXML
    public void openScripter() {
        root.setManaged(true);
        root.visibleProperty().setValue(true);
        scripterInput.requestFocus();
    }

    /**
     * Hide article search box area.
     * @see <a href="http://stackoverflow.com/questions/19923443/
     *javafx-fill-empty-space-when-component-is-not-visible">
     * JavaFX Fill empty space when component is not visible?</a>
     */
    @FXML
    public void hideScripter() {
        root.visibleProperty().setValue(false);
        root.setManaged(false);
    }

    /**
     * Open script file.
     * @see <a href="http://javafx-trick.appspot.com/article/110010/80074/70110.html">
     * ファイル選択ダイアログ(showOpenDialog)の作成方法</a>
     */
    @FXML
    public void openScript() {
        final FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("."));
        final File result = fc.showOpenDialog(stage.getScene().getWindow());
        loadScript(result);
    }

    /**
     * Save script to file.
     */
    @FXML
    public void saveScript() {
        try {
            if (new File(scriptName.getText()) != null) {
                final File file = new File("script"
                        + Language.extension(scriptLanguage.getSelectionModel().getSelectedItem()));
                Files.createFile(file.toPath());
                scriptName.setText(file.getAbsolutePath());
            }
            final File file = new File(scriptName.getText());
            Files.write(
                    file.toPath(),
                    scripterInput.getText().getBytes("UTF-8"),
                    StandardOpenOption.WRITE
                    );
        } catch (final IOException e) {
            LOGGER.error("Caught error.", e);
        }
    }

    /**
     * Reload script.
     */
    @FXML
    public void reloadScript() {
        loadScript(new File(scriptName.getText()));
    }

    /**
     * Load script from file.
     * @param file
     */
    private void loadScript(final File file) {
        if (file == null || !file.exists()) {
            return;
        }
        try {
            scriptName.setText(file.getCanonicalPath());
            scripterInput.replaceText(Files.readAllLines(file.toPath()).stream()
                    .collect(Collectors.joining(System.lineSeparator())));
        } catch (final IOException e) {
            LOGGER.error("Caught error.", e);
        }
    }

    /**
     * run script use input plain text.
     */
    @FXML
    private void runScript() {
        scripterOutput.replaceText("Work in Progress......Could you please wait a moment?");
        final ScriptRunner runner = findRunner();
        final String result = runner.run(scripterInput.getText()).get();
        if (StringUtils.isEmpty(result)) {
            return;
        }
        scripterOutput.replaceText(result);
    }

    /**
     * Find current runner.
     * @return ScriptRunner
     */
    private ScriptRunner findRunner() {
        final Language lang
            = Language.valueOf(scriptLanguage.getSelectionModel().getSelectedItem().toUpperCase());
        return ScriptRunner.find(lang);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        scripterInput.setParagraphGraphicFactory(LineNumberFactory.get(scripterInput));
        scripterOutput.setParagraphGraphicFactory(LineNumberFactory.get(scripterOutput));
        scriptLanguage.setOnAction(e -> {
            if (subscription != null) {
                subscription.unsubscribe();
            }
            subscription = findRunner().initHighlight(scripterInput).highlight();
            scripterInput.replaceText(scripterInput.getText());
        });
        scriptLanguage.getSelectionModel().select(0);
        scriptLanguage.fireEvent(new ActionEvent());
        scripterInput.setOnKeyPressed((e) -> {
            if (RUN_SCRIPT.match(e)) {
                runScript();
            }
        });
    }

    /**
     * Return root pane.
     * @return root pane.
     */
    protected Pane getRoot() {
        return root;
    }

}
