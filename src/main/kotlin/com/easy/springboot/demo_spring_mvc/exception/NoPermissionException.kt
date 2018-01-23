package com.easy.springboot.demo_spring_mvc.exception

class NoPermissionException : Exception {
    lateinit var msg: String
    lateinit var t: Throwable



    constructor(msg: String, t: Throwable) : super() {
        this.msg = msg
        this.t = t
    }

    constructor(message: String?, msg: String, t: Throwable) : super(message) {
        this.msg = msg
        this.t = t
    }

    constructor(message: String?, cause: Throwable?, msg: String, t: Throwable) : super(message, cause) {
        this.msg = msg
        this.t = t
    }

    constructor(cause: Throwable?, msg: String, t: Throwable) : super(cause) {
        this.msg = msg
        this.t = t
    }

    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean, msg: String, t: Throwable) : super(message, cause, enableSuppression, writableStackTrace) {
        this.msg = msg
        this.t = t
    }

    constructor(msg: String) : super() {
        this.msg = msg
    }

    constructor(message: String?, msg: String) : super(message) {
        this.msg = msg
    }

    constructor(message: String?, cause: Throwable?, msg: String) : super(message, cause) {
        this.msg = msg
    }

    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean, msg: String) : super(message, cause, enableSuppression, writableStackTrace) {
        this.msg = msg
    }
}
