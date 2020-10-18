package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class DBHandler (context: Context, name: String?,
    factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME,
    factory, DATABASE_VERSION) {
        //the oncreate function which is creating the database
        override fun onCreate(db: SQLiteDatabase) {
            val SQL_WEIGHTTRACK_TABLE = ("CREATE TABLE" + TABLE_WEIGHTTRACK + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"+ COLUMN_WEIGHT +"INTEGER"+ COLUMN_DATE +"STRING" + ")")
            db.execSQL(SQL_WEIGHTTRACK_TABLE)
        }
        //updating the database when a new entry is input
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                               newVersion: Int) {
            db.execSQL(TABLE_WEIGHTTRACK)
            onCreate(db)
        }
        //the columns and database location
        companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "WeightTrack.db"
        val TABLE_WEIGHTTRACK = "weightTrack"

        val COLUMN_ID = "id"
        val COLUMN_WEIGHT = "weight"
        val COLUMN_DATE = "date"
    }
    //function to add rows to the database
    fun addWeightTrack(weightTrack: WeightTrack)
    {
        val values = ContentValues()
        //storing the values the user input for weight and date
        values.put(COLUMN_WEIGHT, weightTrack.weight)
        values.put(COLUMN_DATE, weightTrack.date)

        val db = this.writableDatabase
        //inserting the values the user input into the database
        db.insert(TABLE_WEIGHTTRACK,null, values)
        db.close()
    }
    //function to delete rows from the database
    fun deleteWeightTrack(id: Int): Boolean {
        var result = false
        //creating a SQL query that will display the data for the id the user input
        val query = "SELECT * FROM $TABLE_WEIGHTTRACK WHERE $COLUMN_ID = \"$id\""
        //creating a variable for the database
        val db = this.writableDatabase
        //executing the query and returning its value
        val cursor = db.rawQuery(query, null)
        //based on the value returned it will either move inside or return false because nothing was deleted
        if(cursor.moveToFirst()){
            //taking the id that was retrieved from the sql query
            val id = Integer.parseInt(cursor.getString(0))
            //deleting the id from the table
            db.delete(TABLE_WEIGHTTRACK, COLUMN_ID + "=?",arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result

    }

    fun readWeightTrack(): MutableList<WeightTrack>{
        //creating a list to hold the database to be display
        val list: MutableList<WeightTrack> = ArrayList()
        val db = this.readableDatabase
        //creating an sql query to show all entries from the database
        val query = "Select * from $TABLE_WEIGHTTRACK"
        //executing the query
        val result = db.rawQuery(query, null)
        //testing to see if result has a value and the database isnt blank
        if (result.moveToFirst())
        {
            do {
                //creating a weighttrack class to hold the data to display it
                val weight = WeightTrack()
                //inputting the data from the database into the weight variable and displaying it
                weight.id = result.getString(result.getColumnIndex(COLUMN_ID)).toInt()
                weight.weight = result.getString(result.getColumnIndex(COLUMN_WEIGHT)).toInt()
                weight.date = result.getString(result.getColumnIndex(COLUMN_DATE))
            }
                //until the database is empty it will continue
                while(result.moveToNext())
        }
        return list
    }

}