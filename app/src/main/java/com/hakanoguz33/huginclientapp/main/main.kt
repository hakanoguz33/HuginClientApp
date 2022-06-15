package com.hakanoguz33.huginclientapp.main

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket

fun main(){
        val ss : ServerSocket = ServerSocket(6060)
        val client = ss.accept()
        val isr = InputStreamReader(client.getInputStream())
        val br = BufferedReader(isr)
        var message:String
        message = br.readLine()
        println(message)

}