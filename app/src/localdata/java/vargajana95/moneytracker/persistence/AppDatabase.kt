package vargajana95.moneytracker.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import vargajana95.moneytracker.persistence.dao.CategoryDAO
import vargajana95.moneytracker.persistence.dao.TransactionDAO

@Database(entities = [TransactionData::class, CategoryData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDAO
    abstract fun transactionDao(): TransactionDAO

    companion object {
        const val DATABASE_NAME = "moneytracker.db"
    }
}