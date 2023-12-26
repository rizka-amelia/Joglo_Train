package com.example.joglotrain.data

import android.provider.BaseColumns

internal class DatabaseContract {
 
    internal class UserColumns : BaseColumns {
        companion object {
            const val USER_TABLE_NAME = "user"
            const val _ID = "_id"
            const val USERNAME = "username"
            const val PHONENUMBER = "phonenumber"
            const val EMAIL = "email"
            const val PASSWORD = "password"
        }
    }

    internal class TicketColumns : BaseColumns {
        companion object {
            const val TICKET_TABLE_NAME = "ticket"
            const val _ID = "_id"
            const val TRAINNAME = "trainname"
            const val DESTINATION = "destination"
            const val DURATION = "duration"
            const val PRICE = "price"

            const val YOGYASOLO = "yogyasolo"
            const val SOLOYOGYA = "soloyogya"
        }
    }

    internal class CheckoutColumns : BaseColumns {
        companion object {
            const val CHECKOUT_TABLE_NAME = "checkout"
            const val _ID = "_id"
            const val DATE = "date"
            const val PASSANGERNAME = "passangername"
            const val DESTINATION = "destination"
            const val DURATION = "duration"
            const val PRICE = "price"
            const val JUMLAHPENUMPANG = "jumlahpenumpang"
        }
    }
}