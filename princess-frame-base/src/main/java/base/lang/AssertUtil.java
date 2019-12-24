package base.lang;

public class AssertUtil {

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean val, String message) {
        if (!val) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isFalse(boolean val, String message) {
        if (val) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void noNullElements(Object[] objects) {
        noNullElements(objects, "Array must not contain any null objects");
    }

    public static void noNullElements(Object[] objects, String message) {
        for (Object obj : objects) {
            if (obj == null) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    public static void notEmpty(String string) {
        if (string == null || string.length() == 0) {
            throw new IllegalArgumentException("String must not be empty");
        }
    }

    public static void notEmpty(String string, String message) {
        if (string == null || string.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void fail(String message) {
        throw new IllegalArgumentException(message);
    }

    private AssertUtil() {}
}
