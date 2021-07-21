package com.fsoft.ez.common.utils;

public final class CommonUtils {

    /** Privateコンストラクタ */
    private CommonUtils() {

    }

    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

}
