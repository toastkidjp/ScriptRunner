/*
 * Copyright (c) 2017 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
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
