package com.example.finalproject
//simple login class
class login{
    var id: Int = 0
    var username: String? = null
    var password: String? = null
    //constructor allowing the user to create a log in with id,username, and password
    constructor(id: Int, username: String, password: String){
        this.id = id
        this.username = username
        this.password = password
    }
    //constructor allowing the user to create a log in with just user and password
    constructor(username: String, password: String){
        this.username = username
        this.password = password
    }
}