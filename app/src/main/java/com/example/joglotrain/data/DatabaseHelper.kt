package com.example.joglotrain.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.joglotrain.data.DatabaseContract.CheckoutColumns.Companion.CHECKOUT_TABLE_NAME
import com.example.joglotrain.data.DatabaseContract.TicketColumns.Companion.TICKET_TABLE_NAME
import com.example.joglotrain.data.DatabaseContract.UserColumns.Companion.USER_TABLE_NAME

internal class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "dbapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_USER = "CREATE TABLE $USER_TABLE_NAME" +
            " (${DatabaseContract.UserColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            " ${DatabaseContract.UserColumns.USERNAME} TEXT NOT NULL," +
            " ${DatabaseContract.UserColumns.PHONENUMBER} TEXT NOT NULL," +
            " ${DatabaseContract.UserColumns.EMAIL} TEXT NOT NULL," +
            " ${DatabaseContract.UserColumns.PASSWORD} TEXT NOT NULL)"

        private const val SQL_CREATE_TABLE_TICKET = "CREATE TABLE $TICKET_TABLE_NAME" +
            " (${DatabaseContract.TicketColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            " ${DatabaseContract.TicketColumns.TRAINNAME} TEXT NOT NULL," +
            " ${DatabaseContract.TicketColumns.DESTINATION} TEXT NOT NULL," +
            " ${DatabaseContract.TicketColumns.DURATION} TEXT NOT NULL," +
            " ${DatabaseContract.TicketColumns.PRICE} INT NOT NULL)"

        private const val SQL_CREATE_TABLE_CHECKOUT = "CREATE TABLE $CHECKOUT_TABLE_NAME" +
            " (${DatabaseContract.CheckoutColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            " ${DatabaseContract.CheckoutColumns.DATE} TEXT NOT NULL," +
            " ${DatabaseContract.CheckoutColumns.PASSANGERNAME} TEXT NOT NULL," +
            " ${DatabaseContract.CheckoutColumns.DESTINATION} TEXT NOT NULL," +
            " ${DatabaseContract.CheckoutColumns.DURATION} TEXT NOT NULL," +
            " ${DatabaseContract.CheckoutColumns.PRICE} INT NOT NULL," +
            " ${DatabaseContract.CheckoutColumns.JUMLAHPENUMPANG} INT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_USER)
        db.execSQL(SQL_CREATE_TABLE_TICKET)
        db.execSQL(SQL_CREATE_TABLE_CHECKOUT)
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (0, 'Lodaya Tambahan', '${DatabaseContract.TicketColumns.YOGYASOLO}', '05.35 ----------- 55mnt ----------- 06.30', 220000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (1, 'Sancaka', '${DatabaseContract.TicketColumns.YOGYASOLO}', '06.45 ----------- 45mnt ----------- 07.30', 170000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (2, 'Malioboro Ekspress', '${DatabaseContract.TicketColumns.YOGYASOLO}', '09.31 ----------- 49mnt ----------- 10.20', 210000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (3, 'Sancaka', '${DatabaseContract.TicketColumns.YOGYASOLO}', '11.30 ----------- 45mnt ----------- 12.15', 170000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (4, 'Ranggajati', '${DatabaseContract.TicketColumns.YOGYASOLO}', '11.38 ----------- 49mnt ----------- 12.27', 225000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (5, 'Fajar Utama Solo', '${DatabaseContract.TicketColumns.YOGYASOLO}', '12.37 ----------- 48mnt ----------- 13.25', 225000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (6, 'Argo Wilis', '${DatabaseContract.TicketColumns.YOGYASOLO}', '13.38 ----------- 40mnt ----------- 14.18', 380000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (7, 'Lodaya', '${DatabaseContract.TicketColumns.YOGYASOLO}', '14.00 ----------- 50mnt ----------- 14.50', 240000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (8, 'Joglosemarkerto', '${DatabaseContract.TicketColumns.YOGYASOLO}', '14.53 ----------- 1j 7mnt ----------- 16.00', 60000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (9, 'Sancaka', '${DatabaseContract.TicketColumns.YOGYASOLO}', '17.15 ----------- 45mnt ----------- 18.00', 165000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (10, 'Lodaya Tambahan', '${DatabaseContract.TicketColumns.YOGYASOLO}', '18.31 ----------- 53mnt ----------- 19.24', 220000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (11, 'Wijaya Kusuma', '${DatabaseContract.TicketColumns.YOGYASOLO}', '18.40 ----------- 49mnt ----------- 19.29', 210000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (12, 'Bangunkarta', '${DatabaseContract.TicketColumns.YOGYASOLO}', '20.04 ----------- 50mnt ----------- 20.54', 200000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (13, 'Kertanegara', '${DatabaseContract.TicketColumns.YOGYASOLO}', '21.06 ----------- 49mnt ----------- 21.55', 185000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (14, 'Sancaka', '${DatabaseContract.TicketColumns.YOGYASOLO}', '22.15 ----------- 45mnt ----------- 23.00', 185000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (15, 'Bima', '${DatabaseContract.TicketColumns.YOGYASOLO}', '23.33 ----------- 40mnt ----------- 00.13', 375000)")

        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (16, 'Sancaka', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '01.09 ----------- 51mnt ----------- 02.00', 185000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (17, 'Malioboro Ekspress', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '02.39 ----------- 51mnt ----------- 03.30', 210000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (18, 'Joglosemarkerto', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '06.10 ----------- 55mnt ----------- 07.05', 210000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (19, 'Lodaya', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '07.20 ----------- 46mnt ----------- 08.06', 220000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (20, 'Bangunkarta', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '08.06 ----------- 49mnt ----------- 08.55', 205000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (21, 'Mataram', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '08.50 ----------- 46mnt ----------- 09.36', 210000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (22, 'Lodaya Tambahan', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '09.10 ----------- 48mnt ----------- 09.58', 230000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (23, 'Manahan Panoramic', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '09.45 ----------- 49mnt ----------- 10.34', 950000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (24, 'Sancaka', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '10.09 ----------- 51mnt ----------- 11.00', 165000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (25, 'Kertanegara', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '13.34 ----------- 51mnt ----------- 14.25', 195000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (26, 'Sancaka', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '14.24 ----------- 51mnt ----------- 15.15', 185000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (27, 'Senja Utama Solo', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '18.55 ----------- 47mnt ----------- 19.42', 210000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (28, 'Lodaya', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '19.10 ----------- 46mnt ----------- 19.56', 220000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (29, 'Sancaka', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '20.02 ----------- 48mnt ----------- 20.50', 185000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (30, 'Wijaya Kusuma', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '21.30 ----------- 1j 3mnt ----------- 22.33', 220000)")
        db.execSQL("INSERT INTO $TICKET_TABLE_NAME VALUES (31, 'Malabar', '${DatabaseContract.TicketColumns.SOLOYOGYA}', '22.05 ----------- 46mnt ----------- 22.51', 220000)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $USER_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TICKET_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $CHECKOUT_TABLE_NAME")
        onCreate(db)
    }
}