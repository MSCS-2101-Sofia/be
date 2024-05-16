package org.tennismate.com.common.data

data class ListOfUsersResponse(
    private val user: List<User>
) {
    companion object {
        fun fromListOfUser(users: List<User>) :ListOfUsersResponse {
            return ListOfUsersResponse(users)
        }
    }
}