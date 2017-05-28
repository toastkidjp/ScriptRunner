/*
 * Copyright (c) 2017 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.script.runner;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javax.script.ScriptContext;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.fxmisc.richtext.CodeArea;

import jp.toastkid.script.highlight.GroovyHighlighter;
import jp.toastkid.script.highlight.Highlighter;

/**
 * Groovy's runner.
 *
 * @author Toast kid
 */
public class GroovyRunner extends ScriptRunner {

    /**
     * init ScriptEngine.
     */
    public GroovyRunner() {
        engine = new GroovyScriptEngineFactory().getScriptEngine();
    }

    @Override
    public Optional<String> run(final String script) {
        if (StringUtils.isEmpty(script)) {
            return Optional.empty();
        }
        if (engine == null) {
            System.out.println("groovy null");
        }

        final StringBuilder result = new StringBuilder();

        try (final StringWriter writer = new StringWriter();) {
            if (engine != null) {
                final ScriptContext context = engine.getContext();
                context.setWriter(new PrintWriter(writer));
                context.setErrorWriter(new PrintWriter(writer));
            }
            final java.lang.Object run = engine.eval(script);
            result.append(writer.toString()).append(LINE_SEPARATOR);
            if (run != null) {
                result.append("return = ").append(run.toString());
            }
            writer.close();
        } catch (final CompilationFailedException | IOException | javax.script.ScriptException e) {
            e.printStackTrace();
            result.append("Occurred Exception.").append(LINE_SEPARATOR)
                .append(e.getMessage());
        }
        return Optional.of(result.toString());
    }

    @Override
    public Highlighter initHighlight(final CodeArea codeArea) {
        return new GroovyHighlighter(codeArea);
    }
}
