package vargajana95.moneytracker.ui.summary

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_summary.*

import vargajana95.moneytracker.R
import vargajana95.moneytracker.injector
import javax.inject.Inject

class SummaryFragment : Fragment(), SummaryScreen {
    @Inject
    lateinit var summaryPresenter: SummaryPresenter

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

    override fun showBudget(expense: Int, income: Int, budget: Int) {
        tvExpenseValue.text = resources.getString(R.string.amount, expense)
        tvIncomeValue.text = resources.getString(R.string.amount, income)
        tvBudgetValue.text = resources.getString(R.string.amount, budget)
    }
}
