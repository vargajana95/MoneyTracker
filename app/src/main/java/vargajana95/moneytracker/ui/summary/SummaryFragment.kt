package vargajana95.moneytracker.ui.summary

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import vargajana95.moneytracker.R
import vargajana95.moneytracker.injector
import vargajana95.moneytracker.ui.transactions.SummaryPresenter
import vargajana95.moneytracker.ui.transactions.SummaryScreen
import javax.inject.Inject

class SummaryFragment : Fragment(), SummaryScreen {
    @Inject
    lateinit var summaryPresenter: SummaryPresenter


    override fun show(param: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        injector.inject(this)
        summaryPresenter.attachScreen(this)
    }

    override fun onDetach() {
        summaryPresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    companion object {
        private const val KEY_TRANSACTION_LIST = "KEY_SUMMARY"

        @JvmStatic
        fun newInstance(param1: String): SummaryFragment {
            val fragment = SummaryFragment()
            val bundle = Bundle()

            bundle.putString(KEY_TRANSACTION_LIST, param1)
            fragment.arguments = bundle
            return fragment
        }
    }
}
