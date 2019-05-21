package dev.mvvasilev.skring.util;

import java.util.Collection;

public class Assert {

    public static void notEmpty(Object object) {
        notEmpty(object, "");
    }

    public static void notEmpty(Object object, String message) {
        notNull(object, message);

        if (object instanceof String) {
            if (((String) object).isEmpty()) {
                throw new NullPointerException(message);
            }
        }

        if (object instanceof Collection) {
            if (((Collection) object).isEmpty()) {
                throw new NullPointerException(message);
            }
        }
    }

    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
    }
}
