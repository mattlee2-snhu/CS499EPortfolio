package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //this function is allow the user to sign up for the app and create a profile
    fun addLogin(view: View){
        val loginHandler = loginHandler(this,null,null,1)
        //input the values the user input into username and password variables
        val username = username.text.toString()
        val password = password.text.toString()
        //creating a login class with username and password
        val login = login(username, password)
        //adding the username and password to the database
        loginHandler.addLogin(login)
        Toast.makeText(applicationContext, "Welcome $username",Toast.LENGTH_SHORT).show()
    }
    //this function is to sign a user that already exist in the database
    fun findLogin(view: View){
        val loginHandler = loginHandler(this,null,null,1)
        //test to find if the user exist
        val login = loginHandler.findLogin(username)
        //testing to make sure that login exist and if it to set logId.text to the value input by the user.
        if (login != null){
            loginID.text = login.id.toString()
        }
        else{
            Toast.makeText(applicationContext,"Bad Login",Toast.LENGTH_SHORT).show()
        }
    }
}