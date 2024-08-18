package com.fifteen11.jetpacksamplelibrary.quotes.mock

class UserRepository {

    val users = listOf(
        User(1, "John Doe", "john.mclean@examplepetstore.com", "password123"),
        User(2, "Jane Smith", "john.c.calhoun@examplepetstore.com", "password456"),
        User(3, "Bob Johnson", "john.n.garner@examplepetstore.com", "password789")
    )

    fun loginUser(email: String, password: String): LOGIN_STATUS {
        val users = users.filter { user -> user.email == email }
        return if (users.size == 1) {
            if (users[0].password == password) {
                LOGIN_STATUS.SUCCESS
            } else {
                LOGIN_STATUS.INVALID_PASSWORD
            }
        } else {
            LOGIN_STATUS.INVALID_USER
        }
    }
}