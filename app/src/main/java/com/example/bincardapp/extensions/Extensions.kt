package com.example.bincardapp.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openMap(context: Context, latitude: Double, longitude: Double) {
    val uri = Uri.parse("geo:$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}

fun openPhone(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
    context.startActivity(intent)
}