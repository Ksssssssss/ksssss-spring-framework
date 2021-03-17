package github.ksssss.utils;

import com.sun.istack.internal.Nullable;

import java.util.function.Supplier;

/**
 * @author ksssss
 * @since 2021/3/17 18:35
 */
public abstract class Assert {
    public static void isNull(@Nullable Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new IllegalArgumentException(nollSafeGet(messageSupplier));
        }
    }

    public static void notNull(@Nullable Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new IllegalArgumentException(nollSafeGet(messageSupplier));
        }
    }

    private static String nollSafeGet(@Nullable Supplier<String> messageSupplier) {
        return (messageSupplier != null ? messageSupplier.get() : null);
    }
}
