package com.example.kca.global.error.exception

import com.example.kca.global.error.ErrorCode

open class KcaException(val errorCode: ErrorCode): RuntimeException(errorCode.message)