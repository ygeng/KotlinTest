package com.aiden.kotlintest.net

/**
 * 异常类型
 * @author aiden@tronsis.com
 * @date 2018/8/20 15:42
 */
enum class ExceptionReason {
    /**
     * 解析数据失败
     */
    PARSE_ERROR,
    /**
     * 网络问题
     */
    BAD_NETWORK,
    /**
     * 连接错误
     */
    CONNECT_ERROR,
    /**
     * 连接超时
     */
    CONNECT_TIMEOUT,
    /**
     * 未知错误
     */
    UNKNOWN_ERROR
}