package vargajana95.moneytracker.ui.transactions

import vargajana95.moneytracker.model.TransactionResult

interface TransactionsScreen {
    fun showTransactions(transactions: List<TransactionResult>)
}