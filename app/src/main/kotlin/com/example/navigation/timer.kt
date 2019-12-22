package com.example.navigation

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity



class timer : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        val seekBar=findViewById<SeekBar>(R.id.seekBar2)
        seekBar.setProgress(0)
        var x=0

        object: CountDownTimer(3000,30)
        {
            override fun onFinish() {

                seekBar.setProgress(100)
                val intent= Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)

            }

            override fun onTick(p0: Long) {
                x++
                seekBar.setProgress(x)




            }

        }.start()
    }
}
