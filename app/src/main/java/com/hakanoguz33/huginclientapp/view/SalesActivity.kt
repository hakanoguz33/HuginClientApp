package com.hakanoguz33.huginclientapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.hakanoguz33.huginclientapp.MainActivity
import com.hakanoguz33.huginclientapp.R

class SalesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)
        val btn:Button = findViewById(R.id.btnGoBack)

        btn.setOnClickListener {
            val intent:Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}