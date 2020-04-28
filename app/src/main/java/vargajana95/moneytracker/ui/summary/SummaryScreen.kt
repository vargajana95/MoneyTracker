package vargajana95.moneytracker.ui.transactions

interface SummaryScreen {
    fun showBudget(expense: Int, income: Int, budget: Int)
}