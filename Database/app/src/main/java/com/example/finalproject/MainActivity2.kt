package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
    //this function allows the user to add a weight into the database
    fun addWeight(view: View){
        val dbHandler = DBHandler(this, null,null,1)
        //variables to hold the information the user input
        var date: String = "";
        var inputWeight: Int = -5;
        var goodWeight: Boolean = true;
        //testing date so that it is MM/DD/YYYY
        if (weightTrackDate.text.SIZE != 10)
        {
            //if date is good input information in date variable
            date = weightTrackDate.text.toString()
            goodWeight = true;
        }
        else
        {
            //if date is bad input display how format should be
            Toast.makeText(applicationContext, "Date needs to be MM/DD/YYYY", Toast.LENGTH_SHORT).show()
            goodWeight = false;
        }
        //testing weight to make sure its in range of 0 and 999
        if (weight.text.toInt() > 0 && weight.text.toInt() < 999)
        {
            //if weight is good input weight
            inputWeight = weight.text.toInt()
        }
        else
        {
            //if weight is bad display range weight must be within
            Toast.makeText(applicationContext, "Weight must be between 0 and 999", Toast.LENGTH_SHORT).show()
            goodWeight = false;
        }
        //if goodWeight information input
        if (goodWeight)
        {
            //create a weight variable with class weightTrack
            val weight = WeightTrack(inputWeight,date)
            //adding the weight the user input to the database
            dbHandler.addWeightTrack(weight)
        }
        else
        {
            //if information is bad display input is bad
            Toast.makeText(applicationContext, "Data input for weight is bad", Toast.LENGTH_SHORT).show()
        }
    }
    //this function allows the user to delete a weight from the database
    fun deleteWeight(view: View){
        val dbHandler = DBHandler(this,null,null,1)
        //testing to make sure that id exist
        if(id.text.toString() != null){
            //deleting the weight the user input into the database
            val result = dbHandler.deleteWeightTrack(id.text.toString())
        }

    }
    //this function displays the weights input by the user
    fun readWeight(view: View){
        val dbHandler = DBHandler(this, null, null, 1)
        //reading the information from the database
        val result = dbHandler.readWeightTrack()
    }
    //function to allow teh user to update a weight in the database
    fun updateWeight(view: View) {
        var goodWeight: Boolean = true;
        val dbHandler = DBHandler(this, null, null, 1)
        if (id > 0 && id <= dbHandler.SIZE) {
            var idHolder: Int = id
        } else {
            goodWeight = false
        }

        if (weight.text.toInt() > 0 && weight.text.toInt() < 999) {
            var weightHolder: Int = weight
        } else {
            goodWeight = false
        }

        if (weightTrackDate.text.SIZE != 10){
            var dateHolder: String = weightTrackDate
        }
        else{
            goodWeight = false
        }
        if(goodWeight) {
            val result = dbHandler.updateWeightTrack(idHolder, weightHolder, dateHolder)
        }
    }
}