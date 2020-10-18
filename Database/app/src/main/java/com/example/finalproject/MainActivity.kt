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
        var username: String = "";
        var password: String = "";
        var goodLogin: Boolean = true;
        //creating data validation to test that the username is less than size of 8
        if (username.text.SIZE < 8 ){
            //input the values the user input into username variable
            username = username.text.toString()
            goodLogin = true;
        }
        else{
            //if the login isnt less than size 8 set goodLogin to false
            goodLogin = false;
        }
        //creating data validation to test that the password is less than size of 12
        if(password.text.SIZE < 12){
            //input the values the user input into password variable
            password = password.text.toString()
            goodLogin = true;
        }
        else{
            goodLogin = false;
        }
        //if goodLogin is still true add it to the database
        if (goodLogin){
            //creating a login class with username and password
            val login = login(username, password)
            //adding the username and password to the database
            loginHandler.addLogin(login)
            //greet the user
            Toast.makeText(applicationContext, "Welcome $username",Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(applicationContext,"Bad Signup",Toast.LENGTH_SHORT).show()
        }
    }
    fun deleteLogin(view: View){
        val loginHandler = loginHandler(this, null, null, 1)
        //test to make sure username is less than 8 characters
        if (username.text.SIZE < 8 ){
            //if username is less than 8 characters call the deleteLogin function and delete the username
            val delete = loginHandler.deleteLogin(username)
        }
        else
        {
            Toast.makeText(applicationContext,"Username must be less than 8 characters",Toast.LENGTH_SHORT).show()
        }

    }
    //this function is to sign a user that already exist in the database
    fun findLogin(view: View){
        val loginHandler = loginHandler(this,null,null,1)
        //creating data validation to test that the username is less than size of 8
        if (username.text.SIZE < 8 ){
            //input the values the user input into username variable
            val login = loginHandler.findLogin(username)
        }
        //testing to make sure that login exist and if it to set logId.text to the value input by the user.
        if (login != null){
            loginID.text = login.id.toString()
        }
        else{
            //if login is bad tell the user that username was not found
            Toast.makeText(applicationContext,"Bad Login",Toast.LENGTH_SHORT).show()
        }
    }
}