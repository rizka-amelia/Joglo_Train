package com.example.joglotrain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketModel(
    var id: Int = 0,
    var trainName: String = "",
    var destination: String = "",
    var duration: String = "",
    var price: Int = 0,
    var passangerName: String = "",
    var date: String = "",
    var jumlahPenumpang: Int = 0
) : Parcelable