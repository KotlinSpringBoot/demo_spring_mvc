package com.easy.springboot.demo_spring_mvc.exception;

public class NoPermissionException extends Exception {
    String msg;
    Throwable t;

    public NoPermissionException(String msg, Throwable t) {
        this.msg = msg;
        this.t = t;
    }

    public NoPermissionException(String message, String msg, Throwable t) {
        super(message);
        this.msg = msg;
        this.t = t;
    }

    public NoPermissionException(String message, Throwable cause, String msg, Throwable t) {
        super(message, cause);
        this.msg = msg;
        this.t = t;
    }

    public NoPermissionException(Throwable cause, String msg, Throwable t) {
        super(cause);
        this.msg = msg;
        this.t = t;
    }

    public NoPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msg, Throwable t) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = msg;
        this.t = t;
    }
}
