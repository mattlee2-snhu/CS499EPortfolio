package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }
    //switch to toggle sms notifications cant figure out how to get this to work correctly
    Switch smsSwitch = findViewById(R.id.sms);
    smsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            if(isChecked){

            }
            else{
                //off
            }
        }
    }
}