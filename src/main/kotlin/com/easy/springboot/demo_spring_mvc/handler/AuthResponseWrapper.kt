package com.easy.springboot.demo_spring_mvc.handler

import org.slf4j.LoggerFactory

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


class AuthResponseWrapper(response: HttpServletResponse?) : HttpServletResponseWrapper(response) {
    var log = LoggerFactory.getLogger(AuthResponseWrapper::class.java)

    var _stream = ByteArrayOutputStream()
    var _pw = PrintWriter(_stream)

    /**
     * 覆盖getWriter()方法，将字符流缓冲到本地
     */
    @Throws(IOException::class)
    override fun getWriter(): PrintWriter {
        log.info("getWriter()")
        return _pw
    }

    /**
     * 覆盖getOutputStream()方法，将字节流缓冲到本地
     */
    @Throws(IOException::class)
    override fun getOutputStream(): ServletOutputStream {
        log.info("getOutputStream()")
        return object : ServletOutputStream() {
            override fun isReady(): Boolean {
                return true
            }

            override fun setWriteListener(listener: WriteListener?) {

            }

            @Throws(IOException::class)
            override fun write(b: Int) {
                _stream.write(b)
            }
        }
    }


    /**
     * 把缓冲区内容写入输出流后关闭
     *
     * @author xxj
     */
    fun flush() {
        try {
            _pw.flush()
            _pw.close()
            _stream.flush()
            _stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    /**
     * 获取字节流
     * @return
     */
    fun getByteArrayOutputStream(): ByteArrayOutputStream {
        return _stream
    }

    /**
     * 将换出区内容转为文本输出
     * @return
     */
    fun getTextContent(): String {
        flush()
        return _stream.toString()
    }


}

