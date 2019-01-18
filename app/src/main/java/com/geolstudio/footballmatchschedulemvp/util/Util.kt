package com.geolstudio.footballmatchschedulemvp.util

import android.view.View
import java.text.SimpleDateFormat
import java.util.*



fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.GONE
}

fun String.toLocaleTimeString(): String {
    val sdf = SimpleDateFormat("HH:mm:ssz")
    val date: Date = sdf.parse(this)

    val sdfNewFormat = SimpleDateFormat("HH:mm", Locale("ID"))
    val formattedTime:String = sdfNewFormat.format(date)

    return formattedTime
}

fun String.toSimpleDateString(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val date: Date = sdf.parse(this)

    val sdfNewFormat = SimpleDateFormat("E, dd MMM yyyy")
    val formattedDate:String = sdfNewFormat.format(date)

    return formattedDate
}

fun String.toSimpleLocaleDateString(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ssz")
    val date: Date = sdf.parse(this)

    val sdfNewFormat = SimpleDateFormat("E, dd MMM yyyy")
    val formattedDate:String = sdfNewFormat.format(date)

    return formattedDate
}

fun String.getDateTimeMillis() : Long {
    val strDateTime = this
    val sdf = SimpleDateFormat("E, dd MMM yyyy HH:mm")
    val date = sdf.parse(strDateTime)
    val millis = date.time

    return millis
}