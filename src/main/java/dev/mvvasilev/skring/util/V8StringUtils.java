package dev.mvvasilev.skring.util;

import com.eclipsesource.v8.V8Array;

public final class V8StringUtils {

    public static String v8ConcatStringArray(V8Array array) {
        StringBuilder result = new StringBuilder();

        if (array.getKeys().length > 1) {
            for (int i = 0; i < array.getKeys().length; i++) {
                result.append(array.getString(i));
            }
        } else {
            result = new StringBuilder(array.toString());
        }

        return result.toString();
    }

}
