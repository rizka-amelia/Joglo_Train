package com.example.joglotrain.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.joglotrain.data.DatabaseContract.CheckoutColumns.Companion.CHECKOUT_TABLE_NAME
import com.example.joglotrain.data.DatabaseContract.TicketColumns.Companion.TICKET_TABLE_NAME
import com.example.joglotrain.data.DatabaseContract.UserColumns.Companion.USERNAME
import com.example.joglotrain.data.DatabaseContract.UserColumns.Companion.USER_TABLE_NAME
import com.example.joglotrain.data.DatabaseContract.UserColumns.Companion._ID

class UserHelper(context: Context) {

    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryByUsername(username: String): Cursor {
        return database.query(USER_TABLE_NAME, null, "$USERNAME = ?", arrayOf(username), null, null, null, null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(USER_TABLE_NAME, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(USER_TABLE_NAME, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(USER_TABLE_NAME, "$_ID = '$id'", null)
    }


    fun queryAllTicket(): Cursor {
        return database.query(
            TICKET_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC",
            null
        )
    }

    fun deleteMyTicketById(id: String): Int {
        return database.delete(CHECKOUT_TABLE_NAME, "$_ID = '$id'", null)
    }

    fun updateMyTicketById(id: String, values: ContentValues?): Int {
        return database.update(CHECKOUT_TABLE_NAME, values, "$_ID = ?", arrayOf(id))
    }

    fun queryAllMyTicket(): Cursor {
        return database.query(
            CHECKOUT_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC",
            null
        )
    }

    fun insertCheckoutTicket(values: ContentValues?): Long {
        return database.insert(CHECKOUT_TABLE_NAME, null, values)
    }

    companion object {

        private var INSTANCE: UserHelper? = null

        fun getInstance(context: Context): UserHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserHelper(context)
            }
    }

}