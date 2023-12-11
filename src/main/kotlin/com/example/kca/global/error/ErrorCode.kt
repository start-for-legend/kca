package com.example.kca.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    USER_NOT_FOUND("user not found", 404),
    DUPLICATED_NAME("exists name", 409),

    INVALID_TOKEN("유효하지 않은 token 입니다.", 403),
    EXPIRATION_TOKEN("만료된 token 입니다.", 403),

    INTERVAL_SERVER_ERROR("서버오류 입니다.", 500)

}