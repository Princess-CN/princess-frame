package base.model;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 结果对象
 * @param <T> 返回数据泛型
 */
@Data
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Result<T> implements Serializable {
    // ==============================Fields========================================
    private Integer code;
    private String message;
    private T data;

    // ==============================Constructors==================================
    public Result(String message, T data) {
        this(200, message, data);
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result<?> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>("OK", data);
    }

    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(message, data);
    }

    public static Result<?> error() {
        return error("ERROR");
    }

    public static Result<?> error(String message) {
        return new Result<>(500, message, null);
    }

    public static Result<?> ofMessage(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
