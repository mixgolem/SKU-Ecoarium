package com.example.ecoariumapp

import java.text.SimpleDateFormat
import java.util.*
//QR코드를 위한 타이머
fun convertIsoToCustomFormat(input: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = inputFormat.parse(input)

    val outputFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분", Locale.getDefault())
    outputFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul") // Seoul timezone

    return outputFormat.format(date)
}