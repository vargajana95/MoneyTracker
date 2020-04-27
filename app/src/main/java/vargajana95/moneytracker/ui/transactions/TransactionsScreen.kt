package vargajana95.moneytracker.ui.transactions

import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction

interface TransactionsScreen {
    fun showTransactions(transactions: List<Transaction>)
    fun transactionAdded(transaction: Transaction)
    fun showNewTransactionDialog(categories: List<Category>);
}