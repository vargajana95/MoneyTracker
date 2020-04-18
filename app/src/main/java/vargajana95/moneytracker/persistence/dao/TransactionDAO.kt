package vargajana95.moneytracker.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

import vargajana95.moneytracker.persistence.TransactionData

@Dao
interface TransactionDAO {
    @Query("""SELECT * FROM `transactiondata`""")
    fun getAllTransactions(): Single<List<TransactionData>>

    @Insert
    fun insertTransactions(vararg transaction: TransactionData)
}