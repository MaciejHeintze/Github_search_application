package com.example.githubsearchapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubsearchapplication.repository_details.SingleRepositoryDetails
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_id.setOnClickListener {
            val intent = Intent(this, SingleRepositoryDetails::class.java)
            startActivity(intent)
        }
    }
}
