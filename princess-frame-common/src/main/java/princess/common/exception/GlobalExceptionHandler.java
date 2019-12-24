package princess.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import base.model.Result;
import lombok.extern.slf4j.Slf4j;
import princess.common.core.expection.ErrorType;
import princess.common.core.expection.GeneralException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected Result<?> handleGlobalException(Exception exception) {
        log(exception);
        String message = exception.getMessage();
        if (message == null) {
            message = "Service Error !";
        }
        return Result.error(message);
    }

    /**
     * 异常日志
     * @param e 异常
     */
    protected void log(Exception e) {
        if (e instanceof GeneralException && ErrorType.PROMPT.equals(((GeneralException) e).getType())) {
            log.warn(e.getMessage());
        } else if (e instanceof org.apache.catalina.connector.ClientAbortException) {
            log.warn(e.toString());
        } else {
            log.error("!", e);
        }
    }
}
