package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
    //this function allows the user to add a weight into the database
    fun addWeight(view: View){
        val dbHandler = DBHandler(this, null,null,1)
        //variables to hold the information the user input
        val date = weightTrackDate.text.toString())
        val weight = WeightTrack(weight.text.toString(),date)
        //adding the weight the user input to the database
        dbHandler.addWeightTrack(weight)
        }
    //this function allows the user to delete a weight from the database
    fun deleteWeight(view: View){
        val dbHandler = DBHandler(this,null,null,1)
        //deleting the weight the user input into the database
        val result = dbHandler.deleteWeightTrack(id.text.toString())

    }
    //this function displays the weights input by the user
    fun readWeight(view: View){
        val dbHandler = DBHandler(this, null, null, 1)
        //reading the information from the database
        val result = dbHandler.readWeightTrack()
    }
}