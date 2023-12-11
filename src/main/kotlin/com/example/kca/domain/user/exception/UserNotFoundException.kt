package com.example.kca.domain.user.exception

import com.example.kca.global.error.ErrorCode
import com.example.kca.global.error.exception.KcaException

class UserNotFoundException : KcaException(ErrorCode.USER_NOT_FOUND)