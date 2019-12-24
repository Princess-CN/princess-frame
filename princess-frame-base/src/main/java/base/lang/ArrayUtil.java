package base.lang;

/**
 * 数组工具类
 * @author _yyl
 */
public class ArrayUtil {

    public static final int INDEX_NOT_FOUND = -1;

    /**
     * 获得数组第一个元素
     * @param <T> 元素类型泛型
     * @param array 数组对象
     * @return 数组第一个元素,如果数组为空返回NULL
     */
    public static <T> T getFirst(T[] array) {
        return (array == null || array.length == 0) ? null : array[0];
    }

    /**
     * 获得集合对象中对象的索引
     * @param array 集合对象
     * @param valueToFind 查找的对象
     * @return 对象的索引
     */
    public static int indexOf(final Object[] array, final Object valueToFind) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (valueToFind == null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else if (array.getClass().getComponentType().isInstance(valueToFind)) {
            for (int i = 0; i < array.length; i++) {
                if (valueToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }
}
