package vargajana95.moneytracker.ui.transactions

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_transaction_list.*
import kotlinx.android.synthetic.main.fragment_transaction_list.view.*
import vargajana95.moneytracker.R

import vargajana95.moneytracker.injector
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import javax.inject.Inject

class TransactionListFragment : Fragment(), TransactionsScreen, NewTransactionDialog.NewTransactionDialogListener {
    @Inject
    lateinit var transactionsPresenter: TransactionsPresenter


    private val transactionList: MutableList<Transaction> = mutableListOf()
    private var transactionsAdapter: TransactionsAdapter? = null


    override fun showTransactions(transactions: List<Transaction>) {
        swipeRefreshLayoutArtists.isRefreshing = false
        transactionList.clear()
        transactionList.addAll(transactions)

        transactionsAdapter?.notifyDataSetChanged()
    }

    override fun transactionAdded(transaction: Transaction) {
        transactionList.add(transaction)
        transactionsAdapter?.notifyItemInserted(transactionList.size)
    }

    override fun showNewTransactionDialog(categories: List<Category>) {
        val newTransactionDialog = NewTransactionDialog()
        newTransactionDialog.setTargetFragment(this,0)
        newTransactionDialog.categories = categories
        newTransactionDialog.show(parentFragmentManager, "new_transaction")
    }

    override fun onResume() {
        super.onResume()
        transactionsPresenter.refreshTransactions()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        injector.inject(this)
        transactionsPresenter.attachScreen(this)
    }

    override fun onDetach() {
        transactionsPresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_list, container, false)
        view.fab.setOnClickListener { v ->
            transactionsPresenter.createNewTransactionDialog()
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerViewTransactions.layoutManager = llm

        transactionsAdapter = TransactionsAdapter(requireContext(), transactionList)
        recyclerViewTransactions.adapter = transactionsAdapter

        swipeRefreshLayoutArtists.setOnRefreshListener {
            transactionsPresenter.refreshTransactions()
        }
    }

    override fun onCreateNewTransaction(transaction: Transaction) {
        transactionsPresenter.createNewTransaction(transaction)
    }

}
