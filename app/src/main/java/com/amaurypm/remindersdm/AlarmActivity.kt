package com.amaurypm.remindersdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
    }

    fun click(view: View) {
        finish()
    }
}