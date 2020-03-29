package vargajana95.moneytracker.ui.transactions

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import vargajana95.moneytracker.R
import vargajana95.moneytracker.injector
import javax.inject.Inject

class TransactionListFragment : Fragment(), TransactionsScreen {
    @Inject
    lateinit var transactionsPresenter: TransactionsPresenter


    override fun show(term: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        return inflater.inflate(R.layout.fragment_transaction_list, container, false)
    }

    companion object {
        private const val KEY_TRANSACTION_LIST = "KEY_TRANSACTION_LIST"

        @JvmStatic
        fun newInstance(param1: String): TransactionListFragment {
            val fragment = TransactionListFragment()
            val bundle = Bundle()

            bundle.putString(KEY_TRANSACTION_LIST, param1)
            fragment.arguments = bundle
            return fragment
        }
    }


}
