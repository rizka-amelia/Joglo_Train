package com.example.joglotrain.helper

import android.database.Cursor
import com.example.joglotrain.data.DatabaseContract
import com.example.joglotrain.data.TicketModel
import com.example.joglotrain.data.UserModel

object MappingHelper {

    var jumlahPenumpang = 1
    var hargaTicket = 0
    var dateTicket = ""
    var duration = ""
    var destination = ""
    var dataUser: UserModel = UserModel()

    fun mapCursorToUserModel(cursor: Cursor?): UserModel {
        val userModel = UserModel()

        cursor?.apply {
            while (moveToNext()) {
                userModel.id = getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns._ID))
                userModel.username =
                    getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.USERNAME))
                userModel.email =
                    getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.EMAIL))
                userModel.phoneNumber =
                    getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.PHONENUMBER))
                userModel.password =
                    getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.PASSWORD))
            }
        }
        return userModel
    }

    fun mapCursorToAllTicket(cursor: Cursor?): ArrayList<TicketModel> {
        val ticketList = ArrayList<TicketModel>()

        cursor?.apply {
            while (moveToNext()) {
                ticketList.add(
                    TicketModel(
                        getInt(getColumnIndexOrThrow(DatabaseContract.TicketColumns._ID)),
                        getString(getColumnIndexOrThrow(DatabaseContract.TicketColumns.TRAINNAME)),
                        getString(getColumnIndexOrThrow(DatabaseContract.TicketColumns.DESTINATION)),
                        getString(getColumnIndexOrThrow(DatabaseContract.TicketColumns.DURATION)),
                        getInt(getColumnIndexOrThrow(DatabaseContract.TicketColumns.PRICE))
                    )
                )
            }
        }
        return ticketList
    }

    fun mapCursorToMyTickets(cursor: Cursor?): ArrayList<TicketModel> {
        val ticketList = ArrayList<TicketModel>()

        cursor?.apply {
            while (moveToNext()) {
                ticketList.add(
                    TicketModel(
                        id = getInt(getColumnIndexOrThrow(DatabaseContract.CheckoutColumns._ID)),
                        destination = getString(getColumnIndexOrThrow(DatabaseContract.CheckoutColumns.DESTINATION)),
                        duration = getString(getColumnIndexOrThrow(DatabaseContract.CheckoutColumns.DURATION)),
                        price = getInt(getColumnIndexOrThrow(DatabaseContract.CheckoutColumns.PRICE)),
                        passangerName = getString(getColumnIndexOrThrow(DatabaseContract.CheckoutColumns.PASSANGERNAME)),
                        date = getString(getColumnIndexOrThrow(DatabaseContract.CheckoutColumns.DATE)),
                        jumlahPenumpang = getInt(getColumnIndexOrThrow(DatabaseContract.CheckoutColumns.JUMLAHPENUMPANG))
                    )
                )
            }
        }
        return ticketList
    }
}
