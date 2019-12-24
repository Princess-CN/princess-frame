package princess.common.core.expection;

/**
 * 通用异常类
 * @author YYL
 */
@SuppressWarnings("serial")
public class GeneralException extends RuntimeException {

    private final ErrorType type;
    private final String message;

    public GeneralException(ErrorType type, String message) {
        super(message);
        this.message = message;
        this.type = type;
    }

    public GeneralException(ErrorType type, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public ErrorType getType() {
        return type;
    }
}

