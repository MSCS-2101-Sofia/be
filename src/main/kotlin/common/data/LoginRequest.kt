package org.tennismate.com.common.data

data class LoginRequest(
    val username: String,
    val password_md5: String
) {

}