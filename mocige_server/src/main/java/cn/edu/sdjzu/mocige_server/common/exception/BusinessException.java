package cn.edu.sdjzu.mocige_server.common.exception;

/**
 * 业务异常
 * 用于在业务逻辑中抛出特定的异常，由全局异常处理器统一处理
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}