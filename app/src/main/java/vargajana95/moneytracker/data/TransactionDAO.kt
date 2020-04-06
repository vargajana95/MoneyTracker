package vargajana95.moneytracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDAO {
    @Query("""SELECT * FROM `transaction`""")
    fun getAllTransactions(): List<Transaction>

    @Insert
    fun insertTransactions(vararg transaction: Transaction)
}