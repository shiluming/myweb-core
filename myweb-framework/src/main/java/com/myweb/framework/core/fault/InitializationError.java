package com.myweb.framework.core.fault;

/**
 * Created by rola
 * 初始化错误类
 */
public class InitializationError extends Error{

    public InitializationError() {
        super();
    }
    public InitializationError(String message) {
        super(message);
    }

    public InitializationError(String message,Throwable cause) {
        super(message,cause);
    }
    public InitializationError(Throwable cause) {
        super(cause);
    }
}
