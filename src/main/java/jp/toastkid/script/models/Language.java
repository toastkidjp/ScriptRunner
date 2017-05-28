/*
 * Copyright (c) 2017 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.script.models;

import org.apache.commons.lang3.StringUtils;

/**
 * Script Language names.
 * @author Toast kid
 *
 */
public enum Language {
    JAVASCRIPT, GROOVY, PYTHON;

    /**
     * 文字列に合った拡張子を返す.
     * @param lang 文字列
     * @return 拡張子
     */
    public static String extension(final String lang) {
        if (StringUtils.isBlank(lang)) {
            return ".groovy";
        }
        return extension(Language.valueOf(lang.toUpperCase()));
    }

    /**
     * Enum に合った拡張子を返す.
     * @param lang Enum
     * @return 拡張子
     */
    public static String extension(final Language lang) {
        if (lang == null) {
            return ".groovy";
        }
        switch (lang) {
            case JAVASCRIPT:
                return ".js";
            case PYTHON:
                return ".py";
            case GROOVY:
            default:
                return ".groovy";
        }
    }
}
