package com.hakanoguz33.huginclientapp

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.format.Formatter
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hakanoguz33.huginclientapp.view.SalesActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ip:TextView = findViewById(R.id.ipTextView)
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ipAddress: String = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
        ip.text = "Your Device IP Address: $ipAddress"

        thread {
            var s:Socket
            var isr :InputStreamReader
            var buff:BufferedReader
            var message:String
            var h:Handler
            try {
                var ss :ServerSocket = ServerSocket(6060)
                while (true)
                {
                    s=ss.accept()
                    isr = InputStreamReader(s.getInputStream())
                    buff = BufferedReader(isr)
                    message = buff.readLine()
                }
                h.post(Runnable {
                    run(){
                        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
                        println(message)
                        Log.e("olay",message)
                    }
                })
            }catch (e:IOException)
            {
                e.printStackTrace()
            }

        }

        var btn: Button = findViewById(R.id.btnConnection)

        btn.setOnClickListener {
            val intent:Intent = Intent(this,SalesActivity::class.java)
            startActivity(intent)
        }

    }
}