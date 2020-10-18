package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class loginHandler(context: Context, name: String?,
        factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DBHandler.DATABASE_NAME,
        factory, DBHandler.DATABASE_VERSION
        ) {

        override fun onCreate(db: SQLiteDatabase) {
            //creating the database
            val CREATE_TABLE_LOGIN = ("CREATE TABLE" + TABLE_LOGIN + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + "TEXT" + ")")
            db.execSQL(CREATE_TABLE_LOGIN)

        }
        //function to update the database based on changes
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                               newVersion: Int) {
            db.execSQL(TABLE_LOGIN)
            onCreate(db)

        }
        //function to add a new log in from a user
        fun addLogin(login: login){
            val values = ContentValues()
            //inputting the values the user input into values class
            values.put(COLUMN_PASSWORD, login.password)
            values.put(COLUMN_USERNAME, login.username)

            val db = this.writableDatabase
            //inserting the values the user input into the database
            db.insert(TABLE_LOGIN,null,values)
            db.close()
        }
        fun deleteLogin(username: String): Boolean {
            var result = false
            //creating a SQL query that will display the data for the username the user input
            val query = "SELECT * FROM ${loginHandler.TABLE_LOGIN} WHERE ${loginHandler.COLUMN_USERNAME} = \"$username\""
            //creating a variable for the database
            val db = this.writableDatabase
            //executing the query and returning its value
            val cursor = db.rawQuery(query, null)
            //based on the value returned it will either move inside or return false because nothing was deleted
            if(cursor.moveToFirst()){
                //taking the username that was retrieved from the sql query
                val username = cursor.getString(0)
                //deleting the username from the table
                db.delete(loginHandler.TABLE_LOGIN, loginHandler.COLUMN_USERNAME + "=?",arrayOf(username))
                cursor.close()
                result = true
            }
            db.close()
            return result
        }

        //function to determine if a log in already exist
        fun findLogin(username: String): login? {
            //sql query to find a user based on their username
            val query = "SELECT * FROM $TABLE_LOGIN WHERE $COLUMN_USERNAME = \"$username\""

            val db = this.writableDatabase
            //executing the sql statement
            val result = db.rawQuery(query,null)
            var login: login? = null
            //if the database contained a user with that username it would continue
            if (result.moveToFirst())
            {
                result.moveToFirst()
                //inputting the information into the fields and creating a class called login
                val id = Integer.parseInt(result.getString(0))
                val username = result.getString(1)
                val password = result.getString(2)
                login = login(id, username, password)
                result.close()
            }
            db.close()
            //return login that was creating in the if statement
            return login
        }
        //columns in the database
        companion object {

            private val DATABASE_VERSION = 1
            private val DATABASE_NAME = "loginDB.db"
            val TABLE_LOGIN = "login"

            val COLUMN_ID = "_id"
            val COLUMN_USERNAME = "username"
            val COLUMN_PASSWORD = "password"
        }
    }﻿
}