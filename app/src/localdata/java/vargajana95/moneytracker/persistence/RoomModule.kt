package vargajana95.moneytracker.persistence

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import vargajana95.moneytracker.persistence.dao.CategoryDAO
import vargajana95.moneytracker.persistence.dao.TransactionDAO
import javax.inject.Singleton


@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
            //.fallbackToDestructiveMigration()
            //.allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providesTransactionDao(appDatabase: AppDatabase): TransactionDAO {
        return appDatabase.transactionDao()
    }

    @Singleton
    @Provides
    fun providesCategoryDao(appDatabase: AppDatabase): CategoryDAO {
        return appDatabase.categoryDao()
    }
}